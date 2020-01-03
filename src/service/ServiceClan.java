/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Clan;
import java.util.List;

/**
 *
 * @author Nemanja
 */
public interface ServiceClan {

    public Clan zapamtiCl(Clan clan);

    public void obrisiCl(Long brojCK);

    public Clan izmeniCl(Clan clan);

    public List<Clan> vratiListuCl();

    public Clan vratiCl(Long brojCK);

    public List<Clan> vratiClanovePoKriterijumu(String sifra, String ime, String prezime);

    
    
}
