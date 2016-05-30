package com.hubble.algorithm.graph;
/**
 * 简单无向图 接口</br>
 * 图的概念：</br>
 * 1. 连通图：从图的任意顶点都存在一条路径到达另外任意一个顶点，则图是连通的</br>
 * 2. 有环图：存在一条路径使得路径的起点和终点相同</br>
 * @author hubble
 *
 */
public interface Graph {
	/**
	 * 顶点的数量
	 * @return
	 */
	public int  V();
	
	/**
	 * 边的数量
	 * @return
	 */
	public int E();
	
	/**
	 * 向图中增加一条边
	 * @param v1
	 * @param v2
	 */
	public void addEdge(int v1, int v2);
	
	/**
	 * 和 V 相邻的所有定点
	 * @param V
	 * @return
	 */
	public Iterable<Integer> adj(int V);
	
	
}
