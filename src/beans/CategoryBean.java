package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.CategoryDTO;
import dto.ProductDTO;
import entities.Category;
import entities.Product;

@Stateless
@LocalBean
public class CategoryBean {
	
    @PersistenceContext(name="entityManager") 
    EntityManager entityManager;
    
	public List<CategoryDTO> getAllCategories() {
		// TODO Auto-generated method stub
		List<Category> prods = (List<Category>)entityManager.createQuery("select p from Category p", Category.class).getResultList();
		ArrayList<CategoryDTO> result = new ArrayList<CategoryDTO>();
		for(Category prod : prods) {
			CategoryDTO product = new CategoryDTO();
			product.setId(prod.getId());
			product.setCategoryName(prod.getName());
			result.add(product);
		}
		return result;
	}
	

	public List<ProductDTO> getProductByCategory(Long id) {
		// TODO Auto-generated method stub
  		Query q = entityManager.createQuery("select x from Product x where x.categoryID=:id");
		q.setParameter("id", id);
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
	
	public CategoryDTO getCategorytByID(Long id) {
		// TODO Auto-generated method stub
  		Query q = entityManager.createQuery("select x from Category x where x.id=:id");
		q.setParameter("id", id);
		Category res = (Category) q.getSingleResult();
		CategoryDTO prod = new CategoryDTO();
		prod.setId(res.getId());
		prod.setCategoryName(res.getName());
		return prod;
	}
}
