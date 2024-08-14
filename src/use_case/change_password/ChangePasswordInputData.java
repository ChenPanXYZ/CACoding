package use_case.change_password;

public class ChangePasswordInputData {

    final private String password;
    final private String username;

    public ChangePasswordInputData(String password, String username) {
        this.password = password;
        this.username = username;
    }

    String getPassword() {
        return password;
    }
    String getUsername() {
        return username;
    }

}
