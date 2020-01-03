/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.impl.database;

import database.connection.ConnectionFactory;
import domain.Nosnja;
import domain.enumeracije.Pol;
import domain.enumeracije.VrstaBeline;
import domain.enumeracije.VrstaNosnje;
import domain.enumeracije.VrstaOpanaka;
import storage.StorageNosnja;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nemanja
 */
public class StorageDatabaseNosnja implements StorageNosnja {

    @Override
    public Nosnja sacuvajN(Nosnja n) throws Exception {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String upit = "INSERT INTO nosnja1(vrstaNosnje, pol, nazivNosnje,  kolicina, opisNosnje, vrstaBeline, vrstaOpanaka, velicina) VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, String.valueOf(n.getVrstaNosnje()));
        ps.setString(2, String.valueOf(n.getPol()));
        ps.setString(3, n.getNazivNosnje());
        ps.setInt(4, n.getKolicina());
        ps.setString(5, n.getOpis());
        ps.setString(6, String.valueOf(n.getVrstaBeline()));
        ps.setString(7, String.valueOf(n.getVrstaOpanaka()));
        ps.setString(8, n.getVelicina());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        while (rs.next()) {
            int id = rs.getInt(1);
            n.setSifraNosnje(id);
        }
        ps.close();
        return n;

    }

    @Override
    public Nosnja izmeniN(Nosnja n) throws Exception {
        Connection connection = ConnectionFactory.getInstance().getConnection();

        String upit = "UPDATE nosnja1 SET vrstaNosnje = ?, pol = ?, nazivNosnje = ?, "
                + "kolicina = ?, opisNosnje = ?, vrstaBeline = ?, vrstaOpanaka = ?, "
                + "velicina = ? WHERE sifraNosnje = ?";

        PreparedStatement ps = connection.prepareStatement(upit);

        ps.setString(1, String.valueOf(n.getVrstaNosnje()));
        ps.setString(2, String.valueOf(n.getPol()));
        ps.setString(3, n.getNazivNosnje());
        ps.setInt(4, n.getKolicina());
        ps.setString(5, n.getOpis());
        ps.setString(6, String.valueOf(n.getVrstaBeline()));
        ps.setString(7, String.valueOf(n.getVrstaOpanaka()));
        ps.setString(8, n.getVelicina());
        ps.setInt(9, n.getSifraNosnje());

        ps.executeUpdate();

        ps.close();
        return n;

    }

    
    @Override
    public void obrisiN(int sifra) throws Exception {
        Connection conn = ConnectionFactory.getInstance().getConnection();

        String upit = "DELETE FROM nosnja1 WHERE sifraNosnje = ?";

        PreparedStatement ps = conn.prepareStatement(upit);
        ps.setInt(1, sifra);
        ps.executeUpdate();

        ps.close();
    }

    @Override
    public List<Nosnja> vratiListuN() throws SQLException {
        Connection connection = ConnectionFactory.getInstance().getConnection();

        String upit = "SELECT * FROM nosnja1";

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(upit);

        List<Nosnja> nosnje = new ArrayList<>();

        while (rs.next()) {
            Nosnja n = new Nosnja();

            int sifra = rs.getInt("sifraNosnje");
            VrstaNosnje vrstaNosnje = VrstaNosnje.valueOf(rs.getString("vrstaNosnje"));
            Pol pol = Pol.valueOf(rs.getString("pol"));
            String nazivNosnje = rs.getString("nazivNosnje");
            int kolicina = rs.getInt("kolicina");
            String opis = rs.getString("opisNosnje");
            String vrstaBeline1 = rs.getString("vrstaBeline");
            String velicina = rs.getString("velicina");
            String vrstaOpanaka1 = rs.getString("vrstaOpanaka");

            n.setSifraNosnje(sifra);
            n.setVrstaNosnje(vrstaNosnje);
            n.setPol(pol);
            n.setNazivNosnje(nazivNosnje);
            n.setKolicina(kolicina);
            n.setOpis(opis);
            n.setVrstaBeline(vrstaBeline1);
            n.setVelicina(velicina);
            n.setVrstaOpanaka(vrstaOpanaka1);

            nosnje.add(n);

        }
        return nosnje;
    }

