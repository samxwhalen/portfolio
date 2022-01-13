package main.spirits;

import main.people.Person;
import main.people.Resident;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Poltergeist extends Spirit {

    private String spiritType = "poltergeist";
    private List<String> terrorizingActions = new ArrayList<>(Arrays.asList("CRASSHHHHH!!!! All of the dishes in the cabinets have flown out and are shattered on the floor!",
            "A red slap mark appears on someone's face when they wake up in the morning.", "Everyone in the house has bite marks that appear on their ankle...",
            "A hot mug of coffee levitates from the table and is thrown at the wall.", "ALL OF THE FURNITURE IS BLOCKING THE DOOR OUT OF THE HOUSE!!!", "The tv keep turning on when nobody is in the room. It always is on a static screen."));


    public Poltergeist(String name, int ageAtDeath, String causeOfDeath, boolean bodyOnPremise) {
        super(name, ageAtDeath, causeOfDeath, bodyOnPremise);
        setPhysicalForm("a hideous human-like creature crawling on the floor.");
    }

    public Poltergeist() {
    }

    public String getSpiritType() {
        return spiritType;
    }

    public List<String> getTerrorizingActions() {
        return terrorizingActions;
    }

    // this method may get weird - layering classes may not work in this way
    public Poltergeist speakWithHuman(Poltergeist currentPoltergeist){
        Spirit transcendingRealms = currentPoltergeist;
        return currentPoltergeist;
    }

    public void terrorize(List<Resident> currentResidents){
        for(Person resident : currentResidents){

            int range = terrorizingActions.size();
            int randomizer = (int)(Math.random() * range);

            for (int i = randomizer; i < terrorizingActions.size(); i++ ){
                System.out.println(terrorizingActions.get(i));

                if(resident.getSkepticismLevel() < 6){
                    resident.setSkepticismLevel(-1);
                    resident.scareReaction();

                    System.out.println("While in the basement, " + resident.getName() + " has their head yanked back by their hair. They begin screaming more frantically and clawing at the basement door.");

                }

                resident.setSkepticismLevel(-1);
                resident.scareReaction();

            }

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Poltergeist)) return false;
        Poltergeist that = (Poltergeist) o;
        return Objects.equals(terrorizingActions, that.terrorizingActions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(terrorizingActions);
    }
}
