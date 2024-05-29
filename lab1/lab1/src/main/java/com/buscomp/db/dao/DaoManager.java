package com.buscomp.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class DaoManager {
    public static final String TRIPS_TABLE = "trips";
    public static final String DRIVERS_TABLE = "drivers";
    public static final String REQUESTS_TABLE = "requests";
    public static final String VEHICLES_TABLE = "vehicles";
    public static final String STATUS_TABLE = "status";

    public static final String USER_TABLE = "users";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "BusComp", "postgres", "admin");
            if (connection != null) {
                System.out.println("Connected somewhere");
            } else {
                System.out.println("Did not connect");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;

    }
}
