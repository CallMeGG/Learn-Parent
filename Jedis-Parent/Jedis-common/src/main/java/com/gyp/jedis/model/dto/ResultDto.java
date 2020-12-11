package com.gyp.jedis.model.dto;


import com.gyp.jedis.constant.RequestStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Description: 通讯JSON数据封装类
 *
 * @author GYP
 * @version 1.0
 */
@ApiModel("统一响应结果")
@Data
public class ResultDto<T> implements Serializable {

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态(200:正常 -1:请求失败 -10000:尚未登录)", allowableValues = "200,-1,-10000")
    private final Integer code;

    /**
     * 提示信息
     */
    @ApiModelProperty(value = "错误提示信息")
    private final String msg;

    /**
     * 数据交互时间
     */
    @ApiModelProperty(value = "数据交互时间")
    private final Long time = System.currentTimeMillis();

    /**
     * 结果集
     */
    @ApiModelProperty(value = "相应结果")
    private final T result;

    public ResultDto() {
        this(RequestStatus.SUCCESS);
    }

    public ResultDto(RequestStatus requestStatus) {
        this(requestStatus.getCode(), requestStatus.getMsg());
    }

    public ResultDto(Integer code, String msg) {
        this(code, msg, null);
    }

    public ResultDto(RequestStatus requestStatus, T result) {
        this(requestStatus.getCode(), requestStatus.getMsg(), result);
    }

    public ResultDto(Integer code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
}
