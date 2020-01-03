/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Nemanja
 */
class Koreografija {
    private int sifraKoreografije;
    private String nazivKoreografije;

    public Koreografija() {
    }

    public Koreografija(int sifraKoreografije, String nazivKoreografije) {
        this.sifraKoreografije = sifraKoreografije;
        this.nazivKoreografije = nazivKoreografije;
    }

    public int getSifraKoreografije() {
        return sifraKoreografije;
    }

    public void setSifraKoreografije(int sifraKoreografije) {
        this.sifraKoreografije = sifraKoreografije;
    }

    public String getNazivKoreografije() {
        return nazivKoreografije;
    }

    public void setNazivKoreografije(String nazivKoreografije) {
        this.nazivKoreografije = nazivKoreografije;
    }
    
    
    
}
