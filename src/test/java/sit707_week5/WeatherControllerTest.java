package sit707_week5;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class WeatherControllerTest {

	@Test
	public void testStudentIdentity() {
		String studentId = "224714265";
		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "Nimmi Dubey";
		Assert.assertNotNull("Student name is null", studentName);
	}

	@Test
	public void testTemperatureMin() {
		System.out.println("+++ testTemperatureMin +++");

		// Arrange
		WeatherController wController = WeatherController.getInstance();

		// Act
		int nHours = wController.getTotalHours();
		double minTemperature = 1000;

		for (int i = 0; i < nHours; i++) {
			double temperatureVal = wController.getTemperatureForHour(i + 1);

			if (minTemperature > temperatureVal) {
				minTemperature = temperatureVal;
			}
		}

		// Assert
		Assert.assertTrue(
				wController.getTemperatureMinFromCache() == minTemperature);

		// Cleanup
		wController.close();
	}

	@Test
	public void testTemperatureMax() {
		System.out.println("+++ testTemperatureMax +++");

		// Arrange
		WeatherController wController = WeatherController.getInstance();

		// Act
		int nHours = wController.getTotalHours();
		double maxTemperature = -1;

		for (int i = 0; i < nHours; i++) {
			double temperatureVal = wController.getTemperatureForHour(i + 1);

			if (maxTemperature < temperatureVal) {
				maxTemperature = temperatureVal;
			}
		}

		// Assert
		Assert.assertTrue(
				wController.getTemperatureMaxFromCache() == maxTemperature);

		// Cleanup
		wController.close();
	}

	@Test
	public void testTemperatureAverage() {
		System.out.println("+++ testTemperatureAverage +++");

		// Arrange
		WeatherController wController = WeatherController.getInstance();

		// Act
		int nHours = wController.getTotalHours();
		double sumTemp = 0;

		for (int i = 0; i < nHours; i++) {
			double temperatureVal = wController.getTemperatureForHour(i + 1);
			sumTemp += temperatureVal;
		}

		double averageTemp = sumTemp / nHours;

		// Assert
		Assert.assertTrue(
				wController.getTemperatureAverageFromCache() == averageTemp);

		// Cleanup
		wController.close();
	}

	@Test
	public void testTemperaturePersist() {

		System.out.println("+++ testTemperaturePersist +++");

		// Arrange
		WeatherController wController = WeatherController.getInstance();

		// Act
		String persistTime = wController.persistTemperature(10, 19.5);

		/*
		 * To make the test repeatable and avoid failure due to small
		 * execution delays, only compare hour and minute instead of
		 * exact seconds.
		 */
		String now = new SimpleDateFormat("H:m").format(new Date());

		System.out.println("Persist time: " + persistTime + ", now: " + now);

		// Assert
		Assert.assertTrue(persistTime.startsWith(now));

		// Cleanup
		wController.close();
	}
}