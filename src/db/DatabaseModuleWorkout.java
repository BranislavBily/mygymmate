package db;

import db.DTO.Workout;
import sample.Modules.ModuleTables;

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
        String query = "select * from " + ModuleTables.WORKOUTS + " where userID = ?";
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
            workout.setWeight(resultSet.getDouble("weight"));
            workout.setDate(resultSet.getString("date"));
            return workout;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }




}
