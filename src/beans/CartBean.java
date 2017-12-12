package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



import dto.CartProductDTO;
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
	

	public Long countCart(Long user_id) {
		try {
			Query query = entityManager.createQuery("select count(p) from Cart p where p.useriD=:user_id" ); 
			query.setParameter("user_id", user_id);
			Long count = (Long) query.getSingleResult();
			System.out.println("COUNT " + count);
			return count;
		} catch(Exception e) {
			return (long)0;
		}
		
	}

	private Cart checkUserAndCart(Long id, Long user_id) {		
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
	public List<CartProductDTO> getCart(Long user_id) {
		// TODO Auto-generated method stub
		Query q1 = entityManager.createQuery("select x from User x where x.logged=true");
		User res = (User) q1.getSingleResult();
		System.out.println(res);
		Query q = entityManager.createQuery("select y from Cart y where y.useriD=:id");
		q.setParameter("id", res.getId());
		
		String txt = "Your cart is waiting for you: ";
		ArrayList<CartProductDTO> prodsInCart = new ArrayList<CartProductDTO>(); 
		List<Cart> resultList = (List<Cart>)q.getResultList();
		for (Cart cart : resultList) {
			ProductDTO prod = productBean.getProductByID(cart.getProductID());
			CartProductDTO cartprod = new CartProductDTO();
			cartprod.setId(prod.getId());
			cartprod.setCategoryID(prod.getCategoryID());
			cartprod.setDescription(prod.getDescription());
			cartprod.setImage(prod.getImage());
			cartprod.setLabel(prod.getLabel());
			cartprod.setPrice(prod.getPrice());
			cartprod.setTitle(prod.getTitle());
			cartprod.setQuantity(cart.getQuantity());
			prodsInCart.add(cartprod);
			txt = txt + "\n" + prod.getDescription();
		}
		return prodsInCart;
	}



		@Override
	public void removeFromCart(Long id, Long user_id) {
		// TODO Auto-generated method stub
	     	Query q = entityManager.createQuery("select x from User x where x.id=:id");
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
