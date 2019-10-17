/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import libs.AVLTree;

/**
 *
 * @author priet
 */
public class Main {
    public static void main(String[] args){
        AVLTree<Integer> tree=new AVLTree ();
        
        tree.insert(20);
        tree.insert(9);
        tree.insert(10);
        tree.insert(15);
        tree.insert(30);
        
        
        tree.print();
        
        tree.remove(9);
        tree.print();

        tree.remove(10);
        tree.print();
        

    }
}
