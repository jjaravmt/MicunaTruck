/*
 * Created by MIGUEL ANGEL POMA HINOSTROZA
 * Date:
 * Time:
 * Description:
 * */

package pe.edu.utp.micunatruck.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersEntity extends BaseEntity {
    private static String DEFAULT_SQL = "SELECT * FROM micunatruck.users ";
    private static String DEFAULT_SQL_UPDATE = "UPDATE micunatruck.users SET ";

    private List<User> findByCriteria(String sql, UserTypeEntity userTypeEntity)
    {
        List<User> users = null;
        boolean indHasData = false;
        if(getConnection() != null){
            users = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);

                while (resultSet.next()){
                    indHasData = true;
                    User region = new User()
                            .setId(resultSet.getInt("id"))
                            .setUserType(userTypeEntity.findById(resultSet.getInt("user_type_id")))
                            .setName(resultSet.getString("name"))
                            .setLastName(resultSet.getString("lastname"))
                            .setLegalName(resultSet.getString("legal_name"))
                            .setDescription(resultSet.getString("description"))
                            .setPhoto(resultSet.getString("photo"))
                            .setAddress(resultSet.getString("address"))
                            .setTelephone(resultSet.getString("telephone"))
                            .setEmail(resultSet.getString("email"))
                            .setPassword(resultSet.getString("password"))
                            .setFlagActive(resultSet.getBoolean("flag_active"))
                            ;
                    users.add(region);
                }

                if(!indHasData)
                    users = null;

                return users;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<User> findAll(){
        return findByCriteria(DEFAULT_SQL + "WHERE flag_active = 1", new UserTypeEntity());
    }

    public User findById(int id){
        List<User> users = findByCriteria(DEFAULT_SQL + " WHERE flag_active = 1 AND id = " + String.valueOf(id), new UserTypeEntity());
        return (users) != null ? users.get(0) : null;
    }

    public User findUserByEmailAndPassword(String email, String password){
        List<User> users = findByCriteria(DEFAULT_SQL +
                                            " WHERE flag_active = 1 AND email = '" + email + "'" +
                                            " AND password = '" + password + "'", new UserTypeEntity());
        return (users) != null ? users.get(0) : null;
    }

    public User findByName(String name){
        List<User> users = findByCriteria(DEFAULT_SQL + " WHERE name LIKE '%" + name + "%'", new UserTypeEntity());
        return (users != null) ? users.get(0) : null;
    }

    private int updateByCriteria(String sql){
        if(getConnection() != null){
            try {
                return getConnection().createStatement().executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public User create(UserType userType, String name, String lastName, String legalName,
                       String description, String photo, String address, String telephone,
                       String email, String password, boolean flagActive){
        if(findByName(name) == null){
            if(getConnection() != null){
                String sql =
                        "INSERT INTO micunatruck.users(" +
                            "user_type_id, name, lastname, legal_name, description, photo, " +
                            "address, telephone, email, password, flag_active, created_at) " +
                        "VALUES("
                            + String.valueOf(userType.getId())
                            + ",'" + name + "'"
                            + ",'" + lastName + "'"
                            + ",'" + legalName + "'"
                            + ",'" + description + "'"
                            + ",'" + photo + "'"
                            + ",'" + address + "'"
                            + ",'" + telephone + "'"
                            + ",'" + email + "'"
                            + ",'" + password + "'"
                            + "," +  String.valueOf(flagActive ? 1 : 0)
                            + ", NOW()"
                        + ")";
                int results = updateByCriteria(sql);
                if(results > 0){
                    User region = new User();
                    return region;
                }
            }
        }
        return null;
    }

    public boolean delete(int id){
        return updateByCriteria(DEFAULT_SQL_UPDATE + "flag_active = 0, deleted_at = NOW() WHERE id = " +  String.valueOf(id)
        ) > 0;
    }

    /*public boolean delete(String name){
        return updateByCriteria("DELETE FROM micunatruck.users WHERE name = '" +  name + "'") > 0;
    }*/

    public boolean update(User user, UserType userType){
        String sql = DEFAULT_SQL_UPDATE +
                "user_type_id = " + userType.getId() + ", " +
                "name = '" + user.getName() + "', " +
                "lastname = '" + user.getLastName() + "', " +
                "legal_name = '" + user.getLegalName() + "', " +
                "description = '" + user.getDescription() + "', " +
                "photo = '" + user.getPhoto() + "', " +
                "address = '" + user.getAddress() + "', " +
                "telephone = '" + user.getTelephone() + "', " +
                "email = '" + user.getEmail() + "', " +
                "password = '" + user.getPassword() + "', " +
                "flag_active = " + String.valueOf(user.getFlagActive() ? 1 : 0)  + ", " +
                "updated_at = NOW() " +
                "WHERE id = " + String.valueOf(user.getId());
        return updateByCriteria(sql) > 0;
    }
}