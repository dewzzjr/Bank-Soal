/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import java.util.ArrayList;

/**
 * An Interface for data class.
 * @author Dewangga P. Praja
 * @param <D> Data
 */
public interface Data<D> extends java.io.Serializable {
    public String getNama();
    public D get(int index);
    public ArrayList<D> get();
    public D getLast();
    public boolean add(D data);
    public boolean add(ArrayList<D> data);
    public int length();
}
