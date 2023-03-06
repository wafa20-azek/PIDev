/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Event;
import edu.dottn.entities.Member;
import edu.dottn.services.MemberServices;
import edu.dottn.util.DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ProInfo
 */
public class ServiceEvent implements IService<Event> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Event t) {
        try {
            String req = "INSERT INTO `event`(`event_name`, `event_description`, `event_date`, `event_location`, `event_status`,`user`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getName());
            ps.setString(2, t.getDescription());
            ps.setDate(3, t.getEventDate());
            ps.setString(4, t.getLocation());
            ps.setString(5, t.getStatus().name());
            ps.setInt(6,3);
            ps.executeUpdate();
            System.out.println("event added");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `event` WHERE (`event_id` = " + id + ")";
            Statement s = cnx.createStatement();
            s.executeUpdate(req);
            System.out.println("row deleted");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public Event getOneById(int id) {
        Event event = null;
        try {
            String req = "SELECT  `event_name`, `event_description`, `event_date`, `event_location`, `event_status` FROM `event` WHERE event_id = "+id+"";
            PreparedStatement ps = cnx.prepareStatement(req);
        
            ResultSet result = ps.executeQuery();
            System.out.println("row by id pulled");
            if (result.next()) {
                event = new Event();
                event.setName(result.getString("event_name"));
                event.setDescription(result.getString("event_description"));
                event.setEventDate(result.getDate("event_date"));
                event.setLocation(result.getString("event_location"));
                event.setStatus(Event.Status.valueOf(result.getString("event_status")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return event;

    }

    @Override
    public void modifier(Event t) {
        try {
            String req = "UPDATE `event` SET `event_name`='"+t.getName()+"',`event_description`='"+t.getDescription()+"',`event_date`='"+t.getEventDate()+"',`event_location`='"+t.getLocation()+"',`event_status`='"+t.getStatus().toString()+"',`user`='"+3+"' WHERE `event_id`='"+t.getIdEvent()+"'";
            PreparedStatement ps = cnx.prepareStatement(req);
         
            ps.executeUpdate();
            System.out.println("roww updated ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Event> getAll() {
        List<Event> listE = new ArrayList();
        try {

            String req = "SELECT * FROM `event`";
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);

            while (result.next()) {
                Event ev = new Event();
                ev.setIdEvent(result.getInt("event_id"));
                ev.setName(result.getString("event_name"));
                ev.setDescription(result.getString("event_description"));
                ev.setEventDate(result.getDate("event_date"));
                ev.setLocation(result.getString("event_location"));
                ev.setStatus(Event.Status.valueOf(result.getString("event_status")));
                listE.add(ev);

            }
            System.out.println("Row pulled");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listE;
    }

    public void deletePastEvents() {
        try {
            String req = "DELETE FROM `event` WHERE `event_date` < NOW()";
            Statement s = cnx.createStatement();
            int count = s.executeUpdate(req);
            System.out.println(count + " row(s) deleted");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Event> search(String hint) {
        List<Event> results = new ArrayList<>();
        try {
            String req = "SELECT * FROM `event` WHERE `event_name` LIKE ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, "%" + hint + "%");
           
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Event ev = new Event();
                ev.setName(result.getString("event_name"));
                ev.setDescription(result.getString("event_description"));
                ev.setEventDate(result.getDate("event_date"));
                ev.setLocation(result.getString("event_location"));
                ev.setStatus(Event.Status.valueOf(result.getString("event_status")));
                results.add(ev);
            }
            System.out.println("Search completed");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return results;
    }

    public List<Event> getEventsByDateRange(Date startDate, Date endDate) {
        List<Event> events = new ArrayList<>();
        try {
            events=this.getAll();
            System.out.println("Events by date range pulled");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return events.stream()
                .filter(event -> event.getEventDate().after(startDate) && event.getEventDate().before(endDate))
                .collect(Collectors.toList());
    }

    public void participer(Event eve, int userId) {
        try {
            // Check if the event exists
            Event event = getOneById(eve.getIdEvent());
            if (event == null) {
                System.out.println("Event does not exist");
                return;
            }

            // Check if the user exists
            MemberServices ms = new MemberServices();
            Member m = ms.getOneById(userId);
            if (m == null) {
                System.out.println("User does not have an account");
                return;
            }

            // Add the user to the event's participants list
            String req = "INSERT INTO `participant` (`nameP`, `event`) VALUES (?, ?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, userId);
            ps.setInt(2, eve.getIdEvent());
            ps.executeUpdate();
            System.out.println("User has successfully participated in the event");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public List<Event> getById(int id) {
        List<Event> listE = new ArrayList();
        try {
            String req = "SELECT  `event_id`,`event_name`, `event_description`, `event_date`, `event_location`, `event_status` FROM `event` WHERE user = "+id+"";
            PreparedStatement ps = cnx.prepareStatement(req);
        
            ResultSet result = ps.executeQuery();
            System.out.println("row by id pulled");
            while (result.next()) {
                Event ev = new Event();
                ev.setIdEvent(result.getInt("event_id"));
                ev.setName(result.getString("event_name"));
                ev.setDescription(result.getString("event_description"));
                ev.setEventDate(result.getDate("event_date"));
                ev.setLocation(result.getString("event_location"));
                ev.setStatus(Event.Status.valueOf(result.getString("event_status")));
                listE.add(ev);

            }
            System.out.println("Row pulled");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listE;
}}
