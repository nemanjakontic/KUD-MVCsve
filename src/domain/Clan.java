/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.enumeracije.Ansambl;
import domain.enumeracije.Pol;
import java.util.Date;
import java.util.Objects;


/**
 *
 * @author Nemanja
 */
public class Clan {
    private Long brojCK;
    private String ime;
    private String prezime;
    private Pol pol;
    private double visina;
    private java.util.Date datumRodjenja;
    private java.util.Date datumUclanjenja;
    private double brojObuce;
    private Ansambl ansambl;
    private boolean aktivan;

    public Clan() {
    }

    public Clan(Long brojCK, String ime, String prezime, Pol pol, double visina, Date datumRodjenja, Date datumUclanjenja, double brojObuce, Ansambl ansambl, boolean aktivan) {
        this.brojCK = brojCK;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.visina = visina;
        this.datumRodjenja = datumRodjenja;
        this.brojObuce = brojObuce;
        this.ansambl = ansambl;
        this.datumUclanjenja = datumUclanjenja;
        this.aktivan = aktivan;
    }

    public Long getBrojCK() {
        return brojCK;
    }

    public void setBrojCK(Long brojCK) {
        this.brojCK = brojCK;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public double getVisina() {
        return visina;
    }

    public void setVisina(double visina) {
        this.visina = visina;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public double getBrojObuce() {
        return brojObuce;
    }

    public void setBrojObuce(double brojObuce) {
        this.brojObuce = brojObuce;
    }

    public Ansambl getAnsambl() {
        return ansambl;
    }

    public void setAnsambl(Ansambl ansambl) {
        this.ansambl = ansambl;
    }

    public Date getDatumUclanjenja() {
        return datumUclanjenja;
    }

    public void setDatumUclanjenja(Date datumUclanjenja) {
        this.datumUclanjenja = datumUclanjenja;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final Clan other = (Clan) obj;
        if (!Objects.equals(this.brojCK, other.brojCK)) {
            return false;
        }
        return true;
    }

    

   
    
    
    
}
