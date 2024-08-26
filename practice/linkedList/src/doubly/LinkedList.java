package doubly;

class Node {
    String data;
    Node prev;
    Node next;
}

class DoublyLinkedList {
    Node head;
    Node tail;
    int size;

    DoublyLinkedList() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    // 1. 삽입
    void addData(int idx, String data) {
        // 1-1. 범위 체크
        if(idx < 0 || idx > size) {
            System.out.println("범위 밖!");
            return;
        }

        // 1-2. 삽입 할 위치 앞 노드 찾기
        Node curr = head;
        for(int i = 0; i < idx; i++) {
            curr = curr.next;
        }

        // 1-3. 새 노드 생성 후 연결
        Node newNode = new Node();
        newNode.data = data;

        newNode.prev = curr;
        newNode.next = curr.next;

        curr.next.prev = newNode;
        curr.next = newNode;

        size++;
    }


    // 2. 삭제
    void removeData(int idx) {
        // 2-1. 범위 체크
        if(idx < 0 || idx > size) {
            System.out.println("범위 밖!");
            return;
        }
        
        // 2-2. 삭제 할 위치 노드 찾기
        Node curr = head.next;
        for(int i = 0; i < idx; i++) {
            curr = curr.next;
        }

        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
    }


}

public class LinkedList {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
    }
}
