package main.logic;

import java.util.Scanner;

import main.console.Console;
import main.questions.Generator;
import main.questions.Question;

public class Logic {

	public static void main(String[] args)
	{ 
		Console.Print("hello");
		Generator generator  = new Generator("resourses\\questions.txt");
		Scanner in = new Scanner(System.in);
		int index = 0;
		while(true)
		{
			Question question = generator.quetions.get(index);
			Console.Print(question.Text);
			question.Answer = in.nextLine();
			if(question.Answer.equals("end"))
				break;
			index = (index + 1) % generator.quetions.size();
		}
		in.close();
	}
}