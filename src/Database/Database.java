/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * All Database of BankSoal which include MataKuliah inside.
 * @author Dewangga P. Praja and Ragesa Mario Junior
 */
public class Database implements Data<MataKuliah> {
    private static final ArrayList<MataKuliah> database = new ArrayList<>();
    private static final String nama = "Database"; 
    
    
    /**
     * Call this method instead of normal Constructor
     * @return Constructor of Database
     */
    public static Database getDatabase() {
        return new Database();
    }
    
    private Database(){}
    
    //<editor-fold defaultstate="collapsed" desc="Implements Data <MataKuliah>">
    @Override
    public String getNama() {
        return nama;
    }
    
    @Override
    public MataKuliah get(int index) {
        return database.get(index);
    }
    
    @Override
    public ArrayList<MataKuliah> get() {
        return database;
    }
    
    @Override
    public MataKuliah getLast() {
        return get(length()-1);
    }
    
    @Override
    public boolean add(MataKuliah data) {
        return database.add(data);
    }
    
    @Override
    public boolean add(ArrayList<MataKuliah> data) {
        return database.addAll(data);
    }
    
    @Override
    public int length() {
        return database.size();
    }
    //</editor-fold>
    
    /**
     * Check if the ArrayList of MataKuliah is empty or not
     * @return true if empty
     */
    public boolean isEmpty() {
        return database.isEmpty();
    }
    /**
     * Used to update the data
     * Remove the old data and call the new available data 
     * @param portable state of data availability
     * @return true if success
     */
    public boolean setData(boolean portable) {
        boolean success;
        if ( ! database.isEmpty() ) {
            database.clear();
        }
           
        if (portable) {
            try {
                openFile();
                success = true;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
                success = false;
            }
        } else {
            SQLHelper a;
            try {
                a = new SQLHelper();
                add( a.getMataKuliah() );
                success = true;
            } catch (SQLException e) {
                System.out.println(e);
                success = false;
            }
        }
        
        return success;
    }
    
    /**
     *
     * @param generate from MataKuliah Selected
     * @throws IOException
     */
    public void generateSoal (ArrayList<PokokBahasan> generate) throws IOException {
        ArrayList<Soal> all = new ArrayList<>();
        
        generate.stream().forEach((PokokBahasan pb) -> {
            all.addAll(pb.get());
        });
        
        Random n = new Random();
        int rand, index = 0;
        List<String> printSoal = new ArrayList<>();
        List<String> printJawab = new ArrayList<>();
        while(! all.isEmpty()){
            index++;
            rand = n.nextInt(all.size());
            Soal tulis = all.remove(rand);
            String[] pilihan = randomArray(tulis.getPilihan());
            
            printSoal.add(index + ". " + tulis.getSoal());
            printSoal.add("  A. " + pilihan[0]);
            printSoal.add("  B. " + pilihan[1]);
            if (pilihan.length > 2) {
                printSoal.add("  C. " + pilihan[2]);
                if (pilihan.length > 3) {
                    printSoal.add("  D. " + pilihan[3]);
                    if (pilihan.length > 4) {
                        printSoal.add("  E. " + pilihan[4]);
                    }
                }
            }
        
            char kunci = checkKunci(pilihan, tulis.getJawaban());
            printJawab.add(index + ". " + kunci);
        
        }
        // File Chooser Save
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Compressed File (*.zip)", "zip");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            create(selectedFile, printSoal, printJawab);
        }
    }

    private String[] randomArray(String[] pilihan) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = pilihan.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = pilihan[index];
            pilihan[index] = pilihan[i];
            pilihan[i] = a;
        }
        return pilihan;
    }

    private void create
        (
            File selectedFile, 
            List<String> printSoal, 
            List<String> printJawab
        ) throws IOException 
    {
        Path soal = Paths.get("Soal.txt");
        Path jawab = Paths.get("Kunci Jawaban.txt");
        Files.write(soal, printSoal, Charset.forName("UTF-8"));
        Files.write(jawab, printJawab, Charset.forName("UTF-8"));
        try ( FileOutputStream fos = new FileOutputStream(selectedFile); 
              ZipOutputStream zos = new ZipOutputStream(fos); ) 
        {
            addToZipFile("Soal.txt", zos);
            addToZipFile("Kunci Jawaban.txt", zos);
        }
        File a = soal.toFile();
        File b = jawab.toFile();
        a.delete(); b.delete();
    }

    
    private void addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {

        System.out.println("Writing '" + fileName + "' to zip file");

        File file = new File(fileName);
        try (FileInputStream fis = new FileInputStream(file)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zos.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }

            zos.closeEntry();
        }
    }
    
    private char checkKunci(String[] pilihan, String jawaban){
        char kunci;    
        if (pilihan[0].equals(jawaban)) {
            kunci = 'A';
        } else if (pilihan[1].equals(jawaban)) {
            kunci = 'B';
        } else if (pilihan[2].equals(jawaban)) {
            kunci = 'C';
        } else if (pilihan[3].equals(jawaban)) {
            kunci = 'D';
        } else {
            kunci = 'E';
        }
        return kunci;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Export and Import File">
    
    /**
     * Use to open file with file chooser
     * with extension BankSoal File extention (.bs)
     * @throws IOException if open file is failed
     * @throws ClassNotFoundException if Casting object is failed
     */
    public void openFile() throws IOException, ClassNotFoundException {
        // File Chooser Open
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("BankSoal File (*.bs)", "bs");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            // Read Serialization File Object
            try ( FileInputStream fileIn = new FileInputStream(selectedFile);
                  ObjectInputStream in = new ObjectInputStream(fileIn); )
            {
                add( (ArrayList<MataKuliah>) in.readObject() );
            }
            
        }
    }
    
    /**
     * Use to save file with file chooser
     * with extension BankSoal File extention (.bs)
     * @param export
     * @throws IOException when write the object to file
     */
    public void saveFile(ArrayList<MataKuliah> export) throws IOException {
        // File Chooser Save
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("BankSoal File (*.bs)", "bs");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            // Write Serialization File Object
            try ( FileOutputStream fileOut = new FileOutputStream(selectedFile);
                  ObjectOutputStream out = new ObjectOutputStream(fileOut); )
            {
                out.writeObject(export);
            }
        }
    }
    
    //</editor-fold>



}