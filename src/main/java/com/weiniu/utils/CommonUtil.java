package com.weiniu.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import javax.servlet.http.HttpServletRequest;

/**
 * 公共 有关的
 * 
 * @author yyx
 * @date 2016年11月7日 14:55:27
 * @version 2.0
 */

public class CommonUtil {
	/**
	 * 从请求中获取手机的类型
	 * @param request
	 * @return
	 */
//	public static String getMobieType(HttpServletRequest request){
//		String mobileType = "";
//		if(!CommonUtil.isEmpty(request)){
//			String accessType = request.getHeader("user-agent")==null?"":request.getHeader("user-agent");
//			if(!CommonUtil.isEmpty(accessType)){
//				accessType = accessType.toLowerCase();
//				if(accessType.indexOf("ios") != -1){
//					mobileType = "ios";
//				}
//				if(accessType.indexOf("android") != -1){
//					mobileType = "android";
//				}
//			}
//		}
//		return mobileType;
//	}
	
	/**
	 * 判断两个非空的字符串是否相等
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean checkEqualByStr(String str1, String str2){
		boolean index = false;
		if(isEmpty(str1) || isEmpty(str2)){
			return index;
		}
		if(str1.equals(str2)){
			index = true;
		}
		return index;
	}
	public static boolean isEmptyZero(Long str) {
		if (str == null) {
			return true;
		} else if( str == 0){
			return true;
		} else{
			return false;
		}
	}
	
	public static List<Map<String, Object>> listMapToLowerCase(
			List<Map<String, Object>> list) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				list.set(i, mapToLowerCase(map));
			}
		}
		return list;
	}
	/**
	 * 将map中的key都转换为小写
	 * @param inMap
	 * @return
	 */
	public static Map<String, Object> mapToLowerCase(Map<String, Object> inMap) {
		Map<String, Object> resultMap = null;
		if (inMap != null) {
			if (LinkedHashMap.class.isInstance(inMap)) {
				resultMap = new LinkedHashMap<String, Object>();
			} else if (HashMap.class.isInstance(inMap)) {
				resultMap = new HashMap<String, Object>();
			} else if(TreeMap.class.isInstance(inMap)){
				resultMap = new HashMap<String, Object>();
			} else {
				return inMap;
			}
			for (Iterator<Entry<String, Object>> it = inMap.entrySet()
					.iterator(); it.hasNext();) {
				Entry<String, Object> e = it.next();
				String new_key = e.getKey().toLowerCase();
				resultMap.put(new_key, e.getValue());
			}
		}
		return resultMap;
	}


	public static String changeEncode(String str, String fromEncode,
			String toEncode) throws UnsupportedEncodingException {
		String returnStr;
		returnStr = new String(str.getBytes(fromEncode), toEncode);
		return returnStr;
	}

	public static String changeEncodeToUtf8(String str)
			throws UnsupportedEncodingException {
		return changeEncode(str, "ISO-8859-1", "UTF-8");
	}
	

	public static List<String> changeToList(String str) {
		if (isEmpty(str)) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		int fromIndex = 0;
		int toIndex = 0;
		while ((toIndex = str.indexOf("},{", fromIndex)) > -1) {
			String s = str.substring(fromIndex, toIndex + 1);
			fromIndex = toIndex + 2;
			list.add(s);
		}
		String s = str.substring(fromIndex);
		if (!isEmpty(s)) {
			list.add(s);
		}
		return list;
	}

