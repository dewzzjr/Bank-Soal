/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

/**
 *
 * @author Dewangga P. Praja
 */
public class Soal implements java.io.Serializable{
    private final String [] pilihan;
    private final String soal;
    private final PokokBahasan parent;

    public Soal(String soal, PokokBahasan parent) {
        this.pilihan = new String[5];
        this.soal = soal;
        this.parent = parent;
    }

    public PokokBahasan getParent() {
        return parent;
    }
    
    public String getSoal() {
        return soal;
    }
    
    public String getJawaban() {
        return pilihan[0];
    }
    
    public String[] getPilihan() {
        return trimArray();
    }
    
    public String getPilihan(int index) {
        return pilihan[index];
    }
    
    public void setPilihan(int index, String pilihan) {
        this.pilihan[index] = pilihan;
    }
    
    private String[] trimArray () {
        int count = 0;
        for (String i : pilihan) {
            if (! i.isEmpty()) {
                count++;
            }
        }

        String[] newArray = new String[count];

        int index = 0;
        for (String i : pilihan) {
            if (! i.isEmpty()) {
                newArray[index++] = i;
            }
        }
        
        return newArray;
    }

}
