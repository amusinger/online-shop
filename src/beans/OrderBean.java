package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
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

import dto.OrderDTO;
import dto.ProductDTO;
import entities.Cart;
import entities.Order;
import entities.User;

@Stateless
@LocalBean
@Path("/orders")
public class OrderBean implements IOrderBean{
		
    @EJB
    ProductBean productBean;
    
    @EJB
    CartBean cartBean;
    
    @PersistenceContext(name="entityManager") 
    EntityManager entityManager;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buy/{id}/address/{address}")
    public void order(@PathParam("id") Long id, @PathParam("address") String address) {
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
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/view/all")
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
