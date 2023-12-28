package pro.sky.integerlist.services;

import org.junit.jupiter.api.Test;
import pro.sky.integerlist.exceptions.IntegerListIndexOutOfBoundsException;
import pro.sky.integerlist.exceptions.IntegerListItemNotFoundException;
import pro.sky.integerlist.exceptions.IntegerListNullArgumentValueException;
import pro.sky.integerlist.exceptions.IntegerListStorageInitializationException;
import pro.sky.integerlist.interfaces.IntegerList;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.integerlist.constants.ArrayIntegerListConstants.*;

public class ArrayIntegerListTest {

    private final IntegerList out = new ArrayIntegerList();

    // constructor

    @Test
    public void shouldThrowIntegerListStorageInitializationExceptionWhenCapacityIsNegative() {
        assertThrows(IntegerListStorageInitializationException.class, () -> new ArrayIntegerList(NEGATIVE_CAPACITY));
    }

    // add method

    @Test
    public void shouldThrowIntegerListNullArgumentValueExceptionWhenAddMethodCallWithNullItem() {
        assertThrows(IntegerListNullArgumentValueException.class, () -> out.add(NULL_ITEM));
    }

    @Test
    public void shouldThrowIntegerListIndexOutOfBoundsExceptionWhenAddMethodCallWithIndexNotInListRange() {
        assertThrows(IntegerListIndexOutOfBoundsException.class, () -> out.add(NEGATIVE_INDEX, FIRST_ITEM));
        assertThrows(IntegerListIndexOutOfBoundsException.class, () -> out.add(FIRST_INDEX, FIRST_ITEM));
    }

    @Test
    public void shouldReturnItemWhenAddMethodCallWithValidIndexAndItem() {
        assertEquals(out.add(SECOND_ITEM), SECOND_ITEM);
        assertEquals(out.add(ZERO_INDEX, FIRST_ITEM), FIRST_ITEM);

        assertArrayEquals(out.toArray(), ARRAY_OF_TWO_FIRST_NUMBER_ITEMS);
    }

    // set method

    @Test
    public void shouldThrowIntegerListNullArgumentValueExceptionWhenSetMethodCallWithNullItem() {
        out.add(FIRST_ITEM);
        assertThrows(IntegerListNullArgumentValueException.class, () -> out.set(ZERO_INDEX, NULL_ITEM));
    }

    @Test
    public void shouldThrowIntegerListIndexOutOfBoundsExceptionWhenSetMethodCallWithIndexNotInListRange() {
        out.add(FIRST_ITEM);
        assertThrows(IntegerListIndexOutOfBoundsException.class, () -> out.set(FIRST_INDEX, SECOND_ITEM));
        assertThrows(IntegerListIndexOutOfBoundsException.class, () -> out.set(NEGATIVE_INDEX, SECOND_ITEM));
    }

    @Test
    public void shouldReturnItemWhenSetMethodCallWithValidIndexAndItem() {
        out.add(SECOND_ITEM);
        out.add(SECOND_ITEM);
        assertEquals(out.set(ZERO_INDEX, FIRST_ITEM), FIRST_ITEM);

        assertArrayEquals(out.toArray(), ARRAY_OF_TWO_FIRST_NUMBER_ITEMS);
    }

    // remove method

    @Test
    public void shouldThrowIntegerListNullArgumentValueExceptionWhenRemoveMethodCallWithNullItem() {
        assertThrows(IntegerListNullArgumentValueException.class, () -> out.remove(NULL_ITEM));
    }

    @Test
    public void shouldThrowIntegerListItemNotFoundExceptionWhenRemoveMethodCallWithEmptyList() {
        assertThrows(IntegerListItemNotFoundException.class, () -> out.remove(FIRST_ITEM));
    }

    @Test
    public void shouldThrowIntegerListItemNotFoundExceptionWhenRemoveMethodCallWithListNotContainsItem() {
        out.add(SECOND_ITEM);
        assertThrows(IntegerListItemNotFoundException.class, () -> out.remove(FIRST_ITEM));
    }

    @Test
    public void shouldReturnItemWhenRemoveMethodCallWithListContainsItem() {
        out.add(SECOND_ITEM);
        out.add(THIRD_ITEM);
        assertEquals(out.remove(THIRD_ITEM), THIRD_ITEM);
        assertEquals(out.remove(SECOND_ITEM), SECOND_ITEM);

        assertArrayEquals(out.toArray(), EMPTY_INTEGER_ARRAY);
    }

    @Test
    public void shouldThrowIntegerListIndexOutOfBoundsExceptionWhenRemoveMethodCallWithIndexNotInListRange() {
        assertThrows(IntegerListIndexOutOfBoundsException.class, () -> out.remove(ZERO_INDEX));
        out.add(FIRST_ITEM);
        assertThrows(IntegerListIndexOutOfBoundsException.class, () -> out.remove(FIRST_INDEX));
    }

