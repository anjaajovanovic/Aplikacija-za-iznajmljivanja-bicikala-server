/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.login;

import rs.ac.bg.fon.ai.zajednicki.domain.Zaposleni;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 *
 * @author PC
 */
public class LoginOperation extends AbstractGenericOperation {

    Zaposleni zaposleni;
    public String getZaposleni;

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null) {
            throw new Exception("Sistem nije mogao da pronadje zaposlenog.");
        }
    }

    @Override
    protected void executeOperation(Object parametar, String key) throws Exception {
        List<Zaposleni> sviZaposleni = broker.getAll((Zaposleni) parametar, null);
        System.out.println("KLASA LoginOperation SO: " + sviZaposleni);

        if (sviZaposleni.contains((Zaposleni) parametar)) {
            for (Zaposleni z : sviZaposleni) {
                if (z.equals((Zaposleni) parametar)) {
                    zaposleni = z;
                }
            }
        } else {
            zaposleni = null;
        }

    }

}
