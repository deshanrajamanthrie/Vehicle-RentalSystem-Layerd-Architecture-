package lk.ijse.FinalProject.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.FinalProject.TMmodel.VehicleTM;
import lk.ijse.FinalProject.dao.custom.EmployeeDAO;
import lk.ijse.FinalProject.dao.custom.RentDAO;
import lk.ijse.FinalProject.dao.custom.VehicleDAO;
import lk.ijse.FinalProject.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.FinalProject.dao.custom.impl.RentDAOImpl;
import lk.ijse.FinalProject.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.FinalProject.db.DBConnection;
import lk.ijse.FinalProject.model.VehicleDTO;
import lk.ijse.FinalProject.util.CrudUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DashBoardFormController {
    public AnchorPane contest;
    public Label lblTime;
    public Label lblDate;
    public TableColumn ColId;
    public TableColumn colType;
    public TableColumn colqty;
    public TableView<VehicleDTO> tblAvailable;
    public Label totalRentId;
    public Label totalRentId2;
    public Label totalRentId3;
    public Label totalCustomer;
    // private Map<String, Object> paraMap;

    public void initialize() {
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        try {
            loadAlldata();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        RunningTime();
        try {
            LoadAllData();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

    }

    public void LoadAllData() throws SQLException, ClassNotFoundException {
        RentDAO rentDAO = new RentDAOImpl();
        ResultSet resultSet = rentDAO.countRent();
        totalRentId.setText(resultSet.getString(1));


        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        ResultSet resultSet1 = employeeDAO.employeeCount();
        totalRentId2.setText(resultSet1.getString(1));
    }

    private void loadAlldata() throws SQLException, ClassNotFoundException {
        VehicleDAO vehicleDAO = new VehicleDAOImpl();
        ArrayList<VehicleDTO> all = vehicleDAO.getAll();
        Button btn = new Button();
        for (VehicleDTO v : all) {
            tblAvailable.getItems().add(new VehicleTM(v.getId(), v.getType(), v.getNumplate(), v.getQty(), v.getPerdaycost(), btn));
        }
    }


    private void RunningTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        final Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss a");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String time = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(time);
                });
            }
        });
        thread.start();
    }


    public void HomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) contest.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));

    }

    public void AddVehicleOnActon(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/AddNewVehicleForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);
    }

    public void AllVehicleOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/AllVehicleForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);
    }

    public void RentOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/RentManageForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);

    }

    public void ReturnOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/ReturnForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);
    }


    public void EmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/EmployeeManagementForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);
    }

    public void ReportOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/lk/ijse/FinalProject/view/reports/deshan.jrxml"));
            Connection connection = DBConnection.getInstance().getConnection();
            JasperReport compileReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, connection);
            JasperViewer.viewReport(jasperPrint, false);


        } catch (JRException e) {
            e.printStackTrace();
        }
    }


    public void LogoutOnAction(ActionEvent actionEvent) {

    }

    public void CustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/CustomerManageForm.fxml "));
        contest.getChildren().clear();
        contest.getChildren().add(parent);


    }
}