    @Test
    public void shouldReturnItemWhenRemoveMethodCallWithValidIndex() {
        out.add(FIRST_ITEM);
        out.add(SECOND_ITEM);
        out.add(THIRD_ITEM);

        assertEquals(out.remove(SECOND_INDEX), THIRD_ITEM);
        assertEquals(out.remove(FIRST_INDEX), SECOND_ITEM);
        assertEquals(out.remove(ZERO_INDEX), FIRST_ITEM);

        assertArrayEquals(out.toArray(), EMPTY_INTEGER_ARRAY);
    }

    // contains method

    @Test
    public void shouldThrowIntegerListNullArgumentValueExceptionWhenContainsMethodCallWithNullItem() {
        assertThrows(IntegerListNullArgumentValueException.class, () -> out.contains(NULL_ITEM));
    }

    @Test
    public void shouldReturnFalseWhenContainsMethodCallWithEmptyList() {
        assertFalse(out.contains(FIRST_ITEM));
    }

    @Test
    public void shouldReturnFalseWhenContainsMethodCallWithListNotContainsItem() {
        out.add(SECOND_ITEM);
        assertFalse(out.contains(FIRST_ITEM));
    }

    @Test
    public void shouldReturnTrueWhenContainsMethodCallWithListContainsItem() {
        out.add(FIRST_ITEM);
        assertTrue(out.contains(FIRST_ITEM));
    }

    // indexOf method

    @Test
    public void shouldThrowIntegerListNullArgumentValueExceptionWhenIndexOfMethodCallWithNullItem() {
        assertThrows(IntegerListNullArgumentValueException.class, () -> out.indexOf(NULL_ITEM));
    }

    @Test
    public void shouldReturnValueIfItemNotFoundWhenIndexOfMethodCallWithEmptyList() {
        assertEquals(out.indexOf(FIRST_ITEM), RETURN_VALUE_IF_ITEM_NOT_FOUND);
    }

    @Test
    public void shouldReturnValueIfItemNotFoundWhenIndexOfMethodCallWithListNotContainsItem() {
        out.add(SECOND_ITEM);
        assertEquals(out.indexOf(FIRST_ITEM), RETURN_VALUE_IF_ITEM_NOT_FOUND);
    }

    @Test
    public void shouldReturnFirstIndexOfItemWhenIndexOfMethodCallWithListContainsFewFoundItems() {
        out.add(FIRST_ITEM);
        out.add(SECOND_ITEM);
        out.add(THIRD_ITEM);
        out.add(SECOND_ITEM);
        assertEquals(out.indexOf(SECOND_ITEM), FIRST_INDEX);
    }

    // lastIndexOf method

    @Test
    public void shouldThrowIntegerListNullArgumentValueExceptionWhenLastIndexOfMethodCallWithNullItem() {
        assertThrows(IntegerListNullArgumentValueException.class, () -> out.lastIndexOf(NULL_ITEM));
    }

    @Test
    public void shouldReturnValueIfItemNotFoundWhenLastIndexOfMethodCallWithEmptyList() {
        assertEquals(out.lastIndexOf(FIRST_ITEM), RETURN_VALUE_IF_ITEM_NOT_FOUND);
    }

    @Test
    public void shouldReturnValueIfItemNotFoundWhenLastIndexOfMethodCallWithListNotContainsItem() {
        out.add(SECOND_ITEM);
        assertEquals(out.lastIndexOf(FIRST_ITEM), RETURN_VALUE_IF_ITEM_NOT_FOUND);
    }

    @Test
    public void shouldReturnFirstIndexOfItemWhenLastIndexOfMethodCallWithListContainsFewFoundItems() {
        out.add(THIRD_ITEM);
        out.add(SECOND_ITEM);
        out.add(THIRD_ITEM);
        out.add(FOURTH_ITEM);
        assertEquals(out.lastIndexOf(THIRD_ITEM), SECOND_INDEX);
    }

    // get method

    @Test
    public void shouldThrowIntegerListIndexOutOfBoundsExceptionWhenGetMethodCallWithEmptyList() {
        assertThrows(IntegerListIndexOutOfBoundsException.class, () -> out.get(ZERO_INDEX));
    }

    @Test
    public void shouldThrowIntegerListIndexOutOfBoundsExceptionWhenGetMethodCallWithIndexNotInListRange() {
        out.add(FIRST_ITEM);
        assertThrows(IntegerListIndexOutOfBoundsException.class, () -> out.get(FIRST_INDEX));
        assertThrows(IntegerListIndexOutOfBoundsException.class, () -> out.get(NEGATIVE_INDEX));
    }

    @Test
    public void shouldReturnItemWhenGetMethodCallWithValidIndex() {
        out.add(FIRST_ITEM);
        out.add(SECOND_ITEM);
        assertEquals(out.get(ZERO_INDEX), FIRST_ITEM);
        assertEquals(out.get(FIRST_INDEX), SECOND_ITEM);
    }

    // equals method

    @Test
    public void shouldThrowIntegerListNullArgumentValueExceptionWhenEqualsMethodCallWithNullStringList() {
        assertThrows(IntegerListNullArgumentValueException.class, () -> out.equals(null));
    }

