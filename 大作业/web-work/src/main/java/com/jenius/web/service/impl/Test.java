package com.jenius.web.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jenius.web.dao.MybatisDao;
import com.jenius.web.dao.impl.JdbctemplateDao;
import com.jenius.web.meta.Person;
@Controller
public class Test {
	@RequestMapping("/nihao")
	public void inter(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context-dao.xml");
		MybatisDao dao = context.getBean("mybatisDao",MybatisDao.class);
		List<Person> ps = dao.getBuyerInfo();
		for(Person p : ps )
		{
			System.out.println("  id:"+p.getId()+"  name: "+p.getUserName());
		}
	}
}
