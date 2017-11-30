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
	public void addToCart(Long id) {
    		Cart cart = checkUserAndCart(id);
		entityManager.persist(cart);   				
	}

	private Cart checkUserAndCart(Long id) {
		// TODO Auto-generated method stub
     	Query q = entityManager.createQuery("select x from User x where x.logged=true");
     	User res = (User) q.getSingleResult();
     	UserDTO u = new UserDTO();
		u.setId(res.getId());
		u.setEmail(res.getEmail());
		u.setName(res.getName());
		u.setPassword(res.getPassword());
		u.setLogged(res.getLogged());
		
     	System.out.println(id);
		Cart cart = new Cart();
		cart.setProductID(id);
		cart.setUseriD(u.getId());
	
		return cart;
	}


	@Override
	public List<ProductDTO> getCart() {
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
	public void removeFromCart(Long id) {
		// TODO Auto-generated method stub
	     	Query q = entityManager.createQuery("select x from User x where x.logged=true");
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
