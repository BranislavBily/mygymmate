package db.DTO;
//Class for info about user needed for calculating recommended nutrition.
public class UserDietInfo {


    private String typeOfTraining;
    private double weight;
    private double height;
    private String gender;

    public String getTypeOfTraining() {
        return typeOfTraining;
    }

    public void setTypeOfTraining(String typeOfTraining) {
        this.typeOfTraining = typeOfTraining;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserDietInfo{" +
                "typeOfTraining='" + typeOfTraining + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", gender='" + gender + '\'' +
                '}';
    }
}
