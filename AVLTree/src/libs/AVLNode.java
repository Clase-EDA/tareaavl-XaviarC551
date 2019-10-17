/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libs;

/**
 *
 * @author priet
 */
public class AVLNode<T extends Comparable> {
    private T value;
    private int height;
    
    private AVLNode parent;
    private AVLNode leftChild;
    private AVLNode rightChild;

    
    
    public AVLNode(T value){
        this.value=value;
        this.parent=null;
        this.leftChild=null;
        this.leftChild=null;
        this.height=1;
    }
    
    public void setParent(AVLNode parent){
        this.parent=parent;
    }
    public void setLeftChild(AVLNode leftChild){
        this.leftChild=leftChild;
    }
    public void setRightChild(AVLNode rightChild){
        this.rightChild=rightChild;
    }
    public void setBalanceFactor(int height){
        this.height=height;
    }
    public void setHeight(int height) {
        this.height=height;
    }
    
    
    public T getValue() {
        return value;
    }

    public int getHeight(){
        return height;
    }
    
    public int getBalanceFactor() {
        int bf=0;
        AVLNode m=getLeftChild();
        if(m!=null)
            bf-=m.getHeight();
        m=getRightChild();
        if(m!=null)
            bf+=m.getHeight();
        return bf;
    }

    public AVLNode getParent() {
        return parent;
    }

    public AVLNode getLeftChild() {
        return leftChild;
    }

    public AVLNode getRightChild() {
        return rightChild;
    }
    
    public void hang(AVLNode other){
        if(other!=null){
            if(this.getValue().compareTo(other.getValue())>0){
                this.setLeftChild(other);
            }
            else{
                this.setRightChild(other);
            }
            other.setParent(this);
        }
            
    }
    
    
}
