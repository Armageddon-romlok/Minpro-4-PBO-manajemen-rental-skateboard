package dao;

import config.Koneksi;
import model.Penyewa;
import model.StreetSkate;
import model.CruiserSkate;
import model.Skateboard;
import java.sql.*;
import java.util.ArrayList;

public class PenyewaDAO {

    public ArrayList<Penyewa> getAllData() {
        ArrayList<Penyewa> list = new ArrayList<>();
        String sql = "SELECT * FROM penyewa";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String idRental = rs.getString("id_rental");
                String nama = rs.getString("nama_penyewa");
                int lama = rs.getInt("lama_sewa");
                String tipe = rs.getString("tipe_papan");
                String idPapan = rs.getString("id_papan");
                String merk = rs.getString("merk_papan");
                double tarif = rs.getDouble("tarif_sewa");
                String spek = rs.getString("spesifikasi_khusus");

                Skateboard papan;
                if (tipe.equalsIgnoreCase("StreetSkate")) {
                    papan = new StreetSkate(idPapan, merk, tarif, spek);
                } else {
                    papan = new CruiserSkate(idPapan, merk, tarif, spek);
                }

                list.add(new Penyewa(idRental, nama, lama, papan));
            }
        } catch (SQLException e) {
            System.out.println(">> ERROR Read DB: " + e.getMessage());
        }
        return list;
    }

    public boolean tambahData(Penyewa p, String tipePapan, String spekKhusus) {
        String sql = "INSERT INTO penyewa (id_rental, nama_penyewa, lama_sewa, tipe_papan, id_papan, merk_papan, tarif_sewa, spesifikasi_khusus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getIdRental());
            stmt.setString(2, p.getNamaPenyewa());
            stmt.setInt(3, p.getLamaSewa());
            stmt.setString(4, tipePapan);
            stmt.setString(5, p.getPapan().getIdPapan());
            stmt.setString(6, p.getPapan().getMerk());
            stmt.setDouble(7, p.getPapan().getTarifSewa());
            stmt.setString(8, spekKhusus);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(">> ERROR Insert DB: " + e.getMessage());
            return false;
        }
    }

    public boolean updateLamaSewa(String idRental, int lamaBaru) {
        String sql = "UPDATE penyewa SET lama_sewa = ? WHERE id_rental = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, lamaBaru);
            stmt.setString(2, idRental);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(">> ERROR Update DB: " + e.getMessage());
            return false;
        }
    }

    public boolean hapusData(String idRental) {
        String sql = "DELETE FROM penyewa WHERE id_rental = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idRental);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(">> ERROR Delete DB: " + e.getMessage());
            return false;
        }
    }
}