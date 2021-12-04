package org.dfpl.lecture.database.assignment2.assignment16011170;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedSet;

@SuppressWarnings("unused")
public class SixWayBPlusTree implements NavigableSet<Integer> {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	private SixWayBPlusTreeNode root;
	private LinkedList<SixWayBPlusTreeNode> leafList;

	// parent = null, keyList를 저장할 수 있고, 자식 노드를 저장할 수 있는 root 생성)
	SixWayBPlusTree() {
		this.root = new SixWayBPlusTreeNode();
		this.leafList = new LinkedList<SixWayBPlusTreeNode>();
	}

	public SixWayBPlusTreeNode getRoot() {
		return root;
	}

	public void setRoot(SixWayBPlusTreeNode root) {
		this.root = root;
	}

	public LinkedList<SixWayBPlusTreeNode> getLeafList() {
		return leafList;
	}

	public void setLeafList(LinkedList<SixWayBPlusTreeNode> leafList) {
		this.leafList = leafList;
	}

	/**
	 * 과제 Assignment4를 위한 메소드:
	 * 
	 * key로 검색하면 root부터 시작하여, key를 포함할 수 있는 leaf node를 찾고 key가 실제로 존재하면 해당 Node를
	 * 반환하고, 그렇지 않다면 null을 반환한다. 중간과정을 System.out.println(String) 으로 출력해야 함. 6 way
	 * B+ tree에서 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21 이 순서대로
	 * add되었다고 했을 때,
	 * 
	 * 예: getNode(11)을 수행하였을 때 > less than 13 > larger than or equal to 10 > 11
	 * found 위의 3 문장을 콘솔에 출력하고 11을 포함한 SixWayBPlusTreeNode를 반환함
	 * 
	 * 예: getNode(22)를 수행하였을 때 > larger than or equal to 13 > larger than or equal
	 * to 19 > 22 not found 위의 3 문장을 콘솔에 출력하고 null을 반환함.
	 * 
	 * @param key
	 * @return
	 */
	public SixWayBPlusTreeNode getNode(Integer key) {
		SixWayBPlusTreeNode checkLeafNode = new SixWayBPlusTreeNode();

		// 초기값은 항상 root로 시작.
		checkLeafNode = root;
		int index = 0;
		while (true) {
			// checkLeafNode == leafnode라면
			// System.out.println(checkLeafNode.getKeyList());
			// System.out.println(checkLeafNode.getChildren().size());
			boolean findCheck = false;
			if (checkLeafNode.getChildren() == null || checkLeafNode.getChildren().size() == 0) {
				// keyList에서 key와 같은 값이 있는지 탐색
				for (int i = 0; i < checkLeafNode.getKeyList().size(); i++) {
					if (key == checkLeafNode.getKeyList().get(i)) {
						findCheck = true;

					}
				}
				if (findCheck) {
					System.out.println(key + " found");
					return checkLeafNode;
				} else {
					System.out.println(key + " not found");
					return null;
				}

			} else {
				// checkLeafNode != leafnode라면
				// 현재 keyList와 key 값을 비교해서 다음 node로 이동하여 leaf node인지 탐색해야 함.
				boolean rightCheck = true;
				int size = checkLeafNode.getKeyList().size();
				// keyList에서 key값과 비교하며 leftchild를 탐색함
				for (int i = 0; i < size; i++) {
					// key가 keyList[i]값보다 작으면 index를 저장하고 break
					if (key < checkLeafNode.getKeyList().get(i)) {
						rightCheck = false;
						index = i;
						break;
					}
				}
				// key가 keyList[i]보다 클 때는 rightCheck = false가 동작하지 않아서 true
				if (rightCheck) {
					// 마지막 children으로 이동
					System.out.println("Larger than or equal to " + checkLeafNode.getKeyList().get(size - 1));
					index = checkLeafNode.getChildren().size() - 1;
					checkLeafNode = checkLeafNode.getChildren().get(index);
				}
				// key가 keyList보다 작은 값을 가지면 rightCheck = false
				else {
					// index 기반하여 왼쪽 자식으로 이동
					System.out.println("Less than " + checkLeafNode.getKeyList().get(index));
					checkLeafNode = checkLeafNode.getChildren().get(index);
				}

			}

		}
	}

	/**
	 * 과제 Assignment4를 위한 메소드:
	 * 
	 * inorder traversal을 수행하여, 값을 오름차순으로 출력한다.
	 * 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21 이 순서대로 add되었다고 했을 때, 1
	 * 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 위와 같이 출력되어야 함.
	 */
	public void inorderTraverse() {
		// TODO Auto-generated method stub
		Iterator<SixWayBPlusTreeNode> iterator = leafList.iterator();
		SixWayBPlusTreeNode temp = new SixWayBPlusTreeNode();
		List<Integer> duplicateList = new ArrayList<Integer>();
		while (iterator.hasNext()) {
			temp = iterator.next();
			for (int i = 0; i < temp.getKeyList().size(); i++) {
				System.out.println(temp.getKeyList().get(i));
			}

		}
	}

	@Override
	public Comparator<? super Integer> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer first() {
		int first = 0;
		first = leafList.get(0).getKeyList().get(0);
		// TODO Auto-generated method stub
		return first;
	}

