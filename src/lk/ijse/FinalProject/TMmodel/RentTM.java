package lk.ijse.FinalProject.TMmodel;

import javafx.scene.control.Button;

import java.sql.Date;
import java.time.LocalDate;

public class RentTM {

    private String id;
    private String StartDate;
    private LocalDate ReserveDate;
    private String CustomerId;
    private Button btn;

    public RentTM() {
    }

    public RentTM(String id, String startDate, LocalDate reserveDate, String customerId, Button btn) {
        this.id = id;
        StartDate = startDate;
        ReserveDate = reserveDate;
        CustomerId = customerId;
        this.btn = btn;
    }



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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "RentTM{" +
                "id='" + id + '\'' +
                ", StartDate='" + StartDate + '\'' +
                ", ReserveDate=" + ReserveDate +
                ", CustomerId='" + CustomerId + '\'' +
                ", btn=" + btn +
                '}';
    }
}

    /*public RentTM(String id, String startDate, LocalDate reserveDate, String customerId, Button btn) {
    }

    public RentTM(String id, String startDate, LocalDate reserveDate, String customerId) {
        this.id = id;
        StartDate = startDate;
        ReserveDate = reserveDate;
        CustomerId = customerId;
    }




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
    @Override
    public String toString() {
        return "RentTM{" +
                "id='" + id + '\'' +
                ", StartDate='" + StartDate + '\'' +
                ", ReserveDate=" + ReserveDate +
                ", CustomerId='" + CustomerId + '\'' +
                '}';
    }*/



