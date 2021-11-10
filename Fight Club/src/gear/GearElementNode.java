package gear;

import java.util.Comparator;
import java.util.Objects;

class GearElementNode implements ListOfGear {
  private final Gear gear;
  private final ListOfGear rest;

  public GearElementNode(Gear gear, ListOfGear rest) {
    this.gear = gear;
    this.rest = rest;
  }

  @Override
  public int length() {
    return 1 + rest.length();
  }

  @Override
  public Gear get(int index) {
    if (index == 0) {
      return gear.getCopy();
    } else {
      try {
        return rest.get(index - 1);
      } catch (IndexOutOfBoundsException e) {
        throw new IndexOutOfBoundsException("No such gear: " + index);
      }
    }
  }

  @Override
  public ListOfGear insert(Gear g, Comparator<Gear> comp) {
    if (comp.compare(g, this.gear) >= 0) {
      return new GearElementNode(g, this);
    } else {
      return new GearElementNode(gear, rest.insert(g, comp));
    }
  }

  @Override
  public ListOfGear sort(Comparator<Gear> comp) {
    return rest.sort(comp).insert(gear, comp);
  }

  @Override
  public boolean equalsElementNode(GearElementNode gElt) {
    return gear.equals(gElt.gear) && rest.equals(gElt.rest);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ListOfGear) {
      ListOfGear g = (ListOfGear) o;
      return g.equalsElementNode(this);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(gear, rest);
  }
}
