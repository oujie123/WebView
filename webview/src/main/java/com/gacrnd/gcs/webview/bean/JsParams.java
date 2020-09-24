package com.gacrnd.gcs.webview.bean;

import com.google.gson.JsonObject;

/**
 * @author Jack_Ou  created on 2020/9/24.
 */
public class JsParams {
    private String name;
    private JsonObject param;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonObject getParam() {
        return param;
    }

    public void setParam(JsonObject param) {
        this.param = param;
    }
}
