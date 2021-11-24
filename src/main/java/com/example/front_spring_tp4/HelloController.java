package com.example.front_spring_tp4;

import com.sun.jersey.api.client.GenericType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import model.Hero;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static eu.hansolo.tilesfx.Tile.RED;
import static javafx.scene.paint.Color.BLACK;

public class HelloController {

    private Stage stage;
    private Scene scene;


    @FXML
    private TextField name;
    @FXML
    private TextField faction  ;
    @FXML
    private TextField power;
    @FXML
    private TextField hp;

    @FXML
    private Label nametxt;
    @FXML
    private Label factiontxt  ;
    @FXML
    private Label powertxt;
    @FXML
    private Label hptxt;
    @FXML
    private Button valideButton;

    //modify
    @FXML
    private TextField idm;
    @FXML
    private TextField namem;
    @FXML
    private TextField factionm  ;
    @FXML
    private TextField powerm;
    @FXML
    private TextField hpm;

    @FXML
    private Label idmtxt;
    @FXML
    private Label namemtxt;
    @FXML
    private Label factionmtxt  ;
    @FXML
    private Label powermtxt;
    @FXML
    private Label hpmtxt;
    @FXML
    private Button validemButton;
    // get one
    @FXML
    private TextField idd;
    @FXML
    private Label iddtxt;
    @FXML
    private Label namedtxt;
    @FXML
    private Label factiondtxt  ;
    @FXML
    private Label powerdtxt;
    @FXML
    private Label hpdtxt;
    //delete
    @FXML
    private TextField idHeroDelete ;
    @FXML
    private Label idDeleteTxt;
    //get
    @FXML
    private TextArea textAreaGet;



    @FXML
    protected void validedAdd(ActionEvent event) throws IOException {
        boolean valide = true;
        if(name.getText().isEmpty()){
            nametxt.setTextFill(RED);
            valide = false;
        }
        if(faction.getText().isEmpty()){
            factiontxt.setTextFill(RED);
            valide = false;
        }
        if(power.getText().isEmpty()){
            powertxt.setTextFill(RED);
            valide = false;
        }

        if(hp.getText().isEmpty()){
            hptxt.setTextFill(RED);
            valide = false;
        }else{
            try {
               Double.parseDouble((hp.getText()));
            } catch (NumberFormatException e) {
                hptxt.setTextFill(RED);
                valide = false;
            }
        }
        if(!valide){
            valideButton.setTextFill(RED);
        }
        if(valide){
            nametxt.setTextFill(BLACK);
            factiontxt.setTextFill(BLACK);
            hptxt.setTextFill(BLACK);
            powertxt.setTextFill(BLACK);
            valideButton.setTextFill(BLACK);

            double t = Double.parseDouble(hp.getText());
            Hero hero = new Hero(name.getText(), faction.getText(), power.getText(),(int)(t));
            hero.setId(0);
            HelloApplication.addHero(hero);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();

        }

    }

    @FXML
    protected void deletePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("delete.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void validUpdate(ActionEvent event) throws IOException, URISyntaxException {
        boolean valide = true;
        if(namem.getText().isEmpty()){
            namemtxt.setTextFill(RED);
            valide = false;
        }
        if(factionm.getText().isEmpty()){
            factionmtxt.setTextFill(RED);
            valide = false;
        }
        if(powerm.getText().isEmpty()){
            powermtxt.setTextFill(RED);
            valide = false;
        }

        if(hpm.getText().isEmpty()){
            hpmtxt.setTextFill(RED);
            valide = false;
        }else{
            try {
                Double.parseDouble((hpm.getText()));
            } catch (NumberFormatException e) {
                hpmtxt.setTextFill(RED);
                valide = false;
            }
        }
        if(!valide){
            validemButton.setTextFill(RED);
        }
        if(valide) {
            namemtxt.setTextFill(BLACK);
            factionmtxt.setTextFill(BLACK);
            hpmtxt.setTextFill(BLACK);
            powermtxt.setTextFill(BLACK);
            validemButton.setTextFill(BLACK);
            Hero hero = new Hero(namem.getText(), factionm.getText(), powerm.getText(), (int) Double.parseDouble(hpm.getText()));
            hero.setId(Integer.parseInt(idm.getText()));
            HelloApplication.updateHero(hero);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    protected void supprimer(ActionEvent event) throws IOException, URISyntaxException {
        if(idHeroDelete.getText().isEmpty()){
            idDeleteTxt.setTextFill(RED);
        }else {
            String res = HelloApplication.getElement(Integer.parseInt(idHeroDelete.getText()));
            if (res.isEmpty()) {
                idDeleteTxt.setTextFill(RED);
            } else {
                idDeleteTxt.setTextFill(BLACK);
                HelloApplication.delete(Integer.parseInt(idHeroDelete.getText()));
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    @FXML
    protected void modifierRes() throws ParseException, org.json.simple.parser.ParseException {
        if(idm.getText().isEmpty()){
            idmtxt.setTextFill(RED);
        }else {
            String res = HelloApplication.getElement(Integer.parseInt(idm.getText()));
            if (res.isEmpty()) {
                idmtxt.setTextFill(RED);
            } else {
                idmtxt.setTextFill(BLACK);

                JSONParser parser = new JSONParser();
                Object obj = parser.parse(res);
                JSONObject jsonObject = (JSONObject) obj;
                namem.setText((String) jsonObject.get("name"));
                factionm.setText((String) jsonObject.get("faction"));
                powerm.setText((String) jsonObject.get("power"));
                hpm.setText(String.valueOf(jsonObject.get("hp")));
            }
        }
    }


    @FXML
    protected void getOne() throws ParseException, org.json.simple.parser.ParseException {

        if(idd.getText().isEmpty()){
            iddtxt.setTextFill(RED);
        }else{
            String res = HelloApplication.getElement(Integer.parseInt(idd.getText()));
            if(res.isEmpty()){
                iddtxt.setTextFill(RED);
            }else {
                iddtxt.setTextFill(BLACK);
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(res);
                JSONObject jsonObject = (JSONObject) obj;
                namedtxt.setText((String) jsonObject.get("name"));
                factiondtxt.setText((String) jsonObject.get("faction"));
                powerdtxt.setText((String) jsonObject.get("power"));
                hpdtxt.setText(String.valueOf(jsonObject.get("hp")));
            }
        }
    }
    @FXML
    protected void get(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("get.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void getJson() {
        textAreaGet.setText(HelloApplication.getElementAll());
    }
    @FXML
    protected void retour(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void formulaire(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("formulaire.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void modifier(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("modifier.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void getOneHero(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("getOne.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

}