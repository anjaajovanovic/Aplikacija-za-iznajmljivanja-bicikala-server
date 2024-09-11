/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operation.login;

import rs.ac.bg.fon.ai.zajednicki.domain.Zaposleni;
import java.util.List;
import rs.ac.bg.fon.ai.server.operation.AbstractGenericOperation;

/**
 * Klasa koja omogućava izvršavanje operacije prijave za zaposlenog u sistemu.
 * 
 * Ova klasa nasleđuje {@link AbstractGenericOperation} i implementira
 * logiku za prijavu zaposlenog tako što proverava da li zaposleni postoji
 * u bazi podataka i postavlja odgovarajući objekat {@link Zaposleni}.
 *  
 * @author PC
 */
public class LoginOperation extends AbstractGenericOperation {

	/**
     * Zaposleni koji je uspešno prijavljen.
     */
    Zaposleni zaposleni;
    
    /**
     * Nepoznata promenljiva.
     */
    public String getZaposleni;

    /**
     * Vraća objekat {@link Zaposleni} koji je uspešno prijavljen.
     * 
     * @return Zaposleni koji je prijavljen
     */
    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    /**
     * Proverava preduslove za izvršenje operacije.
     * 
     * @param param Objekat koji treba da bude instanca {@link Zaposleni}
     * @throws Exception Ako je parametar null
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null) {
            throw new Exception("Sistem nije mogao da pronadje zaposlenog.");
        }
    }

    /**
     * Izvršava operaciju prijave zaposlenog.
     * 
     * Pretražuje sve zaposlene u bazi podataka i upoređuje ih sa prosleđenim
     * objektom {@link Zaposleni}. Ako se zaposleni nalazi u bazi, postavlja
     * odgovarajući objekat {@link Zaposleni}.
     * 
     * @param parametar Objekat {@link Zaposleni} koji se koristi za prijavu
     * @param key Ključ koji se koristi za pretragu u bazi podataka
     * @throws Exception Ako dođe do greške tokom pretrage
     */
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
