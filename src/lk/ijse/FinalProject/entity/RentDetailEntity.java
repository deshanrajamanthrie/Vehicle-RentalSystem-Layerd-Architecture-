package lk.ijse.FinalProject.entity;

public class RentDetailEntity {
    private String RentId;
    private String VehicleId;
    private int spendDays;

    public RentDetailEntity() {
    }

    private int qty;

    public RentDetailEntity(String rentId, String vehicleId, int spendDays, int qty, double perDayCost) {
        RentId = rentId;
        VehicleId = vehicleId;
        this.spendDays = spendDays;
        this.qty = qty;
        this.perDayCost = perDayCost;
    }

    private double perDayCost;

    public String getRentId() {
        return RentId;
    }

    public void setRentId(String rentId) {
        RentId = rentId;
    }

    public String getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(String vehicleId) {
        VehicleId = vehicleId;
    }

    public int getSpendDays() {
        return spendDays;
    }

    public void setSpendDays(int spendDays) {
        this.spendDays = spendDays;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPerDayCost() {
        return perDayCost;
    }

    public void setPerDayCost(double perDayCost) {
        this.perDayCost = perDayCost;
    }
}
