package pe.edu.utp.micunatruck.beans;

import pe.edu.utp.micunatruck.models.Applicant;
import pe.edu.utp.micunatruck.models.MicunaTruckService;
import pe.edu.utp.micunatruck.models.User;
import pe.edu.utp.micunatruck.models.Event;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class AplicantBean implements Serializable {

    private MicunaTruckService micunaTruckService;
    private Applicant applicant;
    private User user;
    private Event event;

    public AplicantBean(){
        micunaTruckService = new MicunaTruckService();
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getId(){
        return this.getApplicant().getId();
    }

    public void setId(int id){
        this.getApplicant().setId(id);
    }

    public int getEventId(){
        return this.getEvent().getId();
    }

    public void setEventId(int id){
        this.getEvent().setId(id);
    }

    public String newApplicant(Event event){
        this.setUser((User) SessionUtils.getUser());
        this.setApplicant(micunaTruckService.createPostulant(getUser(), event, false));
        return "success";
    }

    public void submit() {
        this.setUser((User) SessionUtils.getUser());
        /*this.setApplicant(micunaTruckService.createPostulant(getUser(), event, false));*/
    }
}
