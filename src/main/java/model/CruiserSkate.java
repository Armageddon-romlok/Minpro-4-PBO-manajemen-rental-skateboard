package model;

public class CruiserSkate extends Skateboard {
    private String panjangPapan;

    public CruiserSkate(String idPapan, String merk, double tarifSewa, String panjangPapan) {
        super(idPapan, merk, tarifSewa);
        this.panjangPapan = panjangPapan;
    }

    @Override
    public void tampilkanDetailPapan() {
        System.out.println("Tipe Papan : Cruiser Skate");
        System.out.println("ID Papan   : " + idPapan);
        System.out.println("Merk       : " + merk);
        System.out.println("Tarif/Hari : Rp " + tarifSewa);
        System.out.println("Panjang    : " + panjangPapan);
    }
}