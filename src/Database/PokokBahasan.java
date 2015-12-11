/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import java.util.ArrayList;

/**
 * PokokBahasan which include Soal inside.
 * @author Dewangga P. Praja
 */
public class PokokBahasan implements Data<Soal>{
    private final MataKuliah parent;
    private final String nama;
    private final ArrayList<Soal> soal;

    public PokokBahasan(String nama, MataKuliah parent) {
        this.soal = new ArrayList<>();
        this.nama = nama;
        this.parent = parent;
    }

    public MataKuliah getParent() {
        return parent;
    }
    
    @Override
    public String getNama() {
        return nama;
    }

    @Override
    public Soal get(int index) {
        return soal.get(index);
    }
    
    @Override
    public ArrayList<Soal> get() {
        return soal;
    }
    
    @Override
    public boolean add(Soal s) {
        boolean success = this.soal.add(s);
        return success;
    }
    
    @Override
    public boolean add(ArrayList<Soal> s) {
        boolean success = this.soal.addAll(s);
        return success;
    }

    @Override
    public Soal getLast() {
        return get(length()-1);
    }

    @Override
    public int length() {
        return soal.size();
    }
}
