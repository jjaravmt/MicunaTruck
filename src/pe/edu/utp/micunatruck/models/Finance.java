package pe.edu.utp.micunatruck.models;
import java.util.Date;

public class Finance {
    private int id;
    private int origin_id;
    private int origin_type_id;
    private double amount;
    private Date start_date;
    private Date end_date;
    private boolean flag_active;
    private Date update_at;
    private Date deleted_at;
    private Date created_at;

    public Finance() {

    }

    public Finance(int id, int origin_id,
                   int origin_type_id, double amount, Date start_date, Date end_date,
                   boolean flag_active, Date update_at, Date deleted_at, Date created_at) {
        this.id = id;
        this.origin_id = origin_id;
        this.origin_type_id = origin_type_id;
        this.amount = amount;
        this.start_date = start_date;
        this.end_date = end_date;
        this.flag_active = flag_active;
        this.update_at = update_at;
        this.deleted_at = deleted_at;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public Finance setId(int id) {
        this.id = id;
        return this;
    }

    public int getOrigin_id() {
        return origin_id;
    }

    public Finance setOrigin_id(int origin_id) {
        this.origin_id = origin_id;
        return this;
    }

    public int getOrigin_type_id() {
        return origin_type_id;
    }

    public Finance setOrigin_type_id(int origin_type_id) {
        this.origin_type_id = origin_type_id;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public Finance setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Finance setStart_date(Date start_date) {
        this.start_date = start_date;
        return this;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public Finance setEnd_date(Date end_date) {
        this.end_date = end_date;
        return this;
    }

    public boolean isFlag_active() {
        return flag_active;
    }

    public Finance setFlag_active(boolean flag_active) {
        this.flag_active = flag_active;
        return this;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public Finance setUpdate_at(Date update_at) {
        this.update_at = update_at;
        return this;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public Finance setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
        return this;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Finance setCreated_at(Date created_at) {
        this.created_at = created_at;
        return this;
    }
}
