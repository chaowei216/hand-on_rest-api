/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author DELL
 */
public class BookStoreClient {
    
    public static void main(String[] args) throws Exception {
        useGET();
        usePOST();
    }

    public static void useGET() throws Exception {

        URL url = new URL("http://localhost:8080/bookstore/api/books");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed: HTTP error code -> "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String outputMsg;
        System.out.println("Retrieve from API-Server...\n");
        while ((outputMsg = br.readLine()) != null) {
            System.out.println(outputMsg);
        }

        conn.disconnect();
    }

    public static void usePOST() throws Exception {

        URL url = new URL("http://localhost:8080/bookstore/api/books");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        //String newBookJSON = "{\"isbn\":\"2425402340697\",\"title\":\"Đời Ngắn Đừng Ngủ Dài\",\"author\":\"Robin Sharma\",\"edition\":2,\"publishedYear\":2014}";
        String newBookJSON = "{\"isbn\":\"2436636288761\",\"title\":\"Trên Đường Băng\",\"author\":\"Tony Buổi Sáng\",\"edition\":2,\"publishedYear\":2017}";
        //String input = "{\"isbn\":\"2436636288761\",\"title\":\"Trên Đường Băng\",\"author\":\"Tony Buổi Sáng\",\"edition\":2,\"publishedYear\":2017}";

        OutputStream os = conn.getOutputStream();
        os.write(newBookJSON.getBytes());
        os.flush();

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            throw new RuntimeException("Failed: HTTP error code -> "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String outputMsg;
        System.out.println("Retrieve from API-Server...\n");
        while ((outputMsg = br.readLine()) != null) {
            System.out.println(outputMsg);
        }
    }
}
