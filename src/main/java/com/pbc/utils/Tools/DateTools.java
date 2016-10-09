package com.pbc.utils.Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;



public class DateTools {
	public static final String FULL="yyyy-MM-dd HH:mm:ss.SSS";
	public static final String YEAR_SECOND = "yyyy-MM-dd HH:mm:ss";
	public static final String YEAR_MINUTE = "yyyy-MM-dd HH:mm";
	public static final String YEAR_HOUR = "yyyy-MM-dd HH";
	public static final String YEAR_DAY = "yyyy-MM-dd";
	public static final String S_YEAR_DAY = "yyyyMMdd";
	public static final String HOUR_SECOND = "HH:mm:ss";
	private static SimpleDateFormat sdf_FULL;
	private static SimpleDateFormat sdf_YEAR_SECOND;
	private static SimpleDateFormat sdf_YEAR_MINUTE;
	private static SimpleDateFormat sdf_YEAR_HOUR;
	private static SimpleDateFormat sdf_YEAR_DAY;
	private static SimpleDateFormat sdf_HOUR_SECOND;
	/**
	 * 格式化日期
	 * @param format
	 * @param date
	 * @return
	 */
	public static String format(String format, Date date){

		if(format == null || "".equals(format) || date==null)
			return "";
		SimpleDateFormat sdf = getFormator(format);
		return sdf.format(date);
	}

	private static SimpleDateFormat getFormator(String format){

		SimpleDateFormat sdf;
		if(FULL.equals(format)){
			if(sdf_FULL == null)
				sdf_FULL = new SimpleDateFormat(FULL);
			sdf = sdf_FULL;
		} else if(YEAR_SECOND.equals(format)) {
			if(sdf_YEAR_SECOND==null)
				sdf_YEAR_SECOND = new SimpleDateFormat(YEAR_SECOND);
			sdf = sdf_YEAR_SECOND;
		} else if(YEAR_MINUTE.equals(format)) {
			if(sdf_YEAR_MINUTE == null)
				sdf_YEAR_MINUTE = new SimpleDateFormat(YEAR_MINUTE);
			sdf = sdf_YEAR_MINUTE;
		} else if(YEAR_HOUR.equals(format)) {
			if(sdf_YEAR_HOUR == null)
				sdf_YEAR_HOUR = new SimpleDateFormat(YEAR_HOUR);
			sdf = sdf_YEAR_HOUR;
		} else if(YEAR_DAY.equals(format)) {
			if(sdf_YEAR_DAY == null)
				sdf_YEAR_DAY = new SimpleDateFormat(YEAR_DAY);
			sdf = sdf_YEAR_DAY;
		} else if(HOUR_SECOND.equals(format)) {
			if(sdf_HOUR_SECOND == null)
				sdf_HOUR_SECOND = new SimpleDateFormat(HOUR_SECOND);
			sdf = sdf_HOUR_SECOND;
		} else {
			sdf = (new SimpleDateFormat(format));
		}
		return sdf;
	}

