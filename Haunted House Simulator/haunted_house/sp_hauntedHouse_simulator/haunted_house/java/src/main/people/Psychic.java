package main.people;

import main.ContactDeadApproach;
import main.spirits.Demon;
import main.spirits.Spirit;

import java.util.ArrayList;
import java.util.List;

public class Psychic extends Person{

    private int psychicStrength = 0;
    private List<Resident> clients = new ArrayList<>();
    private String helpType = "psychic";

    public Psychic(Person person) {
    }


    public void setPsychicStrength(int psychicStrength) {
        this.psychicStrength = psychicStrength;
    }

    public int getPsychicStrength() {
        return this.psychicStrength;
    }

    public Psychic(String name, int age, int skepticismLevel, int psychicStrength) {
        super(name, age, skepticismLevel);
        this.psychicStrength = psychicStrength;
    }

    public Psychic(int psychicStrength) {
        this.psychicStrength = psychicStrength;
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

    public void talkToSpirits(String contactChannel, Spirit channelingSpirit, List<Person> clients, String message){

        int contactStrength = getPsychicStrength();
        ContactDeadApproach approachMethod = new ContactDeadApproach();

        approachMethod.setApproachMethod(contactChannel);
        approachMethod.contactDead(contactStrength, message, channelingSpirit, clients, approachMethod.getApproachMethod());

        if (getPsychicStrength()< 3 && getPsychicStrength() >= 0){
            approachMethod.changeSkepticism(clients, 2);

        } else if (getPsychicStrength() >=4 && getPsychicStrength() <= 7){
            approachMethod.changeSkepticism(clients, 5);
            int range = clients.size();
            int randomizer = (int)(Math.random() * range);
            for (int i = randomizer; i < clients.size(); i++ ){
                clients.get(i).isPossessed();
                System.out.println("Well looks like " + clients.get(i).getName() + " is now the one channeling the spirit. Great work.");
                System.out.println(clients.get(i).getName() + " is now ");
                System.out.print(clients.get(i).possessedAction(true));
            }

        } else if (getPsychicStrength() > 7 && getPsychicStrength() <= 10){
            approachMethod.changeSkepticism(clients, 8);
            if(channelingSpirit.getHatredOfHumansLevel() < 6){
                approachMethod.helpSpirit(channelingSpirit);
            }
            if (channelingSpirit.getHatredOfHumansLevel() > 6 && channelingSpirit.getHatredOfHumansLevel() <= 10){
                Demon demon = approachMethod.openDoorsToHell();
                System.out.println("Well damn, looks like we're now dealing with a FREAKING DEMON in the house. Thanks a lot!!");
                System.out.println(demon.beSummoned(clients));

                System.out.println("Maybe we should call a priest at this point..");
            }
        }
    }

    public void consultClients(List<Resident> clients){
        this.clients = clients;


        for (Person client : clients){
            if (client.getAge() < 13){
                System.out.printf(this.name + " says: '%s, you must be very careful sweetheart.. the spirit(s) may try to contact you since your imagination is still so fresh. \n", client.getName());

            } else if (client.getAge() > 13 && client.getAge() < 21){
                System.out.printf(this.name + " says: '%s, you must watch out for strange urges.. malevolent spirits pray on the raw emotion of people your age. \n", client.getName());


            } else if (client.getAge() > 21 && client.getAge() < 65 ){
                System.out.printf(this.name + " says: '%s, you must resist the urge to ignore the activity. Something very bad could happen if you aren't careful \n", client.getName());

            }
            else if (client.getAge() > 65 && client.getAge() < 100){
                System.out.printf(this.name + " says: '%s, you must get out of this house at all cost. Your age means you are closer to the entrance to the spirit realm " +
                        "and therefore they may try and take your body to enter this world! \n", client.getName());
            }
        }
    }

}
