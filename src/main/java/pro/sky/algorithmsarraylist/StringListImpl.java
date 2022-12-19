package pro.sky.algorithmsarraylist;

import pro.sky.algorithmsarraylist.exception.NonExistentValueException;
import pro.sky.algorithmsarraylist.exception.ValueCannotBeNullException;
import pro.sky.algorithmsarraylist.exception.WrongIndexException;

import java.util.Arrays;

public class StringListImpl implements StringList{

    private String[] data;
    private int size = 0;

    public StringListImpl() {
        this.data = new String[]{};
    }

    @Override
    public String add(String item) {
        return add(size, item);
    }

    @Override
    public String add(int index, String item) {
        checkItem(item);
        if (index < 0 || index > size) {
            throw new WrongIndexException("Wrong index");
        }
        if (size+1 > data.length) {
            grow();
        }
        System.arraycopy(this.data, index, this.data, index+1, size-index);
        this.data[index] = item;
        this.size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkItem(item);
        checkIndex(index);
        this.data[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        int elementIndex = indexOf(item);
        if (elementIndex == -1) {
            throw new NonExistentValueException("Failed to find the element to remove");
        }
        return remove(elementIndex);
    }
    //Это не очень эффективная техника, эффективно - это все в одном цикле аккуратно сделать,
    // но для данной реализации это не сильно требуется, поэтому такой реализации вполне достаточно
    //В ArrayList такая же плюс-минус реализация

    @Override
    public String remove(int index) {
        checkIndex(index);
        String elem = this.data[index];
        this.data[index] = null;
        //если элемент не последний
        if (index < size - 1) {
            System.arraycopy(this.data, index+1, this.data, index, size-index-1);
        }
        size--;
        return elem;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
//        for (int i = 0; i < this.data.length; i++) {
//            if (this.data[i] == null) {
//                break;
//            }
        checkItem(item);
        for (int i = 0; i < size; i++) {
            if (this.data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

//    @Override
//    public int lastIndexOf(String item) {
//        checkItem(item);
//        int lastIndex = -1;
//        for (int i = 0; i < size; i++) {
//            if (item.equals(this.data[i])) {
//                lastIndex = i;
//            }
//        }
//        return lastIndex;
//    }
//    //Вариант с переходом вперед и с бесконечной итерацией

    @Override
    public int lastIndexOf(String item) {
        checkItem(item);
        for (int i = size-1; i >= 0; i--) {
            if (item.equals(this.data[i])) {
                return i;
            }
        }
        return -1;
    }
    //Перебирание с конца - цикл сразу прерывается как только находим последний такой элемент списка

    @Override
    public String get(int index) {
        checkIndex(index);
        return this.data[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            return false;
        }
        StringListImpl otherListImpl = (StringListImpl) otherList;
//        return Arrays.equals(this.data, otherListImpl.data); //не подходит такое сравнение - нижестоящие массивы различаются, а размерности могут быть равны
        if (otherListImpl.size != this.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.data[i].equals(otherListImpl.data[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            this.data[i] = null;
        }
        this.size = 0;
    }

    @Override
    public String[] toArray() {
        String[] strings = new String[size];
        System.arraycopy(this.data, 0, strings, 0, size);
        return strings;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new WrongIndexException("Wrong index");
        }
    }

    private void checkItem(String item) {
        if (item == null) {
            throw new ValueCannotBeNullException("Value in List cannot be null");
        }
    }

    private void grow() {
        this.data = Arrays.copyOf(this.data, this.data.length+1);
    }
}
