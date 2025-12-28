package com.movietickets.ui.Controller;

import com.movietickets.model.Movie;
import com.movietickets.model.User;
import com.movietickets.repository.MovieRepository;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URI;
import java.util.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;


public class MoviesController implements Initializable{
    MovieRepository movieRepo;
    Movie movie1,movie2;
    User user;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = UserSessionController.getUser();
        movieRepo = new MovieRepository();
        Optional<Movie> movie1opt = movieRepo.findById(1);
        Movie movie1 = movie1opt.isPresent() ? movie1opt.get() : null;
        if (movie1 != null) {
            myLable2.setText(movie1.toString());
        }

        Optional<Movie> movie2opt = movieRepo.findById(2);
        Movie movie2 = movie2opt.isPresent() ? movie2opt.get() : null;
        if (movie2 != null) {
            myLable1.setText(movie2.toString());
        }
            if(user == null) {
                showRegisterAlert();
            }
    }

    private void showRegisterAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Login Required");
        alert.setContentText("You must Login first before browsing movies.");
        alert.show();
        alert.showAndWait();
        return;
    }

    private String email;
    private int userid;
    public void setUserData(String email, int userId) {
        this.email = email;
        this.userid = userId;
        System.out.println("Email = " + email);
        System.out.println("ID = " + userId);
    }

    @FXML
    private Hyperlink mylocation;
    @FXML
    private Hyperlink mypdf;
    @FXML
    private Hyperlink home;
    @FXML
    private Hyperlink contact;
    @FXML
    private Hyperlink about;
    @FXML private List<ImageView> movies;
    @FXML private List<Label> labels;

    @FXML
    private Label myLable1;
    @FXML
    private Label myLable2;
    @FXML
    private Label myLable3;
    @FXML
    private Label myLable4;
    @FXML
    private Label myLable5;
    @FXML
    private Label myLable6;
    @FXML
    private Label myLable7;
    @FXML
    private Label myLable8;
    @FXML
    private Label myLable9;
    @FXML
    private Label myLable10;
    @FXML
    private Label myLable11;
    @FXML
    private Label myLable12;
    @FXML
    private Label myLable13;
    @FXML
    private Label myLable14;
    @FXML
    private Label myLable15;
    @FXML
    private Label myLable16;
    @FXML
    private Label myLable17;
    @FXML
    private Label myLable18;
    @FXML
    private Label myLable19;
    @FXML
    private ImageView mymovie1;
    @FXML
    private ImageView mymovie2;
    @FXML
    private ImageView mymovie3;
    @FXML
    private ImageView mymovie4;
    @FXML
    private ImageView mymovie5;
    @FXML
    private ImageView mymovie6;
    @FXML
    private ImageView mymovie7;
    @FXML
    private ImageView mymovie8;
    @FXML
    private ImageView mymovie9;
    @FXML
    private ImageView mymovie10;
    @FXML
    private ImageView mymovie11;
    @FXML
    private ImageView mymovie12;
    @FXML
    private ImageView mymovie13;
    @FXML
    private ImageView mymovie14;
    @FXML
    private ImageView mymovie15;
    @FXML
    private ImageView mymovie16;
    @FXML
    private ImageView mymovie17;
    @FXML
    private ImageView mymovie18;
    @FXML
    private ImageView mymovie19;
    @FXML
    private AnchorPane sceneID;
    @FXML
    private HBox HBox1;
    @FXML
    private Button mysee;
    @FXML
    private VBox VBox1;
    @FXML
    private VBox VBox2;
    @FXML
    private Button myhome;
    @FXML
    private Button myrigister;
    @FXML
    private Button mymovie;
    @FXML
    private Button mycinema;
    @FXML
    private  Button mycontact;
    @FXML
    private Button mymenuButton;
    @FXML
    private VBox myVboxButtons;
    @FXML
    private void ControllButtons(ActionEvent event) throws IOException{
        myVboxButtons.setVisible(!myVboxButtons.isVisible());
    }

    @FXML
    private void goHome(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/movietickets/resources/Main.fxml"));


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

        UserSessionController.clear();
    }

    @FXML
    private void goLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("/com/movietickets/resources/Login.fxml")
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
    private void goCinema(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(
                getClass().getResource("/com/movietickets/resources/Cinema.fxml")
        );

        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(scrollPane, 900, 650));
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
    private void myClick(ActionEvent event) throws IOException {
        mysee.setVisible(false);
        sceneID.setPrefHeight(3216);
        HBox1.setLayoutX(-4);
        HBox1.setLayoutY(3120);
        VBox1.setVisible(true);
        VBox2.setVisible(true);
    }

    @FXML
    private void Visuable(ImageView imageview, Label label){
        imageview.setVisible(false);
        label.setVisible(true);
    }

    @FXML
    private void goVisuable (MouseEvent mouseEvent) throws IOException{
        Visuable(mymovie1, myLable1);
        Visuable(mymovie2, myLable2);
    }

    @FXML
    private void Hide(ImageView imageview, Label label){
        imageview.setVisible(true);
        label.setVisible(false);
    }
    @FXML
    private void goHide(MouseEvent mouseEvent)throws IOException{
        Hide(mymovie1, myLable1);
        Hide(mymovie2, myLable2);
    }

    @FXML
    private void showDetails1(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/movietickets/resources/Movie&ShowDetails1.fxml"));
        Parent root = loader.load();

        MovieDetails1Controller moviedetails1controller = loader.getController();
        moviedetails1controller.setUserData(email,userid);
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
                    UserSessionController.clear();
                    ReservationSessionController.clear();
                    ShowSessionController.clear();
                } else {
                    Windowevent.consume();
                }

            });
        });
    }

    @FXML
    private void showDetails2(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/movietickets/resources/Movie&ShowDetails2.fxml"));
        Parent root = loader.load();

        MovieDetails2Controller moviedetails2controller = loader.getController();
        moviedetails2controller.setUserData(email, userid);
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
                    UserSessionController.clear();
                    ShowSessionController.clear();
                    ReservationSessionController.clear();
                } else {
                    Windowevent.consume();
                }

            });
        });
    }

    @FXML
    private void goAbout(ActionEvent event) throws IOException{
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
            // x,y Axises
            double lat = 30.0444;
            double lng = 31.2357;

            String url = "https://www.google.com/maps?q=" + lat + "," + lng;

            Desktop.getDesktop().browse(new URI(url));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}