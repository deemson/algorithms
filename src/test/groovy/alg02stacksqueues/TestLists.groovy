package alg02stacksqueues

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized)
class TestLists {
    @Parameters(name = '{index}: {0}')
    static java.util.List<Class<Stack>> listsClasses() {
        return [ArrayList, LinkedList]
    }

    @Parameter
    public Class<List> ListClass
    List list

    @Before
    void setUp() {
        list = ListClass.newInstance()
    }

    @Test
    void test() {
        assert list.isEmpty()
        list.add(3)
        list.add(8)
        list.add(12)
        assert !list.isEmpty()
        assert [3, 8, 12] == list.toArray()
        assert list.get(2) == 12
        assert list.get(1) == 8
        assert list.get(0) == 3
    }

}