/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Clan;
import domain.Otpremnica;
import domain.enumeracije.FormMode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import service.ServiceOtpremnica;
import service.impl.ServiceOtpremnicaImpl;
import ui.component.table.model.OtpremniceTableModel;
import ui.form.FMain;
import ui.form.FPretragaOtpremnica;

/**
 *
 * @author Nemanja
 */
public class ControllerFPretragaOtpremnica {

    private FPretragaOtpremnica fPretragaOtpremnica;
    private final ServiceOtpremnica serviceOtpremnica;

    public ControllerFPretragaOtpremnica() {
        serviceOtpremnica = new ServiceOtpremnicaImpl();
    }

    void otvoriFormuZaPretraguOtpremnica(FMain fMain) {
        fPretragaOtpremnica = new FPretragaOtpremnica(fMain, true);
        fillTabela();
        fillClanove();
        addListeners();
        fPretragaOtpremnica.setVisible(true);
    }

    private List<Otpremnica> vratiSveOtpremnice() {
        return serviceOtpremnica.vratiOt();
    }

    private List<Otpremnica> vratiOtpremnicePoKriterijumu(String sifra, String clan){
        return serviceOtpremnica.vratiOtpPoKrt(sifra, clan);
    }
    
    private Otpremnica vratiOtpremnicu(Long sifraOtpremnice) {
        return serviceOtpremnica.vratiOt(sifraOtpremnice);
    }

    private void fillTabela() {
        List<Otpremnica> lista = vratiSveOtpremnice();

        fPretragaOtpremnica.getjTable1().setModel(new OtpremniceTableModel(lista));
    }

    private void addListeners() {
        fPretragaOtpremnica.addButtonDetailsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = fPretragaOtpremnica.getjTable1().getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(fPretragaOtpremnica, "Molimo vas selektujte clana");
                } else {
                    Long sifraOtpremnice = (Long) fPretragaOtpremnica.getjTable1().getValueAt(selectedRow, 0);
                    Otpremnica otpremnica = vratiOtpremnicu(sifraOtpremnice);
                    GUICoordinator.getInstance().pozoviPostavljanjeOtpremnice(otpremnica);
                    GUICoordinator.getInstance().otvoriIzdavanjeNosnje(null, FormMode.FORM_VIEW);

                    OtpremniceTableModel otm = (OtpremniceTableModel) fPretragaOtpremnica.getjTable1().getModel();
                    List<Otpremnica> lista = vratiSveOtpremnice();
                    otm.azuriraj(lista);
                }
            }
        });

        fPretragaOtpremnica.addButtonPretraziListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fPretragaOtpremnica.getJtxtSifra().getText().isEmpty() && fPretragaOtpremnica.getJcmbClan().getSelectedItem().equals("Izaberite clana")) {
                    OtpremniceTableModel otm = (OtpremniceTableModel) fPretragaOtpremnica.getjTable1().getModel();
                    List<Otpremnica> lista = vratiSveOtpremnice();

                    otm.azuriraj(lista);
                } else {
                    String sifra = fPretragaOtpremnica.getJtxtSifra().getText();
                    String clan = String.valueOf(fPretragaOtpremnica.getJcmbClan().getSelectedItem());
                    
                    List<Otpremnica> listaOtp = vratiOtpremnicePoKriterijumu(sifra, clan);
                    
                    OtpremniceTableModel otm = new OtpremniceTableModel(listaOtp);
                    fPretragaOtpremnica.getjTable1().setModel(otm);
                }
            }
        });

    }

    private void fillClanove() {
        fPretragaOtpremnica.getJcmbClan().removeAllItems();

        List<Clan> clanovi = GUICoordinator.getInstance().vratiSveClanove();

        fPretragaOtpremnica.getJcmbClan().addItem("Izaberite clana");

        for (Clan clan : clanovi) {
            fPretragaOtpremnica.getJcmbClan().addItem(clan);
        }

    }

}
