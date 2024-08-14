package use_case.change_password;

public class ChangePasswordOutputData {

    private final String username;
    private String creationTime;

    private boolean useCaseFailed;

    public ChangePasswordOutputData(String username, String creationTime, boolean useCaseFailed) {
        this.username = username;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

}
