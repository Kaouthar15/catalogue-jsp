package actions;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import models.Category;
import services.CategoryService;

import java.util.List;
@ManagedBean(name = "adminCategories") 
@RequestScoped 
public class AdminCategories {
    private CategoryService categoryService = new CategoryService();
    
    private List<Category> categories;
    private Category category;
    private Category selectedCategory; // For holding the selected category for delete and update
    private Long categoryId;
    private String keyword;
    private boolean flagAdd = false;

    // Getter and Setter methods
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

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public boolean isFlagAdd() {
        return flagAdd;
    }

    public void setFlagAdd(boolean flagAdd) {
        this.flagAdd = flagAdd;
    }

    // Action methods
    public void list() {
        categories = categoryService.list();
        System.out.println("list categories method");
    }

    @PostConstruct
    public void init() {
        list(); 
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
        if (selectedCategory != null && selectedCategory.getId() > 0) {
            categoryId = selectedCategory.getId();
            // Show confirmation dialog
        }
    }

    public void confirmDelete() {
        if (categoryId > 0) {
            categoryService.remove(categoryId);
            list();  // Refresh the list after deletion
        }
    }

    public void search() {
        if (keyword != null && !keyword.isEmpty()) {
            categories = categoryService.selectByKeyword(keyword);
        }
    }

    public void enableAddMode() {
        System.out.println(flagAdd);
        this.flagAdd = true;
        System.out.println(flagAdd);
    }

    public void edit() {
        if (selectedCategory != null) {
            categoryId = selectedCategory.getId();
            updateCategoryFormData(); // Populate the form with selected category data
        }
    }
}
