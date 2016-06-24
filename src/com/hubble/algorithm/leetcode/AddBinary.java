package com.hubble.algorithm.leetcode;

import java.util.Stack;

/**
 * 计算两个二进制字符串的和。</br>
 * 如 ‘111’ + ‘1’ = ‘1000’
 * 
 * @author hubble
 *
 */
public class AddBinary {
	public static void main(String[] args) {
				System.out.println(addBinary("1", "111"));
	}
	
	public static String addBinary(String a, String b) {
		int index = 0;
		int r = 0;
		Stack<Character> s = new Stack<>();
		while(a.length() > index && b.length() > index) {
			int i = a.charAt(a.length()  - index - 1) - '0' + b.charAt(b.length()  - index - 1) - '0' + r;
			r = i / 2;
			s.push((char) (i % 2 + '0'));
			index++;
		}
		
		
		while(a.length() > index) {
			int i = a.charAt(a.length() - index - 1) - '0' + r;
			r = i / 2;
			s.push((char) (i % 2 + '0'));
			index++;
		}
		
		while(b.length() > index) {
			int i = b.charAt(b.length() - index - 1) - '0' + r;
			r = i / 2;
			s.push((char) (i % 2 + '0'));
			index++;
		}
		
		
		
		if(r>0) {
			s.push((char) (r + '0'));
		}
		
		char[] result = new char[s.size()];
		int j = 0;
		while(!s.isEmpty()) {
			result[j++] = s.pop();
		}
		return String.valueOf(result);
	}
}
