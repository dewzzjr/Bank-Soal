/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;


public class SQLHelper {
    
    private final Connection con;
    
    /**
     * Connecting BankSoal Database
     * Using JDBC Derby
     * @throws SQLException when 
     */
    public SQLHelper() throws SQLException {
        String HOST = "jdbc:derby://localhost:1527/BankSoal";
        String USERNAME = "dosen";
        String PASSWORD = "038130";
        con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        
    }
    
    /**
     * Call this method when the connection is not necessary anymore
     * @throws SQLException when the connection is null
     */
    public void close() throws SQLException {
        con.close();
    }
    


    /**
     * Accessors SQL Database 
     * @return ArrayList of MataKuliah
     * @throws SQLException
     */
    //<editor-fold defaultstate="collapsed" desc="Get From SQL Database">
    public ArrayList<MataKuliah> getMataKuliah() throws SQLException {
        ArrayList<MataKuliah> mk = new ArrayList<>();
        ArrayList<PokokBahasan> pb;
        String query = "SELECT nama_matakuliah FROM mata_kuliah";
        
        try (ResultSet rs = con.createStatement().executeQuery(query)) {
            while (rs.next()) {
                String nama = rs.getString("nama_matakuliah");
                mk.add(new MataKuliah(nama));
                MataKuliah temp = mk.get(mk.size()-1);
                pb = getPokokBahasan(temp);
                temp.add(pb);
            }
        }System.out.println(query);

        return mk;
    }
    
    private ArrayList<PokokBahasan> getPokokBahasan(MataKuliah n) throws SQLException{
        ArrayList<PokokBahasan> pb = new ArrayList<>();
        ArrayList<Soal> soal;
        String namaMatkul = "'" + n.getNama() + "'";
        String query =
                "SELECT nama_matakuliah, nama_pokokbahasan " +
                "FROM mata_kuliah " +
                "JOIN pokok_bahasan " +
                "ON mata_kuliah.id_matakuliah=pokok_bahasan.id_matakuliah " +
                "WHERE nama_matakuliah=" + namaMatkul;
        
        try (ResultSet rs = con.createStatement().executeQuery(query)) {
            while (rs.next()) {
                String nama = rs.getString("nama_pokokbahasan");
                pb.add(new PokokBahasan(nama, n));
                PokokBahasan temp = pb.get(pb.size()-1);
                soal = getSoal(temp);
                temp.add(soal);
            }
        }System.out.println(query);

        return pb;
    }
    
    private ArrayList<Soal> getSoal(PokokBahasan n) throws SQLException {
        ArrayList<Soal> s = new ArrayList<>();
        String namaBahasan = "'" + n.getNama() + "'";
        String query =
                "SELECT jawaban, pilihan1, pilihan2, " +
                "pilihan3, pilihan4, nama_pokokbahasan, soal " +
                "FROM pokok_bahasan " +
                "JOIN soal " +
                "ON pokok_bahasan.id_pokokbahasan=soal.id_pokokbahasan " +
                "JOIN pilihan " +
                "ON pilihan.id_soal=soal.id_soal " +
                "WHERE nama_pokokbahasan=" + namaBahasan;
        
        try (ResultSet rs = con.createStatement().executeQuery(query)) {
            while (rs.next()) {
                String nama = rs.getString("soal");
                s.add(new Soal(nama, n));
                Soal temp = s.get(s.size()-1);
                for (int i = 0; i < 5; i++) {
                    temp.setPilihan(i, rs.getString(i + 1));
                }
            }
        }System.out.println(query);

        return s;
    }
    
