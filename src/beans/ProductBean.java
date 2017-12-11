package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.ProductDTO;
import entities.Product;

@Stateless
@LocalBean
public class ProductBean implements IProductBean{


    @PersistenceContext(name="entityManager") 
    EntityManager entityManager;
    
  
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
			//prod.setQuantity(prod.getQuantity());
			result.add(product);
		}
		return result;
	}

  
	@Override
	public ProductDTO getProductByID(Long id) {
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
		//prod.setQuantity(res.getQuantity());
		return prod;
	}


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
			//prod.setQuantity(r.getQuantity());
			prods.add(prod);
		}
		return prods;
	}

 
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
			//prod.setQuantity(r.getQuantity());
			prods.add(prod);
		}
		return prods;
	}

}
