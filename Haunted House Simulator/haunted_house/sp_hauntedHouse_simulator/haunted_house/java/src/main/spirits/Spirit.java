package main.spirits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Spirit {

    private String name;
    private int ageAtDeath;
    private String causeOfDeath;
    private boolean bodyOnPremise;

    // 0-10 inclusive
    private int hatredOfHumansLevel;
    private int peacefulnessLevel;

    private List<String> manyMessages = new ArrayList<>();
    private String defaultMessage;
    private String unfinishedBusiness;
    private String wish;
    private String physicalForm;
    private String spiritType;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgeAtDeath() {
        return ageAtDeath;
    }

    public void setAgeAtDeath(int ageAtDeath) {
        this.ageAtDeath = ageAtDeath;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public boolean isBodyOnPremise() {
        return bodyOnPremise;
    }

    public void setBodyOnPremise(boolean bodyOnPremise) {
        this.bodyOnPremise = bodyOnPremise;
    }

    public int getHatredOfHumansLevel() {
        return hatredOfHumansLevel;
    }

    public void setHatredOfHumansLevel(int hatredOfHumansLevel) {
        if (hatredOfHumansLevel <= 10 && hatredOfHumansLevel >= 0){
            this.hatredOfHumansLevel = hatredOfHumansLevel;
        } else {
            System.out.println("Please pick a number between 1 - 10.");
        }

    }

    public int getPeacefulnessLevel() {

        return peacefulnessLevel;
    }

    public void setPeacefulnessLevel(int peacefulnessLevel) {
        if (peacefulnessLevel <= 10 && peacefulnessLevel >= 0){
            this.peacefulnessLevel = peacefulnessLevel;
        } else {
            System.out.println("Please pick a number between 1 - 10.");
        }
    }

    public String getDefaultMessage() {
        return this.defaultMessage;
    }

    public void setDefaultMessage(String message) {
        this.defaultMessage = message;
    }


    public List<String> getManyMessages() {
        return this.manyMessages;
    }

    public void setManyMessages(List<String> manyMessages) {
        this.manyMessages = manyMessages;
    }

    public String getUnfinishedBusiness() {
        return unfinishedBusiness;
    }

    public void setUnfinishedBusiness(String unfinishedBusiness) {
        this.unfinishedBusiness = unfinishedBusiness;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public String getPhysicalForm() {
        return physicalForm;
    }

    public void setPhysicalForm(String physicalForm) {
        this.physicalForm = physicalForm;
    }

    public String getSpiritType() {
        return spiritType;
    }

    public void setSpiritType(String spiritType) {
        this.spiritType = spiritType;
    }

    public Spirit(String name, int ageAtDeath, String causeOfDeath, boolean bodyOnPremise) {
        this.name = name;
        this.ageAtDeath = ageAtDeath;
        this.causeOfDeath = causeOfDeath;
        this.bodyOnPremise = bodyOnPremise;
    }

    public Spirit(){}

    public String haunt(){
        String haunt = "";

        for (String message : manyMessages){
            if (hatredOfHumansLevel > 8 && hatredOfHumansLevel <= 10){
                haunt = "Suddenly a door slams and you hear" + message + "*SMASH* *CRASH*";
            } else if (hatredOfHumansLevel > 5 && hatredOfHumansLevel <= 8){
                haunt = "Written on the fog of the mirror is: " + message;
            } else if (hatredOfHumansLevel >= 0 && hatredOfHumansLevel <= 5){
                haunt = "Did you hear that noise? It sounded like someone said: "  + this.defaultMessage;
            }
        }

        return haunt;
    }

    public String leaveClues(String messageReceived){

        String clueReturned = "";

        if (messageReceived.equals("VIDEO")){
            clueReturned = this.physicalForm;
        }

        if(messageReceived.contains("help")){
            if (hatredOfHumansLevel <= 5){

                clueReturned = "I " + this.unfinishedBusiness;
            } else {
                clueReturned = "Why would I help you get rid of me!?";
            }

        }

        if(messageReceived.contains("get out")){
            if (hatredOfHumansLevel > 5){
                clueReturned = this.haunt();
            } else if (hatredOfHumansLevel <= 5 && bodyOnPremise){
                clueReturned = "I'm trying... can you find my remains?";
                this.peacefulnessLevel++;
            }

            clueReturned = this.unfinishedBusiness;

        }

        if(messageReceived.contains("you")){

            if(messageReceived.contains("age")){
                clueReturned = "I am "+ this.ageAtDeath;
            }

            if(messageReceived.contains("happened")){
                if(this.causeOfDeath.contains("unknown")){
                    clueReturned = "I don't know... one moment I was alive and then I wasn't.";
                } else {
                    clueReturned = "I " + this.causeOfDeath;
                    this.peacefulnessLevel++;
                }
            }

            if (messageReceived.contains("name")) {
                clueReturned = "I am " + this.name + " and " + this.wish;
            }

             if (bodyOnPremise){
                if (messageReceived.contains("where") || messageReceived.contains("how")){
                    clueReturned = "I remember last being .... ";
                    this.peacefulnessLevel++;
                }

                clueReturned = "I'm trying... can you find my remains?";
            }

            clueReturned = this.unfinishedBusiness;
            this.peacefulnessLevel++;

        }

        if(messageReceived.contains("want")){
            clueReturned = "They " + discoverWish();
            this.peacefulnessLevel++;
        }


        return clueReturned;

    }


    public String discoverWish(){ return this.wish;}

    public String restInPeace(){

        String goodBye = "";

        if(peacefulnessLevel < 5){
            goodBye = "There is much more I need to tell you.";
        } else if (peacefulnessLevel >= 5 && peacefulnessLevel <= 7){
            goodBye = "I cannot rest until " + wish;
        } else if (peacefulnessLevel >= 9){
            goodBye = this.name + "is finally at peace and will no longer bother you.";
        }
        return goodBye;
    }

}
