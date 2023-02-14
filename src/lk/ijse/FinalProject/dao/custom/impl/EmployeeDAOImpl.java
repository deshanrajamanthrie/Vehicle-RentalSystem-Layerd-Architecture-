package lk.ijse.FinalProject.dao.custom.impl;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import lk.ijse.FinalProject.TMmodel.EmployeeTM;
import lk.ijse.FinalProject.dao.custom.EmployeeDAO;
import lk.ijse.FinalProject.model.EmployeeDTO;
import lk.ijse.FinalProject.model.VehicleDTO;
import lk.ijse.FinalProject.util.CrudDAO;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Employee VALUES (?,?,?,?,?,?)",
                dto.getId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getSalary(), dto.getPosition());
    }

    @Override
    public boolean delete(String txtId) throws SQLException, ClassNotFoundException {
        boolean isDeleted = CrudUtil.executeUpdate("DELETE FROM Employee WHERE EmployeeId= ?", txtId);
        return isDeleted;
    }

    @Override
    public ResultSet search(String txtId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT *FROM Employee WHERE EmployeeID =?", txtId);
        return resultSet;
    }

    @Override
    public boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        boolean isupdate = CrudUtil.executeUpdate("UPDATE Employee SET EmployeeName =?,EmployeeAddress=?,EmployeeContact=?,EmployeeSalary=?,EmplyoyeeJobtype=? WHERE EmployeeID= ?",
                dto.getName(), dto.getAddress(), dto.getContact(), dto.getSalary(), dto.getPosition(), dto.getId());
        return isupdate;

    }

    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT *FROM Employee");
        ArrayList<EmployeeDTO> allEmployee = new ArrayList<EmployeeDTO>();
        while (rst.next()) {
            allEmployee.add(new EmployeeDTO(rst.getString(1), rst.getString(2), rst.getString(3),
                    rst.getString(4), rst.getDouble(5), rst.getString(6)));
        }
        return allEmployee;
    }
    public ResultSet employeeCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT COUNT( EmployeeID) FROM Employee");
        if (rst.next()) {
            rst.getString(1);
        }
        return rst;
    }



}
