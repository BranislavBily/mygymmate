package db;

import db.DTO.UserDietInfo;
import db.DTO.Weight;
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
import java.util.Map;

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
            preparedStatement.setString(3,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String formatDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateD = format.parse(date);
            LocalDate localDate = dateD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return localDate.getDayOfMonth() + "." + (localDate.getMonthValue()) + "." + (localDate.getYear());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Weight getUserWeight() {
        String query = "select weight, date from " + ResourceTables.WEIGHT + " where userID = ?  order By datetime(date) desc LIMIT 1";
        Weight weight=new Weight();
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                weight.setWeight(resultSet.getDouble("weight"));
                weight.setDate(formatDate(resultSet.getString("date")));
                return weight;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Weight getUserWeight(int id) {
        String query = "select weight, date from " + ResourceTables.WEIGHT + " where userID = ?  order By datetime(date) desc LIMIT 1";
        Weight weight=new Weight();
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                weight.setWeight(resultSet.getDouble("weight"));
                weight.setDate(formatDate(resultSet.getString("date")));
                return weight;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Map<String, Double> getAllWeight() {
        Map<String, Double> dataForChart = new LinkedHashMap<>();
        String query = "select date, weight from " + ResourceTables.WEIGHT + " where userID = ? ORDER BY date(date) asc";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                dataForChart.put(getDate(resultSet.getString("date")), resultSet.getDouble("weight"));
            }
            return dataForChart;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    private String getDate(String dateKey) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateKey);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth() + "." + (localDate.getMonthValue());
    }

}
