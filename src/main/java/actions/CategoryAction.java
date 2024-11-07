package actions;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import models.Category;
import services.CategoryService;

import java.util.List;

@ManagedBean(name = "categoryAction") // ManagedBean annotation
@RequestScoped // Define the scope of the bean (request scope)
public class CategoryAction {
    private CategoryService categoryService = new CategoryService();
    
    private List<Category> categories;
    private Category category;
    private Long categoryId;
    private String keyword;

    // Getter and Setter methods
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
    public void list() {
        categories = categoryService.list();
    }

    public void add() {
        if (category != null) {
            categoryService.add(category);
        }
    }

    public void update() {
        if (category != null && category.getId() != null) { 
            categoryService.update(category);
        }
    }

    public void updateCategoryFormData() {
        Category category = categoryService.getById(categoryId);
        setCategory(category); 
    }
    
    public void delete() {
        if (categoryId > 0) {
            categoryService.remove(categoryId);
        }
    }

    public void search() {
        if (keyword != null && !keyword.isEmpty()) {
            categories = categoryService.selectByKeyword(keyword);
        }
    }
}
