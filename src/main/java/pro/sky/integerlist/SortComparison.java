package pro.sky.integerlist;

import java.util.Arrays;
import java.util.Random;

public class SortComparison {
    static final int NUMBERS_COUNT = 100_000;

    public static void main(String[] args) {
        Integer[] numbersForBubbleSort = getRandomArrayOfIntegerNumbers(NUMBERS_COUNT);
        Integer[] copyNumbersForSelectionSort = Arrays.copyOf(numbersForBubbleSort, numbersForBubbleSort.length);
        Integer[] copyNumbersForInsertionSort = Arrays.copyOf(numbersForBubbleSort, numbersForBubbleSort.length);
        Integer[] copyNumbersForQuickSort = Arrays.copyOf(numbersForBubbleSort, numbersForBubbleSort.length);
        Integer[] copyNumbersForMergeSort = Arrays.copyOf(numbersForBubbleSort, numbersForBubbleSort.length);

        testBubbleSortMethod(numbersForBubbleSort);

        testSelectionSortMethod(copyNumbersForSelectionSort);
        testInsertionSortMethod(copyNumbersForInsertionSort);
        testQuickSortMethod(copyNumbersForQuickSort);
        testMergeSortMethod(copyNumbersForMergeSort);
    }

    private static Integer[] getRandomArrayOfIntegerNumbers(int numbersCount) {
        Integer[] result = new Integer[numbersCount];
        Random random = new Random();
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(-numbersCount, numbersCount + 1);
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

    private static void testQuickSortMethod(Integer[] arr) {
        System.out.print("Быстрая сортировка: ");
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println((end - start) + " ms.");
    }

    private static void testMergeSortMethod(Integer[] arr) {
        System.out.print("Сортировка слиянием: ");
        long start = System.currentTimeMillis();
        mergeSort(arr);
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

    private static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void mergeSort(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    private static void merge(Integer[] arr, Integer[] left, Integer[] right) {
        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }
}
