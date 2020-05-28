package com.geekbrains.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private Vector<ClientHandler> clients;
    private AuthService authService;
    private DB db;

    public DB getDb() {
        return db;
    }

    public AuthService getAuthService() {
        return authService;
    }
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    public Server() {
        db = new DB();
        clients = new Vector<>();
        authService = new SimpleAuthService(this);
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен на порту 8189");
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(this, socket);
                System.out.println("Подключился новый клиент");
                executorService.submit(new ClientHandler(this, socket), "Подключился новый клиент");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("Сервер завершил свою работу");
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg, true);
        }
    }

    public void changeNickname(ClientHandler sender, String newNickname) {
        String oldNickname = sender.getNickname();
        if (db.changeNickname(oldNickname, newNickname)) {
            broadcastMsg(oldNickname + " теперь известен как " + newNickname);
            sender.setNickname(newNickname);
            broadcastClientsList();
        } else {
            sender.sendMsg("Ник " + newNickname + " уже занет", true);
        }
    }


    public void privateMsg(ClientHandler sender, String receiverNick, String msg) {
        if (sender.getNickname().equals(receiverNick)) {
            sender.sendMsg("заметка для себя: " + msg, true);
            return;
        }
        for (ClientHandler o : clients) {
            if (o.getNickname().equals(receiverNick)) {
                o.sendMsg("от " + sender.getNickname() + ": " + msg, true);
                sender.sendMsg("для " + receiverNick + ": " + msg, true);
                return;
            }
        }
        sender.sendMsg("Клиент " + receiverNick + " не найден", true);
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
        broadcastClientsList();
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastClientsList();
    }

    public boolean isNickBusy(String nickname) {
        for (ClientHandler o : clients) {
            if (o.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastClientsList() {
        StringBuilder sb = new StringBuilder(15 * clients.size());
        sb.append("/clients ");
        // '/clients '
        for (ClientHandler o : clients) {
            sb.append(o.getNickname()).append(" ");
        }
        // '/clients nick1 nick2 nick3 '
        sb.setLength(sb.length() - 1);
        // '/clients nick1 nick2 nick3'
        String out = sb.toString();
        for (ClientHandler o : clients) {
            o.sendMsg(out);
        }
    }
}
