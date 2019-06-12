package db;

import db.DTO.ProfileData;
import sample.Resources.ResourceTables;
import sample.Session;

import java.sql.*;
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

    private ProfileData getUserProfileData(int id) {
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

    /**
     * Returns {@code ArrayList<ProfileData>} of all the users based on first and last name
     * @param firstName {@code String} firstName of Trainee
     * @return {@code ArrayList<ProfileData>} if all users based on search,
     *         empty {@code ArrayList<ProfileData>} if no users found or {@code null} if an error occurred
     */
    public ArrayList<ProfileData> getAllTraineesByName(String firstName) {
        ArrayList<ProfileData> users = new ArrayList<>();
        String query = "select * from " + ResourceTables.USERS +
                " where status = ? and firstName like ? and TrainerID is null";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "Trainee");
            preparedStatement.setString(2, firstName);
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

    /**
     * Returns {@code ArrayList<ProfileData>} of all the users based on first and last name
     * @param firstName {@code String} firstName of Trainee
     * @param lastName {@code String} lastName of Trainee
     * @return {@code ArrayList<ProfileData>} if all users based on search,
     *          empty {@code ArrayList<ProfileData>} if no users found or {@code null} if an error occurred
     */
    public ArrayList<ProfileData> getAllTraineesByName(String firstName, String lastName) {
        ArrayList<ProfileData> users = new ArrayList<>();
        String query = "select * from " + ResourceTables.USERS +
                " where status = ? and firstName like ? and lastName like ? and TrainerID is null ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "Trainee");
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
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

    /**
     * Adds {@code int} TrainerID to the user based on id
     * @param id {@code int} id of the user that is adding Trainer
     * @return {@code true} if TrainerID added, {@code false} if not
     */
    public boolean addTraineeToTrainer(int id) {
        String query = "update " + ResourceTables.USERS + " set TrainerID = ? where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes connection between Trainer and Trainee
     * @param id {@code int} of the user that is bye bye with Trainer
     * @return {@code true} if connection deleted, {@code false} if not
     */
    public boolean deleteTraineeFromTrainer(int id) {
        String query = "update " + ResourceTables.USERS + " set TrainerID = ? where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, null);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getTraineeEmailFromDbByUsername(String username){

        String query = "select email from " + ResourceTables.USERS +
                " where username = ? ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
}
