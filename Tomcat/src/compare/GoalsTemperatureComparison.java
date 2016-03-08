package compare;

import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;

import domain.DataSource;
import domain.FootballGoalsSource;
import domain.TemperatureSource;

public class GoalsTemperatureComparison {

	DataSource goals;
	DataSource temperatures;
	DataCollectionBuilder builder;
	DataCollection result;

	public GoalsTemperatureComparison() {
		goals = new FootballGoalsSource();
		temperatures = new TemperatureSource();
		builder = new DataCollectionBuilder(goals, temperatures, Resolution.DAY);
		result = builder.getResult();
	}

	public String getComparedData() {
		String s = "{";
		for (Entry<String, MatchedDataPair> entry : result.getData().entrySet()) {
			s += "\"matchning\":" + " {\"date\":    " + entry.getKey()  + ",\"goals\":   " + entry.getValue().getXValue() + ",\"degrees\": " + entry.getValue().getYValue() + "},";
		}
		return s + " }";
	}

}
