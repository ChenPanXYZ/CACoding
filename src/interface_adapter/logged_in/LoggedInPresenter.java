package interface_adapter.login;

import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupState;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggedInPresenter implements ChangePasswordOutputBoundary {

    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public LoggedInPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

//    @Override
//    public void prepareSuccessView(LoginOutputData response) {
//        // On success, switch to the logged in view.
//        LoginState loginState = loginViewModel.getState();
//        loginState.setUsername(response.getUsername());
//        this.loginViewModel.setState(loginState);
//        loginViewModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView(loginViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
//    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData user) {

    }

    @Override
    public void prepareFailView(String error) {
        // TODO
    }
}
