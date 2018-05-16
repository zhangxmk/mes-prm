package com.yonyou.mes.prm.temptask.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test111 {
public static void main(String[] args) {
//	String a = "777%25E7%2594%25A8%25E6%2588%25B7";
	try {
		 System.out.println("UTF-8");
		  String a = URLEncoder.encode("魏建巍", "UTF-8");//编码
		  System.out.println(a);
		  a = URLDecoder.decode(URLDecoder.decode(a,"UTF-8"),"UTF-8");
		  System.out.println(a);//还原
		  
		  //下面同理
		  System.out.println("/nGBK(百度就是用这种)");
		  a = URLEncoder.encode("001", "GBK");
		  System.out.println(a);
		  System.out.println(URLDecoder.decode(URLDecoder.decode(a,"GBK"),"GBK"));
	} catch (UnsupportedEncodingException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
}
}
