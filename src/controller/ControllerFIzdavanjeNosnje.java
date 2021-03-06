/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Clan;
import domain.IzmeneOtpremnice;
import domain.Nosnja;
import domain.Otpremnica;
import domain.enumeracije.FormMode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import service.ServiceOtpremnica;
import service.impl.ServiceOtpremnicaImpl;
import ui.component.table.model.IzmeneOtpremnice1TableModel;
import ui.component.table.model.NosnjaTableModel;
import ui.component.table.model.StavkeOtpremniceTableModel;
import ui.form.FIzdavanjeNosnje;
import ui.form.FMain;

/**
 *
 * @author Nemanja
 */
class ControllerFIzdavanjeNosnje {

    private FIzdavanjeNosnje fIzdavanjeNosnje;
    private final ServiceOtpremnica serviceOtpremnica;
    private final Map<String, Object> map;

    public ControllerFIzdavanjeNosnje() {
        serviceOtpremnica = new ServiceOtpremnicaImpl();

        map = new HashMap<>();
    }

    public void postaviOtpremnicu(Otpremnica o) {
        map.put("trenutnaOtpremnica", o);
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public Otpremnica sacuvajOtpremnicu(Otpremnica o) {
        return serviceOtpremnica.sacuvajOt(o);
    }

    public void obrisiOtpremnicu(Long sifra){
        serviceOtpremnica.obrisiOtp(sifra);
    }
    
    public Otpremnica izmeniOtpremnicu(Otpremnica o) {
        return serviceOtpremnica.izmeniOtpremnicu(o);
    }

    public void otvoriFormuZaIzdavanjeNosnje(FMain fMain, FormMode formMode) {
        fIzdavanjeNosnje = new FIzdavanjeNosnje(fMain, true);
        fillFormOtpremnica();
        //na dva mesta postavlja modele tabela - ispraviti
        prepareFormMode(formMode);
        addListenersOtpremnica();
        fIzdavanjeNosnje.setVisible(true);
    }

    private void fillFormOtpremnica() {
        fillDatum();
        fillPrimalac();
        populateTableNosnja();
        populateTableOtpremnica();
        populateTableIzmene();
    }

    private void fillDatum() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date datum = new Date();
        String dat = sdf.format(datum);
        fIzdavanjeNosnje.getJtxtdatumkreiranja().setText(dat);
    }

    private void fillPrimalac() {
        fIzdavanjeNosnje.getJcmbPrimalac().removeAllItems();

        List<Clan> lista = GUICoordinator.getInstance().vratiSveClanove();

        for (Clan clan : lista) {
            fIzdavanjeNosnje.getJcmbPrimalac().addItem(clan);
        }

    }

    private void populateTableNosnja() {
        List<Nosnja> lista = GUICoordinator.getInstance().vratiSvuNosnju();

        fIzdavanjeNosnje.getJtblnosnje().setModel(new NosnjaTableModel(lista));
    }

    private void populateTableOtpremnica() {
        StavkeOtpremniceTableModel otp = new StavkeOtpremniceTableModel(new Otpremnica());
        fIzdavanjeNosnje.getJtblotpremnica().setModel(otp);
    }

    private void populateTableIzmene() {
        /*IzmeneOtpremniceTableModel iotm = new IzmeneOtpremniceTableModel(new Otpremnica());
        fIzdavanjeNosnje.getjTblIzmene().setModel(iotm);*/

        IzmeneOtpremnice1TableModel iotm = new IzmeneOtpremnice1TableModel(new Otpremnica());
        fIzdavanjeNosnje.getjTblIzmene().setModel(iotm);

    }

