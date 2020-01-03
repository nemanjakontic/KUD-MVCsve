/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;


import domain.Nosnja;


import java.util.List;

/**
 *
 * @author Nemanja
 */
public interface StorageNosnja {

    public Nosnja sacuvajN(Nosnja o) throws Exception;

    public Nosnja izmeniN(Nosnja o) throws Exception;

    public void obrisiN(int sifra) throws Exception;

    public List<Nosnja> vratiListuN() throws Exception;

    public Nosnja vratiN(int sifraNosnje);

    public List<Nosnja> vratiNosnjuPoK(String kriterijumPretrage, String naziv, String vrsta) throws Exception;

   
    
}
