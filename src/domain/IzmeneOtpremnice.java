/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;

/**
 *
 * @author Nemanja
 */
public class IzmeneOtpremnice {
    private int rb;
    private Date datumIzmene;
    private Nosnja nosnja;

    public IzmeneOtpremnice() {
    }

    public IzmeneOtpremnice(int rb, Date datumIzmene, Nosnja nosnja) {
        this.rb = rb;
        this.datumIzmene = datumIzmene;
        this.nosnja = nosnja;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Date getDatumIzmene() {
        return datumIzmene;
    }

    public void setDatumIzmene(Date datumIzmene) {
        this.datumIzmene = datumIzmene;
    }

    public Nosnja getNosnja() {
        return nosnja;
    }

    public void setNosnja(Nosnja nosnja) {
        this.nosnja = nosnja;
    }
    
    
    
}
