package actions;

import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;

import services.CategoryService;
import services.ProductService;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import models.Category;
import models.Product;

public class ProductAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();
    
    private List<Product> products;
    private List<Category> categories;
    private Product product = new Product();
    private Long productId;
    private String keyword;
    private Long categoryId; 
    private String nameString;


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
    @SkipValidation
    public String execute() {
        categories = categoryService.list();
        System.out.println(categories);
        return SUCCESS;
    }
    @SkipValidation
    public String list() {
        
        products = productService.list();
        return SUCCESS;
    }
    @SkipValidation
    public String addProductFormData() {
    	setCategories(categoryService.list());
    	return SUCCESS; 
    }
    
    public String add() {
    	System.out.println("adding");
        categories = categoryService.list();
        
        if (product != null && categoryId != null) {
                    
            productService.add(product, categoryId);
            return SUCCESS;
        }
        return INPUT;
    }
    @SkipValidation
    public String input() {
        categories = categoryService.list();
        return INPUT; 
    }
    @SkipValidation
    public String update() {
        categories = categoryService.list();
        if (product != null && product.getId() != null && categoryId != null) {
            productService.update(product, categoryId); 
            return SUCCESS;
        }
        return INPUT;
    }
    @SkipValidation
    public String updateProductFormData() {
    	setCategories(categoryService.list());
    	Product product = productService.get1(productId);
    	setProduct(product);
    	return SUCCESS; 
    }
    @SkipValidation
    public String delete() {
        if (productId != null && productId > 0) {
            productService.remove(productId);
            return SUCCESS;
        }
        return INPUT;
    }
    @SkipValidation
    public String search() {
    	
        if (keyword != null && !keyword.isEmpty()) {
        	products = productService.selectByKeyword(keyword);
            return SUCCESS;
        }
        return INPUT;
    }
	public String getNameString() {
		return nameString;
	}
	@RequiredStringValidator (message= "You must enter a value for name.")
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

}
