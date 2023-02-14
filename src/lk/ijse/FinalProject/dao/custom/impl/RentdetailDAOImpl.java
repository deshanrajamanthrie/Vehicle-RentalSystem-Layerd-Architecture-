package lk.ijse.FinalProject.dao.custom.impl;

import lk.ijse.FinalProject.dao.custom.RentdetailDAO;
import lk.ijse.FinalProject.dao.custom.VehicleDAO;
import lk.ijse.FinalProject.model.RentDetailDTO;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentdetailDAOImpl implements RentdetailDAO {

    private VehicleDAO vehicleDAO = new VehicleDAOImpl();

    @Override
    public boolean saveRentDetails(ArrayList<RentDetailDTO> details) throws SQLException, ClassNotFoundException {
        for (RentDetailDTO data : details) {
            boolean isDetailSaved = CrudUtil.executeUpdate("INSERT INTO Rentdetail VALUES(?,?,?,?,?)",
                    data.getRentId(), data.getVehicleId(), data.getSpendDays(), data.getQty(),
                    data.getPerDayCost());
            if (isDetailSaved) {

                boolean b = vehicleDAO.UpdateVehicleqty(data.getVehicleId(), data.getQty());
                if (!b) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean save(RentDetailDTO dto) throws SQLException, ClassNotFoundException {
        return false;
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
    public boolean update(RentDetailDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<RentDetailDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT *FROM Rentdetail");
        ArrayList<RentDetailDTO> getAllRentDetail = new ArrayList<>();
        while (rst.next()){
            getAllRentDetail.add(new RentDetailDTO(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getInt(4),rst.getDouble(5)));
        }
        return getAllRentDetail;
    }
}
