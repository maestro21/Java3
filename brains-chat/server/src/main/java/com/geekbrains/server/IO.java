package com.geekbrains.server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IO {

    String src;

    IO(String login) {
        src = "logs/history_" + login  + ".txt";
        File f = new File("logs");
        if(!f.exists()) {
            f.mkdir();
        }
        f = new File(src);
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void log(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(src,true))) {
            writer.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readLastMessages() {
        try (BufferedReader reader = new BufferedReader(new FileReader(src))) {
            String str;
            ArrayList<String> result = new ArrayList<>();
            while ((str = reader.readLine()) != null) {
                result.add(str);
            }
            int n = 10;
            if(result.size() > n) {
                List<String> subList =  result.subList(Math.max(result.size() - n, 0), result.size());
                result = new ArrayList<>(subList);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
