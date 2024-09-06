/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.threads;

import static rs.ac.bg.fon.ai.zajednicki.communication.Operation.ADD_KORISNIK;
import static rs.ac.bg.fon.ai.zajednicki.communication.Operation.GET_ALL_KORISNICI;
import static rs.ac.bg.fon.ai.zajednicki.communication.Operation.GET_KORISNIK;
import static rs.ac.bg.fon.ai.zajednicki.communication.Operation.LOGIN;
import rs.ac.bg.fon.ai.zajednicki.communication.Receiver;
import rs.ac.bg.fon.ai.zajednicki.communication.Request;
import rs.ac.bg.fon.ai.zajednicki.communication.Response;
import rs.ac.bg.fon.ai.zajednicki.communication.Sender;
import rs.ac.bg.fon.ai.server.controller.Controller;
import rs.ac.bg.fon.ai.zajednicki.domain.Bicikl;
import rs.ac.bg.fon.ai.zajednicki.domain.Iznajmljivanje;
import rs.ac.bg.fon.ai.zajednicki.domain.Korisnik;
import rs.ac.bg.fon.ai.zajednicki.domain.Statistika;
import rs.ac.bg.fon.ai.zajednicki.domain.StavkaIznajmljivanja;
import rs.ac.bg.fon.ai.zajednicki.domain.Zaposleni;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class ProcessingClientRequests extends Thread {
    Socket socket;
    Sender sender;
    Receiver receiver;
    boolean end = false;

    public ProcessingClientRequests(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }    

    @Override
    public void run() {
        while(!end){
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                
                switch (request.getOperation()) {
                    case LOGIN:
                        Zaposleni z = (Zaposleni) request.getArgument();
                        z = Controller.getInstance().login(z);
                        response.setResult(z);
                        break;
                    case GET_ALL_KORISNICI:
                        List<Korisnik> korisnici = Controller.getInstance().getAllKorisnici();
                        response.setResult(korisnici);
                        break;
                    case DELETE_KORISNIK:
                        try {
                            Korisnik k = (Korisnik) request.getArgument();
                            Controller.getInstance().deleteKorisnik(k);
                            response.setResult(null);
                        } catch (Exception e) {
                            response.setResult(e);
                        }
                        break;
                    case ADD_KORISNIK:
                        try {
                            Korisnik k = (Korisnik) request.getArgument();
                            Controller.getInstance().addKorisnik(k);
                            response.setResult(null);
                        } catch (Exception e) {
                            response.setResult(e);
                        }
                        break;
                    case EDIT_KORISNIK:
                        try {
                            Korisnik ko = (Korisnik) request.getArgument();
                            Controller.getInstance().editKorisnik(ko);
                            response.setResult(null);
                        } catch (Exception e) {
                            response.setResult(e);
                        }
                        break;
                    case GET_ALL_BICIKLE:
                        List<Bicikl> bicikle = Controller.getInstance().getAllBicikle();
                        response.setResult(bicikle);
                        break;
                    case ADD_NEW_IZNAJMLJIVANJE:
                        try {
                            Iznajmljivanje i = (Iznajmljivanje) request.getArgument();
                            Controller.getInstance().addIznajmljivanje(i);
                            response.setResult(null);
                        } catch (Exception e) {
                            response.setResult(e);
                        }
                        break;
                    case GET_ALL_IZNAJMLJIVANJA:
                        List<Iznajmljivanje> iznajmljivanja = Controller.getInstance().getAllIznajmljivanja();
                        response.setResult(iznajmljivanja);
                        break;
                    case GET_ALL_STAVKE_IZNAJMLJIVANJA:
                        Iznajmljivanje iz = (Iznajmljivanje) request.getArgument();
                        List<StavkaIznajmljivanja> stavke = Controller.getInstance().getAllStavke(iz);
                        response.setResult(stavke);
                        break;
                    case EDIT_IZNAJMLJIVANJE:
                        try {
                            Iznajmljivanje izn = (Iznajmljivanje) request.getArgument();
                            Controller.getInstance().editIznajmljivanje(izn);
                            response.setResult(null);
                        } catch (Exception e) {
                            response.setResult(e);
                        }
                        break;
                    case DELETE_IZNAJMLJIVANJE:
                        try {
                            Iznajmljivanje izna = (Iznajmljivanje) request.getArgument();
                            Controller.getInstance().deleteIznajmljivanje(izna);
                            response.setResult(null);
                        } catch (Exception e) {
                            response.setResult(e);
                        }
                        break;
                    case GET_ALL_STATISTIKA:
                        List<Statistika> statistike = Controller.getInstance().getAllStatistike();
                        response.setResult(statistike);
                        break;
                    case GET_KORISNIK:
                        try {
                            Korisnik k = (Korisnik) request.getArgument();
                            Korisnik korisnik = Controller.getInstance().getKorisnik(k);
                            response.setResult(korisnik);
                        } catch (Exception e) {
                            response.setResult(e);
                        }
                        break;
                    case GET_IZNAJMLJIVANJE:
                        try {
                            Iznajmljivanje i = (Iznajmljivanje) request.getArgument();
                            Iznajmljivanje iznajmljivanje = Controller.getInstance().getIznajmljivanje(i);
                            response.setResult(iznajmljivanje);
                        } catch (Exception e) {
                            response.setResult(e);
                        }
                        break;
                    default:
                        System.out.println("Greska, ta operacija ne postoji!");
                }
                sender.send(response);
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
        }
    }
    
    
    public void stopThread() {
        end = true;
        try {
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        interrupt();
    }
    
    
}
