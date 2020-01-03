/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Clan;
import domain.enumeracije.FormMode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import service.ServiceClan;
import service.impl.ServiceClanImpl;
import ui.component.table.model.ClanTableModel;
import ui.form.FMain;
import ui.form.FNoviClan;
import ui.form.FPretragaClana;

/**
 *
 * @author Nemanja
 */
public class ControllerFPretragaClana {

    private FPretragaClana fPretragaClana;
    private final ServiceClan serviceClan;

    public ControllerFPretragaClana() {
        serviceClan = new ServiceClanImpl();
    }

    public List<Clan> vratiListuClanova() {
        return serviceClan.vratiListuCl();
    }

    public Clan vratiClana(Long brojCK) {
        return serviceClan.vratiCl(brojCK);
    }

    public List<Clan> vratiClanovePoKriterijumu(String sifra, String ime, String prezime){
        return serviceClan.vratiClanovePoKriterijumu(sifra, ime, prezime);
    }
    
    void otvoriFormuZaPretraguClana(FMain fMain) {
        fPretragaClana = new FPretragaClana(fMain, true);
        fillForm();
        addListenersPretragaClana();
        fPretragaClana.setVisible(true);
    }

    private void addListenersPretragaClana() {
        fPretragaClana.addButtonDetailsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = fPretragaClana.getjTblClanovi().getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(fPretragaClana, "Molimo vas selektujte clana");
                } else {
                    Long brojCK = (Long) fPretragaClana.getjTblClanovi().getValueAt(selectedRow, 0);
                    Clan clan = vratiClana(brojCK);
                    GUICoordinator.getInstance().inicijalizujKonstruktorKontroleraNoviClan();
                    GUICoordinator.getInstance().pozoviPostavljanjeClana(clan);
                    //
                    //fPretragaClana.dispose();
                    //
                    GUICoordinator.getInstance().otvoriNovogClana(null, FormMode.FORM_VIEW);

                    ClanTableModel ctm = (ClanTableModel) fPretragaClana.getjTblClanovi().getModel();
                    List<Clan> lista = vratiListuClanova();

                    ctm.azuriraj(lista);

                    //fPretragaClana.setVisible(true);
                }
            }
        });

        /*fPretragaClana.addButtonAzurirajListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClanTableModel ctm = (ClanTableModel) fPretragaClana.getjTblClanovi().getModel();
                List<Clan> lista = vratiListuClanova();

                ctm.azuriraj(lista);
            }
        });*/

        fPretragaClana.addButtonPretraziListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fPretragaClana.getjTxtPretraga().getText().isEmpty() && fPretragaClana.getJtxtIme().getText().isEmpty() && fPretragaClana.getJtxtPrezime().getText().isEmpty()) {
                    ClanTableModel ctm = (ClanTableModel) fPretragaClana.getjTblClanovi().getModel();
                    List<Clan> lista = vratiListuClanova();

                    ctm.azuriraj(lista);
                } else {
                    String kriterijumPretrage = fPretragaClana.getjTxtPretraga().getText();
                    String ime = fPretragaClana.getJtxtIme().getText();
                    String prezime = fPretragaClana.getJtxtPrezime().getText();
                    /*Long sifra;
                    try {
                        sifra = Long.parseLong(kriterijumPretrage);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(fPretragaClana, "Sifra mora biti numericki karakter!");
                        return;
                    }*/
                    //
                    List<Clan> clanoviPoKriterijumu = vratiClanovePoKriterijumu(kriterijumPretrage, ime, prezime);
                    //
                    //Clan clan = vratiClana(sifra);
                    /*List<Clan> clanovi = new ArrayList<>();
                    if (clan != null) {
                        clanovi.add(clan);
                    }*/
                    ClanTableModel ctm = new ClanTableModel(clanoviPoKriterijumu);
                    fPretragaClana.getjTblClanovi().setModel(ctm);
                }
            }
        });

    }

    private void fillForm() {
        List<Clan> clanovi = vratiListuClanova();
        fPretragaClana.getjTblClanovi().setModel(new ClanTableModel(clanovi));
    }

}