	@Override
	public Integer last() {
		int last = 0;
		int leafListLastIndex = leafList.size() - 1;
		int keyListLastIndex = leafList.get(leafListLastIndex).getKeyList().size() - 1;
		last = leafList.get(leafListLastIndex).getKeyList().get(keyListLastIndex);
		// TODO Auto-generated method stub
		return last;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Integer e) {
		// TODO Auto-generated method stub

		List<Integer> tempKeyList = new ArrayList<Integer>();
		SixWayBPlusTreeNode checkLeafNode = new SixWayBPlusTreeNode();
		int index = 0;
		int leafListIndex = 0;
		int count = 0;
		// root node부터 e값이 들어갈 leaf node탐색을 해야 함.
		// 시작은 항상 root노드에서 시작
		checkLeafNode = root;
		// children size == 0 -> 자식이 없다. -> 즉 root = leaf node 이다.
		while (true) {
			if (checkLeafNode.getChildren().size() == 0) {

				// tempRoot에 root keyList 가져와서 추가
				tempKeyList = checkLeafNode.getKeyList();

				// LeafList 에서 tempKeyList탐색
				// leafList.indexOf(checkLeafNode);

				// 이미 key가 존재한다면 false를 반환함.
				for (int i = 0; i < tempKeyList.size(); i++) {
					// System.out.println(e + " " + tempKeyList.get(i));
					if (e - tempKeyList.get(i) == 0) {
						System.out.println(e + "가 존재함");
						return false;
					}
				}
				// keyList에 데이터 추가
				tempKeyList.add(e);

				// 정렬을 위한 sort
				Collections.sort(tempKeyList);

				// MAX_KEY 조건 위배 -> Left 0 to 2, Middle 3 , Right 3 to 5 split 발생
				if (tempKeyList.size() > 5) {

					SixWayBPlusTreeNode childLeft = new SixWayBPlusTreeNode();
					SixWayBPlusTreeNode childRight = new SixWayBPlusTreeNode();
					List<Integer> left = new ArrayList<Integer>();
					List<Integer> right = new ArrayList<Integer>();
					List<Integer> middle = new ArrayList<Integer>();
					List<SixWayBPlusTreeNode> tempChild = new ArrayList<SixWayBPlusTreeNode>();

					// childLeft value set
					left.add(tempKeyList.get(0));
					left.add(tempKeyList.get(1));
					left.add(tempKeyList.get(2));
					childLeft.setKeyList(left);

					// childRight value set
					right.add(tempKeyList.get(3));
					right.add(tempKeyList.get(4));
					right.add(tempKeyList.get(5));
					childRight.setKeyList(right);

					// find leafListIndex
					Iterator<SixWayBPlusTreeNode> iterator = leafList.iterator();
					SixWayBPlusTreeNode templeafList = new SixWayBPlusTreeNode();
					count = 0;
					while (iterator.hasNext()) {
						templeafList = iterator.next();
						if (templeafList == checkLeafNode) {
							leafListIndex = count;
						}
						count++;

					}

					// middle value set
					if (checkLeafNode.getParent() != null) {
						middle = checkLeafNode.getParent().getKeyList();
						// 부모의 자식 index 노드 삭제
						checkLeafNode.getParent().getChildren().remove(index);
						leafList.remove(leafListIndex);
						tempChild = checkLeafNode.getParent().getChildren();
					}
					// middle 값 update 그리고 tempChild 값 update
					// index 노드자리에 childLeaf, index+1 자리에 childRight가 들어가게됨
					tempChild.add(index, childLeft);
					tempChild.add(index + 1, childRight);
					middle.add(tempKeyList.get(3));
					Collections.sort(middle);

					// checkLeafNode가 root node가 아닐 때
					// checkLeafNode의 parent를 부모로
					if (checkLeafNode.getParent() != null) {

						childRight.setParent(checkLeafNode.getParent());
						childLeft.setParent(checkLeafNode.getParent());
						checkLeafNode.getParent().setChildren(tempChild);
						checkLeafNode.getParent().setKeyList(middle);

						leafList.add(leafListIndex, childLeft);
						leafList.add(leafListIndex + 1, childRight);

					}
					// checkLeafNode가 root node 일 때
					// checkLeafNode를 부모로 childLeft는 왼쪽자식 childRight는 오른쪽자식
					else {
						childLeft.setParent(checkLeafNode);
						childRight.setParent(checkLeafNode);
						checkLeafNode.setChildren(tempChild);
						checkLeafNode.setKeyList(middle);

						// leafList add
						leafList.remove();
						leafList.add(childLeft);
						leafList.add(childRight);
					}
					boolean leafNodeSplit = true;
					// 여기 위로 코드는 잘 돌아가고 여기서 문제가 있음
					// split 이후 부모가 MAX_KEY조건을 위배하는경우
					if (checkLeafNode.getParent() != null && checkLeafNode.getParent().getKeyList().size() > 5) {
						SixWayBPlusTreeNode parentNode = new SixWayBPlusTreeNode();

						while (true) {
							if (leafNodeSplit) {
								parentNode = checkLeafNode.getParent();
							}

							SixWayBPlusTreeNode parentLeftChild = new SixWayBPlusTreeNode();
							SixWayBPlusTreeNode parentRightChild = new SixWayBPlusTreeNode();
							List<SixWayBPlusTreeNode> parentTempChild = new ArrayList<SixWayBPlusTreeNode>();
							List<SixWayBPlusTreeNode> leftChildrenTempChild = new ArrayList<SixWayBPlusTreeNode>();
							List<SixWayBPlusTreeNode> rightChildrenTempChild = new ArrayList<SixWayBPlusTreeNode>();
							List<Integer> parentTempKey = new ArrayList<Integer>();
							List<Integer> leftChildrenTempKey = new ArrayList<Integer>();
							List<Integer> rightChildrenTempKey = new ArrayList<Integer>();

							// parentLeftChild set
							leftChildrenTempKey.add(parentNode.getKeyList().get(0));
							leftChildrenTempKey.add(parentNode.getKeyList().get(1));
							parentLeftChild.setKeyList(leftChildrenTempKey);

							leftChildrenTempChild.add(parentNode.getChildren().get(0));
							leftChildrenTempChild.add(parentNode.getChildren().get(1));
							leftChildrenTempChild.add(parentNode.getChildren().get(2));
							parentLeftChild.setChildren(leftChildrenTempChild);

							// leafNode parent, children set
							parentNode.getChildren().get(0).setParent(parentLeftChild);
							parentNode.getChildren().get(1).setParent(parentLeftChild);
							parentNode.getChildren().get(2).setParent(parentLeftChild);

							// parentRightChild set
							rightChildrenTempKey.add(parentNode.getKeyList().get(3));
							rightChildrenTempKey.add(parentNode.getKeyList().get(4));
							rightChildrenTempKey.add(parentNode.getKeyList().get(5));
							parentRightChild.setKeyList(rightChildrenTempKey);

							rightChildrenTempChild.add(parentNode.getChildren().get(3));
							rightChildrenTempChild.add(parentNode.getChildren().get(4));
							rightChildrenTempChild.add(parentNode.getChildren().get(5));
							rightChildrenTempChild.add(parentNode.getChildren().get(6));
							parentRightChild.setChildren(rightChildrenTempChild);

							// leafNode parent, children set
							parentNode.getChildren().get(3).setParent(parentRightChild);
							parentNode.getChildren().get(4).setParent(parentRightChild);
							parentNode.getChildren().get(5).setParent(parentRightChild);
							parentNode.getChildren().get(6).setParent(parentRightChild);

							// parentNode가 root노드일 때
							// parentNode는 부모로 leftChild는 왼쪽 rightChild는 오른쪽
							if (parentNode.getParent() == null) {
								parentLeftChild.setParent(parentNode);
								parentRightChild.setParent(parentNode);
								// parentNode set
								parentTempKey.add(parentNode.getKeyList().get(2));
								parentNode.setKeyList(parentTempKey);
								parentTempChild.add(parentLeftChild);
								parentTempChild.add(parentRightChild);
								parentNode.setChildren(parentTempChild);
							}
							// parentNode가 root노드가 아닐 때(부모가 있을 때)
							// parentNode의 parent를 부모로 leftChild는 왼쪽 rightChild는 오른쪽
							else {
								parentLeftChild.setParent(parentNode.getParent());
								parentRightChild.setParent(parentNode.getParent());

								// parentNode set
								parentTempKey = parentNode.getParent().getKeyList();
								parentTempKey.add(parentNode.getKeyList().get(2));
								Collections.sort(parentTempKey);
								parentNode.getParent().setKeyList(parentTempKey);

								// index의 새로운 탐색을 해야함. 기존 leafNode index로 연산하면 안됨
								for (int i = 0; i < parentNode.getParent().getChildren().size(); i++) {
									if (parentNode.getParent().getChildren().get(i) == parentNode) {
										index = i;
										break;
									}

								}
								parentNode.getParent().getChildren().remove(index);

								// 부모의 자식을 가져와서 조작
								parentTempChild = parentNode.getParent().getChildren();
								parentTempChild.add(index, parentLeftChild);
								parentTempChild.add(index + 1, parentRightChild);
								parentNode.getParent().setChildren(parentTempChild);

							}
							// LeafNodeSplit이 한번 완료되면 false로 바꾼 후 부모르
							leafNodeSplit = false;
							if (parentNode.getParent() != null) {
								parentNode = parentNode.getParent();
							}
							if (parentNode.getKeyList().size() < 6) {
								break;
							}

						}

					}

					return true;

				}
				// MAX_KEY조건 위배하지 않으면 root에 k값 추가하고 끝
				else {
					checkLeafNode.setKeyList(tempKeyList);
					if (leafList.isEmpty()) {
						leafList.add(checkLeafNode);
					} else {
						Iterator<SixWayBPlusTreeNode> iterator = leafList.iterator();
						SixWayBPlusTreeNode templeafList = new SixWayBPlusTreeNode();
						count = 0;
						while (iterator.hasNext()) {
							templeafList = iterator.next();
							if (templeafList == checkLeafNode) {
								leafListIndex = count;
								break;
							}
							count++;

						}
						leafList.set(leafListIndex, checkLeafNode);
					}

					return true;
				}
			}

			// root != leaf node ( root node 가 leaf node가 아닐 때)
			else {

				boolean rightCheck = true;
				// keyList를 순회하면서 e(key)값과 비교합니다.
				for (int i = 0; i < checkLeafNode.getKeyList().size(); i++) {
					// e 가 keyList[i] 보다 작으면 왼쪽 자식으로 이동 하고 rightCheck = false
					if (e < checkLeafNode.getKeyList().get(i)) {
						rightCheck = false;
						checkLeafNode = checkLeafNode.getChildren().get(i);
						index = i;
						leafListIndex = i;
						break;
					}
				}

				// 만약 e가 keyList[i]의 모든 값보다 크다면 rightCheck = true 맨 오른쪽으로 자식으로이동
				if (rightCheck) {
					index = checkLeafNode.getKeyList().size();
					leafListIndex = index;
					checkLeafNode = checkLeafNode.getChildren().get(index);

				}
			}

		}
	}

