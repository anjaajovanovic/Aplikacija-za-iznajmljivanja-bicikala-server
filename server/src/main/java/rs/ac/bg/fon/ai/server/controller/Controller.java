/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.controller;

import rs.ac.bg.fon.ai.zajednicki.domain.*;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.bicikl.GetAllBicikleOperation;
import rs.ac.bg.fon.ai.server.operation.iznajmljivanje.AddIznajmljivanjeOperation;
import rs.ac.bg.fon.ai.server.operation.iznajmljivanje.DeleteIznajmljivanjeOperation;
import rs.ac.bg.fon.ai.server.operation.iznajmljivanje.EditIznajmljivanjeOperation;
import rs.ac.bg.fon.ai.server.operation.iznajmljivanje.GetAllIznajmljivanjaOperation;
import rs.ac.bg.fon.ai.server.operation.iznajmljivanje.GetIznajmljivanjeOperation;
import rs.ac.bg.fon.ai.server.operation.korisnici.AddKorisnikOperation;
import rs.ac.bg.fon.ai.server.operation.korisnici.DeleteKorisnikOperation;
import rs.ac.bg.fon.ai.server.operation.korisnici.EditKorisnikOperation;
import rs.ac.bg.fon.ai.server.operation.korisnici.GetAllKorisniciOperation;
import rs.ac.bg.fon.ai.server.operation.korisnici.GetKorisnikOperation;
import rs.ac.bg.fon.ai.server.operation.login.LoginOperation;
import rs.ac.bg.fon.ai.server.operation.statistika.GetAllStatistikeOperation;
import rs.ac.bg.fon.ai.server.operation.stavka.GetAllStavkeOperation;

/**
 * Klasa Controller koordinira i upravlja izvršavanjem različitih operacija u serverskoj aplikaciji. 
 * Obezbeđuje metode za rad sa poslovnom logikom vezanom za korisnike, bicikle, iznajmljivanja, stavke iznajmljivanja i statistike.
 * 
 * Klasa koristi singlton patern, što osigurava da postoji samo jedna instanca Controller-a tokom rada aplikacije.
 * 
 * Encapsulira logiku za izvršavanje različitih operacija tako što ih delegira odgovarajućim klasama operacija.
 * 
 * @author Anja Jovanovic
 */
public class Controller {
	
	/**
     * Instanca klase Controller, jedina dozvoljena instanca (singlton).
     */
    private static Controller instance;
    
    /**
     * Privatni konstruktor sprečava kreiranje objekata izvan klase, čime se osigurava primena singlton paterna.
     */
    private Controller(){
    }
    
    /**
     * Vraća jedinu instancu klase Controller. Ako instanca još uvek ne postoji, kreira se nova.
     * 
     * @return instanca klase Controller
     */
    public static Controller getInstance(){
        if (instance == null){
            instance = new Controller();
        }
        return instance;
    }

    /**
     * Izvršava login operaciju za prosleđenog zaposlenog.
     * 
     * @param z Zaposleni koji se prijavljuje
     * @return Zaposleni koji je uspešno prijavljen
     * @throws Exception ako dođe do greške prilikom prijave
     */
    public Zaposleni login(Zaposleni z) throws Exception {
        LoginOperation operation = new LoginOperation();
        
        operation.execute(z, null);
        System.out.println("KLASA CONTROLLER: "+ operation.getZaposleni);
        return operation.getZaposleni();
    }

    /**
     * Dodaje novog korisnika u sistem.
     * 
     * @param k Korisnik koji se dodaje
     * @throws Exception ako dođe do greške prilikom dodavanja korisnika
     */
    public void addKorisnik(Korisnik k) throws Exception {
        AddKorisnikOperation operation = new AddKorisnikOperation();
        operation.execute(k, null);
    }

    /**
     * Briše korisnika iz sistema.
     * 
     * @param k Korisnik koji se briše
     * @throws Exception ako dođe do greške prilikom brisanja korisnika
     */
    public void deleteKorisnik(Korisnik k) throws Exception {
        DeleteKorisnikOperation operation = new DeleteKorisnikOperation();
        operation.execute(k, null);
    }

    /**
     * Ažurira podatke o postojećem korisniku.
     * 
     * @param ko Korisnik čiji se podaci ažuriraju
     * @throws Exception ako dođe do greške prilikom ažuriranja korisnika
     */
    public void editKorisnik(Korisnik ko) throws Exception {
        EditKorisnikOperation operation = new EditKorisnikOperation();
        operation.execute(ko, null);
    }

    /**
     * Vraća listu svih korisnika iz baze.
     * 
     * @return lista svih korisnika
     * @throws Exception ako dođe do greške prilikom preuzimanja korisnika
     */
    public List<Korisnik> getAllKorisnici() throws Exception {
        GetAllKorisniciOperation operation = new GetAllKorisniciOperation();
        operation.execute(null, null);
        System.out.println("KLASA CONTROLLER: "+ operation.getKorisnici());
        return operation.getKorisnici();
    }

