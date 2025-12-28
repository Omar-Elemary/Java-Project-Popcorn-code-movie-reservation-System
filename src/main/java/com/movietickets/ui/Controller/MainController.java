package com.movietickets.ui.Controller;

import com.movietickets.model.User;
import com.movietickets.repository.MovieRepository;
import com.movietickets.model.Movie;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import javafx.event.ActionEvent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;




public class MainController {
    @FXML
    private Button myLogin;
    @FXML
    private Button mymovie;
    @FXML
    private Button mycinema;
    @FXML
    private Button mycontact;
    @FXML
    private Hyperlink Contact;
    @FXML
    private Hyperlink myAbout;
    @FXML
    private Hyperlink mypdf;
    @FXML
    private Hyperlink mylocation;

    @FXML
    private void goLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("/com/movietickets/resources/Login.fxml")
        );

        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(scrollPane, 790, 490);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goMovies(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/movietickets/resources/Movies.fxml"));


        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(scrollPane, 900, 650);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goCinema(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(
                getClass().getResource("/com/movietickets/resources/Cinema.fxml")
        );

        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(scrollPane, 900, 650);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goContact(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("/com/movietickets/resources/Contact.fxml")
        );

        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(scrollPane, 900, 650);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goAbout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("/com/movietickets/resources/About.fxml")
        );

        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(scrollPane, 900, 650);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void downloadPDF(ActionEvent event) {
        FileChooser saveDialog = new FileChooser();
        saveDialog.setTitle("Save File");
        saveDialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        saveDialog.setInitialFileName("file.pdf");

        File dest = saveDialog.showSaveDialog(null);

        if (dest == null) return;

        try (InputStream in = getClass().getResourceAsStream("/com/movietickets/resources/files/myfile.pdf")) {
            if (in == null) {
                System.out.println("File Not Exist");
                return;
            }

            Files.copy(in, dest.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Download is Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goLocation(ActionEvent event){
        try {
            double lat = 30.0444;
            double lng = 31.2357;

            String url = "https://www.google.com/maps?q=" + lat + "," + lng;

            Desktop.getDesktop().browse(new URI(url));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}