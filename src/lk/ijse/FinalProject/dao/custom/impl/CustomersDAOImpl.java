package lk.ijse.FinalProject.dao.custom.impl;

import lk.ijse.FinalProject.dao.custom.CustomerDAO;
import lk.ijse.FinalProject.model.CustomerDTO;
import lk.ijse.FinalProject.model.EmployeeDTO;
import lk.ijse.FinalProject.model.VehicleDTO;
import lk.ijse.FinalProject.util.CrudDAO;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomersDAOImpl implements CustomerDAO {
    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?)",
                dto.getId(), dto.getName(), dto.getNic(), dto.getAddress(), dto.getContact());
    }

    @Override
    public boolean delete(String txtId) throws SQLException, ClassNotFoundException {
        boolean isDeleted = CrudUtil.executeUpdate("DELETE FROM Customer WHERE CustID =?", txtId);
        return isDeleted;
    }

    @Override
    public ResultSet search(String txtId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT *FROM Customer WHERE CustID=?", txtId);
        return resultSet;
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        boolean isUpdated = CrudUtil.executeUpdate("UPDATE Customer SET CustName=?,CustNic=?,CustAddress=?,CustContact=? WHERE CustID=?",
                dto.getName(), dto.getNic(), dto.getAddress(), dto.getContact(), dto.getId());
        return isUpdated;
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT *FROM Customer");
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        while (rst.next()) {
            allCustomers.add(new CustomerDTO(rst.getString(1), rst.getString(2),
                    rst.getString(3), rst.getString(4), rst.getString(5)));
        }
        return allCustomers;
    }

    @Override
    public ArrayList<String> getCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT  CustID FROM Customer");
        ArrayList<String> id = new ArrayList<>();
        while (resultSet.next()) {
            id.add(resultSet.getString(1));
        }
        return id;
    }

    @Override
    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException {  //Rent order ekt customer Object ekak passa karai
        ResultSet resultSet = CrudUtil.executeQuery("SELECT *FROM Customer WHERE CustId=?", id);
        if (resultSet.next()) {
            return new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }

}



