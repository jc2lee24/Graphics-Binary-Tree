import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Screen extends JPanel implements ActionListener{
    private BinarySearchTree<Integer> tree;
    private JTextField number_field;
    private JButton add_button;
    private JButton remove_button;
    private JButton balance;

    public Screen(){
        tree = new BinarySearchTree<>();
        tree.add(10);
        tree.add(5);
        tree.add(1);
        tree.add(9);
        tree.add(15);
        tree.add(11);
        tree.add(19);

        number_field = new JTextField();
        number_field.setFont(new Font("Arial", Font.PLAIN, 20));
        number_field.setHorizontalAlignment(SwingConstants.CENTER);
        number_field.setBounds(50, 500, 200, 30);
        number_field.setText("Value");
        this.add(number_field);

        add_button = new JButton();
        add_button.setFont(new Font("Arial", Font.BOLD, 20));
        add_button.setHorizontalAlignment(SwingConstants.CENTER);
        add_button.setBounds(300, 500, 200, 30);
        add_button.setText("add");
        this.add(add_button);
        add_button.addActionListener(this);

        remove_button = new JButton();
        remove_button.setFont(new Font("Arial", Font.BOLD, 20));
        remove_button.setHorizontalAlignment(SwingConstants.CENTER);
        remove_button.setBounds(550, 500, 200, 30);
        remove_button.setText("remove");
        this.add(remove_button);
        remove_button.addActionListener(this);

        balance = new JButton();
        balance.setFont(new Font("Arial", Font.BOLD, 20));
        balance.setHorizontalAlignment(SwingConstants.CENTER);
        balance.setBounds(300, 450, 200, 30);
        balance.setText("balance");
        this.add(balance);
        balance.addActionListener(this);

        setLayout(null);
    }

    public Dimension getPreferredSize(){
        return new Dimension(800,600);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        tree.drawMe(g, 400, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == add_button){
            int addValue = Integer.parseInt(number_field.getText());
            tree.add(addValue);
        }
        else if(e.getSource() == remove_button){
            int removeValue = Integer.parseInt(number_field.getText());
            tree.remove(removeValue);
        }
        else if(e.getSource() == balance){
            tree.balance();
        }

        repaint();
    }
}
