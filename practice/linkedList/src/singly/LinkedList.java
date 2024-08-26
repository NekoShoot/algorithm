package singly;

class Node {
    String data;
    Node link;

    // 기본 생성자
    Node() {}
}

class SinglyLinkedList {
    // head, size 생성
    Node head;
    int size;

    SinglyLinkedList() {
        head = new Node();
    }

    // 1. 원소 삽입
    void addData(int idx, String data) {
        // 1-1. 범위 체크
        if(idx < 0 || idx > size) {
            System.out.println("범위 밖!");
            return;
        }
        // 1-2. 새 노드 생성 및 데이터 input
        Node newNode = new Node();
        newNode.data = data;

        // 1-3. 삽입할 위치 앞에 있는 노드 찾기(link에 연결해야 하기 때문에)
        Node curr = head;
        for(int i = 0; i < idx; i ++) {
            curr = curr.link;
        }

        // 1-4. 새 노드부터 연결
        newNode.link = curr.link; // 중간에 삽입되는 경우 생각해보면 쉽다!
        curr.link = newNode;

        // size 늘리기
        size++;
    }
    // 2. 원소 삭제
    void removeData(int idx) {
        if(idx < 0 || idx > size) {
            System.out.println("범위 밖!");
            return;
        }

        Node curr = head;
        for(int i = 0; i < idx; i++) {
            curr = curr.link;
        }

        // 삭제할 노드와 연결된 링크 끊고 삭제할 노드의 다음 노드와 연결해주기
        curr.link = curr.link.link;

        size--;
    }
    // 3. 순회 출력
    void printAll() {
        Node curr = head.link;
        while(curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.link;
        }
        System.out.println();
    }

}

public class LinkedList {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
    }
}
