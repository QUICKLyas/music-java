package com.music.commons.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
@NoArgsConstructor
@AllArgsConstructor
public enum CodeEnum {
    SUCCESS(200,"操作成功"),
    SUCCESS_BUT_NO_DATA(200,"请求成功，数据不存在"),
    SUCCESS_BUT_HAS_DATA(200, "请求成功，数据已存在"),
    SUCCESS_BUT_BUSINESS_DEAL(200, "请求成功，但是业务处理上有处理"),
    SUCCESS_BUT_CHANNEL_NOT_ALLOWED(200, "请求成功，但是该操作不允许在当前渠道执行"),
    BAD_REQUEST(400, "请求参数错误"),
    BAD_REQUEST_ILLEGAL_PARAM(400, "请求参数错误"),
    BAD_REQUEST_AUTH(401, "认证失败"),
    BAD_REQUEST_AUTH_NO_SESSION(401, "认证失败，用户未登录"),
    BAD_REQUEST_FORBIDDEN(403, "资源无权限"),
    BAD_REQUEST_FORBIDDEN_FALSE_CHANNEL(403, "资源无权限，该操作不允许在当前渠道执行"),
    BAD_REQUEST_NOTFOUND(404, "资源不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器错误");

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
