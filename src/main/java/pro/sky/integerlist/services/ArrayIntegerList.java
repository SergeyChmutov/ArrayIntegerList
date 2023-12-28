package pro.sky.integerlist.services;

import pro.sky.integerlist.exceptions.IntegerListIndexOutOfBoundsException;
import pro.sky.integerlist.exceptions.IntegerListItemNotFoundException;
import pro.sky.integerlist.exceptions.IntegerListNullArgumentValueException;
import pro.sky.integerlist.exceptions.IntegerListStorageInitializationException;
import pro.sky.integerlist.interfaces.IntegerList;

public class ArrayIntegerList implements IntegerList {
    private static final int DEFAULT_STORAGE_CAPACITY = 10;
    private Integer[] storage;
    private int size;

    public ArrayIntegerList() {
        this.storage = new Integer[DEFAULT_STORAGE_CAPACITY];
        this.size = 0;
    }

    public ArrayIntegerList(int capacity) {
        if (capacity < 0) {
            throw new IntegerListStorageInitializationException(
                    "Первоначальный размер для хранения значений списка не может быть задан отрицательным числом"
            );
        }
        this.storage = new Integer[capacity];
        this.size = 0;
    }

    @Override
    public Integer add(Integer item) {
        checkParameterForNull(item);
        rearrangeStorageIfNeeded();
        storage[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkIndexValidRangeForAddItem(index);
        checkParameterForNull(item);
        rearrangeStorageIfNeeded();
        if (index < size) {
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = item;
            size++;
        } else {
            storage[size++] = item;
        }
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkIndexValidRange(index);
        checkParameterForNull(item);
        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkParameterForNull(item);
        int indexOfItem = indexOf(item);
        if (indexOfItem == -1) {
            throw new IntegerListItemNotFoundException("Не найден указанный элемент");
        }
        removeItemByIndexFromStorage(indexOfItem);
        return item;
    }

    @Override
    public Integer remove(int index) {
        checkIndexValidRange(index);
        Integer item = storage[index];
        removeItemByIndexFromStorage(index);
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        checkParameterForNull(item);
        Integer[] items = toArray();
        sortInsertion(items);
        return binarySearchIteratively(items, item,0, items.length - 1) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        checkParameterForNull(item);
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkParameterForNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndexValidRange(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new IntegerListNullArgumentValueException("Для сравнения передано null-значение");
        }
        if (this == otherList) {
            return true;
        }
        if (size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!storage[i].equals(otherList.get(i))) {
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
            storage[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] result = new Integer[size];
        System.arraycopy(storage, 0, result, 0, size);
        return result;
    }

    private void checkParameterForNull(Integer item) {
        if (item == null) {
            throw new IntegerListNullArgumentValueException("Обрабатываемое число не может иметь значение null");
        }
    }

    private void rearrangeStorageIfNeeded() {
        if (storage.length == size) {
            rearrangeStorage();
        }
    }

    private void rearrangeStorage() {
        int newStorageLength = (size * 3) / 2 + 1;
        Integer[] newStorage = new Integer[newStorageLength];
        System.arraycopy(storage, 0, newStorage, 0, size);
        this.storage = newStorage;
    }

    private void checkIndexValidRangeForAddItem(int index) {
        if (index < 0 || index > size) {
            throw new IntegerListIndexOutOfBoundsException(getOutOfBoundMessage(index, size));
        }
    }

    private void checkIndexValidRange(int index) {
        if (isEmpty() || index < 0 || index > size - 1) {
            throw new IntegerListIndexOutOfBoundsException(getOutOfBoundMessage(index, size));
        }
    }

    private String getOutOfBoundMessage(int index, int size) {
        return "Индекс: " + index + ", размер списка " + size;
    }

    private void removeItemByIndexFromStorage(int index) {
        size--;
        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
        storage[size] = null;
    }

    private void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Integer temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private int binarySearchIteratively(Integer[] sortedArray, int item, int leftIndex, int rightIndex) {
        int index = -1;

        while (leftIndex <= rightIndex) {
            int mid = leftIndex  + ((rightIndex - leftIndex) / 2);
            if (sortedArray[mid] < item) {
                leftIndex = mid + 1;
            } else if (sortedArray[mid] > item) {
                rightIndex = mid - 1;
            } else if (sortedArray[mid] == item) {
                index = mid;
                break;
            }
        }

        return index;
    }
}
