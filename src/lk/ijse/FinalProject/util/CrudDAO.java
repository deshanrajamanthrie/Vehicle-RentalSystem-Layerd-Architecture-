package lk.ijse.FinalProject.util;

import lk.ijse.FinalProject.model.CustomerDTO;
import lk.ijse.FinalProject.model.VehicleDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID > extends SuperDAO {    //Using Genarics part
    boolean save(T dto) throws SQLException, ClassNotFoundException;

    boolean delete( ID txtId) throws SQLException, ClassNotFoundException;

    ResultSet search(ID txtId) throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

}
