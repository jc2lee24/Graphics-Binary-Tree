import javax.swing.*;

public class Runner extends JFrame {
  public static void main(String[] args) {
    JFrame fr = new JFrame("YAY WE'RE DOING SOMETHING NEW WITH GRAPHICS!!!!");
		Screen sc = new Screen();
		
		fr.add(sc);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.pack();
		fr.setVisible(true);
  }
}