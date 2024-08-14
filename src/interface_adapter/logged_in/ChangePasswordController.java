package interface_adapter.logged_in;

import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class ChangePasswordController {
    final ChangePasswordInputBoundary userChangePasswordUseCaseInteractor;
    public ChangePasswordController(ChangePasswordInputBoundary userChangePasswordUseCaseInteractor) {
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
    }
    public void execute(String password, String username) {
        ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(username, password);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }
}
