# Tugas 2 IF3110 Pengembangan Aplikasi Berbasis Web

## Deskripsi Tugas
Pada Tugas Besar 2 WBD, kami melakukan *upgrade* Website ojek online sederhana pada Tugas Besar 1 dengan mengaplikasikan **arsitektur web service REST dan SOAP**. Adapun hal-hal yang kami pelajari selama kami melakukan eksplorasi dan pembuatan tugas besar ini adalah sebagai berikut:
* Produce dan Consume REST API
* Mengimplementasikan service Single Sign-On (SSO) sederhana
* Produce dan Consume Web Services dengan protokol SOAP
* Membuat web application dengan menggunakan JSP yang akan memanggil web services dengan SOAP (dengan JAX-WS) dan REST (dengan Servlet).

## Anggota Tim
Berikut ini adalah anggota dari tim pengembangan aplikasi berbasis web ini:
1. Kevin Iswara - 13515085
2. Aditya Pratama - 13515103 
3. Rachel Sidney Devianti - 13515124

## Arsitektur Umum Server
![Gambar Arsitektur Umum](arsitektur_umum.png)

Tugas 2 ini terdiri dari berberapa komponen yang harus dibuat:
* `Web app`: digunakan untuk menangani HTTP request dari web browser dan menghasilkan HTTP response. Bagian yang diimplementasi dengan JSP ini juga bertugas untuk meng-generate tampilan web layaknya PHP. Bagian ini dibuat dengan **Java + Java Server Pages**.
* `Ojek Online Web Service`: digunakan sebagai interface yang dipanggil oleh aplikasi melalui protokol SOAP. melakukan query ke database, operasi insert, dan operasi update untuk entitas User, History, dan Preferred Locations. Webservice ini akan dipanggil oleh aplikasi dengan menggunakan SOAP. Webservice ini dibuat dengan **JAX-WS dengan protokol SOAP atau Webservice lain** yang basisnya menggunakan **SOAP dan Java**.
* `Identity service`: dipanggil oleh aplikasi untuk menerima email (sebagai username) dan password pengguna, dan menghasilkan *access token*. Identity Service juga dipanggil oleh *ojek online web service* untuk melakukan validasi terhadap *access token* yang sedang dipegang oleh *web app*. Service ini dibuat menggunakan REST. Identity service ini wajib dibuat dengan menggunakan **Java Servlet**.
* `Database`: Basis data yang telah dibuat pada tugas 1 dipisahkan menjadi basis data khusus manajemen *account* (menyimpan username, password, dkk) dan basis data ojek online tanpa manajemen *account*. Basis data *account* digunakan secara khusus oleh Identity Service. 

## Deskripsi Tugas
Kami diminta untuk membuat ojek online sederhana seperti tugas 1.  Kebutuhan fungsional dan non-fungsional tugas yang harus dibuat adalah sebagai berikut.
1. Halaman website yang memiliki tampilan serupa dengan tugas 1.
2. Ojek Online web service dengan fungsi-fungsi sesuai kebutuhan sistem dalam mengakses data (kecuali login, register, dan logout).
3. Identity service yang menangani manajemen *account* seperti login dan register, serta validasi access token.
4. Fitur validasi email dan username pada halaman register tidak perlu diimplementasikan dengan menggunakan AJAX.
5. Tidak perlu melakukan validasi javascript.
6. Tampilan pada tugas ini **tidak akan dinilai**. Sangat disarankan untuk menggunakan asset dan tampilan dari tugas sebelumnya. Boleh menggunakan CSS framework seperti Bootstrap atau javascript library seperti jQuery.
7. Tidak perlu memperhatikan aspek keamanan dan etika penyimpanan data.

## Skenario
### Login
1. Pengguna mengakses halaman login, contoh: `/login.jsp` dan mengisi form.
2. JSP akan membuka HTTP request ke Identity Service, contoh `POST /login` dengan body data email dan password.
3. Identity service akan melakukan query ke DB untuk mengetahui apakah email dan password tersebut valid.
4. Identity service akan memberikan HTTP response `access token` dan `expiry time` jika email dan password ada di dalam DB, atau error jika tidak ditemukan data. Silakan definisikan `expiry time` yang menurut Anda sesuai.
5. Access token ini digunakan sebagai representasi state dari session pengguna dan harus dikirimkan ketika pengguna ingin melakukan semua aktivitas, kecuali login, register, dan logout. 
6. Access token dibangkitkan secara random. Silakan definisikan sendiri panjang tokennya.
7. Cara penyimpanan access token dibebaskan.
8. Silakan definisikan format request dan response sesuai kebutuhan anda. Anda dapat menggunakan JSON atau XML untuk REST.

