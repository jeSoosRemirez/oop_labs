package com.buscomp.db.dao;

import com.buscomp.db.entity.Entity;
import com.buscomp.db.entity.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VehicleDao extends EntityDao {

    public VehicleDao(Connection connection) {
        super(connection, DaoManager.VEHICLES_TABLE);
    }

    public void create(Vehicle entity) throws Exception {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table
                + " (model, capacity, max_load, status, id) VALUES (?, ?, ?, ?, ?)");
        statement.setString(1, entity.getModel());
        statement.setInt(2, entity.getCapacity());
        statement.setDouble(3, entity.getMaxLoad());
        statement.setString(4, entity.getStatus());
        statement.setString(5, entity.getId());
        statement.executeUpdate();
    }

    public Entity read(String id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE id = ?");
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Vehicle entity = new Vehicle(UUID.fromString(resultSet.getString("id")));
            entity.setModel(resultSet.getString("model"));
            entity.setCapacity(resultSet.getInt("capacity"));
            entity.setMaxLoad(resultSet.getDouble("max_load"));
            entity.setStatus(resultSet.getString("status"));
            return entity;
        }
        return null;
    }

    public void update(Vehicle entity) throws Exception {
        PreparedStatement statement = connection.prepareStatement("UPDATE " + table
                + " SET model = ?, capacity = ?, max_load = ?, status = ? WHERE id = ?");
        statement.setString(1, entity.getModel());
        statement.setInt(2, entity.getCapacity());
        statement.setDouble(3, entity.getMaxLoad());
        statement.setString(4, entity.getStatus());
        statement.setString(5, entity.getId());
        statement.executeUpdate();
    }

    public void delete(String id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM " + table + " WHERE id = ?");
        statement.setString(1, id);
        statement.executeUpdate();
    }

    public List<Entity> readAll() throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table);
        ResultSet resultSet = statement.executeQuery();
        List<Entity> entities = new ArrayList<>();
        while (resultSet.next()) {
            Vehicle entity = new Vehicle(UUID.fromString(resultSet.getString("id")));
            entity.setModel(resultSet.getString("model"));
            entity.setCapacity(resultSet.getInt("capacity"));
            entity.setMaxLoad(resultSet.getDouble("max_load"));
            entity.setStatus(resultSet.getString("status"));
            entities.add(entity);
        }
        return entities;
    }

    public List<Entity> readByModel(String model) throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE model = ?");
        statement.setString(1, model);
        ResultSet resultSet = statement.executeQuery();
        List<Entity> entities = new ArrayList<>();
        while (resultSet.next()) {
            Vehicle entity = new Vehicle(UUID.fromString(resultSet.getString("id")));
            entity.setModel(resultSet.getString("model"));
            entity.setCapacity(resultSet.getInt("capacity"));
            entity.setMaxLoad(resultSet.getDouble("max_load"));
            entity.setStatus(resultSet.getString("status"));
            entities.add(entity);
        }
        return entities;
    }
}
