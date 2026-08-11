package com.bridgelabz.repository;

import com.bridgelabz.dao.GreetingDAO;
import com.bridgelabz.model.Greeting;
import com.bridgelabz.util.DBUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GreetingRepository implements GreetingDAO {

    // CREATE
    @Override
    public int save(Greeting greeting) {

        String sql = "INSERT INTO greetings (user_name, message) VALUES (?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, greeting.getUserName());
            statement.setString(2, greeting.getMessage());

            return statement.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error while saving greeting", e);
        }
    }

    // GET BY ID
    @Override
    public Greeting findById(int id) {

        String sql = "SELECT * FROM greetings WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return mapGreeting(resultSet);
            }

            return null;

        } catch (SQLException e) {

            throw new RuntimeException("Error while finding greeting", e);
        }
    }

    // GET ALL
    @Override
    public List<Greeting> findAll() {

        String sql = "SELECT * FROM greetings";

        List<Greeting> greetings = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                greetings.add(mapGreeting(resultSet));
            }

        } catch (SQLException e) {

            throw new RuntimeException("Error while fetching greetings", e);
        }

        return greetings;
    }

    // UPDATE
    @Override
    public int update(Greeting greeting) {

        String sql = "UPDATE greetings " + "SET user_name = ?, message = ? " + "WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, greeting.getUserName());

            statement.setString(2, greeting.getMessage());

            statement.setInt(3, greeting.getId());

            return statement.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error while updating greeting", e
            );
        }
    }

    // DELETE
    @Override
    public int delete(int id) {

        String sql = "DELETE FROM greetings WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException("Error while deleting greeting", e);
        }
    }

    // SEARCH BY NAME
    @Override
    public List<Greeting> searchByName(String name) {

        String sql = "SELECT * FROM greetings " +"WHERE user_name ILIKE ?";

        List<Greeting> greetings = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + name + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                greetings.add(mapGreeting(resultSet));
            }

        } catch (SQLException e) {

            throw new RuntimeException("Error while searching greetings", e);
        }

        return greetings;
    }

    // GET BY USER NAME
    @Override
    public List<Greeting> findByUserName(String userName) {

        String sql = "SELECT * FROM greetings " + "WHERE user_name = ?";

        List<Greeting> greetings = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                greetings.add(mapGreeting(resultSet));
            }

        } catch (SQLException e) {

            throw new RuntimeException("Error while finding user greetings", e);
        }

        return greetings;
    }

    // ResultSet -> Greeting
    private Greeting mapGreeting(ResultSet resultSet) throws SQLException {

        Greeting greeting = new Greeting();

        greeting.setId(resultSet.getInt("id"));

        greeting.setUserName(resultSet.getString("user_name"));

        greeting.setMessage(resultSet.getString("message"));

        greeting.setCreatedDate(resultSet.getString("created_date"));

        return greeting;
    }
}