package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.OrderDTO;
import dto.ProductDTO;
import entities.Order;
import entities.User;
import mail.MailEvent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Stateless
@LocalBean
public class OrderBean implements IOrderBean{
	
	@Resource(mappedName = "java:jboss/mail/MyMail")
	private Session mailSession;
	
	
	@Resource
    private TimerService timerService;
	
	@Inject
	private Event<MailEvent> eventProducer;
	
    @EJB
    ProductBean productBean;
    
    @EJB
    CartBean cartBean;
    
    @PersistenceContext(name="entityManager") 
    EntityManager entityManager;
    

    public void order(Long id, String address, Long user_id) {
    		cartBean.removeFromCart(id, user_id);
    		Query q = entityManager.createQuery("select x from User x where x.id=:user_id");
    		q.setParameter("user_id", user_id);
    		User res = (User) q.getSingleResult();
    		Order or = new Order();
		or.setProductID(id);
		or.setUserID(res.getId());
		or.setAddress(address);
		System.out.println(or);
		entityManager.persist(or); 
		
		String email = res.getEmail();
		String txtMsg = getOrder(res.getId());
		
		System.out.println("heeeeeey");
		try {
			System.out.println(txtMsg);
			sendOrderConfirmationMail(email, "Your Orders", txtMsg);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
 
	private String getOrder(Long id) {
		// TODO Auto-generated method stub
		Query q = entityManager.createQuery("select y from Order y where y.userID=:id");
		q.setParameter("id", id);
		
		ArrayList<OrderDTO> prodsInOrder = new ArrayList<OrderDTO>(); 
		String msg = "Product(s): "; 
		
		List<Order> resultList = (List<Order>)q.getResultList();
		for (Order order : resultList) {
			ProductDTO p = productBean.getProductByID(order.getProductID());
			msg = msg +"\n" +  p.getTitle() + " : " + p.getDescription();
			
		}
		return msg;
	}



	public void sendOrderConfirmationMail(String email, String subject, String text) throws AddressException, MessagingException{
		System.out.println("send order");
		System.out.println(email);
		Message msg = new MimeMessage(mailSession);
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject(subject);
        msg.setText(text);
        
        sendEmail(text);
        Transport.send(msg);
		
	}
	
	public void sendEmail(String txt) {
		TimerConfig tf = new TimerConfig(txt, false);
	    timerService.createIntervalTimer(new Date(), 5000, tf);
	}
	
	@Timeout
    public void onTimeout(Timer timer) {
	
    		String txt = (String)timer.getInfo();
    		System.out.println(txt);
    		Message msg = new MimeMessage(mailSession);
        try {
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("nelazagox@ax80mail.com"));
			msg.setSubject("Cart");
		    msg.setText("Please check orders, new one was added!" + txt);	        
		    Transport.send(msg);
		    
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
    }



	public List<OrderDTO> viewOrders(Long user_id) {
		// TODO Auto-generated method stub
		Query q1 = entityManager.createQuery("select x from User x where x.id=:user_id");
		q1.setParameter("user_id", user_id);
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
	
	public List<OrderDTO> getAllOrders() {
		List<Order> resultList = (List<Order>)entityManager.createQuery("select p from Order p", Order.class).getResultList();	
		ArrayList<OrderDTO> prodsInOrder = new ArrayList<OrderDTO>(); 
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
