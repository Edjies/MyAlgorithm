package com.hubble.algorithm.graph;

import java.util.Stack;
/**
 * 深度优先搜索路径</br>
 * 基于深度优先搜索，保存了两个邻接点的路径信息。</br>
 * 深度优先搜索无法解决最短路径的情况
 * @author hubble
 *
 */
public class DepthFirstPaths implements Paths {
	/**图*/
	private Graph graph;
	
	/**起点*/
	private int s;
	
	private boolean[] marked;
	
	// 保存邻接点路径信息， 若 搜索到v-w (即 w 在此之前没有被搜索到), 则 edgeTo[w] = v
	private int[] edgeTo;
	
	public DepthFirstPaths(Graph graph, int s) {
		this.graph = graph;
		this.s = s;
		marked = new boolean[graph.V()];
		
		edgeTo = new int[graph.V()];
		
		// 创建路径
		dfs(graph, s);
	}
	
	/**
	 * 在搜索到一个新的顶点时，把这条路径标记
	 * @param graph
	 * @param v
	 */
	private void dfs(Graph graph, int v) {
		marked[v] = true;	
		for(int w : graph.adj(v)) {
			if(!marked[w]) {
				edgeTo[w] = v;
				dfs(graph, w);
			}
		}
	}

	/**
	 * 如果某个顶点被标记，则存在从起点到该顶点的路径</br>
	 * @param v
	 * @return
	 */
	@Override
	public boolean hasPathTo(int v) {
		return marked[v];
	}

	/**
	 * 从该顶点开始，沿着路径搜索，知道找到顶点
	 * @param v
	 * @return
	 */
	@Override
	public Iterable<Integer> pathTo(int v) {
		Stack<Integer> path = new Stack<>();

		// 不存在路径
		if(!hasPathTo(v)) {
			return path;
		}
		
		// 存在路径
		int w;
		while((w = edgeTo[v]) != s) {
			path.push(w);
			v = w;
		}
	  path.push(w);
		
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
	
	public static void main(String[] args) {
		int[] graph = {9,10,  0,1,  2,3,  4,2,  1,3,  3,4,  3,5,  7,6, 3,6, 2,5};
		SimpleGraph sg = new SimpleGraph(graph);
		DepthFirstPaths paths = new DepthFirstPaths(sg, 0);
		paths.printPath(8);
	}
	
	

}
