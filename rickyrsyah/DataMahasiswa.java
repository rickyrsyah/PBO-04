package rickyrsyah;

import java.util.ArrayList;
import java.util.Iterator;

class DataMahasiswa {
	private ArrayList<Mahasiswa> dataMahasiswa = new ArrayList<Mahasiswa>();
	
	void tambah(Mahasiswa mahasiswa) {
		dataMahasiswa.add(mahasiswa);
	}

	boolean hapus(String nim) {
		Iterator it = dataMahasiswa.iterator();
		boolean ret = false;
		while(it.hasNext()) {
			if (((Mahasiswa) it.next()).nim.equals(nim) ) {
				ret = true;
				it.remove();
			}
		}
		return ret;
	}
	Mahasiswa[] cari(Mahasiswa mahasiswa) {
		ArrayList<Mahasiswa> ret = new ArrayList<Mahasiswa>();
		Iterator it = dataMahasiswa.iterator();
		Mahasiswa comp;

		if (mahasiswa.nim != null) {
			while(it.hasNext()) {
				comp = (Mahasiswa) it.next();
				if (comp.nim.equals(mahasiswa.nim)) {
					ret.add(comp);
				}
			}
		} else {
			while(it.hasNext()) {
				comp = (Mahasiswa) it.next();
				if (comp.gender == mahasiswa.gender) {
					ret.add(comp);
				}
			}
		}
		return ret.toArray(new Mahasiswa[ret.size()]);
	}

	void tampil() {
		System.out.println("\n== Menampilkan semua data mahasiswa ==");
		int no = 0;
		for (Mahasiswa mhs: dataMahasiswa) {
			System.out.println((++no)+" ----------------");
			System.out.println("NIM: "+mhs.nim);
			System.out.println("Nama: "+mhs.nama);
			System.out.println("Tanggal Lahir: "+String.format("%1$tY-%1$tm-%1$td", mhs.tglLahir));
			System.out.println("Jenis Kelamin: "+mhs.gender+" ("
				+(mhs.gender == 0 ? "Laki-laki" : "Perempuan")+")");
		}
		System.out.println(
			"----------------\n"
			+"Total data yang direkam: "+no
			);
	}
}