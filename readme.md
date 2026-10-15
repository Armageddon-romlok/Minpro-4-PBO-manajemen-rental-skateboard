# Minpro-4-PBO-SistemRentalSkateboard

## 1. Deskripsi Singkat Program
Program ini adalah aplikasi *Command Line Interface* (CLI) Manajemen Rental Skateboard yang telah terintegrasi penuh dengan basis data relasional MySQL. Sistem ini mendukung operasi CRUD secara persisten, di mana data penyewaan papan (Street Skate dan Cruiser Skate) tidak lagi hilang saat program ditutup, melainkan disimpan ke dalam *database*. Program ini dikembangkan menggunakan arsitektur MVC, pola desain DAO, serta konektivitas JDBC.

<img width="397" height="195" alt="{F563976C-1EB7-4AB1-BAFC-B2167942BA6F}" src="https://github.com/user-attachments/assets/1ae68270-5bc3-4ca3-838d-576d9f7ddb88" />


## 2. Penjelasan Struktur Package
Proyek ini mengadopsi arsitektur MVC yang dikembangkan lebih lanjut dengan penambahan lapisan akses dan konfigurasi data:
*   **`config`**: Menyimpan kelas `Koneksi.java` yang bertugas membangun jembatan penghubung ke database MySQL.
*   **`dao` (Data Access Object)**: Berisi kelas `PenyewaDAO.java` yang menjadi "pintu" eksklusif untuk berbicara dengan *database*, memisahkan murni logika SQL dari logika bisnis aplikasi.
*   **`model`**: Berisi *blueprint* objek seperti `Skateboard`, tipe-tipe papan, dan `Penyewa` beserta antarmuka kontraknya.
*   **`view`**: Menangani antarmuka CLI, interaksi *Scanner*, dan *Exception Handling* (try-catch).
*   **`controller`**: Mengatur alur pertukaran data antara `View` dan lapisan `DAO`.
*   **`main`**: Titik mula (*entry point*) untuk menjalankan aplikasi melalui `MainApp`.

<img width="315" height="626" alt="{613EFF01-9D14-4BE8-878A-F8510FECB5F7}" src="https://github.com/user-attachments/assets/d2b25476-d665-4114-8f16-e963694887d4" />

## 3. Penjelasan Alur Program
Saat program dijalankan, sistem secara otomatis membangun koneksi ke MySQL di latar belakang. Ketika pengguna memilih menu **Tampilkan Rental**, `Controller` akan meminta `DAO` untuk melakukan kueri (SELECT) ke *database*. Data *dummy* awal yang sudah disuntikkan lewat file `.sql` akan langsung ditarik dan dicetak ke layar. Apabila pengguna melakukan operasi Tambah, Update, atau Hapus, instruksi diteruskan dari `View` ke `Controller`, lalu dieksekusi secara oleh `DAO` menggunakan sintaks SQL ke dalam tabel MySQL.

<img width="468" height="659" alt="{4BC7C21B-89ED-4761-BD6A-7B07E3D19D87}" src="https://github.com/user-attachments/assets/47116beb-b549-48b0-b19e-c56150acc914" />
<img width="1052" height="172" alt="{3A5920A2-8D7E-4CAE-81B6-3A9EC0CE521A}" src="https://github.com/user-attachments/assets/7d6ec04e-c78b-4714-a456-8214c8d1304b" />


## 4. Penjelasan Penerapan Encapsulation dan Inheritance
Penerapan **Encapsulation (Pengkapsulan)** dibuktikan dengan penggunaan modifikator `private` pada atribut kelas dan pelindungan integritas data melalui metode *Getter* dan *Setter* (misalnya validasi lama sewa minimal 1 hari). Atribut ID (`idRental`, `idPapan`) juga dilindungi dengan *keyword* `final` agar tidak bisa dimodifikasi setelah transaksi dibuat. 

Penerapan **Inheritance (Pewarisan)** ditunjukkan dengan penggunaan kata kunci `extends`. Kelas `StreetSkate` dan `CruiserSkate` adalah turunan (subclass) yang mewarisi atribut umum (`idPapan`, `merk`, `tarifSewa`) dari kelas induknya, yaitu `Skateboard`.

