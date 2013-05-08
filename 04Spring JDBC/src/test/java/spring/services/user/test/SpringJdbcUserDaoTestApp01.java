package spring.services.user.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.service.domain.User;
import spring.service.user.UserDao;

/*
 * FileName : SpringJdbcUserDaoTestApp01.java
 */
public class SpringJdbcUserDaoTestApp01 {
	///Main Method
	public static void main(String[] args) throws Exception{

		ApplicationContext context =
				new ClassPathXmlApplicationContext(
															new String[] {	"/config/datasourceservice.xml",
																						//"/config/userservice01.xml"	 }
																						"/config/userservice02.xml"	 }
																						//"/config/userservice03.xml"	 }
																						//"/config/userservice04.xml"	 }
																						//"/config/userservice05.xml"	 }
																						//"/config/userservice06.xml"	 }
									                                                    );
		//==> IoC Container �� ���� ȹ���� UserDao �ν��Ͻ� ȹ��
		UserDao userDao  //= (UserDao)context.getBean("springJdbcUserDaoImpl01");
											= (UserDao)context.getBean("SpringJdbcUserDaoImpl02");
											//= (UserDao)context.getBean("springJdbcUserDaoImpl03");
											//= (UserDao)context.getBean("springJdbcUserDaoImpl04");
											//= (UserDao)context.getBean("springJdbcUserDaoImpl05");
											//= (UserDao)context.getBean("springJdbcUserDaoImpl06");

		
		User user = new User("user04","�ָ�","user04",40);

		System.out.println("///////////////////////////////////////////////////////////////////////////////////////");
		//1.UserDao.addUser(user) Test
		System.out.println(":: 1. add(INSERT)  ? "+userDao.addUser(user));
		
		System.out.println("///////////////////////////////////////////////////////////////////////////////////////");
		//2.UserDao.getUser(userId) Test
		user = userDao.getUser("user04");
		System.out.println(":: 2. get(SELECT)  ? "+user);
		
		System.out.println("///////////////////////////////////////////////////////////////////////////////////////");
		//3.UserDao.uadateUser(user) Test
		user.setUserName("�庸��");
		System.out.println(":: 3. update(UPDATE)  ? "+userDao.updateUser(user));
		
		System.out.println("///////////////////////////////////////////////////////////////////////////////////////");
		//4.UserDao.getUser(userId) Test
		user = userDao.getUser("user04");
		System.out.println(":: 4. get(SELECT)  ? "+user);

		System.out.println("///////////////////////////////////////////////////////////////////////////////////////");
		//5.UserDao.removeUser(userId) Test
		System.out.println(":: 5. remove(DELETE)  ? "+userDao.removeUser("user04"));
		
		System.out.println("///////////////////////////////////////////////////////////////////////////////////////");
		//6.UserDao.getUserList() Test
		System.out.println(":: 6. all User(SELECT)  ? ");
		List<User> list = userDao.getUserList();
		for (int i =0 ;  i < list.size() ; i++) {
			System.out.print( "<"+ ( i +1 )+"> ��° ȸ�� ����... ");
			System.out.println( list.get(i).toString() );
		}
	}
}//end of class