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
public class AVLTree<T extends Comparable> {
    private AVLNode root;
    

    public AVLTree() {
        this.root=null;
    }
    public boolean isEmpty(){return this.root==null;}
    private int max(int a, int b){return a>b?a:b;}
    private int height(AVLNode n){return n==null?0:n.getHeight();}
    public void insert(T value){
        AVLNode newNode=new AVLNode(value);
        if(isEmpty()){
            this.root=newNode;
        }
        else{
            AVLNode prev=this.root;
            AVLNode curr=this.root;
            while(curr!=null){
                prev=curr;
                if(curr.getValue().compareTo(value)>0){
                    curr=curr.getLeftChild();
                }
                else if(curr.getValue().compareTo(value)<0){
                    curr=curr.getRightChild();
                }
                else{
                    return;
                }
            }
            prev.hang(newNode);
            curr=prev;
            while(curr!=null){
                curr.setHeight(1+max(height(curr.getLeftChild()),
                        height(curr.getRightChild())));
                if(curr.getBalanceFactor()>1){
                    
                    if(curr.getRightChild().getBalanceFactor()==1){
                        curr=rotateRightRight(curr);
                    }
                    else if(curr.getRightChild().getBalanceFactor()==-1){
                        curr=rotateRightLeft(curr);
                    }
                }
                else if(curr.getBalanceFactor()<-1){
                    if(curr.getLeftChild().getBalanceFactor()==1){
                        curr=rotateLeftRight(curr);
                    }
                    else if(curr.getLeftChild().getBalanceFactor()==-1){
                        curr=rotateLeftLeft(curr);
                
                    }
                }
                else{
                    curr=curr.getParent();
                }
            }
        }
    }
    
    private AVLNode rotateRightRight(AVLNode n){
        AVLNode p=n.getParent();
        
        AVLNode alpha=n;
        AVLNode beta=n.getRightChild();
        AVLNode gamma=n.getRightChild().getRightChild();
        
        AVLNode a=alpha.getLeftChild();
        AVLNode b=beta.getLeftChild();
        
        alpha.setRightChild(b);
        if(b!=null)
            b.setParent(alpha);
        beta.hang(alpha);
        
        alpha.setHeight(1+max(height(a),height(b)));
        beta.setHeight(1+max(height(alpha),height(gamma)));
        
        if(p!=null)
            p.hang(beta);
        else{
            root=beta;
            beta.setParent(null);
        }
        
        return p;
    }
    
    private AVLNode rotateLeftLeft(AVLNode n){
        AVLNode p=n.getParent();
        
        AVLNode alpha=n;
        AVLNode beta=n.getLeftChild();
        AVLNode gamma=n.getLeftChild().getLeftChild();
        
        AVLNode a=alpha.getRightChild();
        AVLNode b=beta.getRightChild();
        
        alpha.setLeftChild(b);
        if(b!=null)
            b.setParent(b);
        beta.hang(alpha);
        
        alpha.setHeight(1+max(height(a),height(b)));
        beta.setHeight(1+max(height(alpha),height(gamma)));
        
        if(p!=null)
            p.hang(beta);
        else{
            root=beta;
            beta.setParent(null);
        }
        return p;
    }
    
    private AVLNode rotateLeftRight(AVLNode n){
        AVLNode p=n.getParent();
        
        AVLNode alpha=n;
        AVLNode beta=n.getLeftChild();
        AVLNode gamma=n.getLeftChild().getRightChild();
        
       
        
        AVLNode a=alpha.getRightChild();
        AVLNode b=beta.getLeftChild();
        AVLNode c=gamma.getLeftChild();
        AVLNode d=gamma.getRightChild();
        
        gamma.hang(alpha);
        gamma.hang(beta);
        beta.setRightChild(c);
        if(c!=null)
            c.setParent(beta);
        alpha.setLeftChild(d);
        if(d!=null)
            d.setParent(alpha);
        
        beta.setHeight(1+max(height(b),height(c)));
        alpha.setHeight(1+max(height(d),height(a)));
        gamma.setHeight(1+max(height(beta),height(alpha)));
        
        if(p!=null)
            p.hang(gamma);
        else{
            root=gamma;
            gamma.setParent(null);
        }
        return p;
    }
    
