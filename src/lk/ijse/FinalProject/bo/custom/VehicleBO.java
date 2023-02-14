package lk.ijse.FinalProject.bo.custom;

import lk.ijse.FinalProject.dao.custom.VehicleDAO;
import lk.ijse.FinalProject.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.FinalProject.model.VehicleDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleBO {

    ArrayList<VehicleDTO> getAllVehicle() throws SQLException, ClassNotFoundException;

    boolean saveVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException;

    ResultSet searchVehicle(String id) throws SQLException, ClassNotFoundException;

    boolean updateVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteVehicle(String id) throws SQLException, ClassNotFoundException;
}
