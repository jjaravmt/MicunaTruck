package pe.edu.utp.micunatruck.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinancesEntity extends BaseEntity {
    private static String DEFAULT_SQL = "SELECT * FROM micunatruck.incomes";

    private List<Finance> findByCriteria(String sql){
        List<Finance> Finances;
        if(getConnection() != null){
            Finances = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement()
                        .executeQuery(sql);
                while (resultSet.next()){
                    Finance region = new Finance()
                            .setId(resultSet.getInt("id"))
                                .setOriginId(resultSet.getInt("originId"))
                            .setOriginTypeId(resultSet.getInt("originId"))
                            .setAmount(resultSet.getDouble("amount"))
                            .setStartDate(resultSet.getDate("start_date"))
                            .setEndDate(resultSet.getDate("end_date"))
                            .setFlagActive(resultSet.getBoolean("flag_active"))
                            ;//comentario
                    Finances.add(region);
                }
                return Finances;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Finance> findAll(){
        return findByCriteria(DEFAULT_SQL);
    }
    ///listFinance
    public Finance findById(int id){
        List<Finance> finances = findByCriteria(DEFAULT_SQL + " WHERE id = " +
                String.valueOf(id));
        return (finances) != null ? finances.get(0) : null;
    }

    public Finance findByName(String name){
        List<Finance> incomes = findByCriteria(DEFAULT_SQL+ " WHERE name like '%" + name + "%'");
        return (incomes != null) ? incomes.get(0) : null;
    }

    private int getMaxId(){
        String sql = "SELECT MAX(id) AS max_id FROM income";
        if(getConnection() != null){
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
                return resultSet.next() ? resultSet.getInt("max_id") : 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
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

    public Finance create(int id, int originId, int originTypeId, double amount, Date startDate, Date endDate,
                          boolean flagActive){
        if(getConnection() != null){
            String sql = "INSERT INTO incomes(id, origin_id, origin_type_id, " +
                    "amount, start_date, end_date, flag_active, created_at) " +
                    "VALUES(" +
                    id +
                    ", " + originId +
                    ", '" + originTypeId + "'" +
                    ", " + amount+
                    ", '" + startDate + "'" +
                    ", '" + endDate + "'" +
                    ", " + flagActive +
                    ", NOW())";
            int results = updateByCriteria(sql);
            if(results > 0){
                Finance finance = new Finance(id,originId,originTypeId,amount,startDate,endDate,
                flagActive);
                return finance;
            }
        }
        return null;
    }

    public boolean delete(int id){
        return updateByCriteria("DELETE FROM events WHERE id = " +  String.valueOf(id)) > 0;
    }

    public boolean delete(String name){
        return updateByCriteria("DELETE FROM events WHERE name = " +  name) > 0;
    }

    /*public boolean update(Finance region){
        return updateByCriteria("UPDATE region SET user_id="+String.valueOf(region.getUserId())+
                ", event_status_id ='"+String.valueOf(region.getUserId())+"', name = '"+region.getName()+
                "', description ='"+String.valueOf(region.getDescription())+"', image='"+String.valueOf(region.getImage())+"' WHERE id = " + String.valueOf(region.getId())) > 0;
    }*/
}