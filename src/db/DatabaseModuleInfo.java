package db;

import db.DTO.ProfileData;
import sample.Resources.ResourceTables;
import sample.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Database module for showing Trainee of Trainer info
 */
public class DatabaseModuleInfo {

    private Connection connection;
    private int userID;

    public DatabaseModuleInfo() {
        connection = SqliteConnection.connector();
        if(connection == null) System.exit(1);
        userID = Session.getUserID();
    }

    public ArrayList<ProfileData> getTraineesOfLoggedTrainer() {
        ArrayList<ProfileData> users = new ArrayList<>();
        String query = "select * from " + ResourceTables.USERS + " where TrainerID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                ProfileData profileData = getProfileDataFromResultSet(resultSet);
                if(profileData != null) users.add(profileData);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ProfileData getMyTrainer() {
        Integer trainerID = getMyTrainerID();
        if(trainerID == null) {
            return null;
        } else {
            return getUserProfileData(trainerID);
        }
    }

    public Integer getMyTrainerID() {
        String query = "select TrainerID from " + ResourceTables.USERS + " where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            int id = resultSet.getInt("TrainerID");
            if(id > 0) return id;
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ProfileData getProfileDataFromResultSet(ResultSet resultSet) {
        try {
            ProfileData profileData = new ProfileData();
            profileData.setId(resultSet.getInt("ID"));
            profileData.setFirstName(resultSet.getString("FirstName"));
            profileData.setLastName(resultSet.getString("LastName"));
            profileData.setGender(resultSet.getString("gender"));
            profileData.setStatus(resultSet.getString("status"));
            profileData.setTypeOfTraining(resultSet.getString("typeOfTraining"));
            profileData.setUsername(resultSet.getString("username"));
            return profileData;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ProfileData getUserProfileData(int id) {
        String query = "select * from " + ResourceTables.USERS + " where ID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return getProfileDataFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
