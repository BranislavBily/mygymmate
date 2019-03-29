package sample.Users;

import sample.Modules.TypeOfTraining;

public class UserInfo {

    private String gender;
    private String firstName;
    private String lastName;
    private double weight;
    private double height;
    private double recommendedCalories;
    private TypeOfTraining typeOfTraining;

    public UserInfo(String gender, String firstName, String lastName, double weight, double height, double recommendedCalories, TypeOfTraining typeOfTraining) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.height = height;
        this.recommendedCalories = recommendedCalories;
        this.typeOfTraining = typeOfTraining;
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

    public double getRecommendedCalories() {
        return recommendedCalories;
    }

//    public double getRecommendedProtein() {
//        return recommendedProtein;
//    }
//
//    public double getRecommendedWater() {
//        return recommendedWater;
//    }

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
                ", recommendedCalories=" + recommendedCalories +
                ", typeOfTraining=" + typeOfTraining +
                '}';
    }
}
