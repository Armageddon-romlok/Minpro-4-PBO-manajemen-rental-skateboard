package model;

public class Penyewa implements LayananRental {
    private final String idRental;
    private String namaPenyewa;
    private int lamaSewa;
    private Skateboard papan;

    public Penyewa(String idRental, String namaPenyewa, int lamaSewa, Skateboard papan) {
        this.idRental = idRental;
        this.namaPenyewa = namaPenyewa;
        setLamaSewa(lamaSewa); 
        this.papan = papan;
    }

    public String getIdRental() { 
        return idRental; 
    }

    public String getNamaPenyewa() { 
        return namaPenyewa; 
    }

    public int getLamaSewa() { 
        return lamaSewa; 
    }

    public Skateboard getPapan() { 
        return papan; 
    }
    
    public void setLamaSewa(int lamaSewa) {
        this.lamaSewa = lamaSewa > 0 ? lamaSewa : 1;
    }

    public double hitungTotalBiaya() {
        return lamaSewa * papan.getTarifSewa();
    }

    public double hitungTotalBiaya(double diskon) {
        double total = (lamaSewa * papan.getTarifSewa()) - diskon;
        return total > 0 ? total : 0;
    }

    @Override
    public void konfirmasiPenyewaan() {
        System.out.println(">> [SISTEM] Penyewaan atas nama " + namaPenyewa + " telah dikonfirmasi.");
    }

    @Override
    public void cetakStruk() {
        System.out.println("----------------------------------");
        System.out.println("ID Rental  : " + idRental);
        System.out.println("Penyewa    : " + namaPenyewa);
        System.out.println("Lama Sewa  : " + lamaSewa + " Hari");
        papan.tampilkanDetailPapan();
        System.out.println("TOTAL BIAYA: Rp " + hitungTotalBiaya());
        System.out.println("----------------------------------");
    }
}