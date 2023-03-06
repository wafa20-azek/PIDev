/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import java.util.List;

/**
 *
 * @author rajhi
 */
public interface DService <T>{
     public void ajouter(T t); // c fait
    public void supprimerParId(int id); //c fait
    public T getOneById(int id);
    public List<T> getAll();
    
}
