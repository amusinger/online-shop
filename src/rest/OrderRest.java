package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.OrderBean;
import dto.OrderDTO;

@Path("/orders")
public class OrderRest {
		
	@EJB
    OrderBean orderBean;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buy/{id}/address/{address}/user/{user_id}")
    public void order(@PathParam("id") Long id, @PathParam("address") String address, @PathParam("user_id") Long user_id) {
    		orderBean.order(id, address, user_id);
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/view/all")
	public List<OrderDTO> viewOrders() {
    		return orderBean.viewOrders();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/view/all/admin")
	public List<OrderDTO> getAllOrders() {
		return orderBean.getAllOrders();
} 
	
}
