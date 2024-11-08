package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import models.User;
import services.UserService;

import java.util.List;

@ManagedBean(name = "adminUsers") 
@SessionScoped 
public class AdminUsers {
    private UserService userService = new UserService();
    
    private List<User> users;
    private User user;
    private User newUser = new User();
    private User selectedUser; 
    private Long userId;
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

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public boolean isAddMode() {
        return addMode;
    }

    public void setAddMode(boolean addMode) {
        this.addMode = addMode;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser() {
        return user;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId(Long userId) {
        return this.userId;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    // Action methods
    public void list() {
        users = userService.list();
        System.out.println("list");
    }
    
    public void onRowSelect() {
        System.out.println(selectedUser);
    }

    @PostConstruct
    public void init() {
        list(); 
    }

    // delete method
    public void delete() {
        if (selectedUser != null && selectedUser.getId() > 0) {
            userService.remove(selectedUser.getId());
            selectedUser = null;
            list();  
        }
    }

    // search method
    public void search() {
        if (keyword != null && !keyword.isEmpty()) {
            users = userService.selectByKeyword(keyword);
        }
    }

    // editing methods
    public void edit() {
        if (selectedUser != null) {
            userService.update(selectedUser); 
            this.editMode = false; 
        }
    }

    public void enableEditMode() {
        this.editMode = true;
        this.addMode = false;
    }

    public void update() {
        if (user != null && user.getId() != null) { 
            userService.update(user);
        }
    }

    // adding methods
    public void enableAddMode() {
        this.addMode = true;
        this.editMode = false;
        this.setNewUser(new User());
    }

    public void add() {
        userService.add(newUser); 
        this.addMode = false;
        list();
    }
}
