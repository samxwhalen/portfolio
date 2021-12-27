package main;

import main.people.Person;
import main.people.Priest;
import main.people.Psychic;
import main.people.Resident;
import main.spirits.Demon;
import main.spirits.Ghost;
import main.spirits.Poltergeist;
import main.spirits.Spirit;

import java.util.*;

public class HauntedHouseApplication {


    public static void main(String[] args) {

        HauntedHouseApplication hauntedHouse = new HauntedHouseApplication();
        hauntedHouse.run();

    }

    public HauntedHouseApplication(){
    }

    public void run(){
        displayBanner();
        System.out.println("");
        String prompt = "";

        // Create the residents:
        List<Resident> currentResidents = null;
        try{
            prompt = "Anyway.. How many people live here with you?";
            currentResidents = moveIn(Integer.parseInt(getUserInput(prompt)));
        } catch (NumberFormatException nfe){
            System.out.println("You didn't enter a number. Try again.");
        }


       // Ways to reach the dead:
        List<ContactDeadApproach> contactDead = decideContactDeadMethods();

        // Outside help:
        List<Person> helpers = getHelp(contactDead);

        // Set the stage:
        System.out.println("");
        prompt = "How scary do you want the haunted house to be? CREEPY / SCARY / HORRIFYING"; // 3 paths depending on the number

        // Start the story:
        hauntedHouseOutcome(getUserInput(prompt), currentResidents, helpers, contactDead);

    }

    private String getUserInput(String prompt){
        System.out.print(prompt + " ---> ");
        return new Scanner(System.in).nextLine();
    }

    private void hauntedHouseOutcome(String choice, List<Resident> residents, List<Person> help, List<ContactDeadApproach> contactMethods){

        boolean restInPeace = false;
        boolean getHelp = false;
        int spiritPeace = 0;
        String prompt = "";

        List<ContactDeadApproach> approaches = contactMethods;
        List<Resident> currentResidents = residents;
        List<Person> outsideHelp = help;


        Map<String, String> specificMessages = new HashMap<>();

        List<Person> allParticipants = new ArrayList<>();
        for (Resident resident : residents){
            allParticipants.add(resident);
        }

        for (Person helper : help){
            allParticipants.add(helper);
        }

        // Create the spirits:
        List<Spirit> spirits = generateSpirits(choice);

        for (Spirit spirit : spirits){
            specificMessages.put(spirit.getName(), spirit.getDefaultMessage());
            specificMessages.put(spirit.getName(), spirit.getUnfinishedBusiness());
        }

        for (ContactDeadApproach method : approaches){
            method.setPersonalizedMessages(specificMessages);
            method.setParticipants(allParticipants);
        }

        while(restInPeace == false){


            if (choice.equalsIgnoreCase("creepy")){

                Ghost ghost1 = (Ghost) spirits.get(0);
                Ghost ghost2 = (Ghost) spirits.get(1);

                spiritPeace = spirits.get(0).getPeacefulnessLevel() + spirits.get(1).getPeacefulnessLevel() - 4 ;

                Psychic psychic = new Psychic(0);

                System.out.println(ghost1.haunt());

                System.out.println("Weird stuff has been happening in the house. \n");

                for (Resident resident : residents) {

                    System.out.println( resident.scareReaction());

                    System.out.println(ghost1.followFamiliarRoutine());
                    resident.setSkepticismLevel(-1);

                    System.out.println(ghost2.followFamiliarRoutine());
                }

                //this needs to be wrapped in a loop or tucked away in a method so that it doesn't keep triggering
                while (getHelp == false){
                    System.out.println("It's decided that it is time to consult someone who could help.\n");
                    int potentialHelp = help.size();

                    prompt = "Pick a number between 1 - " + potentialHelp;
                    Integer selection = Integer.parseInt(getUserInput(prompt));
                    if (selection <= 0 && selection > potentialHelp){
                        prompt = "Try Again.";
                        selection = Integer.parseInt(getUserInput(prompt));
                    }

                    if(selection == 1){
                        psychic = (Psychic) help.get(0);
                    } else if (selection == 2) {
                        psychic = (Psychic) help.get(1);
                    }
                    else if (selection == 3) {
                        psychic = (Psychic) help.get(2);
                    }

                    System.out.printf("\nYou phone %s, a %s. \n", psychic.getName(), psychic.getHelpType());
                    psychic.setClients(residents);

                    //have psychic consult clients
                    psychic.consultClients(residents);

                    // contact dead method
                    System.out.println("\nThe advice is severe. If there is going to be any peace in this house, drastic measures must be taken. \n");
                    System.out.printf("%s advises that everyone try to contact the spirit by means of: \n", psychic.getName());
                    for (ContactDeadApproach approach : approaches){
                        System.out.print(approach.getApproachMethod() + "\n");   // this may be weird
                    }

                    for (Resident resident : currentResidents) {
                        if (resident.getSkepticismLevel() <= 4){
                            System.out.printf("%s is totally on board. Whatever %s says, they will do! \n", resident.getName(), psychic.getName());
                        } if (resident.getSkepticismLevel() > 4 && resident.getSkepticismLevel() <= 7){
                            System.out.printf("%s is hesitant but doesn't see the harm in trying a different approach. They think %s seems a little full of it but they want to keep an open mind \n", resident.getName(), psychic.getName());
                        } if (resident.getSkepticismLevel() > 4 && resident.getSkepticismLevel() <= 7){
                            System.out.printf("%s is totally opposed to the idea. They want to know why they should believe a word %s says! It smells like a scam to them \n", resident.getName(), psychic.getName());
                        }
                    }

                    //this breaks the loop / stops it from being repeated each time they interact with spirits
                    getHelp = true;
                }

                int strengthOfContact = 0;

                if (psychic.getPsychicStrength() < 5){
                    strengthOfContact = psychic.getPsychicStrength() + 3;

                } else {
                    strengthOfContact = psychic.getPsychicStrength();
                }

                System.out.println("Let the contacting begin! \n ");
                for (ContactDeadApproach approach : approaches){

                    prompt = "What should their message to the spirit be? \n";
                    String message = getUserInput(prompt);

                    approach.contactDead(strengthOfContact, message, ghost1, allParticipants, approach.getApproachMethod());
                    spiritPeace = ghost1.getPeacefulnessLevel();

                    if (spiritPeace == 10){
                        restInPeace = true;
                    }
                }

                // copy and paste logic from above an tweak for these options - consider best way to layer spirits

            }else if (choice.equalsIgnoreCase("scary")){
                //terrorizingAction for poltergeist

            } else if (choice.equalsIgnoreCase("horrifying")){
                //terrorizingAction for poltergeist

            }
        }
        //the haunting loop:

        //prints out the story -- use while loop to track spirit peacefulness level


        //default interaction loop - basic haunting
        //provide a loop with user input for a more intense haunting
        //absolute chaos mode

    }


