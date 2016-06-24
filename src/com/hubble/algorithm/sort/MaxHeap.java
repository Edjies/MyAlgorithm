package com.hubble.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 最大堆</br>
 *1. 定义： 对于堆中的任何一个节点，节点的值大于或等于它任何一个子节点的值</br>
 *</br>
 *2. 数组的实现：
 *				若数组以 1 为 基点， 则 最大堆的节点有如下性质：</br>
 *	&nbsp;&nbsp;&nbsp;&nbsp; 对于一个 k 位置的节点， 其父节点的位置为 k/2, 其两个子节点的位置为 2k,   2k+1 .  </br>
 *</br>
 *3. 上浮操作：</br>
 *	&nbsp;&nbsp;&nbsp;&nbsp;若一个堆中的一个节点 大于其父节点的值， 则可通过 不断和父节点交换 恢复最大堆的性质</br>
 *</br>
 *4. 下沉操作：
 *	&nbsp;&nbsp;&nbsp;&nbsp;若堆中一个节点的值小于 其 两个子节点中较大的那个，则可通过不断和 两个子节点中较大的那个 恢复最大堆的性质</br>
 *</br>
 *5. 最大堆的主要操作：</br>
 *	&nbsp;&nbsp;&nbsp;&nbsp;删除最大值 deleteMax()和 插入新值insert()， 他们都可在lgN的范围内完成</br>
 *
 *6. 最大堆为优先队列的实现基础</br>
 * @author hubble
 *
 * @param 
 */
public class MaxHeap <T extends Comparable<T>>{
	private int max = 100;
	private int size = 0;
	private Object[] pq;
	
	public static void main(String[] args) {
		MaxHeap<Integer> heap = new MaxHeap<>(10);
		for(int i = 0; i < heap.max; i++) {
			heap.insert(new Random().nextInt(50));
		}
		
		System.out.println("堆生成：" + Arrays.toString(heap.pq));
		
		for(int i = 0; i < heap.max; i++) {
			System.out.print(heap.deleteMax() + ",");
		}
	}
	
	@SuppressWarnings("unchecked")
	public MaxHeap(int max) {
		this.max = max;
		pq = new Object[max + 1];
	}
	
	public T insert(T t) {
		if(size < max) {
			size++;
		}else {
			return null;
		}
		
		pq[size] = t;
		
		swim(size);
		
		return t;
	}
	
	public T deleteMax() {
		if(size > 0) {
			T re = (T)pq[1];
			pq[1] = pq[size];
			size--;
			sink(1);
			return re;
		}
		return null;
	}
	
	public int size() {
		return size;
	}
	
	/**
	 * 如果 一个节点的值大于其父节点的值，则交换这两个节点。 pq[k] > pq[2k]
	 * @param k
	 */
	private void swim(int k) {
		while(k > 1 && k <= size) { // 存在父节点的条件
			if(less(k / 2, k)) {
				exch(k/2, k);
				k = k/2; // 继续向上上浮
			}else {
				break; // 注意循环中止条件
			}
		}
	}
	
	
	/**
	 * 如果一个节点的值小于其两个子节点最大的那个，则与最大的子节点交换
	 * @param k
	 */
	private void sink(int k) {
		int  j;
		while( 2 * k <= size) {  // 存在子节点的条件
			// 获取最大子节点的位置
			j = 2 * k;
			if(j + 1 <= size) {
				if(less(j, j+ 1)) {
					j = j + 1;
				}
			}
			// 交换
			if(less(k, j)) {
				exch(j, k);
			}else{
				break;
			}
			
			
			// 继续下一次下沉操作
			k = j;
		}
	}
	
	/**
	 * i 位置的元素是否小于 j位置的元素
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean less(int i, int j) {
		return ((Comparable)pq[i]).compareTo((Comparable)pq[j]) < 0;
	}
	
	/**
	 * 交换两个位置的值
	 * @param i
	 * @param j
	 */
	private void exch(int i, int j) {
		T temp = (T)pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	
	
	
	
}
