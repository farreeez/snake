import java.util.ArrayList;
import java.util.Arrays;

public class Snake {
  private int direction;
  private int length;
  private int[] applePosition = new int[2];
  private ArrayList<Integer> positions = new ArrayList<>(Arrays.asList(0, 0));

  public Snake() {
    this.direction = 1;
    this.length = 2;
    this.positions.addAll(Arrays.asList(4, 10, 3, 10));
    this.applePosition[0] = 13;
    this.applePosition[1] = 10;
  }

  public int[] getApplePosition() {
    return this.applePosition;
  }

  public void changeDirection(int direction) {
    this.direction = direction;
  }

  public void move() {
    if (this.direction == 1) {
      positions.addAll(2, Arrays.asList(positions.get(2) - 1, positions.get(3)));
    } else if (this.direction == -1) {
      positions.addAll(2, Arrays.asList(positions.get(2) + 1, positions.get(3)));
    } else if (this.direction == 2) {
      positions.addAll(2, Arrays.asList(positions.get(2), positions.get(3) + 1));
    } else if (this.direction == -2) {
      positions.addAll(2, Arrays.asList(positions.get(2), positions.get(3) - 1));
    }
    positions.remove(positions.size() - 1);
    positions.remove(positions.size() - 1);
    for(int i =0; i<positions.size(); i++){
        System.out.println(positions.get(i));
    }
  }

  public void increseLneght() {
    this.length += 2;
  }

  public void changePosition() {}

  public ArrayList<Integer> getPosition() {
    return this.positions;
  }

public int getDirection() {
    return this.direction;
}
}