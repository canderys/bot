package main.statistics;

import java.util.Map;
import java.util.HashMap;

import main.console.Console;
import main.statistics.HeroStatistics;

public class StatisticsParser {
	public static Map<String, HeroStatistics> GetAllHeroesStatistics(String stats)
	{
		Map<String, HeroStatistics> statisticsMap = new HashMap<String, HeroStatistics>();
		System.out.println(stats);
		String[] records = stats.split("null_win");
		for (String record : records)
		{
			HeroStatistics stat = StatisticsParser.ParseRecord(record);
			statisticsMap.put(stat.heroName, stat);
		}
		return statisticsMap;
	}
	
	private static HeroStatistics ParseRecord(String record)
	{
		String[] parameters = record.split(",");
		HeroStatistics result = new HeroStatistics();
		for (String parameter : parameters)
		{
			int index = parameter.indexOf("id\":");
			if (index != -1)
			{
				result.id = Integer.parseInt(parameter.substring(index + 4).trim());
				continue;
			}
			index = parameter.indexOf("_win\":");
			if (index != -1)
			{
				result.winsAmount += Integer.parseInt(parameter.substring(index + 6, parameter.length()).trim());
				continue;
			}
			index = parameter.indexOf("_pick\":");
			if (index != -1)
			{
				int index_null = parameter.indexOf("null_pick");
				if (index_null != -1)
				{
					result.nullMatches += Integer.parseInt(parameter.substring(index + 11, parameter.length()).trim());
					continue;
				}
				result.matchesAmount += Integer.parseInt(parameter.substring(index + 7, parameter.length()).trim());
				continue;
			}
			
			index = parameter.indexOf("localized_name");
			if (index == -1)
				continue;
			result.heroName = parameter.substring(index + 17, parameter.length() - 1);
		}
		return result;
	}
}
