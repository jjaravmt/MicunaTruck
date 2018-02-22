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

    private MicunaTruckService service;
    private Admin admin;
    private User user;
    
}
