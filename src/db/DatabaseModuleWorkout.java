package db;

import db.DTO.Workout;
import sample.Resources.ResourceTables;
import sample.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class DatabaseModuleWorkout {

    private Connection connection;
    private int userID;

    public DatabaseModuleWorkout() {
        connection = SqliteConnection.connector();
        if (connection == null) System.exit(1);
        userID = Session.getUserID();
    }

    public ArrayList<Workout> loadWorkouts() {
        ArrayList<Workout> workouts = new ArrayList<>();
        String query = "select * from " + ResourceTables.WORKOUTS + " where userID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Workout workout = loadWorkoutFromResultSet(resultSet);
                if (workout != null) workouts.add(workout);
            }
            return workouts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns Workout from ResultSet
     * @param resultSet from which workout will be loaded
     * @return Workout from ResultSet
     */
    private Workout loadWorkoutFromResultSet(ResultSet resultSet) {
        Workout workout = new Workout();
        try {
            workout.setExercise(resultSet.getString("exercise"));
            workout.setRepetitions(resultSet.getInt("repetitions"));
            String weight = Double.toString(resultSet.getDouble("weight"));
            if (weight.equals("0.0")) {
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

    /**
     * Gets all workouts of logged in user of certain exercise
     * @param exercise What workouts will be loaded
     * @return all workouts of certain exercise
     */
    public ArrayList<Workout> getWorkoutsByExerciseName(String exercise) {
        ArrayList<Workout> workouts = new ArrayList<>();
        String query = "select * from " + ResourceTables.WORKOUTS +
                " where userID = ? and exercise = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, exercise);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Workout workout = loadWorkoutFromResultSet(resultSet);
                if (workout != null) workouts.add(workout);
            }
            return workouts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean insertWorkout(Workout workout) {
        String query = "insert into  " + ResourceTables.WORKOUTS + "(exercise, repetitions, weight , date, userId) values (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, workout.getExercise());
            preparedStatement.setInt(2, workout.getRepetitions());
            preparedStatement.setString(3, workout.getWeight());
            preparedStatement.setString(4, workout.getDate());
            preparedStatement.setInt(5, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Returns {@code LinkedHasSet<String>} of all exercises that the logged in user has ever done
     * @return {@code LinkedHasSet<String>} of all exercises
     */
    public LinkedHashSet<String> getAllUsersExercises() {
        LinkedHashSet<String> exercises = new LinkedHashSet<>();
        String query = "select exercise from " + ResourceTables.WORKOUTS + " where userID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                exercises.add(resultSet.getString("exercise"));
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
