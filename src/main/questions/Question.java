package main.questions;

public class Question {
	public String Text;
	public String Answer;
	
	public Question(String text)
	{
		Text = text;
	}
	public boolean equals(Question anotherQuestion)
	{
		return this.Text == anotherQuestion.Text;
	}
}
