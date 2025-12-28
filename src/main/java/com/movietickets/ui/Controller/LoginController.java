package com.movietickets.ui.Controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.util.Optional;
import java.util.ResourceBundle;

import com.movietickets.model.Admin;
import com.movietickets.model.User;
import com.movietickets.repository.AdminRepository;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    UserRepository userRepo;
    AdminRepository adminRepo;
    User user;
    Admin admin;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userRepo = new UserRepository();
        adminRepo = new AdminRepository();
        myID.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null
        ));
    }
    @FXML
    private Button mymenuButton;
    @FXML
    private VBox myVboxButtons;
    @FXML
    private void ControllButtons(ActionEvent event) throws IOException{
        myVboxButtons.setVisible(!myVboxButtons.isVisible());
    }
    @FXML
    private Hyperlink mypdf;
    @FXML
    private Hyperlink mylocation;
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
    private Hyperlink home;
    @FXML
    private Hyperlink contact;
    @FXML
    private Hyperlink About;
    @FXML
    private Button mysubmit;
    @FXML
    private TextField myEmail;
    @FXML
    private TextField myID;
    @FXML
    private javafx.scene.control.Label errorLogin;

    @FXML
    private void goHome(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/movietickets/resources/Main.fxml"));

        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(scrollPane, 900, 650);

        stage.setScene(scene);
        stage.show();

        System.out.println("Returned to Home with ScrollBar");
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

            double lat = 30.0444;
            double lng = 31.2357;

            String url = "https://www.google.com/maps?q=" + lat + "," + lng;

            Desktop.getDesktop().browse(new URI(url));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goSubmit (ActionEvent event) throws IOException{
        try {
            // Reset error message
            errorLogin.setVisible(false);
            errorLogin.setText("");

            // Validate email
            String emailText = myEmail.getText().trim();
            boolean isEmailValid = !emailText.isEmpty() && emailText.contains("@");
            String inputEmail = isEmailValid ? emailText : null;

            // Validate ID
            String idText = myID.getText().trim();
            boolean isIDValid = !idText.isEmpty() && idText.length() <= 4;
            int inputID = isIDValid ? Integer.parseInt(idText) : 0;

            // Check if credentials are valid
            Optional<User> userEmail = userRepo.findByEmail(inputEmail);
            Optional<User> userID = userRepo.getById(inputID);
            Optional<Admin> adminEmail = adminRepo.findByEmail(inputEmail);
            Optional<Admin> adminID = adminRepo.findById(inputID);

            boolean isUseValidUser = userEmail.isPresent() && userID.isPresent() && userEmail.get().getId() == userID.get().getId();
            boolean isAdminValidUser = adminEmail.isPresent() && adminID.isPresent() && adminEmail.get().getId() == adminID.get().getId();

            // Check if credentials match
            if (!isAdminValidUser && !isUseValidUser) {
                throw new Exception("Invalid credentials");
            }

            // Load Admin page
            if (isAdminValidUser) {
                if (inputID >= 1000 && inputID < 2000) {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/com/movietickets/resources/Admin.fxml")
                    );
                    Parent root = loader.load();

                    AdminController controller = loader.getController();
                    controller.setAdminData(inputEmail, inputID);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            }
            // Load User Profile page
            else if (isUseValidUser) {
                if (inputID < 1000 || inputID >= 2000) {
                    myrigister.setVisible(false);
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/com/movietickets/resources/Profile(User).fxml")
                    );
                    Parent root = loader.load();

                    ProfileUserController controller = loader.getController();
                    controller.setUserData(inputEmail, inputID);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                }

            }
        } catch (NumberFormatException ex) {
            errorLogin.setVisible(true);
            errorLogin.setText("ID must be a number");
        } catch (Exception e) {
            errorLogin.setVisible(true);

            if (e.getMessage() != null && e.getMessage().contains("Invalid credentials")) {
                errorLogin.setText("That login information doesn't match our records.");
            } else if (myEmail.getText().trim().isEmpty()) {
                errorLogin.setText("Email is required");
            } else if (!myEmail.getText().trim().contains("@")) {
                errorLogin.setText("Invalid Email - must contain @");
            } else if (myID.getText().trim().isEmpty()) {
                errorLogin.setText("ID is required");
            } else if (myID.getText().trim().length() > 4) {
                errorLogin.setText("ID must be less than or equal to 4 characters");
            } else {
                errorLogin.setText("An error occurred during login. Please try again.");
            }
        }
    }
}