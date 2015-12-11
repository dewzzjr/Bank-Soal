/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import java.util.ArrayList;

/**
 * MataKuliah which include PokokBahasan inside.
 * @author Dewangga P. Praja
 */
public class MataKuliah implements Data<PokokBahasan> {
    
    private final String nama;
    private final ArrayList<PokokBahasan> bahasan;

    public MataKuliah(String nama) {
        this.bahasan = new ArrayList<>();
        this.nama = nama;
    }

    @Override
    public String getNama() {
        return nama;
    }

    @Override
    public PokokBahasan get(int index) {
        return bahasan.get(index);
    }
    
    @Override
    public ArrayList<PokokBahasan> get() {
        return bahasan;
    }
    
    @Override
    public boolean add(PokokBahasan s) {
        boolean success = this.bahasan.add(s);
        return success;
    }
    
    @Override
    public boolean add(ArrayList<PokokBahasan> s) {
        boolean success = this.bahasan.addAll(s);
        return success;
    }

    @Override
    public PokokBahasan getLast() {
        return get(length()-1);
    }

    @Override
    public int length() {
        return bahasan.size();
    }
}
