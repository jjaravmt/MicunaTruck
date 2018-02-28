package pe.edu.utp.micunatruck.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicantsEntity extends BaseEntity {

    private static String DEFAULT_SQL = "SELECT * FROM micunatruck.applicants ";
    private static String DEFAULT_SQL_UPDATE = "UPDATE micunatruck.applicants SET ";

    private List<Applicant> findByCriteria(String sql, UsersEntity usersEntity, EventsEntity eventsEntity)
    {
        List<Applicant> applicants = null;
        boolean indHasData = false;
        if(getConnection() != null){
            applicants = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);

                while (resultSet.next()){
                    indHasData = true;
                    Applicant applicant = new Applicant()
                            .setId(resultSet.getInt("id"))
                            .setUser(usersEntity.findById(resultSet.getInt("user_id")))
                            .setEvent(eventsEntity.findById(resultSet.getInt("event_id")))
                            .setFlagActive(resultSet.getBoolean("flag_active"))
                            ;
                    applicants.add(applicant);
                }

                if(!indHasData)
                    applicants = null;

                return applicants;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Applicant> findAll(UsersEntity usersEntity, EventsEntity eventsEntity){
        return findByCriteria(DEFAULT_SQL + "WHERE flag_active = 1", usersEntity, eventsEntity);
    }

    public Applicant findById(int id, UsersEntity usersEntity, EventsEntity eventsEntity){
        List<Applicant> applicants = findByCriteria(DEFAULT_SQL + " WHERE flag_active = 1 AND id = " + String.valueOf(id),
                usersEntity,
                eventsEntity);
        return (applicants) != null ? applicants.get(0) : null;
    }

    public List<Applicant> findByUser(User user, UsersEntity usersEntity, EventsEntity eventsEntity){
        List<Applicant> applicants = findByCriteria(DEFAULT_SQL + " WHERE flag_active = 1 AND user_id = " + String.valueOf(user.getId()),
                usersEntity,
                eventsEntity);
        return (applicants) != null ? applicants : null;
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

    public Applicant create(User user, Event event, boolean flagActive){
        //if(findByName(name) == null){
            if(getConnection() != null){
                String sql =
                        "INSERT INTO micunatruck.applicants(" +
                                "user_id, event_id, flag_active, created_at) " +
                                "VALUES("
                                + String.valueOf(user.getId())
                                + "," + String.valueOf(event.getId())
                                + "," +  String.valueOf(flagActive ? 1 : 0)
                                + ", NOW()"
                                + ")";
                int results = updateByCriteria(sql);
                if(results > 0){
                    Applicant applicant = new Applicant(getMaxId(), user, event);
                    return applicant;
                }
            }
        //}
        return null;
    }

    private int getMaxId(){
        String sql = "SELECT MAX(id) AS max_id FROM micunatruck.applicants";
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

    public List<Applicant> findAllByEvent(Event event, UsersEntity usersEntity, EventsEntity eventsEntity){
        String sql = DEFAULT_SQL + " WHERE event_id = " + String.valueOf(event.getId()) + " AND deleted_at IS NULL";
        return findByCriteria(sql, usersEntity, eventsEntity);
    }
}
