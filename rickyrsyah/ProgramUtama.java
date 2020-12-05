package rickyrsyah;

import java.util.Date;
import java.util.Scanner;

class ProgramUtama {
	public static void main(String[] args) {
		DataMahasiswa dataMahasiswa = new DataMahasiswa();
		Mahasiswa mahasiswa;
		Scanner sc = new Scanner(System.in);
		int pilihan;
		do {
			do {
				System.out.println(
					"\n== Menu Utama ==\n"
					+"1. Tambah Data\n" 
					+"2. Hapus Data\n" // nim
					+"3. Cari Data\n" // gender atau nim submenu kriteria lanjutan
					+"4. Tampil Data\n" // semua data dan total data
					+"5. Keluar\n"
					+"================");
				System.out.print("Pilih no. menu: ");
				pilihan = Integer.parseInt(sc.nextLine());
			} while(pilihan < 1 || pilihan > 5);

			int gender;
			switch (pilihan) {
				case 1:
					System.out.println("\n== Menambah data mahasiswa ==");
					mahasiswa = new Mahasiswa();

					System.out.print("Masukkan nim: ");
					mahasiswa.nim = sc.nextLine();

					System.out.print("Masukkan nama: ");
					mahasiswa.nama = sc.nextLine();

					System.out.print("Masukkan tanggal tahun lahir (YYYY): ");
					int thnLahir = Integer.parseInt(sc.nextLine());

					System.out.print("Masukkan tanggal bulan lahir (MM): ");
					int blnLahir = Integer.parseInt(sc.nextLine());

					System.out.print("Masukkan tanggal hari lahir (DD): ");
					int hariLahir = Integer.parseInt(sc.nextLine());

					mahasiswa.tglLahir = new Date(thnLahir-1900, blnLahir-1, hariLahir);

					do {
						System.out.print("Masukkan jenis kelamin (0 untuk laki-laki, 1 untuk perempuan): ");
						gender = Integer.parseInt(sc.nextLine());
					} while(gender < 0 || gender > 1);
					mahasiswa.gender = gender;

					dataMahasiswa.tambah(mahasiswa);
					System.out.println("Selesai! Data berhasil ditambahkan.");
					break;
				case 2:
					System.out.println("\n== Menghapus data mahasiswa ==");
					System.out.print("Masukkan nim mahasiswa yang ingin dihapus: ");

					if (dataMahasiswa.hapus(sc.nextLine())) {
						System.out.println("Data berhasil dihapus!");
					} else {
						System.out.println("Data tidak ditemukan!");
					}
					break;
				case 3:
					int pilihanSub;
					mahasiswa = new Mahasiswa();
					do {
						System.out.println(
							"\n== Mencari data mahasiswa ==\n"
							+"1. Cari berdasarkan gender\n"
							+"2. Cari berdasarkan nim\n"
							+"3. Kembali\n"
							+"================");
						System.out.print("Pilih no. menu: ");
						pilihanSub = Integer.parseInt(sc.nextLine());
					} while(pilihanSub < 1 || pilihanSub > 3);
					if (pilihanSub == 1) {
						do {
							System.out.print(
								"\n================\n"
								+"Masukkan 0 untuk laki-laki, 1 untuk perempuan (0/1): ");
							gender = Integer.parseInt(sc.nextLine());
						} while(gender < 0 || gender > 1);
						mahasiswa.gender = gender;
						System.out.println("== Menampilkan data mahasiswa dengan gender "
							+(mahasiswa.gender == 0 ? "0 (laki-laki)" : "1 (perempuan)")+" ==");
					} else if (pilihanSub == 2) {
						System.out.print(
							"\n================\n"
							+"Masukkan nim: ");
						mahasiswa.nim = sc.nextLine();
						System.out.println("== Menampilkan data mahasiswa dengan nim "
							+(mahasiswa.nim)+" ==");
					}
					Mahasiswa[] mhsDitemukan = dataMahasiswa.cari(mahasiswa);
					if (mhsDitemukan.length == 0) {
						System.out.println("Tidak menemukan apapun!");
					} else {
						System.out.println("Menemukan "+mhsDitemukan.length+" yang sesuai dengan kriteria.");
						int no = 0;
						for (Mahasiswa mhs: mhsDitemukan) {
							System.out.println((++no)+" ----------------");
							System.out.println("NIM: "+mhs.nim);
							System.out.println("Nama: "+mhs.nama);
							System.out.println("Tanggal Lahir: "+String.format("%1$tY-%1$tm-%1$td", mhs.tglLahir));
							System.out.println("Jenis Kelamin: "+mhs.gender+" ("
								+(mhs.gender == 0 ? "Laki-laki" : "Perempuan")+")");
						}
					}
					break;
				case 4:
					dataMahasiswa.tampil();
					break;
			}
		} while(pilihan != 5);
		sc.close();
	}
}