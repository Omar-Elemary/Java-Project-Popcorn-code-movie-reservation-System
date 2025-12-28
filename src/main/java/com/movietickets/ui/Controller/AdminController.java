package com.movietickets.ui.Controller;

import com.movietickets.model.Admin;
import com.movietickets.model.Show;
import com.movietickets.repository.AdminRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController implements Initializable{
    Admin admin;
    AdminRepository adminRepo;
    Show myshow;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminRepo = new AdminRepository();
        myshow = ShowSessionController.getShow();
        setADDMovieID(AddmovieID);
        setRemoveMovieID(RemovemovieID);
    }
    @FXML
    private Button myshowButton;
    @FXML
    private Button myfinish;
    @FXML
    private Label mylable;
    @FXML
    private TextArea mytextArea;
    @FXML
    private Button myAddbutton;
    @FXML
    private Button myRemoveButton;
    @FXML
    private TextField myadminID1;
    @FXML
    private TextField myadminID2;
    @FXML
    private TextField myadminID3;
    @FXML
    private TextField mymovieID1;
    @FXML
    private TextField mymovieID2;
    @FXML
    private TextField myadminEmail;
    @FXML
    private HBox myHbox1;
    @FXML
    private HBox myHbox2;
    private int AddmovieID;
    public void setADDMovieID(int AddmovieID) {
        this.AddmovieID = AddmovieID;
        if(AddmovieID != 0) {
            myHbox1.setLayoutX(13);
            myHbox1.setLayoutY(400);
            myHbox1.setVisible(true);
            myadminID2.setText(String.valueOf(AdminID));
            mymovieID1.setText(String.valueOf(AddmovieID));
        }
    }

    private int RemovemovieID;
    public void setRemoveMovieID(int RemovemovieID) {
        this.RemovemovieID = RemovemovieID;
        if(RemovemovieID != 0) {
            myHbox2.setLayoutX(13);
            myHbox2.setLayoutY(412);
            myHbox2.setVisible(true);
            myadminID3.setText(String.valueOf(AdminID));
            mymovieID2.setText(String.valueOf(RemovemovieID));
        }
    }
    private String email;
    private int AdminID;
    public void setAdminData(String email, int AdminId) {
        this.email = email;
        this.AdminID = AdminId;
        Optional<Admin> adminopt = adminRepo.findById(AdminID);
        admin = adminopt.isPresent()?adminopt.get():null;
        if(admin == null){
            System.out.println("Admin is Null");
        }
        AdminSessionController.setAdmin(admin);
        myadminID1.setText(String.valueOf(AdminID));
        myadminEmail.setText(email);
    }
    @FXML
    private void goShow(ActionEvent event) throws IOException {
        FXMLLoader loader =new FXMLLoader(
                getClass().getResource("/com/movietickets/resources/Show(Admin).fxml")
        );
        Parent root = loader.load();

        AdminShowController controller = loader.getController();
        controller.setAdminData(email, AdminID);

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
    private void goRemove(ActionEvent event) throws IOException {
        FXMLLoader loader =new FXMLLoader(
                getClass().getResource("/com/movietickets/resources/RemoveMovie.fxml")
        );
        Parent root = loader.load();

        AdminRemoveMovieController controller = loader.getController();
        controller.setAdminData(email, AdminID);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
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
    private void addMovie(ActionEvent event) throws IOException{
        FXMLLoader loader =new FXMLLoader(
                getClass().getResource("/com/movietickets/resources/AddMovie.fxml")
        );
        Parent root = loader.load();

        AdminAddMovieController controller = loader.getController();
        controller.setAdminData(email, AdminID);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
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
    private void Complete(ActionEvent event) throws IOException, InterruptedException {
        Thread.sleep(2000);
        if(myshow == null){
            return;
        }
        mylable.setVisible(true);
        mytextArea.setVisible(true);
        //mytextArea.appendText();
        AdminSessionController.clear();
    }
}