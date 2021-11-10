package transmission;

/**
 * Your car uses an Automatic Transmission system.
 */
public class AutomaticTransmission implements Transmission {
  private final int treshold1;
  private final int treshold2;
  private final int treshold3;
  private final int treshold4;
  private final int treshold5;

  private int speed = 0;
  private int gear = 0;

  /**
   * Intializes speed and gear variables.
   *
   * @param t1 - Treshold to change from gear 1 to gear 2.
   * @param t2 - Treshold to change from gear 2 to gear 3.
   * @param t3 - Treshold to change from gear 3 to gear 4.
   * @param t4 - Treshold to change from gear 4 to gear 5.
   * @param t5 - Treshold to change from gear 5 to gear 6.
   * @throws IllegalArgumentException for illegal values.
   */
  public AutomaticTransmission(int t1, int t2, int t3, int t4, int t5) throws
          IllegalArgumentException {
    if ( t1 > 0 && t2 > t1 && t3 > t2 && t4 > t3 && t5 > t4 ) {
      this.treshold1 = t1;
      this.treshold2 = t2;
      this.treshold3 = t3;
      this.treshold4 = t4;
      this.treshold5 = t5;
    }
    else {
      throw new IllegalArgumentException();
    }
  }

  private void changeGear(int s) {
    if (s >= treshold5) {
      this.gear = 6;
    }  else if (s >= treshold4) {
      this.gear = 5;
    } else if (s >= treshold3) {
      this.gear = 4;
    } else if (s >= treshold2) {
      this.gear = 3;
    } else if (s >= treshold1) {
      this.gear = 2;
    } else if (s > 0) {
      this.gear = 1;
    } else {
      this.gear = 0;
    }
  }

  /**
   * toString() to return a string with requested information.
   *
   * @return a string with information.
   */
  @Override
  public String toString() {
    return String.format("Transmission (speed = %d, gear = %d)", this.speed, this.gear);
  }

  /**
   * Increases the speed and gear of the car according to the conditions specified.
   */
  @Override
  public void increaseSpeed() {
    if (this.speed == 0) {
      this.gear += 1;
      this.speed += 1;
    } else if (this.gear == 6) {
      this.speed += 1;
    } else {
      changeGear(this.speed + 1);
      this.speed += 1;
    }
  }

  /**
   * Decreases the speed of the car and changes gear according to specified conditions.
   *
   * @throws IllegalStateException if there is an illegal state.
   */
  @Override
  public void decreaseSpeed() throws IllegalStateException {
    if (this.speed == 0) {
      throw new IllegalStateException("Speed should not be less than 0.");
    } else {
      this.speed -= 1;
      changeGear(this.speed);
    }
  }

  /**
   * To get the speed of the car.
   *
   * @return the speed of the car.
   */
  @Override
  public int getSpeed() {
    return this.speed;
  }

  /**
   * To get the gear the car is in.
   *
   * @return the gear of the car.
   */
  @Override
  public int getGear() {
    return this.gear;
  }
}