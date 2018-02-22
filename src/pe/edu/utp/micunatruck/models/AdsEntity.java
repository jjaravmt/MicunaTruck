package pe.edu.utp.micunatruck.models;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdsEntity extends BaseEntity {


        private static String DEFAULT_SQL = "SELECT * FROM micunatruck.ads ";
        /*private static String DEFAULT_SQL_UPDATE = "UPDATE micunatruck.ads SET ";*/


    private List<Ads> findByCriteria(String sql,AdminsEntity adminsEntity){
        List<Ads> adsRs;
        if(getConnection() != null){
            adsRs = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement()
                        .executeQuery(sql);
                while (resultSet.next()){
                    Ads ads = new Ads(
                            resultSet.getInt("id"),
                            adminsEntity.findById(resultSet.getInt("admin_id")),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getString("image"),
                            resultSet.getDouble("price"),
                            resultSet.getDate("start_date"),
                            resultSet.getDate("end_date"),
                            resultSet.getBoolean("flag_active"),
                            resultSet.getDate("deleted_at"),
                            resultSet.getDate("updated_at"),
                            resultSet.getDate("created_at"),
                            resultSet.getInt("idSpace"));
                    adsRs.add(ads);
                }
                return adsRs;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Ads> findAll(AdminsEntity adminsEntity){
        return findByCriteria(DEFAULT_SQL,adminsEntity);
    }

    public Ads findById(int id,AdminsEntity adminsEntity){
        List<Ads> adsRs=findByCriteria(DEFAULT_SQL + "WHERE id="+String.valueOf(id),adminsEntity);
        return (adsRs!=null?adsRs.get(0):null);
    }

    public Ads findByNameSdEd(String name,Date start_date,Date end_date,AdminsEntity adminsEntity){
        List<Ads> adsRs=findByCriteria(DEFAULT_SQL+"WHERE start_date='"+start_date+"' and end_date='"+end_date+"'",adminsEntity);
        return (adsRs!=null?adsRs.get(0):null);
    }

    private int updateByCriteria(String sql){
        if (getConnection()!=null){
            try {
                    return getConnection()
                        .createStatement()
                        .executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }



//    public Ads create(int idadmin,String name,String description,
//                      String image,Double price,Date start_date,Date end_date,
//                      int idspace, AdminsEntity adminsEntity){
//
//        if (findByNameSdEd(name,start_date,end_date,adminsEntity)==null){
//            if (getConnection()!=null){
//                String sql="INSERT INTO ads(admin_id,name,description,image,price,flag_active,start_date,end_date,created_at,idspace)" +
//                        "values("+idadmin+",'"+name+"','"+description+"','"+image+"',"+price+",1,'"+start_date+"','"+end_date+"',NOW(),"+idspace+")";
//
//                int results=updateByCriteria(sql);
//                if (results>0){
//                    Ads ads=new Ads();
//                }
//            }
//
//        }
//    }







}