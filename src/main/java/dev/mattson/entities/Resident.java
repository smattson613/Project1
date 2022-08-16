package dev.mattson.entities;

public class Resident {

    private int residentId;
    private String fistName;
    private String lastName;
    private Status status;

    public Resident(){
    }

    public Resident(int id, String fistName, String lastName, Status status) {
        this.residentId = id;
        this.fistName = fistName;
        this.lastName = lastName;
        this.status = status;
    }

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "residentId=" + residentId +
                ", fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                '}';
    }
}


