package pe.edu.utp.micunatruck.models;

import java.util.Date;

public class Postulant {

    private int id;
    private User user;
    private Event event;
    private boolean flagActive;
    private Date updatedAt;
    private Date deleteAt;
    private Date createdAt;

    public Postulant() {

    }

    public Postulant(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public Postulant(int id, User user, Event event) {
        this.id = id;
        this.user = user;
        this.event = event;
    }

    public Postulant(int id, User user, Event event, boolean flagActive, Date updatedAt, Date deleteAt, Date createdAt) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.flagActive = flagActive;
        this.updatedAt = updatedAt;
        this.deleteAt = deleteAt;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public Postulant setId(int id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Postulant setUser(User user) {
        this.user = user;
        return this;
    }

    public Event getEvent() {
        return event;
    }

    public Postulant setEvent(Event event) {
        this.event = event;
        return this;
    }

    public boolean isFlagActive() {
        return flagActive;
    }

    public Postulant setFlagActive(boolean flagActive) {
        this.flagActive = flagActive;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Postulant setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public Postulant setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Postulant setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}