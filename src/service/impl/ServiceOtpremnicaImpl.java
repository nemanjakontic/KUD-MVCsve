/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.Otpremnica;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ServiceOtpremnica;
import storage.StorageOtpremnica;
import storage.impl.database.StorageDatabaseOtpremnica;

/**
 *
 * @author Nemanja
 */
public class ServiceOtpremnicaImpl implements ServiceOtpremnica{
    private final StorageOtpremnica storageOtpremnica;

    public ServiceOtpremnicaImpl() {
        storageOtpremnica = new StorageDatabaseOtpremnica();
    }
    
    
    
    @Override
    public Otpremnica sacuvajOt(Otpremnica o) {
        o.setDatumKreiranja(new Date());
        try {
            return storageOtpremnica.sacuvajO(o);
        } catch (Exception ex) {
            Logger.getLogger(ServiceOtpremnicaImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Otpremnica izmeniOtpremnicu(Otpremnica o) {
        o.setDatumKreiranja(new Date());
        try {
            return storageOtpremnica.izmeniO(o);
        } catch (Exception ex) {
            Logger.getLogger(ServiceOtpremnicaImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Otpremnica> vratiOt() {
        try {
            return storageOtpremnica.vratiSveO();
        } catch (Exception ex) {
            Logger.getLogger(ServiceOtpremnicaImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Otpremnica vratiOt(Long sifraOtpremnice) {
        try {
            return storageOtpremnica.vratiOtp(sifraOtpremnice);
        } catch (Exception ex) {
            Logger.getLogger(ServiceOtpremnicaImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Otpremnica> vratiOtpPoKrt(String sifra, String clan) {
        try {
            return storageOtpremnica.vratiOtpPoKr(sifra, clan);
        } catch (Exception ex) {
            Logger.getLogger(ServiceOtpremnicaImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void obrisiOtp(Long sifra) {
        try {
            storageOtpremnica.obrisiO(sifra);
        } catch (Exception ex) {
            Logger.getLogger(ServiceOtpremnicaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
