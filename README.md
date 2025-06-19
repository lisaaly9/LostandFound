# Lost and Found System

Aplikasi Lost and Found berbasis Java yang membantu pengguna untuk melaporkan, mencari, dan mengklaim barang hilang atau ditemukan. Proyek ini menggunakan JavaFX untuk antarmuka pengguna dan menerapkan arsitektur modular dengan pemisahan antara controller, model, service, dan DAO.

## Fitur Utama
- Registrasi dan login user & admin
- Registrasi admin dengan verifikasi password khusus
- Login menggunakan username/email
- Pelaporan barang hilang dan ditemukan
- Daftar & detail barang hilang dan ditemukan
- Klaim barang oleh pemilik
- Penghapusan barang ditemukan setelah diklaim
- Laporan aktivitas (report) untuk admin dan user
- Statistik jumlah barang hilang, ditemukan, dan klaim di dashboard
- Navigasi antar halaman (Lost, Found, Report, Claim, Logout)
- Upload dan preview gambar barang
- Logout dan session management

## Struktur Proyek
```
src/
  main/
    java/
      org/
        oop/
          lostfound/
            MainApp.java
            config/
            controller/
            dao/
            enums/
            model/
            service/
    resources/
      org/
        oop/
          image/         # Gambar ikon aplikasi
          lostfound/     # File FXML tampilan
          style/         # CSS styling
```

## Cara Build & Run

### Prasyarat
- Java 11 atau lebih baru
- Maven

### Build
```sh
mvnw clean package
```

### Jalankan Aplikasi
```sh
mvnw javafx:run
```

## Kontributor
- [Lisa Sapitri                   11241041]
- [Aldy Tri Ramadhani             11241009]
- [Muhammad Afdhalul Adam         11241051]
- [Sebastian Paskahadi Budiman    11241079]


## Lisensi
Proyek ini menggunakan lisensi MIT.