    private AVLNode rotateRightLeft(AVLNode n){
        AVLNode p=n.getParent();
        
        AVLNode alpha=n;
        AVLNode beta=n.getRightChild();
        AVLNode gamma=n.getRightChild().getLeftChild();
        
       
        
        AVLNode a=alpha.getLeftChild();
        AVLNode b=beta.getRightChild();
        AVLNode c=gamma.getRightChild();
        AVLNode d=gamma.getLeftChild();
        
        gamma.hang(alpha);
        gamma.hang(beta);
        beta.setLeftChild(c);
        if(c!=null)
            c.setParent(beta);
        alpha.setRightChild(d);
        if(d!=null)
            d.setParent(alpha);
        
        beta.setHeight(1+max(height(b),height(c)));
        alpha.setHeight(1+max(height(d),height(a)));
        gamma.setHeight(1+max(height(beta),height(alpha)));
        
        if(p!=null)
            p.hang(gamma);
        else{
            root=gamma;
            gamma.setParent(null);
        }
        return p;
    }
    
    
    
    public void remove(T e){
        AVLNode curr=root;
        while(curr!=null && curr.getValue().compareTo(e)!=0){
            if(curr.getValue().compareTo(e)>0)
                curr=curr.getLeftChild();
            else
                curr=curr.getRightChild();
        }
        if(curr==null)
            return;
        if(curr.getLeftChild()==null||curr.getRightChild()==null){
            if(curr.getLeftChild()!=null){
                if(curr.getParent()==null){
                    root=curr.getLeftChild();
                    curr.getLeftChild().setParent(null);
                    curr=root;
                }
                else{
                    curr.getParent().hang(curr.getLeftChild());
                    curr=curr.getParent();
                }
            }
            else if(curr.getRightChild()!=null){
                if(curr.getParent()==null){
                    root=curr.getRightChild();
                    curr.getRightChild().setParent(null);
                    curr=root;
                }
                else{
                    curr.getParent().hang(curr.getRightChild());
                    curr=curr.getParent();
                }
            }
            else{
                if(curr.getParent()==null)
                    root=null;
                else{
                    if(curr.getParent().getLeftChild()==curr)
                        curr.getParent().setLeftChild(null);
                    else
                        curr.getParent().setRightChild(null);
                    curr=curr.getParent();

                }
                
            }
        }
        else{
            AVLNode succ=getInorderSuccessor(curr);
            
            
            succ.hang(curr.getLeftChild());
            if(succ.getParent()!=curr){
                succ.getParent().setLeftChild(succ.getRightChild());
                succ.setRightChild(curr.getRightChild());
                curr.getRightChild().setParent(succ);
            }
            
            
            if(curr.getParent()!=null){
                curr.getParent().hang(succ);
            }
            else{
                succ.setParent(null);
                root=succ;
            }
            curr=succ;
        }
        
        if(root==null)
            return;
        
        while(curr!=null){
            curr.setHeight(1+max(height(curr.getLeftChild()),
                    height(curr.getRightChild())));
            if(curr.getBalanceFactor()>1){

                if(curr.getRightChild().getBalanceFactor()>=0){
                    curr=rotateRightRight(curr);
                }
                else if(curr.getRightChild().getBalanceFactor()==-1){
                    curr=rotateRightLeft(curr);
                }
            }
            else if(curr.getBalanceFactor()<-1){
                if(curr.getLeftChild().getBalanceFactor()>=0){
                    curr=rotateLeftRight(curr);
                }
                else if(curr.getLeftChild().getBalanceFactor()==-1){
                    curr=rotateLeftLeft(curr);
                }
            }
            else{
                curr=curr.getParent();
            }
        }
    }
    
    public void print(){
        Queue<AVLNode> queue=new Queue();
        queue.push(root);
        int tierElements=1;
        boolean finished=false;
        int count=0;
        if(root==null){
            System.out.println("");
            return;
        }
        int currHeight=root.getHeight()-1;
        while(!finished){
            count=0;
            for(int i=0;i<currHeight;i++){
                System.out.print("\t");
            }
            for(int i=0;i<tierElements;i++){
                if(queue.peek()==null){
                    count++;
                    queue.push(null);
                    queue.push(null);
                }
                else{
                    System.out.print(queue.peek().getValue().toString()+","+
                            queue.peek().getBalanceFactor());
                    queue.push(queue.peek().getLeftChild());
                    queue.push(queue.peek().getRightChild());
                }
                System.out.print("\t");
                queue.pop();
            }
            System.out.println("");
            finished=count==tierElements;
            currHeight--;
            tierElements<<=1;
        }
    }
    
    private AVLNode getInorderSuccessor(AVLNode n){
        if(n!=null){
            if(n.getRightChild()!=null){
                AVLNode curr=n.getRightChild();
                while(curr.getLeftChild()!=null){
                    curr=curr.getLeftChild();
                }
                return curr;
            }
            else if(n.getParent()!=null){
                while(n.getParent()!=null && n == n.getParent().getRightChild())
                        n=n.getParent();
                return n.getParent();
            }
        }
        return null;
    }
    
    public int getHeight(){
        if(root!=null)
            return root.getHeight();
        return 0;
    }
}
