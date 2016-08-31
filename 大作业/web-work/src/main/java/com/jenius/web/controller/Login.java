package com.jenius.web.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jenius.web.dao.MybatisDao;
import com.jenius.web.dao.impl.Dao;
import com.jenius.web.dao.impl.JdbctemplateDao;
import com.jenius.web.meta.Bylist;
import com.jenius.web.meta.Person;
import com.jenius.web.meta.Product;
import com.jenius.web.meta.ProductList;

import net.sf.json.JSONObject;

@Controller
public class Login {
	private HttpSession session;
	private Person p;
	@RequestMapping("login")
	public String login(ModelMap map ,HttpServletRequest request) {
		return "login";
	}

	@RequestMapping("/index")
	public String login(ModelMap map, HttpServletRequest request, HttpServletResponse response)
			throws NoSuchAlgorithmException {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		Dao is = context.getBean("isLogin", Dao.class);
		p = context.getBean("person", Person.class);
		ApplicationContext context1 = new ClassPathXmlApplicationContext("application-context-dao.xml");
		MybatisDao dao = context1.getBean("mybatisDao", MybatisDao.class);
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		session = request.getSession();
		p.setUserName(userName);
		p.setPassword(password);
		Map<String, Object> root = new HashMap<String, Object>();
		Map<String, Object> RequestParameters = new HashMap<String, Object>();
		List<ProductList> pl = dao.getBoughtList();
		List<Integer> number =null;
		number =dao.getTitleContentId();
		HashMap<Integer , Integer> inte = new HashMap<Integer, Integer>();
		int k=0;
		List <Integer> ins = dao.getContentId();
		for(int i : ins)
		{
			inte.put(i, k);
			k++;
		}
		number = dao.getTitleContentId();
		for(int i : number)
		{
			pl.get(inte.get(i)).setIsBuy(true);
			pl.get(inte.get(i)).setIsSell(true);
		}
		root.put("RequestParameters", RequestParameters);
		RequestParameters.put("type", 0);
		if (is.IsLoginDao(p)) {
			session.setAttribute("username", userName);
			session.setAttribute("pwd", password);
			root.put("user", p);
			RequestParameters.put("type", 0);
			map.addAttribute("productList", pl);
			map.addAllAttributes(root);
			return "index";
		}
		String un = (String) session.getAttribute("username");
		String pwd = (String) session.getAttribute("pwd");
		p.setUserName(un);
		p.setPassword(pwd);
		if (is.IsLoginDao(p)) {
			root.put("user", p);
			root.put("RequestParameters", RequestParameters);
			map.addAttribute("productList", pl);
			map.addAllAttributes(root);
			String type = request.getParameter("type");
			if (type != null) {
				if (type.equals("1"))
					RequestParameters.put("type", 1);
			} else
				RequestParameters.put("type", 0);
			return "index";
		}
		map.addAttribute("productList", pl);
		map.addAllAttributes(root);

		return "index";

	}

	@RequestMapping("/logout")
	public String logout(ModelMap map) {
		session.invalidate();
		Map<String, Object> root = new HashMap<String, Object>();
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context-dao.xml");
		MybatisDao dao = context.getBean("mybatisDao", MybatisDao.class);
		List<ProductList> pl = dao.getBoughtList();
		HashMap<Integer , Integer> inte = new HashMap<Integer, Integer>();
		int k=0;
		List <Integer> ins = dao.getContentId();
		List<Integer> number =null;
		for(int i : ins)
		{
			inte.put(i, k);
			k++;
		}
		number = dao.getTitleContentId();
		for(int i : number)
		{
			System.out.println(i+"  "+inte.get(i));
			pl.get(inte.get(i)).setIsBuy(true);
			pl.get(inte.get(i)).setIsSell(true);
		}
		// map.addAttribute("isUser", false);
		map.addAttribute("productList", pl);
		map.addAllAttributes(root);

		return "index";
	}

