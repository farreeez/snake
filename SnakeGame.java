import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class SnakeGame extends JPanel implements KeyListener, ActionListener {
  private int originX = 50;
  private int originY = 50;
  private int numOfCells = 21;
  private int sizeOfCell = 35;
  private Snake snake = new Snake();
  private Timer timer;
  private int delay = 100;

  public SnakeGame(){
    setFocusable(true);
    addKeyListener(this);
    setPreferredSize(new Dimension(800, 800));
  }

  public void startGame() {
    timer = new Timer(delay, this);
    timer.start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    for (int i = 0; i < numOfCells; i++) {
      g.drawLine(50, originY + sizeOfCell * i, 750, originY + sizeOfCell * i);
      g.drawLine(originX + sizeOfCell * i, 50, originX + sizeOfCell * i, 750);
    }

    g.setColor(Color.gray);
    for (int i = 0; i < snake.getPosition().size() / 2 - 1; i++) {
      int j = (i + 1) * 2;
      g.fillRect(
          snake.getPosition().get(j) * sizeOfCell + 50,
          snake.getPosition().get(j + 1) * sizeOfCell + 50,
          sizeOfCell,
          sizeOfCell);
    }

    g.setColor(Color.red);
    g.fillRect(
        snake.getApplePosition()[0] * sizeOfCell + 50,
        snake.getApplePosition()[1] * sizeOfCell + 50,
        sizeOfCell,
        sizeOfCell);
  }

  @Override
  public void keyPressed(KeyEvent move) {
    int keyCode = move.getKeyCode();
    if (keyCode == KeyEvent.VK_UP && snake.getDirection() != -2) {
      snake.changeDirection(2);
    } else if (keyCode == KeyEvent.VK_DOWN && snake.getDirection() != 2) {
      snake.changeDirection(-2);
    } else if (keyCode == KeyEvent.VK_LEFT && snake.getDirection() != 1) {
      snake.changeDirection(-1);
    } else if (keyCode == KeyEvent.VK_RIGHT && snake.getDirection() != -1) {
      snake.changeDirection(1);
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Big Snake Good");
    SnakeGame board = new SnakeGame();
    board.setBackground(Color.green);
    frame.add(board);
    frame.pack();
    frame.setVisible(true);
    board.startGame();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    snake.move();
    
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
  }
}
