import java.util.*;
import java.io.*;

public class Dynamic_Programming {
    static List<Integer> fiboDP = new ArrayList<>();
        
    public static void main(String[] args) {
        // 1번항과 2번항 값 넣기
        fiboDP.add(0);
        fiboDP.add(1);

        fibonnaci(30);
        System.out.println(fiboDP.get(30));
    }

    static void fibonnaci(int idx) {
        for(int i = 2; i <= idx; i++) {
            int fiboFirst = fiboDP.get(i - 2);
            int fiboLast = fiboDP.get(i - 1);
            fiboDP.add(fiboFirst + fiboLast);
        }
    }
}
