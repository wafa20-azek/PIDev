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
public interface WLServices<T> {
    public void ajouter(T t);
    public void delete(int id );
    public T getOneById(int id);
    public List<T> getAllById();
    
}
