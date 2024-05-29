package com.buscomp.db.dao;

import com.buscomp.db.entity.Driver;
import com.buscomp.db.entity.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DriverDao extends EntityDao {

    public DriverDao(Connection connection) {
        super(connection, DaoManager.DRIVERS_TABLE);
    }

    public void create(Driver entity) throws Exception {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table
                + " (name, assigned_vehicle_id, id) VALUES (?, ?, ?)");
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getAssignedVehicleId());
        statement.setString(3, entity.getId());
        statement.executeUpdate();
    }

    public Entity read(String id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE id = ?");
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Driver entity = new Driver(UUID.fromString(resultSet.getString("id")));
            entity.setName(resultSet.getString("name"));
            entity.setAssignedVehicleId(resultSet.getString("assigned_vehicle_id"));
            return entity;
        }
        return null;
    }

    public void update(Driver entity) throws Exception {
        PreparedStatement statement = connection.prepareStatement("UPDATE " + table
                + " SET name = ?, assigned_vehicle_id = ? WHERE id = ?");
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getAssignedVehicleId());
        statement.setString(3, entity.getId());
        statement.executeUpdate();
    }

    public void delete(String id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM " + table + " WHERE id = ?");
        statement.setString(1, id);
        statement.executeUpdate();
        TripDao tripDao = new TripDao(connection);
        tripDao.cascadeDelete("driver_id", id);
        VehicleDao vehicleDao = new VehicleDao(connection);
        vehicleDao.updateDriver(id);
    }

    public List<Entity> readAll() throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table);
        ResultSet resultSet = statement.executeQuery();
        List<Entity> entities = new ArrayList<>();
        while (resultSet.next()) {
            Driver entity = new Driver(UUID.fromString(resultSet.getString("id")));
            entity.setName(resultSet.getString("name"));
            entity.setAssignedVehicleId(resultSet.getString("assigned_vehicle_id"));
            entities.add(entity);
        }
        return entities;
    }

    public List<Entity> readByName(String name) throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE name=?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        List<Entity> entities = new ArrayList<>();
        while (resultSet.next()) {
            Driver entity = new Driver(UUID.fromString(resultSet.getString("id")));
            entity.setName(resultSet.getString("name"));
            entity.setAssignedVehicleId(resultSet.getString("assigned_vehicle_id"));
            entities.add(entity);
        }
        return entities;
    }
}
