package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.CartBean;
import dto.CartProductDTO;

@Path("/carts")
public class CartRest {

    @EJB
    CartBean cartBean;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add/{id}/user/{user_id}")
	public void addToCart(@PathParam("id") Long id, @PathParam("user_id") Long user_id) {
    		cartBean.addToCart(id, user_id);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/count/{user_id}")
	public Long countCart(@PathParam("user_id") Long user_id) {
    		return cartBean.countCart(user_id);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/checkout/{user_id}")
	public List<CartProductDTO> getCart(@PathParam("user_id") Long user_id) {
    		return cartBean.getCart(user_id);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}/user/{user_id}")
    public void removeFromCart(@PathParam("id") Long id, @PathParam("user_id") Long user_id) {
    		cartBean.removeFromCart(id, user_id);
    }
}
