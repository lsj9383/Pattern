package ThreadSpeciicStorageDemo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TSLog {
	private PrintWriter writer = null;
	
	public TSLog(String filename){
		try {
			writer = new PrintWriter(new FileWriter(filename));
		} catch (IOException e) {}
	}
	
	public void println(String s){
		writer.println(s);
	}
	
	public void close()
	{
		writer.close();
	}
}
