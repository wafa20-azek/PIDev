/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import java.util.List;

/**
 *
 * @author WALID
 */
public interface UServices<T> {
    public T ajouterUser(T t);
    public void deleteUser(int id );
    public void modifierUser(T t);
    public T getOneById(int id);
    public List<T> getAllById();
    
    
}
