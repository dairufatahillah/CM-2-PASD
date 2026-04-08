import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Mahasiswa[] listMhs = {
            new Mahasiswa("22001", "Andi", "Teknik Informatika"),
            new Mahasiswa("22002", "Budi", "Teknik Informatika"),
            new Mahasiswa("22003", "Citra", "Sistem Informasi Bisnis")
        };

        Buku[] listBuku = {
            new Buku("B001", "Algoritma", 2020),
            new Buku("B002", "Basis Data", 2019),
            new Buku("B003", "Pemrograman", 2021),
            new Buku("B004", "Fisika", 2024)
        };

        Peminjaman[] listPeminjaman = {
            new Peminjaman(listMhs[0], listBuku[0], 7),
            new Peminjaman(listMhs[1], listBuku[1], 3),
            new Peminjaman(listMhs[2], listBuku[2], 10),
            new Peminjaman(listMhs[2], listBuku[3], 6),
            new Peminjaman(listMhs[0], listBuku[1], 4)
        };

        int menu;
        do {
            System.out.println("\n=== SISTEM PEMINJAMAN RUANG BACA JTI ===");
            System.out.println("1. Tampilkan Mahasiswa");
            System.out.println("2. Tampilkan Buku");
            System.out.println("3. Tampilkan Peminjaman");
            System.out.println("4. Urutkan Berdasarkan Denda (Terbesar)");
            System.out.println("5. Cari Berdasarkan NIM");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1:
                    System.out.println("\nDaftar Mahasiswa:");
                    for (Mahasiswa a : listMhs) a.tampilMahasiswa();
                    break;
                case 2:
                    System.out.println("\nDaftar Buku:");
                    for (Buku b : listBuku) b.tampilBuku();
                    break;
                case 3:
                    System.out.println("\nData Peminjaman:");
                    for (Peminjaman c : listPeminjaman) c.tampilPeminjaman();
                    break;
                case 4:
                    for (int i = 1; i < listPeminjaman.length; i++) {
                        Peminjaman temp = listPeminjaman[i];
                        int j = i;
                        while (j > 0 && listPeminjaman[j - 1].denda < temp.denda) {
                            listPeminjaman[j] = listPeminjaman[j - 1];
                            j--;
                        }
                        listPeminjaman[j] = temp;
                    }
                    System.out.println("\nSetelah diurutkan (Denda terbesar):");
                    for (Peminjaman c : listPeminjaman) c.tampilPeminjaman();
                    break;
                case 5:
                    System.out.print("Masukkan NIM: ");
                    String cariNIM = sc.nextLine();

                    for (int i = 0; i < listPeminjaman.length - 1; i++) {
                        for (int j = 1; j < listPeminjaman.length - i; j++) {
                            if (listPeminjaman[j - 1].mhs.nim.compareTo(listPeminjaman[j].mhs.nim) > 0) {
                                Peminjaman tmp = listPeminjaman[j];
                                listPeminjaman[j] = listPeminjaman[j - 1];
                                listPeminjaman[j - 1] = tmp;
                            }
                        }
                    }

                    int left = 0;
                    int right = listPeminjaman.length - 1;
                    int foundIdx = -1;

                    while (left <= right) {
                        int mid = left + (right - left) / 2;
                        int cmp = listPeminjaman[mid].mhs.nim.compareTo(cariNIM);

                        if (cmp == 0) {
                            foundIdx = mid;
                            break;
                        } else if (cmp < 0) {
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }

                    if (foundIdx != -1) {
                        int mulai = foundIdx;
                        while (mulai > 0 && listPeminjaman[mulai - 1].mhs.nim.equals(cariNIM)) {
                            mulai--;
                        }
                        int akhir = foundIdx;
                        while (akhir < listPeminjaman.length - 1 && listPeminjaman[akhir + 1].mhs.nim.equals(cariNIM)) {
                            akhir++;
                        }

                        System.out.println("\nData Ditemukan:");
                        for (int i = mulai; i <= akhir; i++) {
                            listPeminjaman[i].tampilPeminjaman();
                        }
                    } else {
                        System.out.println("\nData dengan NIM " + cariNIM + " tidak ditemukan");
                    }
                    break;
                case 0:
                    System.out.println("Keluar dari program");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (menu != 0);

        sc.close();
    }
}