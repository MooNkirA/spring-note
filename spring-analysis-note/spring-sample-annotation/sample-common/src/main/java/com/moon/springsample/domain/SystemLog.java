package com.moon.springsample.domain;

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
public class SystemLog implements Serializable {

    private String id;      // 日志的主键
    private String method;  // 当前执行的操作方法名称
    private String action;  // 当前执行的操作方法说明
    private Date time;      // 执行时间
    private String remoteIP;// 来访者IP

    public SystemLog() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRemoteIP() {
        return remoteIP;
    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SystemLog.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("method='" + method + "'")
                .add("action='" + action + "'")
                .add("time=" + time)
                .add("remoteIP='" + remoteIP + "'")
                .toString();
    }
}
