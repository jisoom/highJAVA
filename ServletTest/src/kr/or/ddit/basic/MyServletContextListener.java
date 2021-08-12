package kr.or.ddit.basic;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener, ServletContextAttributeListener {
	
	public MyServletContextListener() {
		System.out.println("[MyServletContextListener]" + " 생성자 호출됨");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("[MyServletContextListener]" + " contextDestroyed() 메소드 호출됨");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("[MyServletContextListener]" + " contextInitialized() 메소드 호출됨");
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		System.out.println("[MyServletContextListener]" + " attributeAdded 메소드 호출됨 : " + scae.getName());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		System.out.println("[MyServletContextListener]" + " attributeRemoved 메소드 호출됨 : " + scae.getName());
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		System.out.println("[MyServletContextListener]" + " attributeReplaced 메소드 호출됨 : " + scae.getName());
	}
}
