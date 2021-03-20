package tqsua.TqsStack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class TqsStackTest {
    private TqsStack<Double> stack;

    @BeforeEach
    public void setUp() {
        stack = new SimpleTqsStack<>();
    }

    @Test
    void emptyOnInit() {
        assertTrue(stack.isEmpty(), "The stack is not empty on creation");
    }

    @Test
    void sizeZeroOnInit() {
        assertEquals(0, stack.size(), "The stack size is bigger than 0 on creation");
    }

    @Test
    void  pushAssertions() {
        int n = 5;
        for (int i=0; i<n; i++) {
            stack.push(5.0);
        }
        assertAll(
                () -> assertFalse(stack.isEmpty(), "The stack is empty"),
                () -> assertEquals(n, stack.size(), "Stack's size is not " + n)
        );
    }

    @Test
    void pushAndPop() {
        double x = -1.0;
        stack.push(x);
        assertEquals(x, stack.pop(), "A pop after a push didn't return the pushed value");

    }

    @Test
    void pushAndPeek() {
        double x = -7.5;
        stack.push(x);
        int originalSize = stack.size();
        assertAll(
                () -> assertEquals(x, stack.peek(), "Return value doesn't match the pushed one"),
                () -> assertEquals(originalSize, stack.size(), "The ize changed")
        );
    }

    @Test
    void pushAndEmpty() {
        int n = 5;
        for (int i=0; i<n; i++) {
            stack.push(n*2.0);
        }
        // empty the stack (n pops)
        for (int i=0; i<n; i++) {
            stack.pop();
        }
        assertAll(
                () -> assertTrue(stack.isEmpty(), "The stack is not empty"),
                () -> assertEquals(0, stack.size(), "stack's size is not 0")
        );
    }

    @Test
    void popOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> stack.pop(), "Can't pop on empty stack");
    }

    @Test
    void peekOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> stack.peek(), "Can't peek on empty stack");
    }

    @Test
    void pushToBounded() {
        stack = new SimpleTqsStack<>(1);
        stack.push(5.0);
        assertThrows(IllegalStateException.class, () -> stack.push(5.0), "Can't push to a full bounded stack");
    }

}
