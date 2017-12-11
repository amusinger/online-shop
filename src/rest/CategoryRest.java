package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.CategoryBean;
import beans.ProductBean;
import dto.CategoryDTO;
import dto.ProductDTO;

@Path("/categories")
public class CategoryRest {
	
	 @EJB
	 CategoryBean categoryBean;
	 
	 
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("/view/all")
		public List<CategoryDTO> getAllCategories() {
	    		return categoryBean.getAllCategories();
	    }
	    
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("view/{id}")
		public List<ProductDTO> getProductByCategory(@PathParam("id") Long id) {
	    		return categoryBean.getProductByCategory(id);
	    }
	    
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("view/cat/{id}")
		public CategoryDTO getCategorytByID(@PathParam("id") Long id) {
	    		return categoryBean.getCategorytByID(id);
	    }
	    
	    
	    

}
