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
  private boolean done = true;

  public SnakeGame() {
    setFocusable(true);
    addKeyListener(this);
    setPreferredSize(new Dimension(800, 800));
  }

  // starts a timer that excecutes this code every 100ms
  public void startGame() {
    timer = new Timer(delay, this);
    timer.start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // paints the grid
    for (int i = 0; i < numOfCells; i++) {
      g.drawLine(50, originY + sizeOfCell * i, 750, originY + sizeOfCell * i);
      g.drawLine(originX + sizeOfCell * i, 50, originX + sizeOfCell * i, 750);
    }

    if (gameEnds()) {
      String endGame = "You Lost!";
      // if the snake filled the whole grid you win the game
      if (snake.getPosition().size() > 801) {
        endGame = "You Won!";
      }
      g.setFont(new Font("Verdana", 1, 80));
      int textWidth = g.getFontMetrics().stringWidth(endGame);
      int textHeight = g.getFontMetrics().getHeight();
      int x = (700 - textWidth) / 2 + 50;
      int y = (700 - textHeight) / 2 + 25 + textHeight;
      g.setColor(Color.gray);
      g.fillRect((700 - (textWidth + textWidth / 4)) / 2 + 50, (700 - (textHeight * 2)) / 2 + 50,
          textWidth + textWidth / 4, textHeight * 2);
      g.setColor(Color.black);
      g.drawString(endGame, x, y);
      return;
    }

    // draws the snake
    g.setColor(Color.gray);
    for (int i = 0; i < snake.getPosition().size() / 2 - 1; i++) {
      int j = (i + 1) * 2;
      g.fillRect(
          snake.getPosition().get(j) * sizeOfCell + 50,
          snake.getPosition().get(j + 1) * sizeOfCell + 50,
          sizeOfCell,
          sizeOfCell);
    }

    // draws the apple
    g.setColor(Color.red);
    g.fillRect(
        snake.getApplePosition()[0] * sizeOfCell + 50,
        snake.getApplePosition()[1] * sizeOfCell + 50,
        sizeOfCell,
        sizeOfCell);
  }

  // movement code
  @Override
  public void keyPressed(KeyEvent move) {
    int keyCode = move.getKeyCode();
    if (keyCode == KeyEvent.VK_UP && snake.getDirection() != 2) {
      snake.changeDirection(-2);
    } else if (keyCode == KeyEvent.VK_DOWN && snake.getDirection() != -2) {
      snake.changeDirection(2);
    } else if (keyCode == KeyEvent.VK_LEFT && snake.getDirection() != 1) {
      snake.changeDirection(-1);
    } else if (keyCode == KeyEvent.VK_RIGHT && snake.getDirection() != -1) {
      snake.changeDirection(1);
    }
  }

  // sets up the window for the game
  public static void main(String[] args) {
    JFrame frame = new JFrame("Big Snake Good");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    SnakeGame board = new SnakeGame();
    board.setBackground(Color.green);
    frame.add(board);
    frame.pack();
    frame.setVisible(true);
    board.startGame();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    snake.move();
    snake.eatsApple();
    repaint();
    if (gameEnds()) {
      done = true;
    }
  }

  private boolean gameEnds() {
    // if the player wins and the snake has filled the entire grid return true
    if (snake.getPosition().size() > 801) {
      return true;
    }

    // if the snake touched the board return true
    if (snake.getPosition().get(2) > 19 || snake.getPosition().get(2) < 0 || snake.getPosition().get(3) < 0
        || snake.getPosition().get(3) > 19) {
      return true;
    }

    // if the snake ate itself return true
    int length = snake.getPosition().size() / 2 - 1;
    for (int i = 0; i < length; i++) {
      int l = (i + 1) * 2;
      for (int j = 0; j < length; j++) {
        int k = (j + 1) * 2;
        if (snake.getPosition().get(l) == snake.getPosition().get(k) && k != l) {
          if (snake.getPosition().get(l + 1) == snake.getPosition().get(k + 1)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

}
