package db;


import db.DTO.ProfileData;
import db.DTO.RegisteredUser;
import sample.Controllers.SceneControllers.Controller;
import sample.Resources.ResourceTables;
import sample.Session;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Module for handling data from Users table
 */
public class DatabaseModuleUser extends Controller {

    private Connection connection;
    private int userID;

    public DatabaseModuleUser() {
        connection = SqliteConnection.connector();
        if(connection == null) System.exit(1);
        userID = Session.getUserID();
    }

    /**
     * Method that returns user ID from the database if logging credentials
     * where correct, <code>null</code> if they were not
     *
     * @param username of the user trying to log in
     * @param password of the user trying to log in
     * @return <code>null</code> if logging credential where wrong, ID of user if credentials were found
     */
    //TODO Javadoc
    public Integer isUser(String username, String password) {
        String query = "select ID from "+ ResourceTables.USERS+" where Username = ? and Password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt("ID");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserStatus(int userID) {
        ResultSet resultSet;
        String query = "select status from " + ResourceTables.USERS + " where ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getString("status");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserStatus() {
        ResultSet resultSet;
        String query = "select status from " + ResourceTables.USERS + " where ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getString("status");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method used for loading username into Labels
     * @return String with username of user, <code>null</code> if no user is found
     */
    public String getUsername() {
        ResultSet resultSet;
        String query = "select username from " + ResourceTables.USERS + " where ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getString("username");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isUsernameTaken(String username) {
        ResultSet resultSet;
        String query = "select * from "+ ResourceTables.USERS+" where Username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //After email
    public boolean isEmailTaken(String email) {
        ResultSet resultSet;
        String query = "select * from "+ ResourceTables.USERS+" where email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Email validation
    public boolean isEmailCorrect(String email) {
        String regex = "^[\\w!#$%&'*+\\/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+\\/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }



    public boolean insertUserIntoDatabase(RegisteredUser registerUser) {
        String query = "insert into " + ResourceTables.USERS + " (username, password, status, firstName, lastName, height, gender, typeOfTraining, email) values(?, ?, ?, ?, ?, ?, ?, ?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, registerUser.getUsername());
            preparedStatement.setString(2, registerUser.getPassword());
            preparedStatement.setString(3, registerUser.getStatus());
            preparedStatement.setString(4, registerUser.getFirstName());
            preparedStatement.setString(5, registerUser.getLastName());
            preparedStatement.setDouble(6, registerUser.getHeight());
            preparedStatement.setString(7, registerUser.getGender());
            preparedStatement.setString(8, registerUser.getTypeOfTraining());
            preparedStatement.setString(9, registerUser.getEmail());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertUserDietIntoDatabase(int id){

        String query = "insert into " + ResourceTables.DIET + " (userId, calories, protein, water, date) values(?, ?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, 0);
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.setString(5,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ProfileData getUserProfileData() {
        String query = "select * from " + ResourceTables.USERS + " where ID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
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

    public String getUserEmailByUserName(String username){
        String query = "select email from " + ResourceTables.USERS + " where username = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getString(1);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserEmailByUserID(int userID){
        String query = "select email from " + ResourceTables.USERS + " where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getString(1);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateUser(ProfileData profileData) {
        String query = "update " + ResourceTables.USERS + " set username = ?, firstName = ?, lastName = ?, " +
                "gender = ?, typeOfTraining = ? where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, profileData.getUsername());
            preparedStatement.setString(2, profileData.getFirstName());
            preparedStatement.setString(3, profileData.getLastName());
            preparedStatement.setString(4,profileData.getGender());
            preparedStatement.setString(5,profileData.getTypeOfTraining());
            preparedStatement.setInt(6, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean correctUserPassword(String password) {
        String username = getUsername();
        return isUser(username, password) !=  null;

    }

    public boolean updateUserPassword(String newPassword) {
        String query = "update " + ResourceTables.USERS + " set password = ? where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteLoggedInUser() {
        String query = "delete from " + ResourceTables.USERS + " where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

    @Override
    protected void addingNotification(String email) {

    }
}
