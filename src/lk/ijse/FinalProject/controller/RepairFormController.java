package lk.ijse.FinalProject.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.FinalProject.TMmodel.RepairTm;
import lk.ijse.FinalProject.bo.custom.RepairBO;
import lk.ijse.FinalProject.bo.custom.impl.RepairBOImpl;
import lk.ijse.FinalProject.dao.custom.RepairDAO;
import lk.ijse.FinalProject.dao.custom.impl.RepairDAOImpl;
import lk.ijse.FinalProject.model.RepairDTO;
import lk.ijse.FinalProject.util.CrudDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class RepairFormController {

    public JFXTextField repirId;
    public JFXTextField repairPaymenttxt;
    public JFXTextField vehiclesId;
    public TableView<RepairTm> tblrepair;
    public TableColumn colId;
    public TableColumn paymentId;
    public TableColumn vehicleId;
    public TableColumn colOperate;


    //private CrudDAO<RepairDTO,String> repairDAO = new RepairDAOImpl();
    //  RepairDAO repairDAO = new RepairDAOImpl();
    RepairBO repairBO = new RepairBOImpl();


    private Pattern repairIDpattern;
    private Pattern repirCostpattern;
    private Pattern vehicleIDpattern;

    public void initialize() {
        try {
            loadAllrepairdata();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        displayRepairData();
        repairValidation();
    }


    private void repairValidation() {
        repairIDpattern = Pattern.compile("K[0-9][0-9][0-9]");
        repirCostpattern = Pattern.compile("[1-9][0-9]*(.[0-9]{2})?$");
        vehicleIDpattern = Pattern.compile("V[0-9][0-9][0-9]");
    }


    private void displayRepairData() {
        colId.setCellValueFactory(new PropertyValueFactory<>("repairid"));
        paymentId.setCellValueFactory(new PropertyValueFactory<>("repairPrize"));
        vehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    private void loadAllrepairdata() throws SQLException, ClassNotFoundException {
        try {
            ArrayList<RepairDTO> allRepair = repairBO.getAllRepair();
            for (RepairDTO r : allRepair) {
                Button btn = new Button("Delete");
                tblrepair.getItems().add(new RepairTm(r.getRepairid(), r.getRepairPrize(), r.getVehicleId(), btn));

                btn.setOnAction(event -> {
                            final int index = tblrepair.getSelectionModel().getSelectedIndex();
                            for (int i = 0; i < 100; i++) {
                                if (index == i) {
                                    tblrepair.getItems().remove(i);
                                }
                            }
                        }
                );
            }
            tblrepair.refresh();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //  RepairDAO repairdto = null;
    RepairDTO repairDTO = null;

    public void RepairOnAction(ActionEvent actionEvent) {
        boolean isRepairidMatched = repairIDpattern.matcher(repirId.getText()).matches();
        boolean isRepairCostMatched = repirCostpattern.matcher(repairPaymenttxt.getText()).matches();
        boolean isVehicleIdMatched = vehicleIDpattern.matcher(vehiclesId.getText()).matches();

        if (isRepairidMatched) {
            if (isRepairCostMatched) {
                if (isVehicleIdMatched) {
                    repairDTO = new RepairDTO(repirId.getText(), Double.parseDouble(repairPaymenttxt.getText()), vehiclesId.getText());
                } else {
                    new Alert(Alert.AlertType.ERROR, "InValid Vehicle Number !").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Repair Cost! ").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Repair Id!").show();
        }

        try {
           /* repairBO.saveRepair(new RepairDTO(
                    repirId.getText(), Double.parseDouble(repairPaymenttxt.getText()
            ), vehiclesId.getText()));*/
            repairBO.saveRepair(repairDTO);
            repirId.clear();
            repairPaymenttxt.clear();
            vehiclesId.clear();
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successed!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Try Again");
        }
        tblrepair.refresh();
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void txtrepairOnAction(ActionEvent actionEvent) {
    }
}
