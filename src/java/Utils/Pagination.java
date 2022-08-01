/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.ArrayList;

/**
 *
 * @author sannx
 */
public class Pagination {

    public static ArrayList<String> get(int page, int total) {
        int current = page, last = total, delta = 2, left = current - delta, right = current + delta + 1, l = -1;
        ArrayList<Integer> range = new ArrayList<>();
        ArrayList<String> rangeWithDots = new ArrayList<>();

        for (int i = 1; i <= last; i++) {
            if (i == 1 || i == last || i >= left && i < right) {
                range.add(i);
            }
        }

        for (Integer i : range) {
            if (l != -1) {
                if (i - l == 2) {
                    rangeWithDots.add(Integer.toString(l + 1));
                } else if (i - l != 1) {
                    rangeWithDots.add("...");
                }
            }
            rangeWithDots.add(Integer.toString(i));
            l = i;
        }

        return rangeWithDots;
    }
}
