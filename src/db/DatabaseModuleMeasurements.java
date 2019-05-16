package db;

import db.DTO.Measurement;
import sample.Resources.ResourceTables;
import sample.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;

public class DatabaseModuleMeasurements {

    private Connection connection;
    private int userID;

    public DatabaseModuleMeasurements() {
        connection = SqliteConnection.connector();
        if (connection == null) System.exit(1);
        userID = Session.getUserID();
    }

    public Measurement getUserMeasurement() {
        String query = "select * from " + ResourceTables.MEASUREMENTS + " where userID = ? ORDER BY date(date) desc LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getMeasurementFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Measurement getUserMeasurement(int userID) {
        String query = "select * from " + ResourceTables.MEASUREMENTS + " where userID = ? ORDER BY date(date) desc LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getMeasurementFromResultSet(resultSet);
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertMeasures(Measurement measurement){
        String query = "insert into  " + ResourceTables.MEASUREMENTS + " (UserID, LeftArm, RightArm , LeftForearm, RightForearm, Shoulders, Waist, Chest, LeftThigh, RightThigh, LeftCalf, RightCalf, Date) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setDouble(2, measurement.getLeftArm());
            preparedStatement.setDouble(3, measurement.getRightArm());
            preparedStatement.setDouble(4, measurement.getLeftForeArm());
            preparedStatement.setDouble(5, measurement.getRightForeArm());
            preparedStatement.setDouble(6, measurement.getShoulders());
            preparedStatement.setDouble(7, measurement.getWaist());
            preparedStatement.setDouble(8, measurement.getChest());
            preparedStatement.setDouble(9, measurement.getLeftThigh());
            preparedStatement.setDouble(10, measurement.getRightThigh());
            preparedStatement.setDouble(11, measurement.getLeftCalf());
            preparedStatement.setDouble(12, measurement.getRightCalf());
            preparedStatement.setString(13, measurement.getDate());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public LinkedHashMap<String, Double> getAllMeasurementsByBodyPart(String bodyPart) {
        LinkedHashMap<String, Double> measurements = new LinkedHashMap<>();
        String query = "select * from " + ResourceTables.MEASUREMENTS + " where userID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
            measurement.setLeftArm(resultSet.getDouble("LeftArm"));
            measurement.setRightArm(resultSet.getDouble("RightArm"));
            measurement.setLeftForeArm(resultSet.getDouble("LeftForeArm"));
            measurement.setRightForeArm(resultSet.getDouble("RightForeArm"));
            measurement.setShoulders(resultSet.getDouble("Shoulders"));
            measurement.setWaist(resultSet.getDouble("Waist"));
            measurement.setChest(resultSet.getDouble("Chest"));
            measurement.setLeftThigh(resultSet.getDouble("LeftThigh"));
            measurement.setRightThigh(resultSet.getDouble("RightThigh"));
            measurement.setLeftCalf(resultSet.getDouble("LeftCalf"));
            measurement.setRightCalf(resultSet.getDouble("RightCalf"));
            measurement.setDate(formatDate(resultSet.getString("date")));
            measurement.setFullDate(formatFullDate(resultSet.getString("date")));
            return measurement;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean measurementAlreadyAddedToday(Measurement measurement) {
        String query = "select * from " +ResourceTables.MEASUREMENTS + " where userID = ? and date = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, measurement.getDate());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMeasurement(Measurement measurement) {
        String query = "update " +ResourceTables.MEASUREMENTS + " set LeftArm = ?, RightArm = ?, LeftForearm = ?, RightForearm = ?, " +
                "Chest = ?, Shoulders = ?, Waist = ?, LeftThigh = ?, RightThigh = ?, LeftCalf = ?, RightCalf = ? where userID = ? and date = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, measurement.getLeftArm());
            preparedStatement.setDouble(2, measurement.getRightArm());
            preparedStatement.setDouble(3, measurement.getLeftForeArm());
            preparedStatement.setDouble(4, measurement.getRightForeArm());
            preparedStatement.setDouble(5, measurement.getChest());
            preparedStatement.setDouble(6, measurement.getShoulders());
            preparedStatement.setDouble(7, measurement.getWaist());
            preparedStatement.setDouble(8, measurement.getLeftThigh());
            preparedStatement.setDouble(9, measurement.getRightThigh());
            preparedStatement.setDouble(10, measurement.getLeftCalf());
            preparedStatement.setDouble(11, measurement.getRightCalf());
            preparedStatement.setInt(12, userID);
            preparedStatement.setString(13, measurement.getDate());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllUserMeasurements() {
        String query = "delete from " + ResourceTables.MEASUREMENTS + " where userID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Turns data format required for SQLITE database into european time format without year
     * @param dateKey {@code String} date from database in american format
     * @return {@code String} date of workout in european format
     * @throws ParseException
     */
    private String formatDate(String dateKey) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateKey);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth() + "." + (localDate.getMonthValue());
    }

    /**
     * Turns data format required for SQLITE database into european time format
     * @param dateKey {@code String} date from database in american format
     * @return {@code String} date of workout in european format
     * @throws ParseException
     */
    private String formatFullDate(String dateKey) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateKey);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth() + "." + (localDate.getMonthValue() + "." + (localDate.getYear()));
    }
}