    @Override
    public Nosnja vratiN(int sifraNosnje) {
        Connection connection;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            String upit = "SELECT * FROM nosnja1 where sifraNosnje = " + sifraNosnje;

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(upit);

            Nosnja n = null;
            
            //List<Nosnja> nosnje = new ArrayList<>();
            
            if (rs.next()) {
                int sifra = rs.getInt("sifraNosnje");
                VrstaNosnje vrstaNosnje = VrstaNosnje.valueOf(rs.getString("vrstaNosnje"));
                Pol pol = Pol.valueOf(rs.getString("pol"));
                String nazivNosnje = rs.getString("nazivNosnje");
                int kolicina = rs.getInt("kolicina");
                String opis = rs.getString("opisNosnje");
                String vrstaBeline1 = rs.getString("vrstaBeline");
                String velicina = rs.getString("velicina");
                String vrstaOpanaka1 = rs.getString("vrstaOpanaka");

                n = new Nosnja();
                n.setSifraNosnje(sifra);
                n.setVrstaNosnje(vrstaNosnje);
                n.setPol(pol);
                n.setNazivNosnje(nazivNosnje);
                n.setKolicina(kolicina);
                n.setOpis(opis);
                n.setVrstaBeline(vrstaBeline1);
                n.setVelicina(velicina);
                n.setVrstaOpanaka(vrstaOpanaka1);
                
                
            }
            return n;
        } catch (SQLException ex) {
            Logger.getLogger(StorageDatabaseNosnja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Nosnja> vratiNosnjuPoK(String kriterijumPretrage, String naziv, String vrsta) throws Exception{
        Connection connection = ConnectionFactory.getInstance().getConnection();

        String upit = "SELECT * FROM nosnja1";
        
        if(!kriterijumPretrage.isEmpty() && !naziv.isEmpty() && !vrsta.equals("Izaberite_vrstu_nosnje")){
            upit += " WHERE sifraNosnje LIKE '%" + kriterijumPretrage + "%' AND nazivNosnje LIKE '%" + naziv + "%' AND vrstaNosnje LIKE '%" + vrsta + "%'";
        }else if(!kriterijumPretrage.isEmpty() && !naziv.isEmpty()){
            upit += " WHERE sifraNosnje LIKE '%" + kriterijumPretrage + "%' AND nazivNosnje LIKE '%" + naziv + "%'";
        }else if(!kriterijumPretrage.isEmpty() && !vrsta.equals("Izaberite_vrstu_nosnje") ){
            upit += " WHERE sifraNosnje LIKE '%" + kriterijumPretrage + "%' AND vrstaNosnje LIKE '%" + vrsta + "%'";
        }else if(!naziv.isEmpty() && !vrsta.equals("Izaberite_vrstu_nosnje")){
            upit += " WHERE nazivNosnje LIKE '%" + naziv + "%' AND vrstaNosnje LIKE '%" + vrsta + "%'";
        }else if(!kriterijumPretrage.isEmpty()){
            upit += " WHERE sifraNosnje LIKE '%" + kriterijumPretrage + "%'";
        }else if(!naziv.isEmpty()){
            upit += " WHERE nazivNosnje LIKE '%" + naziv + "%'";
        }else if(!"Izaberite_vrstu_nosnje".equals(vrsta)){
            upit += " WHERE vrstaNosnje LIKE '%" + vrsta + "%'";
        }
        
        Statement stat = connection.createStatement();
        
        ResultSet rs = stat.executeQuery(upit);
        List<Nosnja> nosnje = new ArrayList<>();
        Nosnja n = null;
        
        while(rs.next()) {
                int sifra = rs.getInt("sifraNosnje");
                VrstaNosnje vrstaNosnje = VrstaNosnje.valueOf(rs.getString("vrstaNosnje"));
                Pol pol = Pol.valueOf(rs.getString("pol"));
                String nazivNosnje = rs.getString("nazivNosnje");
                int kolicina = rs.getInt("kolicina");
                String opis = rs.getString("opisNosnje");
                String vrstaBeline1 = rs.getString("vrstaBeline");
                String velicina = rs.getString("velicina");
                String vrstaOpanaka1 = rs.getString("vrstaOpanaka");

                n = new Nosnja();
                n.setSifraNosnje(sifra);
                n.setVrstaNosnje(vrstaNosnje);
                n.setPol(pol);
                n.setNazivNosnje(nazivNosnje);
                n.setKolicina(kolicina);
                n.setOpis(opis);
                n.setVrstaBeline(vrstaBeline1);
                n.setVelicina(velicina);
                n.setVrstaOpanaka(vrstaOpanaka1);
                
                nosnje.add(n);
                
        }
        return nosnje;
    }

}

