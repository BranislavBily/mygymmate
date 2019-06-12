package db.DTO;

public class User {

    private String username;

    private String password;

    private String email;

    private String code;

    public User(){}

    public User(String username, String password,String email,String code) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.code=code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "{Username: " + getUsername() + ", Password: " + getPassword() + ", Email: "+getEmail()+", code : "+getCode()+"}";
    }
}
