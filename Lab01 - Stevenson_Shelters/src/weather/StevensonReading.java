package weather;

import java.util.Objects;


/**
 * Each instance of this class represents a Stevenson reading taken from a Stevenson Shelter.
 */
public final class StevensonReading implements WeatherReading {
  private final double T;
  private final double D;
  private final double v;
  private final int totalRain;
  private final double R;
  private final double HI;
  private final double windChill;

  /**
   * Constructs a StevensonReading object and initializes below parameters to given values.
   *
   * @param airTemp - Air Temperature in Celsius.
   * @param dewpointTemp - Dew Point Temperature.
   * @param windSpeed - Wind Speed in miles/hr.
   * @param rain - Total amount of Rain in mm.
   */
  public StevensonReading(double airTemp, double dewpointTemp, double windSpeed, int rain) {
    if (dewpointTemp < airTemp && windSpeed >= 0 && rain >= 0) {
      this.T = airTemp;
      this.D = dewpointTemp;
      this.v = windSpeed;
      this.totalRain = rain;
    }
    else {
      throw new IllegalArgumentException();
    }
    this.R = 5 * (D - T) + 100;
    if (this.R < 0 || this.R > 100) {
      throw new IllegalArgumentException();
    }
    this.HI = -8.78469475556 + 1.61139411 * T + 2.33854883889 * R + -0.14611605 * T * R
            + -0.012308094 * (Math.pow(T, 2)) + -0.0164248277778 * (Math.pow(R, 2))
            + 0.002211732 * Math.pow(T, 2) * R + 0.00072546 * T * Math.pow(R, 2)
            + -0.000003582 * Math.pow(T, 2) * Math.pow(R, 2);
    this.windChill = 35.74 + 0.6215 * (convertCtoF(T)) - 35.75 * (Math.pow(v, 0.16))
            + 0.4275 * (convertCtoF(T)) * (Math.pow(v, 0.16));
  }

  /**
   * Get the temperature of this reading in Celsius and convert it to Fahrenheit.
   * Temperature is required in Fahrenheit for calculating Wind Chill.
   *
   * @return the temperature (Fahrenheit).
   */
  private static double convertCtoF(double temperature) {
    return (temperature * 1.8) + 32;
  }


  /**
   * To check if two objects are equal or not.
   *
   * @param o - StevensonReading Object.
   *
   * @return a boolean value (True/False).
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StevensonReading that = (StevensonReading) o;
    return Double.compare(that.T, T) == 0 && Double.compare(that.D, D) == 0
            && Double.compare(that.v, v) == 0 && totalRain == that.totalRain
            && Double.compare(that.R, R) == 0 && Double.compare(that.HI, HI) == 0
            && Double.compare(that.windChill, windChill) == 0;
  }

  /**
   * Returns the hashCode of two objects.
   *
   * @return if values of two objects are same or different.
   */
  @Override
  public int hashCode() {
    return Objects.hash(T, D, v, totalRain, R, HI, windChill);
  }

  /**
   * Returns the string representation of all the variables in the class.
   *
   * @return a string.
   */
  @Override
  public String toString() {
    return "Reading: "
            + "T = " + (int) Math.round(T)
            + ", D = " + (int) Math.round(D)
            + ", v = " + (int) Math.round(v)
            + ", rain = " + totalRain;
  }

  /**
   * Get the temperature of this reading rounded to the nearest integer.
   *
   * @return the temperature
   */
  @Override
  public int getTemperature() {
    return (int) Math.round(T);
  }

  /**
   * Get the dew point for this reading rounded to the nearest integer.
   *
   * @return the dew point
   */
  @Override
  public int getDewPoint() {
    return (int) Math.round(D);
  }

  /**
   * Get the wind speed for this reading rounded to the nearest integer.
   *
   * @return the wind speed
   */
  @Override
  public int getWindSpeed() {
    return (int) Math.round(v);
  }

  /**
   * Get the total rain of this reading (in mm).
   *
   * @return the total rain
   */
  @Override
  public int getTotalRain() {
    return totalRain;
  }

  /**
   * Get the relative humidity of this weather reading rounded to the nearest
   * integer.
   *
   * @return the relative humidity
   */
  @Override
  public int getRelativeHumidity() {
    return (int) Math.round(R);
  }

  /**
   * Get the heat index for this weather reading rounded to the nearest integer.
   *
   * @return the heat index
   */
  @Override
  public int getHeatIndex() {
    return (int) Math.round(HI);
  }

  /**
   * Get the wind chill rounded to the nearest integer.
   *
   * @return the wind chill
   */
  @Override
  public int getWindChill() {
    return (int) Math.round(windChill);
  }

}
