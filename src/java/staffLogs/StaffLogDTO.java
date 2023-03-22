/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package staffLogs;

/**
 *
 * @author thaiq
 */
public class StaffLogDTO {
    private int logID;
    private String email;
    private String timeIn;
    private String timeOut;

    public StaffLogDTO() {
    }

    public StaffLogDTO(int logID, String email, String timeIn, String timeOut) {
        this.logID = logID;
        this.email = email;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public String toString() {
        return "StaffLogDTO{" + "logID=" + logID + ", email=" + email + ", timeIn=" + timeIn + ", timeOut=" + timeOut + '}';
    }
    
}
