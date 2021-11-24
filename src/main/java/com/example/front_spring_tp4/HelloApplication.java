package com.example.front_spring_tp4;

import javafx.application.Application;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.*;

import com.sun.jersey.api.client.*;
import model.Hero;

import javax.ws.rs.core.*;


public class HelloApplication extends Application {
    private static WebResource service ;
    private static ClientResponse response;
    private void initializeService() throws URISyntaxException {
        URI uri = new URI("http://localhost:8080");
        UriBuilder uriBuilder = UriBuilder.fromUri(uri);
        Client client = Client.create(new DefaultClientConfig());
        service = client.resource(uriBuilder.build());
    }
    static String getElementAll() {
        String res = service.path("/heroes") .accept(MediaType.APPLICATION_JSON_TYPE) .get(new GenericType<String>(){});
        System.out.println(res);
        return res;
    }

    static String getElement(int id) {
        String res = service.path("/heroes/"+ id) .accept(MediaType.APPLICATION_JSON_TYPE) .get(new GenericType<String>(){});
        System.out.println(res);
        return res;
    }

    public static void addHero(Hero hero){
        String json = Hero.hero_to_JSON(hero);
        response = service.path("/heroes").accept("application/json")
                .type("application/json").post(ClientResponse.class, json);
    }

    public static void updateHero(Hero hero) {
        String json = Hero.hero_to_JSON(hero);
        response = service.path("/heroes/"+ hero.getId() ).accept("application/json")
                .type("application/json").put(ClientResponse.class, json);

    }
    public static void delete(int text){
        service.path("/heroes/" +text).delete();
    }

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        this.initializeService();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("League of Héros!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}