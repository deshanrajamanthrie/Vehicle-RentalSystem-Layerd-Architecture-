package lk.ijse.FinalProject.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.FinalProject.TMmodel.RentTM;
import lk.ijse.FinalProject.TMmodel.VehicleTM;
import lk.ijse.FinalProject.bo.custom.RentAndDetailBO;
import lk.ijse.FinalProject.bo.custom.VehicleBO;
import lk.ijse.FinalProject.bo.custom.impl.RentAndDetailBOImpl;
import lk.ijse.FinalProject.bo.custom.impl.VehicleBOImpl;
import lk.ijse.FinalProject.model.RentDTO;
import lk.ijse.FinalProject.model.VehicleDTO;
import lk.ijse.FinalProject.util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnFormController {

    public TableView tblVehicleForm;
    public TableColumn colId;
    public TableColumn coltype;
    public TableColumn colnoplate;
    public TableColumn colqty;
    public TableColumn colcost;
    public JFXTextField txtqty;
    public JFXTextField txtId;
    public JFXTextField vehicletype;
    public JFXTextField vehiclenoPlate;
    public JFXTextField vehicledaypercost;
    public TableView<RentTM> tblRent;
    public TableColumn colrentid;
    public TableColumn colstartdateid;
    public TableColumn colreservedateId;
    public TableColumn colcustomerId;
    public TableColumn colbutton;

    private VehicleBO vehicleBO = new VehicleBOImpl();
    private RentAndDetailBO rentAndDetailBO = new RentAndDetailBOImpl();

    public void initialize() {
        viewRentdata();
        Viewdata();
        try {
            loadVehicleStore();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            loadRentData();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void viewRentdata() {
        colrentid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colstartdateid.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        colreservedateId.setCellValueFactory(new PropertyValueFactory<>("ReserveDate"));
        colcustomerId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        colbutton.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void Viewdata() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colnoplate.setCellValueFactory(new PropertyValueFactory<>("numplate"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colcost.setCellValueFactory(new PropertyValueFactory<>("perdaycost"));
    }


    private void loadVehicleStore() throws SQLException, ClassNotFoundException {


        ArrayList<VehicleDTO> allVehicle = vehicleBO.getAllVehicle();
        for (VehicleDTO v : allVehicle) {
            Button btn = new Button("Remove Vehicle");
            tblVehicleForm.getItems().add(new VehicleTM(v.getId(), v.getType(), v.getNumplate(), v.getQty(), v.getPerdaycost(), btn));
            btn.setOnAction(event -> {
                        final int index = tblVehicleForm.getSelectionModel().getSelectedIndex();
                        for (int i = 0; i < 100; i++) {
                            if (index == i) {
                                tblVehicleForm.getItems().remove(i);
                            }
                        }
                    }
            );

        }
    }

    private void loadRentData() throws SQLException, ClassNotFoundException {
        try {
            ArrayList<RentDTO> allRent = rentAndDetailBO.getallRent();
            for (RentDTO r : allRent) {
                Button btn = new Button("Remove");
                tblRent.getItems().add(new RentTM(r.getId(), r.getStartDate(), r.getReserveDate(), r.getCustomerId(), btn));

                btn.setOnAction(event -> {
                            final int index = tblRent.getSelectionModel().getSelectedIndex();
                            for (int i = 0; i < 100; i++) {
                                if (index == i) {
                                    tblRent.getItems().remove(i);
                                }
                            }
                        }
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void RepairOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("../view/RepairForm.fxml"))
        ));
        stage.setTitle("Repair Form");
        stage.show();
    }

    public void UpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        VehicleBO vehicleBO = new VehicleBOImpl();
        vehicleBO.updateVehicle(new VehicleDTO(txtId.getText(), vehicletype.getText(), vehiclenoPlate.getText(), txtqty.getText(),
                Double.parseDouble(vehicledaypercost.getText())));

        Boolean isUpdated = true | false;
        if (isUpdated == true) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Succesd !").show();
        } else if (isUpdated == false) {
            new Alert(Alert.AlertType.WARNING, "Have Not Updated Try Again !");
        }
        txtId.clear();
        vehicletype.clear();
        vehiclenoPlate.clear();
        txtqty.clear();
        vehicledaypercost.clear();
    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        search();
    }

    public void searchOnAction(ActionEvent actionEvent) {

        search();
    }

    private void search() {
        try {
            ResultSet result = vehicleBO.searchVehicle(txtId.getText());
            if (result.next()) {
                vehicletype.setText(result.getString(2));
                vehiclenoPlate.setText(result.getString(3));
                txtqty.setText(result.getString(4));
                vehicledaypercost.setText(result.getString(5));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Vehicle !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
