package org.example;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Game extends JFrame implements ActionListener, MouseListener{

    JFrame frame = new JFrame();
    JButton reset = new JButton("Reset");
    JButton giveUp = new JButton("Give Up");
    JPanel buttonPanel = new JPanel();
    Container grid = new Container();
    int[][] counts;
    JButton[][] buttons;
    int size;
    static final int MINE = 10;


    public Game(int size) {
        super("Minesweeper");

        this.size = size;
        counts = new int[size][size];
        buttons = new JButton[size][size];

        frame.setSize(900,900);
        frame.setLayout(new BorderLayout());
        frame.add(buttonPanel,BorderLayout.SOUTH);
        reset.addActionListener(this);
        giveUp.addActionListener(this);

        grid.setLayout(new GridLayout(size,size));

        for(int a = 0; a < buttons.length; a++)
        {
            for(int b = 0; b < buttons[0].length; b++)
            {
                buttons[a][b] = new JButton();
                buttons[a][b].addActionListener(this);
                grid.add(buttons[a][b]);
            }
        }

        buttonPanel.add(reset);
        buttonPanel.add(giveUp);

        frame.add(grid,BorderLayout.CENTER);
        // make mines

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == reset)
        {
            for(int x = 0; x < size; x++)
            {
                for(int y = 0; y < size; y++)
                {
                    buttons[x][y].setEnabled(true);
                    buttons[x][y].setText("");
                    // make mines
                }
            }
        }
        else if(ae.getSource() == giveUp)
        {
             // loos
        }
        else
        {
            for(int x = 0; x < size; x++)
            {
                for( int y = 0; y < size; y++)
                {
                    if(ae.getSource() == buttons[x][y])
                    {
                        // click on a cell
                    }
                }
            }
        }


    }
        @Override
    public void mouseClicked(MouseEvent me) {
        SwingUtilities.isRightMouseButton(me);
    }
    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) { }
}