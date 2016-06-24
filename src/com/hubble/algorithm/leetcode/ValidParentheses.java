package com.hubble.algorithm.leetcode;

import java.util.Stack;

/**
 * 给定一个只包含 '(', ')', '[', ']', '{', '}'的字符串， 判断 字符串的括号是否是正确的顺序。</br>
 * 思路：</br>
 * 遍历字符串，遇到 左括号入栈， 遇到右括号出栈， 如果出栈括号 和 当前括号不一样，则不合法。
 * @author hubble
 *
 */
public class ValidParentheses {
	public static void main(String[] args) {
		System.out.print(isValid("((([{}])))"));
	}
	
	
	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < s.length(); i++) {
			char b =s.charAt(i);
			if(b == '(' || b == '[' || b == '{') {
				stack.push(b);
			}
			
			if(b == ')' || b == ']' || b == '}') {
				if(stack.isEmpty() || stack.pop() != b) {
					return false;
				}
			}
		}
		return true;
	}
}
