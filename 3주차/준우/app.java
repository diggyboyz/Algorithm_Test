package org.dfpl.lecture.database.assignment2.assignment16011170;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Random;
import java.util.TreeSet;

public class App {

	public static void main(String[] args) {
		System.out.println("Assignment 4: ");
		SixWayBPlusTree bpTree = new SixWayBPlusTree();
		for (int i = 1; i < 22; i++) {
			bpTree.add(i);
		}

		bpTree.getNode(11);
		System.out.println();
		bpTree.getNode(22);
		System.out.println();
		bpTree.inorderTraverse();

		System.out.println("Assignment 5: ");
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			list.add(r.nextInt(10000));
		}
		NavigableSet<Integer> treeSet = new TreeSet<Integer>();
		for (Integer val : list) {
			treeSet.add(val);
		}

		NavigableSet<Integer> yourBTree = new SixWayBPlusTree();
		for (Integer val : list) {
			yourBTree.add(val);
		}

		System.out.println("first test: " + treeSet.first().equals(yourBTree.first()));
		System.out.println("last test: " + treeSet.last().equals(yourBTree.last()));
		Iterator<Integer> treeIterator = treeSet.iterator();
		Iterator<Integer> yourBTreeIterator = yourBTree.iterator();
		boolean isPass = true;
		while (treeIterator.hasNext() && yourBTreeIterator.hasNext()) {
			if (!treeIterator.next().equals(yourBTreeIterator.next())) {
				isPass = false;
				break;
			}
		}
		System.out.println("iterator test: " + isPass);

		for (int i = 0; i < list.size() / 2; i++) {
			treeSet.remove(list.get(i));
			yourBTree.remove(list.get(i));
		}
		treeIterator = treeSet.iterator();
		yourBTreeIterator = yourBTree.iterator();
		isPass = true;
		while (treeIterator.hasNext() && yourBTreeIterator.hasNext()) {
			if (!treeIterator.next().equals(yourBTreeIterator.next())) {
				isPass = false;
				break;
			}
		}
		System.out.println("remove test: " + isPass);
	}
}
