package org.example;


import java.awt.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument.Content;

import java.awt.event.*;
import java.util.ArrayList;

public class Game extends JFrame implements ActionListener, MouseListener{

    JFrame frame = new JFrame();
    JButton reset = new JButton("Reset");
    JButton giveUp = new JButton("Give Up");
    JPanel buttonPanel = new JPanel();
    Container grid = new Container();
    int[][] counts;
    
    JButton[][] buttons;
    int size;
    static final int MINE = 9;


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
        createMines(size);
                    creatNums(size);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);



    }

    public void createMines(int s){
        ArrayList<Integer> list = new ArrayList<>();
                for(int x = 0; x < s; x++)
                {
                    for(int y = 0; y < s; y++)
                    {
                        list.add(x*100+y);
                    }
                }
                counts = new int[s][s];
                for(int a = 0; a < (int)(s * 1.5); a++)
                        {
                            int choice = (int)(Math.random() * list.size());
                            counts [list.get(choice) / 100] [list.get(choice) % 100] = MINE;
                            list.remove(choice);
                        }
        }

         public void takeTheL(int m){

                for(int x = 0; x < size; x++)
                {
                    for(int y = 0; y < size; y++)
                    {
                        if(buttons[x][y].isEnabled())  
                        {
                            if(counts[x][y] != MINE)
                            {
                                buttons[x][y].setText(""+ counts[x][y]);
                            }

                            else
                            {
                                buttons[x][y].setText("X");

                            }
                            buttons[x][y].setEnabled(false);
                        }
                    }
                }
                //boom
            }

    public Void creatNums(int size) {
        int k=0;

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){

                if(counts[i][j]!=9){
                    int x;

                    for(x=i-1 ; x<=i+1 ; x++){
                        for(int y=j-1 ; y<=j+1 ; y++){
                            if(counts[x][y]==MINE&&!(x<0||x>size-1||y<0||y>size-1)){
                                k++;
                            }
                        }
                    }
                    counts[i][j]=k;
                    k=0;
                }
            }
        }
        return null;
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
                    createMines(size);
                    creatNums(size);
                    creatNums(size);
                }
            }
        }

        else if(ae.getSource() == giveUp)
        {
             takeTheL(size);
        }

        else
        {
            for(int x = 0; x < size; x++)
            {
                for( int y = 0; y < size; y++)
                {
                    if(ae.getSource() == buttons[x][y])
                    {
                        if(counts[x][y]==9)
                            takeTheL(size);
                        else
                        {
                            buttons[x][y].setEnabled(false);
                            buttons[x][y].setText(""+counts[x][y]);

                        }
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