package com.example.adriangracia.studybuddy.objects;

import java.io.Serializable;

/**
 * Created by idk on 4/22/2015.
 */
public class EventObject implements Serializable{

    String title;
    String location;
    String description;
    String subject;
    int duration;
    TimeObject to;
    String peopleGoing;
    String numPeopleGoing;

    public EventObject(String title, String location,String description, String subject, int duration, TimeObject to, String peopleGoing, String numPeopleGoing){
        this.title=title;
        this.location=location;
        this.description=description;
        this.subject=subject;
        this.duration=duration;
        this.to=to;
        this.peopleGoing=peopleGoing;
        this.numPeopleGoing=numPeopleGoing;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TimeObject getTo() {
        return to;
    }

    public void setTo(TimeObject to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPeopleGoing() {
        return peopleGoing;
    }

    public void setPeopleGoing(String peopleGoing) {
        this.peopleGoing = peopleGoing;
    }

    public String getNumPeopleGoing() {
        return numPeopleGoing;
    }

    public void setNumPeopleGoing(String numPeopleGoing) {
        this.numPeopleGoing = numPeopleGoing;
    }

    public String getDurationString(){
        String str ="";
        str+=duration + " hours";
        return str;
    }
}
