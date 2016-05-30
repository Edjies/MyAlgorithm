package com.hubble.algorithm.graph;

import java.util.ArrayList;
/**
 * 用邻接表实现一个简单的无向图
 * @author hubble
 *
 */
public class SimpleGraph implements Graph {
	/**顶点的数量*/
	private final int v;
	
	/**边的数量*/
	private int e;
	
	/**邻接表*/
	private ArrayList<Integer>[] adj;	
	
	
	@SuppressWarnings("unchecked")
	public SimpleGraph(int v) {
		this.v = v;
		this.e = 0;
		adj = (ArrayList<Integer>[]) new ArrayList[v]; 
		for(int i = 0; i < v; i++) {
			adj[i] = new ArrayList<Integer>();
		}
	}
	
	/**
	 * 从一个数组中创建一个图</br>
	 * @param graph  [<顶点数>, <边数>, <顶点v1>, <顶点w1>, ... ]
	 */
	public SimpleGraph(int[] graph) {
		this(graph[0]);
		for(int i = 2; i < graph.length; i+=2) {
			addEdge(graph[i],graph[i + 1]);
		}
	}

	@Override
	public int V() {
		return v;
	}

	@Override
	public int E() {
		return e;
	}

	/**
	 * 两个顶点的链表增加一个邻接点,边增加1</br>
	 */
	@Override
	public void addEdge(int v1, int v2) {
		adj[v1].add(v2);
		adj[v2].add(v1);
		e++;
	}

	/**
	 * 返回顶点对应的链表
	 */
	@Override
	public Iterable<Integer> adj(int V) {
		return adj[V];
	}
	
	/**
	 * 图是否有环</br>
	 * 在不考虑自环和平行边的情况下：</br>
	 *	在进行深度搜索过程中， 若搜到一个被标记的节点， 并且该节点不是上一次的递归节点，则表示存在环（存在从其它节点达到该节点的路径）</br>
	 * @return
	 */
	public boolean isCycle() {
		boolean[] mark = new boolean[this.V()];
		for(int i = 0; i < V();  i++) {
			if(!mark[i]) {
				return dfs(i, i, mark);// 函数的递归返回，（在某一个递归阶段，若函数有明确的返回值，则递归结束，执行递归返回）
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param s 需要搜索的节点
	 * @param r 进行环判断的节点
	 * @param mark
	 * @return
	 */
	private boolean dfs(int s, int u, boolean[] mark) {
		mark[s] = true;
		for(int w : adj(s)) {
			if(!mark[w]) {
				return dfs(w, s, mark);
			}else if(w != u) { // 被标记的邻接点不是其递归节点
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[] graph = {10,5,  0,1,  0,6,  2,3,  5,1,  0,4};
		SimpleGraph sg = new SimpleGraph(graph);
		System.out.println("graph is cycle:" + sg.isCycle());
		
	}

	
}
