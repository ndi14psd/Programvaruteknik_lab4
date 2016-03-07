package compare;

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
		StringBuilder builder = new StringBuilder();
		result.getData().forEach((date, match) -> {
			builder.append(date + " # " + result.getXUnit() + ": " + match.getXValue() + " - " + result.getYUnit() + ": "
					+ match.getYValue());
		});
		return builder.toString();
	}

}
