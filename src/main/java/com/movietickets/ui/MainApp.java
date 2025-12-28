package com.movietickets.ui;

import java.io.InputStream;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/com/movietickets/resources/Main.fxml"));
        Parent root = loader.load();


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane, 850, 650);


        try (InputStream iconStream = MainApp.class.getResourceAsStream("/com/movietickets/Logo.png")) {
            if (iconStream != null) {
                stage.getIcons().add(new Image(iconStream));
            }
        }

        stage.setTitle("Movie Ticket System");
        stage.setScene(scene);


        stage.setResizable(false);


        stage.show();


        stage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
