
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arnaud
 */
public class Fenetre extends javax.swing.JFrame {

    private void LightRed() {
        lblRouge.setForeground(Color.RED);
        lblVert.setForeground(Color.GRAY);
        lblOrange.setForeground(Color.GRAY);
    }

    private void activation() {
        switch( state ) {
            case INITIAL:
                bStart.setEnabled(true);
                bStop.setEnabled(false);
                bPanne.setEnabled(false);
                timerNR.stop();
                timerNV.stop();
                timerNO.stop();
                timerPO.stop();
                timerPE.stop();
                break;   
            case NORMALRED:
                bStart.setEnabled(false);
                bStop.setEnabled(true);
                bPanne.setEnabled(true);
                timerNR.restart();
                timerNV.stop();
                timerNO.stop();
                timerPO.stop();
                timerPE.stop();
                break;
            case NORMALGREEN:
                bStart.setEnabled(false);
                bStop.setEnabled(true);
                bPanne.setEnabled(true);
                timerNR.stop();
                timerNV.restart();
                timerNO.stop();
                timerPO.stop();
                timerPE.stop();
                break;
            case NORMALORANGE:
                bStart.setEnabled(false);
                bStop.setEnabled(true);
                bPanne.setEnabled(true);
                timerNR.stop();
                timerNV.stop();
                timerNO.restart();
                timerPO.stop();
                timerPE.stop();
                break;
            case OUTORANGE:
                bStart.setEnabled(true);
                bStop.setEnabled(true);
                bPanne.setEnabled(false);
                timerNR.stop();
                timerNV.stop();
                timerNO.stop();
                timerPO.restart();
                timerPE.stop();
                break;
            case OUTOFF:
                bStart.setEnabled(true);
                bStop.setEnabled(true);
                bPanne.setEnabled(false);
                timerNR.stop();
                timerNV.stop();
                timerNO.stop();
                timerPO.stop();
                timerPE.restart();
                break;
        }
    }

    private void SwitchOffAll() {
        lblRouge.setForeground(Color.GRAY);
        lblVert.setForeground(Color.GRAY);
        lblOrange.setForeground(Color.GRAY);
    }

    private void LightOrange() {
        lblRouge.setForeground(Color.GRAY);
        lblVert.setForeground(Color.GRAY);
        lblOrange.setForeground(Color.ORANGE);
    }

    private void LightGreen() {
        lblRouge.setForeground(Color.GRAY);
        lblVert.setForeground(Color.GREEN);
        lblOrange.setForeground(Color.GRAY);
    }
    private enum State { INITIAL, NORMALRED, NORMALGREEN, NORMALORANGE, OUTORANGE, OUTOFF };
    private State state;
    private javax.swing.Timer timerNR;
    private javax.swing.Timer timerNV;
    private javax.swing.Timer timerNO;
    private javax.swing.Timer timerPO;
    private javax.swing.Timer timerPE;

