package defaultxml;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import org.jdom2.JDOMException;

import ch.makery.address.model.Person;
import defaultxml.HandleXML;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * siehe:
 * http://cslibrary.stanford.edu/110/BinaryTrees.html
 * http://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/
 * http://www.code2learn.com/2013/02/binary-search-tree-bst-algorithm.html
 * http://javarevisited.blogspot.de/2015/10/how-to-implement-binary-search-tree-in-java-example.html
 */

public class Searchtree{

	private static Node root;

	private static class Node {
		Node left;
		Node right;
		Person person;

		Node(Person newPerson) {
			left = null;
			right = null;
			person = newPerson;
		}
	}

	public Node getRootNode() {
		return root;
	}
	
	public void setRootNode(Node node) {
		root = node;
	}
	
	
	public Searchtree() {
		root = null;
	}

	public Person search(Person person) {
		return search(root, person);
	}
	
	public Person search(String pn) {
		Person person = new Person("","","",pn);
		return search(root, person);
	}
	
	private Person search(Node node, Person person) {
		Person gefunden=null;
		if (person.compareTo(node.person)==0) {
			gefunden = node.person;
		}
		else if (person.compareTo(node.person)<0 && node.left!=null) {
			return(search(node.left, person));
		}
		else if (node.right!=null) {
			return(search(node.right, person));
		}
		return gefunden;
	}
	
	public void insert (Person person) {
		root = insert(root, person);
	}
	
	private Node insert(Node node, Person person) {
		if (node==null) {
			node = new Node(person);
		}
		else {
			if (person.compareTo(node.person)<=0) {
				node.left = insert(node.left, person);
			} else {
				node.right = insert(node.right, person);
			}
		}
		return(node);
	}

	public void print() {
		Node node = root;
		if (node.left==null&&node.right==null&&node.person==null) {
				System.out.println("Diese Liste ist leer!");
		} else {
			print(root);
		}
	}
	
	public void print(Node node) {
		// ueberfluessig? Person person = null;
		if (node.left instanceof Node) {
			print(node.left);
		}
		HandleArrayList.printObject(node.person);
		if (node.right instanceof Node) {
			print(node.right);
		}
		// ueberfluessig? HandleArrayList.printObject(person);
	}

	public ArrayList<Object> search(ArrayList<String> zuSuchen) {
		ArrayList<Object> gefunden = new ArrayList<Object>();
		for (int i = 0; i<zuSuchen.size(); i++) {
			gefunden.add(search(zuSuchen.get(i)));
		}
		return gefunden;
	}

	public ArrayList<Object> zuArrayList() {
		ArrayList<Object> arrayList = new ArrayList<Object>();
		Node node = root;
		if (node.left==null&&node.right==null&&node.person==null) {
			System.out.println("Diese Liste ist leer!");
	} else {
		getObjects(arrayList, root);
	}
		return arrayList;
	}
	
	public void getObjects(ArrayList<Object> arrayList, Node node) {
		// überflüssig? Person person = null;
		if (node.left instanceof Node) {
			getObjects(arrayList, node.left);
		}
		arrayList.add((Object)node.person);
		if (node.right instanceof Node) {
			getObjects(arrayList, node.right);
		}
		// überflüssig? HandleArrayList.inArrayListAnhaengen(arrayList, person);
	}

	public void clear() {
		root = null;
		root.left = null;
		root.right = null;
	}
}