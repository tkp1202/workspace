package spring.service.user.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import spring.service.domain.User;
import spring.service.user.UserDao;
import spring.service.user.UserService;

/* 
 * FileName : JdbcUserDaoImpl.java  ( Data Access Object ) 
 * :: Persistence(?) Layer 인터페이스 구현 한 RDBMS 와의  CRUD 구현 클래스
 *    
 *  //========> 변경 추가된 부분 파악 !!
 *  //====> SQL을 캡슐화 추상화  sqlService를 injection 받음  
 *  
 */

public class SpringUserServiceImpl07 extends JdbcDaoSupport implements UserService {

	// /Field
	
	UserDao userDAO; 

	
	
	 public void setUserDAO(UserDao userDAO) {
		 System.out.println("::"+getClass()+".setUserDAO Call...");
		 this.userDAO = userDAO; 
	 }
	 
	 
	// /Constructor
	public SpringUserServiceImpl07() {
		System.out.println("::" + getClass()
				+ ".SpringUserServiceImpl07 디폴트 생성자 Call");
	}

	// /Method
	// ==> 회원정보 :: INSERT ( 회원가입 )
	public void addUser(User user) throws Exception {
		userDAO.addUser(user);
		// ::JDBC 절차에 따른 instance 생성

		// 1단계 connection DataSource 처리
		// 2단계 INSERT 문을 생성및 전송 SQL 만 작성
		

		// 3단계 INSERT 실행
		// jdbc 절차를 추상화 캡슐화화 org.springframework.jdbc.core.jdbcTemplate
		// Bean을 통해 JDBC 절차 수행
		// JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

		// INSERT parameter value 로 전달될 Object Array 생성
		
                                
	
	}// end method

	// ==> 회원정보 :: SELECT ( 회원정보 검색 )
	public User getUser(String userId) throws Exception {

			//1단계 connection DataSource 처리 
			
			
			//2단계 SELECT 문 생성 및 전송  Sql문만 작성 ! 
			

			//3단계 SELECT 실행
			//JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource); 
			
			// SELECT parameter value로 전달될 Object Array 생성 
			//Object[] args = new Object[]{userId}; 			 
			 
			//4단계 SELECT  실행후 Resultset ===>Domain Object Binding
			
			return userDAO.getUser(userId); 
				
		
	}// end of method

	// ==> 회원정보 :: UPDATE ( 회원정보 변경 )
	public void updateUser(User user) throws Exception {

		userDAO.updateUser(user);

	}// end method

		

	// ==> 회원정보 :: SELECT ( 모든 회원 정보 검색 )
	public List<User> getUserList(User user) throws Exception {


		return userDAO.getUserList();
	}


	


	

}// end of class