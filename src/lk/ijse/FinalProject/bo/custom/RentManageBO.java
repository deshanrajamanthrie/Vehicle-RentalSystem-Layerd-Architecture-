package lk.ijse.FinalProject.bo.custom;

import javafx.scene.control.Alert;
import lk.ijse.FinalProject.dao.custom.CustomerDAO;
import lk.ijse.FinalProject.dao.custom.RentDAO;
import lk.ijse.FinalProject.dao.custom.impl.CustomersDAOImpl;
import lk.ijse.FinalProject.dao.custom.impl.RentDAOImpl;
import lk.ijse.FinalProject.dao.custom.impl.RentdetailDAOImpl;
import lk.ijse.FinalProject.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.FinalProject.db.DBConnection;
import lk.ijse.FinalProject.model.CustomerDTO;
import lk.ijse.FinalProject.model.RentDTO;
import lk.ijse.FinalProject.model.RentDetailDTO;
import lk.ijse.FinalProject.model.VehicleDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RentManageBO {

    boolean getRentManageOrder(ArrayList<RentDetailDTO> details, RentDTO rent) throws SQLException, ClassNotFoundException;

    VehicleDTO getListVehicle(String getSelectID) throws SQLException, ClassNotFoundException;

    ArrayList<String> getVehicleIdList() throws SQLException, ClassNotFoundException;

    CustomerDTO getListCustomer(String getSelectedId) throws SQLException, ClassNotFoundException;

    ArrayList<String> getCustomerIdList() throws SQLException, ClassNotFoundException;

    String getfindNewItemId() throws ClassNotFoundException;

    ResultSet countRent() throws SQLException, ClassNotFoundException;
}
