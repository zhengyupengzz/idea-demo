package cn.itxdl.client;

import java.util.Scanner;

public class ClientScanner {
	
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * 自定义成员方法将创建好的扫描器对象返回出去
	 * @return 扫描器对象
	 */
	public static Scanner getSc() {
		return sc;
	}
	
	/**
	 * 自定义成员方法实现扫描器的关闭
	 */
	public static void closeSc() {
		sc.close();
	}
	
}
