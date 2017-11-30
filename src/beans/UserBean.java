package beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dto.UserDTO;
import entities.User;

@Stateless
@LocalBean
public class UserBean implements IUserBean{

	@PersistenceContext(unitName="entityManager")
	private EntityManager entityManager;

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
    

	@Override
	public void register(String email,  String password) {
//		Query q = entityManager.createQuery
//				("insert into users (email, password) "
//						+ "values(email, password)");
//		q.setParameter("email", email);
//		q.setParameter("password", password);
//		User u = (User) q.getSingleResult();
//		UserDTO userDTO = new UserDTO();
//		userDTO.setId(u.getId());
//		userDTO.setEmail(u.getEmail());
//		userDTO.setName(u.getName());
//		userDTO.setLogged(false);
		
		User x = new User(email, password);
		entityManager.persist(x);
	}
    

	@Override
	public void logout(String email) {
		// TODO Auto-generated method stub
		Query q = entityManager.createQuery("select x from User x where x.email=:email");
		q.setParameter("email", email);
		User u = (User) q.getSingleResult();
		UserDTO userDTO = new UserDTO();
		userDTO.setId(u.getId());
		userDTO.setEmail(u.getEmail());
		userDTO.setName(u.getName());
		userDTO.setLogged(false);
		entityManager.persist(u);
	}

}