	@RequestMapping("/show")
	public String showInfo(ModelMap map, HttpServletRequest request) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context-dao.xml");
		MybatisDao dao = context.getBean("mybatisDao", MybatisDao.class);
		Map<String, Object> root = new HashMap<String, Object>();
		ProductList pp=dao.getBoughtListById(Integer.valueOf(request.getParameter("id")));
		session = request.getSession();
		Product pd=dao.getProductes(Integer.valueOf(request.getParameter("id")));
		pd.setId(pp.getId());
		pd.setImage(pp.getImage());
		pd.setPrice(pp.getPrice());
		pd.setTitle(pp.getTitle());
		pd.setDetail(pp.getDetail());
		for(int i =0; i<dao.getTitleByContentId().size();i++)
		{
			System.out.println(dao.getTitleByContentId().get(i));
			if(dao.getTitleByContentId().get(i).equals(pp.getTitle()))
			{
				pd.setIsBuy(true);
				pd.setBuyPrice(dao.getBylistInfo().get(i).getBuyPrice());
			}
		}
		session.setAttribute("id", pd.getId());
		session.setAttribute("price", (int)pd.getPrice());
		System.out.println(session.getAttribute("id"));
		root.put("product",pd) ;
		Dao is = new Dao();
		String un = (String) session.getAttribute("username");
		String pwd = (String) session.getAttribute("pwd");
		p.setUserName(un);
		p.setPassword(pwd);
		if (is.IsLoginDao(p)) {
			// root.put("isUser", true);
			root.put("user", p);
			map.addAllAttributes(root);
			return "show";
		}
		map.addAllAttributes(root);
		return "show";
	}
	@RequestMapping("account")
	public String account(ModelMap map,HttpServletRequest request)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		ApplicationContext context1 = new ClassPathXmlApplicationContext("application-context-dao.xml");
		Bylist bylist = context.getBean("byList",Bylist.class);
		MybatisDao dao = context1.getBean("mybatisDao",MybatisDao.class);
		List<Bylist> list = dao.getBylistInfo();
		String un = (String) session.getAttribute("username");
		String pwd = (String) session.getAttribute("pwd");
		p.setUserName(un);
		p.setPassword(pwd);
		Map<String ,Object> root = new HashMap<String, Object>();
		root.put("buyList", list);
		Date d = new Date();
		root.put("user", p);
		d.getTime();//long
		System.out.println(d.getTime());
		System.out.println(list.get(0).getBuyTime());
		map.addAllAttributes(root);
		return "account";
	}
	@RequestMapping("public")
	public String pub(ModelMap map,HttpServletRequest request)
	{
		Map<String ,Object> root = new HashMap<String, Object>();
		Map<String ,Object> product = new HashMap<String, Object>();
		root.put("product", product);
		product.put("id", request.getParameter("id"));
		System.out.println(request.getParameter("id"));
		map.addAllAttributes(root);
		return "public";
	}
	@RequestMapping("publicSubmit")
	public String publicSubmit(ModelMap map,HttpServletRequest request) throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("utf-8");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		ApplicationContext context1 = new ClassPathXmlApplicationContext("application-context-dao.xml");
		MybatisDao dao = context1.getBean("mybatisDao", MybatisDao.class);
		Dao is = context.getBean("isLogin", Dao.class);
		Product p = new Product();
		p.setTitle(request.getParameter("title"));
		p.setSummary(request.getParameter("summary"));
		p.setImage(request.getParameter("image"));
		p.setDetail(request.getParameter("detail"));
		System.out.println(p.getSummary());
		p.setPrice(Long.valueOf(request.getParameter("price")));
		is.insertProduct(p);
		List<ProductList> pl = dao.getBoughtList();
		HashMap<Integer , Integer> inte = new HashMap<Integer, Integer>();
		int k=0;
		List <Integer> ins = dao.getContentId();
		List<Integer> number =null;
		for(int i : ins)
		{
			inte.put(i, k);
			k++;
		}
		number = dao.getTitleContentId();
		for(int i : number)
		{
			System.out.println(i+"  "+inte.get(i));
			pl.get(inte.get(i)).setIsBuy(true);
			pl.get(inte.get(i)).setIsSell(true);
		}
		Map<String ,Object> root = new HashMap<String, Object>();
		System.out.println("--------------");
		int kk=0;
		for( int i=0 ; i<dao.getContentId().size();i++)
		{
			System.out.println(i);
			kk=dao.getContentId().get(i);
		}
		System.out.println("--------------");
		System.out.println(kk);
		p.setId(kk);
		root.put("user", p);
		root.put("product", p);
		map.addAllAttributes(root);
		return "publicSubmit";
	}
	@RequestMapping("edit")
	public String edit(ModelMap map,HttpServletRequest request) throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("utf-8");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		ApplicationContext context1 = new ClassPathXmlApplicationContext("application-context-dao.xml");
		MybatisDao dao = context1.getBean("mybatisDao", MybatisDao.class);
		Product p = new Product();
		p=dao.getProductes(Integer.valueOf(request.getParameter("id")));
		int id= Integer.valueOf(request.getParameter("id"));
		p.setId(id);
		System.out.println("edit");
		System.out.println(p.getId());
		System.out.println(p.getImage());
		System.out.println(p.getDetail());
		List<ProductList> pl = dao.getBoughtList();
		HashMap<Integer , Integer> inte = new HashMap<Integer, Integer>();
		int k=0;
		List <Integer> ins = dao.getContentId();
		List<Integer> number =null;
		for(int i : ins)
		{
			inte.put(i, k);
			k++;
		}
		number = dao.getTitleContentId();
		for(int i : number)
		{
			System.out.println(i+"  "+inte.get(i));
			pl.get(inte.get(i)).setIsBuy(true);
			pl.get(inte.get(i)).setIsSell(true);
		}
		Map<String ,Object> root = new HashMap<String, Object>();
		root.put("user", p);
		root.put("product", p);
		map.addAllAttributes(root);
		return "edit";
	}
	@RequestMapping("api/buy")
	public String buy(HttpServletRequest request,HttpServletResponse response,ModelMap map)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		Dao d = context.getBean("isLogin",Dao.class);
		int id =(Integer) session.getAttribute("id");
		int price = Integer.parseInt(String.valueOf(session.getAttribute("price"))); 
		System.out.println(id);
		System.out.println(price);
		d.order(id,price,new Date().getTime());
		map.addAttribute("code", 200);
		map.addAttribute("message","succsess");
		map.addAttribute("result", true);
		return"{code:200,message:sucess,result:true}";
	
	}
	@RequestMapping("api/delete")
	public String deletePudent(HttpServletRequest request,HttpServletResponse response,ModelMap map)
	{
		System.out.println("delete");
		int id = Integer.valueOf(request.getParameter("id"));
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		Dao d = context.getBean("isLogin",Dao.class);
		System.out.println(id);
		d.deleteProduct(id);
		map.addAttribute("code", 200);
		map.addAttribute("message","succsess");
		map.addAttribute("result", true);
		return"{code:200,message:sucess,result:true}";
		
	}
	@RequestMapping("editSubmit")
	public String editSubmit(ModelMap map,HttpServletRequest request) throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("utf-8");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		Dao is = context.getBean("isLogin", Dao.class);
		Product p = new Product();
		p.setId(Integer.valueOf(request.getParameter("id")));
		p.setTitle(request.getParameter("title"));
		p.setSummary(request.getParameter("summary"));
		p.setSummary(request.getParameter("summary"));
		p.setImage(request.getParameter("image"));
		p.setDetail(request.getParameter("detail"));
		p.setPrice(Integer.valueOf(request.getParameter("price")));
		is.updateProduct(p);
		Map<String ,Object> root = new HashMap<String, Object>();
		root.put("product", p);
		map.addAllAttributes(root);
		return"editSubmit";
	}
	
}