## 5. Penjelasan Penerapan Polymorphism dan Abstraction
Penerapan **Abstraction (Abstraksi)** dilakukan dengan mendeklarasikan kelas `Skateboard` sebagai `abstract`, menjadikannya *blueprint* murni. Kelas ini memiliki *abstract method* `tampilkanDetailPapan()` tanpa *body*, yang mewajibkan kelas turunannya mendefinisikan bentuk tampilannya sendiri.

Penerapan **Polymorphism (Polimorfisme)** dilakukan melalui **Overriding**, di mana `StreetSkate` dan `CruiserSkate` menimpa metode `tampilkanDetailPapan()` dengan `@Override` untuk mencetak spesifikasi khusus (ukuran roda atau panjang papan). Selain itu, terdapat **Overloading** pada kelas `Penyewa` yang memiliki dua versi metode `hitungTotalBiaya()`: satu tanpa parameter untuk tarif normal, dan satu dengan parameter `double diskon`.

## 6. Penjelasan Penerapan JDBC (Java Database Connectivity)
Program ini menggunakan API standar JDBC untuk berkomunikasi dengan basis data relasional. Konektivitas didaftarkan melalui file `pom.xml` dengan menambahkan *dependency* `mysql-connector-j`. 

Pada lapisan kode, antarmuka `java.sql.Connection` digunakan bersama `DriverManager` di dalam package `config` untuk menyambungkan URL *database*, *username*, dan *password*. Eksekusi kueri dilakukan di dalam `PenyewaDAO` menggunakan antarmuka `PreparedStatement` dan `ResultSet` untuk memetakan baris data SQL kembali menjadi objek Java (`ArrayList`).

<img width="962" height="532" alt="{E9C4FD58-B5C9-4226-9050-6A7EA94B34E9}" src="https://github.com/user-attachments/assets/cf5fd781-60e2-4bc3-a52c-223a8b66fcf6" />
<img width="708" height="388" alt="{8BBE3AFD-0457-44E4-9342-025F344B690C}" src="https://github.com/user-attachments/assets/428fb525-709d-4fce-8558-f67c5b2632ea" />



## 7. Penjelasan Letak Penerapan Nilai Tambah (Nilai Plus)
Proyek ini mengimplementasikan nilai tambah pada arsitektur pengamanan kueri basis data dan desain struktur (*Design Pattern*):
1.  **Penerapan DAO (Data Access Object) Pattern:** Seluruh operasi *database* diisolasi ke dalam package `dao`. Kelas `Controller` tidak lagi menyentuh sintaks SQL sama sekali, sehingga kode menjadi lebih rapi, modular, dan terstruktur standar industri perangkat lunak.
2.  **Keamanan PreparedStatement (Anti SQL-Injection):** Alih-alih merangkai *String* kueri secara langsung yang rentan diretas, program ini mengeksekusi parameter SQL menggunakan `PreparedStatement` (tanda `?`). Input dari pengguna diperlakukan murni sebagai data, bukan bagian dari perintah eksekusi, sehingga membantu mencegah serangan SQL Injection pada parameter yang diberikan pengguna.
3.  **Penerapan Interface Murni:** Tetap mempertahankan kontrak `LayananRental` dari proyek sebelumnya untuk mengunci aturan metode `konfirmasiPenyewaan()` dan `cetakStruk()`.

<img width="637" height="458" alt="{07BE1911-3EE7-4338-9624-62871A084493}" src="https://github.com/user-attachments/assets/2e841441-c7ce-451c-bd0b-5fa0f25f2317" />
<img width="1068" height="165" alt="{CEDF8059-C3E2-4E81-A059-DCBE6A288449}" src="https://github.com/user-attachments/assets/aace6978-9367-4ff9-8491-3e57b25c70f5" />

<img width="1520" height="210" alt="{9E07434B-7950-4D2C-B408-ACACAC5EA6ED}" src="https://github.com/user-attachments/assets/e2104795-6da1-4d1a-9140-73af71e8d86a" />
