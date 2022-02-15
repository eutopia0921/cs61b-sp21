package deque;


import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cp;

    public MaxArrayDeque(Comparator<T> c) {

        cp = c;
    }

    public T max() {
        if (size() == 0) {
            return null;
        }
        int maximum = 0;
        for (int i = 0; i < size(); i++) {
            int cmp = cp.compare(get(i), get(maximum));
            if (cmp > 0) {
                maximum = i;
            }
        }
        return get(maximum);
    }

    public T max(Comparator<T> c) {
        cp = c;
        return max();
    }


}
