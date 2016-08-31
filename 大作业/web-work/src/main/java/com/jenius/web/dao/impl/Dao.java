package com.jenius.web.dao.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jenius.web.dao.MybatisDao;
import com.jenius.web.meta.Person;
import com.jenius.web.meta.Product;
import com.jenius.web.util.MD5andKL;


public class Dao {
	public boolean IsLoginDao(Person p)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context-dao.xml");
		MybatisDao dao = context.getBean("mybatisDao",MybatisDao.class);
		String userName="";
		String password = "";
		try {
			userName = dao.getBuyerInfoByName(p.getUserName()).getUserName();
			password = dao.getBuyerInfoByName(p.getUserName()).getPassword();
		} catch (Exception e) {
			((ConfigurableApplicationContext)context).close();
			return false;
		}
		
		p.setPassword(MD5andKL.MD5(p.getPassword()));
		if(p.getUserName().equals(userName)&&p.getPassword().equals(password))
		{
			p.setUsertype(dao.getBuyerInfoByName(userName).getUsertype());
			((ConfigurableApplicationContext)context).close();
			return true;
		}
		((ConfigurableApplicationContext)context).close();
		return false;
	}
	public void insertProduct(Product p)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context-dao.xml");
		MybatisDao dao = context.getBean("mybatisDao",MybatisDao.class);
		System.out.println(p.getPrice()+"   "+p.getTitle()+"     "+p.getDetail()+"   "+p.getId());
		dao.insertProduct(p.getPrice(), p.getTitle(), p.getDetail());
		dao.insertImage(p.getId(), p.getImage(), p.getTitle(),p.getDetail());
		
	}
	
	public Product getProductInfo()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context-dao.xml");
		MybatisDao dao = context.getBean("mybatisDao",MybatisDao.class);
		Product p = new Product();
		System.out.println("getProduct");
		
		return p;
	}
	public void updateProduct(Product p)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context-dao.xml");
		MybatisDao dao = context.getBean("mybatisDao",MybatisDao.class);
		System.out.println("update Dao");
		System.out.println(p.getPrice()+"   "+p.getTitle()+"     "+p.getDetail()+"   "+p.getId());
		dao.updateProduct(p.getPrice(), p.getTitle(), p.getDetail(),p.getId());
		dao.updateImage(p.getId(), p.getImage(), p.getTitle(),p.getDetail());
		
	}
	public boolean order(int contentId,int price,long time)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context-dao.xml");
		MybatisDao dao = context.getBean("mybatisDao",MybatisDao.class);
		dao.insertOrder(contentId, price, time);
		return true;
	}
	public boolean deleteProduct(int id)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context-dao.xml");
		MybatisDao dao = context.getBean("mybatisDao",MybatisDao.class);
		dao.deleteProductContent(id);
		dao.deleteProductImages(id);
		return true;
	}
	
	
}
