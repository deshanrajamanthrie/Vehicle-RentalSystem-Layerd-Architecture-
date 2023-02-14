package lk.ijse.FinalProject.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.FinalProject.bo.custom.RentManageBO;
import lk.ijse.FinalProject.bo.custom.impl.RentManageBOImpl;
import lk.ijse.FinalProject.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RentManageFormController {
    public JFXComboBox<String> cmbCustomer;
    public JFXTextField txtCustName;
    public JFXTextField txtCustNic;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCustContact;
    public JFXComboBox<String> cmbVehicle;
    public JFXTextField txtVehicleName;
    public JFXTextField txtVehicleNoplate;
    public JFXTextField txtVehicleqty;
    public JFXTextField txtVehiclePerDayCost;
    public JFXTextField txtVehicleSpendDays;
    public TableView<CartTM> tblcartTM;
    public TableColumn colid;
    public TableColumn coltype;
    public TableColumn colnoplate;
    public TableColumn colcost;
    public TableColumn colspendays;
    public TableColumn coltotalcost;
    public TableColumn colbutton;
    public Label lblOrderTotal;
    public JFXDatePicker CalenderId;
    public Label lbldate;

    /*private VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();
    private CustomerDAO customersDAO = new CustomersDAOImpl();
    private RentDAO rentDAO = new RentDAOImpl();*/
    RentManageBO rentManageBO = new RentManageBOImpl();

    public void initialize() {
        setDate();
        disPlayCartTm();
        setCustomerId();
        setVehicleID();
        //---------------------------------------------------------------------
        cmbCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                setCustomerDetail(newValue));
        //-----------------------------------------------------------------------
        cmbVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            try {
                setVehicleDetail(newValue);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void setDate() {
        lbldate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    private void setVehicleDetail(String getSelectID) throws SQLException, ClassNotFoundException {


        VehicleDTO v = rentManageBO.getListVehicle(getSelectID);
        if (v != null) {
            txtVehicleName.setText(v.getType());                          //Set values  the text Field
            txtVehicleNoplate.setText(v.getNumplate());
            txtVehicleqty.setText(v.getQty());
            txtVehiclePerDayCost.setText(String.valueOf(v.getPerdaycost()));   // double Casting to string
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Vehicle !").show();
        }

    }

    private void setVehicleID() {
        try {
            ObservableList<String> viObList = FXCollections.observableArrayList(rentManageBO.getVehicleIdList());
            cmbVehicle.setItems(viObList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerDetail(String selectedCustomerId) {
        try {
            CustomerDTO c = rentManageBO.getListCustomer(selectedCustomerId);
            if (c != null) {
                txtCustName.setText(c.getName());
                txtCustNic.setText(c.getNic());
                txtCustAddress.setText(c.getAddress());
                txtCustContact.setText(c.getContact());
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerId() {
        try {
            ObservableList<String> cidObList = FXCollections.observableArrayList(
                    rentManageBO.getCustomerIdList());
            cmbCustomer.setItems(cidObList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void AddCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("../view/CustomerManageForm.fxml"))
        ));
        stage.show();
    }

    private void disPlayCartTm() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colnoplate.setCellValueFactory(new PropertyValueFactory<>("numplate"));
        colcost.setCellValueFactory(new PropertyValueFactory<>("perdaycost"));
        colspendays.setCellValueFactory(new PropertyValueFactory<>("spenddays"));
        coltotalcost.setCellValueFactory(new PropertyValueFactory<>("totalcost"));
        colbutton.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    ObservableList<CartTM> tmList = FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) {
        double perDaycost = Double.parseDouble(txtVehiclePerDayCost.getText());     //gananayta avashya data reference vlta ganima
        int spendDay = Integer.parseInt(txtVehicleSpendDays.getText());
        int qty = Integer.parseInt(txtVehicleqty.getText());
        double totalcost = qty * perDaycost * spendDay;                                       // Sulukirima
        Button btn = new Button("Remove ");

        CartTM isExists = isExists(cmbVehicle.getValue());
        if (isExists != null) {
            for (CartTM temp : tmList) {
                if (temp.equals(isExists)) {
                    temp.setSpenddays(temp.getSpenddays() + spendDay);
                    temp.setTotalcost(temp.getTotalcost() + totalcost);
                }
            }
        } else {

            CartTM cartTM = new CartTM(             //Add the CartTm Table
                    cmbVehicle.getValue(),
                    txtVehicleName.getText(),
                    txtVehicleNoplate.getText(),
                    qty,
                    perDaycost,
                    spendDay,
                    totalcost,
                    btn
            );
            btn.setOnAction(event -> {
                for (CartTM tempTm : tmList) {
                    if (tempTm.getId().equals(cartTM.getId())) {
                        tmList.remove(tempTm);
                        calculateTotal();
                    }
                }
            });
            tmList.add(cartTM);
            tblcartTM.setItems(tmList);
        }
        calculateTotal();
        tblcartTM.refresh();
    }

    public void removeOnAction(ActionEvent actionEvent) {
        final int index = tblcartTM.getSelectionModel().getSelectedIndex();
        for (int i = 0; i < 100; i++) {
            if (index == i) {
                tblcartTM.getItems().remove(i);
            }
        }
    }

    private CartTM isExists(String vehicleId) {
        for (CartTM cartTM : tmList) {
            if (cartTM.getId().equals(vehicleId)) {
                return cartTM;
            }
        }
        return null;
    }

    private void calculateTotal() {                                   // Full Total Calculation
        double total = 0;
        for (CartTM cartTM : tmList) {
            total = total + cartTM.getTotalcost();
        }
        lblOrderTotal.setText(String.valueOf(total));
    }

    public void RentOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Transaction();
    }

    public void Transaction() throws ClassNotFoundException {
        String rentId = rentManageBO.getfindNewItemId();
        RentDTO rent = new RentDTO(
              //  "R017",
                rentId,
                lbldate.getText(),
                CalenderId.getValue(),
                cmbCustomer.getValue()
        );
        ArrayList<RentDetailDTO> details = new ArrayList<>();
        for (CartTM cartTM : tmList) {
            details.add(
                    new RentDetailDTO(
                    //        "R017",
                            rentId,
                            cartTM.getId(),
                            cartTM.getSpenddays(),
                            cartTM.getQty(),
                            cartTM.getPerdaycost()
                    ));
        }
        try {
            rentManageBO.getRentManageOrder(details, rent);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void RentOrderDetailOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/RentAndDetailForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }


    




}
