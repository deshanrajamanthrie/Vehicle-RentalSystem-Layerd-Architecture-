package lk.ijse.FinalProject.entity;

public class RepairEntity {
    private String repairid;

    public RepairEntity() {
    }

    private double repairPrize;

    public RepairEntity(String repairid, double repairPrize, String vehicleId) {
        this.repairid = repairid;
        this.repairPrize = repairPrize;
        this.vehicleId = vehicleId;
    }

    private String vehicleId;

    public String getRepairid() {
        return repairid;
    }

    public void setRepairid(String repairid) {
        this.repairid = repairid;
    }

    public double getRepairPrize() {
        return repairPrize;
    }

    public void setRepairPrize(double repairPrize) {
        this.repairPrize = repairPrize;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
}
