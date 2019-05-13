package db;

import db.DTO.UserDietInfo;
import db.DTO.Weight;
import sample.Resources.ResourceTables;
import sample.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseModuleWeight {

    private Connection connection;
    private int userID;

    public DatabaseModuleWeight() {
        connection = SqliteConnection.connector();
        if (connection == null) System.exit(1);
        userID = Session.getUserID();
    }

    public boolean insertWeight(int id, double weight) {
        String query = "insert into " + ResourceTables.WEIGHT + "(userID, weight, date) values(?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1,id);
            preparedStatement.setDouble(2,weight);
            preparedStatement.setString(3,new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Weight getUserWeight() {
        String query = "select weight, date from " + ResourceTables.WEIGHT + " where userID = ?  order By date(date) asc LIMIT 1";
        Weight weight=new Weight();
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {

                weight.setWeight(resultSet.getDouble("weight"));
                weight.setDate(resultSet.getString("date"));
                return weight;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
