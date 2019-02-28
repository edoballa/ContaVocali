/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contavocali;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * La classe gestisce un buffer di stringhe che contiene tutto quanto deve 
 * essere visualizzato sullo schermo
 * 
 * @author ballabio_edoardo
 */
public class Schermo {

    /**
     * coda di stringhe usata dai thread per la visualizzazione su schermo
     */
    private Queue<String> buffer;

    public Schermo() {
        this.buffer = new ArrayDeque();
    }
   
    public synchronized void add(String str) {
        buffer.add(str);
    }
    
    @Override
    public synchronized String toString() {
        return buffer.toString();
    }

    synchronized void reset() {
        buffer.clear();
    }
}
