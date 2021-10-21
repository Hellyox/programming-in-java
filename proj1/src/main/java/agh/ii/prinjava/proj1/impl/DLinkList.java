package agh.ii.prinjava.proj1.impl;

public class DLinkList<E> {
    private Node<E> node = null;


    private static class Node<T> {
        T elem;
        Node<T> next;
        Node<T> prev;

        public Node(T elem){
            this.elem=elem;
        }

        public T getElem() {
            return elem;
        }
    }


    public void addLast(E e){
        if (this.node==null){
            this.node= new Node<>(e);
        }
        else {
            Node<E> tmp = this.node;
            while(tmp.next!=null){
                tmp = tmp.next;
            }
            tmp.next= new Node<>(e);
            tmp.next.prev = tmp;
        }
    }

    public void addFirst(E e){
        if (this.node==null){
            this.node= new Node<>(e);
        }
        else{
            Node<E> tmp = this.node;
            tmp.prev = new Node<>(e);
            tmp.prev.next= tmp;
            this.node= tmp.prev;
        }
    }

    public E removeFirst(){
        if (this.node==null){
            return null;
        }
        else{
            E tmp= node.getElem();
            this.node= this.node.next;
            if (this.node!= null){
                this.node.prev= null;
            }
            return tmp;
        }
    }

    public E removeLast(){
        if (this.node==null){
            return null;
        }
        else{
            Node<E> tmp = this.node;
            while(tmp.next!=null){
                tmp = tmp.next;
            }
            E last= tmp.getElem();
            if (tmp.prev!= null){
                tmp.prev.next = null;
            }
            else{
                this.node=null;
            }
            return last;
        }
    }

    @Override
    public String toString() {
        if (this.node==null){
            return "The list is empty!";
        }
        else {
            String str = "DLinkList = ";
            Node<E> tmp = this.node;
            while(tmp!=null){
                str += tmp.getElem().toString()  +";";
                tmp = tmp.next;
            }
            return str;
        }
    }

    public int Count() {
        int ct = 0;
        if (this.node == null) {
            return ct;
        }
        else {
            Node<E> tmp = this.node;
            while(tmp!=null){
                ct += 1;
                tmp = tmp.next;
            }
            System.out.println(ct);
            return ct;
        }
    }
}


