
package pasienrs;

import GUI.CetakAntrian;
import GUI.DataDokter;
import GUI.DataKlinik;
import GUI.FormPeriksa;


public class PasienRS {

    
    public static void main(String[] args) {
        // TODO code application logic here
        FormPeriksa per = new FormPeriksa();
        per.setVisible(true);
        DataKlinik kli = new DataKlinik();
        kli.setVisible(true);
        DataDokter dok = new DataDokter();
        dok.setVisible(true);
        CetakAntrian cetak = new CetakAntrian();
        cetak.setVisible(true);
    }
    
}
