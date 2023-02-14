package lk.ijse.FinalProject.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.util.regex.Pattern;

public class AddNewVehicleFormController {

    public TextField txtId;
    public TextField txttype;
    public TextField txtnoplate;
    public TextField txtqty;
    public TextField txtfee;
    public TableView tblVehicle;
    public TableColumn colId;
    public TableColumn colType;
    public TableColumn colnPlate;
    public TableColumn colQty;
    public TableColumn colHire;
    public TableColumn colOperate;

    private Pattern VehicleIdPattern;
    private Pattern VehicletypePattrn;
    private Pattern VehiclenumplatePattern;
    private Pattern VehicleqtyPattern;
    private Pattern VehilefeePatern;


    VehicleBOImpl vehicleBO = new VehicleBOImpl();


    //CrudDAO<VehicleDTO, String> vehicleDAO = new VehicleDAOImpl(); //Property Injection
    //VehicleDAO vehicleDAO = new VehicleDAOImpl();

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colnPlate.setCellValueFactory(new PropertyValueFactory<>("numplate"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colHire.setCellValueFactory(new PropertyValueFactory<>("perdaycost"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));
        try {
            loadAllData();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        SetVehicleValidation();
    }

    //Regex Patterns
    private void SetVehicleValidation() {
        VehicleIdPattern = Pattern.compile("V[0-9][0-9][0-9]");
        VehicletypePattrn = Pattern.compile("^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$");
        VehiclenumplatePattern = Pattern.compile("^[A-Z]{1,4}-[0-9]{3,5}$");
        VehicleqtyPattern = Pattern.compile("^[1]$");
        VehilefeePatern = Pattern.compile("[1-9][0-9]*(.[0-9]{2})?$");
    }


    private void loadAllData() throws SQLException, ClassNotFoundException {
        VehicleBOImpl vehicleBO = new VehicleBOImpl();

        try {
            ArrayList<VehicleDTO> allVehicle = vehicleBO.getAllVehicle();
            for (VehicleDTO v : allVehicle) {
                Button btn = new Button("Remove Vehicle");
                tblVehicle.getItems().add(new VehicleTM(v.getId(), v.getType(), v.getNumplate(), v.getQty(), v.getPerdaycost(), btn));
                btn.setOnAction(event -> {
                            final int index = tblVehicle.getSelectionModel().getSelectedIndex();
                            for (int i = 0; i < 100; i++) {
                                if (index == i) {
                                    tblVehicle.getItems().remove(i);
                                }
                            }
                        }
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    VehicleDTO vehicleDTO = null;

    public void addOnAction(ActionEvent actionEvent) {
        boolean isVehiIdmatched = VehicleIdPattern.matcher(txtId.getText()).matches();
        boolean isVehiTypemached = VehicletypePattrn.matcher(txttype.getText()).matches();
        boolean isVehiNumplatemached = VehiclenumplatePattern.matcher(txtnoplate.getText()).matches();
        boolean isVehicleqtymatched = VehicleqtyPattern.matcher(txtqty.getText()).matches();
        boolean isVehiclefeematched = VehilefeePatern.matcher(txtfee.getText()).matches();


        if (isVehiIdmatched) {
            if (isVehiTypemached) {
                if (isVehiNumplatemached) {
                    if (isVehicleqtymatched) {
                        if (isVehiclefeematched) {
                            vehicleDTO = new VehicleDTO(txtId.getText(), txttype.getText(), txtnoplate.getText(), txtqty.getText(),
                                    Double.parseDouble(txtfee.getText()
                                    ));
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid Perday Cost !").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid Vehicle Quentity !").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid Numplate !").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Name !").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Id !").show();
        }

        try {
            if (vehicleBO.saveVehicle(vehicleDTO));
            txtId.clear();
            txttype.clear();
            txtnoplate.clear();
            txtqty.clear();
            txtfee.clear();
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successed").show();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        tblVehicle.refresh();
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        VehicleBOImpl vehicleBO = new VehicleBOImpl();
        vehicleBO.updateVehicle(new VehicleDTO(txtId.getText(), txttype.getText(), txtnoplate.getText(), txtqty.getText(),
                Double.parseDouble(txtfee.getText())));

        Boolean isUpdated = true | false;
        if (isUpdated == true) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Succesd !").show();
        } else if (isUpdated == false) {
            new Alert(Alert.AlertType.WARNING, "Have Not Updated Try Again !");
        }
        txtId.clear();
        txttype.clear();
        txtnoplate.clear();
        txtqty.clear();
        txtfee.clear();
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
                txttype.setText(result.getString(2));
                txtnoplate.setText(result.getString(3));
                txtqty.setText(result.getString(4));
                txtfee.setText(result.getString(5));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Vehicle !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        tblVehicle.refresh();
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        search();
        VehicleBOImpl vehicleBO = new VehicleBOImpl();
        try {
            vehicleBO.deleteVehicle(txtId.getText());
            Boolean isDeleted = true;
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Delete Successd!").show();
            }
            txtId.clear();
            txttype.clear();
            txtnoplate.clear();
            txtqty.clear();
            txtfee.clear();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
