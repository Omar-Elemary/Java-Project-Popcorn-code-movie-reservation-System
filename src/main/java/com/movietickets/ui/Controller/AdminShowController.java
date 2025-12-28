package com.movietickets.ui.Controller;

import com.movietickets.model.Admin;
import com.movietickets.model.Hall;
import com.movietickets.model.Movie;
import com.movietickets.model.Show;
import com.movietickets.repository.AdminRepository;
import com.movietickets.repository.HallRepository;
import com.movietickets.repository.MovieRepository;
import com.movietickets.repository.ShowRepository;
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

public class AdminShowController implements Initializable {
    private String email;
    private int AdminId;
    Admin admin;
    AdminRepository adminRepo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminRepo = new AdminRepository();
        admin = AdminSessionController.getAdmin();
        ShowIDOld.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null
        ));
        myMovieID.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null
        ));
        myHallID.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null
        ));
        myShowID.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null
        ));
    }

    public void setAdminData(String email, int adminID) {
        this.email = email;
        this.AdminId = adminID;

        System.out.println("Email = " + email);
        System.out.println("ID = " + adminID);

        if(admin == null){
            System.out.println("Admin is Null");
            return;
        }
    }

    private Show myShow;
    @FXML
    private TextField ShowIDOld;
    @FXML
    private TextField myShowID;
    @FXML
    private TextField myStartTime;
    @FXML
    private TextField myDate;
    @FXML
    private TextField myMovieID;
    @FXML
    private TextField myHallID;
    @FXML
    private Label myDetailsLabel;
    @FXML
    private TextArea myDetailsTextArea;
    @FXML
    private Button myconfirm;
    @FXML
    void goAdmin(ActionEvent event) throws IOException {
        FXMLLoader loader =new FXMLLoader(
                getClass().getResource("/com/movietickets/resources/Admin.fxml")
        );
        Parent root = loader.load();

        AdminController controller = loader.getController();
        controller.setAdminData(email,AdminId);
        ScrollPane scrollPane = new ScrollPane();
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
    private void goConfirm(ActionEvent event) throws IOException {
        ShowRepository showRepo = new ShowRepository();
        MovieRepository movieRepo = new MovieRepository();
        HallRepository hallRepo = new HallRepository();
        Optional<Movie> movieopt = movieRepo.findById(Integer.parseInt(myMovieID.getText()));
        Movie movie = movieopt.isPresent()?movieopt.get():null;
        Optional<Hall> hallopt = hallRepo.findById(Integer.parseInt(myHallID.getText()));
        Hall hall = hallopt.isPresent()?hallopt.get():null;
        Optional<Show> showOldopt = showRepo.findById(Integer.parseInt(ShowIDOld.getText()));
        Show showOld = showOldopt.isPresent() ? showOldopt.get() : null;
        if(showOld == null){
            return;
        }
        myShow = new Show(Integer.parseInt(myShowID.getText()),myStartTime.getText(),myDate.getText(),movie,hall);
        ShowSessionController.setShow(myShow);
        myconfirm.setVisible(false);
        myDetailsLabel.setVisible(true);
        myDetailsTextArea.setVisible(true);
        myDetailsTextArea.setText("ShowID: "+myShowID.getText()+"\nStart Time: "+myStartTime.getText()+"\nDate: "+myDate.getText()
        +"\nMovieID: "+myMovieID.getText()+"\nHallID: "+myHallID.getText());
        showRepo.update(showOld);
    }
    public void setShow(Show myShow){
        this.myShow = myShow;
    }
}