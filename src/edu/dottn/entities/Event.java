/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author ProInfo
 */
public class Event {
    private int idEvent;
    private String Name;
    private String Description;
    private Date eventDate;
    private String Location;
    

   
    public enum Status{ongoing,completed} ;
    private Status status;
    
    public Event() {
    }

    public Event(int idEvent, String Name, String Description, Date eventDate, String Location, Status status) {
        this.idEvent = idEvent;
        this.Name = Name;
        this.Description = Description;
        this.eventDate = eventDate;
        this.Location = Location;
        this.status = status;
    }

    public Event(String Name, String Description, Date eventDate, String Location, Status status) {
        this.Name = Name;
        this.Description = Description;
        this.eventDate = eventDate;
        this.Location = Location;
        this.status = status;
    }

 
    

    public int getIdEvent() {
        return idEvent;
    }
    
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.idEvent;
        hash = 11 * hash + Objects.hashCode(this.Name);
        hash = 11 * hash + Objects.hashCode(this.Description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        if (this.idEvent != other.idEvent) {
            return false;
        }
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Event{" + "Name=" + Name + ", Description=" + Description + ", eventDate=" + eventDate + ", Location=" + Location + ", status=" + status + '}';
    }

    

   
}
