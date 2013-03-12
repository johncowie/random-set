import org.apache.commons.lang.builder.EqualsBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class RandomSet<T> {

    private final HashSet<WrapperClass<T>> set = new HashSet<WrapperClass<T>>();
    private final String seed;

    public RandomSet(String seed) {
        this.seed = seed;
    }

    // Insert at random location
    public void insert(T val) {
        set.add(new WrapperClass<T>(val));
    }

    public T removeFirst() {
        WrapperClass<T> wt = set.iterator().next();
        set.remove(wt);
        return wt.getVal();
    }

    public boolean contains(T val) {
        return set.contains(new WrapperClass<T>(val));
    }

    @Override
    public String toString() {
        return set.toString();
    }

    private class WrapperClass<T> {
        private final T t;

        public WrapperClass(T t) {
            this.t = t;
        }

        public T getVal() {
            return this.t;
        }

        @Override
        public String toString() {
            return this.t.toString();
        }

        @Override
        public boolean equals(Object other) {
            return EqualsBuilder.reflectionEquals(this, other);
        }

        public int hashCode() {
            return new Random(seed.hashCode()+t.hashCode()).nextInt();
        }
    }

    public static void main(String[] args) {
        RandomSet<Integer> s = new RandomSet<Integer>("John");
        System.out.println(s.new WrapperClass<Integer>(10).equals(s.new WrapperClass<Integer>(10)));
        System.out.println(s.new WrapperClass<Integer>(10).hashCode() == s.new WrapperClass<Integer>(10).hashCode());
        for(int i = 0; i < 20; i++) {
            s.insert(i);
        }
        System.out.println(s);
        System.out.println(s.removeFirst());
        s.set.remove(s.new WrapperClass<Integer>(11));
        System.out.println(s);
    }

}
