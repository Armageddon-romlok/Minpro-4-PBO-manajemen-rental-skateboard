package controller;

import dao.PenyewaDAO;
import model.Penyewa;
import java.util.ArrayList;

public class RentalController {
    private PenyewaDAO dao = new PenyewaDAO();

    public ArrayList<Penyewa> getDaftarRental() {
        return dao.getAllData();
    }

    public boolean isIdAda(String idRental) {
        for (Penyewa p : dao.getAllData()) {
            if (p.getIdRental().equalsIgnoreCase(idRental)) return true;
        }
        return false;
    }

    public boolean tambahData(Penyewa p, String tipePapan, String spekKhusus) {
        return dao.tambahData(p, tipePapan, spekKhusus);
    }

    public boolean updateLamaSewa(String idRental, int lamaBaru) {
        return dao.updateLamaSewa(idRental, lamaBaru);
    }

    public boolean hapusData(String idRental) {
        return dao.hapusData(idRental);
    }
}