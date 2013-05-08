package spring.service.user.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import spring.service.domain.User;
import spring.service.user.UserDao;

/* 
 * FileName : JdbcUserDaoImpl.java  ( Data Access Object ) 
 * :: Persistence(?) Layer 인터페이스 구현 한 RDBMS 와의  CRUD 구현 클래스
 *    
 *  //========> 변경 추가된 부분 파악 !!
 *  // 시스템에 의존적 내용 상수 배포시 결정되는 정보는 반드시 분류 관리 되어야 한다 
 *  // SQL를  properties meta-data로 관리 
 *  // SQL을 갖는 properties 의 정보를 map으로 injection 받아 사용 
 *  
 *  
 */
public class SpringJdbcUserDaoImpl05 extends JdbcDaoSupport implements UserDao {

	// /Field
	
	private  Map<String, String> sqlMap;

	
	
	 public void setsqlMap(Map<String, String> sqkMap) {
		 System.out.println("::"+getClass()+".setSqlMap Call...");
		 this.sqlMap = sqlMap; 
	 }
	 
	 
	// /Constructor
	public SpringJdbcUserDaoImpl05() {
		System.out.println("::" + getClass()
				+ ".SpringJdbcUserDaoImpl05 디폴트 생성자 Call");
	}

	// /Method
	// ==> 회원정보 :: INSERT ( 회원가입 )
	public int addUser(User userVO) {
		// ::JDBC 절차에 따른 instance 생성

		// 1단계 connection DataSource 처리
		// 2단계 INSERT 문을 생성및 전송 SQL 만 작성
		

		// 3단계 INSERT 실행
		// jdbc 절차를 추상화 캡슐화화 org.springframework.jdbc.core.jdbcTemplate
		// Bean을 통해 JDBC 절차 수행
		// JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

		// INSERT parameter value 로 전달될 Object Array 생성
		return getJdbcTemplate().update(
				this.sqlMap.get("add"),
				new Object[] { userVO.getUserId(), 
							userVO.getUserName(),
							userVO.getPassword(), 
							userVO.getAge(),
							userVO.getRegDate() });
                                
	
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
			
			return(User)
				(getJdbcTemplate().queryForObject( this.sqlMap.get("get"),
													new Object[] { userId },
													new RowMapper() {
					
					
						public Object mapRow(ResultSet result,int rowNum) throws SQLException {
															
						
						User user = new User();
						user.setUserId(result.getString("user_id"));
						user.setUserName(result.getNString("user_name"));
						user.setPassword(result.getString("password"));
						user.setAge(result.getInt("age"));
						user.setRegDate(result.getTimestamp("reg_date"));
						
						return user;
							}	// end of MapRow inner class
						}	// end of query 
					) // end of query object method
			); // end of casting block 
		
	}// end of method

	// ==> 회원정보 :: UPDATE ( 회원정보 변경 )
	public int updateUser(User user) throws Exception {

		// 1단계 connection

		// 2단계 UPDATE 문 생성및 전송 SQL문만 작성


		return getJdbcTemplate().update(
				this.sqlMap.get("update"),
				new Object[] { user.getUserName(), 
								user.getPassword(),
								user.getAge(), 
								user.getUserId() });

	}// end method

	// ==> 회원정보 :: DELETE ( 회원정보 삭제 )
	public int removeUser(String userId) throws Exception {

		// 1단계 connection

		// 2단계 DELETE 문 생성및 전송 SQL문만 작성

		
		// 3단계 UPDATA 실행
		/*
		 * NamedParameterJdbcTemplate jdbcTemplate = new
		 * NamedParameterJdbcTemplate(dataSource);
		 */
		// parameter value로 전달된 위한 map 생성및 value put
		/*
		 * Map paramMap = new HashMap(); paramMap.put("id", userId);
		 */
		return getJdbcTemplate().update(
				this.sqlMap.get("remove"),
				new Object[] { userId });

	}// end of method

	// ==> 회원정보 :: SELECT ( 모든 회원 정보 검색 )
	public List<User> getUserList() throws Exception {

		// 1단계 connection

		// 2단계 SELECT 문 생성 및 전송
		

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

		return getJdbcTemplate().query(
				this.sqlMap.get("getList"),
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
					}// end rowMap

				} // end Anonymous

		); // end query method 

	}// end of Method

}// end of class