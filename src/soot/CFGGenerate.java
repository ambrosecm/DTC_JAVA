package soot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soot.Body;
import soot.BodyTransformer;
import soot.G;
import soot.PackManager;
import soot.PhaseOptions;
import soot.SootMethod;
import soot.Transform;
import soot.Unit;
import soot.jimple.JimpleBody;
import soot.options.Options;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.graph.UnitGraph;
import soot.util.cfgcmd.AltClassLoader;
import soot.util.cfgcmd.CFGGraphType;
import soot.util.cfgcmd.CFGIntermediateRep;
import soot.util.cfgcmd.CFGToDotGraph;
import soot.util.dot.DotGraph;


public class CFGGenerate extends BodyTransformer {

	private static final String altClassPathOptionName = "alt-class-path";
	private static final String graphTypeOptionName = "graph-type";
	private static final String defaultGraph = "BriefUnitGraph";
	private static final String irOptionName = "ir";
	private static final String defaultIR = "jimple";
	private static final String multipageOptionName = "multipages";
	private static final String briefLabelOptionName = "brief";

	private CFGGraphType graphtype;
	private CFGIntermediateRep ir;
	private CFGToDotGraph drawer;
	private String path="E:/";
//	private String eclipsepath="E:\\software\\eclipse4.16\\eclipse\\";
	private String eclipsepath="E:\\software\\eclipse4.16\\eclipse\\";

	public void setPath(String path1) {
		path=path1;
	}
	protected void internalTransform(Body b, String phaseName, Map options) {
		initialize(options);
		System.out.println(options);
		System.out.println(b);
		Body body = ir.getBody((JimpleBody) b);
		//System.out.println(body);
		print_cfg(body);
		
	}

	public void generate(String filepath) {
//		try {
//			Runtime.getRuntime().exec("cmd /c rmdir /Q /S "+eclipsepath+"sootOutput");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		String split[]=filepath.split("/");
		String arg1="";
		for(int i=0;i<split.length-2;i++) {
				arg1=arg1+split[i]+"/";
		}
		String split2[]=split[split.length-1].split("\\.");
		String arg2=split[split.length-2]+"."+split2[0];
		
		CFGGenerate viewer = new CFGGenerate();
		viewer.setPath(arg1);
		Transform printTransform = new Transform("jtp.printcfg", viewer);
		printTransform.setDeclaredOptions("enabled " + altClassPathOptionName + ' ' + graphTypeOptionName + ' '
				+ irOptionName + ' ' + multipageOptionName + ' ' + briefLabelOptionName + ' ');
		printTransform.setDefaultOptions("enabled " + altClassPathOptionName + ": " + graphTypeOptionName + ':'
				+ defaultGraph + ' ' + irOptionName + ':' + defaultIR + ' ' + multipageOptionName + ":false " + ' '
				+ briefLabelOptionName + ":false ");
		PackManager.v().getPack("jtp").add(printTransform);
		//args = viewer.parse_options(args);
		String[] soot_args = new String[3];
	    //soot_args[0] = "-cp";
	    soot_args[0] = "--soot-classpath";
	    soot_args[1] = arg1+";E:\\Tools\\JDK\\jdk1.7.0_80\\jre\\lib\\rt.jar";
	    soot_args[2] = arg2;
	    
	    
		soot.Main.main(soot_args);
		
		GenerateGraph g=new GenerateGraph();

		g.genera(path+"cfg.dot");
		
		
		
	}

	private void initialize(Map<String, String> options) {
		if (drawer == null) {
			drawer = new CFGToDotGraph();
			drawer.setBriefLabels(PhaseOptions.getBoolean(options, briefLabelOptionName));
			drawer.setOnePage(!PhaseOptions.getBoolean(options, multipageOptionName));
			drawer.setUnexceptionalControlFlowAttr("color", "black");
			drawer.setExceptionalControlFlowAttr("color", "red");
			drawer.setExceptionEdgeAttr("color", "lightgray");
			drawer.setShowExceptions(Options.v().show_exception_dests());
			ir = CFGIntermediateRep.getIR(PhaseOptions.getString(options, irOptionName));
			graphtype = CFGGraphType.getGraphType(PhaseOptions.getString(options, graphTypeOptionName));

			AltClassLoader.v().setAltClassPath(PhaseOptions.getString(options, altClassPathOptionName));
			AltClassLoader.v().setAltClasses(
					new String[] { "soot.toolkits.graph.ArrayRefBlockGraph", "soot.toolkits.graph.Block",
							"soot.toolkits.graph.Block$AllMapTo", "soot.toolkits.graph.BlockGraph",
							"soot.toolkits.graph.BriefBlockGraph", "soot.toolkits.graph.BriefUnitGraph",
							"soot.toolkits.graph.CompleteBlockGraph", "soot.toolkits.graph.CompleteUnitGraph",
							"soot.toolkits.graph.TrapUnitGraph", "soot.toolkits.graph.UnitGraph",
							"soot.toolkits.graph.ZonedBlockGraph", });
		}
	}

	protected void print_cfg(Body body) {
		DirectedGraph<Unit> graph = graphtype.buildGraph(body);
		//System.out.println(graph);
		DotGraph canvas = graphtype.drawGraph(drawer, graph, body);
		//GenerateFlow gen = new GenerateFlow((UnitGraph) graph);
		
		
		
		String methodname = body.getMethod().getSubSignature();
		String classname = body.getMethod().getDeclaringClass().getName().replaceAll("\\$", "\\.");
		String filename = soot.SourceLocator.v().getOutputDir();
		if (filename.length() > 0) {
			filename = filename + java.io.File.separator;
		}
		String pathname = filename + classname + " " + methodname.replace(java.io.File.separatorChar, '.') + DotGraph.DOT_EXTENSION;

		G.v().out.println("Generate dot file in " + pathname);
		canvas.plot(pathname);
		
		try {
			Runtime.getRuntime().exec("cmd /c copy "+eclipsepath+filename+classname+"*.dot "+path+" && "
					+ "ren "+path+filename+classname+"*.dot cfg.dot");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}