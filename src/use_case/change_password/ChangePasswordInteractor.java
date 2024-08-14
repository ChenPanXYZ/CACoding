package use_case.change_password;

import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;

public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    final ChangePasswordUserDataAccessInterface userDataAccessObject;
    final ChangePasswordOutputBoundary userPresenter;
    final UserFactory userFactory;

    public ChangePasswordInteractor(ChangePasswordUserDataAccessInterface changePasswordDataAccessInterface,
                                    ChangePasswordOutputBoundary changePasswordOutputBoundary, UserFactory userFactory) {
        this.userDataAccessObject = changePasswordDataAccessInterface;
        this.userPresenter = changePasswordOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(ChangePasswordInputData changePasswordInputData) {
        LocalDateTime now = LocalDateTime.now();
        User user = userFactory.create(changePasswordInputData.getUsername(), changePasswordInputData.getPassword(), now);
        userDataAccessObject.changePassword(user);

        ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getPassword(), user.getName(), false);
        userPresenter.prepareSuccessView(changePasswordOutputData);
    }
}