package sample.Users;

import sample.Modules.TypeOfTraining;

public class UserInfo {

    private String gender;
    private String firstName;
    private String lastName;
    private double weight;
    private double height;
    private double actualCalories;
    private double actualProtein;
    private double actualHydration;
    private String status;
    private double dailyCalories;
    private TypeOfTraining typeOfTraining;

    public UserInfo(String gender, String firstName, String lastName, double weight, double height, double dailyCalories, double actualCalories, double actualProtein, double actualHydration, TypeOfTraining typeOfTraining, String status) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.height = height;
        this.dailyCalories=dailyCalories;
        this.actualCalories = actualCalories;
        this.actualProtein = actualProtein;
        this.actualHydration = actualHydration;
        this.typeOfTraining = typeOfTraining;
        this.status=status;
    }

    public UserInfo(String gender, String firstName, String lastName, double weight, double height, String status, TypeOfTraining typeOfTraining) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.height = height;
        this.status = status;
        this.typeOfTraining = typeOfTraining;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getActualCalories() {
        return actualCalories;
    }

    public double getActualProtein() {
        return actualProtein;
    }

    public double getActualHydration() {
        return actualHydration;
    }

    public TypeOfTraining getTypeOfTraining() {
        return typeOfTraining;
    }

    public void setActualCalories(double actualCalories) {
        this.actualCalories = actualCalories;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "gender='" + gender + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", actualCalories=" + actualCalories +
                ", actualProtein=" + actualProtein +
                ", actualHydration" + actualHydration +
                ", typeOfTraining=" + typeOfTraining +
                '}';
    }
}
