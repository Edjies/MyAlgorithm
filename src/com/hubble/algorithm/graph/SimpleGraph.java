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

}
