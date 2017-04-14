package com.demo.springboot_quickstart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DBAutoNumber {

	/**
	 * 一次种子的最少数值
	 */
	private final static BigDecimal SEEDCACHE = new BigDecimal(200); // 数据库读入内存一次种子的最少数值
	
	/**
	 * 数据库增加一次种子的最少数值
	 */
	private final static BigDecimal SEEDDB = new BigDecimal(500); // 数据库增加一次种子的最少数值
	/**
	 * 自增长种子
	 */
	private static final BigDecimal SEED = new BigDecimal(1); // 自增长种子

	private static HashMap<String, BigDecimal> cacheID = new HashMap<String, BigDecimal>();

	private static HashMap<String, BigDecimal> DBID = new HashMap<String, BigDecimal>();

	static {
		cacheID.put("test.start", new BigDecimal(1));
		cacheID.put("test.end", new BigDecimal(99));
		DBID.put("test.start", new BigDecimal(100));
		DBID.put("test.end", new BigDecimal(300));
	}
	

	static synchronized BigDecimal requestID(String key, int lngNumber) throws Throwable{
		// '申请的个数不可以小于等于零
		if (lngNumber <= 0) {
			throw new Exception("申请的ID个数不能小于等于零.");
		}
		BigDecimal spNumber = BigDecimal.valueOf(lngNumber);
		BigDecimal spBegin = cacheID.get(key + ".start");
		BigDecimal spEnd = cacheID.get(key + ".end");
		BigDecimal ret = spBegin;
		if (spBegin.add(spNumber).compareTo(spEnd.add(SEED)) == 1) {
			Map<String,BigDecimal> map = dbGetNumber(key, getRequestNumberFromDB(lngNumber,spBegin,spEnd));
			spBegin = map.get("start");
			spEnd = map.get("end");
		}
		spBegin = ret.add(spNumber);
		cacheID.put(key + ".start", spBegin);
		cacheID.put(key + ".end", spEnd);
		return ret;
	}

	private static Map<String,BigDecimal> dbGetNumber(String key, BigDecimal requestNumberFromDB) throws Throwable {
		Map<String,BigDecimal> map = new HashMap<String,BigDecimal>();
		try {
			BigDecimal dbStartValue = DBID.get(key + ".start"); // 起始值
			BigDecimal dbEndValue = DBID.get(key + ".end"); //当前占用最大值
			BigDecimal originDBStartValue = dbStartValue;
			if (dbStartValue.add(requestNumberFromDB).compareTo(dbEndValue.add(SEED)) == 1) {
				dbStartValue = dbStartValue.add(requestNumberFromDB);
				dbEndValue = dbEndValue.add(getRequestNumberToDB(requestNumberFromDB));
				DBID.put(key + ".start", dbStartValue);
				DBID.put(key + ".end", dbEndValue);
			} else {
				DBID.put(key + ".start", dbStartValue.add(requestNumberFromDB));
			}
			map.put("start", originDBStartValue);
			map.put("end", originDBStartValue.add(requestNumberFromDB).subtract(SEED));
			return map;
		} catch (Throwable e) {

			throw e;
		} finally {

		}

	}
	
	/**
	 * 内存向DB请求的种子数量
	 * @param originRequestNumber
	 * @param spBegin
	 * @param spEnd
	 * @return
	 */
	public static BigDecimal getRequestNumberFromDB(int originRequestNumber,BigDecimal spBegin,BigDecimal spEnd)
	{
		BigDecimal originRequestNumberBigDecimal = new BigDecimal(originRequestNumber);
		originRequestNumberBigDecimal = originRequestNumberBigDecimal.subtract((spEnd.subtract(spBegin).add(SEED)));
		originRequestNumberBigDecimal = originRequestNumberBigDecimal.divide(SEEDCACHE, RoundingMode.UP).multiply(SEEDCACHE);
		return originRequestNumberBigDecimal;
	}
	
	
	public static BigDecimal getRequestNumberToDB(BigDecimal requestNumberFromDB)
	{
		BigDecimal requestNumberToDB = requestNumberFromDB.divide(SEEDDB, RoundingMode.UP).multiply(SEEDDB);
		return requestNumberToDB;
	}
	

	public static void main(String[] args) throws Throwable {
		
		int number =1;
		while(true)
		{
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter request number：");
		number = scanner.nextInt();
		if(number==0)break;
		System.out.println(requestID("test", number));

		System.out.println("=========cacheID=============");
		for (String key : cacheID.keySet()) {
			System.out.println(key + ":" + cacheID.get(key));

		}
		System.out.println("======================");
		System.out.println("=========DBID=============");
		for (String key : DBID.keySet()) {

			System.out.println(key + ":" + DBID.get(key));

		}
		System.out.println("======================");
		
		}
		
	
	}

}
