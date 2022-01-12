package main;

import main.people.Person;
import main.spirits.Demon;
import main.spirits.Poltergeist;
import main.spirits.Spirit;

import java.util.*;

public class ContactDeadApproach {


    protected  List<Person> participants;
    protected String approachMethod;
    protected List<String> genericMessages = new ArrayList<>(Arrays.asList("Hello", "goodbye", "sjahsa", "yes", "no", "help"));
    private Map<String, String> personalizedMessages = new HashMap<>();
    private String spectacularAction;
    private String mediocreAction;

    public String getApproachMethod() {
        return this.approachMethod;
    }

    public void setApproachMethod(String approach) {
        this.approachMethod = approach;
    }

    public List<String> getGenericMessages() {
        return genericMessages;
    }

    public void setGenericMessages(List<String> genericMessages) {
        this.genericMessages = genericMessages;
    }

    public Map<String, String> getPersonalizedMessages() {
        return personalizedMessages;
    }

    public void setPersonalizedMessages(Map<String, String> personalizedMessages) {
        this.personalizedMessages = personalizedMessages;
    }

    public String getSpectacularAction() {
        return spectacularAction;
    }

    public void setSpectacularAction(String spectacularAction) {
        this.spectacularAction = spectacularAction;
    }

    public String getMediocreAction() {
        return mediocreAction;
    }

    public void setMediocreAction(String mediocreAction) {
        this.mediocreAction = mediocreAction;
    }

