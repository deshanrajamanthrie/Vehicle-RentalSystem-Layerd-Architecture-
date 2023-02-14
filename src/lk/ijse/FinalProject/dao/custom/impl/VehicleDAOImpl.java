package lk.ijse.FinalProject.dao.custom.impl;

import lk.ijse.FinalProject.dao.custom.VehicleDAO;
import lk.ijse.FinalProject.model.VehicleDTO;
import lk.ijse.FinalProject.util.CrudDAO;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDAOImpl implements VehicleDAO {
    public boolean save(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Vehicle VALUES(?,?,?,?,?)", dto.getId(), dto.getType(), dto.getNumplate(),
                dto.getQty(), dto.getPerdaycost());
    }

    public boolean delete(String txtId) throws SQLException, ClassNotFoundException {
        boolean isDeleted = CrudUtil.executeUpdate("DELETE FROM Vehicle WHERE VehicleID=?", txtId);
        return isDeleted;
    }

    public ResultSet search(String txtId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Vehicle WHERE VehicleID=?", txtId);
        return resultSet;
    }

    public boolean update(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        boolean Updated = CrudUtil.executeUpdate("UPDATE Vehicle SET VehicleType=?,VehicleNoPlate=?,Vehicleqty=?,dayperCost=? WHERE VehicleID=?",
                dto.getType(), dto.getNumplate(), dto.getQty(), Double.parseDouble(String.valueOf(dto.getPerdaycost())), dto.getId());
        return Updated;

    }

    public ArrayList<VehicleDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT *FROM Vehicle");
        ArrayList<VehicleDTO> allVehicles = new ArrayList<>();
        while (rst.next()) {
            allVehicles.add(new VehicleDTO(rst.getString(1), rst.getString(2),
                    rst.getString(3), rst.getString(4), rst.getDouble(5)));
        }
        return allVehicles;
    }
    public ArrayList<String> getVehicleId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT VehicleID FROM Vehicle");
        ArrayList<String> id = new ArrayList<>();
        while (resultSet.next()) {
            id.add(resultSet.getString(1));
        }
        return id;
    }
     public VehicleDTO getVehicle(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT *FROM Vehicle WHERE VehicleID=?", id);
        if (resultSet.next()) {
            return new VehicleDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            );
        }
        return null;
    }

    @Override
    public boolean UpdateVehicleqty(String vehicleId, int qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Vehicle SET  Vehicleqty= Vehicleqty-? WHERE VehicleID=?", qty, vehicleId);
    }
}