    //</editor-fold>
    
    
    
    
    /**
     * Mutators SQL Database
     * @param input From ArrayList of MataKuliah
     * @throws SQLException
     */
    //<editor-fold defaultstate="collapsed" desc="Insert and Check From SQL Database">
    public void insertMataKuliah(ArrayList<MataKuliah> input) throws SQLException {
        
        ArrayList<MataKuliah> data = getMataKuliah();
        boolean same;
        String query;
        int index;
        for (MataKuliah data1 : data) {
            for (MataKuliah input1 : input) {
                same = data1.getNama().equals(input1.getNama());
                if (! same) {
                    index = autoincrement("id_matakuliah", "mata_kuliah");
                    query = "INSERT INTO mata_kuliah" +
                            "VALUES ("
                            + index + ", "
                            + input1.getNama() +
                            ")";
                    con.createStatement().execute(query);
                } else {
                    query = "SELECT id_matakuliah " +
                            "FROM mata_kuliah " +
                            "WHERE nama_matakuliah='" + input1.getNama() + "'";
                    try (ResultSet rs = con.createStatement().executeQuery(query)) {
                        rs.next();
                        index = rs.getInt(1);
                    }
                }System.out.println(query);
                insertPokokBahasan(input1.get(), data1.get(), index);
                
            }
        }
    }
    
    private void insertPokokBahasan(ArrayList<PokokBahasan> n, ArrayList<PokokBahasan> data, int matkul) throws SQLException {
        
        boolean same;
        String query;
        int index;
        for (PokokBahasan data1 : data) {
            for (PokokBahasan input1 : n) {
                same = data1.getNama().equals(input1.getNama());
                if (! same) {
                    index = autoincrement("id_pokokbahasan", "pokok_bahasan");
                    query = "INSERT INTO pokok_bahasan" +
                            "VALUES ("
                            + index + ", "
                            + input1.getNama() + ", "
                            + matkul +
                            ")";
                    con.createStatement().execute(query);
                } else {
                    query = "SELECT id_pokokbahasan " +
                            "FROM pokok_bahasan " +
                            "WHERE nama_pokokbahasan='" + input1.getNama() + "'";
                    try (ResultSet rs = con.createStatement().executeQuery(query)) {
                        rs.next();
                        index = rs.getInt(1);
                    }
                }System.out.println(query);
                
                insertSoal(input1.get(), data1.get(), index, matkul);
            }
        }
    }
    
    private void insertSoal(ArrayList<Soal> n, ArrayList<Soal> data, int matkul, int bahasan) throws SQLException {
        
        boolean same;
        String query;
        int index;
        for (Soal data1 : data) {
            for (Soal input1 : n) {
                same = data1.getSoal().equals(input1.getSoal());
                if (! same) {
                    index = autoincrement("id_soal", "soal");
                    query = "INSERT INTO soal" +
                            "VALUES ("
                            + index + ", "
                            + input1.getSoal() + ", "
                            + bahasan + ", "
                            + matkul +
                            ")";
                    con.createStatement().execute(query);
                } else {
                    query = "SELECT id_soal " +
                            "FROM soal" +
                            "WHERE soal='" + input1.getSoal() + "'";
                    try (ResultSet rs = con.createStatement().executeQuery(query)) {
                        rs.next();
                        index = rs.getInt(1);
                    }
                }System.out.println(query);
                insertPilihan(input1.getPilihan(), data1.getPilihan(), index);
                
            }
        }
    }
    
    private void insertPilihan(String[] input, String[] data, int soal) throws SQLException {
        if (! Arrays.equals(input, data)) {
            String query = "INSERT INTO pilihan" +
                    "VALUES ("
                    + soal + ", "
                    + input[0] + ", "
                    + input[1] + ", "
                    + input[2] + ", "
                    + input[3] + ", "
                    + input[4] +
                    ")";
            con.createStatement().execute(query);
            System.out.println(query);
        }
    }
    
    private int autoincrement (String column, String table) throws SQLException {
        String query = "SELECT MAX(" + column + ") FROM " + table;
        int index;
        try (ResultSet rs = con.createStatement().executeQuery(query)) {
            rs.next();
            index = rs.getInt(1) + 1;
        }System.out.println(query);
        return index;
    }
    //</editor-fold>
}
