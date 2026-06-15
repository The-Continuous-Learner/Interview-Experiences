package Y2026.Deloitte.Codes;

import java.util.HashMap;

public class LRUCache {
    public static void main(String[] args) {
        // Given array of strings, build lru cache with 5 positions, return format should be A-B-C-D-E
        String[] str = {"A","C","B","D","A","Z","X","A"};
        System.out.println(performCaching(str, 5));

    }

    public static String performCaching(String[] str, int cacheSize){
        Node startNode = new Node("");
        Node endNode = new Node("");
        startNode.next = endNode;
        endNode.prev = startNode;
        HashMap<String, Node> hm = new HashMap<>();

        for(String x: str){
            Node node = hm.get(x);
            if (node != null){
                removeNode(node, hm);
                addNode(x, endNode, hm);
            } else {
                if (hm.size() < cacheSize) {
                    addNode(x, endNode, hm);
                } else {
                    removeNode(startNode.next, hm);
                    addNode(x, endNode, hm);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        Node traveler = startNode.next;
        while (traveler != null) {
            sb.append(traveler.val+"-");
            traveler = traveler.next;
        }
        sb.setLength(sb.length()-2);
        return sb.toString();
    }

    public static void addNode(String val, Node backHead, HashMap<String, Node> hm){
        Node node = new Node(val);
        Node previous = backHead.prev;
        Node nextNode = backHead;
        previous.next = node;
        node.prev = previous;
        node.next = nextNode;
        nextNode.prev = node;
        hm.put(val, node);
    }

    public static void removeNode(Node node, HashMap<String, Node> hm){
        Node previous = node.prev;
        Node next = node.next;
        hm.remove(node.val);
        previous.next = next;
        next.prev = previous;
    }
}


class Node {
    String val;
    Node next;
    Node prev;

    public Node(String val){
        this.val = val;
    }

    public String toString(){
        return val+" ";
    }
}
