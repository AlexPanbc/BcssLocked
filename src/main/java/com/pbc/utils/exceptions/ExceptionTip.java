package com.pbc.utils.exceptions;

/**
 * Created by LiuHuiChao on 2016/10/18.
 */
public class ExceptionTip {

    private Header header;

    public ExceptionTip() {
    }
    public ExceptionTip(String code) {
        if(header == null){
            header = new Header();
        }
        header.setErrorcode(code);
    }
    public ExceptionTip(String code, String errorinfo) {
        if(header == null){
            header = new Header();
        }
        header.setErrorcode(code);
        header.setErrorinfo(errorinfo);
    }
    public Header getHeader() {
        return header;
    }
    public void setHeader(String  code) {
        if(header == null){
            header = new Header();
        }
        header.setErrorcode(code);
    }
}
