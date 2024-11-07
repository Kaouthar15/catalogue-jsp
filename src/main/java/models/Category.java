package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories")
//@NamedQuery(name = "Category.list", query = "SELECT c FROM Category c"))
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    
    
    // Constructors
	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(String name2, String description2) {
		this.name = name2;
        this.description = description2;
	}

	public Category(long long1, String string, String string2) {
		this.id = long1;
        this.name = string;
        this.description = string2;
	}

	// Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String toString() {
    	return "Category [id=" + id + ", name=" + name + ", description=" + description + "]";
    }
}
