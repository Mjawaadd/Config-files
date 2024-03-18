package com.sun.lru.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LRU<K, V> {

	public LRU(int size) {
		super();
		this.size = size;
	}
	
	public LRU() {
		this(DEFAULT_SIZE);
	}
	
	public void add(K key, V value) {
		if( lruCache.size() <= size ) {
			lruCache.put(key, value);
		} else {
			evict();
			add (key, value);
		}
		
	}

	private void evict() {
		// TODO Auto-generated method stub
		
	}

	public V getKey(K key) {
		return lruCache.get(key);
	}

	private static final int DEFAULT_SIZE = 10;
	private Map<K, V> lruCache = new HashMap<>();
	private int size = DEFAULT_SIZE;
	
	private class Node {
		K key;
		Long timeEntered;
	}
	
	PriorityQueue<Node> pq = new PriorityQueue<>((a,b) ->  a.timeEntered.compareTo(b.timeEntered));

	public static void main(String[] args) {

		LRU<Integer, String> lru = new LRU<>();

		lru.add(1, "test");

		System.out.println(lru.getKey(1));
	}

}
