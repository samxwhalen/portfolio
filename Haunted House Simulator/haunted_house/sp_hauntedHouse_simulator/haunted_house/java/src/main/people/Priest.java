package main.people;

import main.ContactDeadApproach;
import main.spirits.Demon;
import main.spirits.Ghost;
import main.spirits.Spirit;

import java.util.ArrayList;
import java.util.List;

public class Priest extends Person {

    private int holiness = 3;
    private List<Resident> clients = new ArrayList<>();
    private String helpType = "priest";

    public Priest() {

    }

    public int getHoliness() {
        return holiness;
    }

    public Priest(String name, int age, int skepticismLevel, int holiness, List<Resident> clients) {
        super(name, age, skepticismLevel);
        this.holiness = holiness;
        this.clients = clients;
    }

    public Priest(int holiness){
        this.holiness = holiness;
    }

    public String getHelpType() {
        return helpType;
    }

    public List<Resident> getClients() {
        return clients;
    }

    public void setClients(List<Resident> clients) {
        this.clients = clients;
    }


    public void releasesDemon(Demon channelingSpirit, List<Person> clients, String message){

        ContactDeadApproach approachMethod = new ContactDeadApproach();

        int contactStrength = getHoliness();
        approachMethod.setApproachMethod("exorcism");
        approachMethod.contactDead(contactStrength, message, channelingSpirit, clients, approachMethod);

        // generates a random number to impact outcome
        int range = 10;
        int randomNumber = (int)(Math.random() * range);

        if (getHoliness() < 3 && getHoliness() >= 0){
            approachMethod.changeSkepticism(clients, 2);
            prayForSins(channelingSpirit, clients);

            if (randomNumber == 6){
                Demon newDemon = approachMethod.openDoorsToHell();
                releasesDemon(newDemon, clients, "We send you back to satan !");
            }

            if (randomNumber < 3){
                for (int i = randomNumber; i < clients.size(); i++){
                    System.out.println(channelingSpirit.stealSoul(clients.get(i)));
                }
            }

        } else if (getHoliness() >=4 && getHoliness() <= 7){
            approachMethod.changeSkepticism(clients, 5);

            System.out.println(channelingSpirit.beSummoned(clients));

            if (randomNumber == 8){
                int differentRange = clients.size();
                int randomizer = (int)(Math.random() * differentRange);
                for (int i = randomizer; i < clients.size(); i++ ){
                    clients.get(i).isPossessed();
                    System.out.println("Well looks like " + clients.get(i).getName() + " is now also possessed by a demon. Great work.");
                    System.out.println(clients.get(i).getName() + " is now ");
                    System.out.print(clients.get(i).possessedAction(true));
                }

            }


        } else if (getHoliness() > 7 && getHoliness() <= 10){
            approachMethod.changeSkepticism(clients, 8);
            if(channelingSpirit.getDemonicPower() <= 6){
                approachMethod.helpSpirit(channelingSpirit);
                channelingSpirit.setDemonicPower(-5);
                for (Person client : clients){
                    if (client.isPossessed()){
                        client.setPossessed(false);
                        System.out.println(client.getName() + "'s soul has been released!!");
                    }
                }
            }
            if (channelingSpirit.getDemonicPower() > 6 && channelingSpirit.getDemonicPower() <= 10){
                channelingSpirit.setDemonicPower(-3);
                Demon additionalDemon = approachMethod.openDoorsToHell();

                additionalDemon.setDemonicPower(randomNumber);

                // this may not work but is worth a shot
                releasesDemon(additionalDemon, clients, "GET OUT OF HERE NOW!");

            }
        }


    }

    public void prayForSins(Spirit ghost, List<Person> people){

        for (Person client : clients){
            setSkepticismLevel(+1);
            System.out.println(client.getName() + " feels a little bit more assured.. maybe? yeah, maybe a little bit.");
        }

        ghost.setPeacefulnessLevel(+1);

    }



}