    /**
     * Creates new form Fenetre
     */
    public Fenetre() {
        initComponents();

        timerNR = new javax.swing.Timer(5000, new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        timerNRActionPerformed(evt);
                    }
                }
                );

        timerNV = new javax.swing.Timer(5000, new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        timerNVActionPerformed(evt);
                    }
                }
                );

        timerNO = new javax.swing.Timer(2000, new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        timerNOActionPerformed(evt);
                    }
                }
                );

        timerPO = new javax.swing.Timer(1000, new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        timerPOActionPerformed(evt);
                    }
                }
                );

        timerPE = new javax.swing.Timer(500, new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        timerPEActionPerformed(evt);
                    }
                }
                );

        state = State.INITIAL;
        activation();
        SwitchOffAll();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRouge = new javax.swing.JLabel();
        lblOrange = new javax.swing.JLabel();
        lblVert = new javax.swing.JLabel();
        bStart = new javax.swing.JButton();
        bStop = new javax.swing.JButton();
        bPanne = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblRouge.setFont(new java.awt.Font("DejaVu Sans Mono", 1, 36)); // NOI18N
        lblRouge.setForeground(new java.awt.Color(255, 0, 0));
        lblRouge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRouge.setText("ROUGE");

        lblOrange.setFont(new java.awt.Font("DejaVu Sans Mono", 1, 36)); // NOI18N
        lblOrange.setForeground(new java.awt.Color(255, 153, 0));
        lblOrange.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrange.setText("ORANGE");

        lblVert.setFont(new java.awt.Font("DejaVu Sans Mono", 1, 36)); // NOI18N
        lblVert.setForeground(new java.awt.Color(0, 204, 51));
        lblVert.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVert.setText("VERT");

        bStart.setText("START");
        bStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStartActionPerformed(evt);
            }
        });

        bStop.setText("STOP");
        bStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStopActionPerformed(evt);
            }
        });

        bPanne.setText("PANNE");
        bPanne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPanneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOrange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRouge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addComponent(bStop)
                        .addGap(79, 79, 79)
                        .addComponent(bPanne))
                    .addComponent(lblVert, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lblRouge)
                .addGap(52, 52, 52)
                .addComponent(lblOrange)
                .addGap(48, 48, 48)
                .addComponent(lblVert)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bStart)
                    .addComponent(bStop)
                    .addComponent(bPanne))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void timerNRActionPerformed(java.awt.event.ActionEvent evt) {
        switch( state ) {
            case INITIAL:
                throw new RuntimeException("timerNR - INITIAL");
            case NORMALRED:
                state = State.NORMALGREEN;
                activation();
                LightGreen();
                break; 
            case NORMALGREEN:
                throw new RuntimeException("timerNR - NORMALGREEN");
            case NORMALORANGE:
                throw new RuntimeException("timerNR - NORMALORANGE");
            case OUTORANGE:
                throw new RuntimeException("timerNR - OUTORANGE");
            case OUTOFF:
                throw new RuntimeException("timerNR - OUTOFF");
        }
    }

    private void timerNVActionPerformed(java.awt.event.ActionEvent evt) {
        switch( state ) {
            case INITIAL:
                throw new RuntimeException("timerNV - INITIAL");
            case NORMALRED:
                throw new RuntimeException("timerNV - NORMALRED");
            case NORMALGREEN:
                state = State.NORMALORANGE;
                activation();
                LightOrange();
                break;
            case NORMALORANGE:
                throw new RuntimeException("timerNV - NORMALORANGE");
            case OUTORANGE:
                throw new RuntimeException("timerNV - OUTORANGE");
            case OUTOFF:
                throw new RuntimeException("timerNV - OUTOFF");
        }
    }

    private void timerNOActionPerformed(java.awt.event.ActionEvent evt) {
        switch( state ) {
            case INITIAL:
                throw new RuntimeException("timerNO - INITIAL");
            case NORMALRED:
                throw new RuntimeException("timerNO - NORMALRED");
            case NORMALGREEN:
                throw new RuntimeException("timerNO - NORMALGREEN");
            case NORMALORANGE:
                state = State.NORMALRED;
                activation();
                LightRed();
                break;
            case OUTORANGE:
                throw new RuntimeException("timerNO - OUTORANGE");
            case OUTOFF:
                throw new RuntimeException("timerNO - OUTOFF");
        }
    }

    private void timerPOActionPerformed(java.awt.event.ActionEvent evt) {
        switch( state ) {
            case INITIAL:
                throw new RuntimeException("timerPo - INITIAL");
            case NORMALRED:
                throw new RuntimeException("timerPo - NORMALRED");
            case NORMALGREEN:
                throw new RuntimeException("timerPo - NORMALGREEN");
            case NORMALORANGE:
                throw new RuntimeException("timerPo - NORMALORANGE");
            case OUTORANGE:
                state = State.OUTOFF;
                activation();
                SwitchOffAll();
                break;
            case OUTOFF:
                throw new RuntimeException("timerPo - OUTOFF");
        }
    }

    private void timerPEActionPerformed(java.awt.event.ActionEvent evt) {
        switch( state ) {
            case INITIAL:
                throw new RuntimeException("timerPE - INITIAL");
            case NORMALRED:
                throw new RuntimeException("timerPE - NORMALRED");
            case NORMALGREEN:
                throw new RuntimeException("timerPE - NORMALGREEN");
            case NORMALORANGE:
                throw new RuntimeException("timerPE - NORMALORANGE");
            case OUTORANGE:
                throw new RuntimeException("timerPE - OUTORANGE");
            case OUTOFF:
                state = State.OUTORANGE;
                activation();
                LightOrange();
                break;
        }
    }


    private void bStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStartActionPerformed
        switch( state ) {
            case INITIAL:
                state = State.NORMALRED;
                activation();
                LightRed();
                break;
            case NORMALRED:
                throw new RuntimeException("bStart - NORMALRED");
            case NORMALGREEN:
                throw new RuntimeException("bStart - NORMALGREEN");
            case NORMALORANGE:
                throw new RuntimeException("bStart - NORMALORANGE");
            case OUTORANGE:
                state = State.NORMALRED;
                activation();
                LightRed();
                break;
            case OUTOFF:
                state = State.NORMALRED;
                activation();
                LightRed();
                break;  
        }
    }//GEN-LAST:event_bStartActionPerformed

    private void bStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStopActionPerformed
        switch( state ) {
            case INITIAL:
                throw new RuntimeException("bStop - INITIAL");
            case NORMALRED:
                state = State.INITIAL;
                activation();
                SwitchOffAll();
                break;  
            case NORMALGREEN:
                state = State.INITIAL;
                activation();
                SwitchOffAll();
                break;  
            case NORMALORANGE:
                state = State.INITIAL;
                activation();
                SwitchOffAll();
                break;  
            case OUTORANGE:
                state = State.INITIAL;
                activation();
                SwitchOffAll();
                break;  
            case OUTOFF:
                state = State.INITIAL;
                activation();
                SwitchOffAll();
                break;  
        }
    }//GEN-LAST:event_bStopActionPerformed

    private void bPanneActionPerformed(java.awt.event.ActionEvent evt) {                                       
        switch( state ) {
            case INITIAL:
                throw new RuntimeException("bPanne - INITIAL");
            case NORMALRED:
                state = State.OUTORANGE;
                activation();
                LightOrange();
                break;  
            case NORMALGREEN:
                state = State.OUTORANGE;
                activation();
                LightOrange();
                break;  
            case NORMALORANGE:
                state = State.OUTORANGE;
                activation();
                LightOrange();
                break;  
            case OUTORANGE:
                throw new RuntimeException("bPanne - OUTORANGE");
            case OUTOFF:
                throw new RuntimeException("bPanne - OUTOFF");
        }
    }                                      

    
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
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fenetre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bPanne;
    private javax.swing.JButton bStart;
    private javax.swing.JButton bStop;
    private javax.swing.JLabel lblOrange;
    private javax.swing.JLabel lblRouge;
    private javax.swing.JLabel lblVert;
    // End of variables declaration//GEN-END:variables
}
