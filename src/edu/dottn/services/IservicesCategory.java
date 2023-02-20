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
public interface IservicesCategory<C> {

    public void addCategory(C p);

    public void removeCategory(int id);

    public void modifyCategory(C p);



    public C getById(int id);

    public List<C> getAll();

}