    private List<Spirit> generateSpirits(String choice){
        List<Spirit> spirits = new ArrayList<>();

        if (choice.equalsIgnoreCase("creepy")){

            Ghost ghost = new Ghost("Elizabeth", 10, "Strangled by unknown person", true);
            ghost.setOldHabits(new ArrayList<>( Arrays.asList ("a toy is seen moving in the middle of the room", "someone is giggling in the bathroom", "scratches of ink are found on an empty journal page", "nobody remembers putting these flowers on the table")));
            ghost.setPeacefulnessLevel(7);
            ghost.setHatredOfHumansLevel(2);
            ghost.setDefaultMessage("Will you play with me?");
            ghost.setUnfinishedBusiness("needs her bones to be found.");
            ghost.setWish("want to play with other children.");
            ghost.setManyMessages(new ArrayList<>(Arrays.asList("My mommy loved me!", "I was a bad little girl", "Are you a ghost?", "Can we play a game?", "I'm scared...")));


            spirits.add(ghost);

            Ghost anotherGhost = new Ghost("Dale", 57, "fell down the stairs", false);
            anotherGhost.setOldHabits(new ArrayList<>( Arrays.asList ("the sound of a beer being cracked open cuts though the house but there is no one else home..",
                    "the tv turns on again whenever it is turned off", "every night at 2am the sound of something tumbling down the stairs wakes the house up", "it suddenly smells like roast beef in here...")));
            anotherGhost.setPeacefulnessLevel(5);
            anotherGhost.setHatredOfHumansLevel(5);
            anotherGhost.setDefaultMessage("I'm watching the game!");
            anotherGhost.setUnfinishedBusiness("needs to find his high school varsity jersey");
            anotherGhost.setWish("want to crack a another cold one with the boys.");
            anotherGhost.setManyMessages(new ArrayList<>(Arrays.asList("Go get my wife! Tell her I am hungry.", "What happened to all of my beer?", "My neck is really killing me", "Are the birds on tonight?", "What are you doing in my house!?")));

            spirits.add(anotherGhost);

            

        } else if (choice.equalsIgnoreCase("scary")){

            Poltergeist poltergeist1 = new Poltergeist("Mary", 88, "unknown", true);
            poltergeist1.setPeacefulnessLevel(1);
            poltergeist1.setHatredOfHumansLevel(9);
            poltergeist1.setDefaultMessage("GIVE ME YOUR BODY!");
            poltergeist1.setUnfinishedBusiness("needs to steal a new human body to occupy.");
            poltergeist1.setWish("wants to steal your body and use it as her own so she may have a second shot at life.");
            poltergeist1.setManyMessages(new ArrayList<>(Arrays.asList("Sleep tight tonight.", "What soft skin you have...", "You will not get out of here alive.", "How will you ever defeat me?", "YOUR BODY IS MINE!")));


            spirits.add(poltergeist1);

            Poltergeist poltergeist2 = new Poltergeist("Sampford Peverell", 60, "shot to death by firing squad for a crime", false);
            poltergeist2.setPeacefulnessLevel(4);
            poltergeist2.setHatredOfHumansLevel(7);
            poltergeist2.setDefaultMessage("LEAVE NOW!");
            poltergeist2.setUnfinishedBusiness("scare every new resident until the house is empty");
            poltergeist2.setWish("wants his house back.");
            poltergeist2.setManyMessages(new ArrayList<>(Arrays.asList("Why are you in my house?", "If you stay, I will destroy you.", "You will never know peace while you are here.", "Fear me!")));

            spirits.add(poltergeist2);

        } else if (choice.equalsIgnoreCase("horrifying")){

            Poltergeist poltergeist = new Poltergeist("Tina Resch", 26, "starvation", true); // fill out
            poltergeist.setPeacefulnessLevel(1);
            poltergeist.setHatredOfHumansLevel(10);
            poltergeist.setDefaultMessage("You don't want to know what I am going to do to you...");
            poltergeist.setUnfinishedBusiness("harass whoever comes in contact with her ghostly form.");
            poltergeist.setWish("to make everyone's life a living hell.");
            poltergeist.setManyMessages(new ArrayList<>(Arrays.asList("If you stay here, you will die.", "GET OUT OF THIS HOUSE!!!", "The weakest of you will do my bidding.", "I am always with you.", "I have nothing but hatred for you.")));

            spirits.add(poltergeist);

            Demon demon = new Demon (7, "your infant child", "hahaha!! I AM STRONGER NOW!", "Can you provide me with fresh, young blood?", true);
            demon.setName("Lamia");
            demon.setDefaultMessage(demon.getSummoningResponse());
            demon.setHatredOfHumansLevel(7);
            demon.setAgeAtDeath(836452);
            demon.setCauseOfDeath("Lamia never dies.");
            demon.setPeacefulnessLevel(2);
            demon.setUnfinishedBusiness("collect the souls of young children.");
            demon.setWish("to truly not be summoned so that they do not have to collect child souls.");
            demon.setManyMessages(new ArrayList<>(Arrays.asList("Why did you call on me?!", "Your child is mine.", "I will rip future children from the womb that is connected to you.", "For what it's worth, I don't want to do this.", "GIVE ME A CHILD!!")));

        }

        return spirits;
    }

