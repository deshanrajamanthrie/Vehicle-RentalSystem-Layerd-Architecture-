package lk.ijse.FinalProject.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.FinalProject.bo.custom.RentManageBO;
import lk.ijse.FinalProject.dao.custom.CustomerDAO;
import lk.ijse.FinalProject.dao.custom.RentDAO;
import lk.ijse.FinalProject.dao.custom.VehicleDAO;
import lk.ijse.FinalProject.dao.custom.impl.*;
import lk.ijse.FinalProject.db.DBConnection;
import lk.ijse.FinalProject.model.CustomerDTO;
import lk.ijse.FinalProject.model.RentDTO;
import lk.ijse.FinalProject.model.RentDetailDTO;
import lk.ijse.FinalProject.model.VehicleDTO;
import lk.ijse.FinalProject.util.SuperDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentManageBOImpl implements RentManageBO {

    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);
    CustomerDAO customersDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    RentDAO rentDAO = (RentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RENT);


    public boolean getRentManageOrder(ArrayList<RentDetailDTO> details, RentDTO rent) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isRentSaved = rentDAO.save(rent);
            if (isRentSaved) {
                boolean isDetailSaved = new RentdetailDAOImpl().saveRentDetails(details);
                if (isDetailSaved) {
                    connection.commit();
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved Successed !").show();
                } else {
                    connection.rollback();
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            } else {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

    public VehicleDTO getListVehicle(String getSelectID) throws SQLException, ClassNotFoundException {
        return vehicleDAO.getVehicle(getSelectID);
    }

    public ArrayList<String> getVehicleIdList() throws SQLException, ClassNotFoundException {
        return vehicleDAO.getVehicleId();

    }

    public CustomerDTO getListCustomer(String getSelectedId) throws SQLException, ClassNotFoundException {
        return customersDAO.getCustomer(getSelectedId);
    }

    public ArrayList<String> getCustomerIdList() throws SQLException, ClassNotFoundException {
        return customersDAO.getCustomerId();
    }
    public String getfindNewItemId() throws ClassNotFoundException {
        return rentDAO.findNewItemId();
    }
    public ResultSet countRent() throws SQLException, ClassNotFoundException {
        return rentDAO.countRent();
    }
}
