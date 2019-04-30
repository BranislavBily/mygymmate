package db.DTO;

/**
 * Data transfer object for Workout
 */
public class Workout {

    private String exercise;
    private int repetitions;
    private double weight;
    private String date;

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "exercise='" + exercise + '\'' +
                ", repetitions=" + repetitions +
                ", weight=" + weight +
                ", date='" + date + '\'' +
                '}';
    }
}
