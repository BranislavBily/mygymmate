package sample.Users;

public class GymGoer extends User {

    private String gender;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;
    private double weight;
    private double height;
    private double recommendedCalories;
    private double recommendedProtein;
    private double recommendedWater;
    private TypeOfTraining typeOfTraining;

    public GymGoer() {}

    public GymGoer(String username, String password) {
        super(username, password);
    }

    public GymGoer(String username, String password, String gender, double weight, double height, double recommendedCalories, double recommendedProtein, double recommendedWater, TypeOfTraining typeOfTraining) {
        super(username, password);
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.recommendedCalories = recommendedCalories;
        this.recommendedProtein = recommendedProtein;
        this.recommendedWater = recommendedWater;
        this.typeOfTraining = typeOfTraining;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public double getRecommendedCalories() {
        return recommendedCalories;
    }

    public void setRecommendedCalories(double recommendedCalories) {
        this.recommendedCalories = recommendedCalories;
    }

    public double getRecommendedProtein() {
        return recommendedProtein;
    }

    public void setRecommendedProtein(double recommendedProtein) {
        this.recommendedProtein = recommendedProtein;
    }

    public double getRecommendedWater() {
        return recommendedWater;
    }

    public void setRecommendedWater(double recommendedWater) {
        this.recommendedWater = recommendedWater;
    }

    public TypeOfTraining getTypeOfTraining() {
        return typeOfTraining;
    }

    public void setTypeOfTraining(TypeOfTraining typeOfTraining) {
        this.typeOfTraining = typeOfTraining;
    }
}
