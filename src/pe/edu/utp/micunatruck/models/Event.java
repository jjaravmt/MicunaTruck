package pe.edu.utp.micunatruck.models;

import java.util.Date;

public class Event {

    private int id;
    private User user;
    private EventStatus eventStatus;
    private String name;
    private String description;
    private String image;
    private int flagActive;
    private Date deleteAt;
    private Date updatedAt;
    private Date createdAt;

    public Event(){

    }

    public Event(int id, User user, EventStatus eventStatus, String name, String description, String image, int flagActive){
        this.setId(id);
        this.setUser(user);
        this.setEventStatus(eventStatus);
        this.setName(name);
        this.setDescription(description);
        this.setImage(image);
        this.setFlagActive(flagActive);
    }

    public int getId() {
        return id;
    }

    public Event setId(int id) {
        this.id = id;
        return this;
    }



    public String getName() {
        return name;
    }

    public Event setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Event setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Event setImage(String image) {
        this.image = image;
        return this;
    }

    public int getFlagActive() {
        return flagActive;
    }

    public Event setFlagActive(int flagActive) {
        this.flagActive = flagActive;
        return this;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }


    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public Event setUser(User user) {
        this.user = user;
        return this;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public Event setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
        return this;
    }
}
