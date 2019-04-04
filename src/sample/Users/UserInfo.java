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
    private TypeOfTraining typeOfTraining;

    public UserInfo(String gender, String firstName, String lastName, double weight, double height, double actualCalories, double actualProtein, double actualHydration, TypeOfTraining typeOfTraining) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.height = height;
        this.actualCalories = actualCalories;
        this.actualProtein = actualProtein;
        this.actualHydration = actualHydration;
        this.typeOfTraining = typeOfTraining;
    }

    public UserInfo(String gender, String firstName, String lastName, double weight, double height, double actualCalories, TypeOfTraining typeOfTraining) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.height = height;
        this.actualCalories = actualCalories;
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
