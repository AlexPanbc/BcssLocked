package com.pbc.utils.exceptions;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by LiuHuiChao on 2016/10/18.
 */
public class ResponseTip {
    private Header header;
    private Object body;

    public ResponseTip() {

    }

    public ResponseTip(Object body){
        if(header == null){
            header = new Header();
            header.setErrorcode("1000");//没有任何异常的时候，返回状态码1000
        }
        this.body = body;
    }

    public ResponseTip(String code) {
        if(header == null){
            header = new Header();
        }
        header.setErrorcode(code);
    }

    public Header getHeader() {
        return header;
    }
    public void setHeader(String code) {
        if(header == null){
            header = new Header();
        }
        header.setErrorcode(code);
    }
    public Object getBody() {
        return body;
    }
    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        JSONObject result = new JSONObject();
        if (this.header != null) {
            result.put("header", this.header);
        }
        if (this.body != null) {
            result.put("body", this.body);
        }
        return result.toString();
    }
}
