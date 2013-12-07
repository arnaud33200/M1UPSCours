
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
        trafficLigtht.LightRed();
    }
    
    private void SwitchOffAll() {
        trafficLigtht.SwitchOffAll();
    }

    private void LightOrange() {
        trafficLigtht.LightOrange();
    }

    private void LightGreen() {
        trafficLigtht.LightGreen();
    }
    
    private void LightRedOrange() {
        trafficLigtht.LightRedOrange();
    }

    private void activation() {
        switch( state ) {
            case INITIAL:
                bStart.setEnabled(true);
                bStop.setEnabled(false);
                bPanne.setEnabled(false);
                timerNR.stop();
                timerNRO.stop();
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
                timerNRO.stop();
                timerNV.stop();
                timerNO.stop();
                timerPO.stop();
                timerPE.stop();
                break;
            case NORMALREDORANGE:
                bStart.setEnabled(false);
                bStop.setEnabled(true);
                bPanne.setEnabled(true);
                timerNR.stop();
                timerNRO.restart();
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
                timerNRO.stop();
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
                timerNRO.stop();
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
                timerNRO.stop();
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
                timerNRO.stop();
                timerNV.stop();
                timerNO.stop();
                timerPO.stop();
                timerPE.restart();
                break;
        }
    }

    private enum State { INITIAL, NORMALRED, NORMALREDORANGE, NORMALGREEN, NORMALORANGE, OUTORANGE, OUTOFF };
    private State state;
    private javax.swing.Timer timerNR;
    private javax.swing.Timer timerNRO;
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
        
        timerNRO = new javax.swing.Timer(2000, new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        timerNROActionPerformed(evt);
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

        bStart = new javax.swing.JButton();
        bStop = new javax.swing.JButton();
        bPanne = new javax.swing.JButton();
        trafficLigtht = new TrafficLigtht();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        trafficLigtht.setBackground(new java.awt.Color(102, 102, 102));
        trafficLigtht.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addComponent(bStop)
                        .addGap(79, 79, 79))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(trafficLigtht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)))
                .addComponent(bPanne)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(trafficLigtht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bStart)
                    .addComponent(bStop)
                    .addComponent(bPanne))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void timerNRActionPerformed(java.awt.event.ActionEvent evt) {
        switch( state ) {
            case INITIAL:
                throw new RuntimeException("timerNR - INITIAL");
            case NORMALRED:
                state = State.NORMALREDORANGE;
                activation();
                LightRedOrange();
                break;
            case NORMALREDORANGE:
                throw new RuntimeException("timerNR - NORMALREDORANGE");
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
    
    private void timerNROActionPerformed(java.awt.event.ActionEvent evt) {
        switch( state ) {
            case INITIAL:
                throw new RuntimeException("timerNRO - INITIAL");
            case NORMALRED:
                throw new RuntimeException("timerNRO - NORMALRED");
            case NORMALREDORANGE:
                state = State.NORMALGREEN;
                activation();
                LightGreen();
                break;
            case NORMALGREEN:
                throw new RuntimeException("timerNRO - NORMALGREEN");
            case NORMALORANGE:
                throw new RuntimeException("timerNRO - NORMALORANGE");
            case OUTORANGE:
                throw new RuntimeException("timerNRO - OUTORANGE");
            case OUTOFF:
                throw new RuntimeException("timerNRO - OUTOFF");
        }
    }

    private void timerNVActionPerformed(java.awt.event.ActionEvent evt) {
        switch( state ) {
            case INITIAL:
                throw new RuntimeException("timerNV - INITIAL");
            case NORMALRED:
                throw new RuntimeException("timerNV - NORMALRED");
            case NORMALREDORANGE:
                throw new RuntimeException("timerNV - NORMALREDORANGE");
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
            case NORMALREDORANGE:
                throw new RuntimeException("timerNO - NORMALREDORANGE");
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
            case NORMALREDORANGE:
                throw new RuntimeException("timerPO - NORMALREDORANGE");
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
            case NORMALREDORANGE:
                throw new RuntimeException("timerPE - NORMALREDORANGE");
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
            case NORMALREDORANGE:
                throw new RuntimeException("bStart - NORMALREDORANGE");
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
            case NORMALREDORANGE:
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
            case NORMALREDORANGE:
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
    private TrafficLigtht trafficLigtht;
    // End of variables declaration//GEN-END:variables
}
