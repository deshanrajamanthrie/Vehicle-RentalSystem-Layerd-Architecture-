package lk.ijse.FinalProject.dao.custom;

import lk.ijse.FinalProject.model.VehicleDTO;
import lk.ijse.FinalProject.util.CrudDAO;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleDAO extends CrudDAO<VehicleDTO, String> {
    ArrayList<String> getVehicleId() throws SQLException, ClassNotFoundException;

    VehicleDTO getVehicle(String id) throws SQLException, ClassNotFoundException;

    boolean UpdateVehicleqty(String vehicleId, int qty) throws SQLException, ClassNotFoundException;

}
