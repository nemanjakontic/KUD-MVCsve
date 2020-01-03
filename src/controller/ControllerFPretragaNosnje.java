/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Clan;
import domain.Nosnja;
import domain.enumeracije.FormMode;
import domain.enumeracije.FormModeVrstaNosnje;
import domain.enumeracije.VrstaNosnje;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import service.ServiceNosnja;
import service.impl.ServiceNosnjaImpl;
import ui.component.table.model.NosnjaTableModel;
import ui.form.FDodajNosnju;
import ui.form.FMain;
import ui.form.FPretragaNosnje;

/**
 *
 * @author Nemanja
 */
public class ControllerFPretragaNosnje {

    private FPretragaNosnje fPretragaNosnje;
    private final ServiceNosnja serviceNosnja;

    public ControllerFPretragaNosnje() {
        serviceNosnja = new ServiceNosnjaImpl();
    }

    void otvoriFormuZaPretraguNosnje(FMain fMain) {
        fPretragaNosnje = new FPretragaNosnje(fMain, true);
        fillTabela();
        fillVrsteNosnje();
        addListenersPretragaNosnje();
        fPretragaNosnje.setVisible(true);
    }

    private void fillTabela() {
        List<Nosnja> lista = vratiListuNosnje();

        fPretragaNosnje.getjTblNosnja().setModel(new NosnjaTableModel(lista));
    }

    public Nosnja vratiNosnju(int sifraNosnje) {
        return serviceNosnja.vratiNo(sifraNosnje);
    }

    public List<Nosnja> vratiNosnjePoKriterijumu(String kriterijumPretrage, String naziv, String vrsta){
        return serviceNosnja.vratiNosnjePoKrit(kriterijumPretrage, naziv, vrsta);
    }
    
    public List<Nosnja> vratiListuNosnje() {
        return serviceNosnja.vratiListuNo();
    }

    private void addListenersPretragaNosnje() {
        fPretragaNosnje.addButtonDetailsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = fPretragaNosnje.getjTblNosnja().getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(fPretragaNosnje, "Molimo vas selektujte clana");
                } else {
                    int sifraNosnje = (int) fPretragaNosnje.getjTblNosnja().getValueAt(selectedRow, 0);
                    Nosnja nosnja = vratiNosnju(sifraNosnje);

                    GUICoordinator.getInstance().inicijalizujKonstruktorKontroleraNovaNosnja();
                    GUICoordinator.getInstance().pozoviPostavljanjeNosnje(nosnja);//.postaviNosnju(nosnja);

                    if (nosnja.getVrstaNosnje().equals(VrstaNosnje.BELINA)) {
                        GUICoordinator.getInstance().otvoriNovuNosnju(null, FormModeVrstaNosnje.FORM_BELINA, FormMode.FORM_VIEW);
                    } else if (nosnja.getVrstaNosnje().equals(VrstaNosnje.OPANCI)) {
                        GUICoordinator.getInstance().otvoriNovuNosnju(null, FormModeVrstaNosnje.FORM_OPANCI, FormMode.FORM_VIEW);
                    } else if (nosnja.getVrstaNosnje().equals(VrstaNosnje.NOSNJA)) {
                        GUICoordinator.getInstance().otvoriNovuNosnju(null, FormModeVrstaNosnje.FORM_NOSNJA, FormMode.FORM_VIEW);
                    }
                    NosnjaTableModel ctm = (NosnjaTableModel) fPretragaNosnje.getjTblNosnja().getModel();
                    List<Nosnja> lista = vratiListuNosnje();

                    ctm.azuriraj(lista);
                }
            }
        });

        /*fPretragaNosnje.addButtonAzurirajListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NosnjaTableModel ctm = (NosnjaTableModel) fPretragaNosnje.getjTblNosnja().getModel();
                List<Nosnja> lista = vratiListuNosnje();

                ctm.azuriraj(lista);
            }
        });*/

        fPretragaNosnje.addButtonPretraziListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fPretragaNosnje.getjTxtPretraga1().getText().isEmpty() && fPretragaNosnje.getJtxtNaziv().getText().isEmpty() && fPretragaNosnje.getJcmbVrsta().getSelectedItem().equals(VrstaNosnje.Izaberite_vrstu_nosnje)) {
                    NosnjaTableModel ctm = (NosnjaTableModel) fPretragaNosnje.getjTblNosnja().getModel();
                    List<Nosnja> lista = vratiListuNosnje();

                    ctm.azuriraj(lista);
                } else {
                    String kriterijumPretrage = fPretragaNosnje.getjTxtPretraga1().getText();
                    String naziv = fPretragaNosnje.getJtxtNaziv().getText();
                    String vrsta = String.valueOf(fPretragaNosnje.getJcmbVrsta().getSelectedItem());
                    /*int sifra;
                    try {
                        sifra = Integer.parseInt(kriterijumPretrage);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(fPretragaNosnje, "Sifra mora biti numericki karakter!");
                        return;
                    }
                    Nosnja nosnja = vratiNosnju(sifra);
                    List<Nosnja> nosnje = new ArrayList<>();
                    if (nosnja != null) {
                        nosnje.add(nosnja);
                    }*/
                    
                    List<Nosnja> nosnje = vratiNosnjePoKriterijumu(kriterijumPretrage, naziv, vrsta);
                    
                    NosnjaTableModel ctm = new NosnjaTableModel(nosnje);
                    fPretragaNosnje.getjTblNosnja().setModel(ctm);
                }
            }
        });

    }

    private void fillVrsteNosnje() {
        fPretragaNosnje.getJcmbVrsta().removeAllItems();
        
        fPretragaNosnje.getJcmbVrsta().addItem(VrstaNosnje.Izaberite_vrstu_nosnje);
        fPretragaNosnje.getJcmbVrsta().addItem(VrstaNosnje.BELINA);
        fPretragaNosnje.getJcmbVrsta().addItem(VrstaNosnje.NOSNJA);
        fPretragaNosnje.getJcmbVrsta().addItem(VrstaNosnje.OPANCI);
    }

}
