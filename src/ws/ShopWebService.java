package ws;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import beans.ProductBean;
import dto.ProductDTO;

@WebService(serviceName="ShopWebService")
public class ShopWebService {

	@EJB 
	ProductBean productBean;
	
	@WebMethod
    public List<ProductDTO> getAllProducts() {
		return productBean.getAllProducts();
    }
	
	@WebMethod
    public ProductDTO ProductById(Long id) {
		return productBean.getProductByID(id);
    }
	
	@WebMethod
	public List<ProductDTO> searchProductByName(String name) {
		return productBean.searchProductByName(name);
	}
	
	@WebMethod
	public List<ProductDTO> filterByPrice(Long minPrice, Long maxPrice) {
		return productBean.filterByPrice(minPrice, maxPrice);
	}
	
}
