package view;

import controller.RentalController;
import model.CruiserSkate;
import model.Penyewa;
import model.Skateboard;
import model.StreetSkate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RentalView {
    private RentalController controller = new RentalController();
    private Scanner input = new Scanner(System.in);

    private String bacaInputString(String pesan) {
        String teks = "";
        while (teks.trim().isEmpty()) {
            System.out.print(pesan);
            teks = input.nextLine();
            if (teks.trim().isEmpty()) {
                System.out.println(">> ERROR: Input tidak boleh kosong!");
            }
        }
        return teks;
    }

    public void tampilkanMenuUtama() {
        boolean jalan = true;
        while (jalan) {
            try {
                System.out.println("\n=== SISTEM RENTAL SKATEBOARD ===");
                System.out.println("1. Tambah Rental");
                System.out.println("2. Tampilkan Rental");
                System.out.println("3. Update Lama Sewa");
                System.out.println("4. Hapus Rental");
                System.out.println("5. Keluar");
                System.out.print("Pilih menu (1-5): ");
                int pilihan = input.nextInt();
                input.nextLine(); 

                switch (pilihan) {
                    case 1: menuTambahData(); break;
                    case 2: menuTampilData(); break;
                    case 3: menuUpdateData(); break;
                    case 4: menuHapusData(); break;
                    case 5: 
                        System.out.println("Program dihentikan secara aman.");
                        jalan = false; 
                        break;
                    default:
                        System.out.println(">> Pilihan tidak tersedia (1-5).");
                }
            } catch (InputMismatchException e) {
                System.out.println(">> ERROR: Masukan harus berupa angka!");
                input.nextLine(); 
            }
        }
    }

    private void menuTambahData() {
        System.out.println("\n--- TAMBAH DATA ---");
        String idRental = bacaInputString("ID Rental: ");

        if (controller.isIdAda(idRental)) {
            System.out.println(">> ERROR: ID sudah terdaftar!");
            return;
        }

        String nama = bacaInputString("Penyewa  : ");
        
        System.out.print("Lama Sewa: ");
        int lama = input.nextInt();
        
        System.out.println("\nPilih Papan:\n1. Street Skate\n2. Cruiser Skate");
        System.out.print("Pilihan (1-2): ");
        int tipe = input.nextInt();
        input.nextLine();

        String idPapan = bacaInputString("ID Papan : ");
        String merk = bacaInputString("Merk     : ");
        System.out.print("Tarif    : "); double tarif = input.nextDouble();
        input.nextLine();

        Skateboard papan;
        String tipePapanStr;
        String spekKhusus;

        if (tipe == 1) {
            tipePapanStr = "StreetSkate";
            spekKhusus = bacaInputString("Uk. Roda (misal: 52mm): ");
            papan = new StreetSkate(idPapan, merk, tarif, spekKhusus);
        } else if (tipe == 2) {
            tipePapanStr = "CruiserSkate";
            spekKhusus = bacaInputString("Panjang (misal: 27 inch): ");
            papan = new CruiserSkate(idPapan, merk, tarif, spekKhusus);
        } else {
            System.out.println(">> ERROR: Tipe papan tidak valid.");
            return;
        }

        Penyewa penyewaBaru = new Penyewa(idRental, nama, lama, papan);

        if (controller.tambahData(penyewaBaru, tipePapanStr, spekKhusus)) {
            penyewaBaru.konfirmasiPenyewaan();
            System.out.println(">> Data berhasil ditambahkan ke database!");
        } else {
            System.out.println(">> ERROR: Gagal menyimpan data ke database.");
        }
    }

    private void menuTampilData() {
        System.out.println("\n--- DAFTAR RENTAL ---");
        if (controller.getDaftarRental().isEmpty()) {
            System.out.println("Data kosong.");
        } else {
            for (Penyewa p : controller.getDaftarRental()) {
                p.cetakStruk();
            }
        }
    }

    private void menuUpdateData() {
        String id = bacaInputString("\nMasukkan ID Rental untuk diupdate: ");
        System.out.print("Lama Sewa Baru: ");
        int lamaBaru = input.nextInt();
        input.nextLine(); 
        
        if (controller.updateLamaSewa(id, lamaBaru)) {
            System.out.println(">> Lama sewa berhasil diupdate di database.");
        } else {
            System.out.println(">> ERROR: ID tidak ditemukan atau gagal diupdate.");
        }
    }

    private void menuHapusData() {
        String id = bacaInputString("\nMasukkan ID Rental untuk dihapus: ");
        if (controller.hapusData(id)) {
            System.out.println(">> Data berhasil dihapus dari database.");
        } else {
            System.out.println(">> ERROR: ID tidak ditemukan atau gagal dihapus.");
        }
    }
}