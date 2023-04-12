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

	public static void main(String[] args) throws Exception {
		
        
        // 1. 转换的是完整的Java文件
        String absolutePath = "E:\\Project\\GitHub\\DTC_JAVA\\src\\TriangleClass\\Triangle.java";
        ParseResult<CompilationUnit> result = new JavaParser().parse(Paths.get(absolutePath));
		System.out.println(result.getResult().get());
        // 2. 转换的为部分代码片段
//        ParseResult<VariableDeclarationExpr> exprResult = new JavaParser().parseVariableDeclarationExpr("int x = 3");
//        System.out.println(exprResult.getResult().get());
//        YamlPrinter printer = new YamlPrinter(true);
//        System.out.println(printer.output(exprResult.getResult().get()));
        
//        YamlPrinter printer = new YamlPrinter(true);
//        System.out.println(printer.output());
//        
//        XmlPrinter printer = new XmlPrinter(true);
//        System.out.println(printer.output());
//        
//        DotPrinter printer = new DotPrinter(true);
//        try (FileWriter fileWriter = new FileWriter("ast.dot");
//            PrintWriter printWriter = new PrintWriter(fileWriter)) {
//            printWriter.print(printer.output());
//        }
	}
}
