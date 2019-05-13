package db;

import db.DTO.Diet;
import db.DTO.UserDietInfo;
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

public class DatabaseModuleDiet {

    private Connection connection;
    private int userID;

    public DatabaseModuleDiet() {
        connection = SqliteConnection.connector();
        if (connection == null) System.exit(1);
        userID = Session.getUserID();
    }

    public Diet loadDiet() {
        Diet diet = new Diet();
        ResultSet resultSet;
        String query = "select * from " + ResourceTables.DIET + " where userID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            if (loadDietFromResultSet(resultSet) != null) {
                diet = loadDietFromResultSet(resultSet);
            }
            return diet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getDate(String dateKey) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateKey);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth() + "." + (localDate.getMonthValue() + "." + (localDate.getYear()));
    }

    private Diet loadDietFromResultSet(ResultSet resultSet) {
        Diet diet = new Diet();
        try {
            diet.setActualCalories(resultSet.getInt("calories"));
            diet.setActualProtein(resultSet.getInt("protein"));
            diet.setActualWater(resultSet.getInt("water"));
            diet.setDate(getDate(resultSet.getString("date")));
            return diet;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateCalories(int calories) {
        String query = "update " + ResourceTables.DIET + " set calories = ? where userId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, calories);
            preparedStatement.setInt(2, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProtein(int protein) {
        String query = "update " + ResourceTables.DIET + " set protein= ? where userId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, protein);
            preparedStatement.setInt(2, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateWater(int water) {
        String query = "update " + ResourceTables.DIET + " set water = ? where userId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, water);
            preparedStatement.setInt(2, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateDate(String date){
        String query = "update " + ResourceTables.DIET + " set date = ? where userId = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, date);
            preparedStatement.setInt(2, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public UserDietInfo getUserDietInfo() {
        ResultSet resultSet;
        UserDietInfo userDietInfo=new UserDietInfo();
        String query = "select * from " + ResourceTables.USERS+ " where ID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                userDietInfo.setGender(resultSet.getString("gender"));
                userDietInfo.setTypeOfTraining(resultSet.getString("typeOfTraining"));
                userDietInfo.setHeight(resultSet.getInt("height"));
                userDietInfo.setWeight(getUserWeight());
                return userDietInfo;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private double getUserWeight() {
        DatabaseModuleWeight databaseModuleWeight = new DatabaseModuleWeight();
        return  databaseModuleWeight.getUserWeight().getWeight();
    }

    public UserDietInfo getUserDietInfo(int userID) {
        ResultSet resultSet;
        UserDietInfo userDietInfo=new UserDietInfo();
        String query = "select * from " + ResourceTables.USERS+ " where ID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                userDietInfo.setGender(resultSet.getString("gender"));
                userDietInfo.setTypeOfTraining(resultSet.getString("typeOfTraining"));
                userDietInfo.setHeight(resultSet.getInt("height"));
                userDietInfo.setWeight(getUserWeight());
                return userDietInfo;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteUserDiet() {
        String query = "delete from " + ResourceTables.DIET + " where userID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
