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
public interface CService <T> {
    public void ajouterComment(T t, int id);
    public void supprimerParId(int id); 
    public T getOneById(int id);
    public List<T> getAll();
    
}
