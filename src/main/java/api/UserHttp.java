package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.User;

public class UserHttp extends BaseHttpClient {

    @Step("Создание пользователя")
    public Response createUser(User user) {
        Response response = doPostRequest(URL.CREATE_USER, user);
        if (user.getAccessToken() == null) {
            user.setAccessToken(response.getBody().jsonPath().get("accessToken"));
        }
        return response;
    }

    @Step("Авторизация пользователя")
    public Response loginUser(User user) {
        Response response = doPostRequest((URL.LOGIN_USER), user);
        user.setAccessToken(response.getBody().jsonPath().get("accessToken"));
        return response;
    }

    @Step("Удаление пользователя")
    public Response deleteUser(User user) {
        return doDeleteRequest(URL.DELETE_USER, user.getAccessToken());
    }


}