    /**
     * Vraća listu svih bicikala iz baze.
     * 
     * @return lista svih bicikala
     * @throws Exception ako dođe do greške prilikom preuzimanja bicikala
     */
    public List<Bicikl> getAllBicikle() throws Exception {
        GetAllBicikleOperation operation = new GetAllBicikleOperation();
        operation.execute(null, null);
        System.out.println("KLASA CONTROLLER: "+ operation.getBicikle());
        return operation.getBicikle();
    }

    /**
     * Dodaje novo iznajmljivanje u sistem.
     * 
     * @param i Iznajmljivanje koje se dodaje
     * @throws Exception ako dođe do greške prilikom dodavanja iznajmljivanja
     */
    public void addIznajmljivanje(Iznajmljivanje i) throws Exception {
        AddIznajmljivanjeOperation operation = new AddIznajmljivanjeOperation();
        operation.execute(i, null);
    }

    /**
     * Vraća listu svih iznajmljivanja iz baze.
     * 
     * @return lista svih iznajmljivanja
     * @throws Exception ako dođe do greške prilikom preuzimanja iznajmljivanja
     */
    public List<Iznajmljivanje> getAllIznajmljivanja() throws Exception {
        GetAllIznajmljivanjaOperation operation = new GetAllIznajmljivanjaOperation();
        operation.execute(null, null);
        System.out.println("KLASA CONTROLLER: "+ operation.getIznajmljivanja());
        return operation.getIznajmljivanja();
    }

    /**
     * Vraća listu svih stavki iznajmljivanja za zadato iznajmljivanje.
     * 
     * @param i Iznajmljivanje za koje se preuzimaju stavke
     * @return lista svih stavki iznajmljivanja
     * @throws Exception ako dođe do greške prilikom preuzimanja stavki
     */
    public List<StavkaIznajmljivanja> getAllStavke(Iznajmljivanje i) throws Exception {
        GetAllStavkeOperation operation = new GetAllStavkeOperation();
        StavkaIznajmljivanja st = new StavkaIznajmljivanja();
        st.setIznajmljivanje(i);
        operation.execute(st, null);
        System.out.println("KLASA CONTROLLER: " + operation.getStavke());
        return operation.getStavke();
    }

    /**
     * Ažurira podatke o postojećem iznajmljivanju.
     * 
     * @param izn Iznajmljivanje koje se ažurira
     * @throws Exception ako dođe do greške prilikom ažuriranja iznajmljivanja
     */
    public void editIznajmljivanje(Iznajmljivanje izn) throws Exception {
       EditIznajmljivanjeOperation operation = new EditIznajmljivanjeOperation();
       operation.execute(izn, null);
    }

    /**
     * Briše iznajmljivanje iz sistema.
     * 
     * @param izna Iznajmljivanje koje se briše
     * @throws Exception ako dođe do greške prilikom brisanja iznajmljivanja
     */
    public void deleteIznajmljivanje(Iznajmljivanje izna) throws Exception {
        DeleteIznajmljivanjeOperation operation = new DeleteIznajmljivanjeOperation();
        operation.execute(izna, null);
    }

    /**
     * Vraća listu svih statistika iz baze.
     * 
     * @return lista svih statistika
     * @throws Exception ako dođe do greške prilikom preuzimanja statistika
     */
    public List<Statistika> getAllStatistike() throws Exception {
        GetAllStatistikeOperation operation = new GetAllStatistikeOperation();
        operation.execute(null, null);
        System.out.println("KLASA CONTROLLER: "+ operation.getStatistike());
        return operation.getStatistike();
    }

    /**
     * Vraća podatke o korisniku na osnovu prosleđenog korisnika.
     * 
     * @param k Korisnik čiji se podaci preuzimaju
     * @return preuzeti Korisnik
     * @throws Exception ako dođe do greške prilikom preuzimanja korisnika
     */
    public Korisnik getKorisnik(Korisnik k) throws Exception {
        GetKorisnikOperation operation = new GetKorisnikOperation();
        operation.execute(k, null);
        return operation.getKorisnik();
    }

    /**
     * Vraća podatke o iznajmljivanju na osnovu prosleđenog iznajmljivanja.
     * 
     * @param i Iznajmljivanje čiji se podaci preuzimaju
     * @return preuzeto Iznajmljivanje
     * @throws Exception ako dođe do greške prilikom preuzimanja iznajmljivanja
     */
    public Iznajmljivanje getIznajmljivanje(Iznajmljivanje i) throws Exception {
        GetIznajmljivanjeOperation operation = new GetIznajmljivanjeOperation();
        operation.execute(i, null);
        return operation.getIznajmljivanje();
    }

    
    
}
