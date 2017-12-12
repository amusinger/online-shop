package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.ProductBean;
import dto.ProductDTO;

@Path("/products")
public class ProductRest {

    @EJB
    ProductBean productBean;
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("view/{id}")
	public ProductDTO getProductByID(@PathParam("id") Long id) {
    		return productBean.getProductByID(id);
    }
    
    @POST
    @Path("add/")
    public void addProduct(String title, String desc, String img, String price, String cat_id) {
    		productBean.addProduct(title, desc, img, price, cat_id);
    }
    
    
    @POST
    @Path("delete/")
    public void deleteProduct(String id) {
    		productBean.deleteProduct(id);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/view/all")
	public List<ProductDTO> getAllProducts() {
    		return productBean.getAllProducts();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/{name}")
	public List<ProductDTO> searchProductByName(@PathParam("name") String name) {
    		return productBean.searchProductByName(name);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/filter/{min}/max/{max}")
	public List<ProductDTO> filterByPrice(@PathParam("min") Long minPrice, @PathParam("max") Long maxPrice) {
    		return productBean.filterByPrice(minPrice, maxPrice);
    }
    
}
