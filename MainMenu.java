import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class MainMenu extends Hangman implements ActionListener
{
    private JPanel menu, middle;
    private ButtonGroup categories, mode;
    private JButton ready;
    private JRadioButton b1, b2, b3, b4, easy, hard;
    private String mod,cat; 
    public MainMenu() {
        super();
         menu = new JPanel();
         middle = new JPanel();
        GridLayout g1 = new GridLayout(8, 1);
        middle.setLayout(g1);
        menu.setLayout(new BorderLayout());
        
        JLabel lb1 = new JLabel("                     Welcome to Hangman");
        menu.add(lb1, BorderLayout.NORTH);
        middle.add(new JLabel("Select a category"));
        b1 = new JRadioButton("Food");
        b2 = new JRadioButton("Animals");
        b3 = new JRadioButton("Household Items");
        b4 = new JRadioButton("Locations");
        categories = new ButtonGroup();
        categories.add(b1);
        categories.add(b2);
        categories.add(b3);
        categories.add(b4);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        middle.add(b1);
        middle.add(b2);
        middle.add(b3);
        middle.add(b4);
        
        middle.add(new JLabel("Select a mode"));
        mode = new ButtonGroup();
        easy = new JRadioButton("Easy");
        hard = new JRadioButton("Hard");
        mode.add(easy);
        mode.add(hard);
        easy.addActionListener(this);
        hard.addActionListener(this);
        middle.add(easy);
        middle.add(hard);
        
        g1.setVgap(10);
        g1.setHgap(5);
        
        ready = new JButton("Ready");
        ready.addActionListener(this);
        menu.add(ready, BorderLayout.SOUTH);
        
        menu.add(middle, BorderLayout.CENTER);

    }
    public JPanel getPanel()
    {
        return menu;
    }
    public void actionPerformed(ActionEvent e)
    {
        
        if(e.getSource()==b1 ||e.getSource()==b2||e.getSource()==b3||e.getSource()==b4)
        cat=((JRadioButton)e.getSource()).getText();
        else if(e.getSource()==easy||e.getSource()==hard)
        mod =((JRadioButton)e.getSource()).getText();
        else if(e.getSource()==ready&&mod!=null &&cat!=null) 
        {
         System.out.println(mod+cat);
         getWin().removeAll();
         System.out.println("made new panel");
         win.revalidate();
         win.repaint();
         GamePanel game = new GamePanel(cat, mod);
       }
       
    }
    
}