    public List<Person> getHelp(List<ContactDeadApproach> desiredMethod){
        List<Person> helpers = new ArrayList<>();
        for (ContactDeadApproach method : desiredMethod){
            if (method.approachMethod.contains("seance") || method.approachMethod.contains("paranormal")){
                int range = 10;
                int randomizer = (int)(Math.random() * range);
                Psychic newPsychic = new Psychic(randomizer);

                if (randomizer >= 0 || randomizer <= 4){
                    newPsychic.setAge(55);
                    newPsychic.setPsychicStrength(randomizer);
                    newPsychic.setPossessedAction("jumps onto the person next to them and begins to claw feverishly at their face");
                    newPsychic.setName("Barbara");
                }
                if (randomizer >= 5 || randomizer <= 7){
                    newPsychic.setAge(60);
                    newPsychic.setPsychicStrength(randomizer);
                    newPsychic.setPossessedAction("ripping at her clothes and screaming an inhuman noise at a pitch that shatters glass");
                    newPsychic.setName("Jon");
                }
                if (randomizer >= 8 || randomizer <= 10){
                    newPsychic.setAge(48);
                    newPsychic.setPsychicStrength(randomizer);
                    newPsychic.setPossessedAction("full consumed by the spirits. Eyes are rolled back into head and blood drips from their nose");
                    newPsychic.setName("Sylvia");
                }


                helpers.add(newPsychic);
            }

            if (method.approachMethod.contains("exorcism")){
                int range = 10;
                int randomizer = (int)(Math.random() * range);
                Priest priest = new Priest(randomizer);

                if (randomizer >= 0 || randomizer <= 4){
                    priest.setAge(26);
                    priest.setPossessedAction("robes become engulfed in flames yet he is laughing and his flesh does not burn");
                    priest.setName("Father Kyle");
                }
                if (randomizer >= 5 || randomizer <= 7){
                    priest.setAge(49);
                    priest.setPossessedAction("tries to bite the flesh of the youngest person present");
                    priest.setName("Father James");
                }
                if (randomizer >= 8 || randomizer <= 10){
                    priest.setAge(63);
                    priest.setPossessedAction("grabs at the crucifix around his neck, speaking in reversed latin");
                    priest.setName("Father William");
                }

                helpers.add(priest);
            }

        }

        return helpers;
    }


