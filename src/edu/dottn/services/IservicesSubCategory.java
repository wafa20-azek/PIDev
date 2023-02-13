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
public interface IservicesSubCategory<S> {

    public void addSubCategory(S s);

    public void removeSubCategory(int id);

    public void modifySubCategory(S s);

    public S getById(int id);

    public List<S> getAllById();

}