    private void addListenersOtpremnica() {
        fIzdavanjeNosnje.addButtonDodajStavkuListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NosnjaTableModel ntm = (NosnjaTableModel) fIzdavanjeNosnje.getJtblnosnje().getModel();
                int selectedRow = fIzdavanjeNosnje.getJtblnosnje().getSelectedRow();
                if (selectedRow >= 0) {
                    Nosnja n = ntm.getNosnja(selectedRow);
                    StavkeOtpremniceTableModel otm = (StavkeOtpremniceTableModel) fIzdavanjeNosnje.getJtblotpremnica().getModel();
                    Long kolicina = Long.valueOf(fIzdavanjeNosnje.getJtxtkolicina().getText().trim());
                    otm.addNosnja(n, kolicina);
                }
            }
        });

        fIzdavanjeNosnje.addMouseClickedTable(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NosnjaTableModel ntm = (NosnjaTableModel) fIzdavanjeNosnje.getJtblnosnje().getModel();
                int red = fIzdavanjeNosnje.getJtblnosnje().getSelectedRow();
                if (red >= 0) {
                    Nosnja n = ntm.getNosnja(red);
                    fIzdavanjeNosnje.getJtxtsifranosnje().setText(String.valueOf(n.getSifraNosnje()));
                    fIzdavanjeNosnje.getJtxtvrstaNosnje().setText(String.valueOf(n.getVrstaNosnje()));

                    fIzdavanjeNosnje.getJtxtnazivnosnje().setText(n.getNazivNosnje());
                    fIzdavanjeNosnje.getJtxtkolicina().setText("1");
                    fIzdavanjeNosnje.getJtxtkolicina().grabFocus();
                    fIzdavanjeNosnje.getJtxtkolicina().setSelectionStart(0);

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        fIzdavanjeNosnje.addButtonObrisiStavkuListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StavkeOtpremniceTableModel otm = (StavkeOtpremniceTableModel) fIzdavanjeNosnje.getJtblotpremnica().getModel();
                int selectedRow = fIzdavanjeNosnje.getJtblotpremnica().getSelectedRow();
                if (selectedRow >= 0) {
                    Nosnja n = otm.getOtpremnica().getStavkaOtpremnice().get(selectedRow).getNosnja();
                    IzmeneOtpremnice1TableModel iotm = (IzmeneOtpremnice1TableModel) fIzdavanjeNosnje.getjTblIzmene().getModel();
                    iotm.addIzmena(n);
                    otm.removeNosnja(selectedRow);

                }
            }
        });

        fIzdavanjeNosnje.addButtonSacuvajListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Otpremnica o = ((StavkeOtpremniceTableModel) fIzdavanjeNosnje.getJtblotpremnica().getModel()).getOtpremnica();
                Clan clanPrimalac = (Clan) fIzdavanjeNosnje.getJcmbPrimalac().getSelectedItem();
                o.setClan(clanPrimalac);

                IzmeneOtpremnice1TableModel izm = (IzmeneOtpremnice1TableModel) fIzdavanjeNosnje.getjTblIzmene().getModel();
                Otpremnica otp = izm.getOtpremnica();
                for (IzmeneOtpremnice izmena : otp.getIzmenaOtpremnice()) {
                    o.getIzmenaOtpremnice().add(izmena);
                }

                if (o.getStavkaOtpremnice().isEmpty()) {
                    o.setAktivna(false);
                } else {
                    o.setAktivna(true);
                }
                o = sacuvajOtpremnicu(o);
                JOptionPane.showMessageDialog(fIzdavanjeNosnje, "Otpremnica sa sifrom: " + o.getSifraOtpremnice() + " je sacuvana!");
                postaviOtpremnicu(o);
                prepareFormMode(FormMode.FORM_VIEW);
            }
        });

        fIzdavanjeNosnje.addButtonEnableChangesListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareFormMode(FormMode.FORM_UPDATE);
            }
        });

        fIzdavanjeNosnje.addButtonUpdateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Clan clanPrimalac = (Clan) fIzdavanjeNosnje.getJcmbPrimalac().getSelectedItem();

                //IzmeneOtpremniceTableModel otm = (IzmeneOtpremniceTableModel) fIzdavanjeNosnje.getjTblIzmene().getModel();
                //otm.addIzmena();
                //int poslednjiIndeks = ((IzmeneOtpremniceTableModel) fIzdavanjeNosnje.getjTblIzmene().getModel()).getO().getIzmenaOtpremnice().size() - 1;
                //IzmeneOtpremnice izmena = ((IzmeneOtpremniceTableModel) fIzdavanjeNosnje.getjTblIzmene().getModel()).getO().getIzmenaOtpremnice().get(poslednjiIndeks);
                Otpremnica o = ((StavkeOtpremniceTableModel) fIzdavanjeNosnje.getJtblotpremnica().getModel()).getOtpremnica();
                //o.getIzmenaOtpremnice().add(izmena);
                IzmeneOtpremnice1TableModel izm = (IzmeneOtpremnice1TableModel) fIzdavanjeNosnje.getjTblIzmene().getModel();
                Otpremnica otp = izm.getOtpremnica();
                /*for (int i = 0; i < o.getIzmenaOtpremnice().size(); i++) {
                    o.getIzmenaOtpremnice().remove(o.getIzmenaOtpremnice().get(i));
                }
                for (IzmeneOtpremnice izmena : otp.getIzmenaOtpremnice()) {
                    o.getIzmenaOtpremnice().add(izmena);
                }*/
                o.setIzmenaOtpremnice(otp.getIzmenaOtpremnice());
                o.setClan(clanPrimalac);
                o = izmeniOtpremnicu(o);
                JOptionPane.showMessageDialog(fIzdavanjeNosnje, "Otpremnica sa sifrom: " + o.getSifraOtpremnice() + " je izmenjena!");

                postaviOtpremnicu(o);

                prepareFormMode(FormMode.FORM_VIEW);
            }
        });

        fIzdavanjeNosnje.addButtonDeleteListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Otpremnica otpremnica =(Otpremnica) getMap().get("trenutnaOtpremnica");
                Long sifra = otpremnica.getSifraOtpremnice();
                obrisiOtpremnicu(sifra);
                JOptionPane.showMessageDialog(fIzdavanjeNosnje, "Obrisana je otpremnica sa sifrom: " + otpremnica.getSifraOtpremnice());
                prepareFormMode(FormMode.FORM_ADD);
            }
        });
        
        fIzdavanjeNosnje.addButtonUbaciNovogListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareFormMode(FormMode.FORM_ADD);
            }
        });
        
    }

    private void prepareFormMode(FormMode form_mode) {
        if (form_mode.equals(FormMode.FORM_ADD)) {
            fIzdavanjeNosnje.getjBtnDelete().setEnabled(false);
            fIzdavanjeNosnje.getjBtnEnableChanges().setEnabled(false);
            fIzdavanjeNosnje.getjBtnUpdate().setEnabled(false);
            fIzdavanjeNosnje.getJbtnsacuvaj().setEnabled(true);
            fIzdavanjeNosnje.getjBtnCancel().setEnabled(true);
            fIzdavanjeNosnje.getjBtnUbaciNovog().setEnabled(false);
            
            fIzdavanjeNosnje.getJtxtsifra().setText("");
            fIzdavanjeNosnje.getJcmbPrimalac().setEnabled(true);
            fIzdavanjeNosnje.getJtblotpremnica().setEnabled(true);
            fIzdavanjeNosnje.getJtblnosnje().setEnabled(true);
            fIzdavanjeNosnje.getJbtndodajstavku().setEnabled(true);
            populateTableIzmene();
            populateTableOtpremnica();

        } else if (form_mode.equals(FormMode.FORM_VIEW)) {
            fIzdavanjeNosnje.getJbtnsacuvaj().setEnabled(false);
            fIzdavanjeNosnje.getjBtnUpdate().setEnabled(false);
            fIzdavanjeNosnje.getjBtnEnableChanges().setEnabled(true);
            fIzdavanjeNosnje.getjBtnDelete().setEnabled(true);
            fIzdavanjeNosnje.getjBtnUbaciNovog().setEnabled(true);

            fIzdavanjeNosnje.getJtxtdatumkreiranja().setEnabled(false);
            fIzdavanjeNosnje.getJbrnobrisistavku().setEnabled(false);
            fIzdavanjeNosnje.getJtxtizdavac().setEnabled(false);
            fIzdavanjeNosnje.getJcmbPrimalac().setEnabled(false);
            fIzdavanjeNosnje.getJbtndodajstavku().setEnabled(false);
            fIzdavanjeNosnje.getJtblnosnje().setEnabled(false);
            fIzdavanjeNosnje.getJtblotpremnica().setEnabled(false);

            Otpremnica o = (Otpremnica) getMap().get("trenutnaOtpremnica");
            postaviOtpremnicuZaPrikaz(o);

        } else if (form_mode.equals(FormMode.FORM_UPDATE)) {
            fIzdavanjeNosnje.getjBtnDelete().setEnabled(false);
            fIzdavanjeNosnje.getjBtnEnableChanges().setEnabled(false);
            fIzdavanjeNosnje.getjBtnUpdate().setEnabled(true);
            fIzdavanjeNosnje.getJbtnsacuvaj().setEnabled(false);
            fIzdavanjeNosnje.getjBtnCancel().setEnabled(true);

            fIzdavanjeNosnje.getJtxtdatumkreiranja().setEnabled(false);
            fIzdavanjeNosnje.getJbrnobrisistavku().setEnabled(true);
            fIzdavanjeNosnje.getJtxtizdavac().setEnabled(false);
            fIzdavanjeNosnje.getJcmbPrimalac().setEnabled(false);
            fIzdavanjeNosnje.getJbtndodajstavku().setEnabled(true);
            fIzdavanjeNosnje.getJtblnosnje().setEnabled(true);
            fIzdavanjeNosnje.getJtblotpremnica().setEnabled(true);

            Otpremnica o = (Otpremnica) getMap().get("trenutnaOtpremnica");
            postaviOtpremnicuZaPrikaz(o);
        }
    }

    private void postaviOtpremnicuZaPrikaz(Otpremnica o) {
        fIzdavanjeNosnje.getJtxtsifra().setText(String.valueOf(o.getSifraOtpremnice()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        fIzdavanjeNosnje.getJtxtdatumkreiranja().setText(sdf.format(o.getDatumKreiranja()));

        StavkeOtpremniceTableModel otp = new StavkeOtpremniceTableModel(o);
        fIzdavanjeNosnje.getJtblotpremnica().setModel(otp);

        IzmeneOtpremnice1TableModel izm = new IzmeneOtpremnice1TableModel(o);
        fIzdavanjeNosnje.getjTblIzmene().setModel(izm);
        fIzdavanjeNosnje.getJcmbPrimalac().setSelectedItem(o.getClan());

    }

}
