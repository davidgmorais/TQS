package tqsua.TqsStack;

public interface TqsStack<T> {
    public void push(T object);
    public T pop();
    public T peek();
    public int size();
    public boolean isEmpty();
}
