package pro.sky.integerlist.constants;

import pro.sky.integerlist.interfaces.IntegerList;
import pro.sky.integerlist.services.ArrayIntegerList;

public class ArrayIntegerListConstants {
    public static int ZERO_SIZE_INTEGER_LIST = 0;

    public static int NEGATIVE_CAPACITY = -1;
    public static int USER_DEFINED_CAPACITY = 5;

    public static Integer NULL_ITEM = null;
    public static Integer FIRST_ITEM = 1;
    public static Integer SECOND_ITEM = 2;
    public static Integer THIRD_ITEM = 3;
    public static Integer FOURTH_ITEM = 4;

    public static int NEGATIVE_INDEX = -2;
    public static int ZERO_INDEX = 0;
    public static int FIRST_INDEX = 1;
    public static int SECOND_INDEX = 2;
    public static int THIRD_INDEX = 3;

    public static int RETURN_VALUE_IF_ITEM_NOT_FOUND = -1;

    public static Integer[] EMPTY_INTEGER_ARRAY = new Integer[]{};
    public static Integer[] ARRAY_OF_TWO_FIRST_NUMBER_ITEMS = new Integer[]{1, 2};

    public static IntegerList EMPTY_INTEGER_LIST_WITH_DEFAULT_CAPACITY = new ArrayIntegerList();
    public static IntegerList EMPTY_INTEGER_LIST_WITH_USER_DEFINED_CAPACITY =
            new ArrayIntegerList(USER_DEFINED_CAPACITY);

}
