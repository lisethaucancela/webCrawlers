/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawlers;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Liseth
 */
public class ScrapeTest {

    Scrape scrape;

    public ScrapeTest() {
        scrape = new Scrape();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getEntry method, of class Scrape.
     */
    @Test
    public void testGetEntry() throws Exception {
        System.out.println("getEntry");
        int maxEntries = 30;
        scrape.setMaxEntry(maxEntries);
        scrape.getEntry();
        assertEquals(maxEntries, scrape.getLista().size());
    }

    /**
     * Test of filter method, of class Scrape.
     */
    @Test
    public void testFilter() {
        System.out.println("filter");
        Entry entry1 = new Entry();
        entry1.setTitle("Prueba de filtrado");
        entry1.setNumOrder("1");
        entry1.setPoints(10);
        entry1.setComments(5);
        Entry entry2 = new Entry();
        entry2.setTitle("Prueba de filtrado 2");
        entry2.setNumOrder("2");
        entry2.setPoints(56);
        entry2.setComments(23);
        Entry entry3 = new Entry();
        entry3.setTitle("Prueba de filtrado 3");
        entry3.setNumOrder("2");
        entry3.setPoints(1);
        entry3.setComments(6);

        scrape.getLista().add(entry1);
        scrape.getLista().add(entry2);
        scrape.getLista().add(entry3);

        List<TreeSet> lista = scrape.filter();

        Iterator it = lista.get(0).iterator();
        Entry lista1 = (Entry) it.next();
        assertEquals(56, lista1.getPoints());
        lista1 = (Entry) it.next();
        assertEquals(10, lista1.getPoints());
        lista1 = (Entry) it.next();
        assertEquals(1, lista1.getPoints());

    }

    /**
     * Test of parseInt method, of class Scrape.
     */
    @Test
    public void testParseInt() {
        System.out.println("parseInt");
        String s = "4";
        int expResult = 4;
        int result = scrape.parseInt(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of contarPalabras method, of class Scrape.
     */
    @Test
    public void testContarPalabras() {
        System.out.println("contarPalabras");
        String palabra = "Prueba de palabras";
        int expResult = 3;
        int result = scrape.contarPalabras(palabra);
        assertEquals(expResult, result);
    }

}
