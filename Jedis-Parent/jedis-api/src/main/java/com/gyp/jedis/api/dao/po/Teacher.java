package com.gyp.jedis.api.dao.po;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Title: Teacher
 * date 2020/3/12 14:55
 *
 * @author GYP
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class Teacher {
    private int id;
    private String tname;
    private String tpassword;
    private boolean sex;
    private Date addTime;
}