    public List<Resident> moveIn(int numberOfResidents){
        List<Resident> residents = new ArrayList<>();

        for (int i = 1; i <= numberOfResidents; i++){
            Resident newResident = new Resident();
            String prompt = "";

            prompt = "Give person " + i + " a name";
            String name = getUserInput(prompt);
            newResident.setName(name);
            System.out.println("");


            prompt = "Give this person an age:";
            int age = Integer.parseInt(getUserInput(prompt));
            newResident.setAge(age);
            System.out.println("");



            prompt = "How skeptical are they of spirits and ghosts? (1 - 10)";
            int skepticism = Integer.parseInt(getUserInput(prompt));

            if (skepticism < 0 || skepticism > 10) {
                prompt = "please enter in a number between 1 = 10";
                skepticism = Integer.parseInt(getUserInput(prompt));
                newResident.setSkepticismLevel(skepticism);
                System.out.println("");
            }

            newResident.setSkepticismLevel(skepticism);
            System.out.println("");

            prompt = "Hypothetically speaking, how would they act if they were possessed?";
            String possessedBehavior = getUserInput(prompt);
            newResident.setPossessedAction(possessedBehavior);

            residents.add(newResident);
            System.out.printf("You've added %s (%d years old, skeptic level: %d) to the house. \n", newResident.getName(), newResident.getAge(), newResident.getSkepticismLevel() );
            System.out.println("");

        }

        System.out.println("");
        return residents;
    }

    private List<ContactDeadApproach> decideContactDeadMethods(){

        List<ContactDeadApproach> contactMethods = new ArrayList<>();

        System.out.println("Here are the ways you could reach the dead: ");
        System.out.println("1. ouija board / no additional help");
        System.out.println("2. seance / with help from a psychic");
        System.out.println("3. exorcism / with help from a priest");
        System.out.println("4. paranormal technology / with or without help from a psychic");
        System.out.println("");
        System.out.println("You get to chose 2 potential methods.");

        for (int i = 0; i < 2; i++){
            String prompt = "Pick a method using the numerical value: ";
            String choice = getUserInput(prompt);
            if (choice.equals("1")){
                ContactDeadApproach ouijaBoard = new ContactDeadApproach();
                ouijaBoard.setApproachMethod("ouija");
                ouijaBoard.setSpectacularAction("The planchette begins to wildly spell out something! No one is touching it! Quick, someone grab some record this!");
                ouijaBoard.setMediocreAction("It feels like one of us is forcefully moving it.. stop moving it!");
                contactMethods.add(ouijaBoard);
            }

            if (choice.equals("2")){
                ContactDeadApproach seance = new ContactDeadApproach();
                seance.setApproachMethod("seance");
                seance.setSpectacularAction("Everything on the table begins to levitate right before all of the lights go out. A glow begins to form above an empty chair!");
                seance.setMediocreAction("Absolutely nothing is happening. You can hear someone's stomach gurgle in the silence.");
                contactMethods.add(seance);
            }

            if (choice.equals("3")){
                ContactDeadApproach exorcism = new ContactDeadApproach();
                exorcism.setApproachMethod("exorcism");
                exorcism.setSpectacularAction("All the windows in the house shatter at once! The priest keeps yelling and the possessed person's head begins to spin around on their neck!");
                exorcism.setMediocreAction("The possessed person continues on with their shenanigans while the priest clumsily thumbs through some pages of the bible, mumbling under his breath.");
                contactMethods.add(exorcism);
            }

            if (choice.equals("4")){
                ContactDeadApproach paranormalTechnology = new ContactDeadApproach();
                prompt = "audio or visual?";
                String kind = getUserInput(prompt);

                if (kind.isEmpty() || !"audio".equals(kind) && !"visual".equals(kind) ){
                    prompt = "You need to enter in one or the other: audio or visual?";
                    kind = getUserInput(prompt);
                }

                paranormalTechnology.setApproachMethod("paranormal activity " + kind);
                if (kind.equals("audio")){
                    paranormalTechnology.setSpectacularAction("This is clearly an inhuman voice speaking. Everyone has chills and listens closely.");
                    paranormalTechnology.setMediocreAction("Static, just static. Someone claims to have heard a voice but everyone is pretty underwhelmed at this point.");
                } else if (kind.equals("video")){
                    paranormalTechnology.setSpectacularAction("a figure appears and walks towards the camera.. they look like a corpse.. they keep coming towards the screen until someone yells 'TURN IT OFF!'");
                    paranormalTechnology.setMediocreAction("Nothing out of the ordinary was captured except a dark spot that could or could not be a shadow.");
                }

                contactMethods.add(paranormalTechnology);
            }
        }

        System.out.println("You have chosen:");
        for (ContactDeadApproach method : contactMethods){
            System.out.println(method.approachMethod);
        }

       return contactMethods;

    }

