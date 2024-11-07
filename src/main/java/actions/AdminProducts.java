package actions;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import services.CategoryService;
import services.ProductService;


import models.Category;
import models.Product;
@ManagedBean(name = "adminProducts") 
@RequestScoped 
public class AdminProducts{

    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();
    
    private List<Product> products;
    private List<Category> categories;
    private Product product = new Product();
    private Long productId;
    private String keyword;
    private Long categoryId; 


    // Getter and Setter methods for Struts
    public List<Product> getProducts() {
        return products;
    }
    
    public void setProducts(List<Product> products) {
    	this.products = products;  
    }
    public List<Category> getCategories() {
        return categories;
    }
    

    public void setCategories(List<Category> categories) {
    	this.categories = categories;  
    }
    

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    // Action methods
    public void execute() {
        categories = categoryService.list();
        System.out.println(categories);
    }
    public void list() {
        
        products = productService.list();
    }
    public void addProductFormData() {
    	setCategories(categoryService.list());
    }
    
    public void add() {
        categories = categoryService.list();
        
        if (product != null && categoryId != null) {
                    
            productService.add(product, categoryId);
        }
    }
    public void input() {
        categories = categoryService.list();
    }
    public void update() {
        categories = categoryService.list();
        if (product != null && product.getId() != null && categoryId != null) {
            productService.update(product, categoryId); 
        }
    }
    public void updateProductFormData() {
    	setCategories(categoryService.list());
    	Product product = productService.get1(productId);
    	setProduct(product);
    }
    public void delete() {
        if (productId != null && productId > 0) {
            productService.remove(productId);
        }
    }
    public void search() {
    	
        if (keyword != null && !keyword.isEmpty()) {
        	products = productService.selectByKeyword(keyword);
        }
    }


}
