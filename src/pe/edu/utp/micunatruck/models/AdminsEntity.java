package pe.edu.utp.micunatruck.models;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class AdminsEntity extends BaseEntity {

    private static String DEFAULT_SQL = "SELECT * FROM admins";
    private static String DEFAULT_SQL_UPDATE = "UPDATE micunatruck.admins SET ";

    private List<Admin> findByCriteria(String sql) {
        List<Admin> admins = null;
        boolean indHasData = false;
        if (getConnection() != null) {
            admins = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
                while (resultSet.next()) {
                    indHasData = true;
                    Admin region = new Admin()
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setLastName(resultSet.getString("lastname"))
                            .setPhoto(resultSet.getString("photo"))
                            .setEmail(resultSet.getString("email"))
                            .setPassword(resultSet.getString("password"))
                            .setFlag_active(resultSet.getBoolean("flag_active"));
                    admins.add(region);
                }

                if (!indHasData)
                    admins = null;

                return admins;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
        public List<Admin> findAll(){
            return findByCriteria(DEFAULT_SQL + "WHERE flag_active = 1");
        }

    public Admin findById(int id){
        List<Admin> admins = findByCriteria(DEFAULT_SQL + " WHERE flag_active = 1 AND id = " + String.valueOf(id));
        return (admins) != null ? admins.get(0) : null;
    }

    public Admin findUserByEmailAndPassword(String email, String password){
        List<Admin> admins = findByCriteria(DEFAULT_SQL +
                " WHERE flag_active = 1 AND email = '" + email + "'" +
                " AND password = '" + password + "'");
        return (admins) != null ? admins.get(0) : null;
    }

    public Admin findByName(String name){
        List<Admin> admins = findByCriteria(DEFAULT_SQL + " WHERE name LIKE '%" + name + "%'");
        return (admins != null) ? admins.get(0) : null;
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

    public Admin create( int id, String name, String lastName,String photo,
                       String email, String password, boolean flagActive){
        if(findByName(name) == null){
            if(getConnection() != null){
                String sql =
                        "INSERT INTO micunatruck.admins(" +
                                "id, name, lastname, photo, " +
                                "email, password, flag_active, created_at) " +
                                "VALUES("
                                + ",'" + id + "'"
                                + ",'" + name + "'"
                                + ",'" + lastName + "'"
                                + ",'" + photo + "'"
                                + ",'" + email + "'"
                                + ",'" + password + "'"
                                + "," +  String.valueOf(flagActive ? 1 : 0)
                                + ", NOW()"
                                + ")";
                int results = updateByCriteria(sql);
                if(results > 0){
                    Admin region = new Admin();
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

    public boolean update(Admin admin){
        String sql = DEFAULT_SQL_UPDATE +
                "id = '" + admin.getId() + "', " +
                "name = '" + admin.getName() + "', " +
                "lastname = '" + admin.getLastName() + "', " +
                "photo = '" + admin.getPhoto() + "', " +
                "email = '" + admin.getEmail() + "', " +
                "password = '" + admin.getPassword() + "', " +
                "flag_active = " + String.valueOf(admin.getFlag_active() ? 1 : 0)  + ", " +
                "updated_at = NOW() " +
                "WHERE id = " + String.valueOf(admin.getId());
        return updateByCriteria(sql) > 0;
    }

}
