package cn.itcast.surveypark.util;



public class App {

	public static void main(String[] args) throws Exception {
		String key = "q12_1";
		System.out.println(Integer.parseInt(key.substring(1, key.indexOf("_"))));
	}
}
