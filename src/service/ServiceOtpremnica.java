/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Otpremnica;
import java.util.List;

/**
 *
 * @author Nemanja
 */
public interface ServiceOtpremnica {

    public Otpremnica sacuvajOt(Otpremnica o);

    public Otpremnica izmeniOtpremnicu(Otpremnica o);

    public List<Otpremnica> vratiOt();

    public Otpremnica vratiOt(Long sifraOtpremnice);

    public List<Otpremnica> vratiOtpPoKrt(String sifra, String clan);

    public void obrisiOtp(Long sifra);
    
}
