/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawlers;

import java.util.Comparator;

/**
 *
 * @author Liseth
 */
public class ComparerPoints implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Entry e1 = (Entry) o1;
        Entry e2 = (Entry) o2;
        if (e1.getPoints() < e2.getPoints()) {
            return 1;
        } else {
            return -1;
        }
    }

}
