package agh.ii.prinjava.proj1.impl;

public class DLinkList<E> {
    private Node<E> node = null;


    private static class Node<T> {
        T elem;
        Node<T> next;
        Node<T> prev;
        /** These are the nodes that we will use to navigate through the lists*/

        /**
         * We acces to the value of the node we're in
         * @param elem
         */
        public Node(T elem){
            this.elem=elem;
        }

        /**
         * We use this function to return the value of the element on the node
         * @return
         */
        public T getElem() {
            return elem;
        }
    }


    /**
     * The function adds the value in the input at the end of the list
     * @param e
     */
    public void addLast(E e){
        /** case in which the list is empty, we create a new node*/
        if (this.node==null){
            this.node= new Node<>(e);
        }
        else {
            Node<E> tmp = this.node;
            while(tmp.next!=null){ /** we serach for the last spot*/
                tmp = tmp.next;
            }
            tmp.next= new Node<>(e);
            tmp.next.prev = tmp;
            /** the node is linked to the rest of the list*/
        }
    }

    /**
     * The function adds the value in the input at the beginning of the list
     * @param e
     */
    public void addFirst(E e){
        /** case in which the list is empty, we create a new node*/
        if (this.node==null){
            this.node= new Node<>(e);
        }
        else{
            Node<E> tmp = this.node;
            tmp.prev = new Node<>(e);
            tmp.prev.next= tmp;
            this.node= tmp.prev;
        }
        /** the node is linked to the rest of the list*/
    }

    /**
     * Function to remove the first element of the list
     * @return first element
     */
    public E removeFirst(){
        /** case in which the list is empty */
        if (this.node==null){
            return null;
        }
        else{
            E tmp= node.getElem(); /** we implement the value in tmp to travel through the list*/
            this.node= this.node.next;
            if (this.node!= null){
                this.node.prev= null; /** the first node is detached from the list*/
            }
            return tmp;/**return the first element*/
        }
    }

    /**
     * Function to remove the last element of the list
     * @return last element
     */
    public E removeLast(){
        /** case in which the list is empty */
        if (this.node==null){
            return null;
        }
        else{
            Node<E> tmp = this.node; /** we implement the value tmp to travel through the list*/
            while(tmp.next!=null){
                tmp = tmp.next;
                /**we search for the end*/
            }
            E last= tmp.getElem();
            if (tmp.prev!= null){
                tmp.prev.next = null; /** the last node is detached from the list*/
            }
            else{
                this.node=null; /** set to null in case of a 1 element list*/
            }
            return last;/** return the last value*/
        }
    }

    /**
     * Function to display the list
     * @return
     */
    @Override
    public String toString() {
        /** case in which the list is empty */
        if (this.node==null){
            return "The list is empty!";
        }
        else {
            String str = "DLinkList = ";
            Node<E> tmp = this.node;
            while(tmp!=null){/** every loop until the list is finished, a new value is added to be displayed*/
                str += tmp.getElem().toString()  +";";
                tmp = tmp.next;
            }
            return str; /** return the message to dispaly*/
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


