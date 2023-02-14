package lk.ijse.FinalProject.dao.custom;

import lk.ijse.FinalProject.model.RentDetailDTO;
import lk.ijse.FinalProject.util.CrudDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RentdetailDAO extends CrudDAO<RentDetailDTO,String> {

     boolean saveRentDetails(ArrayList<RentDetailDTO> details) throws SQLException, ClassNotFoundException;
}
