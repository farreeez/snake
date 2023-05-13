import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Snake {
  private int direction;
  private int[] applePosition = { 13, 10 };
  private ArrayList<Integer> positions = new ArrayList<>(Arrays.asList(-2, -2));
  int[] pp = { 1, 2 };
  int[] pp1 = { 1, 3 };
  int[] pp2 = { 1, 4 };
  private ArrayList<int[]> penis = new ArrayList<>(Arrays.asList(pp1, pp2, pp));

  public Snake() {
    this.direction = 1;
    this.positions.addAll(Arrays.asList(4, 10, 3, 10));
  }

  private ArrayList<int[]> changeApplePosition() {
    ArrayList<int[]> availablePositions = new ArrayList<>();

    if (this.positions.size() == 802) {
      return availablePositions;
    }

    for (int i = 0; i < 20; i++) {
      for (int j = 0; j < 20; j++) {
        int[] position = { i, j };
        availablePositions.add(position);
      }
    }

    for (int i = 0; i < this.positions.size() / 2 - 1; i++) {
      int j = (i + 1) * 2;
      for(int k = 0; k < availablePositions.size(); k++){
        if(availablePositions.get(k)[0] == this.positions.get(j) && availablePositions.get(k)[1] == this.positions.get(j+1)){
          availablePositions.remove(k);
        }
      }
    }
    return availablePositions;
  }

  public void eatsApple() {
    if (this.positions.get(2) == this.applePosition[0] && this.positions.get(3) == this.applePosition[1]) {
      for (int i = 0; i < 2; i++) {
        if (this.positions.get(this.positions.size() - 2) == this.positions.get(this.positions.size() - 4)) {
          this.positions.add(this.positions.get(this.positions.size() - 2));
          int difference = this.positions.get(this.positions.size() - 2)
              - this.positions.get(this.positions.size() - 4);
          this.positions.add(this.positions.get(this.positions.size() - 2) + difference);
        } else {
          int difference = this.positions.get(this.positions.size() - 2)
              - this.positions.get(this.positions.size() - 4);
          this.positions.add(this.positions.get(this.positions.size() - 2) + difference);
          this.positions.add(this.positions.get(this.positions.size() - 2));
        }
      }

      ArrayList<int[]> availablePositions = this.changeApplePosition();
      Random random = new Random();
      int[] newApplePosititon = availablePositions.get(random.nextInt(availablePositions.size()));
      this.applePosition[0] = newApplePosititon[0];
      this.applePosition[1] = newApplePosititon[1];
    }
  }

  public int[] getApplePosition() {
    return this.applePosition;
  }

  public void changeDirection(int direction) {
    this.direction = direction;
  }

  public void move() {
    if (this.direction == 1) {
      positions.addAll(2, Arrays.asList(positions.get(2) + 1, positions.get(3)));
    } else if (this.direction == -1) {
      positions.addAll(2, Arrays.asList(positions.get(2) - 1, positions.get(3)));
    } else if (this.direction == 2) {
      positions.addAll(2, Arrays.asList(positions.get(2), positions.get(3) + 1));
    } else if (this.direction == -2) {
      positions.addAll(2, Arrays.asList(positions.get(2), positions.get(3) - 1));
    }
    positions.remove(positions.size() - 1);
    positions.remove(positions.size() - 1);
  }

  public ArrayList<Integer> getPosition() {
    return this.positions;
  }

  public int getDirection() {
    return this.direction;
  }
}
