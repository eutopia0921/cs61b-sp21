package deque;

import java.util.Comparator;


public class MaxArrayDequeTest {
    public static class itemComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }


    public static void main(String[] args) {
        MaxArrayDeque<Integer> l = new MaxArrayDeque(new MaxArrayDequeTest.itemComparator());
        l.addLast(10);
        l.addFirst(100);
        for (int i = 0; i < 10; i++) {
            l.addFirst(i);
        }
        l.printDeque();
        System.out.println(l.max());

    }

}
