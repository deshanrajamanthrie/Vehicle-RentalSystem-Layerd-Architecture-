package lk.ijse.FinalProject.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.FinalProject.TMmodel.VehicleTM;
import lk.ijse.FinalProject.bo.custom.VehicleBO;
import lk.ijse.FinalProject.bo.custom.impl.VehicleBOImpl;
import lk.ijse.FinalProject.dao.custom.VehicleDAO;
import lk.ijse.FinalProject.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.FinalProject.model.VehicleDTO;
import lk.ijse.FinalProject.util.CrudDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AllVehicleFormController {
    public JFXTextField VehicleIdtxt;
    public JFXTextField Vehicletypetxt;
    public JFXTextField VehiclenoPlatetxt;
    public TableColumn colvid;
    public TableColumn colVtype;
    public TableColumn colNumplate;
    public TableColumn colOperate;
    public JFXTextField qtyId;
    public TableView<VehicleTM> tblAllVehicle;
    public JFXTextField txtDayPerCost;
    public static String index;

    VehicleBO vehicleBO = new VehicleBOImpl();

    public void initialize() {
        colvid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colVtype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colNumplate.setCellValueFactory(new PropertyValueFactory<>("numplate"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));
        try {
            loadAllVehicleData();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllVehicleData() throws SQLException, ClassNotFoundException {
        ArrayList<VehicleDTO> allVehicle = vehicleBO.getAllVehicle();
        Button btn = new Button();
        for (VehicleDTO v : allVehicle) {
            tblAllVehicle.getItems().add(new VehicleTM(v.getId(), v.getType(), v.getNumplate(), v.getQty(), v.getPerdaycost(), btn));

        }
    }

    public void txtIdOnAction(KeyEvent keyEvent) {
    }

    public void VehicletxtIdOnAction(ActionEvent actionEvent) {
        search();
    }

    public void SearchOnAction(ActionEvent actionEvent) {
        search();
    }

    private void search() {
        try {
            ResultSet resultSet = vehicleBO.searchVehicle(VehicleIdtxt.getText());

            if (resultSet.next()) {
                VehicleIdtxt.setText(resultSet.getString(1));
                Vehicletypetxt.setText(resultSet.getString(2));
                VehiclenoPlatetxt.setText(resultSet.getString(3));
                qtyId.setText(resultSet.getString(4));

            } else {
                new Alert(Alert.AlertType.ERROR, "Try Again").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
