package lk.ijse.FinalProject.dao.custom.impl;

import lk.ijse.FinalProject.dao.custom.RentDAO;
import lk.ijse.FinalProject.model.RentDTO;
import lk.ijse.FinalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentDAOImpl implements RentDAO {
    @Override
    public boolean save(RentDTO dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Rent VALUES(?,?,?,?)",
               dto.getId(), dto.getStartDate(), dto.getReserveDate(), dto.getCustomerId());


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
    public boolean update(RentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<RentDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Rent");
        ArrayList<RentDTO> allRent= new ArrayList<>();
        while (rst.next()){
           allRent.add(new RentDTO(rst.getString(1), rst.getString(2),rst.getDate(3).toLocalDate(), rst.getString(4)));
        }

        return allRent;
    }
    public String findNewItemId() throws ClassNotFoundException {
        String itemid = null;
        try {
            String sql = "SELECT RentID FROM rent ORDER BY RentID DESC LIMIT 1";
            ResultSet result = CrudUtil.executeQuery(sql);
            if (!result.next()) {
                itemid = generateNextItemId(result.getString(null));
            }
            itemid = generateNextItemId(result.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemid;
    }


    private String generateNextItemId(String currentItemId) {
        if (currentItemId == null) {
            return "R001";
        }else{
            String[] split = currentItemId.split("R");
            int id = Integer.parseInt(split[1]);
            id++;
            String newId = String.format("R%03d", id);
            return newId;
        }
    }
    public ResultSet countRent() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT COUNT(RentID)FROM Rent");
        if (resultSet.next()) {
            resultSet.getString(1);
        }
        return resultSet;


    }



}
