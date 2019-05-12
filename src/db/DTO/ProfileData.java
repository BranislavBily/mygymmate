package db.DTO;

public class ProfileData {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String gender;
    private String status;
    private String typeOfTraining;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeOfTraining() {
        return typeOfTraining;
    }

    public void setTypeOfTraining(String typeOfTraining) {
        this.typeOfTraining = typeOfTraining;
    }

    public String getRealName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "ProfileData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                ", typeOfTraining='" + typeOfTraining + '\'' +
                '}';
    }
}
