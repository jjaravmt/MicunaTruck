package pe.edu.utp.micunatruck.models;

import java.sql.Connection;

public class BaseEntity {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