	/**
	 * 过滤空字符串
	 * @param str
	 * @return
	 */
	public static String trimNull(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(Object str) {
		if (str == null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(Long str) {
		if (str == null) {
			return true;
		} else {
			return false;
		}
	}
	

	public static boolean isEmpty(List<?> list) {
		if (list == null || list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(String[] arr) {
		if (arr == null || arr.length == 0) {
			return true;
		} else {
			return false;
		}
	}



	/**
	 * 从map中获取String
	 * 
	 * @date 2011-7-19
	 * @time 下午05:18:31
	 * @param map
	 * @param key
	 * @return
	 */
	public static String getMapString(Map<String, Object> map, String key) {
		if (map == null) {
			return null;
		}
		Object value_obj = map.get(key);
		if (value_obj == null) {
			return null;
		} else {
			return value_obj.toString().trim();
		}
	}

	/**
	 * 从map中获取long
	 * 
	 * @date 2011-7-19
	 * @time 下午05:18:20
	 * @param map
	 * @param key
	 * @return
	 */
	public static Long getMapLong(HashMap<String, Object> map, String key) {
		String value = getMapString(map, key);
		if (isEmpty(value)) {
			return null;
		} else {
			return Long.parseLong(value);
		}
	}

	public static Double getMapDouble(HashMap<String, Object> map, String key) {
		String value = getMapString(map, key);
		if (isEmpty(value)) {
			return null;
		} else {
			return Double.parseDouble(value);
		}
	}

	/**
	 * 从map中获取Int
	 * 
	 * @date 2011-7-19
	 * @time 下午05:18:20
	 * @param map
	 * @param key
	 * @return
	 */
	public static Integer getMapInt(HashMap<String, Object> map, String key) {
		String value = getMapString(map, key);
		if (isEmpty(value)) {
			return null;
		} else {
			return Integer.parseInt(value);
		}
	}
	/**
	 * 将字符串转换为Integer
	 * 
	 * @param value
	 * @return
	 */
	public static Integer parseInt(String value){
	    if(CommonUtil.isEmpty(value)){
	        return null;
	    }
	    try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
	}
	/**
	 * 在位数不到total的情况下前面补0,默认长度过长则裁剪
	 * 
	 * @date 2011-7-21
	 * @time 上午11:36:16
	 * @param number
	 * @param total
	 * @param cut_if_long
	 * @return
	 */
	public static String addZeroToNumber(Long number, int total) {
		if (number == null) {
			return null;
		}
		String number_string = number.toString();
		return addZeroToNumber(number_string, total, true);
	}

	/**
	 * 在位数不到total的情况下前面补0
	 * 
	 * @date 2011-7-21
	 * @time 上午11:36:16
	 * @param number
	 * @param total
	 * @param cut_if_long
	 * @return
	 */
	public static String addZeroToNumber(String number, int total,
			boolean cut_if_long) {
		if (number == null) {
			return null;
		}
		int length = number.length();
		if (length < total) {
			for (int i = length; i < total; i++) {
				number = "0" + number;
			}
		} else if (cut_if_long && length > total) {
			number = number.substring(length - total, length);
		}
		return number;
	}

	public static String format(String value, String type) {
		if ("".equals(value)) {
			value = "''";
		} else {
			if ("date".equals(type)) {
				value = "to_date('" + value + "','YYYY/MM/DD HH24:MI:SS')";
			} else if ("int".equals(type) || "string".equals(type)) {
				value = "'" + value + "'";
			}
		}

		return value;
	}
	

	public static String arrayToString(String[] arr) {
		if (arr == null) {
			return null;
		} else {
			String re = "";
			for (int i = 0; i < arr.length; i++) {
				re += arr[i] + ",";
			}
			if (!"".equals(re)) {
				re = re.substring(0, re.length() - 1);
			}
			return re;
		}
	}

	public static String listToString(List<String> list) {
		return listToString(list, ",");
	}

	public static String listToString(List<String> list, String split) {
		if (list == null) {
			return null;
		} else {
			String re = "";
			for (String s : list) {
				re += s + split;
			}
			if (!"".equals(re)) {
				re = re.substring(0, re.length() - 1);
			}
			return re;
		}
	}
	public static List<String> stringToList(String str, String split) {
		if (CommonUtil.isEmpty(str)) {
			return null;
		} 
		List<String> list = new ArrayList<String>();
		String[] strArray = str.split(split);
		for(String entity : strArray){
			list.add(entity);
		}
		return list;
	}
	/**
	 * 取得两个数组中的公共部分
	 * 
	 * @author ye
	 * @date 2011-11-6
	 * @time 上午12:19:06
	 * @updater
	 * @update-time
	 * @update-info
	 * @param new_phone_numbers
	 * @param old_phone_numbers
	 * @return
	 */
	public static List<String> getExistNumbers(List<String> new_phone_numbers,
			List<String> old_phone_numbers) {
		List<String> result_list = new ArrayList<String>(
				Arrays.asList(new String[old_phone_numbers.size()]));
		Collections.copy(result_list, old_phone_numbers);
		result_list.retainAll(new_phone_numbers);
		return result_list;
	}

	/**
	 * 取得第一个数组相对第二个数组新增的
	 * 
	 * @author ye
	 * @date 2011-11-6
	 * @time 上午12:19:25
	 * @updater
	 * @update-time
	 * @update-info
	 * @param new_phone_numbers
	 * @param old_phone_numbers
	 * @return
	 */
	public static List<String> getNewNumbers(List<String> new_phone_numbers,
			List<String> old_phone_numbers) {
		List<String> result_list = new ArrayList<String>(
				Arrays.asList(new String[new_phone_numbers.size()]));
		Collections.copy(result_list, new_phone_numbers);
		result_list.removeAll(old_phone_numbers);
		return result_list;
	}

	/**
	 * 取得第一个数组相对第二个数组缺少的
	 * 
	 * @author ye
	 * @date 2011-11-6
	 * @time 上午12:19:44
	 * @updater
	 * @update-time
	 * @update-info
	 * @param new_phone_numbers
	 * @param old_phone_numbers
	 * @return
	 */
	public static List<String> getRemoveNumbers(List<String> new_phone_numbers,
			List<String> old_phone_numbers) {
		List<String> result_list = new ArrayList<String>(
				Arrays.asList(new String[old_phone_numbers.size()]));
		Collections.copy(result_list, old_phone_numbers);
		result_list.removeAll(new_phone_numbers);
		return result_list;
	}
	public static Date addMonth(Date from_date, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(from_date);
		int new_month = c.get(Calendar.MONTH);
		new_month += month;
		int add_year = new_month / 12;
		new_month %= 12;
		if (add_year > 0) {
			int year = c.get(Calendar.YEAR);
			c.set(Calendar.YEAR, year + add_year);
		}
		c.set(Calendar.MONTH, new_month);
		return c.getTime();
	}

	public static boolean isBeforeNow(Date from_date) {
		Date now_date = new Date();
		if (from_date.before(now_date)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isBeforeOrEqualNow(Date from_date) {
		Date now_date = new Date();
		if (now_date.before(from_date)) {
			return false;
		} else {
			return true;
		}
	}

	public static int getDaysBetweenDate(Date from_date, Date to_date) {
		int days;
		days = (int) ((to_date.getTime() - from_date.getTime()) / (24 * 60 * 60 * 1000));
		return days;
	}

	public static double getDoubleLeft(double f, int place) {
		BigDecimal b = new BigDecimal(f);
		double f1 = b.setScale(place, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getRandomSubList(List list, int limit) {
		int size = list.size();
		if (size <= limit) {
			return list;
		} else {
			List retList = new ArrayList();
			int intRd = 0; // 存放随机数
			int count = 0; // 记录生成的随机数个数
			int flag = 0; // 是否已经生成过标志
			int[] intRet = new int[limit];
			Random rdm = new Random(System.currentTimeMillis());
			while (count < limit) {
				intRd = Math.abs(rdm.nextInt()) % size;
				for (int i = 0; i < count; i++) {
					if (intRet[i] == intRd) {
						flag = 1;
						break;
					} else {
						flag = 0;
					}
				}
				if (flag == 0) {
					retList.add(list.get(intRd));
					intRet[count] = intRd;
					count++;
				}
			}
			return retList;
		}

	}

	public static String getPhone(String phoneNo) {
		String returnPhone = null;
		if (phoneNo != null && !"".equals(phoneNo)) {
			phoneNo = phoneNo.replace("+86", "");
			String regEx = "[^0-9]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(phoneNo);
			// 替换与模式匹配的所有字符（即非数字的字符将被""替换）
			returnPhone = m.replaceAll("").trim();
			if (returnPhone.length() > 11) {
				returnPhone = returnPhone.substring(0, 11);
			}
			if (isMobileNO(returnPhone)) {
				// 是手机号
				return returnPhone;
			} else {
				return null;
			}
		}
		return returnPhone;
	}

	/**
	 * 判断是否手机号码
	 * @param mobile
	 * @return
	 */
	public static boolean isMobileNO(String mobile) {
		Pattern p = Pattern.compile("^((13[0-9])|(17[0-9])|(14[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}


	public static Float disCount(Float money, Float d) {
		if(money!=null){
			return money*d;
		}
		return null;
	}
	
	/**
	 * 2014-08-08  获取指定范围内的一个随机数(结果可能出现参数中的数字) hq
	 * @return
	 */
	public static int getRandomNum(int begin , int end){
		if(begin>end){
			int t = begin;
			begin = end;
			end = t;
		}
		int re = 0;
		int size = end - begin + 1;
		Random r = new Random(); 
		int n = r.nextInt()%size;
		re = Math.abs(n)+begin;
		return re;
	}
	/**
	 * 处理多张图片地址为
	 * type:图片的类型
	 * @param carPics
	 * @return
	 */
//	public static List<String>  getCarPicListByScale(String carPics, String width, String height){
//		if(!isEmpty(carPics)){
//			List<String> carPicList = new ArrayList<String>();
//			String[] picArray = carPics.split(",");
//			if(!isEmpty(picArray)){
//				for(String pic : picArray){
//					pic = UploadUtil.getCompletePicUrlByScale(pic, width, height);
//					carPicList.add(pic);
//				}
//				return carPicList;
//			}
//		}
//		return null;
//	};
	/**
	 * 处理多张图片地址为
	 * limit:图片的大小压缩质量
	 * 单位kb
	 * @param carPics
	 * @return
	 */
//	public static List<String>  getCarPicListByLimit(String carPics, String limit){
//		if(!isEmpty(carPics)){
//			List<String> carPicList = new ArrayList<String>();
//			String[] picArray = carPics.split(",");
//			if(!isEmpty(picArray)){
//				for(String pic : picArray){
//					pic = UploadUtil.getCompletePicUrlByLimit(pic, limit);
//					carPicList.add(pic);
//				}
//				return carPicList;
//			}
//		}
//		return null;
//	};
	
	public static String  getBrandLogoByCarSerial(String carSerial){
		if(!CommonUtil.isEmpty(carSerial) && carSerial.indexOf("http://img.hx2cars.com/upload/brandlogo/") == -1){
			return "http://img.hx2cars.com/upload/brandlogo/" + carSerial;
		}
		return carSerial;
	};
}
