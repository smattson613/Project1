package dev.mattson.entities;

public class Resident {

    private int residentId;
    private String username;
    private String password;
    private Title title;

    public Resident(){
    }

    public Resident(int residentId, String username, String password, Title title) {
        this.residentId = residentId;
        this.username = username;
        this.password = password;
        this.title = title;
    }

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "residentId=" + residentId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", title=" + title +
                '}';
    }
}


