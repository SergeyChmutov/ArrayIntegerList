package pro.sky.integerlist;

import pro.sky.integerlist.exceptions.IntegerListIndexOutOfBoundsException;
import pro.sky.integerlist.exceptions.IntegerListNullArgumentValueException;
import pro.sky.integerlist.interfaces.IntegerList;
import pro.sky.integerlist.services.ArrayIntegerList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        IntegerList numbers = new ArrayIntegerList();

        // add
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println(Arrays.toString(numbers.toArray()));

        // remove
        numbers.remove((Integer) 5);
        numbers.remove(0);

        System.out.println(Arrays.toString(numbers.toArray()));

        //add by index
        numbers.add(3, 5);

        // set
        numbers.set(1, 6);

        System.out.println(Arrays.toString(numbers.toArray()));

        // size
        System.out.println("size = " + numbers.size());

        // contains
        System.out.println("numbers.contains(6) = " + numbers.contains(6));

        // indexOf
        System.out.println("numbers.indexOf(1) = " + numbers.indexOf(1));

        // lastIndexOf
        System.out.println("numbers.lastIndexOf(5) = " + numbers.lastIndexOf(5));

        // get
        System.out.println("numbers.get(0) = " + numbers.get(0));

        // equals(self)
        System.out.println("numbers.equals(numbers) = " + numbers.equals(numbers));

        // equals()
        IntegerList anotherNumbers = new ArrayIntegerList(4);
        anotherNumbers.add(1);

        System.out.println("numbers.equals(anotherNumbers) = " + numbers.equals(anotherNumbers));

        // clear() and isEmpty()
        numbers.clear();
        System.out.println("numbers.isEmpty() = " + numbers.isEmpty());

        // throws
        try {
            anotherNumbers.add(2, 0);
        } catch (IntegerListIndexOutOfBoundsException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }

        try {
            anotherNumbers.add(0, null);
        } catch (IntegerListNullArgumentValueException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }
}
