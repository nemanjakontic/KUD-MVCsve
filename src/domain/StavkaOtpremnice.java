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
public class StavkaOtpremnice {
    private int redniBroj;
    private Long kolicina;
    private Nosnja nosnja;

    public StavkaOtpremnice() {
    }

    public StavkaOtpremnice(int redniBroj, Long kolicina, Nosnja nosnja) {
        this.redniBroj = redniBroj;
        this.kolicina = kolicina;
        this.nosnja = nosnja;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public Long getKolicina() {
        return kolicina;
    }

    public void setKolicina(Long kolicina) {
        this.kolicina = kolicina;
    }

    public Nosnja getNosnja() {
        return nosnja;
    }

    public void setNosnja(Nosnja nosnja) {
        this.nosnja = nosnja;
    }
    
    
    
}
