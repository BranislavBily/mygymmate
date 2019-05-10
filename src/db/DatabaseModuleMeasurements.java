package db;

import db.DTO.Measurement;
import sample.Resources.ResourceTables;
import sample.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class DatabaseModuleMeasurements {

    private Connection connection;
    private int userID;

    public DatabaseModuleMeasurements() {
        connection = SqliteConnection.connector();
        if(connection == null) System.exit(1);
        userID = Session.getUserID();
    }

    public Measurement getUserMeasurement() {
        String query = "select * from " + ResourceTables.MEASUREMENTS + " where userID = ? ORDER BY date(date) ASC LIMIT 1";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1,userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getMeasurementFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Measurement getUserMeasurement(int userID) {
        String query = "select * from " + ResourceTables.MEASUREMENTS + " where userID = ? ORDER BY date(date) ASC LIMIT 1";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1,userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getMeasurementFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedHashMap<String, Double> getAllMeasurementsByBodyPart(String bodyPart) {
        LinkedHashMap<String, Double> measurements = new LinkedHashMap<>();
        String query = "select * from " + ResourceTables.MEASUREMENTS + " where userID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1,userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                measurements.put(resultSet.getString("date"), resultSet.getDouble(bodyPart));
            }
            return measurements;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Measurement getMeasurementFromResultSet(ResultSet resultSet) {
        Measurement measurement = new Measurement();
        try {
            measurement.setLeftArm(resultSet.getDouble("Left Arm"));
            measurement.setRightArm(resultSet.getDouble("Right Arm"));
            measurement.setLeftForeArm(resultSet.getDouble("Left ForeArm"));
            measurement.setRightForeArm(resultSet.getDouble("Right ForeArm"));
            measurement.setShoulders(resultSet.getDouble("Shoulders"));
            measurement.setWaist(resultSet.getDouble("Waist"));
            measurement.setChest(resultSet.getDouble("Chest"));
            measurement.setLeftThigh(resultSet.getDouble("Left Thigh"));
            measurement.setRightThigh(resultSet.getDouble("Right Thigh"));
            measurement.setLeftCalf(resultSet.getDouble("Left Calf"));
            measurement.setRightCalf(resultSet.getDouble("Right Calf"));
            return measurement;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteAllUserMeasurement() {
        String query = "delete from " + ResourceTables.MEASUREMENTS + " where userID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
