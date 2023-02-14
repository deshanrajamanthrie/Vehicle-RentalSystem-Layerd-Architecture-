package lk.ijse.FinalProject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.FinalProject.TMmodel.CustomerTM;
import lk.ijse.FinalProject.TMmodel.RentTM;
import lk.ijse.FinalProject.bo.custom.RentAndDetailBO;
import lk.ijse.FinalProject.bo.custom.impl.RentAndDetailBOImpl;
import lk.ijse.FinalProject.dao.custom.RentDAO;
import lk.ijse.FinalProject.dao.custom.RentdetailDAO;
import lk.ijse.FinalProject.dao.custom.impl.RentDAOImpl;
import lk.ijse.FinalProject.dao.custom.impl.RentdetailDAOImpl;
import lk.ijse.FinalProject.model.CustomerDTO;
import lk.ijse.FinalProject.model.RentDTO;
import lk.ijse.FinalProject.model.RentDetailDTO;
import lk.ijse.FinalProject.util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RentAndDetailFormController {

    public TableColumn colrentId;
    public TableColumn StartDate;
    public TableColumn ReserveDate;
    public TableColumn CustomerId;
    public TableColumn ColOperate;
    public TableView<RentTM> tblrent;
    public TableView<RentDetailDTO> tblRentDetail;
    public TableColumn colRenrIdDetail;
    public TableColumn colVehiId;
    public TableColumn colSpendDay;
    public TableColumn colVehicleQty;
    public TableColumn colDayPerCost;

    /*private RentDAO rentDAO = new RentDAOImpl();
    RentdetailDAO rentdetailDAO = new RentdetailDAOImpl();*/
    RentAndDetailBO rentAndDetailBO = new RentAndDetailBOImpl();

    public void initialize() {
        colRenrIdDetail.setCellValueFactory(new PropertyValueFactory<>("RentId"));
        colVehiId.setCellValueFactory(new PropertyValueFactory<>("VehicleId"));
        colSpendDay.setCellValueFactory(new PropertyValueFactory<>("spendDays"));
        colVehicleQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDayPerCost.setCellValueFactory(new PropertyValueFactory<>("perDayCost"));

        try {
            displayerentDetailData();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadRentData();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DisplayData();
    }

    private void displayerentDetailData() throws SQLException, ClassNotFoundException {
        ArrayList<RentDetailDTO> all = rentAndDetailBO.getAllRentDetail();

        //  ArrayList<RentDetailDTO> all = rentdetailDAO.getAll();
        for (RentDetailDTO r : all) {
            tblRentDetail.getItems().add(new RentDetailDTO(r.getRentId(), r.getVehicleId(), r.getSpendDays(), r.getQty(), r.getPerDayCost()));
        }
    }

    private void DisplayData() {
        colrentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        StartDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        ReserveDate.setCellValueFactory(new PropertyValueFactory<>("ReserveDate"));
        CustomerId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        ColOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }


    private void loadRentData() throws SQLException, ClassNotFoundException {



        try {
            ArrayList<RentDTO> allRent = rentAndDetailBO.getallRent();
            for (RentDTO r : allRent) {
                Button btn = new Button("Remove");
                tblrent.getItems().add(new RentTM(r.getId(), r.getStartDate(), r.getReserveDate(), r.getCustomerId(), btn));

                btn.setOnAction(event -> {
                            final int index = tblrent.getSelectionModel().getSelectedIndex();
                            for (int i = 0; i < 100; i++) {
                                if (index == i) {
                                    tblrent.getItems().remove(i);
                                }
                            }
                        }
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
