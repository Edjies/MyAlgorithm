package com.hubble.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

import com.hubble.algorithm.util.TimeAnalysis;

public class Sort{
	public static void main(String[] args) {
		TimeAnalysis analysis = new TimeAnalysis();
		// 冒泡排序
		System.out.println("冒泡排序：");
		Integer[] data = rankArray(100);
		analysis.startRun();
		bubbleSort(data);
		analysis.endRun();
		
		// 插入排序
		System.out.println("插入排序：");
		data = rankArray(100);
		System.out.println(Arrays.toString(data));
		analysis.startRun();
		insertSort(data);
		System.out.println(Arrays.toString(data));
		analysis.endRun();
		
		// 归并排序
		System.out.println("归并排序：");
		data = rankArray(1000000);
		//System.out.println(Arrays.toString(data));
		long start = System.currentTimeMillis();
		mergeSort(data);
//		System.out.println(Arrays.toString(mergeSort(data)));
		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000l + "秒" + (end - start) % 1000l + "毫秒");
		
		// 快速排序
		System.out.println("快速排序：");
		data = rankArray(100000);
		analysis.startRun();
	//System.out.println(Arrays.toString(data));
		quickSort(data);
	//System.out.println(Arrays.toString(data));
		analysis.endRun();
		
		// 堆排序
	}
	
	/**
	 * 冒泡排序：
	 * 重复扫描整个数组， 每扫描一次，通过交换会将未排序部分最大的数置顶，直至所有数都排序完毕或扫描数组时没有进行一次交换。
	 * @param data
	 */
	@SuppressWarnings("rawtypes")
	public static void bubbleSort(Comparable[] data) {
		// 重复扫描
		for(int i = data.length - 1; i >= 1; i--){
			
			// 扫描数组
			boolean end = true;
			for(int j = 1 ; j <=i; j++) {
				if(data[j].compareTo(data[j - 1]) < 0) {
					Comparable temp = data[j];
					data[j] = data[j - 1];
					data[j - 1] = temp;
					end = false;
				}
			}
	
			// 如果没有数据交换，则结束
			if(end) {
				break;
			}
		}
	}
	
	/**
	 * 插入排序</br>
	 * 从无序数组中选择第一个元素插入到有序数组的正确位置。 </br>
	 * 实现：通过不断交换两个元素的位置实现。</br>
	 * @param data
	 */
	public static void insertSort(Integer[] data) {
		for(int i = 1; i  < data.length; i++) {
			for(int j = i; j > 0; j--) {
				// 如果 当前元素小于前一个元素，则交换两者位置
				if(data[j] < data[j - 1]  ) {
					exch(data, j, j - 1);
				}else {
					break;
				}
			}
		}
	}
	
	/**
	 * 选择排序</br>
	 * 从无序数组中选择最小的元素放入无序数组的第一个位置。</br>
	 * @param data
	 */
	public static void selectSort(Integer[] data) {
		for(int i = 0; i < data.length; i++) {
			int min = i;
			// 找到最小元素
			for(int j = i; j < data.length; j++  ) {
				if(data[j] < data[min]) {
					min = j;
				}
			}
			exch(data, i, min);
		}
	}
	
	public static void exch(Integer[] data, int i, int j) {
		int t = data[i];
		data[i] = data[j];
		data[j] = t;
	}
	


	
	private static  Integer[] rankArray() {
		Random random = new Random();
		Integer[] data = new Integer[random.nextInt(100) + 50 ];
		for(int i = 0; i < data.length; i++) {
			data[i] = random.nextInt(100);
		}
		return data;
	}
	
	private static  Integer[] rankArray(int size) {
		Random random = new Random();
		Integer[] data = new Integer[size];
		for(int i = 0; i < data.length; i++) {
			data[i] = random.nextInt(100);
		}
		return data;
	}
	
	@SuppressWarnings("rawtypes")
	public static void insertSort(Comparable[] data) {
		
	}
	
	/**
	 * 归并排序：</br>
	 * 优点：</br>
	 * 以O(NlogN)时间运行</br>
	 * 缺点：</br>
	 * 1. 数据量过大时， 函数堆栈会非常大</br>
	 * @param data
	 */
	@SuppressWarnings("rawtypes")
	public static Comparable[] mergeSort(Comparable[] data) {
		Comparable[] sorted = new Comparable[data.length];
		mergeSort(data, sorted, 0, data.length - 1);
		return sorted;
	}
	
	private static void mergeSort(Comparable[] unsorted, Comparable[] sorted, int left, int right) {
		// 中止条件
		if(left >= right) {
			return;
		}

		int middle = (left + right) / 2;
		
		// divide  递归排序
		mergeSort(unsorted, sorted, left, middle);
		mergeSort(unsorted, sorted, middle + 1, right);
		
		// 合并
		merge(sorted, left, middle, right, unsorted);
			
		
	}
	
	private static void merge(Comparable[] sorted, int left, int middle, int right, Comparable[] unsorted) {
		
		int leftIndex = left;
		int rightIndex = middle + 1;
		int index = left;
		while(leftIndex <= middle && rightIndex <= right) {
			if(unsorted[leftIndex].compareTo(unsorted[rightIndex]) < 0) {
				sorted[index] = unsorted[leftIndex];
				leftIndex++;
			}else {
				sorted[index] = unsorted[rightIndex];
				rightIndex++;
			}
			index++;
		}
		
		while(leftIndex <= middle) {
			sorted[index] = unsorted[leftIndex];
			index++;
			leftIndex++;
		}
		
		while(rightIndex <= right) {
			sorted[index] = unsorted[rightIndex];
			index++;
			rightIndex++;
		}
		
		// 合并结果处理
		for(int j = left; j <= right;  j++) {
			unsorted[j] = sorted[j];
		}
		
//		System.out.println("merged(" + left + ","+middle + "," + right+"):" + Arrays.toString(sorted));
	}
	
	
	
	
	@SuppressWarnings("rawtypes")
	public static void quickSort(Comparable[] data) {
		quickSort(data, 0, data.length - 1);
	}
	
	private static void quickSort(Comparable[] data, int start, int end) {
		if(start >= end) {
			return;
		}
		
		int divide = partition(data, start, end);
//    	System.out.println(divide);
		quickSort(data, start, divide - 1);
		quickSort(data, divide + 1, end);
		
	}
	
	/**
	 * 选择一个标记元素，使得元素左边的数都小于等于该元素， 元素右边的数都大于该元素
	 * @param data
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unchecked" })
	private static int partition(Comparable[] data, int start, int end) {
		Comparable flag = data[start];
		int  divide = start;
		int i = start;
		int j = end + 1;
		while(true) {
			// 漫步条件 和 边界条件
			while(data[++i].compareTo(flag) <=0) {
				divide = i;
				if(i == end) {
					break;
				}
			}
			
			// 漫步条件 和 边界条件
			while(data[--j].compareTo(flag) > 0) {
				if(j == start) {
					break;
				}
			}
			
			// 交换条件
			if(i >= j) {
				break;
			}
			
			// change
			Comparable temp = data[i];
			data[i] = data[j];
			data[j] = temp;
			divide = i;
		}
		
		// 交换结束后，需要确定 分割位置
			Comparable temp = data[divide];
			data[divide] = flag;
			data[start] = temp;
		
		
//		System.out.println(Arrays.toString(data));
		
		
		
		
		
		return divide;
		
	}
	
	
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
        
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition2(Comparable[] data, int min, int max) {
    	int left,right;
        Comparable temp,partitionelement;
        
        left = min;right = max;
        partitionelement = data[min];//partitionelement 是data[min]的一个副本
        
        while(left < right)
        {
            while(data[left].compareTo(partitionelement) <= 0 && left < right)
                left++;
            while(data[right].compareTo(partitionelement) > 0)
                right--;
            if(left < right)
            {
                temp = data[left];
                data[left] = data[right];
                data[right] =temp;
            }
        }
        
        temp = data[min];
        data[min] = data[right];
        data[right] = temp;
        
        //错错误的写法，要的是把第一个元素和data[right]交换，而不是它的副本
        //temp = data[right];
        //data[right] = partitionelement;
        //partitionelement = temp;
        
        return right;
    }
	
	
	 public static void heapSort() {
		 
	 }
}
