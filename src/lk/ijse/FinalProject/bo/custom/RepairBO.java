package lk.ijse.FinalProject.bo.custom;

import lk.ijse.FinalProject.dao.custom.RepairDAO;
import lk.ijse.FinalProject.dao.custom.impl.RepairDAOImpl;
import lk.ijse.FinalProject.model.RepairDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepairBO {

    ArrayList<RepairDTO> getAllRepair() throws SQLException, ClassNotFoundException;

    boolean saveRepair(RepairDTO dto) throws SQLException, ClassNotFoundException;
}
