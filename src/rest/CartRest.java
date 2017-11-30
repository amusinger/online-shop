package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.CartBean;
import dto.ProductDTO;

@Path("/carts")
public class CartRest {

    @EJB
    CartBean cartBean;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add/{id}")
	public void addToCart(@PathParam("id") Long id) {
    		cartBean.addToCart(id);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/checkout")
	public List<ProductDTO> getCart() {
    		return cartBean.getCart();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public void removeFromCart(@PathParam("id") Long id) {
    		cartBean.removeFromCart(id);
    }
}
