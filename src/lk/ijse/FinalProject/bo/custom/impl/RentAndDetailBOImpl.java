package lk.ijse.FinalProject.bo.custom.impl;

import lk.ijse.FinalProject.bo.custom.RentAndDetailBO;
import lk.ijse.FinalProject.dao.custom.RentDAO;
import lk.ijse.FinalProject.dao.custom.RentdetailDAO;
import lk.ijse.FinalProject.dao.custom.impl.DAOFactory;
import lk.ijse.FinalProject.dao.custom.impl.RentDAOImpl;
import lk.ijse.FinalProject.dao.custom.impl.RentdetailDAOImpl;
import lk.ijse.FinalProject.model.RentDTO;
import lk.ijse.FinalProject.model.RentDetailDTO;
import lk.ijse.FinalProject.util.SuperDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class RentAndDetailBOImpl implements RentAndDetailBO {
    RentDAO rentDAO = (RentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RENT);
    RentdetailDAO rentdetailDAO = (RentdetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RENTDETAIL);

    public ArrayList<RentDTO> getallRent() throws SQLException, ClassNotFoundException {
        return rentDAO.getAll();
    }
    public ArrayList<RentDetailDTO> getAllRentDetail() throws SQLException, ClassNotFoundException {

        return rentdetailDAO.getAll();
    }

}
