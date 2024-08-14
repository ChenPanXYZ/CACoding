package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.ChangePasswordController;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoggedInPresenter;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginUserDataAccessInterface;
import view.LoggedInView;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class ChangePasswordUseCaseFactory {

    /** Prevent instantiation. */
    private ChangePasswordUseCaseFactory() {}

    public static LoggedInView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            ChangePasswordUserDataAccessInterface userDataAccessObject) {

        try {
            ChangePasswordController changePasswordController = createChangePasswordUseCase(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
            return new LoggedInView(loggedInViewModel, changePasswordController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static ChangePasswordController createChangePasswordUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            ChangePasswordUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        ChangePasswordOutputBoundary changePasswordOutputBoundary = new LoggedInPresenter(viewManagerModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        ChangePasswordInputBoundary changePasswordInteractor = new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        return new ChangePasswordController(changePasswordInteractor);
    }
}
