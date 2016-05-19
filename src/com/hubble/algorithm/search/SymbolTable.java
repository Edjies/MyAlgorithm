package com.hubble.algorithm.search;

public interface SymbolTable<Key extends Comparable<Key>, Value> {
	public boolean isEmpty() ;
	
	public int size() ;
	
	public boolean contains(Key key) ;
	
	public Value get(Key key) ;
		
	/**如果键不存在， 放入键值对， 如果键存在， 则更新值*/
	public  void put(Key key, Value value) ;
	
	public void delete(Key key) ;
	
	public void deleteMin();
}
