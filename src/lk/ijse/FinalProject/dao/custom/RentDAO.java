package lk.ijse.FinalProject.dao.custom;

import lk.ijse.FinalProject.model.RentDTO;
import lk.ijse.FinalProject.util.CrudDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RentDAO extends CrudDAO<RentDTO, String> {

    boolean save(RentDTO dto) throws SQLException, ClassNotFoundException;

    String findNewItemId() throws ClassNotFoundException;

    ResultSet countRent() throws SQLException, ClassNotFoundException;
}
