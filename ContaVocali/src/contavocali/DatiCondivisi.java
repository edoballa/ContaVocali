/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contavocali;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Classe che memorizza il numero di volta che viene ripetuta una vocale
 *
 * @author ballabio_edoardo
 */
public class DatiCondivisi {
    /**
     * booleane che indicano se i thread sono in thTerminato o no
     */
    private final boolean[] thTerminato;

    private Schermo schermo;
        
    private Vocali vocali;
    
    private Semaphore semaforoA;
    
    private Semaphore semaforoE;
    
    private Semaphore semaforoI;
    
    private Semaphore semaforoO;
    
    private Semaphore semaforoU;
    
    private Semaphore semaforoVisualizza;
    
    
    /**
     * @brief costruttore
     *
     * Inizializza le vocali, lo schermo e il vettore che indica quali thread sono terminati
     */
    public DatiCondivisi() {
        this.thTerminato = new boolean[Vocali.NUM_VOCALI];
        for (int i = 0; i < thTerminato.length; i++) {
            thTerminato[i] = false;
        }
        this.vocali = new Vocali();
        this.schermo = new Schermo();
        
        semaforoA=new Semaphore(0);
        semaforoE=new Semaphore(0);
        semaforoI=new Semaphore(0);
        semaforoO=new Semaphore(0);
        semaforoU=new Semaphore(0);
        semaforoVisualizza=new Semaphore(0);
    }

    public synchronized void resetDatiCondivisi() {
        for (int i = 0; i < thTerminato.length; i++) {
            thTerminato[i] = false;
        }
        this.vocali.reset();
        this.schermo.reset();
    }

    public void scriviSuSchermo(String str) {
        schermo.add(str);
    }
    
    /**
     * @brief controlla se i thread sono terminati
     *
     * @return true se tutti i thread sono terminati
     */
    public synchronized boolean sonoFinitiTutti() {
        boolean ris = true;
        for (int i = 0; i < 5; i++) {
            if (!thTerminato[i]) {
                ris = false;
            }
        }
        return ris;
    }

    /**
     * @brief set terminato
     *
     * imposta come terminato il thread corrispondente alla vocale data
     * @param vocale di cui impostare il thread come terminato
     */
    public synchronized void setFinito(char vocale) {
        thTerminato[vocali.getIndex(vocale)] = true;
    }

    public synchronized String getStringSchermo() {
        return schermo.toString();
    }
    
    public char getVocaleMax() {
        return vocali.getMax();
    }

    public void incNum(char vocale) {
        vocali.incNum(vocale);
    }
    
    public char getVocale(int index) {
        return vocali.getVocale(index);
    }
    
    public void WaitVisualizza() throws InterruptedException {
        semaforoA.acquire();
    } 
    
    public void WaitA() throws InterruptedException {
        semaforoA.acquire();
    }
    
    public void WaitE() throws InterruptedException {
        semaforoE.acquire();
    }
    
    public void WaitI() throws InterruptedException {
        semaforoI.acquire();
    }
    
    public void WaitO() throws InterruptedException {
        semaforoO.acquire();
    }
    
    public void WaitU() throws InterruptedException {
        semaforoU.acquire();
    }
    
    public void SignalVisualizza() {
        semaforoVisualizza.release();
    }
    
    public void SignalA() {
        semaforoA.release();
    }
    
    public void SignalE() {
        semaforoE.release();
    }
    
    public void SignalI() {
        semaforoI.release();
    }
    
    public void SignalO() {
        semaforoO.release();
    }
    
    public void SignalU() {
        semaforoU.release();
    }
}
