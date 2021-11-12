package main.java.com;

import java.awt.*;
import java.util.Map;

public class User {

    private Map<String, Color> colorLibrary;
    private String firstName;
    private String lastName;
    private String fullName;
    private String emailAddress;
    private String birthday;
    private String preferredArtisticMedium;
    private String primaryLocation;

    public Map<String, Color> getColorLibrary() {
        return colorLibrary;
    }

    public void setColorLibrary(Map<String, Color> colorLibrary) {
        this.colorLibrary = colorLibrary;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPreferredArtisticMedium() {
        return preferredArtisticMedium;
    }

    public void setPreferredArtisticMedium(String preferredArtisticMedium) {
        this.preferredArtisticMedium = preferredArtisticMedium;
    }

    public String getPrimaryLocation() {
        return primaryLocation;
    }

    public void setPrimaryLocation(String primaryLocation) {
        this.primaryLocation = primaryLocation;
    }


    public User(){}

    public User(String firstName, String lastName, String emailAddress,
                    String birthday, String preferredArtisticMedium, String primaryLocation){
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = lastName + ", " + firstName;
        this.emailAddress = emailAddress;
        this.birthday = birthday;
        this.preferredArtisticMedium = preferredArtisticMedium;
        this.primaryLocation = primaryLocation;
    }

    public String toString(){
        return firstName + " " + lastName + " can be reached at " + this.emailAddress + ", was born on " + this.birthday +
                ", preferred medium is " + preferredArtisticMedium + ", and lives in " + this.primaryLocation;
    }


}
