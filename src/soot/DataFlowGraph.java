package soot;

import java.util.Collection;
import java.util.List;


public class DataFlowGraph {

	public static void main(String[] args) {
		String systemPath = "C:\\gitrepo\\JavaDataFlowExample\\";
		System.out.println(
				"================Don't forget to change your project path (" + systemPath + ")================");

		String projectPath = "JavaDataFlowExample\\src\\main\\java\\";
		String inputClass = "example/Example1.java";
		StaticJavaDataFlow.getConfig().setProjectPaths(systemPath + projectPath);
		model.DataFlowGraph dfg = JavaDataFlow.create(systemPath + projectPath + inputClass);

		System.out.println("================Example getting all inputs for given Node name================");
		String nodeName = "getA";
		DataFlowMethod getA = ((Collection<DataFlowMethod>) dfg.getMethods()).stream().filter(m -> m.getName().equals(nodeName)).findFirst().get();
		List<DataFlowNode> inputNodes = getA.getReturnNode().get().walkBackUntil(DataFlowNode::isInputParameter,
				dfg::owns);
		System.out.println(inputNodes.get(0).getName());

		System.out.println("================Printing all fields in the DFG================");
		System.out.println(dfg);

	}
}
