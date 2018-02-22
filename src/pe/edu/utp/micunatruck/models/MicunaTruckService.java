package pe.edu.utp.micunatruck.models;

import java.sql.Connection;
import java.util.List;

public class MicunaTruckService {
    private Connection connection;

    private UsersEntity usersEntity;
    private EventsEntity eventsEntity;
    private UserTypeEntity userTypeEntity;
    private AdminsEntity adminsEntity;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /* USERS */
    public UsersEntity getUsersEntity() {
        if(getConnection() != null){
            if(usersEntity == null){
                usersEntity = new UsersEntity();
                usersEntity.setConnection(getConnection());
            }
        }
        return usersEntity;
    }

    public List<User> findAllUsers() {
        return getUsersEntity() != null ? getUsersEntity().findAll() : null;
    }

    public User findUserById(int id){
        return getUsersEntity() != null ? getUsersEntity().findById(id) : null;
    }

    public User findUserByEmailAndPassword(String email, String password){
        return getUsersEntity() != null ? getUsersEntity().findUserByEmailAndPassword(email, password) : null;
    }

    public User createUser(int userTypeId, String name, String lastName, String legalName,
                           String description, String photo, String address, String telephone,
                           String email, String password, boolean flagActive){
        return getUsersEntity() != null ? getUsersEntity().create(userTypeId, name, lastName, legalName,
                description, photo, address, telephone,
                email, password, flagActive) : null;
    }

    public boolean deleteUser(int id){
        return getUsersEntity() != null ?
                getUsersEntity().delete(id) : false;
    }

    public boolean updateUser(User user){
        return getUsersEntity() != null ?
                getUsersEntity().update(user) : false;
    }

    /* EVENTS */
    public EventsEntity getEventsEntity() {
        if(getConnection() != null){
            if(eventsEntity == null){
                eventsEntity = new EventsEntity();
                eventsEntity.setConnection(getConnection());
            }
        }
        return eventsEntity;
    }

    public List<Event> findAllEvents() {
        return getEventsEntity() != null ? getEventsEntity().findAll() : null;
    }

    /* USER TYPE */
    public UserTypeEntity getUsersTypeEntity() {
        if(getConnection() != null){
            if(userTypeEntity == null){
                userTypeEntity = new UserTypeEntity();
                userTypeEntity.setConnection(getConnection());
            }
        }
        return userTypeEntity;
    }

    public List<UserType> findAllUsersType() {
        return getUsersTypeEntity() != null ? getUsersTypeEntity().findAll() : null;
    }

    public UserType findUserTypeById(int id){
        return getUsersTypeEntity() != null ? getUsersTypeEntity().findById(id) : null;
    }



    public Event createEvent(User user, EventStatus eventStatus, String name, String description, String image) {
        return getEventsEntity() != null ?
                getEventsEntity().create(user, eventStatus, name, description, image) : null;
    }

    public boolean deleteEvent(int id) {
        return getEventsEntity() != null ?
                getEventsEntity().delete(id) : false;
    }

    public boolean updateEvent(Event event, User user, EventStatus eventStatus) {
        return getEventsEntity() != null ?
                getEventsEntity().update(event, user, eventStatus) : false;
    }

    /* ADMIN */

    protected AdminsEntity getAdminsEntity(){
        if (getConnection()!=null){
            if (adminsEntity==null){
                adminsEntity=new AdminsEntity();
                adminsEntity.setConnection(getConnection());
            }
        }
        return adminsEntity;
    }


}
