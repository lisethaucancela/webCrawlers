/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawlers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Liseth
 */
public class WebCrawlers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            int maxEntry = 30;
            Scrape scr = new Scrape();
            scr.setMaxEntry(maxEntry);
            scr.getEntry();

        } catch (IOException ex) {
            Logger.getLogger(WebCrawlers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