### Register
1. Pengguna mengakses halaman register, contoh: `/register.jsp` dan mengisi form.
2. JSP akan melakukan HTTP request ke Identity Service, contoh `POST /register` dengan body data yang dibutuhkan untuk registrasi.
3. Identity service akan query DB untuk melakukan validasi bahwa email dan username belum pernah terdaftar sebelumnya.
4. Identity service akan menambahkan user ke DB bila validasi berhasil, atau memberi HTTP response error jika username sudah ada atau confirm password salah.
5. Identity service akan memberikan HTTP response `access token` dan `expiry time` dan user akan ter-login ke halaman profile bila user merupakan driver atau ke halaman order bila user bukan merupakan driver.
6. Silakan definisikan format request dan response sesuai kebutuhan anda. Anda dapat menggunakan JSON atau XML untuk REST.

### Logout
1. Pengguna menekan tombol logout.
2. JSP akan melakukan HTTP request ke Identity Service, contoh `POST /logout` dengan body data yang dibutuhkan.
3. Identity service akan menghapus atau melakukan invalidasi terhadap access token yang diberikan.
4. Identity service akan mengembalikan HTTP response berhasil.
5. Halaman di-*redirect* ke halaman login.

### Add Preferred Location, Make an Order, dll
1. Pengguna mengakses halaman add preferred location, misal `/add-preferred-location.jsp` dan mengisi form.
2. JSP akan memanggil fungsi pada *ojek online web service* dengan SOAP, misalnya `addPreferredLocation(access_token, location)`. Contohnya, dapat dilihat pada
[link berikut](http://www.mkyong.com/webservices/jax-ws/jax-ws-hello-world-example/)
Perhatikan pemanggilan pada contoh ini seperti melakukan remote procedure call.
3. Fungsi tersebut akan melakukan HTTP request ke Identity Service, untuk mengenali user dengan `access_token` yang diberikan.
    - Jika `access_token` **kadaluarsa**, maka `addPreferredLocation` akan memberikan response expired token.
    - Jika `access_token` **tidak valid**, maka `addPreferredLocation` akan memberikan response error ke JSP.
    - Jika `access_token` **valid**, maka `addPreferredLocation` akan memasukan produk ke DB, dan memberikan response kesuksesan ke JSP.
4. Jika `access_token` sudah kadaluarsa atau tidak valid (yang diketahui dari response error ojek online web service), sistem akan me-redirect user ke halaman login.
5. Untuk make an order, get history, dan lainnya kira-kira memiliki mekanisme yang sama dengan add preferred locations di atas.
6. Silakan definisikan format object request dan response sesuai kebutuhan anda.

## Petunjuk Pengerjaan
1. Buatlah organisasi pada gitlab dengan format "IF3110-2017-KXX-nama kelompok", dengan XX adalah nomor kelas.
2. Tambahkan anggota tim pada organisasi anda.
3. Fork pada repository ini dengan organisasi yang telah dibuat.
4. Ubah hak akses repository hasil Fork anda menjadi **private**.
5. [DELIVERABLE] Buat tugas sesuai spesifikasi dan silakan commit pada repository anda (hasil fork). Lakukan berberapa commit dengan pesan yang bermakna, contoh: `add register form`, `fix logout bug`, jangan seperti `final`, `benerin dikit`. Disarankan untuk tidak melakukan commit dengan perubahan yang besar karena akan mempengaruhi penilaian (contoh: hanya melakukan satu commit kemudian dikumpulkan). Sebaiknya commit dilakukan setiap ada penambahan fitur. **Commit dari setiap anggota tim akan mempengaruhi penilaian individu.** Jadi, setiap anggota tim harus melakukan sejumlah commit yang berpengaruh terhadap proses pembuatan aplikasi.
6. Hapus bagian yang tidak perlu dari *readme* ini.
7. [DELIVERABLE] Berikan penjelasan mengenai hal di bawah ini pada bagian **Penjelasan** dari *readme* repository git Anda:
    - Basis data dari sistem yang Anda buat.
    - Konsep *shared session* dengan menggunakan REST.
    - Pembangkitan token dan expire time pada sistem yang anda buat.
    - Kelebihan dan kelemahan dari arsitektur aplikasi tugas ini, dibandingkan dengan aplikasi monolitik (login, CRUD DB, dll jadi dalam satu aplikasi)
8. Pada *readme* terdapat penjelasan mengenai pembagian tugas masing-masing anggota (lihat formatnya pada bagian **pembagian tugas**).
9. Merge request dari repository anda ke repository ini dengan format **Nama kelompok** - **NIM terkecil** - **Nama Lengkap dengan NIM terkecil** sebelum **8 November 2017 23.59**.

## Penjelasan
### Basis Data Sistem
Pada Tugas Besar ini, kami menggunakan 2 buah basis data yaitu basis data **user_ojekonline** (untuk managemen account) dan basis data **ojekonline**. Basis data **ojekonline** berhubungan secara langsung dengan Web Service. Basis data **ojekonline** berisi relasi **driver** dan **orders**. Relasi **driver** memiliki atribut **driver_id** dan **driver_location**. Sedangkan, relasi **orders** memiliki atribut **order_id**, **date**, **picking_point**, **destination**, **passenger_id**, **driver_id**, **rating**, **comment**, **hide_driver**, dan **hide_passenger**. 
Basis data **user_ojekonline** berisi relasi **user** dan **user_info**. Basis data **user_ojekonline** berhubungan secara langsung dengan Identity Service. Relasi **user** memiliki atribut **user_id**, **user_token**, **expiry_time**. Sedangkan, relasi **user_info** menyimpan informasi user yang lain, yaitu: **user_id**, **user_name**, **user_phone**, **user_status**, **user_username**, **user_email**, **user_pass**.

### Konsep *Shared Session*
Pada tugas besar ini kami menggunakan token yang di-generate secara random oleh Identity Service pada saat seorang pengguna melakukan login atau melakukan register, jika login dan register valid. Token ini digunakan sebagai pengganti username dan password. Token yang di-generate secara random ini akan menandai session dari seorang pengguna. Token yang diterima pengguna tersebut dapat diambil dan digunakan oleh pengguna lain (yang mengetahui token pengguna yang bersangkutan). Dengan token pengguna asli, pengguna lain bisa mempergunakan token tersebut untuk mendapat session yang sama di tempat yang berbeda.

### Konsep Pembangkitan Token
Pada tugas besar ini, kami men-generate token seorang pengguna apabila pengguna berhasil melakukan login atau register. Token yang dibangkitkan merupakan kombinasi 20 digit dari huruf dan angka dengan menggunakan objek SecureRandom yang disediakan pada Java. Batas waktu kadaluarsa token adalah 1 hari setelah pengguna melakukan login. Apabila pengguna tidak melakukan logout selama lebih dari 1 hari, maka session pengguna otomatis akan expired. Pada saat logout, token akan dihapus dan pada saat pengguna login, token akan kembali di-generate ulang.

### Kelebihan Arsitektur
Berikut ini adalah kelebihan dari arsitektur server yang dibangun:
1. Arsitektur bersifat modular sehingga lebih mudah dipahami dan dilakukan pengujian terhadap program.
2. Suatu Web Service dapat digunakan oleh lebih dari 1 aplikasi karena adanya Web Service Descriptor Language.
3. Dengan adanya Remote Procedure Calls, maka untuk menggunakan layanan yang disediakan oleh suatu service, tidak perlu mengetahui detail implementasinya.
4. Basis data yang diletakkan pada service yang terpisah (Identity Service dan Web Service) menyebabkan pengamanan terhadap basis data seorang user lebih dapat terkendali.
5. Kesalahan pemrograman pada salah satu bagian (Web App, Web Service, atau Identity Service) tidak menyebabkan matinya keseluruhan sistem yang ada.
 
### Kelemahan Arsitektur
Berikut ini adalah kelemahan dari arsitektur server yang dibangun:
1. Operasi-operasi yang dilakukan pada aplikasi dengan arsitektur ini cenderung lebih lambat dibandingkan dengan aplikasi monolitik. Hal ini dikarenakan diperlukan waktu antar sistem untuk saling berkomunikasi satu dengan yang lainnya.
2. Server harus selalu meminta autentikasi dari setiap request client, walaupun itu sebenarnya client yang sama. Hal ini karena server tidak pernah mencatat dengan siapa dia bertransaksi.
3. Pengguna dapat menjadi kurang nyaman ketika menggunakan aplikasi apabila tokennya expired dan harus mengulang log-in untuk dapat melanjutkan aktivitasnya.

## Pembagian Tugas
Berikut ini adalah pembagian tugas di kelompok kami:

REST :
1. HistoryServlet           : 13515085 
2. LoginServlet             : 13515085, 13515103 
3. LogoutServlet            : 13515085, 13515103
4. OrderDriverServlet       : 13515124
5. RegisterServlet          : 13515085, 13515103
6. ValidateTokenServlet     : 13515085, 13515124
7. ProfileServlet           : 13515103

SOAP :
1. HistoryGojek             : 13515085
2. Location                 : 13515124
3. OrderGojek               : 13515124
4. Profile                  : 13515103
5. ExpiryTime               : 13515124

Web app (JSP) :
1. Login                    : 13515085, 13515103
2. Register                 : 13515085, 13515103
3. Driver History           : 13515085
4. My Previous Order        : 13515085
5. Profile                  : 13515103
6. Edit Profile             : 13515103
7. Edit Preferred Location  : 13515124
8. Order                    : 13515124
9. Select Driver            : 13515124
10. Complete Order          : 13515124

## About

Asisten IF3110 2017

Ade | Johan | Kristianto | Micky | Michael | Rangga | Raudi | Robert | Sashi 

Dosen : Yudistira Dwi Wardhana | Riza Satria Perdana | Muhammad Zuhri Catur Candra