package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.file.UploadedFile;

import models.User;
import services.UserService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

    // Properties for file upload
    private UploadedFile newUserPhoto;  // Photo for the new user
    private UploadedFile selectedUserPhoto; // Photo for the selected user during editing

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

    // Getter and Setter for file upload
    public UploadedFile getNewUserPhoto() {
        return newUserPhoto;
    }

    public void setNewUserPhoto(UploadedFile newUserPhoto) {
        this.newUserPhoto = newUserPhoto;
    }

    public UploadedFile getSelectedUserPhoto() {
        return selectedUserPhoto;
    }

    public void setSelectedUserPhoto(UploadedFile selectedUserPhoto) {
        this.selectedUserPhoto = selectedUserPhoto;
    }

    // Action methods
    public void list() {
        users = userService.list();
        System.out.println("list");
    }
    
    public void onRowSelect() {
//        System.out.println(selectedUser);
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
    	System.out.println(newUserPhoto);
        if (newUserPhoto != null) {
            System.out.println("Uploaded file: " + newUserPhoto.getFileName());
            String photoPath = "/resources/img/" + newUserPhoto.getFileName();
            savePhoto(newUserPhoto, photoPath);
            newUser.setPhoto(photoPath);
        } else {
            System.out.println("No file selected.");
        }
        userService.add(newUser);
        this.addMode = false;
        list();
    }


    private void savePhoto(UploadedFile file, String photoPath) {
        try {
            System.out.println("Saving photo...");
            InputStream input = file.getInputStream();
            String filePath = "C:\\Users\\Kaoutar\\eclipse-workspace\\catalogue-jsf\\src\\main\\webapp\\resources\\img\\" + file.getFileName();
            File outputFile = new File(filePath);
            
            try (FileOutputStream output = new FileOutputStream(outputFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
            }
            System.out.println("Photo saved at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // Logic for updating the photo during editing
    public void updatePhoto() {
        if (selectedUserPhoto != null) {
            String photoPath = "/resources/img/" + selectedUserPhoto.getFileName();
            savePhoto(selectedUserPhoto, photoPath);
            selectedUser.setPhoto(photoPath); // Update the photo path
        }
        userService.update(selectedUser); 
    }
}
