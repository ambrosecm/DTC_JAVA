package soot;


import java.util.List;

import facade.JavaDataFlow;
import facade.StaticJavaDataFlow;
import model.DataFlowMethod;
import model.DataFlowNode;


public class DataFlowGraph {

	public model.DataFlowGraph generate(String filepath) {
		
		String split[]=filepath.split("/");
		String arg1="";
		for(int i=0;i<split.length-2;i++) {
				arg1=arg1+split[i]+"/";
		}
		String arg2=split[split.length-2]+"/"+split[split.length-1];
		String Path1 = arg1;

	    String inputClass = arg2;
		
		// TODO Change this to the exact location of the project (where the .git file is located).
//	    String Path1 = "E:\\Project\\eclipse_project\\DTC_JAVA\\src\\\\";
//
//	    String inputClass = "example\\Example1.java";
	    
	    StaticJavaDataFlow.getConfig().setProjectPaths(Path1);
	    model.DataFlowGraph dfg = JavaDataFlow.create(Path1 + inputClass);

//	    System.out.println("================Example getting all inputs for given Node name================");
//	    String nodeName = "getA";
//	    DataFlowMethod getA = dfg.getMethods().stream().filter(m -> m.getName().equals(nodeName)).findFirst().get();
//	    List<DataFlowNode> inputNodes = getA.getReturnNode().get().walkBackUntil(DataFlowNode::isInputParameter, dfg::owns);
//	    System.out.println(inputNodes.get(0).getName());

	    System.out.println("================Printing all fields in the DFG================");
	    System.out.println(dfg);
	    return dfg;
	}
	
	
}
