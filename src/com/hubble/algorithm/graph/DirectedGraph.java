package com.hubble.algorithm.graph;
import java.util.HashSet;
import java.util.Stack;

/**
 * 有向图</br>
 * 有向图和无向图的最大区别是： 对于边 v -> w 和 w -> v 无向图看成是一条边， 而有向图看成是不同的两条边</br>
 * 有向图的数据结构和无向图的数据结构基本相同。 只是在 addEdge() 方法上有所不同。 </br>
 * @author hubble
 *
 */
public class DirectedGraph implements Graph {
	private int V;
	private int E;
	
	private HashSet<Integer>[] adj;
	
	/**
	 * 从一个数组中创建一个图</br>
	 * @param graph  [<顶点数>, <边数>, <顶点v1>, <顶点w1>, ... ]
	 */
	public DirectedGraph(int[] graph) {
		V = graph[0];
		E = graph[1];
		adj = new HashSet[V];
		for(int i =0; i < V; i++) {
			adj[i] = new HashSet<Integer>();
		}
		for(int i = 2; i < graph.length - 1; i+=2) {
			addEdge(graph[i], graph[i+1]);
		}
	}
	@Override
	public int V() {
		return V;
	}

	@Override
	public int E() {
		return E;
	}

	@Override
	public void addEdge(int v1, int v2) {
		adj[v1].add(v2);
		E++;

	}

	@Override
	public Iterable<Integer> adj(int V) {
		return adj[V];
	}
	
	/**
	 * 有向图的第一个问题</br>
	 * 给定一个图 和 图中的一个顶点，是否存在到该顶点的环。（不考虑自环）</br>
	 * 算法思想：</br>
	 * 使用深度优先搜索遍历图并保存遍历路径， 如果遍历到某个已被标记的顶点并且该顶点等于起点，则到起点的环存在，根据遍历路径获取环上的所有顶点。</br>
	 * 注1：</br>
	 * 由于有向图的性质， v -> w 和 w-> v 看成是两条路径</br>
	 * 
	 * @param v
	 * @return 如果存在返回该环的所有顶点， 否则返回null
	 */
	public Stack<Integer> findCircle(int start) {
		boolean[] marked = new boolean[V];
		int[] edgeTo = new int[V];
		return dfs_c(this, start, marked, edgeTo, start);
	}
	
	// 通过深度优先搜索获取某个顶点的环
	private Stack<Integer> dfs_c(DirectedGraph graph, int v, boolean[] marked, int[] edgeTo, int start) {
		marked[v] = true;
		for(Integer w : graph.adj(v)) {
			
			if(marked[w] && w == start) {
				edgeTo[w] = v;
				// 存在一条以 start 为起点和终点的环, 获取环上的顶点
				Stack<Integer> path = new Stack<>();
				int i = w;
				do {
					path.push(i);
					i = edgeTo[i];
				}while(i != w);
				path.push(w);
				return path;
			}
			
			// 不存在，则继续向下遍历
			else  if(!marked[w]){
				edgeTo[w] = v;	
				return dfs_c(graph, w, marked, edgeTo, start);
			}
		}
		return null;
	}
	
	/**
	 * 有向图的第二个问题，顶点排序 和 拓扑排序
	 * @param args
	 */
	
	
	/**
	 * 加权无向图 与最小生成树
	 * @param args
	 */
	
	public static void main(String[] args) {
		int[] graph = {7, 8, 1, 2,  2,3,  3,5,  5,4,  3,4,  4,6,  6,1, 6,5};
		DirectedGraph directGraph = new DirectedGraph(graph);
		Stack<Integer> path = directGraph.findCircle(2);
		if(path == null) {
			return;
		}
		for(int i = 0; !path.isEmpty(); i++) {
			System.out.print(path.pop() + "->");
		}
	}
	
	

}
