package com.geekbrains.server;

import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {
    private class UserData {
        private String login;
        private String password;
        private String nickname;

        public UserData(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }
    }

    private Server server;

    public SimpleAuthService(Server server) {
        this.server = server;
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        return server.getDb().authUser(login,password);
    }
}
