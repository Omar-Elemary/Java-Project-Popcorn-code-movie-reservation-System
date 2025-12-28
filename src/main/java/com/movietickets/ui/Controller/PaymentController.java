package com.movietickets.ui.Controller;

import com.movietickets.model.Payment;
import com.movietickets.model.Reservation;
import com.movietickets.model.Show;
import com.movietickets.model.User;
import com.movietickets.repository.PaymentRepository;
import com.sun.scenario.effect.Effect;
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
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class PaymentController implements Initializable {
    PaymentRepository paymentRepo;
    Payment payment;
    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    private static final AtomicInteger idTransactiionGenerator = new AtomicInteger(123);
    Reservation reservation;
    User user;
    Show show;
    @FXML
    private TextField mypaymentID;
    @FXML
    private TextField myReservationID;
    @FXML
    private TextField myAmount;
    @FXML
    private ComboBox<String> mymethodPayment;
    @FXML
    private TextField myTransactionID;
    @FXML
    private TextField myTime;
    @FXML
    private Label myDetailsLable;
    @FXML
    private TextArea myTextAreaDetails;
    @FXML
    private Button myRefund;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reservation = ReservationSessionController.getReservation();
        user = UserSessionController.getUser();
        show = ShowSessionController.getShow();
        mymethodPayment.getItems().addAll("InstaPay","masterCard","Vodafone Cash","PayPal");
    }
    private String selectedPayment;
    @FXML
    private void confirmReservation(ActionEvent event) throws IOException{
        selectedPayment = mymethodPayment.getValue();
        if (selectedPayment == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("You must choose method to Pay.");
            alert.show();
            alert.showAndWait();
            return;
        }
        myLabelMethod.setText(selectedPayment);
    }
    @FXML
    private Label myLabelMethod;
    private String email;
    private int userid;
    public void setUserData(String email, int userId) {
        this.email = email;
        this.userid = userId;
        System.out.println("Email = " + email);
        System.out.println("ID = " + userId);
    }

    @FXML
    private Button myback;
    @FXML
    private void goBack(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/movietickets/resources/Reservation.fxml"));
        Parent root = loader.load();
        ReservationController reservationcontroller = loader.getController();
        reservationcontroller.setUserData(email,userid);

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
    private void goProcess(ActionEvent event) throws IOException{
        paymentRepo = new PaymentRepository();
        payment = new Payment(idGenerator.getAndIncrement(),reservation.getReservationId(),reservation.getTotalPrice(),selectedPayment,Integer.parseInt(String.valueOf(idTransactiionGenerator)),reservation.getReservationTime());
        PaymentSessionController.setPayment(payment);
        paymentRepo.save(payment);
        payment.processPayment();
        myDetailsLable.setVisible(true);
        mypaymentID.setText(String.valueOf(payment.getPaymentID()));
        myReservationID.setText(String.valueOf(reservation.getReservationId()));
        myAmount.setText(String.valueOf(reservation.getTotalPrice()));
        myTransactionID.setText(String.valueOf(idTransactiionGenerator));
        myTime.setText(reservation.getReservationTime());
        myLabelTime.setText(myTime.getText());
        myTextAreaDetails.setVisible(true);
        myTextAreaDetails.setText("paymentID: "+mypaymentID.getText()+"\nReservationID: " + myReservationID.getText()+"\nAmount: "+myAmount.getText()+"\nTransactionID: "+myTransactionID.getText()+"\nTime: "+myTime.getText());
        myRefund.setVisible(true);
    }
    @FXML
    private void goRefund(ActionEvent event) throws IOException{
        myTextAreaDetails.setText("");
        payment.refundPayment();
        mypaymentID.setText("");
        myReservationID.setText("");
        myAmount.setText("");
        myTransactionID.setText("");
        myTime.setText("");
        payment.refundPayment();
        boolean deletePayment =  paymentRepo.delete(payment);
        if(deletePayment){
            System.out.println("payment deleted sucessfully");
        }else{
            System.out.println("Error:payment not deleted");
        }
    }
    @FXML
    private Label myLabelTime;
    @FXML
    private void getValue(ActionEvent event) throws IOException{
        myLabelTime.setText(myTime.getText());
    }
}