	/**
	 * 随机生成六位数验证码
	 * @return
	 */
	public static int getRandomNum(){
		 Random r = new Random();
		 return r.nextInt(900000)+100000;//(Math.random()*(999999-100000)+100000)
	}

	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}

	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		if(date!= null){
			return date2Str(date,"yyyy-MM-dd HH:mm:ss");
		}else{
			return "";
		}
	}

	public static String date2StrDay(Date date){
		if(date!= null){
			return date2Str(date,"yyyy-MM-dd");
		}else{
			return "";
		}
	}

	public static String date2StrMonth(Date date){
		if(date!= null){
			return date2Str(date,"yy-MM");
		}else{
			return "";
		}
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static List<Date> str2DateList(String date){
		if(StringUtils.isNotBlank(date)){
			String[] dateArr = date.split(" - ");
			if(null != dateArr&&dateArr.length==2){
				List<Date> dateList = new ArrayList<Date>();
				Date startDate = str2DateNoSS(dateArr[0]);
				dateList.add(startDate);
				Date endDate = str2DateNoSS(dateArr[1]);
				dateList.add(endDate);
				return dateList;
			}
		}
		return null;
	}



	public static List<String> str2DateStrList(String date){
		if(StringUtils.isNotBlank(date)){
			String[] dateArr = date.split(" - ");
			if(null != dateArr&&dateArr.length==2){
				List<String> dateList = new ArrayList<String>();
				String startDate = dateArr[0].replaceAll(" ", "");
				dateList.add(startDate);
				String endDate = dateArr[1].replaceAll(" ", "");
				dateList.add(endDate);
				return dateList;
			}
		}
		return null;
	}

	public static List<Date> str2DateList(String date,String format){
		if(StringUtils.isNotBlank(date)){
			String[] dateArr = date.split("-");
			if(null != dateArr&&dateArr.length==2){
				List<Date> dateList = new ArrayList<Date>();
				Date startDate = str2DateFormat(dateArr[0].replaceAll(" ", ""),format);
				dateList.add(startDate);
				Date endDate = str2DateFormat(dateArr[1].replaceAll(" ", ""),format);
				dateList.add(endDate);
				return dateList;
			}
		}
		return null;
	}

	public static Date str2DateFormat(String date,String format){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}

	public static Date str2DateNoSS(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}

	/**
	 *
	 * @Title: StrToDate
	 * @Description: 字符串转date
	 * @author zouyan
	 * @param @param str
	 * @param @param format
	 * @param @return
	 * @return Date
	 * @throws
	 */
	public static Date StrToDate(String str,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
	    Date date = null;
	    try {
	    	date = sdf.parse(str);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    return date;
	}


	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
            String a = sdf.format(date);
			return a;
		}else{
			return "";
		}
	}

	/**
	 * 把时间根据时、分、秒转换为时间段
	 * @param StrDate
	 */
	public static String getTimes(String StrDate){
		String resultTimes = "";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date now;

	    try {
	    	now = new Date();
	    	Date date=df.parse(StrDate);
	    	long times = now.getTime()-date.getTime();
	    	long day  =  times/(24*60*60*1000);
	    	long hour = (times/(60*60*1000)-day*24);
	    	long min  = ((times/(60*1000))-day*24*60-hour*60);
	    	long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);

	    	StringBuffer sb = new StringBuffer();
	    	//sb.append("发表于：");
	    	if(hour>0 ){
	    		sb.append(hour+"小时前");
	    	} else if(min>0){
	    		sb.append(min+"分钟前");
	    	} else{
	    		sb.append(sec+"秒前");
	    	}

	    	resultTimes = sb.toString();
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }

	    return resultTimes;
	}

	/**
	 * 写txt里的单行内容
	 * @param fileP  文件路径
	 * @param content  写入的内容
	 */
	public static void writeFile(String fileP,String content){
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if(filePath.indexOf(":") != 1){
			filePath = File.separator + filePath;
		}
		try {
	        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");
	        BufferedWriter writer=new BufferedWriter(write);
	        writer.write(content);
	        writer.close();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	  * 验证邮箱
	  * @param email
	  * @return
	  */
	 public static boolean checkEmail(String email){
	  boolean flag = false;
	  try{
	    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher(email);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }

	 /**
	  * 验证手机号码
	  * @param mobileNumber
	  * @return
	  */
	 public static boolean checkMobileNumber(String mobileNumber){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
	    Matcher matcher = regex.matcher(mobileNumber);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }

	/**
	 * 检测KEY是否正确
	 * @param paraname  传入参数
	 * @param FKEY		接收的 KEY
	 * @return 为空则返回true，不否则返回false
	 */
//	public static boolean checkKey(String paraname, String FKEY){
//		paraname = (null == paraname)? "":paraname;
//		return MD5.md5(paraname+DateUtil.getDays()+",fh,").equals(FKEY);
//	}

	/**
	 * 读取txt里的单行内容
	 * @param fileP  文件路径
	 */
	public static String readTxtFile(String fileP) {
		try {

			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if(filePath.indexOf(":") != 1){
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { 		// 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
				new FileInputStream(file), encoding);	// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			}else{
				System.out.println("找不到指定的文件,查看此路径是否正确:"+filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}


	public static void main(String[] args) {
		System.out.println(getRandomNum());
	}


	public static Map<String, Date> getStartAndEndInMonth(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,Date> map = new HashMap<String,Date>();
		try {
			Date date = sdf.parse(dateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			calendar.set(Calendar.DAY_OF_MONTH, 1);
			Date monthFirst = calendar.getTime();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date monthLast = calendar.getTime();

			map.put("start", monthFirst);
			map.put("end", monthLast);

//			System.out.println((java.sql.Date)monthFirst+"---"+(java.sql.Date)monthLast);
		} catch (ParseException e) {//异常获取当月的
			map.clear();
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			Date monthFirst = calendar.getTime();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date monthLast = calendar.getTime();

			map.put("start", monthFirst);
			map.put("end", monthLast);
		}
		return map;
	}

	/**
	 *
	 * @Title: hasSame
	 * @Description: 判断List是否存在重复数据
	 * @author songzhipeng
	 * @param @param list
	 * @param @return
	 * @return boolean  true重复/false不重复
	 * @throws
	 */
	public static boolean hasSame(List<? extends Object> list) {
		if (null == list)
			return false;
		return list.size() == new HashSet<Object>(list).size();
	}

	/**
	 *
	 * @Title: hasEqualSame
	 * @Description: 判断List数据是否完全相同
	 * @author songzhipeng
	 * @param @param list
	 * @param @return
	 * @return boolean  true相同/false不相同
	 * @throws
	 */
	public static boolean hasEqualSame(List<? extends Object> list) {
		if (null == list)
			return false;
		return 1 == new HashSet<Object>(list).size();
	}
}
