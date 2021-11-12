package main.java.com;

import java.util.ArrayList;
import java.util.List;

public class Color {

    protected String hue;

    protected List<String> tints = new ArrayList<>();
    // a list of related shades/associated colors

    protected List<String> generalAssociations = new ArrayList<>();
    protected List<String> symbolism = new ArrayList<>();

    protected List<String> personalAssociations = new ArrayList<>();
    protected List<String> personalSignificance = new ArrayList<>();
    protected List<String> personalUsage = new ArrayList<>();

    //GETTERS & SETTERS

    public List<String> getTints() {
        return tints;
    }
    public void setTints(List<String> tints) {
        this.tints = tints;
    }

    public String getHue() {
        return hue;
    }
    public void setHue(String hue) {
        this.hue = hue;
    }

    public List<String> getGeneralAssociations() {
        return generalAssociations;
    }
    public void setGeneralAssociations(List<String> generalAssociations) {
        this.generalAssociations = generalAssociations;
    }

    public List<String> getSymbolism() {
        return symbolism;
    }
    public void setSymbolism(List<String> symbolism) {
        this.symbolism = symbolism;
    }

    public List<String> getPersonalAssociations() {
        return personalAssociations;
    }
    public void setPersonalAssociations(List<String> personalAssociations) {
        this.personalAssociations = personalAssociations;
    }

    public List<String> getPersonalSignificance() {
        return personalSignificance;
    }
    public void setPersonalSignificance(List<String> personalSignificance) {
        this.personalSignificance = personalSignificance;
    }

    public List<String> getPersonalUsage() {
        return personalUsage;
    }
    public void setPersonalUsage(List<String> personalUsage) {
        this.personalUsage = personalUsage;
    }

    //CONSTRUCTORS

    public Color(){}

    //basic constructor for user entering in personal info to the library
    public Color(String hue, List<String> personalAssociations, List<String> personalSignificance, List<String> personalUsage){
        this.hue = hue;
        this.personalAssociations = personalAssociations;
        this.personalSignificance = personalSignificance;
        this.personalUsage = personalUsage;
    }

    //a more complete constructor is someone wants to enter a brand new color
    public Color(String hue, List<String> personalAssociations, List<String> personalSignificance, List<String> personalUsage, List<String> generalAssociations, List<String> symbolism){
        this.hue = hue;
        this.personalAssociations = personalAssociations;
        this.personalSignificance = personalSignificance;
        this.personalUsage = personalUsage;
        this.generalAssociations = generalAssociations;
        this.symbolism = symbolism;
    }

}

