/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.impl.database;

import database.connection.ConnectionFactory;
import domain.Clan;
import domain.enumeracije.Ansambl;
import domain.enumeracije.Pol;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import storage.StorageClan;

/**
 *
 * @author Nemanja
 */
public class StorageDatabaseClan implements StorageClan{

    /**
     *
     * @param clan
     * @return
     * @throws Exception
     */
    @Override
    public Clan zapamtiC(Clan clan) throws Exception{
        Connection connection = (Connection) ConnectionFactory.getInstance().getConnection();
        
        String query = "INSERT INTO clan(ime, prezime, pol, visina, datumRodjenja, brojObuce, ansambl, datumUclanjenja, aktivan) VALUES (?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, clan.getIme());
        ps.setString(2, clan.getPrezime());
        ps.setString(3, String.valueOf(clan.getPol()));
        ps.setDouble(4, clan.getVisina());
        ps.setDate(5, new java.sql.Date(clan.getDatumRodjenja().getTime()));
        ps.setDouble(6, clan.getBrojObuce());
        ps.setString(7, String.valueOf(clan.getAnsambl()));
        ps.setDate(8, new java.sql.Date(clan.getDatumUclanjenja().getTime()));
        ps.setBoolean(9, clan.isAktivan());
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        while (rs.next()) {
            Long id = rs.getLong(1);
            clan.setBrojCK(id);
        }
        ps.close();
        return clan;
    }

    /**
     *
     * @param brojCK
     * @throws Exception
     */
    @Override
    public void obrisiC(Long brojCK) throws Exception{
        
        Connection conn = ConnectionFactory.getInstance().getConnection();
            
        String query = "DELETE FROM clan WHERE brojCK = ?";
            
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1, brojCK);
        ps.executeUpdate();
            
