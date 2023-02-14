package lk.ijse.FinalProject.bo.custom;

import lk.ijse.FinalProject.dao.custom.RentDAO;
import lk.ijse.FinalProject.dao.custom.RentdetailDAO;
import lk.ijse.FinalProject.dao.custom.impl.RentDAOImpl;
import lk.ijse.FinalProject.dao.custom.impl.RentdetailDAOImpl;
import lk.ijse.FinalProject.model.RentDTO;
import lk.ijse.FinalProject.model.RentDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RentAndDetailBO {

    ArrayList<RentDTO> getallRent() throws SQLException, ClassNotFoundException;

    ArrayList<RentDetailDTO> getAllRentDetail() throws SQLException, ClassNotFoundException;

}
