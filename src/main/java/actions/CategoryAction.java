package actions;

import com.opensymphony.xwork2.ActionSupport;

import models.Category;
import services.CategoryService;

import java.util.List;

public class CategoryAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private CategoryService categoryService = new CategoryService();
    
    private List<Category> categories;
    private Category category;
    private Long categoryId;
    private String keyword;


    // Getter and Setter methods for Struts
    public List<Category> getCategories() {
        return categories;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    // Action methods
    public String list() {
        categories = categoryService.list();
        return SUCCESS;
    }

    public String add() {
        if (category != null) {
            categoryService.add(category);
            return SUCCESS;
        }
        return INPUT;
    }

    public String update() {
        if (category != null && category.getId() != null) { 
            categoryService.update(category);
            return SUCCESS;
        }
        return INPUT;
    }

    public String updateCategoryFormData() {
        Category category = categoryService.getById(categoryId);
        setCategory(category); 
        return SUCCESS;
    }
    
    public String delete() {
        if (categoryId > 0) {
            categoryService.remove(categoryId);
            return SUCCESS;
        }
        return INPUT;
    }

    public String search() {
        if (keyword != null && !keyword.isEmpty()) {
            categories = categoryService.selectByKeyword(keyword);
            return SUCCESS;
        }
        return INPUT;
    }
    @Override
    public void validate() {
        // Custom validation logic, if necessary
    }
}
