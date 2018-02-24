package pe.edu.utp.micunatruck.beans;
import pe.edu.utp.micunatruck.models.EventStatus;
import pe.edu.utp.micunatruck.models.MicunaTruckService;
import pe.edu.utp.micunatruck.models.Admin;
import pe.edu.utp.micunatruck.models.User;




import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class AdminsBean implements Serializable {

    private MicunaTruckService micunaTruckService;
    private Admin admin;
    private User user;


    public AdminsBean() {
        micunaTruckService = new MicunaTruckService();
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getName() {
        return this.getAdmin().getName();
    }

    public void setName(String name) {
        this.getAdmin().setName(name);
    }

    public String getLastName() {
        return this.getAdmin().getLastName();
    }

    public void setLastName(String lastName) {
        this.getAdmin().setLastName(lastName);
    }

    public String getPhoto() {
        return this.getAdmin().getPhoto();

    }

    public void setPhoto(String photo) {
        this.getAdmin().setPhoto(photo);
    }

    public String getEmail() {
        return this.getAdmin().getEmail();
    }

    public void setEmail(String email) {
        this.getAdmin().setEmail(email);
    }

    public String getPassword() {
        return this.getAdmin().getPassword();
    }

    public void setPassword(String password) {
        this.getAdmin().setPassword(password);
    }

}