	@Override
	public boolean remove(Object o) {
		SixWayBPlusTreeNode checkLeafNode = new SixWayBPlusTreeNode();
		SixWayBPlusTreeNode currentLeafNode = new SixWayBPlusTreeNode();
		SixWayBPlusTreeNode parentNode = new SixWayBPlusTreeNode();
		List<Integer> tempKeyList = new ArrayList<Integer>();
		int index = 0;
		int leafListIndex = 0;
		int removeIndex = 0;
		int MIN_KEY = 2;
		checkLeafNode = root;

		while (true) {
			// 삭제는 leafNode에서 발생한다. (자식이 없으면 leafNode)
			if (checkLeafNode.getChildren().size() == 0) {
				currentLeafNode = checkLeafNode;

				// tempKeyList에 currnetLeafNode keyList 가져와서 추가
				tempKeyList = currentLeafNode.getKeyList();

				// key값이 존재하지 않으면 삭제되지않고 key값이 존재하면 삭제되는것 테스트 했음
				if (tempKeyList.contains(o)) {
					// removeIndex에 삭제되는 o의 index 저장
					removeIndex = tempKeyList.indexOf(o);
					// keyList에 데이터 삭제 object o 가 존재하면 삭제함
					tempKeyList.remove(o);
					// 현재노드가 root node라면 부모가 없으니 재귀적으로 탐색할 필요가 없음

					if (currentLeafNode.getParent() != null) {
						parentNode = currentLeafNode.getParent();
						// 만약 LeafNode의 0 데이터가 삭제된다면 부모노드도 바뀌어야 함
						// 단! LeafNode가 LeftList의 0번째 index라면 0번째 데이터가 삭제되어도 부모는 바뀌지 않음
						if (removeIndex == 0) {
							int updateKey = tempKeyList.get(0);
							// 부모를 재귀적으로 올라가면서 값을 교체해줘야 함
							while (true) {
								// 만약 부모노드에 삭제하는 o 데이터가 존재한다면 그 값을 최신값으로 갱신해야 함
								if (parentNode.getKeyList().contains(o)) {
									List<Integer> tempParentKeyList = new ArrayList<Integer>();
									int tempIndex = 0;
									// 삭제할 index를 탐색함
									tempIndex = parentNode.getKeyList().indexOf(o);
									// tempParentList에 parentNode의 keyList를 가져오고 o를 제거
									// 그 후에 updateKey를 삭제된 index인 tempIndex의 위치로 삽임
									tempParentKeyList = parentNode.getKeyList();
									tempParentKeyList.remove(o);
									tempParentKeyList.add(tempIndex, updateKey);
									Collections.sort(tempParentKeyList);
									parentNode.setKeyList(tempParentKeyList);

									break;
								}
								// 부모 노드가 없는 root노드라면 종료
								if (parentNode.getParent() == null) {
									break;
								}
								// 부모노드를 호출하면서 반복함
								else {
									parentNode = parentNode.getParent();
								}

							}
						}
					}
				} else {
					System.out.println(o + "가 존재하지 않음");
					return false;
				}
				// 값을 하나 삭제한 후 부모까지 재귀적으로 변경한 단계

				// MIN_KEY 조건을 위배하는지 검사함 최소 2개값을 가져야 함 그리고 root 노드가 아닌경우
				// root노드는 1개 이상만 값을 가지면 됨
				parentNode = currentLeafNode.getParent();

				if (parentNode != null && tempKeyList.size() < MIN_KEY) {

					List<Integer> tempLsKeyList = new ArrayList<Integer>();
					List<Integer> tempParentKeyList = new ArrayList<Integer>();
					List<Integer> tempRsKeyList = new ArrayList<Integer>();
					SixWayBPlusTreeNode lsNode = new SixWayBPlusTreeNode();
					SixWayBPlusTreeNode rsNode = new SixWayBPlusTreeNode();
					int lsLastIndex = 0;
					int lsLastValue = 0;
					int rsFirstValue = 0;
					// MIN_KEY 조건을 위배하면 merge 발생
					// 1. LS(왼쪽자식) 또는 RS(오른쪽자식)에서 값을 얻어온다
					if (index != 0) {

						// 맨처음 LS검사 LS의 keyList.size()가 3개이상이여야 값을 빌려올 수 있음
						lsNode = parentNode.getChildren().get(index - 1);
						if (lsNode.getKeyList().size() > MIN_KEY) {
							lsLastIndex = lsNode.getKeyList().size() - 1;
							lsLastValue = lsNode.getKeyList().get(lsLastIndex);
							// LS의 keyList저장
							tempLsKeyList = lsNode.getKeyList();

							// LS의 마지막 노드 삭제
							tempLsKeyList.remove(lsLastIndex);
							// LS 업데이트

							// LS의 부모 업데이트
							// LS keyList의 index-1 자리를 lsLastValue로 대체해야 함.
							tempParentKeyList = parentNode.getKeyList();
							tempParentKeyList.remove(index - 1);
							tempParentKeyList.add(index - 1, lsLastValue);
							// 업데이트
							parentNode.setKeyList(tempParentKeyList);

							// 현재노드 0번째 인덱스에 마지막 노드 추가
							tempKeyList.add(0, lsLastValue);

							// leafList update ( LS와 현재노드 모두 변해야 함)
							// LS 변경
							leafListIndex = leafList.indexOf(lsNode);
							// index를 기반으로 LS 제거
							leafList.remove(leafListIndex);

							lsNode.setKeyList(tempLsKeyList);
							leafList.add(leafListIndex, parentNode.getChildren().get(index - 1));

							// 현재노드 변경
							leafListIndex = leafList.indexOf(currentLeafNode);
							// index를 기반으로 currentLeafNode 제거
							leafList.remove(leafListIndex);

							// index를 기반으로 새로운 currentLeafNode 추가
							currentLeafNode.setKeyList(tempKeyList);
							leafList.add(leafListIndex, currentLeafNode);
							// 종료
							return true;
						}
						// 마지막 자식 노드라면 RS에서 빌려올 수 없음( 따라서 if 마지막 자식이 아니라면)
						if (index != parentNode.getChildren().size() - 1) {
							rsNode = parentNode.getChildren().get(index + 1);
							// 두번째 RS검사 RS의 keyList.size()가 3개이상이여야 값을 빌려올 수 있음
							if (rsNode.getKeyList().size() > MIN_KEY) {

								// RS의 처음값을 빌려오며 parentNode에 처음값이 사라지고 RS의 2번째값이 들어감
								rsFirstValue = rsNode.getKeyList().get(0);
								// RS의 keyList저장
								tempRsKeyList = rsNode.getKeyList();

								// RS의 처음 노드 삭제
								tempRsKeyList.remove(0);

								// RS의 부모 업데이트
								// RS keyList의 index를 rsSeceondKey로 대체해야 함.
								tempParentKeyList = parentNode.getKeyList();
								tempParentKeyList.remove(index);
								tempParentKeyList.add(index, tempRsKeyList.get(0));
								// 업데이트
								parentNode.setKeyList(tempParentKeyList);

								// 현재노드에 마지막 노드 추가
								tempKeyList.add(rsFirstValue);

								// leafList update ( RS와 현재노드 모두 변해야 함)
								leafListIndex = leafList.indexOf(rsNode);
								// index를 기반으로 RS 제거
								leafList.remove(leafListIndex);
								rsNode.setKeyList(tempRsKeyList);
								// index 기반으로 새로운 RS 추가
								leafList.add(leafListIndex, rsNode);

								// 현재노드 변경
								leafListIndex = leafList.indexOf(currentLeafNode);
								// index를 기반으로 checkLeafNode 제거
								leafList.remove(leafListIndex);

								// index를 기반으로 새로운 checkLeafNode 추가
								currentLeafNode.setKeyList(tempKeyList);
								leafList.add(leafListIndex, currentLeafNode);

								return true;

							}
						}

					}
					// index == 0 인 경우 RS에서만 빌려올 수 있음
					else {

						// 두번째 RS검사 RS의 keyList.size()가 3개이상이여야 값을 빌려올 수 있음
						if (rsNode.getKeyList().size() > MIN_KEY) {

							// RS의 처음값을 빌려오며 parentNode에 처음값이 사라지고 RS의 2번째값이 들어감
							rsFirstValue = rsNode.getKeyList().get(0);
							// RS의 keyList저장
							tempRsKeyList = rsNode.getKeyList();

							// RS의 처음 노드 삭제
							tempRsKeyList.remove(0);

							// RS의 부모 업데이트
							// RS keyList의 index를 rsSeceondKey로 대체해야 함.
							tempParentKeyList = parentNode.getKeyList();
							tempParentKeyList.remove(index);
							tempParentKeyList.add(index, tempRsKeyList.get(0));
							// 업데이트
							parentNode.setKeyList(tempParentKeyList);

							// 현재노드에 마지막 노드 추가
							tempKeyList.add(rsFirstValue);

							// leafList update ( RS와 현재노드 모두 변해야 함)
							leafListIndex = leafList.indexOf(parentNode.getChildren().get(index + 1));
							// index를 기반으로 RS 제거
							leafList.remove(leafListIndex);
							rsNode.setKeyList(tempRsKeyList);
							// index 기반으로 새로운 RS 추가
							leafList.add(leafListIndex, rsNode);

							// 현재노드 변경
							leafListIndex = leafList.indexOf(currentLeafNode);
							// index를 기반으로 checkLeafNode 제거
							leafList.remove(leafListIndex);

							// index를 기반으로 새로운 checkLeafNode 추가
							currentLeafNode.setKeyList(tempKeyList);
							leafList.add(leafListIndex, currentLeafNode);

							return true;
						}
					}
					// 2. LS또는 RS에서 빌려올 수 없다면
					int prv = 0;
					int plv = 0;
					int leafNodeValue = 0;
					int parentValue = 0;
					List<Integer> tempParentKey = new ArrayList<Integer>();
					List<SixWayBPlusTreeNode> tempGetChild = new ArrayList<SixWayBPlusTreeNode>();

					if (parentNode.getParent() == null && parentNode.getKeyList().size() == 1) {

						lsNode = parentNode.getChildren().get(0);
						rsNode = parentNode.getChildren().get(1);
						tempParentKeyList = lsNode.getKeyList();
						for (int i = 0; i < rsNode.getKeyList().size(); i++) {
							tempParentKeyList.add(rsNode.getKeyList().get(i));
						}
						tempParentKeyList.remove(o);
						parentNode.setChildren(null);
						parentNode.setKeyList(tempParentKeyList);

						// LeafList
						leafListIndex = leafList.indexOf(lsNode);
						leafList.remove(lsNode);

						leafListIndex = leafList.indexOf(rsNode);
						leafList.remove(rsNode);

						leafList.add(parentNode);
						return true;

					}
					// 만약 index가 처음노드라면 LS가 존재하지 않음
					if (index == 0) {
						rsNode = parentNode.getChildren().get(index + 1);
						// leafNodeValue와 RS를 merge 후 부모가 MIN_KEY 조건을 위배하는지 검사
						tempRsKeyList = rsNode.getKeyList();
						// tempKeyList는 remove되어 1개의 값밖에 가지지 않음
						leafNodeValue = tempKeyList.get(0);
						// leafNodeValue + tempRsKeyList 가 부모의 index 번째 자식으로 들어감
						tempRsKeyList.add(0, leafNodeValue);

						// 부모노드의 keyList가 왼쪽으로 한칸씩 당겨지게 됨
						parentValue = parentNode.getKeyList().get(index);
						tempParentKey = parentNode.getKeyList();
						tempParentKey.remove((Object) parentValue);
						parentNode.setKeyList(tempParentKey);

						// 현재자식 setKeyList

						// leafList 처리
						// RS의 index search
						leafListIndex = leafList.indexOf(rsNode);

						// index를 기반으로 RS 제거
						leafList.remove(leafListIndex);

						// 현재 index search
						leafListIndex = leafList.indexOf(currentLeafNode);
						// index를 기반으로 현재노드 제거
						leafList.remove(leafListIndex);
						currentLeafNode.setKeyList(tempRsKeyList);
						// index를 기반으로 새로운 checkLeafNode(RS + 현재 노드) 추가
						leafList.add(leafListIndex, currentLeafNode);

						// RS는 제거되고 index = 0 을 제외한 부모노드의 children도 왼쪽으로 한칸씩 당겨지게 됨
						tempGetChild = parentNode.getChildren();

						// RS제거
						tempGetChild.remove(rsNode);
						// 즉 RS가 제거되면 자동적으로 부모노드의 children이 왼쪽으로 당겨지게 됨
						parentNode.setChildren(tempGetChild);

					}
					// index가 처음노드가 아니라면 LS가 존재함
					else {

						lsNode = parentNode.getChildren().get(index - 1);
						// 우선 LS와 PLV 합친후 부모가 MIN_KEY 조건 위배하는지 검사
						tempLsKeyList = lsNode.getKeyList();
						plv = parentNode.getKeyList().get(index - 1);
						tempLsKeyList.add(tempKeyList.get(0));
						// (tempLsKeyList + plv)가 부모의 index-1번째자식으로 들어감
						tempParentKey = parentNode.getKeyList();
						// 부모노드 plv 삭제
						tempParentKey.remove((Object) plv);
						// 부모노드 keyList update
						parentNode.setKeyList(tempParentKey);
						// 자식노드 update

						// leafList 처리
						// LS의 index search
						leafListIndex = leafList.indexOf(lsNode);
						// index를 기반으로 LS 제거
						leafList.remove(leafListIndex);

						// 현재 index search
						leafListIndex = leafList.indexOf(currentLeafNode);
						// index를 기반으로 현재노드 제거
						leafList.remove(leafListIndex);

						currentLeafNode.setKeyList(tempLsKeyList);
						// index를 기반으로 새로운 checkLeafNode(RS + 현재 노드) 추가
						leafList.add(leafListIndex, currentLeafNode);

						// 부모의 index-1번째 자식이 삭제됨
						tempGetChild = parentNode.getChildren();

						// LS제거(index-1번째 자식)
						tempGetChild.remove(lsNode);
						// 부모 children update
						parentNode.setChildren(tempGetChild);

					}

					// merge 이후에 parentNode가 MIN_KEY 조건을 위배하는지 검사해함
					// parentnOde가 root노드라면 고려하지 않음
					int currentNodeIndex = 0;
					int currnetNodeLastIndex = 0;
					SixWayBPlusTreeNode currentNode = new SixWayBPlusTreeNode();
					SixWayBPlusTreeNode parentNode2 = new SixWayBPlusTreeNode();

					SixWayBPlusTreeNode lsLastChild = new SixWayBPlusTreeNode();
					SixWayBPlusTreeNode rsFirstChild = new SixWayBPlusTreeNode();
					List<SixWayBPlusTreeNode> tempChildList = new ArrayList<SixWayBPlusTreeNode>();
					List<Integer> tempCurrentNodeList = new ArrayList<Integer>();
					List<Integer> tempLsList = new ArrayList<Integer>();
					List<Integer> tempRsList = new ArrayList<Integer>();
					List<Integer> tempParentList = new ArrayList<Integer>();

					int parentValueIndex = 0;

					// 부모노드가 MIN_KEY조건을 위배하는 경우의 수를 찾아보도록 하자
					if (parentNode.getParent() != null && parentNode.getKeyList().size() < MIN_KEY) {
						// System.out.println("부모노드가 MIN_KEY 위배");
						currentNode = parentNode;
						parentNode2 = currentNode.getParent();

						while (true) {
							// 1. LS에서 채워옴 -> return true
							currentNodeIndex = parentNode2.getChildren().indexOf(currentNode);
							currnetNodeLastIndex = parentNode2.getChildren().size() - 1;
							if (currentNodeIndex != 0) {
								lsNode = parentNode2.getChildren().get(currentNodeIndex - 1);
							}
							if (currentNodeIndex != currnetNodeLastIndex) {
								rsNode = parentNode2.getChildren().get(currentNodeIndex + 1);
							}
							if (currentNodeIndex != 0) {
								if (lsNode.getKeyList().size() > MIN_KEY) {
									
									// LS에서빌려옴

									lsLastIndex = lsNode.getKeyList().size() - 1;
									lsLastValue = lsNode.getKeyList().get(lsLastIndex);

									lsLastChild = lsNode.getChildren().get(lsNode.getChildren().size() - 1);

									tempChildList = lsNode.getChildren();
									tempChildList.remove(lsLastChild);

									tempLsList = lsNode.getKeyList();
									tempLsList.remove((Object) lsLastValue);

									parentValue = parentNode2.getKeyList().get(currentNodeIndex - 1);
									tempParentList = parentNode2.getKeyList();
									parentValueIndex = tempParentList.indexOf(parentValue);
									tempParentList.remove(parentValueIndex);
									tempParentList.add(parentValueIndex, lsLastValue);

									// ls update
									lsNode.setChildren(tempChildList);
									lsNode.setKeyList(tempLsList);

									// parent update
									parentNode2.setKeyList(tempParentList);

									// currentNode update
									lsLastChild.setParent(currentNode);

									tempCurrentNodeList = currentNode.getKeyList();
									tempChildList = currentNode.getChildren();

									tempCurrentNodeList.add(0, parentValue);
									tempChildList.add(0, lsLastChild);

									currentNode.setChildren(tempChildList);
									currentNode.setKeyList(tempCurrentNodeList);

									return true;
								}
								// 2. RS에서 채워옴 -> return true
								if (currentNodeIndex != currnetNodeLastIndex) {
									if (rsNode.getKeyList().size() > MIN_KEY) {
										// RS에서빌려옴
										

										rsFirstValue = rsNode.getKeyList().get(0);
										rsFirstChild = rsNode.getChildren().get(0);
										tempChildList = rsNode.getChildren();
										tempChildList.remove(0);

										tempRsList = rsNode.getKeyList();
										tempRsList.remove((Object) rsFirstValue);

										parentValue = parentNode2.getKeyList().get(currentNodeIndex);

										tempParentList = parentNode2.getKeyList();
										parentValueIndex = tempParentList.indexOf(parentValue);

										tempParentList.remove(parentValueIndex);
										tempParentList.add(parentValueIndex, rsFirstValue);

										// rs update
										rsNode.setChildren(tempChildList);
										rsNode.setKeyList(tempRsList);

										// parent update
										parentNode2.setKeyList(tempParentList);

										// currentNode update
										rsFirstChild.setParent(currentNode);

										tempCurrentNodeList = currentNode.getKeyList();
										tempChildList = currentNode.getChildren();

										tempCurrentNodeList.add(parentValue);
										tempChildList.add(rsFirstChild);

										currentNode.setChildren(tempChildList);
										currentNode.setKeyList(tempCurrentNodeList);

										return true;
									}
								}
							}
							// 2. RS에서 채워옴 -> return true
							else {
								if (rsNode.getKeyList().size() > MIN_KEY) {
									// RS에서빌려옴
									
									rsFirstValue = rsNode.getKeyList().get(0);
									rsFirstChild = rsNode.getChildren().get(0);
									tempChildList = rsNode.getChildren();
									tempChildList.remove(0);

									tempRsList = rsNode.getKeyList();
									tempRsList.remove((Object) rsFirstValue);
									parentValue = parentNode2.getKeyList().get((int) currentNodeIndex);

									tempParentList = parentNode2.getKeyList();
									parentValueIndex = tempParentList.indexOf(parentValue);

									tempParentList.remove(parentValueIndex);
									tempParentList.add(parentValueIndex, rsFirstValue);

									// ls update
									rsNode.setChildren(tempChildList);
									rsNode.setKeyList(tempRsList);

									// parent update
									parentNode2.setKeyList(tempParentList);

									// currentNode update
									rsFirstChild.setParent(currentNode);

									tempCurrentNodeList = currentNode.getKeyList();
									tempChildList = currentNode.getChildren();

									tempCurrentNodeList.add(parentValue);
									tempChildList.add(rsFirstChild);

									currentNode.setChildren(tempChildList);
									currentNode.setKeyList(tempCurrentNodeList);

									return true;
								}
							}
							// 3. LS또는 RS에서 못채워오면 부모노드가 root이고 1이면 대통합 -> return true
							if (parentNode2.getParent() == null && parentNode2.getKeyList().size() == 1) {
								// 대통합
								
								List<Integer> tempLeftRightKeyList = new ArrayList<Integer>();
								List<SixWayBPlusTreeNode> tempLeftRightChildList = new ArrayList<SixWayBPlusTreeNode>();
								for (int i = 0; i < parentNode2.getChildren().size(); i++) {
									for (int j = 0; j < parentNode2.getChildren().get(i).getKeyList().size(); j++) {
										tempLeftRightKeyList.add(parentNode2.getChildren().get(i).getKeyList().get(j));
									}
								}
								tempLeftRightKeyList.add(parentNode2.getKeyList().get(0));
								Collections.sort(tempLeftRightKeyList);
								
								
								parentNode2.setKeyList(tempLeftRightKeyList);
								for (int i = 0; i < parentNode2.getChildren().size(); i++) {
									for (int j = 0; j < parentNode2.getChildren().get(i).getChildren().size(); j++) {
										tempLeftRightChildList
												.add(parentNode2.getChildren().get(i).getChildren().get(j));
										parentNode2.getChildren().get(i).getChildren().get(j).setParent(parentNode2);
									}
								}
								parentNode2.setChildren(tempLeftRightChildList);
								return true;
							}
							// 4. LS에서 merge
							if (currentNodeIndex != 0) {
								

								tempLsList = lsNode.getKeyList();
								tempChildList = lsNode.getChildren();

								parentValue = parentNode2.getKeyList().get(currentNodeIndex - 1);
								tempLsList.add(parentValue);

								for (int i = 0; i < currentNode.getKeyList().size(); i++) {
									tempLsList.add(currentNode.getKeyList().get(i));
								}

								Collections.sort(tempLsList);
								// sort test

								for (int i = 0; i < currentNode.getChildren().size(); i++) {
									currentNode.getChildren().get(i).setParent(lsNode);
									tempChildList.add(currentNode.getChildren().get(i));
								}
								lsNode.setKeyList(tempLsList);
								lsNode.setChildren(tempChildList);
								//

								tempChildList = parentNode2.getChildren();
								tempChildList.remove(currentNodeIndex);

								// parentValue delete
								tempParentList = parentNode2.getKeyList();
								tempParentList.remove((Object) parentValue);
								parentNode2.setKeyList(tempParentList);
								parentNode2.setChildren(tempChildList);
							}
							// 5. RS에서 merge
							else {
								
								tempRsList = rsNode.getKeyList();
								tempChildList = rsNode.getChildren();

								parentValue = parentNode2.getKeyList().get(currentNodeIndex);
								tempRsList.add(parentValue);

								for (int i = 0; i < currentNode.getKeyList().size(); i++) {
									tempRsList.add(currentNode.getKeyList().get(i));
								}

								Collections.sort(tempRsList);
								// sort test

								for (int i = 0; i < currentNode.getChildren().size(); i++) {
									currentNode.getChildren().get(i).setParent(rsNode);
									tempChildList.add(i, currentNode.getChildren().get(i));
								}
								rsNode.setKeyList(tempRsList);
								rsNode.setChildren(tempChildList);
								//

								tempChildList = parentNode2.getChildren();
								tempChildList.remove(currentNodeIndex);

								// parentValue delete
								tempParentList = parentNode2.getKeyList();
								tempParentList.remove((Object) parentValue);
								parentNode2.setKeyList(tempParentList);
								parentNode2.setChildren(tempChildList);

							}

							// 6. 부모노드가 MIN_KEY위배안하면 끝
							if (parentNode2.getParent() == null || parentNode2.getKeyList().size() >= MIN_KEY) {
								break;
							}
							// 6. MIN_KEY 위배해서 계속
							else {
								currentNode = parentNode2;
								parentNode2 = currentNode.getParent();
							}
						}

					}
					// 부모노드가 MIN_KEY조건을 위배하지 않는 경우 return true
					else {
						return true;
					}

				}
				// MIN_KEY 조건을 위배하지 않으면 종료
				else {

					// checkLeafNode가 들어가있는 leafList의 index를 찾음
					leafListIndex = leafList.indexOf(currentLeafNode);
					// index를 기반으로 checkLeafNode 제거
					leafList.remove(leafListIndex);

					// index를 기반으로 새로운 checkLeafNode 추가
					currentLeafNode.setKeyList(tempKeyList);
					leafList.add(leafListIndex, currentLeafNode);

					return true;
				}

			} else {
				boolean rightCheck = true;
				// keyList를 순회하면서 e(key)값과 비교합니다.
				for (int i = 0; i < checkLeafNode.getKeyList().size(); i++) {
					// e 가 keyList[i] 보다 작으면 왼쪽 자식으로 이동 하고 rightCheck = false
					// object를 Integer로 형변환
					if ((Integer) o < checkLeafNode.getKeyList().get(i)) {
						rightCheck = false;
						checkLeafNode = checkLeafNode.getChildren().get(i);
						index = i;

						break;
					}
				}

				// 만약 e가 keyList[i]의 모든 값보다 크다면 rightCheck = true 맨 오른쪽으로 자식으로이동
				if (rightCheck) {
					index = checkLeafNode.getChildren().size() - 1;
					checkLeafNode = checkLeafNode.getChildren().get(index);

				}
			}
		}

	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer lower(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer floor(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer ceiling(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer higher(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> iterator() {

		List<Integer> list = new ArrayList<Integer>();
		Iterator<SixWayBPlusTreeNode> iterator = leafList.iterator();
		SixWayBPlusTreeNode temp = new SixWayBPlusTreeNode();
		while (iterator.hasNext()) {
			temp = iterator.next();
			for (int i = 0; i < temp.getKeyList().size(); i++) {
				list.add(temp.getKeyList().get(i));
			}
		}
		Iterator<Integer> intIterator = list.iterator();

		// TODO Auto-generated method stub
		return intIterator;
	}

	@Override
	public NavigableSet<Integer> descendingSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> subSet(Integer fromElement, boolean fromInclusive, Integer toElement,
			boolean toInclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> headSet(Integer toElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> tailSet(Integer fromElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> subSet(Integer fromElement, Integer toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> headSet(Integer toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> tailSet(Integer fromElement) {
		// TODO Auto-generated method stub
		return null;
	}

}
