package pe.edu.utp.micunatruck.models;

import java.sql.Connection;
import java.util.List;

public class UserTypeService {
    private Connection connection;
    private UserTypeEntity userTypeEntity;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

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
}
