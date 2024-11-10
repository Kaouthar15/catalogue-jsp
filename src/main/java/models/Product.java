package models;


import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "products")
//@NamedQuery(name = "Product.getAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Long quantity;
    private Long sdr;
    private String photo;

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;


    private static final long serialVersionUID = 1L;

    public Product() {
    }

    public Product(String name, Long id, Double price, Long quantity, Long sdr, Category category) {
        this.setName(name);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.sdr = sdr;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSdr() {
        return sdr;
    }

    public void setSdr(Long sdr) {
        this.sdr = sdr;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String toString() {
		return "name : "+name +" price : "+price+" quantity : "+quantity + " sdr : " + sdr +" categoryId " + category.getId();
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
