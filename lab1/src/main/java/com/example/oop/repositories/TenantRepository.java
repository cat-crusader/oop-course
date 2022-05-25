package com.example.oop.repositories;

import com.example.oop.Properties;
import com.example.oop.entities.DispatcherEntity;
import com.example.oop.exceptions.HttpException;

import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TenantRepository {
    public static final Properties properties = Properties.getInstance();

    public TenantRepository() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(
                properties.getDatabaseUrl(),
                properties.getDatabaseUsername(),
                properties.getDatabasePassword());
             Statement statement = connection.createStatement()) {
            statement.execute("""
                    create table if not exists tenant
                    (
                        id       bigint generated by default as identity primary key,
                        username varchar(255)
                    );
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DispatcherEntity> findAll() {
        try (Connection connection = DriverManager.getConnection(
                properties.getDatabaseUrl(),
                properties.getDatabaseUsername(),
                properties.getDatabasePassword());
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tenant ORDER BY id;");
            ArrayList<DispatcherEntity> result = new ArrayList<>();
            while (resultSet.next()) {
                DispatcherEntity e = new DispatcherEntity(resultSet.getLong("id"), resultSet.getString("username"));
                result.add(e);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DispatcherEntity findByUsername(String username) {
        try (Connection connection = DriverManager.getConnection(
                properties.getDatabaseUrl(),
                properties.getDatabaseUsername(),
                properties.getDatabasePassword());
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tenant WHERE username = '%s';"
                    .formatted(username));
            if (!resultSet.next()) {
                throw new HttpException(HttpServletResponse.SC_NOT_FOUND);
            }
            return new DispatcherEntity(resultSet.getLong("id"), resultSet.getString("username"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
