package src;

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
abstract public class AbstractGraphSearch implements Serializable {

    final public static int MAX = 500;
    static ArrayList<Ville> villes;
    int num_path = 0;
    public static int[] link_1 = new int[MAX];
    public static int[] link_2 = new int[MAX];
    public int[] lengths = new int[MAX];
    public int maxDepth;
    public Ville[] searchPath = new Ville[MAX];
    public boolean isSearching = true;
    public static int numLinks = 0;
    public Ville goalNodeIndex = null, startNodeIndex = null, currentLoc = null;

    public void addNode(Ville v) {
        System.out.println("ajout de la ville : " + v.getNameVille() + ", " + v.getX() + ", " + v.getY());
        villes.add(v);

    }

    public int getNbNode() {
        return villes.size();
    }

    public int getNumLinks() {
        return numLinks;
    }

    public String getVilleName(int index) {
        try {
            return villes.get(index).getNameVille();
        } catch (Exception e) {
            System.out.println("Error in getVilleName: " + e);
        }
        return "no name"; // error condition
    }

    public int getNodeX(int index) {
        try {
            return villes.get(index).getX();
        } catch (Exception e) {
            System.out.println("Error in getNodePosition: " + e);
        }
        return 0;  // error condition
    }

    public int getNodeY(int index) {
        try {
            return villes.get(index).getY();
        } catch (Exception e) {
            System.out.println("Error in getNodePosition: " + e);
        }
        return 0;  // error condition
    }

    public int getLink1(int index) {
        return link_1[index];
    }

    public int getLink2(int index) {
        return link_2[index];
    }

    public void addLink(int node1, int node2) {
        link_1[numLinks] = node1;
        link_2[numLinks] = node2;
        int dist_squared
                = (villes.get(node1).getX() - villes.get(node2).getX()) * (villes.get(node1).getX() - villes.get(node2).getX())
                + (villes.get(node1).getY() - villes.get(node2).getY()) * (villes.get(node1).getY() - villes.get(node2).getY());
        lengths[numLinks] = (int) Math.sqrt(dist_squared);
        numLinks++;
    }

    public static double get_calculated_dist(int node1, int node2) {
        int dist_squared
                = (villes.get(node1).getX() - villes.get(node2).getX()) * (villes.get(node1).getX() - villes.get(node2).getX())
                + (villes.get(node1).getY() - villes.get(node2).getY()) * (villes.get(node1).getY() - villes.get(node2).getY());
        return (double) Math.sqrt(dist_squared);

    }

    public void addLink(String name1, String name2) {
        int index1 = -1, index2 = -1;
        for (int i = 0; i < villes.size(); i++) {
            if (name1.equals(villes.get(i).getNameVille())) {
                index1 = i;
            }
            if (name2.equals(villes.get(i).getNameVille())) {
                index2 = i;
            }
        }
        if (index1 != -1 && index2 != -1) {
            addLink(index1, index2);
        }
    }

    abstract public Ville[] findPath(Ville start_node, Ville goal_node); // return an array of node indices

    public int getNodeIndex(String name) {
        for (int i = 0; i < villes.size(); i++) {
            if (name.equals(villes.get(i).getNameVille())) {
                return i;
            }
        }
        return -1; // error condition
    }

}
