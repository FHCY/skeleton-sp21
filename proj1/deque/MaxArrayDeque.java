package deque;
import java.util.Comparator;
public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;
    }

    public T max(){
        if(size() == 0) {
            return null;
        }
        T max = get(0);
        for(int i = 1; i < size(); i++) {
            T current = get(i);
            if(comparator.compare(max, current) > 0) {
                max = current;
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        if(size() == 0){
            return null;
        }
        T max = get(0);
        for(int i = 1; i < size(); i++) {
            T current = get(i);
            if(c.compare(max, current) > 0) {
                max = current;
            }
        }
        return max;

    }
}
