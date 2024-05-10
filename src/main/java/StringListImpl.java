import java.util.NoSuchElementException;
import java.util.Objects;

public class StringListImpl implements StringList {

    private final static int DEFAULT_CAPACITY = 10;

    private final String[] data;
    private int size;

    public StringListImpl(int capacity) {
        this.data = new String[capacity];
        this.size = 0;
    }

    public StringListImpl() {
        this(DEFAULT_CAPACITY);
    }

    private void checkItem(String item) {
        if (item == null) {
            throw new IllegalArgumentException("item не должен быть null!");
        }
    }

    private void checkSize() {
        if (size == data.length) {
            throw new IllegalArgumentException("Список полон!");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Выход индекса за границы!");
        }
    }

    @Override
    public String add(String item) {
        checkItem(item);
        checkSize();
        return data[size++] = item;
    }

    @Override
    public String add(int index, String item) {
        if (index == size) {
            return add(item);
        }
        checkItem(item);
        checkIndex(index);
        checkSize();
        System.arraycopy(data, index, data, index + 1, size - index);
        size++;
        return data[index] = item;
    }

    @Override
    public String set(int index, String item) {
        checkItem(item);
        checkIndex(index);
        return data[index] = item;
    }

    @Override
    public String remove(String item) {
        checkItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public String remove(int index) {
        checkIndex(index);
        String removed = data[index];
        if (index < size - 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        data[--size] = null;
        return removed;
    }

    @Override
    public boolean contains(String item) {
        checkItem(item);
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        checkItem(item);
        for (int i = 0; i < size; i++) {
            if (Objects.equals(item, data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkItem(item);
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(item, data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (Objects.equals(get(i), otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public String[] toArray() {
        String[] newData = new String[size];
        System.arraycopy(data, 0, newData, 0, size);
        return newData;
    }

}
