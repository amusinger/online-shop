package beans;

import java.util.List;

import dto.ProductDTO;

public interface IProductBean {
	
	public List<ProductDTO> getAllProducts();
	public ProductDTO getProductByID(Long id);
	public List<ProductDTO> searchProductByName(String name);
	public List<ProductDTO> filterByPrice(Long minPrice, Long maxPrice);	
	
}
