package main.statisticsLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import main.console.Console;

public class StatisticsLoader {
	public static void main(String[] args)
	{
		String path = "https://api.opendota.com/api/heroStats";
		
		HttpURLConnection connection = null;
		StringBuilder s = new StringBuilder();
		
		try 
		{
			connection = (HttpURLConnection) new URL(path).openConnection();
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setConnectTimeout(2000);
			connection.setReadTimeout(2000);
			
			connection.connect();
			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String line;
				while ((line = reader.readLine()) != null)
				{
					s.append(line);
					s.append("\n");
				}
				Console.Print(s.toString());
			}
			else
			{
				Console.Print("Error: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
			}
		} 
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		finally 
		{
			if (connection != null)
				connection.disconnect();
		}
	}
}
