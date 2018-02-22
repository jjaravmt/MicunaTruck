package pe.edu.utp.micunatruck.models;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class MicunaTruckService {
    private Connection connection;

    private UsersEntity usersEntity;
    private EventsEntity eventsEntity;
    private UserTypeEntity userTypeEntity;
    private AdsEntity adsEntity;

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

    protected AdsEntity getAdsEntity(){
        if (getConnection()!=null){
            if (adsEntity==null){
                adsEntity=new AdsEntity();
                adsEntity.setConnection(getConnection());
            }
        }
        return adsEntity;
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

    public List<Ads> findAllAds(){
        return getAdsEntity()!=null?
                getAdsEntity().findAll(getAdminsEntity()):null;
    }

    public Ads findAdsById(int id){
        return getAdsEntity()!=null?getAdsEntity().findById(id,getAdminsEntity()):null;
    }

    public Ads findAdsBySpace(int idspace){
        return getAdsEntity()!=null?getAdsEntity().findBySpace(idspace,getAdminsEntity()):null;
    }

    public Ads createAds(int idadmin, String name, String description, String image, Double price,
                         Date start_date,Date end_date,int idspace){
        return getAdsEntity()!=null?getAdsEntity().create(idadmin,name,description,image,price,start_date,end_date,
                idspace,getAdminsEntity()):null;
    }

    public boolean updateAds(int id, String name, String description, String image, Double price,
                             Date start_date, Date end_date, int flag_active, int idspace){
        return getAdsEntity()!=null?getAdsEntity().update(id,name,description,image,price,start_date,end_date,flag_active,idspace,getAdminsEntity()):false;
    }

    public boolean deleteAds(int id){
        return getAdsEntity()!=null?getAdsEntity().delete(id):false;
    }

}
