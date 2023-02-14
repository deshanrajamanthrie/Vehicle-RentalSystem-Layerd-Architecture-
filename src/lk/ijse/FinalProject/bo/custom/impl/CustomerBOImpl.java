package lk.ijse.FinalProject.bo.custom.impl;

import lk.ijse.FinalProject.bo.custom.CustomerBO;
import lk.ijse.FinalProject.dao.custom.CustomerDAO;
import lk.ijse.FinalProject.dao.custom.impl.CustomersDAOImpl;
import lk.ijse.FinalProject.dao.custom.impl.DAOFactory;
import lk.ijse.FinalProject.model.CustomerDTO;
import lk.ijse.FinalProject.util.SuperDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customersDAO.save(dto);
    }

    public ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customersDAO.search(id);
    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customersDAO.update(dto);
    }

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customersDAO.delete(id);
    }


}
