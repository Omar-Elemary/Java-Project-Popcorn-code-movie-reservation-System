package com.movietickets.ui.Controller;

import com.movietickets.model.*;
import com.movietickets.repository.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class ReservationController implements Initializable{
    ReservationRepository reserveRepo;
    UserRepository userRepo;
    ShowRepository showRepo;
    PaymentRepository paymentRepo;
    Payment payment;
    Reservation reservation;
    User user;
    Show show;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = UserSessionController.getUser();
        show = ShowSessionController.getShow();
        reserveRepo = new ReservationRepository();
        userRepo = new UserRepository();
        showRepo = new ShowRepository();
        reservation = ReservationSessionController.getReservation();
        if (reservation == null) {
            System.out.println("Reservation is NULL");
            return;
        }
        myReservationID.setText(String.valueOf(reservation.getReservationId()));
        myuser.setText(String.valueOf(user.getId()));
        myShow.setText(String.valueOf(show.getShowID()));
        myStatus.setText(reservation.getStatus());
        myTotalPrice.setText(String.valueOf(reservation.getTotalPrice()));
        myReservationTime.setText(reservation.getReservationTime());
        myseatIDs.setText(String.valueOf(reservation.getSeats()));
    }

    private int hallid;
    public void sethallID(int hallid) {
        this.hallid = hallid;
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
    private TextField myReservationID;
    @FXML
    private TextField myuser;
    @FXML
    private TextField myShow;
    @FXML
    private TextField myReservationTime;
    @FXML
    private TextField myStatus;
    @FXML
    private TextField myTotalPrice;
    @FXML
    private TextField myseatIDs;
    @FXML
    private Button mypayment;
    @FXML
    private Button mycancel;
    @FXML
    private Button mycomplete;
    @FXML
    private Button myback;
    @FXML
    private Label myLabelDetails;
    @FXML
    private TextArea myTextAreaDetails;
    @FXML
    private void goPayment(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/movietickets/resources/Payment.fxml"));
        Parent root = loader.load();

        PaymentController paymentcontroller = loader.getController();
        paymentcontroller.setUserData(email, userid);
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
                    PaymentSessionController.clear();
                } else {
                    Windowevent.consume();
                }

            });
        });
    }
    //back to specific show info
    @FXML
    private void goBack(ActionEvent event) throws IOException{
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
                    PaymentSessionController.clear();
                } else {
                    Windowevent.consume();
                }

            });
        });
    }
    @FXML
    private void goMovie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/movietickets/resources/Movies.fxml"));
        Parent root = loader.load();

        MoviesController moviescontroller = loader.getController();
        moviescontroller.setUserData(email, userid);

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
                    PaymentSessionController.clear();
                } else {
                    Windowevent.consume();
                }

            });
        });
    }
    //print ticket
    private static int idGenerator = 2;
    @FXML
    private void goComplete(ActionEvent event) throws IOException {
        TicketRepository ticketRepo = new TicketRepository();
        Payment payment = PaymentSessionController.getPayment();
        if (payment == null) {
            showRegisterAlert();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/movietickets/resources/Payment.fxml"));
            Parent root = loader.load();
            PaymentController paymentcontroller = loader.getController();
            paymentcontroller.setUserData(email, userid);
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
                        PaymentSessionController.clear();
                    } else {
                        Windowevent.consume();
                    }

                });
            });
        } else {
            for (int s : reservation.getSeats()) {
                Ticket ticket = new Ticket(idGenerator, 15.00, reservation, show, s, show.getHallID().getHallID());
                ticketRepo.add(ticket);
                ticket.printToPDF();
                idGenerator++;
            }
            mycomplete.setLayoutX(277);
            mycomplete.setLayoutY(774);
            mycancel.setLayoutX(21);
            mycancel.setLayoutY(774);
            myLabelDetails.setVisible(true);
            myTextAreaDetails.setVisible(true);
            myTextAreaDetails.setText("ReservationID: " + myReservationID.getText() + "\nuser: " + myuser.getText() + "\nShow: " + myShow.getText() + "\n" + "ReservationTime: " + myReservationTime.getText() + "\nStatus: " + myStatus.getText() + "\nTotalPrice: " + myTotalPrice.getText());
            reservation.complete();
            ShowSessionController.clear();
            PaymentSessionController.clear();
            ReservationSessionController.clear();
        }
    }

    private void showRegisterAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Access Denied");
        alert.setHeaderText("Payment Required");
        alert.setContentText("You must Pay first before Print Tickets.");

        alert.getDialogPane().setStyle(
                "-fx-font-size: 14px; -fx-font-family: 'Arial';"
        );

        alert.showAndWait();
    }
    @FXML
    private void goCancel(ActionEvent event) throws IOException{
        SeatRepository seatRepo = new SeatRepository();
        myReservationID.setText("");
        myuser.setText("");
        myShow.setText("");
        myStatus.setText("");
        myseatIDs.setText("");
        myReservationTime.setText("");
        myTotalPrice.setText("");
        for(int s:reservation.getSeats()){
            Optional<Seat> seatopt = seatRepo.findById(s);
            if(seatopt.isPresent()){
                Seat seat = seatopt.get();
                seat.release();
            }else{
                return;
            }
        }

        myTextAreaDetails.setText("");
        boolean deletedReserve = reserveRepo.delete(reservation);
        if(deletedReserve){
            System.out.println("Sucessfully Deleted");
        }else{
            System.out.println("Error During Deleted");
        }
        reservation.cancel();
        if(payment == null){
            return;
        }
        payment.refundPayment();
        boolean deletedPayment =  paymentRepo.delete(payment);
        if(deletedPayment){
            System.out.println("Sucessfully Deleted");
        }else{
            System.out.println("Error During Deleted");
        }
        PaymentSessionController.clear();
        ReservationSessionController.clear();
    }
}