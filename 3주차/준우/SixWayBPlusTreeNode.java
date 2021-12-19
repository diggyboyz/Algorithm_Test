package org.dfpl.lecture.database.assignment2.assignment16011170;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class SixWayBPlusTreeNode {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	
	// 부모 노드를 저장할 수 있어야 한다.(부모 노드는 1개만 가질 수 있다.)
	private SixWayBPlusTreeNode parent;
	//5개의 key를 저장할 수 있어야 한다. -> 6-way b+tree이기 때문에
	private List<Integer> keyList;
	// 자식 노드를 저장할 수 있어야 한다.( 자식 노드는 6개 가질 수 있다.) -> 6-way b+tree이기 때문에
	private List<SixWayBPlusTreeNode> children;
	
	//초기 생성자는 root 노드로 부모가 없음
	SixWayBPlusTreeNode(){
		this.parent = null;
		this.keyList = new ArrayList<Integer>();
		this.children = new ArrayList<SixWayBPlusTreeNode>();
	}

	public SixWayBPlusTreeNode getParent() {
		return parent;
	}

	public void setParent(SixWayBPlusTreeNode parent) {
		this.parent = parent;
	}

	public List<Integer> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<Integer> keyList) {
		this.keyList = keyList;
	}

	public List<SixWayBPlusTreeNode> getChildren() {
		return children;
	}
	
	public void setChildren(List<SixWayBPlusTreeNode> children) {
		this.children = children;
	}

	

	
	
}
