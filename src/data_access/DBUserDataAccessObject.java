package data_access;

import entity.User;
import entity.UserFactory;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;

public class DBUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, ChangePasswordUserDataAccessInterface {
    private UserFactory userFactory;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
        // No need to do anything to reinitialize a user list! The data is is the cloud that may be miles away.
    }

    @Override
    public User get(String username) {
        // Make an API call to get the user object.
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();

            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                JSONObject userJSONObject = responseBody.getJSONObject("user");
                String name = userJSONObject.getString("username");
                String password = userJSONObject.getString("password");
                String creationTimeText = userJSONObject.getString("creation_time");
                LocalDateTime ldt = LocalDateTime.parse(creationTimeText);

                return userFactory.create(name, password, ldt);
            }
            else {
//                throw new RuntimeException(responseBody.getString("message"));
                return null;
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByName(String username) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=%s", username))
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();

            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                return true;
            }
            else {
//                throw new RuntimeException(responseBody.getString("message"));
                return false;
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", user.getName());
        requestBody.put("password", user.getPassword());
        requestBody.put("creation_time", user.getCreationTime().toString());
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user"))
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();

            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                return;
            }
            else {
                //throw new RuntimeException(responseBody.getString("message"));
                // TODO: Throw an exception here.
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void changePassword(User user) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", user.getName());
        requestBody.put("password", user.getPassword());
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user"))
                .method("PUT", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();

            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                return;
            }
            else {
                //throw new RuntimeException(responseBody.getString("message"));
                // TODO: Throw an exception here.
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
