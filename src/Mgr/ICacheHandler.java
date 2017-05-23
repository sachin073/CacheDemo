package Mgr;

import java.util.HashMap;

/**
 * @author sachin
 * Theory :
 * LRU cache >> least used will deleted first
 * A simple priority queue and hashmap for data saving
 * initialized with specific size for developement/testing its 10.
 * String based simple implementaion >> v 2.0 will be generics
 *
 * */



public class ICacheHandler<k ,v>{
        static int currentSize=0;



    class Node<k,vg>{
        Node<k,v> previous;
        Node<k,v> next;
        Integer key;
        String value;



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


        public Node<k,v> get(k key){
            if (!iCache.containsKey(key)){
                System.out.println("no value with this key. put first>");
                //System.out.println("putting...");
                return null;
            }

            return iCache.get(key);

        }


        public void put(k key,v value){

            if (iCache.containsKey(key)){
                System.out.println("entry alread present with this key and has value");
                // TODO: 24/5/17  get call;

                return;
            }

            //adding to the right
            Node<k,v> newNode =new Node(mostUsed,null,key,value);
            mostUsed.next =newNode;
            iCache.put(key,newNode);
            mostUsed =newNode;

            if(iCache.size() ==maxSize){
                //delete leat used entry
                leastUsed=leastUsed.next;
                leastUsed.previous=null;
                iCache.remove(leastUsed.key);
            }


        currentSize++;

        }


}
