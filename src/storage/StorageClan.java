/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import domain.Clan;
import java.util.List;

/**
 *
 * @author Nemanja
 */
public interface StorageClan {

    public Clan zapamtiC(Clan clan) throws Exception;

    public void obrisiC(Long brojCK) throws Exception;

    public Clan izmeniC(Clan clan) throws Exception;

    public List<Clan> vratiListuC() throws Exception;

    public Clan vratiC(Long brojCK) throws Exception;

    public List<Clan> vratiClanovePoKr(String sifra, String ime, String prezime) throws Exception;

    
    
}
