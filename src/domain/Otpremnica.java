/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Nemanja
 */
public class Otpremnica {
    private Long sifraOtpremnice;
    private Date datumKreiranja;
    private List<StavkaOtpremnice> stavkaOtpremnice;
    private List<IzmeneOtpremnice> izmenaOtpremnice;
    private boolean aktivna;
    private Clan clan;

    public Otpremnica() {
        stavkaOtpremnice = new ArrayList<>();
        izmenaOtpremnice = new ArrayList<>();
    }

    public Otpremnica(Long sifraOtpremnice, Date datumKreiranja, boolean aktivna, Clan clan) {
        this.sifraOtpremnice = sifraOtpremnice;
        this.datumKreiranja = datumKreiranja;
        this.aktivna = aktivna;
        this.clan = clan;
        stavkaOtpremnice = new ArrayList<>();
        izmenaOtpremnice = new ArrayList<>();
    }

    public Long getSifraOtpremnice() {
        return sifraOtpremnice;
    }

    public void setSifraOtpremnice(Long sifraOtpremnice) {
        this.sifraOtpremnice = sifraOtpremnice;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public List<StavkaOtpremnice> getStavkaOtpremnice() {
        return stavkaOtpremnice;
    }

    public void setStavkaOtpremnice(List<StavkaOtpremnice> stavkaOtpremnice) {
        this.stavkaOtpremnice = stavkaOtpremnice;
    }

    public List<IzmeneOtpremnice> getIzmenaOtpremnice() {
        return izmenaOtpremnice;
    }

    public void setIzmenaOtpremnice(List<IzmeneOtpremnice> izmenaOtpremnice) {
        this.izmenaOtpremnice = izmenaOtpremnice;
    }

    public boolean isAktivna() {
        return aktivna;
    }

    public void setAktivna(boolean aktivna) {
        this.aktivna = aktivna;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
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
        final Otpremnica other = (Otpremnica) obj;
        if (!Objects.equals(this.sifraOtpremnice, other.sifraOtpremnice)) {
            return false;
        }
        return true;
    }
    
    
    
}
