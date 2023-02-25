package edu.dottn.entities;


import java.sql.Timestamp;


public class Message {
    private int idMessage;
   
    private String senderId;
    private Association  receiverId;
    private String text;
    private Timestamp time;

    public Message(String senderId, Association receiverId, String text, Timestamp time) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.time = time;
    }

        
    
    public Message(int idMessage, String senderId, Association receiverId, String text, Timestamp time) {
        this.idMessage = idMessage;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.time = time;
    }

    public Message() {
    }
    
   

    public int getIdMessage() {
        return idMessage;
    }


    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Association  getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Association  receiverId) {
        this.receiverId = receiverId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    
    
    
    
}
