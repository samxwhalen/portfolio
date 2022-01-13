package main.spirits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ghost extends Spirit {


    private String spiritType = "ghost";
    private List<String> oldHabits = new ArrayList<>(Arrays.asList("You hear rummaging through the closet",
            "A cabinet slams in the other room", "Papers are is disarray on the desk",
            "Where did this old photo come from?", "It smells like someone is frying some onions..."));

    public Ghost(Spirit spirit) {
    }

    public String getSpiritType() {
        return spiritType;
    }

    public List<String> getOldHabits() {
        return oldHabits;
    }

    public void setOldHabits(List<String> oldHabits) {
        this.oldHabits = oldHabits;
    }

    public Ghost(String name, int ageAtDeath, String causeOfDeath, boolean bodyOnPremise) {
        super(name, ageAtDeath, causeOfDeath, bodyOnPremise);
        setPhysicalForm("an unknown person pacing in the room");

    }

    public Ghost() {
    }

    public String shakeChains(){
        return "CLICK CLANK CLICK CLANK - you hear some chains rattling..";
    }

    public String followFamiliarRoutine(){
        int range = oldHabits.size();
        int randomizer = (int)(Math.random() * range);
        String familiarHabit = "";
        for (int i = randomizer; i < oldHabits.size(); i++ ){
            return familiarHabit = oldHabits.get(i);
        }

        return familiarHabit;
    }

    // this method may get weird - layering classes may not work in this way
    public Spirit speakWithHuman(Spirit currentGhost){
        Spirit transcendingRealms = currentGhost;
        return currentGhost;
    }
}
