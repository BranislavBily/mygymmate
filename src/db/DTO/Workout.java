package db.DTO;

/**
 * Data transfer object for Workout
 */
public class Workout {

    private int id;
    private String exercise;
    private int repetitions;
    private String weight;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
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
                "id=" + id +
                ", exercise='" + exercise + '\'' +
                ", repetitions=" + repetitions +
                ", weight='" + weight + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
