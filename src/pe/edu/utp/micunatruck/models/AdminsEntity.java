package pe.edu.utp.micunatruck.models;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class AdminsEntity extends BaseEntity {
    private static String DEFAULT_SQL = "SELECT * FROM micunatruck.users ";

    public Admin findById(int id){
        List<Admin> adsRs=findByCriteria(DEFAULT_SQL + "WHERE id="+String.valueOf(id));
        return (adsRs!=null?adsRs.get(0):null);
    }

    private List<Admin> findByCriteria(String sql)
    {
        List<Admin> ads = null;
        boolean indHasData = false;
        if(getConnection() != null){
            ads = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);

                while (resultSet.next()){
                    indHasData = true;
                    Admin region = new Admin()
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setLastName(resultSet.getString("last_name"))
                            .setPhoto(resultSet.getString("photo"))
                            .setEmail(resultSet.getString("email"))
                            .setPassword(resultSet.getString("password"))
                            .setFlagActive(resultSet.getBoolean("flag_active"))
                            ;
                    ads.add(region);
                }

                if(!indHasData)
                    ads = null;

                return ads;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
