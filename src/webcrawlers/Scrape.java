/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Liseth
 */
public class Scrape {

    private List<Entry> Lista = new ArrayList<>();
    private int maxEntry;
    private static final String URL = "https://news.ycombinator.com";
    private Object HttpRequest;

    public void setMaxEntry(int maxEntry) {
        this.maxEntry = maxEntry;
    }

    public void getEntry() throws Exception {
        Elements element = connect();
        this.obtnerInfo(element);
        List<TreeSet> listaTree = this.filter();
        this.mostrarLista(listaTree);
    }

    public Elements connect() throws IOException {
        Elements element = null;
        if (requests.getStatusConnectionCode(URL) == 200) {
            Document doc = requests.getHtmlDocument(URL);
            element = doc.select("table.itemlist tr td").not("td.votelinks");
        }
        return element;
    }

    public List<TreeSet> filter() {
        ComparerPoints points = new ComparerPoints();
        ComparerComments comments = new ComparerComments();
        TreeSet tpoint = new TreeSet(points);
        TreeSet tcomment = new TreeSet(comments);

        for (Entry i : Lista) {
            int contPalabra = contarPalabras(i.getTitle());
            if (contPalabra > 5) {
                tcomment.add(i);
            } else {
                tpoint.add(i);
            }
        }
        List<TreeSet> listaTree = new ArrayList<>();
        listaTree.add(tpoint);
        listaTree.add(tcomment);
        return listaTree;

    }

    public void mostrarLista(List<TreeSet> lista) {
        try {
            System.out.println(" Less than or equal to five words in the title ordered by points.\n");
            for (Iterator it = lista.get(0).iterator(); it.hasNext();) {
                Entry lista1 = (Entry) it.next();
                System.out.println(lista1.getNumOrder() + " " + lista1.getTitle() + "\n   " + lista1.getPoints() + " puntos " + lista1.getComments() + " comentarios ");
            }
            System.out.println("\n More than five words in the title ordered by amount of comments first\n");
            for (Iterator it = lista.get(1).iterator(); it.hasNext();) {
                Entry lista1 = (Entry) it.next();
                System.out.println(lista1.getNumOrder() + " " + lista1.getTitle() + "\n   " + lista1.getPoints() + " puntos " + lista1.getComments() + " comentarios ");
            }

        } catch (Exception e) {
            System.err.println("Error mostrar: " + e);
        }
    }

    public void obtnerInfo(Elements element) {
        try {
            int j = 1;
            for (int i = 0; i <= element.size() - 4; i += 4) {
                Entry ent = new Entry();
                ent.setNumOrder(element.get(i).getElementsByClass("rank").text());

                ent.setTitle(element.get(i + 1).getElementsByClass("storylink").text());

                String points = element.get(i + 3).getElementsByClass("score").text().replaceAll("\\D", "");
                ent.setPoints(parseInt(points));

                String comment = element.get(i + 3).select("td > a[href^='item']").text().replaceAll("\\D", "");
                ent.setComments(parseInt(comment));

                Lista.add(ent);
                if (j == maxEntry) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error obtenerInfo: " + e);
        }
    }

    public int parseInt(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(s);
    }

    public int contarPalabras(String palabra) {
        int contador = 1, pos;
        palabra = palabra.trim();
        if (palabra.isEmpty()) {
            return 0;
        }
        pos = palabra.indexOf(" ");
        while (pos != -1) {
            contador++;
            pos = palabra.indexOf(" ", pos + 1);
        }
        return contador;
    }

    public List<Entry> getLista() {
        return Lista;
    }

    public void setLista(List<Entry> Lista) {
        this.Lista = Lista;
    }

}
