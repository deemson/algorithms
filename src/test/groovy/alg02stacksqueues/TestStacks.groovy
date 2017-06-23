package alg02stacksqueues

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized)
class TestStacks {
    @Parameters(name = '{index}: {0}')
    static java.util.List<Class<Stack>> stacksClasses() {
        return [ArrayList, LinkedList]
    }

    @Parameter
    public Class<Stack> StackClass
    Stack stack

    @Before
    void setUp() {
        stack = StackClass.newInstance()
    }

    @Test
    void test() {
        assert stack.isEmpty()
        stack.push(3)
        stack.push(8)
        stack.push(12)
        assert !stack.isEmpty()
        assert [3, 8, 12] == stack.toArray()
        assert stack.pop() == 12
        assert stack.pop() == 8
        assert stack.pop() == 3
        assert stack.isEmpty()
    }

}