package lk.ijse.FinalProject.bo.custom.impl;

import lk.ijse.FinalProject.bo.custom.RepairBO;
import lk.ijse.FinalProject.dao.custom.RepairDAO;
import lk.ijse.FinalProject.dao.custom.impl.DAOFactory;
import lk.ijse.FinalProject.dao.custom.impl.RepairDAOImpl;
import lk.ijse.FinalProject.model.RepairDTO;
import lk.ijse.FinalProject.util.SuperDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepairBOImpl implements RepairBO {
    RepairDAO repairDAO = (RepairDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REPAIR);

    public ArrayList<RepairDTO> getAllRepair() throws SQLException, ClassNotFoundException {
        return repairDAO.getAll();
    }

    public boolean saveRepair(RepairDTO dto) throws SQLException, ClassNotFoundException {
        return repairDAO.save(dto);
    }
}
