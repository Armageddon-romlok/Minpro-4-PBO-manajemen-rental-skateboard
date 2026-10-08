package model;

public class StreetSkate extends Skateboard {
    private String ukuranRoda;

    public StreetSkate(String idPapan, String merk, double tarifSewa, String ukuranRoda) {
        super(idPapan, merk, tarifSewa);
        this.ukuranRoda = ukuranRoda;
    }

    @Override
    public void tampilkanDetailPapan() {
        System.out.println("Tipe Papan : Street Skate");
        System.out.println("ID Papan   : " + idPapan);
        System.out.println("Merk       : " + merk);
        System.out.println("Tarif/Hari : Rp " + tarifSewa);
        System.out.println("Uk. Roda   : " + ukuranRoda);
    }
}