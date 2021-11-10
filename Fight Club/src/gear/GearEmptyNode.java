package gear;

import java.util.Comparator;

class GearEmptyNode implements ListOfGear {
  @Override
  public int length() {
    return 0;
  }

  @Override
  public Gear get(int index) {
    throw new IndexOutOfBoundsException("No such gear: " + index);
  }

  @Override
  public ListOfGear sort(Comparator<Gear> comp) {
    return this;
  }

  @Override
  public ListOfGear insert(Gear g, Comparator<Gear> comp) {
    return new GearElementNode(g, this);
  }

  @Override
  public boolean equalsEmptyNode(GearEmptyNode emptyNode) {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ListOfGear) {
      ListOfGear g = (ListOfGear) o;
      return g.equalsEmptyNode(this);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
