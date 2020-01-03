/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Nosnja;
import java.util.List;

/**
 *
 * @author Nemanja
 */
public interface ServiceNosnja {

    public Nosnja sacuvajNo(Nosnja n);

    public Nosnja izmeniNo(Nosnja n);

    public void obrisiNo(int sifra);

    /*public OpstaNosnja sacuvajNo(OpstaNosnja o);

    public OpstaNosnja izmeniNo(OpstaNosnja o);

    public Belina sacuvajBe(Belina b);

    public Belina izmeniBe(Belina b);

    public void obrisiBe(String sifra);*/

    public List<Nosnja> vratiListuNo();

    public Nosnja vratiNo(int sifraNosnje);

    public List<Nosnja> vratiNosnjePoKrit(String kriterijumPretrage, String naziv, String vrsta);

    
    
}
