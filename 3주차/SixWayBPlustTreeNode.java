package org.dfpl.lecture.database.assignment2.assignment16011170;

import java.util.List;

@SuppressWarnings("unused")
public class SixWayBPlusTreeNode {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	private SixWayBPlusTreeNode parent;
	private List<Integer> keyList;
	private List<SixWayBPlusTreeNode> children;

}
