package com.movietickets.ui.Controller;

import com.movietickets.model.User;
import com.movietickets.repository.ReservationRepository;
import com.movietickets.repository.UserRepository;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.imageio.IIOParam;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProfileUserController implements Initializable {
    ReservationRepository reserveRepo;
    UserRepository userRepo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reserveRepo = new ReservationRepository();
        userRepo = new UserRepository();
    }
    @FXML
    private Label mybookTicket;
    @FXML
    private Label labelUserID;
    @FXML
    private Label LabelBooked;
    @FXML
    private Button myhome;
    @FXML
    private TextField myUserID;
    @FXML
    private TextField myUserEmail;
    @FXML
    private TextField myUserID2;
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

    private String email;
    private int userid;
    public void setUserData(String email, int userId) {
        myUserID.setText(String.valueOf(userId));
        myUserEmail.setText(email);
        myUserID2.setText(email);
        this.email = email;
        this.userid = userId;
        if(!reserveRepo.findByUserId(userid).isEmpty()){
            labelUserID.setVisible(true);
            LabelBooked.setVisible(true);
            myUserID2.setVisible(true);
            myUserID2.setText(String.valueOf((userid)));
            mybookTicket.setVisible(true);
        }
        Optional<User> useropt = userRepo.findById(userId);
        User user = useropt.isPresent() ? useropt.get() : null;
        if(user == null){
            System.out.println("user is NULL");
        }
        UserSessionController.setUser(user);
        System.out.println("Email = " + email);
        System.out.println("ID = " + userId);
    }
    @FXML
    private Button mymovies;
    @FXML
    private void goMovies(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/movietickets/resources/Movies.fxml")
        );
        Parent root = loader.load();

        MoviesController moviesController = loader.getController();
        moviesController.setUserData(email, userid);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}