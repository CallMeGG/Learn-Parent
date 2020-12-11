package com.gyp.jedis.api.channel.mysql;

import com.gyp.jedis.api.dao.TeacherMapper;
import com.gyp.jedis.api.dao.po.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: DbService
 * date 2020/3/12 15:37
 *
 * @author GYP
 * @version 1.0
 */
@Slf4j
@Service
public class DbService {
    @Autowired
    private TeacherMapper teacherMapper;

    public boolean doMulInsert(int count) {

        List<List<Teacher>> allList = new ArrayList<>();
        List<Teacher> list = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            list.add(new Teacher()
                    .setId(100000000 + i)
                    .setTname(i + "")
                    .setTpassword("123456")
                    .setSex(i % 2 == 0));
            if (i % 10000 == 0) {
                allList.add(list);
                if (count != i) {
                    list = new ArrayList<>();
                }
            }
        }
        allList.parallelStream().forEach(list1 -> {
            teacherMapper.mulInsert(list1);
        });
        log.info("插入完成");
        return false;
    }

}
