package org.example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test behaviour of a SimpleStack")
class SimpleStackTest<T> {

    @Test
    @DisplayName("Test the state of a newly created slack")
    public void testCreateEmptyStack() { // Test case

        // When a freshly stack is created
        Stack<T> stack = new SimpleStack<T>();

        // Then… (oracle)
        assertTrue(stack.isEmpty(), "A new stack must be empty");
        assertEquals( 0, stack.getSize(), "A new stack has no element");
    }

    @Test
    @DisplayName("Test the push of items")
    public void testPush() throws EmptyStackException {

        // Given an empty stack and an item
        Stack<T> stack = new SimpleStack<T>();
        T item = (T) new SimpleItem();

        // When the item is pushed in the stack
        stack.push(item);

        // Then…
        assertFalse(stack.isEmpty(), "The stack must not be empty");
        assertEquals(1, stack.getSize(),"The stack must constain 1 item");
        assertSame( item, stack.peek(),"The pushed item must be is on top of the stack");

        // Given a new item to add
        T item2 = (T) new SimpleItem();
        // When we add the new item
        stack.push(item2);

        // then...
        assertFalse(stack.isEmpty(), "The stack must be not empty");
        assertEquals(2, stack.getSize(),"The stack must constain 2 items");
        assertSame( item2, stack.peek(),"The pushed item must be on top of the stack");
    }

    @Test
    // @Disabled permet de desactiver le test
    @DisplayName("Test limit when trying to pop an empty stack")
    public void testPopOnEmptyStack()  {
        // Given an empty stack
        Stack<T> stack = new SimpleStack<T>();

        // When we "pop" the stack, should throws an EmptyStackException.
        //assertThrows(EmptyStackException.class, ()->stack.pop(), "EmptyStackException not thrown");
        assertThrows(EmptyStackException.class, stack::pop, "EmptyStackException not thrown");
    }

    @Test
    @DisplayName("Test pop an items")
    public void testPop() throws EmptyStackException {

        // Given stack with an item
        Stack<T> stack = new SimpleStack<T>();
        T item = (T) new SimpleItem();
        stack.push(item);
        // When an item is poped in the stack

        // Then…
        assertSame( item, stack.pop(),"The pushed item must be the same of on top of the stack");

        // When an item is poped in the stack of 2 item
        stack.push(item);
        stack.push(item);
        stack.pop();
        // Then the stack must contain 1 item
        assertEquals(1, stack.getSize(),"The stack must constain 1 item");
        // When the last item is poped
        stack.pop();
        // Then the stack must be empty
        assertTrue(stack.isEmpty(), "The stack must be empty");

    }
}
