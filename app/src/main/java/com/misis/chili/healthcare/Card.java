package com.misis.chili.healthcare;

import java.io.Serializable;

/**
 * Created by Chili on 15.11.2017.
 */

public class Card  implements Serializable {
    int image;
    String name, status, bpm, temp, steps;
    int inhaler;
    public int getInhaler(){
        return this.inhaler;
    }
    public void setInhaler(int in){
        this.inhaler = in;
    }
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getBpm()
    {
        return this.bpm;
    }
    public void setBpm(String bpm)
    {
        this.bpm = bpm;
    }
    public String getTemp()
    {
        return this.temp;
    }
    public void setTemp(String temp)
    {
        this.temp = temp;
    }
    public String getSteps()
    {
        return this.steps;
    }
    public void setSteps(String steps)
    {
        this.steps = steps;
    }
}
