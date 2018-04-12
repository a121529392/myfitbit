/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfitbit;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by HP on 2017/8/10.
 */

public class DataRequester {
    private static final String URL = "http://140.92.88.30/CEP3/sleep/efficiency.do";

    static public void main(String[] argv) throws IOException, UnirestException {
        String str = new String(Files.readAllBytes(Paths.get("sleep.json")));
        System.out.println(new DataRequester().send(str));
    }

    public String send(String body) throws UnirestException {
        RequestBodyEntity entity = Unirest.post(URL)
                .body(body);
        return entity.asJson().getBody().toString();
    }
}
