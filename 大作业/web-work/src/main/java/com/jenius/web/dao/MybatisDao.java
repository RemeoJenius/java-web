package com.jenius.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jenius.web.meta.Bylist;
import com.jenius.web.meta.Person;
import com.jenius.web.meta.Product;
import com.jenius.web.meta.ProductList;

public interface MybatisDao {
	
	@Select("select * from person")
	public List<Person> getBuyerInfo();
	
	
	@Select("select * from person where userName=#{name}")
	public Person getBuyerInfoByName(String name);
	
	@Results({
		@Result(property="summary",column="abstract"),
		@Result(property="image",column="address")
	})
	@Select("select c.id ,c.price,c.title,i.detail,c.abstract , i.address from content c ,images i where i.id=c.id")
	public List<ProductList> getBoughtList();
	
	@Select("select title from content,trx where contentId = content.id")
	public List<String> getTitleByContentId(); 
	
	@Select("select contentId from content,trx where contentId = content.id")
	public List<Integer> getTitleContentId(); 
	
	@Results({
		@Result(property="id",column="id"),
		@Result(property="summary",column="abstract"),
		@Result(property="image",column="address"),
		@Result(property="detail",column="detail")
		
	})
	@Select("select id  from content")
	public List<Integer> getContentId();
	@Results({
		@Result(property="id",column="id"),
		@Result(property="summary",column="abstract"),
		@Result(property="image",column="address"),
		@Result(property="detail",column="detail")
		
	})
	@Select("select c.* ,i.detail,i.address from content c ,images i where c.id=#{id} and i.id = #{id}")
	public Product getProductes(int id);
	
	@Results({
		@Result(property="buyTime",column="time"),
		@Result(property="buyPrice",column="price"),
		@Result(property="image",column="address")
	})
	@Select("select c.id ,t.price , t.time , title , address  from trx t ,content c , images where contentId = c.id and contentId = images.id")
	public List<Bylist> getBylistInfo();
	
	@Insert("insert into content(price,title,abstract) values(#{price},#{title},#{abstract})")
	public void insertProduct(@Param("price")long price,@Param("title")String title,@Param("abstract")String abstrac);
	
	@Insert("insert into images values(#{id},#{address},#{pname},#{detail})")
	public void insertImage(@Param("id")int id,@Param("address")String address,@Param("pname")String panme,@Param("detail")String detail);
	
	@Update("update  content set price=#{price} ,title=#{title},abstract =#{abstract} where id = #{id}")
	public void updateProduct(@Param("price")long price,@Param("title")String title,@Param("abstract")String abstrac,@Param("id")int id);
	
	@Update("update images   set  address= #{address},pname= #{pname},detail=#{detail} where id=#{id}")
	public void updateImage(@Param("id")int id,@Param("address")String address,@Param("pname")String panme,@Param("detail")String detail);
	
	@Results({
		
		@Result(property="summary",column="abstract"),
		@Result(property="image",column="address")
	})
	@Select("select c.id ,c.price,c.title,i.detail,c.abstract , i.address from content c ,images i where i.id=c.id and c.id=#{pid}")
	public ProductList getBoughtListById(@Param("pid")int pid);
	@Results({
		@Result(property="buyTime",column="time"),
	})
	@Insert("insert into trx(contentId,personId,price,time) values(#{contentId} , 0 ,#{price} ,#{buyTime})")
	public void insertOrder(@Param("contentId")int contentId,@Param("price")int price,@Param("buyTime")long buyTime);
	
	@Delete("delete from content where id=#{id}")
	public void deleteProductContent(@Param("id")int id);
	@Delete("delete from images where id=#{id}")
	public void deleteProductImages(@Param("id")int id);
}
