package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import models.Category;
import services.CategoryService;

import java.util.List;
@ManagedBean(name = "adminCategories") 
@SessionScoped 
public class AdminCategories {
    private CategoryService categoryService = new CategoryService();
    
    private List<Category> categories;
    private Category category;
    private Category newCategory = new Category();
    private Category selectedCategory; 
    private Long categoryId;
    private String keyword;
    private boolean addMode = false;
    private boolean editMode = false;

    // Getter and Setter methods
    
    public boolean getEditMode() {
		return editMode;
	}
    public boolean isEditMode() {
		return editMode;
	}
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	
    public Category getNewCategory() {
		return newCategory;
	}

	public void setNewCategory(Category newCategory) {
		this.newCategory = newCategory;
	}
	
	public boolean isAddMode() {
		return addMode;
	}

	public void setAddMode(boolean addMode) {
		this.addMode = addMode;
	}
	public boolean getAddMode() {
		return addMode;
	}
    public List<Category> getCategories() {
        return categories;
    }

    public Category getCategory() {
        return category;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public Long getCategoryId(Long categoryId) {
        return this.categoryId;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    // Action methods
    public void list() {
        categories = categoryService.list();
        System.out.println("list" );
    }
    
    public void onRowSelect() {
        System.out.println(selectedCategory);
    }
    @PostConstruct
    public void init() {
        list(); 
    }

    
    // delete method
    public void delete() {
    	if (selectedCategory != null && selectedCategory.getId() > 0) {
    		categoryService.remove(selectedCategory.getId());
    		selectedCategory = null;
            list();  
        }
    }

    //search method
    public void search() {
        if (keyword != null && !keyword.isEmpty()) {
            categories = categoryService.selectByKeyword(keyword);
        }
    }


    //editing methods
    public void edit() {
        if (selectedCategory != null) {
        	categoryService.update(selectedCategory); 
        	this.editMode = false; 
        }
    }
    public void enableEditMode() {
        this.editMode = true;
        this.addMode = false;
    }
    public void update() {
        if (category != null && category.getId() != null) { 
            categoryService.update(category);
        }
    }
    
    // adding methods
    public void enableAddMode() {
        this.addMode = true;
        this.editMode = false;
        this.setNewCategory(new Category());
    }

	public void add() {
            categoryService.add(newCategory); 
            this.addMode = false;
            list();
    }

	


}