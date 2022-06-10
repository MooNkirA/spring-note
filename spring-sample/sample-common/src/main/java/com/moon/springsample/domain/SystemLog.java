package com.moon.springsample.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

/**
 * 系统日志的实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-9 16:08
 * @description
 */
@Data
public class SystemLog implements Serializable {

    private String id;      // 日志的主键
    private String method;  // 当前执行的操作方法名称
    private String action;  // 当前执行的操作方法说明
    private Date time;      // 执行时间
    private String remoteIP;// 来访者IP

}
