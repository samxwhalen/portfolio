package main.spirits;

import main.ContactDeadApproach;
import main.people.Person;

import java.util.List;

public class Demon extends Spirit {

    private int demonicPower = 3;
    private String exchangeDemand;
    private String soulCaptureResponse;
    private String summoningResponse;
    private boolean isSummoned = false;
    private String spiritType = "Demon";


    public String getSpiritType() {
        return spiritType;
    }

    public int getDemonicPower() {
        return demonicPower;
    }

    public void setDemonicPower(int demonicPower) {
        this.demonicPower = demonicPower;
    }

    public String getExchangeDemand() {
        return exchangeDemand;
    }

    public void setExchangeDemand(String exchangeDemand) {
        this.exchangeDemand = exchangeDemand;
    }

    public String getSoulCaptureResponse() {
        return soulCaptureResponse;
    }

    public void setSoulCaptureResponse(String soulCaptureResponse) {
        this.soulCaptureResponse = soulCaptureResponse;
    }

    public String getSummoningResponse() {
        return summoningResponse;
    }

    public void setSummoningResponse(String summoningResponse) {
        this.summoningResponse = summoningResponse;
    }

    public boolean isSummoned() {
        return isSummoned;
    }

    public void setSummoned(boolean summoned) {
        isSummoned = summoned;
    }

    public Demon(String name, int ageAtDeath, String causeOfDeath, boolean bodyOnPremise, int demonicPower, String exchangeDemand, String soulCaptureResponse, String summoningResponse, boolean isSummoned) {
        super(name, ageAtDeath, causeOfDeath, bodyOnPremise);
        this.demonicPower = demonicPower;
        this.exchangeDemand = exchangeDemand;
        this.soulCaptureResponse = soulCaptureResponse;
        this.summoningResponse = summoningResponse;
        this.isSummoned = isSummoned;
        setPhysicalForm("something unlike anything anyone had ever seen before. A horrific beast that is neither human nor spector.");
    }

    public Demon(int demonicPower, String exchangeDemand, String soulCaptureResponse, String summoningResponse, boolean isSummoned) {
        this.demonicPower = demonicPower;
        this.exchangeDemand = exchangeDemand;
        this.soulCaptureResponse = soulCaptureResponse;
        this.summoningResponse = summoningResponse;
        this.isSummoned = isSummoned;
    }

    public Demon(int demonicPower) {
        this.demonicPower = demonicPower;
    }

    public String stealSoul(Person victim){

        victim.setPossessed(true);

        System.out.println(" ");
        System.out.println(victim.possessedAction(true));

        return victim.getName() + " has had their soul stolen by " + this.getName() + " ! You hear them say: " + this.getSoulCaptureResponse();

    }

    public String beSummoned(List<Person> participants){
        this.isSummoned = true;
        ContactDeadApproach ritual = new ContactDeadApproach();

        String summonedAction = "";

        if (demonicPower <= 3){
            summonedAction = "Did you hear that?.. It sounded like someone said : " + this.summoningResponse;
            for (Person participant : participants){
                if (participant.getSkepticismLevel() < 5){
                    summonedAction = summonedAction + participant.scareReaction();
                }
            }
        } else if (demonicPower > 3 && demonicPower <= 7){
            summonedAction = "Every light bulb in the house bursts, sending glass everywhere. " +
                    "Someone lights a match and notices that there is a weird liquid running down the wall from the ceiling. " +
                    "The smell of sulphur is strong and suddenly everyone hears in their head as if it were their own thought: " +
            this.summoningResponse;
            ritual.triggerPossession(participants, new Demon(this.demonicPower));
        } else if (demonicPower < 7 && demonicPower <= 10){
            Demon newDemon = ritual.openDoorsToHell();

            summonedAction = "A hideous create suddenly appears and says to you directly: " + summoningResponse;



        }

        return summonedAction;
    }


}