    private void displayBanner(){

        System.out.println("");
        System.out.println("");
        System.out.println("CONGRATULATIONS! YOU JUST BOUGHT A HOUSE!! ");
        System.out.println();


        System.out.println(" .     .\n" +
                "                               !!!!!!!\n" +
                "                       .       [[[|]]]    .\n" +
                "                       !!!!!!!!|--_--|!!!!!\n" +
                "                       [[[[[[[[\\_(X)_/]]]]]\n" +
                "               .-.     /-_--__-/_--_-\\-_--\\\n" +
                "               |=|    /-_---__/__-__-_\\__-_\\\n" +
                "           . . |=| ._/-__-__\\===========/-__\\_\n" +
                "           !!!!!!!!!\\========[ /]]|[[\\ ]=====/\n" +
                "          /-_--_-_-_[[[[[[[[[||==  == ||]]]]]]\n" +
                "         /-_--_--_--_|=  === ||=/^|^\\ ||== =|\n" +
                "        /-_-/^|^\\-_--| /^|^\\=|| | | | ||^\\= |\n" +
                "       /_-_-| | |-_--|=| | | ||=|_|_|=||\"|==|\n" +
                "      /-__--|_|_|_-_-| |_|_|=||______=||_| =|\n" +
                "     /_-__--_-__-___-|_=__=_.`---------'._=_|__\n" +
                "    /-----------------------\\===========/-----/\n" +
                "   ^^^\\^^^^^^^^^^^^^^^^^^^^^^[[|]]|[[|]]=====/\n" +
                "       |.' ..==::'\"'::==.. '.[ /~~~~~\\ ]]]]]]]\n" +
                "       | .'=[[[|]]|[[|]]]=`._||==  =  || =\\ ]\n" +
                "       ||== =|/ _____ \\|== = ||=/^|^\\=||^\\ ||\n" +
                "       || == `||-----||' = ==|| | | |=|| |=||\n" +
                "       ||= == ||:^s^:|| = == ||=| | | || |=||\n" +
                "       || = = ||:___:||= == =|| |_|_| ||_|=||\n" +
                "      _||_ = =||o---.|| = ==_||_= == =||==_||_\n" +
                "      \\__/= = ||:   :||= == \\__/[][][][][]\\__/\n" +
                "      [||]= ==||:___:|| = = [||]\\\\//\\\\//\\\\[||]\n" +
                "      }  {---'\"'-----'\"'- --}  {//\\\\//\\\\//}  {\n" +
                "    __[==]__________________[==]\\\\//\\\\//\\\\[==]_\n" +
                "   |`|~~~~|================|~~~~|~~~~~~~~|~~~~||\n" +
                "   |^| ^  |================|^   | ^ ^^ ^ |  ^ ||\n" +
                "  \\|//\\\\/^|/==============\\|/^\\\\\\^/^.\\^///\\\\//|///\n" +
                " \\\\///\\\\\\//===============\\\\//\\\\///\\\\\\\\////\\\\\\/////\n" +
                " \"\"'\"\"'\"\"\".'..'. ' '. ''..'.\"\"'\"\"'\"\"'\"\"''\"''\"''\"\"");



            System.out.println();
            System.out.println(" IT's PRETTY OLD AND CREEPY. ");
    }



}
