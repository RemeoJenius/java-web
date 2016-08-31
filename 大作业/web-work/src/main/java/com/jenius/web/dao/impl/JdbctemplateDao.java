package com.jenius.web.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.jenius.web.meta.Person;
@Repository
public class JdbctemplateDao {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Person> getPersonInfo()
	{
		return this.jdbcTemplate.query("select * from person",new RowMapper<Person>(){

			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				Person p = new Person();
				p.setId(rs.getInt("id"));
				p.setNickName("nickName");
				p.setUserName(rs.getString("userName"));
				return p;
			}
			
		});
		
		
		
	}
}
