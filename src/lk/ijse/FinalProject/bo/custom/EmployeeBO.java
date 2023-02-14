package lk.ijse.FinalProject.bo.custom;

import lk.ijse.FinalProject.dao.custom.EmployeeDAO;
import lk.ijse.FinalProject.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.FinalProject.model.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> loadAllEmployee() throws SQLException, ClassNotFoundException;

    ResultSet searchEmployee(String id) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

     ResultSet employeeCount() throws SQLException, ClassNotFoundException;
}
