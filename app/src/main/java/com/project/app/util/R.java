package com.project.app.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class R {
    private String msg;
    private Object data;
    private int code;
    private boolean showMsg;



    public static R success() {
        return new R("成功", null, 200, false);
    }

    public static R success(Object data) {
        return new R("成功", data, 200, false);
    }

    public static R success(Object data, String msg) {
        return new R(msg, data, 200, true);
    }


    public static R fail() {
        return new R("失败", null, 500, true);
    }

    public static R fail(String msg) {
        return new R(msg, null, 500, true);
    }


    public static R toLogin(String msg) {
        return new R(msg, null, 403, true);
    }
}
