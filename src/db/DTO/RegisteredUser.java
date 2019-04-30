package db.DTO;

public class RegisteredUser {

    private String username;
    private String password;
    private String status;
    private String firstName;
    private String lastName;
    private double weight;
    private double height;
    private String gender;
    private String typeOfTraining;

    public RegisteredUser(String username, String password, String status, String firstName, String lastName, double weight, double height, String gender, String typeOfTraining) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.typeOfTraining = typeOfTraining;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public String getStatus() {
        return status;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public double getWeight() {
        return weight;
    }


    public double getHeight() {
        return height;
    }


    public String getGender() {
        return gender;
    }


    public String getTypeOfTraining() {
        return typeOfTraining;
    }

    @Override
    public String toString() {
        return "RegisteredUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", gender='" + gender + '\'' +
                ", typeOfTraining='" + typeOfTraining + '\'' +
                '}';
    }
}
