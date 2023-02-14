package lk.ijse.FinalProject.bo.custom.impl;

import lk.ijse.FinalProject.bo.custom.VehicleBO;
import lk.ijse.FinalProject.dao.custom.VehicleDAO;
import lk.ijse.FinalProject.dao.custom.impl.DAOFactory;
import lk.ijse.FinalProject.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.FinalProject.model.CustomerDTO;
import lk.ijse.FinalProject.model.VehicleDTO;
import lk.ijse.FinalProject.util.SuperDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleBOImpl implements VehicleBO {
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);

    public ArrayList<VehicleDTO> getAllVehicle() throws SQLException, ClassNotFoundException {
        return vehicleDAO.getAll();
    }

    public boolean saveVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.save(dto);
    }

    public ResultSet searchVehicle(String id) throws SQLException, ClassNotFoundException {
        return vehicleDAO.search(id);
    }

    public boolean updateVehicle(VehicleDTO dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.update(dto);
    }

    public boolean deleteVehicle(String id) throws SQLException, ClassNotFoundException {
        return vehicleDAO.delete(id);
    }


}
