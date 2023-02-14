package lk.ijse.FinalProject.dao.custom;

import lk.ijse.FinalProject.model.CustomerDTO;
import lk.ijse.FinalProject.util.CrudDAO;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<CustomerDTO, String> {
    ArrayList<String> getCustomerId() throws SQLException, ClassNotFoundException;

    CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException;


}
