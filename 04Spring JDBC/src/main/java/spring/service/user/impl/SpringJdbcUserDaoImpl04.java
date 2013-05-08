package spring.service.user.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import spring.service.domain.User;
import spring.service.user.UserDao;

/* 
 * FileName : JdbcUserDaoImpl.java  ( Data Access Object ) 
 * :: Persistence(?) Layer 인터페이스 구현 한 RDBMS 와의  CRUD 구현 클래스
 *    
 *  [[ 아래의 사항을 고려하여 살펴보면...]]  
 * ㅇ 각 Method 의 반복적( 고정적(?) ) 인 부분은 ?  conn pstmt resultset 
 * ㅇ 각 Method 의  가변적인 부분은 ?    CRUD 
 * ㅇ SQLException이 발생한다면, 대응방법은 ?   
 *     - Connection 인스턴스 생성시 SQLException이 발생 한다면 이후 진행가능한가 
 *     - SQLException 발생은 Checked Exception 으로 반드시 try-catch 
 *    		::  복구 불가능한 SQLException 으로 인해 try-catch 를 반드시 기술해야한다.
 *    		::  이를 RuntimeException 으로 처리한다면....
 */
public class SpringJdbcUserDaoImpl04 extends JdbcDaoSupport implements UserDao {

	// /Field

	 private MessageSourceAccessor messageSourceAccessor; 
	 
	 public void setMessageSourceaccessor(MessageSourceAccessor messageSourceAccessor) {
		 System.out.println("::"+getClass()+".setMessageSourceAcessor() Call...");
		 this.messageSourceAccessor = messageSourceAccessor; 
	 }
	 
	 
	// /Constructor
	public SpringJdbcUserDaoImpl04() {
		System.out.println("::" + getClass()
				+ ".SpringJdbcUserDaoImpl04 디폴트 생성자 Call");
	}

	// /Method
	// ==> 회원정보 :: INSERT ( 회원가입 )
	public int addUser(User user) throws Exception {
		// ::JDBC 절차에 따른 instance 생성

		// 1단계 connection DataSource 처리
		// 2단계 INSERT 문을 생성및 전송 SQL 만 작성
		StringBuffer userInsert = new StringBuffer();
		
		userInsert.append("INSERT ");
		userInsert
				.append("INTO USERS( user_id,user_name,password,age,reg_date) ");
		userInsert.append("VALUES( ? , ? , ? , ? , ? )");

		// 3단계 INSERT 실행
		// jdbc 절차를 추상화 캡슐화화 org.springframework.jdbc.core.jdbcTemplate
		// Bean을 통해 JDBC 절차 수행
		// JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

		// INSERT parameter value 로 전달될 Object Array 생성
		return getJdbcTemplate().update(
				userInsert.toString(),

				new Object[] { user.getUserId(), user.getUserName(),
						user.getPassword(), user.getAge(), user.getRegDate() });

		// ==> API 확인
		// return jdbcTemplate.update(userInsert.toString(), args);

	}// end method

	// ==> 회원정보 :: SELECT ( 회원정보 검색 )
	public User getUser(String userId) throws Exception {

			//1단계 connection DataSource 처리 
			
			
			//2단계 SELECT 문 생성 및 전송  Sql문만 작성 ! 
			StringBuffer  userSelect = new StringBuffer();
			userSelect.append("SELECT ");
			userSelect.append("user_id, user_name, password, age, reg_date ");
			userSelect.append("FROM USERS ");
			userSelect.append("WHERE user_id = ?");
			

			//3단계 SELECT 실행
			//JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource); 
			
			// SELECT parameter value로 전달될 Object Array 생성 
			//Object[] args = new Object[]{userId}; 			 
			 
			//4단계 SELECT  실행후 Resultset ===>Domain Object Binding
			
			return(User)
				(getJdbcTemplate().queryForObject( userSelect.toString(),
														new Object[] {userId },
														new RowMapper() {
						
						public Object mapRow(ResultSet result,int rowNum) throws SQLException {
															
						
						User user = new User();
						user.setUserId(result.getString("user_id"));
						user.setUserName(result.getNString("user_name"));
						user.setPassword(result.getString("password"));
						user.setAge(result.getInt("age"));
						user.setRegDate(result.getTimestamp("reg_date"));
						
						return user;
							}	// end of anonymous inner class
						}	// end of query 
					) // end of query object method
			); // end of casting block 
		
	}// end of method

	// ==> 회원정보 :: UPDATE ( 회원정보 변경 )
	public int updateUser(User user) throws Exception {

		// 1단계 connection

		// 2단계 UPDATE 문 생성및 전송 SQL문만 작성

		StringBuffer userUpdate = new StringBuffer();
		userUpdate.append("UPDATE USERS ");
		userUpdate
				.append("SET  user_name = :name, password = :password,age = :age ");
		userUpdate.append("WHERE user_id = :id");

		return getJdbcTemplate().update(
				userUpdate.toString(),
				new Object[] { user.getUserName(), user.getPassword(),
						user.getAge(), user.getUserId() });

	}// end method

	// ==> 회원정보 :: DELETE ( 회원정보 삭제 )
	public int removeUser(String userId) throws Exception {

		// 1단계 connection

		// 2단계 DELETE 문 생성및 전송 SQL문만 작성

		StringBuffer userDelete = new StringBuffer();
		userDelete.append("DELETE ");
		userDelete.append("FROM USERS ");
		// userDelete.append("WHERE user_id = ?");
		userDelete.append("WHERE user_id = :?");

		// 3단계 UPDATA 실행
		/*
		 * NamedParameterJdbcTemplate jdbcTemplate = new
		 * NamedParameterJdbcTemplate(dataSource);
		 */
		// parameter value로 전달된 위한 map 생성및 value put
		/*
		 * Map paramMap = new HashMap(); paramMap.put("id", userId);
		 */
		return getJdbcTemplate().update(userDelete.toString(),
				new Object[] { userId });

	}// end of method

	// ==> 회원정보 :: SELECT ( 모든 회원 정보 검색 )
	public List<User> getUserList() throws Exception {

		// 1단계 connection

		// 2단계 SELECT 문 생성 및 전송
		StringBuffer userSelectAll = new StringBuffer();
		userSelectAll.append("SELECT ");
		userSelectAll.append("user_id, user_name, password, age, reg_date ");
		userSelectAll.append("FROM USERS ");

		// 3단계 SELECT 실행
		/* JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); */

		// 4단계 Select실행후 ResultSet ====> Domain Object Binding
		/*
		 * RowMapper rowMapper = new RowMapper() {
		 * 
		 * 
		 * };
		 */
		// =====API 확인

		return getJdbcTemplate().query(userSelectAll.toString(),
				new RowMapper() {
					public Object mapRow(ResultSet result, int rowNum)
							throws SQLException {

						User user = new User();
						user.setUserId(result.getString("user_id"));
						user.setUserName(result.getString("user_name"));
						user.setPassword(result.getString("password"));
						user.setAge(result.getInt("age"));
						user.setRegDate(result.getTimestamp("reg_date"));

						return user;
					}

				}

		);

	}// end of Method

}// end of class