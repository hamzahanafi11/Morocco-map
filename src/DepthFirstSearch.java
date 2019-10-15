package src;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc
 */
public class DepthFirstSearch extends AbstractGraphSearch implements Serializable {

    ArrayList<Ville> L = new ArrayList<Ville>(), T = new ArrayList<Ville>();  //L =liste des alreadyVisited  T = les etats but
    LinkedList<Ville> pile = new LinkedList<Ville>();                          // pile des sommets à explorer
   
    Ville[] predecessor ;

    public DepthFirstSearch(ArrayList<Ville> v){
        this.villes = v;
    
    }
    
    // retourne un chemin entre les villes passées en parametre dans un tableau de villes 
    public Ville[] findPath(Ville start_node, Ville goal_node) {
        System.out.println("Entered DepthFirstSearch.findPath("
                + start_node.getNameVille() + ", " + goal_node.getNameVille() + ")");
        predecessor = new Ville[villes.size()];
        for (int i = 0; i < villes.size(); i++) {
            predecessor[i] = null;
        }
        pile.add(start_node);
        T.add(goal_node);
        doDFS();
        // calcul du plus court chemin apartir du tableau des predecesseurs
        maxDepth = 0;

        if (!isSearching) {
              searchPath = new Ville[1000];
            searchPath[maxDepth++] = goal_node;
            for (int i = 0; i < 1000; i++) {
                searchPath[maxDepth] = predecessor[getNodeIndex(searchPath[maxDepth - 1].getNameVille())];
                maxDepth++;
                if ((searchPath[maxDepth - 1].getNameVille().compareTo(start_node.getNameVille())) == 0) {
                    break;  // retourner au noeud de depart
                }
            }
        }
        return searchPath;
    }

    private void doDFS() {
        while (!pile.isEmpty()) {
            currentLoc = pile.removeFirst();
            L.add(currentLoc);
            if (T.contains(currentLoc)) {
                System.out.println("But trouvé : (" + currentLoc.getNameVille() + ")");
                isSearching = false;
                break;
            } else {
                Ville[] connected = connected_nodes(getNodeIndex(currentLoc.getNameVille()));
                for (int i = 0; i < connected.length; i++) {
                    if (connected[i] != null && !pile.contains(connected[i]) && !L.contains(connected[i])) {
                        predecessor[getNodeIndex(connected[i].getNameVille())] = currentLoc;
                        pile.addFirst(connected[i]);
                    }
                }
            }
        }
    }

    // retourne un tableaux des villes connectée à l'indice de la ville passée en paramètre
    protected Ville[] connected_nodes(int node) {
        int[] ret = new int[AbstractGraphSearch.MAX];
        int num = 0;

        for (int n = 0; n < villes.size(); n++) {
            boolean connected = false;
            // vérifier s'il ya un liens entre les villes: 'node' et 'n':
            for (int i = 0; i < numLinks; i++) {
                if (link_1[i] == node) {
                    if (link_2[i] == n) {
                        connected = true;
                        break;
                    }
                }
                if (link_2[i] == node) {
                    if (link_1[i] == n) {
                        connected = true;
                        break;
                    }
                }
            }
            if (connected) {
                ret[num++] = n;
            }
        }
        if (num == 0) {
            return null;
        }
        Ville[] rett = new Ville[num];
        for (int i = 0; i < num; i++) {

            rett[i] = villes.get(ret[i]); // un tableau contenant les villes connéctées à la ville passée en paramètre
        }
        return rett;
    }

}
