
package GUI;

import Koneksi.KoneksiDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class CariPasien extends javax.swing.JFrame {

    /**
     * Creates new form CariPasien
     */
    public CariPasien() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nik = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        cari = new javax.swing.JButton();
        selesai = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("NIK");

        jLabel3.setText("Nama");

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIK", "Nama", "Usia", "Tanggal Lahir", "Nama Ibu"
            }
        ));
        jScrollPane1.setViewportView(tabel);

        cari.setText("Cari");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        selesai.setText("Selesai");
        selesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selesaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(selesai, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(nik, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(55, 55, 55)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(nik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selesai)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
         String NIK = nik.getText();
         String Nama = nama.getText();
        
        //Mencari kereta dalam database sesuai rute yang diminta 
        try {
            //Membuat koneksi
            Connection conn = KoneksiDB.getKoneksi();
            Statement s = conn.createStatement();
            //Membuat query SELECT
            String sql = "SELECT * FROM tb_pasien INNER JOIN mendaftar ON tb_pasien.nik = tb_pasien.nik WHERE tb_pasien.nik = '"+ NIK +"' AND tb_pasien.nama = '"+ Nama +"'";

            ResultSet r = s.executeQuery(sql);
            //Membaca data di dalam database baris per baris
            while (r.next()) {
            //Membuat objek 'obj' untuk menampung data yang dibaca dari DB
            int IdAntrian = r.getInt("id_antrian");
            String _NIK = r.getString("nik");
            String _Nama = r.getString("nama");
            
 
        //Membuat tabel model dan menampilkan hasil query ke tabel
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        Object [] data = {IdAntrian,_NIK,_Nama};
        model.addRow(data);
     }       
        //menutup hasil penelusuran dan koneksi
        r.close();
        s.close();
        
        } catch (SQLException e) {
            String pesan = "Data tidak ditemukan!";
            JOptionPane.showMessageDialog(null,pesan,"Kesalahan",JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null,e.getMessage());
    
     } 
                                      

    }//GEN-LAST:event_cariActionPerformed

    private void selesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selesaiActionPerformed
        // TODO add your handling code here:
        FormPeriksa fop = new FormPeriksa();
        fop.setVisible(true);

        dispose();
    }//GEN-LAST:event_selesaiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CariPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CariPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CariPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CariPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CariPasien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField nik;
    private javax.swing.JButton selesai;
    private javax.swing.JTable tabel;
    // End of variables declaration//GEN-END:variables
}