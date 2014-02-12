/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compteuravancerecul;

/**
 *
 * @author arnaud
 */
public class Fenetre extends javax.swing.JFrame {

    private void initGraphicCpt() {
        lblCpt.setText("0");
    }

    private void activation() {
        switch(state) {
            case EI:
                timer.stop();
                bStart.setEnabled(true);
                bStop.setEnabled(false);
                bFoward.setEnabled(false);
                bBackward.setEnabled(false);
                break;
            case FOWARD:
                timer.restart();
                bStart.setEnabled(false);
                bStop.setEnabled(true);
                bFoward.setEnabled(false);
                bBackward.setEnabled(true);
                break;
            case BACKWARD:
                timer.restart();
                bStart.setEnabled(false);
                bStop.setEnabled(true);
                bFoward.setEnabled(true);
                bBackward.setEnabled(false);
                break;
        }
    }

    private void affichPouf() {
        lblCpt.setText("POUF !");
    }

    private void affichPasDir() {
        lblDir.setText("");
    }

    private void affichPlus() {
        lblDir.setText("+");
    }

    private void affichMinus() {
        lblDir.setText("-");
    }

    private void incGraphicCpt() {
        lblCpt.setText("" + cpt);
    }

    private void decGraphicCpt() {
        lblCpt.setText("" + cpt);
    }

    private enum State {

        EI, FOWARD, BACKWARD
    };
    private State state;
    private int cpt;
    private javax.swing.Timer timer;

    /**
     * Creates new form Fenetre
     */
    public Fenetre() {

        initComponents();
        // INIT TIMER
        timer = new javax.swing.Timer(1000,
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        timerActionPerformed(evt);
                    }
                }
        );
        
        affichPasDir();
        cpt = 0;
        state = State.EI;
        activation();
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
        bFoward = new javax.swing.JButton();
        bBackward = new javax.swing.JButton();
        lblCpt = new javax.swing.JLabel();
        lblDir = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

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

        bFoward.setText("> > >");
        bFoward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFowardActionPerformed(evt);
            }
        });

        bBackward.setText("< < <");
        bBackward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBackwardActionPerformed(evt);
            }
        });

        lblCpt.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        lblCpt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCpt.setText("0");

        lblDir.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        lblDir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDir.setText("+");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                        .addComponent(bStop))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bBackward)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bFoward))
                    .addComponent(lblDir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCpt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bFoward)
                    .addComponent(bBackward))
                .addGap(42, 42, 42)
                .addComponent(lblDir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCpt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bStart)
                    .addComponent(bStop))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStartActionPerformed
        switch (state) {
            case EI:
                initGraphicCpt();
                affichPlus();
                cpt = 0;
                state = State.FOWARD;
                activation();
                break;
            case FOWARD:
                throw new RuntimeException("bStart - FOWARD");
            case BACKWARD:
                throw new RuntimeException("bStart - BACKWARD");
        }
    }//GEN-LAST:event_bStartActionPerformed

    private void bStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStopActionPerformed
        switch (state) {
            case EI:
                throw new RuntimeException("bStop - EI");
            case FOWARD:
                affichPouf();
                affichPasDir();
                cpt = 0;
                state = State.EI;
                activation();
                break;
            case BACKWARD:
                affichPouf();
                affichPasDir();
                cpt = 0;
                state = State.EI;
                activation();
                break;
        }
    }//GEN-LAST:event_bStopActionPerformed

    private void bFowardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFowardActionPerformed
        switch (state) {
            case EI:
                throw new RuntimeException("bFoward - EI");
            case FOWARD:
                throw new RuntimeException("bFoward - FOWARD");
            case BACKWARD:
                affichPlus();
                cpt = cpt;
                state = State.FOWARD;
                activation();
                break;
        }
    }//GEN-LAST:event_bFowardActionPerformed

    private void bBackwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBackwardActionPerformed
        switch (state) {
            case EI:
                throw new RuntimeException("bBackward - EI");
            case FOWARD:
                affichMinus();
                cpt = cpt;
                state = State.BACKWARD;
                activation();
                break;
            case BACKWARD:
                throw new RuntimeException("bBackward - BACKWARD");
        }
    }//GEN-LAST:event_bBackwardActionPerformed

    private void timerActionPerformed(java.awt.event.ActionEvent evt) {
        switch (state) {
            case EI:
                throw new RuntimeException("bBackward - EI");
            case FOWARD:
                if (cpt < 10) {
                    ++cpt;
                    incGraphicCpt();      
                    state = State.FOWARD;
                } else if (cpt >= 10) {
                    cpt = 10;
                    incGraphicCpt();
                    affichPasDir(); 
                    state = State.EI;
                }
                activation();
                break;
            case BACKWARD:
                if (cpt > 0) {
                    --cpt;
                    decGraphicCpt();
                    state = State.BACKWARD;
                } else if (cpt <= 0) {
                    // PAS D'ACTION
                    cpt = 0;
                    decGraphicCpt();
                    affichPasDir();
                    state = State.EI;
                }
                activation();
                break;

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
    private javax.swing.JButton bBackward;
    private javax.swing.JButton bFoward;
    private javax.swing.JButton bStart;
    private javax.swing.JButton bStop;
    private javax.swing.JLabel lblCpt;
    private javax.swing.JLabel lblDir;
    // End of variables declaration//GEN-END:variables
}