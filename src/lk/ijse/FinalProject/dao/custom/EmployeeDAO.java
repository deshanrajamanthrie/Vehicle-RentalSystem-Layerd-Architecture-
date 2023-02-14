package lk.ijse.FinalProject.dao.custom;

import lk.ijse.FinalProject.model.EmployeeDTO;
import lk.ijse.FinalProject.util.CrudDAO;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<EmployeeDTO,String> {

     ResultSet employeeCount() throws SQLException, ClassNotFoundException;

}
