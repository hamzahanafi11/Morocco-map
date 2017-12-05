
import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc
 */
public class Ville implements Serializable {

    String villeName;
    int x;
    int y;

    // utilisé pour le Astar
    public double g_scores;
    public final double h_scores;
    public double f_scores = 0;
    public ArrayList<Ville> adjacencies = new ArrayList<Ville>();
    private Ville parent;

    public Ville getParent() {
        return parent;
    }

    public void setParent(Ville parent) {
        this.parent = parent;
    }

    public Ville() {
        this.g_scores = 0;
        this.h_scores = 0;
    }

    public Ville(String name, int x, int y) {
        this.villeName = name;
        this.x = x;
        this.y = y;
        this.g_scores = 0;
        this.h_scores = 0;
    }

    public String getNameVille() {
        return this.villeName;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
