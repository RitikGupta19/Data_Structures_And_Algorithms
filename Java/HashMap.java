// LeetCode Question: https://leetcode.com/problems/design-hashmap/

class HashNode {
    int key;
    int value;
    final int hashCode;
    HashNode next;

    HashNode(int key, int value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}

class MyHashMap {
    ArrayList<HashNode> bucketArr;
    int size;
    int capacity;

    public MyHashMap() {
        bucketArr = new ArrayList<>();
        capacity = 10;
        size = 0;
        for(int i = 0; i < capacity; i++) bucketArr.add(null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public final int hashCode(int key){
        return Objects.hashCode(key);
    }

    public int getBucketIndex(int key) {
        int hashCode = hashCode(key);
        int ind = hashCode % capacity;
        return ind < 0 ? (-1 * ind) : ind;
    }
    
    public void put(int key, int value) {
        int bucketInd = getBucketIndex(key);
        int hashCode = hashCode(key);
        HashNode head = bucketArr.get(bucketInd);
        
        // Existing element
        while(head != null){
            if(head.key == key && head.hashCode == hashCode){
                head.value = value;
                return;
            }
            head = head.next;
        }
        
        // New element
        size++;
        HashNode newNode = new HashNode(key, value, hashCode);
        head = bucketArr.get(bucketInd);
        newNode.next = head;
        bucketArr.set(bucketInd, newNode);

        // Increase capacity, if needed
        if((1.0 * size()) / capacity >= 0.7) {
            ArrayList<HashNode> temp = bucketArr;
            bucketArr = new ArrayList<>();
            size = 0;
            capacity = 2 * capacity;
            for(int i = 0; i < capacity; i++) bucketArr.add(null);
            for(HashNode node: temp) {
                while(node != null) {
                    put(node.key, node.value);
                    node = node.next;
                }
            }
        }
    }
    
    public int get(int key) {
        int bucketInd = getBucketIndex(key);
        int hashCode = hashCode(key);
        HashNode head = bucketArr.get(bucketInd);
        while(head != null){
            if(head.key == key && head.hashCode == hashCode) return head.value;
            head = head.next;
        }
        return -1;
    }
    
    public void remove(int key) {
        int bucketInd = getBucketIndex(key);
        int hashCode = hashCode(key);
        HashNode head = bucketArr.get(bucketInd);
        HashNode prev = null;

        while(head != null){
            if(head.key == key && head.hashCode == hashCode) break;
            prev = head;
            head = head.next;
        }

        if(head == null) return ;
        size--;

        if(prev == null) bucketArr.set(bucketInd, head.next);
        else prev.next = head.next;
        head.next = null;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */