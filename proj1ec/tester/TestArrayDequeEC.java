package tester;

import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;


public class TestArrayDequeEC {
    @Test
    public void test_removeFirst() {
        ArrayDequeSolution<Integer> L1 = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> L2 = new StudentArrayDeque<>();
        int N = 5000;
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i <= N; i++) {
            int random = StdRandom.uniform(0, 3);
            int randVal = StdRandom.uniform(0, 100);
            if (random == 0) {
                // addFirst
                L1.addFirst(randVal);
                L2.addFirst(randVal);
                msg.append("addFirst(").append(randVal).append(")\n");
            } else if (random == 1) {
                // addLast
                L1.addLast(randVal);
                L2.addLast(randVal);
                msg.append("addLast(").append(randVal).append(")\n");
            }else if (L2.size() != 0 && random == 2) {
                if(randVal <50){
                    msg.append("removeFirst()\n");
                    assertEquals(msg.toString(), L1.removeFirst(), L2.removeFirst());
                } else {
                    msg.append("removeLast()\n");
                    assertEquals(msg.toString(), L1.removeLast(), L2.removeLast());
                }
            }
        }
    }
}