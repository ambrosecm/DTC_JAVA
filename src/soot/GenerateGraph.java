package soot;

import java.io.IOException;

public class GenerateGraph {
	public void genera(String filepath) {
		String split[]=filepath.split("/");
		String arg1="";
		for(int i=0;i<split.length-1;i++) {
			arg1=arg1+split[i]+"/";
		}
		String s[]=split[split.length-1].split("\\.");
		arg1=arg1+s[0];
		try {
			Runtime.getRuntime().exec("cmd /c dot -Tpng "+filepath+" -o "+arg1+".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
