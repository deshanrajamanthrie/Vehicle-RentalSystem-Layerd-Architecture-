package lk.ijse.FinalProject.dao.custom.impl;

import lk.ijse.FinalProject.bo.custom.impl.CustomerBOImpl;
import lk.ijse.FinalProject.util.SuperDAO;

public class DAOFactory {
    private static DAOFactory daoFactory;

   private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }


    public enum DAOTypes {
        VEHICLE, CUSTOMER, EMPLOYEE, RENTDETAIL, RENT, REPAIR, QUERY
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case VEHICLE:
                return new VehicleDAOImpl();
            case CUSTOMER:
                return new CustomersDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case RENTDETAIL:
                return new RentdetailDAOImpl();
            case RENT:
                return new RentDAOImpl();
            case REPAIR:
                return new RepairDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
