package pro.sky.algorithmsarraylist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.algorithmsarraylist.exception.NonExistentValueException;
import pro.sky.algorithmsarraylist.exception.ValueCannotBeNullException;
import pro.sky.algorithmsarraylist.exception.WrongIndexException;

public class StringListTests {

    private StringList stringList;

    @BeforeEach
    public void setUp() {
        this.stringList = new StringListImpl();
    }

    @Test
    public void whenItemAddedThenItCanBeFoundItList() {
        this.stringList.add("test");
        Assertions.assertEquals(1, this.stringList.size());
    }

    @Test
    public void whenItemAddedToSpecificIndexWhenElementsIsShiftedRight() {
        this.stringList.add("test");
        this.stringList.add(0, "test1");
        this.stringList.add(1, "test2");
        this.stringList.add(3, "test10");
        Assertions.assertEquals(4, this.stringList.size());
        Assertions.assertEquals("test1", this.stringList.get(0));
        Assertions.assertEquals("test2", this.stringList.get(1));
        Assertions.assertEquals("test", this.stringList.get(2));
        Assertions.assertEquals("test10", this.stringList.get(3));
    }

    @Test
    public void whenValueIsSetWhenGetReturnsThisValue() {
        this.stringList.add("test");
        this.stringList.add("test1");
        this.stringList.add("test2");

        this.stringList.set(1, "NEW_VALUE");
        Assertions.assertEquals(3, this.stringList.size());
        Assertions.assertEquals("NEW_VALUE", this.stringList.get(1));
    }

    @Test
    public void whenTwoElementsAddedInListThenIndexOfReturnsFirst() {
        this.stringList.add("test");
        this.stringList.add("test");
        Assertions.assertEquals(0, this.stringList.indexOf("test"));
    }

    @Test
    public void whenTwoElementsAddedInListThenIndexOfUnknownReturnsMinusOne() {
        this.stringList.add("test");
        this.stringList.add("test");
        Assertions.assertEquals(-1, this.stringList.indexOf("NON_EXISTING_VALUE"));
    }

    @Test
    public void whenTwoElementsAddedInListThenLastIndexOfReturnsSecond() {
        this.stringList.add("test");
        this.stringList.add("test");
        Assertions.assertEquals(1, this.stringList.lastIndexOf("test"));
    }

    @Test
    public void whenTwoElementsAddedInListThenLastIndexOfUnknownReturnsMinusOne() {
        this.stringList.add("test");
        this.stringList.add("test");
        Assertions.assertEquals(-1, this.stringList.lastIndexOf("NON_EXISTING_VALUE"));
    }

    @Test
    public void whenTwoElementsAddedAndSecondRemovedTheSizeIsOne() {
        this.stringList.add("test");
        this.stringList.add("test1");
        this.stringList.remove(1);
        Assertions.assertEquals(1, this.stringList.size());
        Assertions.assertEquals("test", this.stringList.get(0));
    }

    @Test
    public void whenTwoElementsAddedAndFirstRemovedTheSizeIsOne() {
        this.stringList.add("test");
        this.stringList.add("test1");
        this.stringList.remove(0);
        Assertions.assertEquals(1, this.stringList.size());
        Assertions.assertEquals("test1", this.stringList.get(0));
    }

    @Test
    public void whenThreeElementsAddedAndSecondRemovedTheSizeIsTwo() {
        this.stringList.add("test");
        this.stringList.add("test1");
        this.stringList.add("test2");
        this.stringList.remove(1);
        Assertions.assertEquals(2, this.stringList.size());
        Assertions.assertEquals("test", this.stringList.get(0));
        Assertions.assertEquals("test2", this.stringList.get(1));
    }

    @Test
    public void whenElementsAddedAndClearIsCalledThenListIsEmpty() {
        this.stringList.add("test");
        this.stringList.add("test1");
        this.stringList.add("test2");
        this.stringList.clear();
        Assertions.assertTrue(this.stringList.isEmpty());
        Assertions.assertEquals(0, this.stringList.size());
    }

    //
    @Test
    public void whenThreeElementsAddedAndSecondRemovedByItemTheSizeIsTwo() {
        this.stringList.add("test");
        this.stringList.add("test1");
        this.stringList.add("test2");
        this.stringList.remove("test1");
        Assertions.assertEquals(2, this.stringList.size());
        Assertions.assertEquals("test", this.stringList.get(0));
        Assertions.assertEquals("test2", this.stringList.get(1));
    }

    @Test
    public void whenElementsAddedAndContainsThenListContains() {
        this.stringList.add("test");
        this.stringList.add("test1");
        Assertions.assertTrue(this.stringList.contains("test1"));
    }

    @Test
    public void whenArrayListToArrayThenArrayListCopiedToArrayAndReturns() {
        this.stringList.add("test");
        this.stringList.add("test1");
        String[] strings = this.stringList.toArray();
        Assertions.assertEquals(2, strings.length);
        Assertions.assertEquals("test", strings[0]);
        Assertions.assertEquals("test1", strings[1]);
    }

    @Test
    public void whenOtherListEquals() {
        this.stringList.add("test");
        this.stringList.add("test1");
        StringList otherListEquals =  new StringListImpl();
        otherListEquals.add("test");
        otherListEquals.add("test1");
        StringList otherListNotEqualElement =  new StringListImpl();
        otherListNotEqualElement.add("test");
        otherListNotEqualElement.add("test3");
        StringList ListNotEqualSize =  new StringListImpl();
        ListNotEqualSize.add("test");
        ListNotEqualSize.add("test1");
        ListNotEqualSize.add("test2");
        Assertions.assertFalse(this.stringList.equals(null));
        Assertions.assertTrue(this.stringList.equals(otherListEquals));
        Assertions.assertFalse(this.stringList.equals(otherListNotEqualElement));
        Assertions.assertFalse(this.stringList.equals(ListNotEqualSize));
    }

    @Test
    public void whenInvalidArgumentThenException() {
        this.stringList.add("test");
        this.stringList.add("test1");
        this.stringList.add("test2");
        Assertions.assertThrows(WrongIndexException.class, () -> this.stringList.add(-1, "OUT_OF_SIZE"));
        Assertions.assertThrows(WrongIndexException.class, () -> this.stringList.add(4, "OUT_OF_SIZE"));
        Assertions.assertThrows(NonExistentValueException.class, () -> this.stringList.remove("NON_EXISTING_VALUE"));
        //проверка checkIndex(index)
        Assertions.assertThrows(WrongIndexException.class, () -> this.stringList.get(3));
        //проверка checkItem(item)
        Assertions.assertThrows(ValueCannotBeNullException.class, () -> this.stringList.indexOf(null));
    }
}
