package pe.edu.utp.micunatruck.beans;


import pe.edu.utp.micunatruck.models.EventStatus;
import pe.edu.utp.micunatruck.models.MicunaTruckService;
import pe.edu.utp.micunatruck.models.Event;
import pe.edu.utp.micunatruck.models.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class EventsBean implements Serializable{
    private MicunaTruckService service;
    private Event event;
    private User user;
    private EventStatus eventStatus;

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
    //patron de diseño delegate
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
        service.createEvent(this.getUser(), this.getEventStatus(), this.getName(), this.getDescription(), this.getImage());
        return "success";
    }

    public String deleteEvent(Event event){
        service.deleteEvent(event.getId());
        return "success";
    }

}
