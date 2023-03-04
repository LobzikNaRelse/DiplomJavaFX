package com.kurs.obycheniekursprilogenie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        setScene("articles-kurs.fxml", stage);
    }

    public static void main(String[] args) {launch();}

    public static void setScene(String sceneName, Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(sceneName));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Programma Sanechka");
        stage.setScene(scene);
        stage.show();
    }
}