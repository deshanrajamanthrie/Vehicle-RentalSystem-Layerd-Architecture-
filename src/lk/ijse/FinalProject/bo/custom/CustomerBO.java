package lk.ijse.FinalProject.bo.custom;

import lk.ijse.FinalProject.dao.custom.CustomerDAO;
import lk.ijse.FinalProject.dao.custom.impl.CustomersDAOImpl;
import lk.ijse.FinalProject.model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {
    CustomerDAO customersDAO = new CustomersDAOImpl();

    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
}
