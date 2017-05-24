package Mgr;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author sachin
 * Theory :
 * LRU cache >> least used will deleted first
 * A simple priority queue and hashmap for data saving
 * initialized with specific size for developement/testing its 10.
 * String based simple implementaion >> v 2.0 will be generics
 *
 * example;     l 123456 r fize is full
 * insert 6 >   return as no need
 * get 3    >   l 124563 r
 * insert 7 >   l 245637 r and aslo remove from cachemap
 *
 * */



public class ICacheHandler<k ,v>{
        static int currentSize=0;



    class Node<k,v>{   // priority linked list
        Node<k,v> previous;
        Node<k,v> next;
        k key;
        v value;



        public Node(Node<k,v> previous,Node<k,v> next,k key,v value){
            this.previous =previous;
            this.next=next;
            this.key=key;
            this.value=value;
        }
    }


        private HashMap<k,Node<k,v>> iCache;
        private Node<k,v> leastUsed=null;
        private Node<k,v> mostUsed =null;
        private Integer maxSize= 10;

        public ICacheHandler(int maxSize){
            this.maxSize =maxSize;
            leastUsed = new Node<k,v>(null,null,null,null);
            mostUsed =leastUsed;
            iCache =new HashMap<>();

        }


        public v get(k key){
            Node<k,v> tempNode =iCache.get(key);

            if (tempNode ==null){
                System.out.println("no value with this key. put first>");
                //System.out.println("putting...");
                return null;
            }

            if(tempNode.key ==leastUsed.key){
                leastUsed=leastUsed.next;
                leastUsed.previous=null;

            }else if (tempNode.key == mostUsed.key){
                return  tempNode.value;
            }else {//  7 8 9 and 8 is get
               tempNode.previous.next=tempNode.next;
               tempNode.next.previous=tempNode.previous;
            }

            tempNode.previous=mostUsed;
            mostUsed.next=tempNode;
            mostUsed =tempNode;
            mostUsed.next=null;

            return  tempNode.value;

        }


        public void put(k key,v value){

            if (iCache.containsKey(key)){
                System.out.println("entry alread present with this key and has value");
                // TODO: 24/5/17  get call no need;
                return;
            }

            //adding to the right
            Node<k,v> newNode =new Node(mostUsed,null,key,value);
            mostUsed.next =newNode;
            iCache.put(key,newNode);
            mostUsed =newNode;

            if(iCache.size() ==maxSize){  //size is 1 element ?
                //delete leat used entry
                leastUsed=leastUsed.next;
                leastUsed.previous=null;
                iCache.remove(leastUsed.key);
            }



        }


}
