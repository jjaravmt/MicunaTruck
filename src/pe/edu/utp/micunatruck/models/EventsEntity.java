package pe.edu.utp.micunatruck.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventsEntity extends BaseEntity {
    private static String DEFAULT_SQL = "SELECT * FROM micunatruck.events";

    private List<Event> findByCriteria(String sql){
        List<Event> events;
        if(getConnection() != null){
            events = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement()
                        .executeQuery(sql);
                while (resultSet.next()){
                    Event region = new Event()
                            .setId(resultSet.getInt("id"))
                            .setUserId(resultSet.getInt("user_id"))
                            .setEventStatusId(resultSet.getInt("event_status_id"))
                            .setName(resultSet.getString("name"))
                            .setDescription(resultSet.getString("description"))
                            .setImage(resultSet.getString("image"))
                            .setFlagActive(resultSet.getByte("flag_active"))
                            ;//comentario
                    events.add(region);
                }
                return events;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Event> findAll(){
        return findByCriteria(DEFAULT_SQL);
    }

    public Event findById(int id){
        List<Event> events = findByCriteria(DEFAULT_SQL + " WHERE id = " +
                String.valueOf(id));
        return (events) != null ? events.get(0) : null;
    }

    public Event findByName(String name){
        List<Event> events = findByCriteria(DEFAULT_SQL+ " WHERE name like '%" + name + "%'");
        return (events != null) ? events.get(0) : null;
    }

    private int getMaxId(){
        String sql = "SELECT MAX(id) AS max_id FROM events";
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

    public Event create(int user_id, int event_status_id, String name, String description, String image){
        if(findByName(name) == null){
            if(getConnection() != null){
                Date createdAt = new Date();
                String sql = "INSERT INTO events(user_id, event_status_id, name, description, image, flag_active, updated_at, created_at) " +
                        "VALUES("+user_id+","+event_status_id+",'"+name+"','"+description+"','"+image+"',0,'"+(new Timestamp(createdAt.getTime())).toString()+"','"+(new Timestamp(createdAt.getTime())).toString()+"')";
                int results = updateByCriteria(sql);
                if(results > 0){
                    Event event = new Event(getMaxId(), user_id, event_status_id, name, description, image, 1);
                    return event;
                }
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

    public boolean update(Event region){
        return updateByCriteria("UPDATE region SET user_id='"+String.valueOf(region.getUserId())+
                "', event_status_id ='"+String.valueOf(region.getUserId())+"', name = '"+region.getName()+
                "', description ='"+String.valueOf(region.getDescription())+"', image='"+String.valueOf(region.getImage())+"' WHERE id = " + String.valueOf(region.getId())) > 0;
    }
}
