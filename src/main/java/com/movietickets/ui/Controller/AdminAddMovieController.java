package com.movietickets.ui.Controller;

import com.movietickets.model.Admin;
import com.movietickets.repository.AdminRepository;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import com.movietickets.model.Movie;
import com.movietickets.repository.MovieRepository;

public class AdminAddMovieController implements Initializable {
    MovieRepository movieRepo;
    Movie movie;
    @FXML
    private TextField myMovieID;
    @FXML
    private TextField myDuration;
    AdminRepository adminRepo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminRepo = new AdminRepository();
        movieRepo = new MovieRepository();
        movie = new Movie();
        myMovieID.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null
        ));
        myDuration.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null
        ));
        Admin admin = AdminSessionController.getAdmin();
    }

    private String email;
    private int AdminId;
    private int MovieID;

    public void setAdminData(String email, int AdminID) {
        this.email = email;
        this.AdminId = AdminID;

        System.out.println("Email = " + email);
        System.out.println("ID = " + AdminId);
    }

    @FXML
    private Button myback;
    @FXML
    private void goAdmin(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/movietickets/resources/Admin.fxml"));
        Parent root = loader.load();

        AdminController adminController = loader.getController();
        adminController.setAdminData(email,AdminId);
        adminController.setADDMovieID(MovieID);
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
    @FXML
    private Button myok;
    @FXML
    private Label myDetails;
    @FXML
    private TextArea mytextArea;
    @FXML
    private TextField myTitle;
    @FXML
    private TextField myDescription;
    @FXML
    private TextField myCategory;
    @FXML
    private void goComplete(ActionEvent event) throws IOException, InterruptedException {
        Thread.sleep(2000);
        myok.setVisible(false);
        myDetails.setVisible(true);
        mytextArea.setVisible(true);
        MovieID = myMovieID.getText().isEmpty() ? 0 : Integer.parseInt(myMovieID.getText());
        int Duration = myDuration.getText().isEmpty() ? 0 : Integer.parseInt(myDuration.getText());
        mytextArea.appendText("MovieID:"+myMovieID.getText()+"\nTitle:"+myTitle.getText()+"\nmyDescription:"+myDescription.getText()+"\nmyDuration:"+myDuration.getText()+"\nmyCategory:"+myCategory.getText());
        movie.setMovieId(MovieID);
        movie.setTitle(myTitle.getText());
        movie.setDescription(myDescription.getText());
        movie.setCategory(myCategory.getText());
        movie.setDuration(Duration);
        movieRepo.save(movie);
    }
}