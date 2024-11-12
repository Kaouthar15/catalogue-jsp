package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.file.UploadedFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import services.CategoryService;
import services.ProductService;
import models.Category;
import models.Product;

@ManagedBean(name = "adminProducts") 
@SessionScoped
public class AdminProducts {

    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();
    
    private List<Product> products;
    private List<Category> categories;
    private Product newProduct = new Product(); 
    private Product selectedProduct; 
    private Long categoryId;
    private String keyword;
    private boolean addMode = false;
    private boolean editMode = false;
    
    private UploadedFile file;

    

    // Getter and Setter methods
    

    public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		System.out.println("¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤ Calling setFile ¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤");
		System.out.println(file.getFileName() + " " + file.getSize()); 
		this.file = file; 
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String path = servletContext.getRealPath("") + "resources" + File.separator + "img" + File.separator;
		System.out.println(path);
		try {
			@SuppressWarnings("resource")
			OutputStream outputStream = new FileOutputStream(path+file.getFileName()); 
			InputStream inputStream = file.getInputStream();
			 byte[] buffer = new byte[1024];
		        int bytesRead;
		        
		        while ((bytesRead = inputStream.read(buffer)) != -1) {
		        	outputStream.write(buffer, 0, bytesRead);
		        }
		        
		        System.out.println("File successfully uploaded to " + path + file.getFileName());
		} catch (Exception e) {
			System.err.println("Error during file upload: " + e.getMessage());
		}
		
	}
	
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

    public Product getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Product product) {
        this.newProduct = product;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isAddMode() {
        return addMode;
    }

    public void setAddMode(boolean addMode) {
        this.addMode = addMode;
    }
    public boolean getAddMode(boolean addMode) {
    	return addMode;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    // Action methods
    @PostConstruct
    public void init() {
        list();
        categories = categoryService.list(); // Initialize categories
    }

    // List all products
    public void list() {
        products = productService.list();
    }

    // Select a product for editing or deletion
    public void onRowSelect() {
        System.out.println("Selected product: " + selectedProduct);
    }

    // Enable add mode and reset the product object
    public void enableAddMode() {
        this.addMode = true;
        this.editMode = false;
        this.newProduct = new Product(); 
    }

    // Enable edit mode and load selected product
    public void enableEditMode() {
        this.addMode = false;
        this.editMode = true;
    }

    // Add a new product with the selected category
    public void add() {
    	newProduct.setPhoto("/img/"+file.getFileName());
        productService.add(newProduct,categoryId); 
        setAddMode(false); 
        list();
        
    }

    // Edit an existing product with the selected category
    public void update() {
        if (selectedProduct != null && selectedProduct.getId() != null) {
            productService.update(selectedProduct,selectedProduct.getCategory().getId());
           setEditMode(false);
            list();
        }
    }

    // Delete the selected product
    public void delete() {
        if (selectedProduct != null && selectedProduct.getId() != null) {
            productService.remove(selectedProduct.getId());
            selectedProduct = null;
            list();
        }
    }

    // Search products by keyword
    public void search() {
        if (keyword != null && !keyword.isEmpty()) {
            products = productService.selectByKeyword(keyword);
        }
    }
    // cancel mode 
    public void cancelAddMode() {
    	System.out.println(this.addMode);
        this.addMode = false;
        
    }
    
}
