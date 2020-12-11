package com.gyp.jedis.api.channel.jedis;

import com.gyp.jedis.api.dao.TeacherMapper;
import com.gyp.jedis.api.dao.po.Teacher;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Objects;

public class ThreadToMysql extends Thread {
    public String teacherName;
    public String password;
    public JedisPool pool;
    private TeacherMapper teacherMapper;

    public ThreadToMysql(String teacherName, String password, JedisPool pool, TeacherMapper teacherMapper) {//构造函数传入要查询登录的老师姓名和密码
        this.teacherName = teacherName;
        this.password = password;
        this.pool = pool;
        this.teacherMapper = teacherMapper;
    }

    public void run() {
        Jedis jedis = pool.getResource();
        Long startTime = System.currentTimeMillis();//开始时间
        if (jedis.get(teacherName) != null) {
            Long entTime = System.currentTimeMillis();//开始时间
            System.out.println(currentThread().getName() + " 缓存得到的结果: " + jedis.get(teacherName) + " 开始时间:" + startTime + "  结束时间:" + entTime + "  用时:" + (entTime - startTime) + "ms");
            pool.returnResource(jedis);
            System.out.println("释放该redis连接");
        } else {
            String t_name = null;
            try {
                Teacher teachers = teacherMapper.searchList(teacherName, password);
                if (!Objects.isNull(teachers)) {
                    t_name = teachers.getTname();
                    jedis.set(teacherName, t_name);
                    System.out.println("释放该连接");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                pool.returnResource(jedis);
                System.out.println("释放该连接");
            }
            Long end = System.currentTimeMillis();
            System.out.println(currentThread().getName() + "  数据库得到的查询结果:" + t_name + "   用时:" + (end - startTime) / 1000 + "s");
        }
    }

}