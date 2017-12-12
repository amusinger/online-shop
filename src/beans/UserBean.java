package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.UserDTO;
import entities.User;

@Stateless
@LocalBean
public class UserBean implements IUserBean{

	
	@PersistenceContext(unitName="entityManager")
	private EntityManager entityManager;

	public UserDTO getUserByID(Long id) {
		Query q1 = entityManager.createQuery("select x from User x where x.id=:id");
		q1.setParameter("id", id);
		User resultList = (User)q1.getSingleResult();
		
		UserDTO userDTO1 = new UserDTO();
		userDTO1.setId(resultList.getId());
		userDTO1.setEmail(resultList.getEmail());
		userDTO1.setName(resultList.getName());
		userDTO1.setLogged(true);
				
		return userDTO1;
	}
	
	public List<UserDTO> getAllUsers() {		
		List<User> users = (List<User>)entityManager.createQuery("select p from User p", User.class).getResultList();
		ArrayList<UserDTO> result = new ArrayList<UserDTO>();
		for(User u : users) {
			UserDTO user = new UserDTO();
			user.setId(u.getId());
			user.setName(u.getName());
			user.setEmail(u.getEmail());
			user.setPassword(u.getPassword());
			result.add(user);
		}
		return result;
	}
	
	@Override
	public UserDTO login(String email, String password) {		
		Query q = entityManager.createQuery("select x from User x where x.email=:email and x.password=:password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		User u = (User) q.getSingleResult();
		u.setLogged(true);
		UserDTO userDTO = new UserDTO();
		userDTO.setId(u.getId());
		userDTO.setEmail(u.getEmail());
		userDTO.setName(u.getName());
		userDTO.setLogged(true);
		entityManager.persist(u);
		return userDTO;
	}
    
	
	public List<UserDTO> isOnline() {
		Query q1 = entityManager.createQuery("select x from User x where x.logged=true");
		
		ArrayList<UserDTO> onlineUsers = new ArrayList<UserDTO>(); 
		
		List<User> resultList = (List<User>)q1.getResultList();
		for (User u1 : resultList) {
		
			UserDTO userDTO1 = new UserDTO();
			userDTO1.setId(u1.getId());
			userDTO1.setEmail(u1.getEmail());
			userDTO1.setName(u1.getName());
			userDTO1.setLogged(u1.getLogged());
			onlineUsers.add(userDTO1);
		}
		
		return onlineUsers;
	}
	
	@Override
	public void register(String email,  String password) {	
		
		User x = new User(email, password);
		entityManager.persist(x);
	}

    

	@Override
	public void logout(String email) {
		// TODO Auto-generated method stub
		Query q = entityManager.createQuery("select x from User x where x.email=:email");
		q.setParameter("email", email);
		User u = (User) q.getSingleResult();
		u.setLogged(null);
		entityManager.persist(u);
	}

}
