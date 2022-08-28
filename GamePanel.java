import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class GamePanel extends Hangman implements ActionListener
{
    JButton [] alphabet = new JButton[26];
    JLabel [] blank;
    //private static String word;
   
    //private JPanel game;
    static int countWrong=0;
    private LinkedList<ImageIcon> steps = stickmanArray();
    private JPanel drawing, letters;
    private JButton exit, back;
    private String mode, word;
    public GamePanel(String category, String m)
   {
       super();
       JPanel g = new JPanel();
       g.setLayout( new BorderLayout() );
       win.add(g);
       mode=m;
       letters = new JPanel();
       GridLayout grid = new GridLayout(2,13);
       grid.setVgap(2);
       grid.setHgap(2);
       letters.setLayout(grid);
       g.add(letters, BorderLayout.SOUTH);
       
       drawing= new JPanel();
       drawing.add(new JLabel(new ImageIcon("hang.jpg")));
       g.add(drawing, BorderLayout.WEST);
       
       try{
           word=pickRandomWord(category);}
       catch(Exception e){}
       //System.out.println(word);
       
       JPanel blanks = new JPanel();
       blanks.setLayout(new FlowLayout());
       g.add(blanks);
       blanks.add(new JLabel("\n"+category+", "+mode+" mode\n"));
       blanks.add(new JLabel("                                      "));
       blank = new JLabel [word.length()];
       for(int x=0; x<blank.length; x++)
       {
           if(word.charAt(x)==' ')
           blank[x]=new JLabel(" ");
           else
           blank[x] = new JLabel("_");
           
           blanks.add( blank[x], BorderLayout.CENTER);
       }
       
       
       //making and adding buttons//
       for(int y=0; y<alphabet.length; y++)
       {
           alphabet[y] = new JButton(""+(char)(y+97));
           alphabet[y].addActionListener(this);
           letters.add(alphabet[y], BorderLayout.SOUTH);
           //System.out.println(alphabet[y].getText());
           
       }
     
       
        exit = new JButton("Exit");
        exit.addActionListener(this);
        g.add(exit, BorderLayout.NORTH);
       
       
    }
    public static String pickRandomWord(String x)throws IOException
    {
        Random randy = new Random();
        int y = randy.nextInt(32)+1;//+1?
        //System.out.println(y);
        try{
        Scanner fr = new Scanner(new File(x+".txt"));
        int counter=1;
        while(fr.hasNext())
        {
            String w = fr.nextLine();
            if(counter==y)//-1)
            return w;
            counter++;
        }}
        catch(Exception e){}
        return "";
    }
    public static  LinkedList<ImageIcon> stickmanArray()
   {
       LinkedList<ImageIcon> steps = new LinkedList<ImageIcon>();
       steps.add(new ImageIcon("firststep.jpg"));
       steps.add(new ImageIcon("secondstep.jpg"));
       steps.add(new ImageIcon("thirdstep.jpg"));
       steps.add(new ImageIcon("fourthstep.jpg"));
       steps.add(new ImageIcon("fifthstep.jpg"));
       steps.add(new ImageIcon("sixthstep.jpg"));
       steps.add(new ImageIcon("seventhstep.jpg"));
       steps.add(new ImageIcon("eighthstep.jpg"));
       return steps;
   }
   public void addLetter(String c)
    {
            for(int x=0; x<blank.length;x++)
           {
               if(c.equalsIgnoreCase(""+word.charAt(x)))
               blank[x].setText(c);
            }
           
    }
   public boolean letterThere(String c)
    {
        
        for(int x=0; x<word.length();x++)
        {
            //System.out.println(c+" "+word.charAt(x));
            if(c.equalsIgnoreCase(""+word.charAt(x)))
            return true;
        }
        return false;
    }
    public void checkIfWon()
    {
        for(int x=0;x<blank.length;x++)
        {
            if(blank[x].getText().equals("_"))
            return;
        }
        winLose("won");
        
                //goToWinLose(false);
            
    }
    public void winLose(String y)
    {
        for(int x=0; x<26; x++)
            alphabet[x].removeActionListener(this);
        back = new JButton("Return to main menu");
        back.addActionListener(this);
        JButton[] options = { back  };
        JOptionPane.showOptionDialog(null, "Click Exit to return to main menu", "You "+y+"! The word was: "+word,JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
    }
    
     public void checkIfLost()
     {
         if((mode.equals("Hard")&&countWrong==6)||(mode.equals("Easy")&&countWrong==8))
         winLose("lost");
      }
   public void actionPerformed(ActionEvent e)
   {
       if(e.getSource()==exit||e.getSource()==back)
       {
           //SwingUtilities.getWindowAncestor(exit).setVisible(false);
           win.removeAll();
           win.repaint();
           win.revalidate();
           countWrong=0;
           MainMenu mainmenu=new MainMenu();
           JPanel panel=mainmenu.getPanel();
           win.add(panel);
           if(e.getSource()==back)
           SwingUtilities.getWindowAncestor(back).setVisible(false);
        }
        else
        {
           String x=((JButton)(e.getSource())).getText();
           ((JButton)(e.getSource())).setVisible(false);
           if(letterThere(x))
               {
                   addLetter(x);
                   //System.out.println("adding letter");
                   checkIfWon();
                }
           else
           {
               countWrong++;
               drawing.removeAll();
               drawing.add(new JLabel(steps.get(countWrong-1)));
               drawing.repaint();
               drawing.revalidate();
               checkIfLost();
            }
        }
    }
}
