/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import java.util.List;

/**
 *
 * @author WAFA
 */
public interface IservicesProduct<P> {
    
    public void addProduct(P p);
    public void removeProduct(int id);
    public void modifyProduct(P p);
    public P getById(int id);  
    public List<P> getAll();
    
    
    
    
}
