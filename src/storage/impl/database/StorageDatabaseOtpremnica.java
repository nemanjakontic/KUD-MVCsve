/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.impl.database;

import database.connection.ConnectionFactory;
import domain.Clan;
import domain.IzmeneOtpremnice;
import domain.Nosnja;
import domain.Otpremnica;
import domain.StavkaOtpremnice;
import domain.enumeracije.Ansambl;
import domain.enumeracije.Pol;
import domain.enumeracije.VrstaNosnje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import storage.StorageOtpremnica;

/**
 *
 * @author Nemanja
 */
public class StorageDatabaseOtpremnica implements StorageOtpremnica {

    @Override
    public Otpremnica sacuvajO(Otpremnica o) throws Exception {
        Connection connection = ConnectionFactory.getInstance().getConnection();

        String upit = "INSERT INTO otpremnica (datumKreiranja, aktivna, brojCK) VALUES (?,?,?)";

        PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);

        ps.setDate(1, new Date(o.getDatumKreiranja().getTime()));
        ps.setBoolean(2, o.isAktivna());
        ps.setLong(3, o.getClan().getBrojCK());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();

        if (rs.next()) {
            Long id = rs.getLong(1);
            o.setSifraOtpremnice(id);
            upit = "INSERT INTO stavkaOtpremnice (rb,sifraOtpremnice,kolicina,sifraNosnje) VALUES (?,?,?,?)";
            ps = connection.prepareStatement(upit);
            for (StavkaOtpremnice stavka : o.getStavkaOtpremnice()) {
                ps.setInt(1, stavka.getRedniBroj());
                ps.setLong(2, o.getSifraOtpremnice());
                ps.setLong(3, stavka.getKolicina());
                ps.setInt(4, stavka.getNosnja().getSifraNosnje());

                ps.executeUpdate();
            }

            upit = "INSERT INTO izmeneotpremnice(rb,sifraOtpremnice,datumIzmene,sifraNosnje) VALUES (?,?,?,?)";
            ps = connection.prepareStatement(upit);

            for (IzmeneOtpremnice izmena : o.getIzmenaOtpremnice()) {
                ps.setInt(1, izmena.getRb());
                ps.setLong(2, o.getSifraOtpremnice());
                ps.setDate(3, new Date(o.getDatumKreiranja().getTime()));
                ps.setInt(4, izmena.getNosnja().getSifraNosnje());
                ps.executeUpdate();
            }

        }
        return o;
    }

    @Override
    public Otpremnica izmeniO(Otpremnica o) throws Exception {

        Connection connection = ConnectionFactory.getInstance().getConnection();

        String upit = "DELETE FROM stavkaotpremnice WHERE sifraOtpremnice = ?";

        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setLong(1, o.getSifraOtpremnice());
        ps.executeUpdate();

        upit = "INSERT INTO stavkaOtpremnice (rb,sifraOtpremnice,kolicina,sifraNosnje) VALUES (?,?,?,?)";
        ps = connection.prepareStatement(upit);
        for (StavkaOtpremnice stavka : o.getStavkaOtpremnice()) {
            ps.setInt(1, stavka.getRedniBroj());
            ps.setLong(2, o.getSifraOtpremnice());
            ps.setLong(3, stavka.getKolicina());
            ps.setInt(4, stavka.getNosnja().getSifraNosnje());

            ps.executeUpdate();
        }

        String upit1 = "DELETE FROM izmeneotpremnice WHERE sifraOtpremnice = ?";

        ps = connection.prepareStatement(upit1);
        ps.setLong(1, o.getSifraOtpremnice());
        ps.executeUpdate();

        upit1 = "INSERT INTO izmeneOtpremnice(rb,sifraOtpremnice,datumIzmene,sifraNosnje) VALUES (?,?,?,?)";
        ps = connection.prepareStatement(upit1);

        for (IzmeneOtpremnice izmena : o.getIzmenaOtpremnice()) {
            ps.setInt(1, izmena.getRb());
            ps.setLong(2, o.getSifraOtpremnice());
            ps.setDate(3, new Date(o.getDatumKreiranja().getTime()));
            ps.setInt(4, izmena.getNosnja().getSifraNosnje());
            ps.executeUpdate();
        }

        if (o.getStavkaOtpremnice().isEmpty()) {
            upit = "UPDATE otpremnica SET aktivna = ? WHERE sifraOtpremnice = ?";
            ps = connection.prepareStatement(upit);
            ps.setBoolean(1, false);
            ps.setLong(2, o.getSifraOtpremnice());
            ps.executeUpdate();
        }

        return o;
    }

    @Override
    public List<Otpremnica> vratiSveO() throws Exception {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String upit = "SELECT * FROM otpremnica o JOIN clan c ON (o.brojCK = c.brojCK)";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(upit);

        List<Otpremnica> lista = new ArrayList<>();

        while (rs.next()) {
            Otpremnica o = new Otpremnica();
            Long sifra = rs.getLong("sifraOtpremnice");
            Date datum = rs.getDate("datumKreiranja");
            boolean aktivna = rs.getBoolean("aktivna");

            Clan clan = new Clan();

            Long brojCK = rs.getLong("brojCK");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            Pol pol = Pol.valueOf(rs.getString("pol"));
            double visina = rs.getDouble("visina");
            Ansambl ansambl = Ansambl.valueOf(rs.getString("ansambl"));
            boolean aktivan = rs.getBoolean("aktivan");

            clan.setBrojCK(brojCK);
            clan.setIme(ime);
            clan.setPrezime(prezime);
            clan.setPol(pol);
            clan.setVisina(visina);
            clan.setAnsambl(ansambl);
            clan.setAktivan(aktivan);

            o.setSifraOtpremnice(sifra);
            o.setDatumKreiranja(datum);
            o.setAktivna(aktivna);
            o.setClan(clan);

            lista.add(o);
        }

        return lista;
    }

    @Override
    public Otpremnica vratiOtp(Long sifraOtpremnice) throws Exception {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String upit = "SELECT * FROM otpremnica o LEFT JOIN clan c ON (o.brojCK = c.brojCK) "
                + "WHERE o.sifraOtpremnice = " + sifraOtpremnice;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(upit);

        Otpremnica o = new Otpremnica();

        if (rs.next()) {

            o.setSifraOtpremnice(rs.getLong("sifraOtpremnice"));
            o.setDatumKreiranja(rs.getDate("datumKreiranja"));
            o.setAktivna(rs.getBoolean("aktivna"));

            Clan clan = new Clan();
            clan.setBrojCK(rs.getLong("brojCK"));
            clan.setIme(rs.getString("ime"));
            clan.setPrezime(rs.getString("prezime"));
            o.setClan(clan);

            List<StavkaOtpremnice> stavkeOtpremnice = new ArrayList<>();
            upit = "SELECT * FROM stavkaotpremnice st JOIN nosnja1 n ON (st.sifraNosnje = n.sifraNosnje) WHERE sifraOtpremnice = " + sifraOtpremnice;
            rs = statement.executeQuery(upit);
            while (rs.next()) {
                StavkaOtpremnice st = new StavkaOtpremnice();
                st.setRedniBroj(rs.getInt("rb"));
                st.setKolicina(rs.getLong("kolicina"));

                Nosnja nosnja = new Nosnja();
                nosnja.setSifraNosnje(rs.getInt("sifraNosnje"));
                nosnja.setNazivNosnje(rs.getString("nazivNosnje"));
                nosnja.setVrstaNosnje(VrstaNosnje.valueOf(rs.getString("vrstaNosnje")));
                st.setNosnja(nosnja);

                stavkeOtpremnice.add(st);
            }
            o.setStavkaOtpremnice(stavkeOtpremnice);

            List<IzmeneOtpremnice> izmeneOtpremnice = new ArrayList<>();
            upit = "SELECT * FROM izmeneotpremnice iz JOIN nosnja1 n ON (iz.sifraNosnje = n.sifraNosnje) WHERE sifraOtpremnice = " + sifraOtpremnice;
            rs = statement.executeQuery(upit);
            while (rs.next()) {
                IzmeneOtpremnice iz = new IzmeneOtpremnice();
                iz.setRb(rs.getInt("rb"));
                iz.setDatumIzmene(rs.getDate("datumIzmene"));

                Nosnja nosnja = new Nosnja();
                nosnja.setSifraNosnje(rs.getInt("sifraNosnje"));
                nosnja.setNazivNosnje(rs.getString("nazivNosnje"));
                nosnja.setVrstaNosnje(VrstaNosnje.valueOf(rs.getString("vrstaNosnje")));
                iz.setNosnja(nosnja);

                izmeneOtpremnice.add(iz);
            }
            o.setIzmenaOtpremnice(izmeneOtpremnice);

        }

        return o;
    }

    @Override
    public List<Otpremnica> vratiOtpPoKr(String sifra, String clan1) throws Exception {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String upit = "SELECT * FROM otpremnica o LEFT JOIN clan c ON (o.brojCK = c.brojCK)";

        String ime = null;
        String prezime = null;
        
        if (!clan1.equals("Izaberite clana")) {
            ime = clan1.split(" ")[0];
            prezime = clan1.split(" ")[1];
        }

        if (!sifra.isEmpty() && !clan1.equals("Izaberite clana")) {
            upit += " WHERE o.sifraOtpremnice LIKE '%" + sifra + "%' AND c.ime LIKE '%"+ime+"%' AND c.prezime LIKE '%" + prezime + "%'";
        }else if(!sifra.isEmpty()){
            upit += " WHERE o.sifraOtpremnice LIKE '%" + sifra + "%'";
        }else if(!clan1.equals("Izaberite clana")){
            upit += " WHERE c.ime LIKE '%"+ime+"%' AND c.prezime LIKE '%" + prezime + "%'";
        }

        List<Otpremnica> lista = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        
        Otpremnica o = null;
        Clan clan = null;
        
        while (rs.next()) {
            o = new Otpremnica();
            Long sifra1 = rs.getLong("sifraOtpremnice");
            Date datum = rs.getDate("datumKreiranja");
            boolean aktivna = rs.getBoolean("aktivna");

            clan = new Clan();

            Long brojCK = rs.getLong("brojCK");
            String ime1 = rs.getString("ime");
            String prezime1 = rs.getString("prezime");
            Pol pol = Pol.valueOf(rs.getString("pol"));
            double visina = rs.getDouble("visina");
            Ansambl ansambl = Ansambl.valueOf(rs.getString("ansambl"));
            boolean aktivan = rs.getBoolean("aktivan");

            clan.setBrojCK(brojCK);
            clan.setIme(ime1);
            clan.setPrezime(prezime1);
            clan.setPol(pol);
            clan.setVisina(visina);
            clan.setAnsambl(ansambl);
            clan.setAktivan(aktivan);

            o.setSifraOtpremnice(sifra1);
            o.setDatumKreiranja(datum);
            o.setAktivna(aktivna);
            o.setClan(clan);

            lista.add(o);
        }

        return lista;
    }

    @Override
    public void obrisiO(Long sifra) throws Exception {
        Connection conn = ConnectionFactory.getInstance().getConnection();
        
        String upit1 = "DELETE FROM stavkaotpremnice WHERE sifraOtpremnice = ?";
        PreparedStatement pss = conn.prepareStatement(upit1);
        pss.setLong(1, sifra);
        pss.executeUpdate();
        
        upit1 = "DELETE FROM izmeneotpremnice WHERE sifraOtpremnice = ?";
        pss = conn.prepareStatement(upit1);
        pss.setLong(1, sifra);
        pss.executeUpdate();
        
        String query = "DELETE FROM otpremnica WHERE sifraOtpremnice = ?";
            
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1, sifra);
        ps.executeUpdate();
            
        ps.close();
    }

}
