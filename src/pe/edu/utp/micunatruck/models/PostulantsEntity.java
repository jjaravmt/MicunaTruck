package pe.edu.utp.micunatruck.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostulantsEntity extends BaseEntity {

    private static String DEFAULT_SQL = "SELECT * FROM micunatruck.postulants ";
    private static String DEFAULT_SQL_UPDATE = "UPDATE micunatruck.postulants SET ";

    private List<Postulant> findByCriteria(String sql, UsersEntity usersEntity, EventsEntity eventsEntity)
    {
        List<Postulant> postulants = null;
        boolean indHasData = false;
        if(getConnection() != null){
            postulants = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);

                while (resultSet.next()){
                    indHasData = true;
                    Postulant postulant = new Postulant()
                            .setId(resultSet.getInt("id"))
                            .setUser(usersEntity.findById(resultSet.getInt("user_id")))
                            .setEvent(eventsEntity.findById(resultSet.getInt("event_id")))
                            .setFlagActive(resultSet.getBoolean("flag_active"))
                            ;
                    postulants.add(postulant);
                }

                if(!indHasData)
                    postulants = null;

                return postulants;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Postulant> findAll(UsersEntity usersEntity, EventsEntity eventsEntity){
        return findByCriteria(DEFAULT_SQL + "WHERE flag_active = 1", usersEntity, eventsEntity);
    }

    public Postulant findById(int id, UsersEntity usersEntity, EventsEntity eventsEntity){
        List<Postulant> postulants = findByCriteria(DEFAULT_SQL + " WHERE flag_active = 1 AND id = " + String.valueOf(id),
                usersEntity,
                eventsEntity);
        return (postulants) != null ? postulants.get(0) : null;
    }

    public List<Postulant> findByUser(User user, UsersEntity usersEntity, EventsEntity eventsEntity){
        List<Postulant> postulants = findByCriteria(DEFAULT_SQL + " WHERE flag_active = 1 AND user_id = " + String.valueOf(user.getId()),
                usersEntity,
                eventsEntity);
        return (postulants) != null ? postulants : null;
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

    public Postulant create(User user, Event event, boolean flagActive){
        //if(findByName(name) == null){
            if(getConnection() != null){
                String sql =
                        "INSERT INTO micunatruck.postulants(" +
                                "user_id, event_id, flag_active, created_at) " +
                                "VALUES("
                                + "," + String.valueOf(user.getId())
                                + "," + String.valueOf(event.getId())
                                + "," +  String.valueOf(flagActive ? 1 : 0)
                                + ", NOW()"
                                + ")";
                int results = updateByCriteria(sql);
                if(results > 0){
                    Postulant postulant = new Postulant(getMaxId(), user, event);
                    return postulant;
                }
            }
        //}
        return null;
    }

    private int getMaxId(){
        String sql = "SELECT MAX(id) AS max_id FROM micunatruck.postulants";
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
}
