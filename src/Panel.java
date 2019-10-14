package src;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hamza
 */
public class Panel extends JPanel {

    ArrayList<Ville> villes;
    BreadthFirstSearch engine;
    DepthFirstSearch engine2;
    Ville[] path;
    int choix;
    
    public Panel(ArrayList<Ville> v, BreadthFirstSearch e, DepthFirstSearch e2){
        this.villes=v;
        this.engine=e;
        this.engine2=e2;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d= (Graphics2D) g;
        super.paintComponent(g);
        try {

            Image image = ImageIO.read(new File("src/../Data/map.png"));
            g.drawImage(image, 0, 0, this);
            if (!villes.isEmpty()) {
                for (Ville item : villes) {
                    paintNode(g, item.villeName, item.getX(), item.getY());
                }
            }
            for (int i = 0; i < engine.getNumLinks(); i++) {
                int l1 = engine.getLink1(i);
                int l2 = engine.getLink2(i);
                int x1 = engine.getNodeX(l1) - 3;
                int y1 = engine.getNodeY(l1) - 25;
                int x2 = engine.getNodeX(l2) - 3;
                int y2 = engine.getNodeY(l2) - 25;
                
                g2d.setStroke(new BasicStroke(2));
                g2d.setColor(Color.BLACK);
                g2d.draw(new Line2D.Float(x1, y1, x2, y2));
                g.setColor(Color.BLACK);
                g.drawLine(x1, y1, x2, y2);
            }
            switch (choix) {
                case 1:
                    for (int i = 1; i < path.length; i++) {
                        int x1 = engine.getNodeX(engine.getNodeIndex(path[i - 1].getNameVille())) - 3;
                        int y1 = engine.getNodeY(engine.getNodeIndex(path[i - 1].getNameVille())) - 25;
                        int x2 = engine.getNodeX(engine.getNodeIndex(path[i].getNameVille())) - 3;
                        int y2 = engine.getNodeY(engine.getNodeIndex(path[i].getNameVille())) - 25;
                        g2d.setStroke(new BasicStroke(3));
                        g2d.setColor(Color.blue);
                        g2d.draw(new Line2D.Float(x1, y1, x2, y2));
                    }
                    engine.T.clear();
                    engine.L.clear();
                    engine.file.clear();
                    break;
                case 2:
                    for (int i = 1; i < path.length; i++) {
                        int x1 = engine2.getNodeX(engine2.getNodeIndex(path[i - 1].getNameVille())) - 3;
                        int y1 = engine2.getNodeY(engine2.getNodeIndex(path[i - 1].getNameVille())) - 25;
                        int x2 = engine2.getNodeX(engine2.getNodeIndex(path[i].getNameVille())) - 3;
                        int y2 = engine2.getNodeY(engine2.getNodeIndex(path[i].getNameVille())) - 25;
                        g.setColor(Color.red);
                        g2d.setStroke(new BasicStroke(3));
                        g2d.setColor(Color.blue);
                        g2d.draw(new Line2D.Float(x1, y1, x2, y2));
                        g.drawLine(x1, y1, x2, y2);
                    }
                    engine2.T.clear();
                    engine2.L.clear();
                    engine2.pile.clear();
                    break;
                case 3:
                    for (int i = 1; i < path.length; i++) {
                        int x1 = path[i-1].getX()-3;
                        int y1 = path[i-1].getY()-25;
                        int x2 = path[i].getX()-3;
                        int y2 = path[i].getY()-25;
                        g2d.setStroke(new BasicStroke(3));
                        g2d.setColor(Color.blue);
                        g2d.draw(new Line2D.Float(x1, y1, x2, y2));
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("no image found" + e.getMessage());
        }
        g.setColor(Color.red);
    }

    protected void paintNode(Graphics g, String name, int x, int y) {
        g.setColor(Color.red);
        g.fillOval(x - 9, y - 30, 10, 10); // il y'a un décalage!!
    }
}
