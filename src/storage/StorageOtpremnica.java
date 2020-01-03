/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import domain.Otpremnica;
import java.util.List;

/**
 *
 * @author Nemanja
 */
public interface StorageOtpremnica {

    public Otpremnica sacuvajO(Otpremnica o) throws Exception;

    public Otpremnica izmeniO(Otpremnica o) throws Exception;

    public List<Otpremnica> vratiSveO() throws Exception;

    public Otpremnica vratiOtp(Long sifraOtpremnice) throws Exception;

    public List<Otpremnica> vratiOtpPoKr(String sifra, String clan) throws Exception;

    public void obrisiO(Long sifra) throws Exception;
    
}
