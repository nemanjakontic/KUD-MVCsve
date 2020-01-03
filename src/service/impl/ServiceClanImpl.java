/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.Clan;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ServiceClan;
import storage.StorageClan;
import storage.impl.database.StorageDatabaseClan;

/**
 *
 * @author Nemanja
 */
public class ServiceClanImpl implements ServiceClan{
    private final StorageClan storageClan;

    public ServiceClanImpl() {
        storageClan = new StorageDatabaseClan();
    }

    @Override
    public Clan zapamtiCl(Clan clan) {
        try {
            return storageClan.zapamtiC(clan);
        } catch (Exception ex) {
            Logger.getLogger(ServiceClanImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void obrisiCl(Long brojCK) {
        try {
            storageClan.obrisiC(brojCK);
        } catch (Exception ex) {
            Logger.getLogger(ServiceClanImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Clan izmeniCl(Clan clan) {
        try {
            return storageClan.izmeniC(clan);
        } catch (Exception ex) {
            Logger.getLogger(ServiceClanImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Clan> vratiListuCl() {
        try {
            return storageClan.vratiListuC();
        } catch (Exception ex) {
            Logger.getLogger(ServiceClanImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Clan vratiCl(Long brojCK) {
        try {
            return storageClan.vratiC(brojCK);
        } catch (Exception ex) {
            Logger.getLogger(ServiceClanImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Clan> vratiClanovePoKriterijumu(String sifra, String ime, String prezime) {
        try {
            return storageClan.vratiClanovePoKr(sifra, ime, prezime);
        } catch (Exception ex) {
            Logger.getLogger(ServiceClanImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    
    
    
}
