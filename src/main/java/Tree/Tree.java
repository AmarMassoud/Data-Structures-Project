package Tree;


import java.io.Serializable;
import java.util.ArrayList;

public class Tree<E> implements Serializable {
	private Node<E> root;
	public E search(int k)
	{
		if(root == null) return null;
		Node<E> current=root;
		while(current.key!=k)
		{
			if (k<current.key)
				current=current.leftChild;
			else
				current=current.rightChild;
			if(current==null)
				return null;
		}
		return current.data;
	}


	public void insert(int k, E e)
	{
		Node<E> newNode = new Node(k,e); 
		if(root==null) 
			root = newNode;
		else 
		{
			Node current = root; 
			Node parent;
			while(true) 
			{
				parent = current;
				if(k < current.key)
				{
					current = current.leftChild;
					if(current == null) 
					{ 
						parent.leftChild = newNode;
						return;
					}
				} 
				else
				{
					current = current.rightChild;
					if(current == null) 
					{ 
						parent.rightChild = newNode;
						return;
					}
				} 
			} 
		} 
	}

	public boolean delete(int k)
	{
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		while(current.key!= k) 
		{
			parent = current;
			if(k < current.key) 
			{
				isLeftChild = true;
				current = current.leftChild;
			}
			else 
			{
				isLeftChild = false;
				current = current.rightChild;
			}
			if(current == null) 
				return false; 
		}
		if(current.leftChild==null &&current.rightChild==null)
		{
			if(current == root) 
				root = null; 
			else 
				if(isLeftChild)
					parent.leftChild = null; 
				else 
					parent.rightChild = null;
		}
		else 
			if(current.rightChild==null)
				if(current == root)
					root = current.leftChild;
				else 
					if(isLeftChild) 
						parent.leftChild = current.leftChild;
					else // right child of parent
						parent.rightChild = current.leftChild;
			else if(current.leftChild==null)
				if(current == root)
					root = current.rightChild;
				else 
					if(isLeftChild) // left child of parent
						parent.leftChild = current.rightChild;
					else // right child of parent
						parent.rightChild = current.rightChild;
			else 
			{

				Node successor = getSuccessor(current);
				if(current == root)
					root = successor;
				else if(isLeftChild)
					parent.leftChild = successor;
				else
					parent.rightChild = successor;
				successor.leftChild = current.leftChild;
			} 
		return true;

	}
	private Node<E> getSuccessor(Node<E> delNode)
	{
		Node<E> successorParent = delNode;
		Node<E> successor = delNode;
		Node<E> current = delNode.rightChild; // go to right child
		while(current != null) // until no more
		{ // left children,
			successorParent = successor;
			successor = current;
			current = current.leftChild; // go to left child
		}
		// if successor not
		if(successor != delNode.rightChild) // right child,
		{ // make connections
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

	public void traverse(int traverseType)
	{
		switch (traverseType)
		{
		case 1: preorder(root); break;
		case 2: inorder(root);  break;
		case 3: postorder(root); break;
		}

	}
	public void preorder(Node<E> n)
	{
		if(n==null)
			return;
		else
		{
			n.display();
			preorder(n.leftChild);
			preorder(n.rightChild);
		}

	}
	public void inorder(Node<E> n)
	{
		if(n==null)
			return;
		else
		{
			inorder(n.leftChild);
			n.display();
			inorder(n.rightChild);
		}

	}
	public void postorder(Node<E> n)
	{
		if(n==null)
			return;
		else
		{
			postorder(n.leftChild);
			postorder(n.rightChild);
			n.display();
		}
	}

	public int max(){
		Node<E> current=root;
		while (current.rightChild!=null)
			current=current.rightChild;
		return current.key;
	}

	public int min(){
		Node<E> current=root;
		while (current.leftChild!=null)
			current=current.leftChild;
		return current.key;
	}

	public int depth(int k){
		int d=0;
		Node<E> current=root;

		while(current.key!=k){
			if (k<current.key)
				current=current.leftChild;
			else
				current=current.rightChild;
			d=d+1;
			if(current==null)
				return -1;
		}
		return d;
	}

	public void printLeaves(){
		printAllLeaves(root);
	}

	private void printAllLeaves(Node n){
		if (n!=null)
			if (n.leftChild==null && n.rightChild==null)
				n.display();
			else
			{
				printAllLeaves(n.leftChild);
				printAllLeaves(n.rightChild);
			}
	}

	public int count(){
		return countNodes(root);
	}

	private int countNodes(Node n){
		if (n==null)
			return 0;
		else
			return 1+countNodes(n.leftChild)+countNodes(n.rightChild);
	}

	public int countLeaves(){
		return countAllLeaves(root);
	}

	private int countAllLeaves(Node<E> n){
		if (n!=null)
			if (n.leftChild==null && n.rightChild==null)
				return 1;
			else
				return countAllLeaves(n.leftChild)+countAllLeaves(n.rightChild);
		return 0;
	}

	public int treeHeight(){
		return nodeHeight(root)-1;

	}

	private int nodeHeight(Node<E> n){
		if (n==null)
			return 0;
		else
			return 1+Math.max(nodeHeight(n.leftChild),nodeHeight(n.rightChild));
	}

	public boolean isFull(){
		return checkFull(root);
	}

	private boolean checkFull(Node<E> n){
		if (n.leftChild==null && n.rightChild==null)
			return true;
		if (n.leftChild!=null && n.rightChild!=null)
			return checkFull(n.leftChild) && checkFull(n.rightChild);
		return false;
	}


	public ArrayList<E> getAllStudents() {
		ArrayList<E> elements = new ArrayList<>();
		inOrderTraversal(root, elements);
		return elements;
	}
	private void inOrderTraversal(Node<E> node, ArrayList<E> elements) {
		if (node != null) {
			inOrderTraversal(node.leftChild, elements);
			elements.add(node.data);
			inOrderTraversal(node.rightChild, elements);
		}
	}



}