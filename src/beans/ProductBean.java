package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dto.ProductDTO;
import entities.Product;

@Stateless
@LocalBean
@Path("/products")
public class ProductBean implements IProductBean{


    @PersistenceContext(name="entityManager") 
    EntityManager entityManager;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/view/all")
	@Override
	public List<ProductDTO> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> prods = (List<Product>)entityManager.createQuery("select p from Product p", Product.class).getResultList();
		ArrayList<ProductDTO> result = new ArrayList<ProductDTO>();
		for(Product prod : prods) {
			ProductDTO product = new ProductDTO();
			product.setId(prod.getId());
			product.setTitle(prod.getTitle());
			product.setDescription(prod.getDescription());
			product.setPrice(prod.getPrice());
			product.setImage(prod.getImage());
			result.add(product);
		}
		return result;
	}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("view/{id}")
	@Override
	public ProductDTO getProductByID(@PathParam("id") Long id) {
		// TODO Auto-generated method stub
  		Query q = entityManager.createQuery("select x from Product x where x.id=:id");
		q.setParameter("id", id);
		Product res = (Product) q.getSingleResult();
		ProductDTO prod = new ProductDTO();
		prod.setId(res.getId());
		prod.setTitle(res.getTitle());
		prod.setPrice(res.getPrice());
		prod.setDescription(res.getDescription());
		prod.setImage(res.getImage());
		return prod;
	}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/{name}")
	@Override
	public List<ProductDTO> searchProductByName(String name) {
		// TODO Auto-generated method stub
		Query q = entityManager.createQuery("select x from Product x where x.title=:name");
		q.setParameter("name", name);
		List<Product> res = (List<Product>)q.getResultList();
		ArrayList<ProductDTO> prods = new ArrayList<ProductDTO>();
		for(Product r : res) {
			ProductDTO prod = new ProductDTO();
			prod.setId(r.getId());
			prod.setTitle(r.getTitle());
			prod.setDescription(r.getDescription());
			prod.setPrice(r.getPrice());
			prod.setImage(r.getImage());
			prods.add(prod);
		}
		return prods;
	}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/filter/{min}/{max}")
	@Override
	public List<ProductDTO> filterByPrice(Long minPrice, Long maxPrice) {
		if (minPrice == null) {
			minPrice = 0L;
		}
		if (maxPrice == null) {
			maxPrice = 20000L;
		}
		Query q = entityManager.createQuery("select x from Product x where x.price between :minPrice and :maxPrice");
		q.setParameter("minPrice", minPrice);
		q.setParameter("maxPrice", maxPrice);
		List<Product> res = (List<Product>)q.getResultList();
		ArrayList<ProductDTO> prods = new ArrayList<ProductDTO>();
		for(Product r : res) {
			ProductDTO prod = new ProductDTO();
			prod.setId(r.getId());
			prod.setTitle(r.getTitle());
			prod.setDescription(r.getDescription());
			prod.setPrice(r.getPrice());
			prod.setImage(r.getImage());
			prods.add(prod);
		}
		return prods;
	}

}
