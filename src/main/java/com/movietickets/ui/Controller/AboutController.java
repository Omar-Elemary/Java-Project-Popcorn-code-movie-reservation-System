package com.movietickets.ui.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class AboutController {
    @FXML
    private Hyperlink mylocation;
    @FXML
    private Hyperlink mypdf;
    @FXML
    private Hyperlink mycontact;

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
    private Hyperlink myhome;
    @FXML
    private void goHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("/com/movietickets/resources/Main.fxml")
        );

        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(scrollPane, 900, 650);
        Platform.runLater(() -> {
            scrollPane.setVvalue(0);
            scrollPane.requestFocus();
        });

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