    @Test
    public void shouldReturnTrueWhenEqualsMethodCallWithSelfStringList() {
        assertTrue(out.equals(out));
    }

    @Test
    public void shouldReturnTrueWhenEqualsMethodCallWithTwoEmptyLists() {
        assertTrue(out.equals(EMPTY_INTEGER_LIST_WITH_DEFAULT_CAPACITY));
        assertTrue(out.equals(EMPTY_INTEGER_LIST_WITH_USER_DEFINED_CAPACITY));
        assertTrue(EMPTY_INTEGER_LIST_WITH_DEFAULT_CAPACITY.equals(EMPTY_INTEGER_LIST_WITH_USER_DEFINED_CAPACITY));
    }

    @Test
    public void shouldReturnFalseWhenEqualsMethodCallWithTwoStringListWithDifferentNumberOfElements() {
        out.add(FIRST_ITEM);

        IntegerList anotherIntegerList = new ArrayIntegerList();

        anotherIntegerList.add(FIRST_ITEM);
        anotherIntegerList.add(FOURTH_ITEM);

        assertFalse(out.equals(anotherIntegerList));
    }

    @Test
    public void shouldReturnFalseWhenEqualsMethodCallWithTwoStringListWithDifferentOrderOfElements() {
        out.add(FIRST_ITEM);
        out.add(SECOND_ITEM);

        IntegerList anotherIntegerList = new ArrayIntegerList();

        anotherIntegerList.add(SECOND_ITEM);
        anotherIntegerList.add(FIRST_ITEM);

        assertFalse(out.equals(anotherIntegerList));
    }

    @Test
    public void shouldReturnTrueWhenEqualsMethodCallWithTwoStringListWithIdenticalOrderAndElements() {
        out.add(FIRST_ITEM);
        out.add(SECOND_ITEM);

        IntegerList anotherIntegerList = new ArrayIntegerList();

        anotherIntegerList.add(FIRST_ITEM);
        anotherIntegerList.add(SECOND_ITEM);

        assertTrue(out.equals(anotherIntegerList));
        assertTrue(anotherIntegerList.equals(out));
    }

    // size method

    @Test
    public void shouldReturnZeroSizeWhenSizeMethodCallWithEmptyList() {
        assertEquals(out.size(), ZERO_SIZE_INTEGER_LIST);
        assertEquals(EMPTY_INTEGER_LIST_WITH_DEFAULT_CAPACITY.size(), ZERO_SIZE_INTEGER_LIST);
        assertEquals(EMPTY_INTEGER_LIST_WITH_USER_DEFINED_CAPACITY.size(), ZERO_SIZE_INTEGER_LIST);
    }

    @Test
    public void shouldReturnActualSizeWhenSizeMethodCall() {
        out.add(FIRST_ITEM);
        assertEquals(out.size(), 1);

        out.add(SECOND_ITEM);
        assertEquals(out.size(), 2);

        out.add(THIRD_ITEM);
        assertEquals(out.size(), 3);

        out.remove(FIRST_ITEM);
        assertEquals(out.size(), 2);

        out.remove(ZERO_INDEX);
        assertEquals(out.size(), 1);
    }

    // isEmpty method

    @Test
    public void shouldReturnTrueWhenIsEmptyMethodCallWithEmptyList() {
        assertTrue(out.isEmpty());
        assertTrue(EMPTY_INTEGER_LIST_WITH_DEFAULT_CAPACITY.isEmpty());
        assertTrue(EMPTY_INTEGER_LIST_WITH_USER_DEFINED_CAPACITY.isEmpty());
    }

    @Test
    public void shouldReturnFalseWhenIsEmptyMethodCallWithNotEmptyList() {
        out.add(FIRST_ITEM);
        out.set(ZERO_INDEX, FOURTH_ITEM);

        assertFalse(out.isEmpty());
    }

    // clear method

    @Test
    public void shouldReturnZeroSizeWhenClearMethodCall() {
        out.add(FIRST_ITEM);
        out.add(SECOND_ITEM);
        out.add(THIRD_ITEM);
        out.add(FOURTH_ITEM);

        out.clear();

        assertEquals(out.size(), ZERO_SIZE_INTEGER_LIST);
        assertTrue(out.equals(EMPTY_INTEGER_LIST_WITH_DEFAULT_CAPACITY));

        assertArrayEquals(out.toArray(), EMPTY_INTEGER_ARRAY);
        assertEquals(out.toArray().length, ZERO_SIZE_INTEGER_LIST);
    }

    // toArray method

    @Test
    public void shouldReturnEmptyArrayWhenToArrayMethodCallWithEmptyList() {
        assertArrayEquals(out.toArray(), EMPTY_INTEGER_ARRAY);
    }

    @Test
    public void shouldReturnIntegerArrayWhenToArrayMethodCall() {
        out.add(FIRST_ITEM);
        out.add(SECOND_ITEM);
        assertArrayEquals(out.toArray(), ARRAY_OF_TWO_FIRST_NUMBER_ITEMS);
    }
}
