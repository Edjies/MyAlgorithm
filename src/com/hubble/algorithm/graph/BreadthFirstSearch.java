package com.hubble.algorithm.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

import com.hubble.algorithm.util.TimeAnalysis;

/**
 * 广度优先搜索算法</br>
 * 实现了最短路径搜索</br>
 * 算法思想：</br>
 * &nbsp;&nbsp; 和 深度优先搜索算法采用的后进先出的递归策略不同， 广度优先搜索算法采用了一种先进先出的策略。</br>
 * &nbsp;&nbsp; 即在遍历一个顶点 的 邻接点 的邻接点 前， 会先把顶点的所有未标记的邻接点全部遍历完。</br>
 * &nbsp;&nbsp; 这样标记的路径信息总是从起点到该顶点最短的路径信息。路径越短，越先被标记。</br>
 * 
 * @author hubble
 *
 */
public class BreadthFirstSearch implements Paths{
	private boolean[] marked;
	private int[] edgeTo;
	private int s;
	
	public BreadthFirstSearch(Graph graph, int s) {
		marked = new boolean[graph.V()];
		edgeTo = new int[graph.V()];
		this.s = s;
		bst(graph, s);
	}
	@Override
	public boolean hasPathTo(int v) {
		return marked[v];
	}

	
	@Override
	public Iterable<Integer> pathTo(int v) {
		Stack<Integer> path = new Stack<>();
		if(!hasPathTo(v)) {
			return path;
		}
		
		int w;
		while((w = edgeTo[v]) != s) {
			path.push(w);
			v = w;
		}
		
		path.push(s);
		return path;
	}
	
	// 打印从起点到某一顶点的路径
	public void printPath(int v) {
		Stack<Integer> path = (Stack<Integer>) pathTo(v);
		while(!path.isEmpty()) {
			System.out.print(path.pop() + "-");
		}
		System.out.print(v);
	}

	/**
	 * 将起点放入队列</br>
	 * 从队列中移除一个顶点， 遍历其邻接点， 如 邻接点没有被标记，则 将该邻接点放入队列， 并标记路径信息</br>
	 * 先入先出队列保证了路径越短的顶点越先被处理。</br>
	 * @param graph
	 * @param s
	 */
	private void bst(Graph graph, int s) {
		Queue<Integer> queue = new ArrayDeque<>();
		marked[s] = true;
		queue.offer(s);
		while(!queue.isEmpty()) {
			int v = queue.poll();
			for(int w : graph.adj(v)) {
				if(!marked[w]) {
					marked[w] = true;
					edgeTo[w] = v;
					queue.offer(w);
				}
			}
		}		
	}
	
	public static void main(String[] args) {
		int[] graph = {9,10,  0,1,  2,3,  4,2,  1,3,  0,6,  3,5,  7,6, 3,6, 2, 6};
		System.out.println(Arrays.toString(graph));
		SimpleGraph sg = new SimpleGraph(graph);
		TimeAnalysis analysis = new TimeAnalysis();
		analysis.startRun();
		BreadthFirstSearch bfs = new BreadthFirstSearch(sg, 0);
		analysis.endRun();
		bfs.printPath(6);
	}

}
