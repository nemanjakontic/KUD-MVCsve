/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.Nosnja;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import storage.StorageNosnja;
import storage.impl.database.StorageDatabaseNosnja;

/**
 *
 * @author Nemanja
 */
public class ServiceNosnjaImpl implements service.ServiceNosnja{
    private final StorageNosnja storageNosnja;

    public ServiceNosnjaImpl() {
        storageNosnja = new StorageDatabaseNosnja();
    }

    @Override
    public Nosnja sacuvajNo(Nosnja n) {
        try {
            return storageNosnja.sacuvajN(n);
        } catch (Exception ex) {
            Logger.getLogger(ServiceNosnjaImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Nosnja izmeniNo(Nosnja n) {
        try {
            return storageNosnja.izmeniN(n);
        } catch (Exception ex) {
            Logger.getLogger(ServiceNosnjaImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void obrisiNo(int sifra) {
        try {
            storageNosnja.obrisiN(sifra);
        } catch (Exception ex) {
            Logger.getLogger(ServiceNosnjaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Nosnja> vratiListuNo() {
        try {
            return storageNosnja.vratiListuN();
        } catch (Exception ex) {
            Logger.getLogger(ServiceNosnjaImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Nosnja vratiNo(int sifraNosnje) {
        return storageNosnja.vratiN(sifraNosnje);
    }

    @Override
    public List<Nosnja> vratiNosnjePoKrit(String kriterijumPretrage, String naziv, String vrsta) {
        try {
            return storageNosnja.vratiNosnjuPoK(kriterijumPretrage, naziv, vrsta);
        } catch (Exception ex) {
            Logger.getLogger(ServiceNosnjaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
