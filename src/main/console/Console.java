package main.console;
import java.util.Scanner;

public class Console {
	public static void Print(String s)
	{
		System.out.println(s);
	}
	
	public static void Print(int s)
	{
		System.out.println(Integer.toString(s));
	}
	
	public static String Read()
	{
		Scanner in = new Scanner(System.in);
		String a = in.next();
		in.close();
		return a;
	}
}
