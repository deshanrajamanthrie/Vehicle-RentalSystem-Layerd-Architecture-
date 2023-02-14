package lk.ijse.FinalProject.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.FinalProject.TMmodel.EmployeeTM;
import lk.ijse.FinalProject.bo.custom.EmployeeBO;
import lk.ijse.FinalProject.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.FinalProject.model.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class EmployeeManagementFormController {


    public TextField txtId;
    public TextField txtSalary;
    public TextField txtContact;
    public TextField txtAddress;
    public TextField txtName;
    public TableColumn colEid;
    public TableColumn colEname;
    public TableColumn colEaddress;
    public TableColumn colEContact;
    public TableColumn colEsalary;
    public TableColumn colPosition;
    public TextField txtPosition;
    public TableView<EmployeeTM> tblEmployee;
    public TableColumn operateColId;

    private EmployeeBO employeeBO = new EmployeeBOImpl();
    private Pattern EmplIdpattern;
    private Pattern EmplNumpattern;
    private Pattern Empladdress;
    private Pattern EmplContact;
    private Pattern EmplSalary;
    private Pattern EmplPosition;

    private void setEmployeeValidation() {
        EmplIdpattern = Pattern.compile("U[0-9][0-9][0-9]");
        EmplNumpattern = Pattern.compile("^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$");
        Empladdress = Pattern.compile("^[a-zA-Z-\\s]+$");
        EmplContact = Pattern.compile("^07(7|6|8|1|2|5|0|4)[0-9]{7}$");
        EmplSalary = Pattern.compile("[1-9][0-9]*(.[0-9]{2})?$");
        EmplPosition = Pattern.compile("^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$");
    }

    EmployeeDTO employee = null;

    public void AddOnAction(ActionEvent actionEvent) throws ClassNotFoundException {                //Add Employee OnAction
        boolean isEmployeeIdMatched = EmplIdpattern.matcher(txtId.getText()).matches();
        boolean isEmployeeNameMatched = EmplNumpattern.matcher(txtName.getText()).matches();
        boolean isEmployeeAddressMatched = Empladdress.matcher(txtAddress.getText()).matches();
        boolean isEmployeeContact = EmplContact.matcher(txtContact.getText()).matches();
        boolean isEmployeeSalary = EmplSalary.matcher(txtSalary.getText()).matches();
        boolean isEmployeePosition = EmplPosition.matcher(txtPosition.getText()).matches();

        if (isEmployeeIdMatched) {
            if (isEmployeeNameMatched) {
                if (isEmployeeAddressMatched) {
                    if (isEmployeeContact) {
                        if (isEmployeeSalary) {
                            if (isEmployeePosition) {
                                employee = new EmployeeDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText(),
                                        Double.parseDouble(txtSalary.getText()), txtPosition.getText());
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Invalid Position !").show();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid Salary!").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid Contact !").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid Address!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Name!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Id!").show();
        }
        try {
            if (employeeBO.saveEmployee(employee)) ;
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successed").show();
            txtId.clear();
            txtName.clear();
            txtAddress.clear();
            txtContact.clear();
            txtSalary.clear();
            txtPosition.clear();
            tblEmployee.refresh();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void initialize() {
        colEid.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colEname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colEaddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colEContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colEsalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("Position"));
        operateColId.setCellValueFactory(new PropertyValueFactory<>("btn"));
        try {
            loadAllEmployee();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        setEmployeeValidation();
    }

    private void loadAllEmployee() throws SQLException, ClassNotFoundException {

        ArrayList<EmployeeDTO> allEmployee = employeeBO.loadAllEmployee();
        Button btn = new Button("Delete");
        for (EmployeeDTO employee : allEmployee) {
            tblEmployee.getItems().add(new EmployeeTM(employee.getId(), employee.getName(), employee.getAddress(),
                    employee.getContact(), employee.getSalary(),
                    employee.getPosition(), btn));
        }
        btn.setOnAction(event -> {
                    final int index = tblEmployee.getSelectionModel().getSelectedIndex();
                    for (int i = 0; i < 100; i++) {
                        if (index == i) {
                            tblEmployee.getItems().remove(i);
                        }
                    }
                }
        );
        tblEmployee.refresh();
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        search();
        try {
            employeeBO.deleteEmployee(txtId.getText());
            Boolean isDeleted = true;
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Delete Succesed !").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Please Try Again !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtSalary.clear();
        txtPosition.clear();
    }

    public void updateOnAction(ActionEvent actionEvent) {
        try {
            employeeBO.updateEmployee(new EmployeeDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText(),
                    Double.parseDouble(txtSalary.getText()), txtPosition.getText()));
            Boolean isupdate = true | false;
            if (isupdate == true) {
                new Alert(Alert.AlertType.CONFIRMATION, "EMPLOYEE UPDATED...! ").show();
            } else if (isupdate == false) {
                new Alert(Alert.AlertType.WARNING, "Try Again...!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtSalary.clear();
        txtPosition.clear();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {  //Search OnAction
        search();
    }

    public void searchOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        search();
    }

    public void search() {
        try {
            ResultSet resultSet = employeeBO.searchEmployee(txtId.getText());
            if (resultSet.next()) {
                txtName.setText(resultSet.getString(2));
                txtAddress.setText(resultSet.getString(3));
                txtContact.setText(resultSet.getString(4));
                txtSalary.setText(resultSet.getString(5));
                txtPosition.setText(resultSet.getString(6));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Employees").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
