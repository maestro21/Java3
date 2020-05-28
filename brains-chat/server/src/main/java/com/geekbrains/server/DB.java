package com.geekbrains.server;

import java.sql.*;

public class DB {
    Connection conn = null;

    DB() {
        try {
            // db parameters
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:chat.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Соединение с базой установлено");
            populateDb();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    public void populateDb() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(*) from users");
            int users = rs.getInt(1);

            if (users == 0) {
                try (PreparedStatement prepInsert = conn.prepareStatement("INSERT INTO users (login, pass, nickname) " +
                        "VALUES (?, ?, ?)")) {
                    for (int i = 1; i <= 10; i++) {
                        prepInsert.setString(1, "login" + i);
                        prepInsert.setString(2, "pass" + i);
                        prepInsert.setString(3, "Name" + i);
                        prepInsert.addBatch();
                    }
                    int[] result = prepInsert.executeBatch();
                    System.out.println("Созданы начальные пользователи");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean changeNickname(String oldNick, String newNick) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT count(*)  FROM users WHERE nickname = ?");
            st.setString(1, newNick);
            st.execute();
            ResultSet rs = st.executeQuery();
            int users = rs.getInt(1);
            if(users > 0) {
                return false;
            }

            st = conn.prepareStatement("UPDATE users SET nickname = ? WHERE nickname = ? ");
            st.setString(1, newNick);
            st.setString(2, oldNick);
            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String authUser(String login, String pass) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT nickname FROM users WHERE  login = ? AND pass = ?");
            st.setString(1, login);
            st.setString(2, pass);
            st.execute();
            ResultSet rs = st.executeQuery();
            return rs.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
