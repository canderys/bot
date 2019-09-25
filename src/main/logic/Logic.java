package main.logic;

import java.util.Scanner;

import main.console.Console;
import main.questions.Generator;
import main.questions.Question;

public class Logic {

	public static void main(String[] args)
	{
		Console.Print("hello");
		while(true)
		{
			Scanner in = new Scanner(System.in);
			Generator generator  = new Generator("resourses\\questions.txt");
			for(Question question : generator.quetions)
			{
				Console.Print(question.Text);
				question.Answer = in.nextLine();
			}
			in.close();
		}
	}
}
