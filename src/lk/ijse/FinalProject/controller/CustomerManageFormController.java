package lk.ijse.FinalProject.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.FinalProject.TMmodel.CustomerTM;
import lk.ijse.FinalProject.bo.custom.CustomerBO;
import lk.ijse.FinalProject.bo.custom.impl.CustomerBOImpl;
import lk.ijse.FinalProject.dao.custom.impl.DAOFactory;
import lk.ijse.FinalProject.model.CustomerDTO;
import lk.ijse.FinalProject.util.SuperDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CustomerManageFormController {

    public TextField txtId;
    public TextField txtContact;
    public TextField txtNic;
    public TextField txtName;
    public TableView<CustomerTM> customerTable;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colOperate;
    public TextField txtAddress;

    private Pattern customerIdpattern;
    private Pattern customerNpattern;
    private Pattern customerNicpattern;
    private Pattern customerAddresspattern;
    private Pattern customerContactpattterns;
    // private CrudDAO<CustomerDTO,String> customersDAO = new CustomersDAOImpl();
    //private CustomerDAO customersDAO = new CustomersDAOImpl();
    CustomerBO customerBO = new CustomerBOImpl();
    //CustomerBO customerBO = (CustomerBO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("Nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadAllCustomer();
        SetCustomerValidation();
    }

    private void SetCustomerValidation() {
        customerIdpattern = Pattern.compile("C[0-9][0-9][0-9]");
        customerNpattern = Pattern.compile("^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$");
        customerNicpattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
        customerAddresspattern = Pattern.compile("^[a-zA-Z-\\s]+$");
        customerContactpattterns = Pattern.compile("^07(7|6|8|1|2|5|0|4)-[0-9]{7}$");
    }


    private void loadAllCustomer() {

        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomer();
            for (CustomerDTO c : allCustomers) {
                Button btn = new Button("Remove Customer");
                customerTable.getItems().add(new CustomerTM(c.getId(),
                        c.getName(), c.getNic(), c.getAddress(), c.getContact(), btn));

                btn.setOnAction(event -> {
                            final int index = customerTable.getSelectionModel().getSelectedIndex();
                            for (int i = 0; i < 100; i++) {
                                if (index == i) {
                                    customerTable.getItems().remove(i);
                                }
                            }
                        }
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    CustomerDTO customerdto = null;

    public void addOnAction(ActionEvent actionEvent) {


        boolean isCustomeridMatched = customerIdpattern.matcher(txtId.getText()).matches();
        boolean isCustomerNameMatched = customerNpattern.matcher(txtName.getText()).matches();
        boolean isCustomerNicMatched = customerNicpattern.matcher(txtNic.getText()).matches();
        boolean isCustomerAddressMatched = customerAddresspattern.matcher(txtAddress.getText()).matches();
        boolean isCustomerContactMatched = customerContactpattterns.matcher(txtContact.getText()).matches();

        if (isCustomeridMatched) {
            if (isCustomerNameMatched) {
                if (isCustomerNicMatched) {
                    if (isCustomerAddressMatched) {
                        if (isCustomerContactMatched) {
                            customerdto = new CustomerDTO(txtId.getText()
                                    , txtName.getText(), txtNic.getText(), txtAddress.getText(), txtContact.getText());

                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid Contact Number").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid Address !").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid Nic !").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Name !").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Id Number !, Try Again !").show();
        }
        try {
            if(customerBO.saveCustomer(customerdto));
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successed").show();
            txtId.clear();
            txtName.clear();
            txtNic.clear();
            txtAddress.clear();
            txtContact.clear();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        customerTable.refresh();
    }


    public void txtFieldOnKeyRealized(KeyEvent keyEvent) {

    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        search();
    }

    public void searchOnAction(ActionEvent actionEvent) {
        search();
    }

    private void search() {
        try {
            ResultSet resultSet = customerBO.searchCustomer(txtId.getText());
            if (resultSet.next()) {
                txtName.setText(resultSet.getString(2));
                txtNic.setText(resultSet.getString(3));
                txtAddress.setText(resultSet.getString(4));
                txtContact.setText(resultSet.getString(5));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Customer !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        try {
            customerBO.updateCustomer(new CustomerDTO(txtId.getText(), txtName.getText(), txtNic.getText(), txtAddress.getText(),
                    txtContact.getText()));
            Boolean isUpdated = true | false;
            if (isUpdated == true) {
                new Alert(Alert.AlertType.CONFIRMATION, "Update Successed ").show();
            } else if (isUpdated == false) {
                new Alert(Alert.AlertType.WARNING, " Have not Update Try Again !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        search();
        customerBO.deleteCustomer(txtId.getText());
        Boolean isDeleted = true;
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Delete Successed !").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Please Try Again  !").show();
        }
        txtId.clear();
        txtName.clear();
        txtNic.clear();
        txtAddress.clear();
        txtContact.clear();
    }
}

