package entities;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable = false)
    private Long id;
    
    @Column(name="title", nullable = false)
    private String title;
    
    @Column(name="description", nullable = false)
    private String description;
    
    @Column(name="image", nullable = true)
    private String image;
    
	@Column(name="price", nullable = false)
    private Long price;
	
	@Column(name="category_id", nullable = true)
    private Long categoryID;
	
    @Column(name="label", nullable = true)
    private String label;
	
//	@Column(name="quantity", nullable = false)
//    private Long quantity;
//	
//	public Long getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(Long quantity) {
//		this.quantity = quantity;
//	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}


    
    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	
}
