package cn.itcast.surveypark.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ��������Դ
 */
public class TestDataSource {
	
	@Test
	public void getConn() throws Exception{
		/**
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		DataSource ds = (DataSource) ac.getBean("dataSource");
		System.out.println(ds.getConnection());
		*/
		String arr = "1,2,3,";
		arr = arr.substring(0,arr.lastIndexOf(","));
		System.out.println(arr);
	}
}
