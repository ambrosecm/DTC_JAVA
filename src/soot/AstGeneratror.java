package soot;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.printer.YamlPrinter;
import com.github.javaparser.printer.XmlPrinter;
import com.github.javaparser.printer.DotPrinter;


public class AstGeneratror {

	public ParseResult<CompilationUnit> generate(String filepath) throws Exception {
        
        // 1. java file
        String absolutePath = filepath;
        ParseResult<CompilationUnit> result = new JavaParser().parse(Paths.get(absolutePath));
		System.out.println(result.getResult().get());
        // 2. java code
//        ParseResult<VariableDeclarationExpr> exprResult = new JavaParser().parseVariableDeclarationExpr("int x = 3");
//        System.out.println(exprResult.getResult().get());
//        YamlPrinter printer = new YamlPrinter(true);
//        System.out.println(printer.output(result.getResult().get()));
        
//        YamlPrinter printer = new YamlPrinter(true);
//        System.out.println(printer.output(result.getResult().get()));
//        
//        XmlPrinter printer = new XmlPrinter(true);
//        System.out.println(printer.output(result.getResult().get()));
//        
		String split[]=filepath.split("/");
		String arg1="";
		for(int i=0;i<split.length-2;i++) {
			if(i==split.length-3) {
				arg1=arg1+split[i];
			}else {
				arg1=arg1+split[i]+"/";
			}
			
		}
        DotPrinter printer = new DotPrinter(true);
        try (FileWriter fileWriter = new FileWriter(arg1+"/ast.dot");
            PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(printer.output(result.getResult().get()));
        }
        
        GenerateGraph g=new GenerateGraph();
        g.genera(arg1+"/ast.dot");
        return result;
	}
}
