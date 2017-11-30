package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.OrderDTO;
import entities.Order;
import entities.User;

@Stateless
@LocalBean
public class OrderBean implements IOrderBean{
		
    @EJB
    ProductBean productBean;
    
    @EJB
    CartBean cartBean;
    
    @PersistenceContext(name="entityManager") 
    EntityManager entityManager;
    

    public void order(Long id, String address) {
    		cartBean.removeFromCart(id);
    		Query q = entityManager.createQuery("select x from User x where x.logged=true");
    		User res = (User) q.getSingleResult();
    		Order or = new Order();
		or.setProductID(id);
		or.setUserID(res.getId());
		or.setAddress(address);
		System.out.println(or);
		entityManager.persist(or); 
		
    }
    
    
 
	public List<OrderDTO> viewOrders() {
		// TODO Auto-generated method stub
		Query q1 = entityManager.createQuery("select x from User x where x.logged=true");
		User res = (User) q1.getSingleResult();
		System.out.println(res);
		Query q = entityManager.createQuery("select y from Order y where y.userID=:id");
		q.setParameter("id", res.getId());
		
		ArrayList<OrderDTO> prodsInOrder = new ArrayList<OrderDTO>(); 
		List<Order> resultList = (List<Order>)q.getResultList();
		for (Order order : resultList) {
			OrderDTO product = new OrderDTO();
			product.setId(order.getId());
			product.setProduct_id(order.getProductID());
			product.setUser_id(order.getUserID());
			product.setAddress(order.getAddress());
			prodsInOrder.add(product);
		}
		return prodsInOrder;
	}

}
