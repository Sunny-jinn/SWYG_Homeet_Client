package com.sumin.homeet.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sumin.homeet.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class KakaoService {
    private final UserService userService;
    @Value("${REDIRECT_URI}")
    String redirectUri;

    @Value("${CLIENT_ID}")
    String clientId;

    public String getKakaoAccessToken (String code){
        String access_Token = "";
        String refresh_Token = "";
        try {
            URL url = new URL("https://kauth.kakao.com/oauth/token");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder builder = new StringBuilder();
            builder.append("grant_type=authorization_code");
            builder.append("&client_id="+clientId);
            builder.append("&redirect_uri="+redirectUri);
            builder.append("&code="+code);
            writer.write(builder.toString());
            writer.flush();

            //test version이라 나중에 배포할땐 수정하기
            int responseCode = conn.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String msg = "";

            while ((line = reader.readLine()) != null) {
                msg+=line;
            }
            JsonObject json = new Gson().fromJson(msg, JsonObject.class);
            access_Token = json.get("access_token").getAsString();
            refresh_Token = json.get("refresh_token").getAsString();

            //지워야할 부분

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("access_Token = " + access_Token);
        return access_Token;
    }
    public void getKakaoUser(String token) {
        try{
            URL url = new URL("https://kapi.kakao.com/v2/user/me");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token);
            //나중에 처리할 로직
            int responseCode = conn.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String msg = "";
            while ((line = reader.readLine()) != null) {
                msg+=line;
            }
            JsonObject json = new Gson().fromJson(msg, JsonObject.class);
            int id = json.get("id").getAsInt();
            JsonObject jsonObject = json.get("kakao_account").getAsJsonObject();
            System.out.println("jsonObject = " + jsonObject);
            boolean b = jsonObject.get("has_email").getAsBoolean();
            if (b) {
                String email = jsonObject.get("email").getAsString();
                String name = jsonObject.get("profile").getAsJsonObject().get("nickname").getAsString();
//                System.out.println("name = " + name);
                validationUser(email,name);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void validationUser(String email,String nickname){
        User user = new User();
        user.setEmail(email);
        user.setNickname(nickname);
        String s = userService.validationDupUser(user);

    }
}
