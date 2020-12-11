package com.gyp.jedis.api.dao;

import com.gyp.jedis.api.dao.po.Teacher;
import com.gyp.jedis.util.TimeFormatUtil;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Title: TeacherMapperSqlProvider
 * date 2020/3/12 14:58
 *
 * @author GYP
 * @version 1.0
 */
public class TeacherMapperSqlProvider {
    public String searchList(@Param("name") String name, @Param("password") String password) {
        String sql = "select tname from teacher where tname='" + name + "' and tpassword='" + password + "' ";
        return sql;
    }

    public String mulInsert(@Param("list") List<Teacher> list) {
        StringBuffer sql = new StringBuffer("insert into teacher values ");
        for (Teacher teacher : list) {
            sql.append(" ( ");
            sql.append("" + teacher.getId() + ",");
            sql.append("'" + teacher.getTname() + "',");
            sql.append("'" + teacher.getTpassword() + "',");
            sql.append("" + teacher.isSex() + ",");
            sql.append("'" + TimeFormatUtil.format(new Date()) + "'");
            sql.append(" ), ");
        }
        String sqlStr = sql.toString();
        sqlStr = sqlStr.substring(0, sqlStr.length() - 2) + ";";
        return sqlStr;
    }
}
