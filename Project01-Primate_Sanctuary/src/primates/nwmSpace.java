package primates;

/**
 * Space enumeration.
 */
public enum nwmSpace {
  SMALL(1), MEDIUM(5), LARGE(10);

  private final int space;

  private nwmSpace(final int space) {
    this.space = space;
  }

  public int getSpaceRequirement() {
    return space;
  }
}
