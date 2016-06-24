package com.hubble.algorithm.sort;
/**
 * 最小堆</br>
 * 
 * @author hubble
 *@see #MaxHeap
 *@remember 只有我在真正做东西的时候，并且做出来了，才会觉得有趣。不管是啥，想到了就去做。
 * @param <T>
 */
public class MinHeap <T extends Comparable<T>>{
	private int max = 0;
	private Object[] pq;
	private int size = 0;
	
	public MinHeap(int max) {
		this.max = max;
		pq = new Object[max + 1];
		size = 0;
	}
	
	// 下沉, 如果某个节点 大于 其 任何一个子节点， 将该节点 和  其中较小的子节点交换。 
	public void sink(int position) {
		int minchild;
		while(position * 2 <= size) {
			minchild = position * 2;
			if(position * 2 + 1 <= size) {
				if(less(minchild + 1, minchild)) {
					minchild = minchild + 1;
				}
			}
			
			if(less(minchild, position)) {
				exch(minchild, position);
				position = minchild;
			}else {
				break;
			}
		}
		
		
		
	}
	
	// 上浮， 如果 某个子节点比 其父节点的值要小， 则交换。
	public void swim(int position) {
		while(position / 2 > 0) {
			if(less(position, position / 2)) {
				exch(position, position / 2);
			}else {
				break;
			}
			position = position / 2;
		}
	}
	
	
	// 将队列中最后一个元素 和 第一个元素对调， 删掉最后一个元素， 对第一个元素进行下沉操作。
	public T deleteMin() {
		if(size > 0) {
			exch(1, size);
			size--;
			sink(1);
			return (T) pq[size + 1];
		}
			
		
		return null;
	}
	
	// 如果没有溢出， 加入队尾，进行上浮操作。
	public T insert(T t) {
		if(size < max) {
			pq[size + 1] = t;
			size++;
			swim(size);
			return t;
		}
		return null;
		
	}
	
	public boolean isEmpty() {
		return size <= 0;
	}
	
	private boolean less(int p1, int p2) {
		return ((T) pq[p1]).compareTo((T) pq[p2]) < 0;
	}
	
	private void exch(int p1, int p2) {
		T temp = (T) pq[p1];
		pq[p1] = pq[p2];
		pq[p2] = temp;
	}
}
