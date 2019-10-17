/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libs;

import java.util.LinkedList;

/**
 *
 * @author priet
 */
public class Queue<T> {
    private QueueNode<T> first;
    private QueueNode<T> last;

    public Queue() {
        first=null;
    }
    
    public T peek(){
        if(isEmpty())
            return null;
        return first.getValue();
    }
    
    public void pop(){
        QueueNode node=first;
        first=first.getNext();
        
        node.setNext(null);
    }
    
    public void push(T e){
        QueueNode<T> newNode=new QueueNode(e);
        if(isEmpty()){
            first=newNode;
        }
        else{
            last.setNext(newNode);
        }
        last=newNode;
    }
    
    public boolean isEmpty(){return first==null;}
}
