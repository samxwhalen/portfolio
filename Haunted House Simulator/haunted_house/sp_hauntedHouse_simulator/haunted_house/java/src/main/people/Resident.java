package main.people;

import main.spirits.Spirit;

import java.sql.SQLOutput;

public class Resident extends Person {

    private boolean isHomeowner;

    public Resident(String name, int age, int skepticismLevel, boolean isHomeowner) {
        super(name, age, skepticismLevel);
        this.isHomeowner = isHomeowner;
    }

    public Resident() {
    }

    public void sellHouse(){
        if(isHomeowner == true){
            System.out.println("The family has had enough! They're putting the house on the market and have decided to stay at a hotel. THE END.");
            //closes the scanner, somehow?
        }
    }

    public void doubleDown(Spirit spirit){
        System.out.println("The owners of the house don't care what shenanigans are taking place! They're hellbent on staying!!");
        spirit.setPeacefulnessLevel(-2);
        spirit.setHatredOfHumansLevel(+1);
    }
}
