package com.didarproj.gymtracker.model;

public class Item {


    private int id;
    private String gymType;
    private String gymWorkOut;
    private  int gymSet;
    private int gymWeights;
    private String dateGymAdded;

    public Item() {
    }

    public Item(String gymType, String gymWorkOut, int gymSet, int gymWeights, String dateGymAdded) {
        this.gymType = gymType;
        this.gymWorkOut = gymWorkOut;
        this.gymSet = gymSet;
        this.gymWeights = gymWeights;
        this.dateGymAdded = dateGymAdded;
    }


    public Item(int id, String gymType, String gymWorkOut, int gymSet, int gymWeights, String dateGymAdded) {
        this.id = id;
        this.gymType = gymType;
        this.gymWorkOut = gymWorkOut;
        this.gymSet = gymSet;
        this.gymWeights = gymWeights;
        this.dateGymAdded = dateGymAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGymType() {
        return gymType;
    }

    public void setGymType(String gymType) {
        this.gymType = gymType;
    }

    public String getGymWorkOut() {
        return gymWorkOut;
    }

    public void setGymWorkOut(String gymWorkOut) {
        this.gymWorkOut = gymWorkOut;
    }

    public int getGymSet() {
        return gymSet;
    }

    public void setGymSet(int gymSet) {
        this.gymSet = gymSet;
    }

    public int getGymWeights() {
        return gymWeights;
    }

    public void setGymWeights(int gymWeights) {
        this.gymWeights = gymWeights;
    }

    public String getDateGymAdded() {
        return dateGymAdded;
    }

    public void setDateGymAdded(String dateGymAdded) {
        this.dateGymAdded = dateGymAdded;
    }
}
