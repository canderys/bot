package main.statistics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import main.console.Console;
import main.statistics.HeroStatistics;
import main.statistics.StatisticsParser;
import java.util.Map;

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
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			
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
				
				Map<String, HeroStatistics> heroStat = StatisticsParser.GetAllHeroesStatistics(s.toString());
				heroStat.remove(null);
				for (String key : heroStat.keySet())
				{
					Console.Print(heroStat.get(key).id + ": " + key + ". Winrate: " + Math.floor((double)heroStat.get(key).winsAmount / (double)heroStat.get(key).matchesAmount * 10000) / 100 + "%");
				}
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
