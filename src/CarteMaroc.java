package src;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import src.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hamza
 */
public class CarteMaroc extends javax.swing.JFrame implements MouseListener, KeyListener, Serializable {

    Panel panel;
    public static ArrayList<Ville> villes;
    BreadthFirstSearch engine;
    DepthFirstSearch engine2;

    /**
     * Creates new form CarteMaroc
     */
    public CarteMaroc() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, 0);
        this.setTitle("carte du maroc");
        this.addMouseListener(this);
        this.addKeyListener(this);
        villes = new ArrayList<Ville>();
        lireVilles();
        engine = new BreadthFirstSearch(villes);
        engine2 = new DepthFirstSearch(villes);
        panel = new Panel(villes, engine, engine2);
        this.setContentPane(panel);
        lireLiens();
    }

    // enregistrement des villes
    public void enregistrerVilles() {
        if (!villes.isEmpty()) {
            try {
                FileOutputStream file = new FileOutputStream("src/../Data/Villes.txt");
                ObjectOutputStream out = new ObjectOutputStream(file);
                out.writeObject(this.villes);
                out.flush();
                out.close();
            } catch (IOException ex) {
                System.err.println("Erreur d'ecriture : " + ex);
            }
        }
    }

    // enregistrement des liens
    public void enregistrerLinks() {
        if (engine.link_1.length != 0) {
            try {
                FileOutputStream file = new FileOutputStream("src/../Data/Link1.txt");
                ObjectOutputStream out = new ObjectOutputStream(file);
                out.writeObject(this.engine.link_1);
                out.flush();
                out.close();
            } catch (IOException ex) {
                System.err.println("Erreur d'ecriture : " + ex);
            }
        }
        if (engine.link_2.length != 0) {
            try {
                FileOutputStream file = new FileOutputStream("src/../Data/Link2.txt");
                ObjectOutputStream out = new ObjectOutputStream(file);
                out.writeObject(this.engine.link_2);
                out.flush();
                out.close();
            } catch (IOException ex) {
                System.err.println("Erreur d'ecriture : " + ex);
            }
        }
        //enregitrement du nombre des liens
        try {
            FileOutputStream file = new FileOutputStream("src/../Data/nbLinks.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this.engine.numLinks);
            out.flush();
            out.close();
        } catch (IOException ex) {
            System.err.println("Erreur d'ecriture : " + ex);
        }
    }

    // lecture des villes
    public void lireVilles() {
        try {
            FileInputStream file = new FileInputStream("src/../Data/Villes.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            this.villes = (ArrayList<Ville>) in.readObject();
        } catch (Exception ex) {
            System.err.println("Erreur de lecture " + ex);
        }
    }
    // lecture de nombre de liens
    public void lireNbLiens() {
        try {
            FileInputStream file = new FileInputStream("src/../Data/nbLinks.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            this.engine.numLinks = (int) in.readObject();
        } catch (Exception ex) {
            System.err.println("Erreur de lecture " + ex);
        }
    }

    // lecture des liens 
    public void lireLiens() {
        try {
            FileInputStream file = new FileInputStream("src/../Data/Link1.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            this.engine.link_1 = (int[]) in.readObject();
            lireNbLiens();
        } catch (Exception ex) {
            System.err.println("Erreur de lecture " + ex);
        }
        try {
            FileInputStream file = new FileInputStream("src/../Data/Link2.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            this.engine.link_2 = (int[]) in.readObject();
        } catch (Exception ex) {
            System.err.println("Erreur de lecture " + ex);
        }
    }

    // retourner une ville
    public Ville getVille(String nomVille) {
        for (Ville item : villes) {
            if (item.getNameVille().compareTo(nomVille) == 0) {
                return item;
            }
        }
        return null;
    }

    public void ajouterNouvelleVilleDansArrayList(Ville v) {
        villes.add(v);
        if (!villes.isEmpty()) {
            for (Ville item : villes) {
                System.out.println(" nom de la ville: " + item.getNameVille());
                System.out.println(" posX = " + item.getX());
                System.out.println(" posY = " + item.getY());

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 722));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // enregistrement des villes et liens à la fermeture de l'aplication
        this.enregistrerVilles();
        this.enregistrerLinks();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(CarteMaroc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CarteMaroc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CarteMaroc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CarteMaroc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CarteMaroc().setVisible(true);
            }
        });
    }

    // méthodes pour le listener des cliques de la sourie
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getModifiers()) {
            case InputEvent.BUTTON1_MASK: // clique droit
                new DialogAjouterVille(this, e.getX(), e.getY()).setVisible(true);
                break;
            case InputEvent.BUTTON3_MASK: // clique gauche 
                new DialogLienVille(this).setVisible(true);
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    // méthodes pour le listener des tapes du clavier
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE: // le bouton espace
                new DialogChemin(this).setVisible(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