        ps.close();
           
    }

    /**
     *
     * @param clan
     * @return
     * @throws Exception
     */
    @Override
    public Clan izmeniC(Clan clan) throws Exception{
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String query = "UPDATE clan SET ime = ?, prezime = ?, pol = ?, visina = ?, datumRodjenja = ?, brojObuce = ?, ansambl = ?, datumUclanjenja = ?, aktivan = ? WHERE brojCK = ?";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, clan.getIme());
        ps.setString(2, clan.getPrezime());
        ps.setString(3, String.valueOf(clan.getPol()));
        ps.setDouble(4, clan.getVisina());
        ps.setDate(5, new java.sql.Date(clan.getDatumRodjenja().getTime()));
        ps.setDouble(6, clan.getBrojObuce());
        ps.setString(7, String.valueOf(clan.getAnsambl()));
        ps.setDate(8, new java.sql.Date(clan.getDatumUclanjenja().getTime()));
        ps.setBoolean(9, clan.isAktivan());
        ps.setLong(10, clan.getBrojCK());
        ps.executeUpdate();
        
        ps.close();
        return clan;
    }

    @Override
    public List<Clan> vratiListuC() throws Exception{
        List<Clan> clanovi = new ArrayList<>();
        Connection connection = ConnectionFactory.getInstance().getConnection();
        
        String query = "SELECT brojCK, ime, prezime, pol, visina, ansambl, aktivan FROM clan";
        
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
            Long brojCK = rs.getLong("brojCK");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            Pol pol = Pol.valueOf(rs.getString("pol"));
            double visina = rs.getDouble("visina");
            Ansambl ansambl = Ansambl.valueOf(rs.getString("ansambl"));
            boolean aktivan = rs.getBoolean("aktivan");
            
            Clan clan = new Clan();
            clan.setBrojCK(brojCK);
            clan.setIme(ime);
            clan.setPrezime(prezime);
            clan.setPol(pol);
            clan.setVisina(visina);
            clan.setAnsambl(ansambl);
            clan.setAktivan(aktivan);
            
            clanovi.add(clan);
        }
        rs.close();
        statement.close();
        return clanovi;
    }

    @Override
    public Clan vratiC(Long brojCK) throws Exception{
        Connection connection = ConnectionFactory.getInstance().getConnection();
        
        String query = "SELECT * FROM clan WHERE brojCK = ?";
        
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setLong(1, brojCK);
        
        ResultSet rs = ps.executeQuery();
        //List<Clan> clanovi = new ArrayList<>();
        Clan clan = null;
        
        if(rs.next()){
            Long brojCKK = rs.getLong("brojCK");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            Pol pol = Pol.valueOf(rs.getString("pol"));
            double visina = rs.getDouble("visina");
            Date datumRodjenja = rs.getDate("datumRodjenja");
            double brojObuce = rs.getDouble("brojObuce");
            Ansambl ansambl = Ansambl.valueOf(rs.getString("ansambl"));
            Date datumUclanjenja = rs.getDate("datumUclanjenja");
            boolean aktivan = rs.getBoolean("aktivan");
            
            clan = new Clan();
            
            clan.setBrojCK(brojCK);
            clan.setIme(ime);
            clan.setPrezime(prezime);
            clan.setPol(pol);
            clan.setVisina(visina);
            clan.setDatumRodjenja(datumRodjenja);
            clan.setBrojObuce(brojObuce);
            clan.setAnsambl(ansambl);
            clan.setDatumUclanjenja(datumUclanjenja);
            clan.setAktivan(aktivan);
            
            
        }
        return clan;
    }

    @Override
    public List<Clan> vratiClanovePoKr(String sifra, String ime, String prezime) throws Exception{
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String upit = "SELECT * FROM clan WHERE";
        
        if(!sifra.isEmpty() && !ime.isEmpty() && !prezime.isEmpty()){
            upit += " brojCK LIKE '%"+sifra+"%' AND ime LIKE '%" + ime + "%' AND prezime LIKE '%" +prezime+ "%'";
        }else if(!sifra.isEmpty() && !ime.isEmpty() ){
            upit += " brojCK LIKE '%"+sifra+"%' AND ime LIKE '%" + ime + "%'";
        }else if(!sifra.isEmpty() && !prezime.isEmpty()){
            upit += " brojCK LIKE '%"+sifra+"%' AND prezime LIKE '%" +prezime+ "%'";
        }else if(!ime.isEmpty() && !prezime.isEmpty()){
            upit += " ime LIKE '%" + ime + "%' AND prezime LIKE '%" +prezime+ "%'";
        }else if(!sifra.isEmpty()){
            upit += " brojCK LIKE '%"+sifra+"%'";
        }else if(!ime.isEmpty()){
            upit += " ime LIKE '%" + ime + "%'";
        }else if(!prezime.isEmpty()){
            upit += " prezime LIKE '%" +prezime+ "%'";
        }
        
        Statement stat = connection.createStatement();
        
        ResultSet rs = stat.executeQuery(upit);
        List<Clan> clanovi = new ArrayList<>();
        Clan clan = null;
        
        while(rs.next()){
            Long brojCKK = rs.getLong("brojCK");
            String ime1 = rs.getString("ime");
            String prezime1 = rs.getString("prezime");
            Pol pol = Pol.valueOf(rs.getString("pol"));
            double visina = rs.getDouble("visina");
            Date datumRodjenja = rs.getDate("datumRodjenja");
            double brojObuce = rs.getDouble("brojObuce");
            Ansambl ansambl = Ansambl.valueOf(rs.getString("ansambl"));
            Date datumUclanjenja = rs.getDate("datumUclanjenja");
            boolean aktivan = rs.getBoolean("aktivan");
            
            clan = new Clan();
            
            clan.setBrojCK(brojCKK);
            clan.setIme(ime1);
            clan.setPrezime(prezime1);
            clan.setPol(pol);
            clan.setVisina(visina);
            clan.setDatumRodjenja(datumRodjenja);
            clan.setBrojObuce(brojObuce);
            clan.setAnsambl(ansambl);
            clan.setDatumUclanjenja(datumUclanjenja);
            clan.setAktivan(aktivan);
            
            clanovi.add(clan);
        }
        return clanovi;
    }
    
    
    
    
}
