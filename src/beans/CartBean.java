package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.ProductDTO;
import dto.UserDTO;
import entities.Cart;
import entities.User;

@Stateless
@LocalBean
public class CartBean implements ICartBean{

    @EJB
    ProductBean productBean;
      
    @PersistenceContext(name="entityManager") 
    EntityManager entityManager;
    

	@Override
	public void addToCart(Long id, Long user_id) {
    		Cart cart = checkUserAndCart(id, user_id);
		entityManager.persist(cart);   				
	}
	

	public Long countCart() {
		Query query = entityManager.createQuery("SELECT COUNT(p) FROM Cart p " ); 
		Long count = (Long) query.getSingleResult();
		return count;
	}

	private Cart checkUserAndCart(Long id, Long user_id) {
		// TODO Auto-generated method stub
//     	Query q = entityManager.createQuery("select x from User x where x.id=:user_id");
//     	q.setParameter("user_id", user_id);
//     	User res = (User) q.getSingleResult();
//     	UserDTO u = new UserDTO();
//		u.setId(res.getId());
//		u.setEmail(res.getEmail());
//		u.setName(res.getName());
//		u.setPassword(res.getPassword());
//		u.setLogged(res.getLogged());
		
		try {
			Query q2 = entityManager.createQuery("select x from Cart x where x.useriD=:id and x.productID=:prodID");
			q2.setParameter("id", user_id);
			q2.setParameter("prodID", id);
			Cart resultList = (Cart)q2.getSingleResult();
			resultList.setQuantity(resultList.getQuantity()+1);
			return resultList;	
		} catch (Exception e) {
			Cart cart = new Cart();
			cart.setProductID(id);
			cart.setUseriD(user_id);
			cart.setQuantity((long) 1);
			return cart;	
		}
	}


	@Override
	public List<ProductDTO> getCart(Long user_id) {
		// TODO Auto-generated method stub
		Query q1 = entityManager.createQuery("select x from User x where x.logged=true");
		User res = (User) q1.getSingleResult();
		System.out.println(res);
		Query q = entityManager.createQuery("select y from Cart y where y.useriD=:id");
		q.setParameter("id", res.getId());
		
		ArrayList<ProductDTO> prodsInCart = new ArrayList<ProductDTO>(); 
		List<Cart> resultList = (List<Cart>)q.getResultList();
		for (Cart cart : resultList) {
			prodsInCart.add(productBean.getProductByID(cart.getProductID()));
		}
		return prodsInCart;
	}


		@Override
	public void removeFromCart(Long id, Long user_id) {
		// TODO Auto-generated method stub
	     	Query q = entityManager.createQuery("select x from User x where x.user_id=:id");
	     	q.setParameter("id", user_id);
	     	User res = (User) q.getSingleResult();
	     	UserDTO u = new UserDTO();
			u.setId(res.getId());
			u.setEmail(res.getEmail());
			u.setName(res.getName());
			u.setPassword(res.getPassword());
			u.setLogged(res.getLogged());
			
			Query q2 = entityManager.createQuery("select x from Cart x where x.useriD=:id and x.productID=:prodID");
			q2.setParameter("id", res.getId());
			q2.setParameter("prodID", id);
			List<Cart> resultList = (List<Cart>)q2.getResultList();
			for (Cart cart : resultList) {
				entityManager.remove(cart);
			}
	}

}
