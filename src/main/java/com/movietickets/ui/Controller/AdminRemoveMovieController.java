package com.movietickets.ui.Controller;

import com.movietickets.model.Admin;
import com.movietickets.model.Movie;
import com.movietickets.repository.AdminRepository;
import com.movietickets.repository.MovieRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminRemoveMovieController implements Initializable {
    AdminRepository adminRepo;
    Admin admin;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminRepo = new AdminRepository();
        myMovieID.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null
        ));
        admin = AdminSessionController.getAdmin();
    }
    @FXML
    private TextField myMovieID;
    private String email;
    private int AdminId;
    private int movieID;
    @FXML
    private void goRemove(ActionEvent event) throws IOException {
        movieID = Integer.parseInt(myMovieID.getText());
        if(myMovieID.getText() == null || myMovieID.getText().trim().isEmpty()){
            return;
        }
        MovieRepository movieRepo = new MovieRepository();
        if (movieRepo.existsById(movieID)) {
            boolean deleted = movieRepo.deleteById(movieID);

            if (deleted) {
                System.out.println("Movie deleted successfully");
            } else {
                System.out.println("Failed to delete movie");
            }
        } else {
            System.out.println("Movie not found");
        }
    }
    @FXML
    private void goBack(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/movietickets/resources/Admin.fxml"));
        Parent root = loader.load();

        AdminController adminController = loader.getController();
        adminController.setAdminData(email,AdminId);
        adminController.setRemoveMovieID(movieID);

        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(scrollPane, 900, 650);

        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(Windowevent -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Logout");
            alert.setHeaderText("Do You Want to Logout from the Programe");

            ButtonType yesBtn = new ButtonType("Yes");
            ButtonType noBtn = new ButtonType("NO");

            alert.getButtonTypes().setAll(yesBtn, noBtn);

            alert.showAndWait().ifPresent(type -> {

                if (type == yesBtn) {
                    AdminSessionController.clear();
                } else {
                    Windowevent.consume();
                }

            });
        });
    }
    public void setMovieID(int movieID){
        this.movieID = movieID;
    }

    public void setAdminData(String email, int adminID) {
        this.email = email;
        this.AdminId = adminID;
        if(admin == null){
            System.out.println("Admin is Null");
            return;
        }
    }
}