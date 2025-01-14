package me.hello.backend.config;

import java.util.Arrays;
import java.util.Random;

public class SortingTest {

    public static void main(String[] args) {
        // 랜덤 배열 생성
        int[] array = generateRandomArray(10000, 0, 1000000000);
        System.out.println("Original Array: " + Arrays.toString(array));

        // 버블 정렬 실행 및 시간 측정
        int[] bubbleSorted = array.clone();
        long bubbleStart = System.currentTimeMillis();
        /**
         * 버블 정렬
         * 설명: 인접한 두 원소를 비교하여 정렬이 필요하면 교환하는 과정을 반복.
         * 장점: 구현이 간단하고 직관적임.
         * 단점: 데이터가 많아질수록 비효율적임 (시간 복잡도 O(n^2)).
         */
        bubbleSort(bubbleSorted);
        long bubbleEnd = System.currentTimeMillis();
        System.out.println("Bubble Sorted: " + Arrays.toString(bubbleSorted) + " (Time: " + (bubbleEnd - bubbleStart) + " ms)");

        // 선택 정렬 실행 및 시간 측정
        int[] selectionSorted = array.clone();
        long selectionStart = System.currentTimeMillis();
        /**
         * 선택 정렬
         * 설명: 배열에서 최소값을 찾아 첫 번째 원소와 교환하는 작업을 반복.
         * 장점: 간단한 구현.
         * 단점: 데이터가 많아질수록 비효율적임 (시간 복잡도 O(n^2)).
         */
        selectionSort(selectionSorted);
        long selectionEnd = System.currentTimeMillis();
        System.out.println("Selection Sorted: " + Arrays.toString(selectionSorted) + " (Time: " + (selectionEnd - selectionStart) + " ms)");

        // 삽입 정렬 실행 및 시간 측정
        int[] insertionSorted = array.clone();
        long insertionStart = System.currentTimeMillis();
        /**
         * 삽입 정렬
         * 설명: 정렬된 부분과 정렬되지 않은 부분을 나누어, 정렬되지 않은 원소를 적절한 위치에 삽입.
         * 장점: 작은 데이터나 거의 정렬된 데이터에서 효율적임 (최선 시간 복잡도 O(n)).
         * 단점: 데이터가 많으면 비효율적임 (최악 시간 복잡도 O(n^2)).
         */
        insertionSort(insertionSorted);
        long insertionEnd = System.currentTimeMillis();
        System.out.println("Insertion Sorted: " + Arrays.toString(insertionSorted) + " (Time: " + (insertionEnd - insertionStart) + " ms)");

        // 퀵 정렬 실행 및 시간 측정
        int[] quickSorted = array.clone();
        long quickStart = System.currentTimeMillis();
        /**
         * 퀵 정렬
         * 설명: 배열을 피벗 기준으로 두 부분으로 나누어 재귀적으로 정렬.
         * 장점: 평균적으로 매우 빠름 (시간 복잡도 O(n log n)).
         * 단점: 피벗 선택이 나쁘면 성능이 저하될 수 있음 (최악 시간 복잡도 O(n^2)).
         */
        quickSort(quickSorted, 0, quickSorted.length - 1);
        long quickEnd = System.currentTimeMillis();
        System.out.println("Quick Sorted: " + Arrays.toString(quickSorted) + " (Time: " + (quickEnd - quickStart) + " ms)");

        // 병합 정렬 실행 및 시간 측정
        int[] mergeSorted = array.clone();
        long mergeStart = System.currentTimeMillis();
        /**
         * 병합 정렬
         * 설명: 배열을 반으로 나누고 각각 정렬한 후 병합.
         * 장점: 안정적이고 큰 데이터셋에서 효율적 (시간 복잡도 O(n log n)).
         * 단점: 추가 메모리가 필요함.
         */
        mergeSort(mergeSorted, 0, mergeSorted.length - 1);
        long mergeEnd = System.currentTimeMillis();
        System.out.println("Merge Sorted: " + Arrays.toString(mergeSorted) + " (Time: " + (mergeEnd - mergeStart) + " ms)");
    }

    /**
     * 랜덤한 정수 배열 생성
     *
     * @param size 배열 크기
     * @param min  최소값
     * @param max  최대값
     * @return 랜덤 값으로 채워진 배열
     */
    public static int[] generateRandomArray(int size, int min, int max) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }

    /**
     * 버블 정렬
     *
     * @param array 정렬할 배열
     */
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    /**
     * 선택 정렬
     *
     * @param array 정렬할 배열
     */
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    /**
     * 삽입 정렬
     *
     * @param array 정렬할 배열
     */
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    /**
     * 퀵 정렬
     *
     * @param array 정렬할 배열
     * @param low   시작 인덱스
     * @param high  끝 인덱스
     */
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    /**
     * 퀵 정렬의 피벗 분할
     *
     * @param array 배열
     * @param low   시작 인덱스
     * @param high  끝 인덱스
     * @return 피벗의 인덱스
     */
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * 병합 정렬
     *
     * @param array 정렬할 배열
     * @param left  시작 인덱스
     * @param right 끝 인덱스
     */
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    /**
     * 병합 정렬의 병합 작업
     *
     * @param array 배열
     * @param left  시작 인덱스
     * @param mid   중간 인덱스
     * @param right 끝 인덱스
     */
    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    /**
     * 배열 원소 교환
     *
     * @param array 배열
     * @param i     첫 번째 인덱스
     * @param j     두 번째 인덱스
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}