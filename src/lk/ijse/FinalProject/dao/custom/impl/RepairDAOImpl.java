package lk.ijse.FinalProject.dao.custom.impl;

import lk.ijse.FinalProject.dao.custom.RepairDAO;
import lk.ijse.FinalProject.model.RepairDTO;
import lk.ijse.FinalProject.model.VehicleDTO;
import lk.ijse.FinalProject.util.CrudDAO;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairDAOImpl implements RepairDAO {
    public boolean save(RepairDTO dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Repair VALUES (?,?,?)", dto.getRepairid(),
                dto.getRepairPrize(), dto.getVehicleId());
    }

    @Override
    public boolean delete(String txtId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet search(String txtId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(RepairDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public ArrayList<RepairDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.executeQuery("SELECT *FROM Repair");
        ArrayList<RepairDTO> allRepairs = new ArrayList<>();
        while (result.next()) {
            allRepairs.add(new RepairDTO(
                    result.getString(1),
                    result.getDouble(2),
                    result.getString(3)

            ));
        }
        return allRepairs;

    }



}
