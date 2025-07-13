// LRU Cache Implementation in Java using Doubly Linked List and HashMap
class Node {
    Node prev, next;
    int key, value;
    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    HashMap<Integer, Node> map = new HashMap<>();
    Node head = new Node(0, 0);
    Node tail = new Node(0, 0);
    int capacity;
    
    public LRUCache(int capacity) {
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node);
        insert(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) remove(map.get(key));
        if(map.size() == capacity) remove(tail.prev);
        insert(new Node(key, value));
    }

    public void remove(Node node) {
        map.remove(node.key);
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    public void insert(Node node) {
        map.put(node.key, node);
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */