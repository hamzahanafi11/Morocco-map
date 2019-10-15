package src;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc
 */

import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class AstarSearch extends AbstractGraphSearch {

    public static Ville start, goal;
    public static ArrayList<Ville> pathf;

    public static ArrayList<Ville> printPath(Ville target) {
        ArrayList<Ville> path = new ArrayList<Ville>();
        /*    System.out.println(" size :::::::(((>>>> " + villes.size());

        for (int i = 0; i < villes.size(); i++) {
            if (villes.get(i).getParent() != null) {
                System.out.println("City :::: ====> " + villes.get(i).getNameVille() + " Parent ::::: =====> " + villes.get(i).getParent().getNameVille());
            }

        }
         */
        Ville ville = target;
        while (!ville.getNameVille().equals(start.getNameVille())) {
            //   System.out.println("*****************City :::: ====> " + ville.getNameVille() + " Parent ::::: =====> " + ville.getParent().getNameVille());
            path.add(ville);
            ville = ville.getParent();
        }
        path.add(start);

        Collections.reverse(path);
        return path;
    }

    public void AstarSearch(Ville source, Ville goal) {

        Set<Ville> explored = new HashSet<Ville>();

        PriorityQueue<Ville> queue = new PriorityQueue<Ville>(20,
                new Comparator<Ville>() {
            //override compare method
            public int compare(Ville i, Ville j) {
                if (i.f_scores > j.f_scores) {
                    return 1;
                } else if (i.f_scores < j.f_scores) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
        );

        //cost from start
        source.g_scores = 0;

        queue.add(source);

        boolean found = false;

        while ((!queue.isEmpty()) && (!found)) {
            //the node in having the lowest f_score value
            Ville current = queue.poll();
            //  System.out.println("current : " + current);
            explored.add(current);
            //goal found
            if (current.getNameVille().equals(goal.getNameVille())) {
                System.out.println("Chemin trouvé ");
                found = true;
            }
            current.adjacencies = connected_node(getNodeIndexs(current.getNameVille()));
            /*
            System.out.println("les adjacencies de " + current);

            for (Ville e : current.adjacencies) {
                System.out.println(e);
            }
             */
            for (Ville e : current.adjacencies) {
                Ville child = e;
                double cost = get_calculated_dist(getNodeIndexs(current.getNameVille()), getNodeIndexs(e.getNameVille()));
                double temp_g_scores = current.g_scores + cost;
                double temp_f_scores = temp_g_scores + child.h_scores;

                if ((explored.contains(child))
                        && (temp_f_scores >= child.f_scores)) {
                    continue;
                } else if ((!queue.contains(child))
                        || (temp_f_scores < child.f_scores)) {

                    Ville c2 = null;
                    for (Ville c : villes) {
                        if (c.getNameVille().equals(current.getNameVille())) {
                            child.setParent(c);
                            c2 = c;
                        }
                    }
                    for (Ville c : villes) {
                        if (c.getNameVille().equals(child.getNameVille())) {
                            c.setParent(c2);
                        }
                    }

                    child.g_scores = temp_g_scores;
                    child.f_scores = temp_f_scores;

                    if (queue.contains(child)) {
                        queue.remove(child);
                    }

                    queue.add(child);

                }
            }
        }
        /*
        System.out.println("Parcour des parents : ");
        for (Ville c : villes) {
            if (c.getParent() != null) {
                System.out.println("ville :  " + c.getNameVille() + " son Parent est : " + c.getParent().getNameVille());
            }
        }
         */
    }

    public static ArrayList<Ville> connected_node(int node) {
        int[] ret = new int[AbstractGraphSearch.MAX];
        int num = 0;

        for (int n = 0; n < villes.size(); n++) {
            boolean connected = false;
            // See if there is a link between node 'node' and 'n':
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

        ArrayList rett = new ArrayList();
        for (int i = 0; i < num; i++) {

            rett.add(villes.get(ret[i])); // un tableau contenant les villes connéctées à la ville passée en paramètre
        }

        return rett;
    }

    public static int getNodeIndexs(String name) {
        for (int i = 0; i < villes.size(); i++) {
            if (name.equals(villes.get(i).getNameVille())) {
                return i;
            }
        }
        return -1; // error condition
    }

    @Override
    public Ville[] findPath(Ville start_node, Ville goal_node) {
        start = start_node;
        goal = goal_node;

        AstarSearch(start, goal);
        pathf = printPath(goal);
        Ville pathr[] = new Ville[pathf.size()];
        for (int i = 0; i < pathf.size(); i++) {
            pathr[i] = pathf.get(i);
        }
        return pathr;
    }

}
