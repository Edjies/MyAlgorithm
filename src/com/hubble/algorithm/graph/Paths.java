package com.hubble.algorithm.graph;
/**
 * 表示 一个图中 从起点 到所有连通顶点的路径</br>
 * @author hubble
 */
public interface Paths {
	/**
	 * 是否存在从起点到特定顶点的路径
	 * @param v
	 * @return
	 */
	public boolean hasPathTo(int v);
	
	/**
	 * 从起点到 一个顶点的路径
	 * @param v
	 * @return
	 */
	public Iterable<Integer> pathTo(int v);
}
