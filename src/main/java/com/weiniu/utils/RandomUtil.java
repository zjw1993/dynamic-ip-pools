package com.weiniu.utils;

public class RandomUtil {

	/**
	 * 获取指定范围内的随机整数
	 * @param max 最大数
	 * @return
	 */
    public static int randomInt(int max){
    	return (int) Math.floor(Math.random() * max);
    }
    
}
