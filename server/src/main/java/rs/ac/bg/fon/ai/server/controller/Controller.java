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
 *
 * @author PC
 */
public class Controller {
    private static Controller instance;
    
    private Controller(){
    }
    
    public static Controller getInstance(){
        if (instance == null){
            instance = new Controller();
        }
        return instance;
    }

    public Zaposleni login(Zaposleni z) throws Exception {
        LoginOperation operation = new LoginOperation();
        
        operation.execute(z, null);
        System.out.println("KLASA CONTROLLER: "+ operation.getZaposleni);
        return operation.getZaposleni();
    }

    public void addKorisnik(Korisnik k) throws Exception {
        AddKorisnikOperation operation = new AddKorisnikOperation();
        operation.execute(k, null);
    }

    public void deleteKorisnik(Korisnik k) throws Exception {
        DeleteKorisnikOperation operation = new DeleteKorisnikOperation();
        operation.execute(k, null);
    }

    public void editKorisnik(Korisnik ko) throws Exception {
        EditKorisnikOperation operation = new EditKorisnikOperation();
        operation.execute(ko, null);
    }

    public List<Korisnik> getAllKorisnici() throws Exception {
        GetAllKorisniciOperation operation = new GetAllKorisniciOperation();
        operation.execute(null, null);
        System.out.println("KLASA CONTROLLER: "+ operation.getKorisnici());
        return operation.getKorisnici();
    }

    public List<Bicikl> getAllBicikle() throws Exception {
        GetAllBicikleOperation operation = new GetAllBicikleOperation();
        operation.execute(null, null);
        System.out.println("KLASA CONTROLLER: "+ operation.getBicikle());
        return operation.getBicikle();
    }

    public void addIznajmljivanje(Iznajmljivanje i) throws Exception {
        AddIznajmljivanjeOperation operation = new AddIznajmljivanjeOperation();
        operation.execute(i, null);
    }

    public List<Iznajmljivanje> getAllIznajmljivanja() throws Exception {
        GetAllIznajmljivanjaOperation operation = new GetAllIznajmljivanjaOperation();
        operation.execute(null, null);
        System.out.println("KLASA CONTROLLER: "+ operation.getIznajmljivanja());
        return operation.getIznajmljivanja();
    }

    public List<StavkaIznajmljivanja> getAllStavke(Iznajmljivanje i) throws Exception {
        GetAllStavkeOperation operation = new GetAllStavkeOperation();
        StavkaIznajmljivanja st = new StavkaIznajmljivanja();
        st.setIznajmljivanje(i);
        operation.execute(st, null);
        System.out.println("KLASA CONTROLLER: " + operation.getStavke());
        return operation.getStavke();
    }

    public void editIznajmljivanje(Iznajmljivanje izn) throws Exception {
       EditIznajmljivanjeOperation operation = new EditIznajmljivanjeOperation();
       operation.execute(izn, null);
    }

    public void deleteIznajmljivanje(Iznajmljivanje izna) throws Exception {
        DeleteIznajmljivanjeOperation operation = new DeleteIznajmljivanjeOperation();
        operation.execute(izna, null);
    }

    public List<Statistika> getAllStatistike() throws Exception {
        GetAllStatistikeOperation operation = new GetAllStatistikeOperation();
        operation.execute(null, null);
        System.out.println("KLASA CONTROLLER: "+ operation.getStatistike());
        return operation.getStatistike();
    }

    public Korisnik getKorisnik(Korisnik k) throws Exception {
        GetKorisnikOperation operation = new GetKorisnikOperation();
        operation.execute(k, null);
        return operation.getKorisnik();
    }

    public Iznajmljivanje getIznajmljivanje(Iznajmljivanje i) throws Exception {
        GetIznajmljivanjeOperation operation = new GetIznajmljivanjeOperation();
        operation.execute(i, null);
        return operation.getIznajmljivanje();
    }

    
    
}
