package pro.sky.integerlist;

import java.util.Arrays;
import java.util.Random;

public class SortComparison {
    static final int NUMBERS_COUNT = 100_000;

    public static void main(String[] args) {
        Integer[] numbersForBubbleSort = getRandomArrayOfIntegerNumbers(NUMBERS_COUNT);
        Integer[] copyNumbersForSelectionSort = Arrays.copyOf(numbersForBubbleSort, numbersForBubbleSort.length);
        Integer[] copyNumbersForInsertionSort = Arrays.copyOf(numbersForBubbleSort, numbersForBubbleSort.length);

        testBubbleSortMethod(numbersForBubbleSort);
        testSelectionSortMethod(copyNumbersForSelectionSort);
        testInsertionSortMethod(copyNumbersForInsertionSort);
    }

    private static Integer[] getRandomArrayOfIntegerNumbers(int numbersCount) {
        Integer[] result = new Integer[numbersCount];
        Random random = new Random();
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(-50, 51);
        }
        return result;
    }

    private static void testBubbleSortMethod(Integer[] arr) {
        System.out.print("Пузырьковая сортировка: ");
        long start = System.currentTimeMillis();
        sortBubble(arr);
        long end = System.currentTimeMillis();
        System.out.println((end - start) + " ms.");
    }

    private static void testSelectionSortMethod(Integer[] arr) {
        System.out.print("Сортировка выбором: ");
        long start = System.currentTimeMillis();
        sortSelection(arr);
        long end = System.currentTimeMillis();
        System.out.println((end - start) + " ms.");
    }

    private static void testInsertionSortMethod(Integer[] arr) {
        System.out.print("Сортировка вставкой: ");
        long start = System.currentTimeMillis();
        sortInsertion(arr);
        long end = System.currentTimeMillis();
        System.out.println((end - start) + " ms.");
    }

    private static void swapElements(Integer[] arr, int indexFirst, int indexSecond) {
        Integer tmp = arr[indexFirst];
        arr[indexFirst] = arr[indexSecond];
        arr[indexSecond] = tmp;
    }

    private static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    private static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private static void sortInsertion(Integer[] arr) {
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
}