    public List<Person> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Person> participants) {
        this.participants = participants;
    }

    public ContactDeadApproach (String approachMethod, List<Person> participants){
        this.approachMethod = approachMethod;
        this.participants = participants;
    }

    public ContactDeadApproach() {}


    //METHODS

    public void contactDead(int contactStrength, String sendMessage, Spirit spiritToReach, List<Person> participants, String approachMethod){
        int range = genericMessages.size() + personalizedMessages.size();
        int randomizer = (int)(Math.random() * range);

        if(contactStrength > 10 || contactStrength < -1){
            System.out.println("The strength you provided is off the charts (and unfortunately doesn't work). " +
                    "Please provide a number between 0 - 10");
        }

        int numberOfAttempts = 0;

//        was wrapped in a while loop - trying it w/o
            // have this be more specific? Something to consider..
            if(contactStrength < 4){
                System.out.println(genericMessages.get(randomizer));
                numberOfAttempts++;
                changeSkepticism(participants, 1);

                if(numberOfAttempts > 4){
                    System.out.println(personalizedMessages.get(randomizer));
                    System.out.println(getMediocreAction());
                    changeSkepticism(participants, 2);
                }
            }

            if(contactStrength < 7 && contactStrength >= 5){

                if(approachMethod.equalsIgnoreCase("ouija")){
                    System.out.println("They begin to use the ouija board. " + getSpectacularAction());

                    System.out.println("\nWOW!!!! Everyone in the room is stunned. Suddenly the ouija board spells out:");
                    System.out.println(spiritToReach.getName() + " " + spiritToReach.leaveClues(sendMessage));
                    numberOfAttempts++;
                    changeSkepticism(participants, 3);

                } else if (approachMethod.equalsIgnoreCase("seance")){

                    System.out.println("Candles are lit, the table is set. They begin the seance. \nWOW!!!! Everyone in the room is stunned. Suddenly everyone hears: ");
                    System.out.println(spiritToReach.getName() + " " + spiritToReach.leaveClues(sendMessage));
                    numberOfAttempts++;
                    changeSkepticism(participants, 8);

                } else if (approachMethod.equalsIgnoreCase("Exorcism")){

                    System.out.println("Crucifixes are hung, the holy water is out. They begin the exorcism. \nWOW!!!! Everyone in the room is stunned. Suddenly the person being exorcised shouts in a low, inhuman voice:");
                    System.out.println(spiritToReach.getName() + " " + spiritToReach.leaveClues(sendMessage));
                    numberOfAttempts++;
                    changeSkepticism(participants, 6);

                } else if (approachMethod.contains("paranormal technology") || approachMethod.contains("video")){

                    System.out.println("The cameras have been recording for 24 hours. The footage is reviewed. \nWOW!!!! Everyone in the room is stunned. Suddenly the video footage shows a shadowy figure and then cuts to a scene of: "); //this needs to be tweaked since it wouldn't be a message

                    sendMessage = "VIDEO";

                    System.out.println(spiritToReach.leaveClues(sendMessage));
                    numberOfAttempts++;
                    changeSkepticism(participants, 5);

                } else if (approachMethod.contains("paranormal technology") || approachMethod.contains("audio")){

                    System.out.println("Everyone is sitting around the live audio recording machinery. WOW!!!! Everyone in the room is stunned. Suddenly the recording gets clear and a hardly audible voice say though the static: ");
                    System.out.println(spiritToReach.getName() + " " + spiritToReach.leaveClues(sendMessage));
                    numberOfAttempts++;
                    changeSkepticism(participants, 4);
                }
            }

            if(contactStrength < 10 && contactStrength >= 7 && numberOfAttempts > 5){
                // get direct message from spirit needed to give them peace -- trigger some reaction with the spirit class... but how?

                if(spiritToReach.getHatredOfHumansLevel() > 8){
                    triggerPossession(participants, spiritToReach);
                } else {
                    helpSpirit(spiritToReach);
                }
            }

            if (sendMessage.contains("devil") || sendMessage.contains("hell") || sendMessage.contains("satan") ||  sendMessage.contains("Satan") || sendMessage.contains("summon")){
                openDoorsToHell();
            }
    }

    public void changeSkepticism(List<Person> participants, int believabilityRating){
        for (Person participant : participants){
            if(believabilityRating <= 2 && (!participant.getGetHelpType().equals("priest") && !participant.getGetHelpType().equals("psychic"))){
                participant.setSkepticismLevel(+2);
                System.out.println(participant.getName() + " says: wow yeah, that was really convincing. Maybe it was just the wind after all.");

            } else if (believabilityRating >=3 && believabilityRating <= 7 && (!participant.getGetHelpType().equals("priest") && !participant.getGetHelpType().equals("psychic"))){
                participant.setSkepticismLevel(-1);
                System.out.println(participant.getName() + " says: Um okay... that is a little spooky. Maybe there are some spirits lurking here.. ");

            } else if (believabilityRating >= 8 && believabilityRating <= 10 && (!participant.getGetHelpType().equals("priest") && !participant.getGetHelpType().equals("psychic"))){
                participant.setSkepticismLevel(-3);
                System.out.println(participant.getName() + " says: HOLY SMOKES!! This isn't a joke after all.. Maybe we need to get out of here!");
            }
        }



    }

    public Demon openDoorsToHell(){
        System.out.println("Oh my god..... look what you have done....");
        System.out.println("The floor board begin to buckle and break apart. A hole opens up as flames shoot from the ground.");

        int range = 10;
        int randomizer = (int)(Math.random() * range);
        Demon newDemon = new Demon(randomizer);

        return newDemon;

    }

    public void helpSpirit(Spirit currentSpirit){

        Spirit reachingCurrentSpirit = currentSpirit;

        System.out.printf("You've been given some valuable information to help %s find peace. " +
                "Their wish is: ", currentSpirit.getName());
        System.out.println(reachingCurrentSpirit.discoverWish());
        currentSpirit.setPeacefulnessLevel(+3);
        currentSpirit.setHatredOfHumansLevel(-2);

        currentSpirit.restInPeace();

    }

    public void triggerPossession(List<Person> participants, Spirit possessorSpirit){

        int range = participants.size();
        int randomizer = (int)(Math.random() * range);
        for (int i = randomizer; i < participants.size(); i++ ){
            if (participants.get(i).getSkepticismLevel() < 5){
                System.out.println("OH GOD.. OH NO...");
                System.out.println("It appears as if " + participants.get(i).getName() + "'s eyes are glazed over.. they seem really out of it");
                System.out.println("You hear a really weird growling noise escape their throat and.. then they start to speak...");
                System.out.println("'" + possessorSpirit.getDefaultMessage() + "'");
                System.out.println("");
                System.out.println("");
                System.out.println("I am sorry but this is no longer " + participants.get(i).getName() + "... This is " + possessorSpirit.getName() +
                        "using this body as a vessel.");
                participants.get(i).setPossessed(true);

                System.out.println(participants.get(i).possessedAction(true));

            }
        }
    }

    public void contactDead(int contactStrength, String message, Demon channelingSpirit, List<Person> clients, ContactDeadApproach method) {
        Spirit demon = (Spirit) channelingSpirit;
        contactDead( contactStrength, message,channelingSpirit, clients, method);
    }

    public void contactDead(int contactStrength, String message, Poltergeist channelingSpirit, List<Person> clients, ContactDeadApproach method) {
        Spirit poltergeist = (Spirit) channelingSpirit;
        contactDead( contactStrength, message,channelingSpirit, clients, method);
    }
}
