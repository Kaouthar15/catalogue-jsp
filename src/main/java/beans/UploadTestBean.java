package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.file.UploadedFile;

@ManagedBean
@RequestScoped
public class UploadTestBean {
    private UploadedFile uploadedFile;

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void handleUpload() {
        if (uploadedFile != null) {
            System.out.println("Uploaded file name: " + uploadedFile.getFileName());
        } else {
            System.out.println("No file selected.");
        }
    }
}
