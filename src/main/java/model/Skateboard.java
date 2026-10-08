package model;

public abstract class Skateboard {
    protected final String idPapan;
    protected String merk;
    protected double tarifSewa;

    public Skateboard(String idPapan, String merk, double tarifSewa) {
        this.idPapan = idPapan;
        this.merk = merk;
        this.tarifSewa = tarifSewa < 10000 ? 10000 : tarifSewa;
    }

    public String getIdPapan() {
        return idPapan;
    }

    public String getMerk() {
        return merk;
    }

    public double getTarifSewa() {
        return tarifSewa;
    }

    public abstract void tampilkanDetailPapan(); 
}