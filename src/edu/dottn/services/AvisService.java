/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

/**
 *
 * @author bochr
 */
public interface AvisService<A> {

    public void ajouterAvisOffer(A a);

    public void modifierAvisOffer(A a);

    public void supprimerAvisOffer(A a);

    public A getAvisOffeById(int idavis);

}
