package spring.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import spring.service.domain.User;
import spring.service.user.UserDao;
import spring.service.user.UserService;

/* 
 * FileName : JdbcUserDaoImpl.java  ( Data Access Object ) 
 * :: Persistence(?) Layer 인터페이스 구현 한 RDBMS 와의  CRUD 구현 클래스
 *    
 *  #### Annotation 기반 meta-data 설정 ######
 *  @Component("springUserServiceImpl08")의 의미 
 *  <bean id="springUserServiceImpl08"
 *  		class="spring.service.user.impl.SpringUserServiceImpl08"/>
 *  
 *  @Resource  @Autowired의 의미 
 *  
 *    DataType을 기반으로 Container 가 생성한 객체가 존재한다면 injection 함 
 *    객체가 존재 하지 않는다면 Exception 
 *    @AutoWired(required= false) 속성을 이용하면 객체가 존재하지 않아도 Exception 미발생 
 *    @Resource( required=false) 속성이 없으므로 객제가 존재하지 않으면 Exception 
 *    @Resource(name="userDAO06") 속성을 이용 특정 id를 갖는 객체 injection 가능 
 *  
 */

@Component("springUserServiceImpl08")
public class SpringUserServiceImpl08  implements UserService {

	// /Field
	@Resource
	UserDao userDAO; 

	
	
	 public void setUserDAO(UserDao userDAO) {
		 System.out.println("::"+getClass()+".setUserDAO Call...");
		 this.userDAO = userDAO; 
	 }
	 
	 
	// /Constructor
	public SpringUserServiceImpl08() {
		System.out.println("::" + getClass()
				+ ".SpringUserServiceImpl07 디폴트 생성자 Call");
	}

	// /Method
	// ==> 회원정보 :: INSERT ( 회원가입 )
	public void addUser(User user) throws Exception {
		userDAO.addUser(user);
		
                                
	
	}// end method

	// ==> 회원정보 :: SELECT ( 회원정보 검색 )
	public User getUser(String userId) throws Exception {

			
			
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