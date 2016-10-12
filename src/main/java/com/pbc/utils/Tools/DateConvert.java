package com.pbc.utils.Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

/**
 * 重写日期转换
 * <p>
 * Created by Alex on 2016/10/9.
 */
public class DateConvert implements Converter {

    public Object convert(Class arg0, Object arg1) {
        String p = String.valueOf(arg1);
        if (p == null || p.trim().length() == 0) {
            return null;
        }
        return DateTools.date2Str((Date) arg1);
//        try {
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            return df.parse(p);
//        } catch (Exception e) {
//            try {
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//                return df.parse(p.trim());
//            } catch (ParseException ex) {
//                return null;
//            }
//        }

    }
}

