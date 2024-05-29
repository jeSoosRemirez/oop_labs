package com.buscomp.db.dao;

import com.buscomp.db.entity.Trip;
import com.buscomp.db.entity.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TripDao extends EntityDao {

    public TripDao(Connection connection) {
        super(connection, DaoManager.TRIPS_TABLE);
    }

    public void create(Trip entity) throws Exception {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table
                + " (request_id, driver_id, vehicle_id, departure_time, arrival_time, status, id) VALUES (?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, entity.getRequestId());
        statement.setString(2, entity.getDriverId());
        statement.setString(3, entity.getVehicleId());
        statement.setString(4, entity.getDepartureTime());
        statement.setString(5, entity.getArrivalTime());
        statement.setString(6, entity.getStatus());
        statement.setString(7, entity.getId());
        statement.executeUpdate();
    }

    public Entity read(String id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE id = ?");
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Trip entity = new Trip(UUID.fromString(resultSet.getString("id")));
            entity.setRequestId(resultSet.getString("request_id"));
            entity.setDriverId(resultSet.getString("driver_id"));
            entity.setVehicleId(resultSet.getString("vehicle_id"));
            entity.setDepartureTime(resultSet.getString("departure_time"));
            entity.setArrivalTime(resultSet.getString("arrival_time"));
            entity.setStatus(resultSet.getString("status"));
            return entity;
        }
        return null;
    }

    public void update(Trip entity) throws Exception {
        PreparedStatement statement = connection.prepareStatement("UPDATE " + table
                + " SET request_id = ?, driver_id = ?, vehicle_id = ?, departure_time = ?, arrival_time = ?, status = ? WHERE id = ?");
        statement.setString(1, entity.getRequestId());
        statement.setString(2, entity.getDriverId());
        statement.setString(3, entity.getVehicleId());
        statement.setString(4, entity.getDepartureTime());
        statement.setString(5, entity.getArrivalTime());
        statement.setString(6, entity.getStatus());
        statement.setString(7, entity.getId());
        statement.executeUpdate();
    }

    public void delete(String id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM " + table + " WHERE id = ?");
        statement.setString(1, id);
        statement.executeUpdate();
        // Assuming we need to handle cascade delete or update similar to FlightDao
        // Implementing cascade delete or update if necessary
        // For example: 
        // RequestDao requestDao = new RequestDao(connection);
        // requestDao.cascadeDelete("trip_id", id);
    }

    public List<Entity> readAll() throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table);
        ResultSet resultSet = statement.executeQuery();
        List<Entity> entities = new ArrayList<>();
        while (resultSet.next()) {
            Trip entity = new Trip(UUID.fromString(resultSet.getString("id")));
            entity.setRequestId(resultSet.getString("request_id"));
            entity.setDriverId(resultSet.getString("driver_id"));
            entity.setVehicleId(resultSet.getString("vehicle_id"));
            entity.setDepartureTime(resultSet.getString("departure_time"));
            entity.setArrivalTime(resultSet.getString("arrival_time"));
            entity.setStatus(resultSet.getString("status"));
            entities.add(entity);
        }
        return entities;
    }

    public List<Entity> readByDeparture(String departureTime) throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE departure_time = ?");
        statement.setString(1, departureTime);
        ResultSet resultSet = statement.executeQuery();
        List<Entity> entities = new ArrayList<>();
        while (resultSet.next()) {
            Trip entity = new Trip(UUID.fromString(resultSet.getString("id")));
            entity.setRequestId(resultSet.getString("request_id"));
            entity.setDriverId(resultSet.getString("driver_id"));
            entity.setVehicleId(resultSet.getString("vehicle_id"));
            entity.setDepartureTime(resultSet.getString("departure_time"));
            entity.setArrivalTime(resultSet.getString("arrival_time"));
            entity.setStatus(resultSet.getString("status"));
            entities.add(entity);
        }
        return entities;
    }

    public List<Entity> readByArrival(String arrivalTime) throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE arrival_time = ?");
        statement.setString(1, arrivalTime);
        ResultSet resultSet = statement.executeQuery();
        List<Entity> entities = new ArrayList<>();
        while (resultSet.next()) {
            Trip entity = new Trip(UUID.fromString(resultSet.getString("id")));
            entity.setRequestId(resultSet.getString("request_id"));
            entity.setDriverId(resultSet.getString("driver_id"));
            entity.setVehicleId(resultSet.getString("vehicle_id"));
            entity.setDepartureTime(resultSet.getString("departure_time"));
            entity.setArrivalTime(resultSet.getString("arrival_time"));
            entity.setStatus(resultSet.getString("status"));
            entities.add(entity);
        }
        return entities;
    }
}
