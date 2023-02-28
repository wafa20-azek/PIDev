/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import java.util.List;

/**
 *
 * @author bochr
 */
public interface Oservice<O> {

    public void ajouterOffre(O o);

    public void supprimerOffre(int id_Offre);

    public void modifierOffre(O o);

    public List<O> getBYStatus(String status);
//public void AccepterOffre(O o);
//
//    public void RefuserOffer(O o);

    public O getOneById(int id_Offre);

    public List<O> getAll();//interface ;arraylist,vector ce sont des classes
    public void generatePDF(O o) ;
}
