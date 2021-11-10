package gear;

/**
 * Size options for belts worn by the player as part of the gear.
 */
enum BeltSize {
  SMALL(1), MEDIUM(2), LARGE(4);

  private final int units;

  BeltSize(final int units) {
    this.units = units;
  }

  /**
   * Number of units each belt size is.
   * @return - units.
   */
  public int getBeltUnits() {
    return this.units;
  }

}
