package lk.ijse.FinalProject.bo.custom.impl;

import lk.ijse.FinalProject.bo.custom.EmployeeBO;
import lk.ijse.FinalProject.dao.custom.EmployeeDAO;
import lk.ijse.FinalProject.dao.custom.impl.DAOFactory;
import lk.ijse.FinalProject.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.FinalProject.model.EmployeeDTO;
import lk.ijse.FinalProject.util.SuperDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(dto);
    }

    public ArrayList<EmployeeDTO> loadAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }

    public ResultSet searchEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(id);
    }

    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(dto);
    }
    public ResultSet employeeCount() throws SQLException, ClassNotFoundException{
        return employeeDAO.employeeCount();
    }

}
