package main.people;

public abstract class Person {

    String name;
    private int age;

    // 0-10 inclusive
    private int skepticismLevel;

    private boolean isPossessed = false;
    // have this actually do something
    private String possessedAction;
    private String helpType;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSkepticismLevel() {
        return skepticismLevel;
    }

    public void setSkepticismLevel(int skepticismLevel) {
        this.skepticismLevel = skepticismLevel;
    }

    public boolean isPossessed() {
        return isPossessed;
    }

    public void setPossessed(boolean possessed) {
        isPossessed = possessed;
    }

    public void setPossessedAction(String possessedAction) {
        this.possessedAction = possessedAction;
    }

    public String getPossessedAction() {
        return possessedAction;
    }

    public String getGetHelpType() {
        return helpType;
    }

    public void setGetHelpType(String getHelpType) {
        this.helpType = getHelpType;
    }

    public Person(String name, int age, int skepticismLevel) {
        this.name = name;
        this.age = age;
        this.skepticismLevel = skepticismLevel;
    }

    public Person() {
    }

    public String scareReaction(){

        String scareAction = "";

        if (skepticismLevel < 5){
            scareAction = this.name + " panics and accidentally locks themself in the basement, in the dark. They begin to scream hysterically.";
        }
        if (skepticismLevel == 5){
            scareAction = this.name + " goes to the computer and starts googling info about the house.";
        }
        if (skepticismLevel > 5 && skepticismLevel <= 10){
            scareAction = this.name + " looks up from the phone for a moment and shrugs. Must be the wind.";
        }
        return scareAction;
    }

    public String possessedAction(boolean isPossessed){

        String possessionProof = "";
         if (isPossessed == true){
             possessionProof = this.possessedAction;
        }
        return possessionProof;
    }


}
