package soot;

import java.util.LinkedList;

public class TreeNode {
	
	String data;  
    TreeNode parent;  
    LinkedList<TreeNode> childlist;  
      
    TreeNode() {  
        data = null;
        parent = null;
        childlist = new LinkedList<TreeNode>();    
    }
}
