package lk.ijse.FinalProject.entity;

import java.time.LocalDate;

public class RentEntity {
    private String id;
    private String StartDate;

    public RentEntity() {
    }

    private LocalDate ReserveDate;

    public RentEntity(String id, String startDate, LocalDate reserveDate, String customerId) {
        this.id = id;
        StartDate = startDate;
        ReserveDate = reserveDate;
        CustomerId = customerId;
    }

    private String CustomerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public LocalDate getReserveDate() {
        return ReserveDate;
    }

    public void setReserveDate(LocalDate reserveDate) {
        ReserveDate = reserveDate;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

}
