package pe.edu.utp.micunatruck.beans;


import pe.edu.utp.micunatruck.models.EventStatus;
import pe.edu.utp.micunatruck.models.MicunaTruckService;
import pe.edu.utp.micunatruck.models.Event;
import pe.edu.utp.micunatruck.models.User;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.PartialResponseWriter;

@Named
@SessionScoped
//@ManagedBean
//@ViewScoped
public class EventsBean implements Serializable{
    private MicunaTruckService service;
    private Event event;
    private User user;
    private EventStatus eventStatus;
//    private Part uploadedImage;

    public EventsBean() {
        service = new MicunaTruckService();
    }

    public List<Event> getEvents() {
        return service.findAllEvents();
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getName() {
        return this.getEvent().getName();
    }
    //patron de diseño delegate
    public void setName(String name){
        this.getEvent().setName(name);
    }

    public String getDescription() {
        return this.getEvent().getDescription();
    }

    public void setDescription(String description){
        this.getEvent().setDescription(description);
    }

    public String getImage() {
        return this.getEvent().getImage();
    }

    public void setImage(String image){
        this.getEvent().setName(image);
    }

    public String editEvent(Event event){
        this.setEvent(event);
        return "success";
    }

    public String newEvent(){
        this.setEvent(new Event());
        return "success";
    }

    public String updateEvent(){
        service.updateEvent(this.getEvent(), this.getUser(), this.getEventStatus());
        return "success";
    }

    public String createEvent(){
        //this.getName();

//        try (InputStream input = uploadedImage.getInputStream()) {
//            String fileName = uploadedImage.getSubmittedFileName();
//            File image = new File(this.getPathBaseImage(), fileName);
//            Files.copy(input, image.toPath());
//            this.setImage(image.toString());
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }

        service.createEvent(this.getUser(), this.getEventStatus(), this.getName(), this.getDescription(), this.getImage(), this.getEvent().getDate());
        return "success";
    }

    public String deleteEvent(Event event){
        service.deleteEvent(event.getId());
        return "success";
    }

    public String getDate() {
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date = "";
        try{
            date = formatter.format(this.getEvent().getDate());
        }catch (Exception e) {
            date = "";
        }
        return date;
    }

    public void setDate(String date){
        this.getEvent().setDate(date);
    }

    public void validateImage(FacesContext ctx, UIComponent comp, Object value){

    }

//    public Part getUploadedImage() {
//        return uploadedImage;
//    }
//
//    public void setUploadedImage(Part uploadedImage) {
//        this.uploadedImage = uploadedImage;
//    }

    public String getPathBaseImage(){
        Path folder = Paths.get("/web/uploads");
        return folder.toString();
    }
}
