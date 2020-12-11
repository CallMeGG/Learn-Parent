package com.gyp.jedis.api.dao;

import com.gyp.jedis.api.dao.po.Teacher;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Title: TeacherMapper
 * date 2020/3/12 14:53
 *
 * @author GYP
 * @version 1.0
 */

public interface TeacherMapper {

    @SelectProvider(type = TeacherMapperSqlProvider.class, method = "searchList")
    Teacher searchList(@Param("name") String name, @Param("password") String password);

    @InsertProvider(type = TeacherMapperSqlProvider.class, method = "mulInsert")
    boolean mulInsert(@Param("list") List<Teacher> list);
}
