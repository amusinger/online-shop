package dto;

public class CategoryDTO {
	private Long id;
    private String categoryName;
	//private Long productID;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
//	public Long getProductID() {
//		return productID;
//	}
//	public void setProductID(Long productID) {
//		this.productID = productID;
//	}
}
