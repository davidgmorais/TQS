package tqsua.TqsStack;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SimpleTqsStack<T> implements TqsStack<T> {

    private ArrayList<T> stack;
    private int max_size;

    public SimpleTqsStack() {
        this.stack = new ArrayList<>();
    }

    // Constructor for bounded stacks
    public SimpleTqsStack(int max_size) {
        this.stack = new ArrayList<>();
        this.max_size = max_size;
    }

    @Override
    public void push(T object) throws IllegalStateException {
        if (this.max_size != 0 && this.stack.size() >= this.max_size) {
            throw new IllegalStateException();
        }
        this.stack.add(object);
    }

    @Override
    public T pop() throws NoSuchElementException {
        if (this.stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.stack.remove(this.stack.size()-1);
    }

    @Override
    public T peek() throws NoSuchElementException {
        if (this.stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.stack.get(this.stack.size()-1);
    }

    @Override
    public int size() {
        return this.stack.size();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.size() == 0;
    }
}
