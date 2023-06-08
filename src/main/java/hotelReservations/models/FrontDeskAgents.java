package hotelReservations.models;

import java.math.BigDecimal;

public class FrontDeskAgents {

    private int frontDeskAgentID;//auto
    private String firstName;
    private String lastName;
    private String gender;
    private String workingShift;
    private BigDecimal hourlyPay;

    public FrontDeskAgents(String firstName, String lastName, String gender, String workingShift, BigDecimal hourlyPay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.workingShift = workingShift;
        this.hourlyPay = hourlyPay;
    }

    public int getFrontDeskAgentID() {
        return frontDeskAgentID;
    }

    public void setFrontDeskAgentID(int frontDeskAgentID) {
        this.frontDeskAgentID = frontDeskAgentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWorkingShift() {
        return workingShift;
    }

    public void setWorkingShift(String workingShift) {
        this.workingShift = workingShift;
    }

    public BigDecimal getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(BigDecimal hourlyPay) {
        this.hourlyPay = hourlyPay;
    }
}
