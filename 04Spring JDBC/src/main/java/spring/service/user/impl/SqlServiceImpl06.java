package spring.service.user.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;





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
public class SqlServiceImpl06 implements SqlService {

	// /Field
	
	private  Map<String, String> sqlMap;

	
	
	 public void setsqlMap(Map<String, String> sqkMap) {
		 System.out.println("::"+getClass()+".setSqlMap Call...");
		 this.sqlMap = sqlMap; 
	 }
	 
	 
	// /Constructor
	public SqlServiceImpl06() {
		System.out.println("::" + getClass()
				+ ".SqlServiceImpl06 디폴트 생성자 Call");
	}

	public String getSql(String key) throws Exception {
		
		String sql = this.sqlMap.get(key);
		
		if(sql==null) 
			throw new Exception (key+ "에 해당하는 SQL은 없습니다. ");
		else 
			return sql; 

	}// end of Method

}// end of class