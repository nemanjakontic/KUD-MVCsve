/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.enumeracije.Pol;
import domain.enumeracije.VrstaNosnje;
import java.util.Objects;

/**
 *
 * @author Nemanja
 */
public class Nosnja {

    private int sifraNosnje;
    private VrstaNosnje vrstaNosnje;
    private Pol pol;
    private String nazivNosnje;
    private int kolicina;
    private String opis;
    private String vrstaBeline;
    private String velicina;
    private String vrstaOpanaka;

    public Nosnja() {
    }

    public Nosnja(int sifraNosnje, VrstaNosnje vrstaNosnje, Pol pol, String nazivNosnje, int kolicina, String opis, String vrstaBeline, String velicina, String vrstaOpanaka) {
        this.sifraNosnje = sifraNosnje;
        this.vrstaNosnje = vrstaNosnje;
        this.pol = pol;
        this.nazivNosnje = nazivNosnje;
        this.kolicina = kolicina;
        this.opis = opis;
        this.vrstaBeline = vrstaBeline;
        this.velicina = velicina;
        this.vrstaOpanaka = vrstaOpanaka;
    }

    

    public int getSifraNosnje() {
        return sifraNosnje;
    }

    public void setSifraNosnje(int sifraNosnje) {
        this.sifraNosnje = sifraNosnje;
    }

    public VrstaNosnje getVrstaNosnje() {
        return vrstaNosnje;
    }

    public void setVrstaNosnje(VrstaNosnje vrstaNosnje) {
        this.vrstaNosnje = vrstaNosnje;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nosnja other = (Nosnja) obj;
        if (this.sifraNosnje != other.sifraNosnje) {
            return false;
        }
        return true;
    }

    

    
    
    public String getNazivNosnje() {
        return nazivNosnje;
    }

    public void setNazivNosnje(String nazivNosnje) {
        this.nazivNosnje = nazivNosnje;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getVrstaBeline() {
        return vrstaBeline;
    }

    public void setVrstaBeline(String vrstaBeline) {
        this.vrstaBeline = vrstaBeline;
    }

    public String getVelicina() {
        return velicina;
    }

    public void setVelicina(String velicina) {
        this.velicina = velicina;
    }

    public String getVrstaOpanaka() {
        return vrstaOpanaka;
    }

    public void setVrstaOpanaka(String vrstaOpanaka) {
        this.vrstaOpanaka = vrstaOpanaka;
    }

}
