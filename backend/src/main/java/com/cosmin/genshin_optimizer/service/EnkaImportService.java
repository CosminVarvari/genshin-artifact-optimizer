package com.cosmin.genshin_optimizer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class EnkaImportService {
    
    private static final String ENKA_API = "https://enka.network/u/";

    public JsonNode fetchCharacterData(String uid) throws IOException {
        URL url = new URL("https://enka.network/api/uid/" + uid);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // 👇 Aici e partea importantă
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        int status = conn.getResponseCode();
        if (status != 200) {
            throw new IOException("Failed to fetch data: HTTP code " + status);
        }

        Scanner sc = new Scanner(conn.getInputStream());
        StringBuilder inline = new StringBuilder();
        while(sc.hasNext()) {
            inline.append(sc.nextLine());
        }
        sc.close();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(inline.toString());
    }
}
