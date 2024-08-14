package use_case.change_password;

public interface ChangePasswordOutputBoundary {
    void prepareSuccessView(ChangePasswordOutputData user);

    void prepareFailView(String error);
}