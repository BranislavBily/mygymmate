package db;

import db.DTO.Workout;
import sample.Resources.ResourceTables;
import sample.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseModuleWorkout {

    private Connection connection;

    public DatabaseModuleWorkout() {
        connection = SqliteConnection.connector();
        if(connection == null) System.exit(1);
    }

    public ArrayList<Workout> loadWorkouts(int userID) {
        ArrayList<Workout> workouts = new ArrayList<>();
        ResultSet resultSet;
        String query = "select * from " + ResourceTables.WORKOUTS + " where userID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Workout workout = loadWorkoutFromResultSet(resultSet);
                if(workout != null) workouts.add(workout);
            }
            return workouts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Workout loadWorkoutFromResultSet(ResultSet resultSet) {
        Workout workout = new Workout();
        try {
            workout.setExercise(resultSet.getString("exercise"));
            workout.setRepetitions(resultSet.getInt("repetitions"));
            String weight = Double.toString(resultSet.getDouble("weight"));
            if(weight.equals("0.0")) {
                weight = "Bodyweight";
            }
            workout.setWeight(weight);
            workout.setDate(resultSet.getString("date"));
            return workout;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertWorkout(String exersise, int repetitions , int weight, String date) {
        int userID = Session.getUserID();
        String query = "insert into  " + ResourceTables.WORKOUTS + "(exercise, repetitions, weight , date, userId) values (?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, exersise);
            preparedStatement.setInt(2, repetitions);
            preparedStatement.setInt(3, weight);
            preparedStatement.setString(4, date);
            preparedStatement.setInt(5,userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




}
