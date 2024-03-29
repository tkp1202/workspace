package spring.service.user;

import java.util.List;

import spring.service.domain.User;

/* 
 * FileName : UserDao.java  ( Data Access Object ) 
 * :: Persistence(?) Layer 인터페이스
*/ 
public interface UserService{
	
	//==> 회원정보 ::  INSERT ( 회원가입 )
	public void addUser(User user) throws Exception;
	
	//==> 회원정보 ::  SELECT  ( 회원정보 검색 ) 
	public User getUser(String userId) throws Exception ;

	//==> 회원정보 ::  UPDATE  ( 회원정보 변경  )
	public void  updateUser(User user) throws Exception ;
	
	//==> 회원정보 ::  SELECT  ( 모든 회원 정보 검색 )
	public List<User> getUserList(User user) throws Exception ;
	
}//end of class