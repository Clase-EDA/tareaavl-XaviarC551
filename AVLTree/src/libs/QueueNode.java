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
public class QueueNode<T> {
    private T value;
    private QueueNode next;

    public QueueNode(T value) {
        this.value = value;
    }
    
    public void setNext(QueueNode n){
        this.next=n;
    }

    public T getValue() {
        return this.value;
    }

    public QueueNode getNext() {
        return this.next;
    }
    
    
    
}
