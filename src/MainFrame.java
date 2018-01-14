import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MainFrame extends JFrame{



    private String [][] boxs1 = new String[][]{{"1","2","3","4"},//4*4
            {"5","6","7","8"},
            {"9","10","11","12"},
            {"13","14","15"," "}};
//    private String [][] boxs01 = new String[][]{//3*3
//            {"1","2","3"},
//            {"4","5","6"},
//            {"7","8"," "}};


    private JLabel [] jl = new JLabel [16];//4*4
    //    private JLabel [] jl2 = new JLabel [9];//3*3
    private JMenuBar jmb =new JMenuBar();
    private JMenu game = new JMenu("遊戲"),about = new JMenu("關於");
    private JMenuItem [] gm = new JMenuItem[3];//遊戲
    private JMenuItem [] abo = new JMenuItem[2];//關於
    //    private JMenuItem jmc =new JMenuItem("4*4");
    private final int LEFTn = 37,UPn = 38, RIGHTn = 39, DOWNn = 40;
    private boolean flag=true;
    public MainFrame(){
        initComp();
    }
    private void initComp(){
        this.setTitle("PuzzleGame");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(300,250,800,800);
        this.setJMenuBar(jmb);

        Container cp ;
        cp = this.getContentPane();
        cp.setLayout(new GridLayout(4,4));
        jmb.add(game);
        gm[0] = new JMenuItem("4*4");
//        gm[3] = new JMenuItem("3*3");
        gm[1] = new JMenuItem("give up!!");
        gm[2] = new JMenuItem("End Game");
        game.add(gm[0]);
        game.add(gm[1]);
        game.addSeparator();
        game.add(gm[2]);
//        game.add(gm[3]);
        gm[1].setEnabled(false);
        jmb.add(about);
//        about.add(jmc);
        abo[0] = new JMenuItem("遊戲說明");
        about.add(abo[0]);
//        abo[0].add(jmc);
        for(int i = 0; i < jl.length; i++)
        {
            jl[i] = new JLabel();
            jl[i].setFont(new Font("Serif",Font.BOLD,80));
            cp.add(jl[i]);
        }
        gm[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == gm[0]) //新局
                {
                    boxs1 = num();
                    for(int i=0; i< jl.length; i++)//更新數字顯示的位置
                        jl[i].setText("  "+boxs1[i/boxs1.length][i%boxs1.length]);
                    flag = true;
                    gm[1].setEnabled(true);
                }


            }
        });
        gm[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == gm[1]) //投降
                {
                    flag = false;
                    for(int i=0; i< jl.length; i++)//初始化數字顯示的位置
                    {
                        boxs1[i/boxs1.length][i%boxs1.length] = Integer.toString(i+1);
                        if(boxs1[i/boxs1.length][i%boxs1.length].equals("16")) boxs1[i/boxs1.length][i%boxs1.length] = " ";
                        jl[i].setText("  ");//清空
                    }


                }


            }
        });

        abo[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == abo[0]) //遊戲說明
                {
                    JOptionPane.showMessageDialog(null, "過關排列方式      操作方式\n"+
                                    "    1  2   3   4      ↑ 上移\n" +
                                    "    5  6   7   8      ←  左移 \n" +
                                    "    9  10 11 12     →  右移 \n " +
                                    "  13 14 15               ↓ 下移 "
                            , "遊戲規則",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });



        gm[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==gm[2]){
                    System.exit(0);
                }


            }
        });


        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (flag==true) {
                    String str=" ";
                    int [] zero = null;
                    zero = getZero(boxs1);  //取得" "的位置
                    switch(e.getKeyCode())
                    {
                        case LEFTn: //按左鍵
                            if(zero[1]+1<boxs1[zero[0]].length)
                            {
                                str = boxs1[zero[0]][zero[1]+1];
                                boxs1[zero[0]][zero[1]+1] = " ";
                            }
                            break;
                        case UPn: //按上鍵
                            if(zero[0]+1<boxs1.length)
                            {
                                str = boxs1[zero[0]+1][zero[1]];
                                boxs1[zero[0]+1][zero[1]] = " ";
                            }
                            break;
                        case RIGHTn: //按右鍵
                            if(zero[1]-1 >= 0)
                            {
                                str = boxs1[zero[0]][zero[1]-1];
                                boxs1[zero[0]][zero[1]-1] = " ";
                            }
                            break;
                        case DOWNn: //按下鍵
                            if(zero[0]-1 >= 0)
                            {
                                str = boxs1[zero[0]-1][zero[1]];
                                boxs1[zero[0]-1][zero[1]] = " ";
                            }
                            break;
                        default:
                    }
                    boxs1[zero[0]][zero[1]] = str;
                    //更新數字顯示的位置
                    for(int i=0; i< jl.length; i++)
                        jl[i].setText("  "+boxs1[i/boxs1.length][i%boxs1.length]);


                    //判斷是否過關
                    if(boxs1[0][0].equals("1") && boxs1[0][1].equals("2") &&boxs1[0][2].equals("3")&&boxs1[0][3].equals("4") &&
                            boxs1[1][0].equals("5") && boxs1[1][1].equals("6") &&boxs1[1][2].equals("7")&&boxs1[1][3].equals("8") &&
                            boxs1[2][0].equals("9") && boxs1[2][1].equals("10") &&boxs1[2][2].equals("11")&&boxs1[2][3].equals("12") &&
                            boxs1[3][0].equals("13") && boxs1[3][1].equals("14") &&boxs1[3][2].equals("15")&&
                            boxs1[3][3].equals(" ")
                            )
                    {


                        JOptionPane.showMessageDialog(null, "過關了!!成功", "訊息",
                                JOptionPane.INFORMATION_MESSAGE);

                        //判斷是否再玩一次
                        if(JOptionPane.showConfirmDialog(null, "再玩一次？", "訊息",
                                JOptionPane.YES_NO_OPTION) == 0
                                )
                        {
                            boxs1 = num();
                            for(int i=0; i< jl.length; i++)//更新數字顯示的位置
                                jl[i].setText("  "+boxs1[i/boxs1.length][i%boxs1.length]);
                        }
                        else System.exit(0);  //結束遊戲
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


    }
    private String[][] num()//配合getZero
    {
        String [][] boxs2 = new String[][]{{"1","2","3","4"},
                {"5","6","7","8"},
                {"9","10","11","12"},
                {"13","14","15"," "}};

        //用亂數打亂數字的排列
        for(int count = 0; count < 200; count++)
        {
            String temp = " ";
            int [] zero = null;
            int rnd= (int)(Math.random()*4)+LEFTn;//用來隨機移動數字
            //上下左右4個數值來控制
            //來改變" "的位置
            //因而改變位置

            zero = getZero(boxs2);  //取得0的位置
            switch(rnd)
            {
                case LEFTn: //左移
                    if(zero[1]+1<boxs2[zero[0]].length)
                    {
                        temp = boxs2[zero[0]][zero[1]+1];
                        boxs2[zero[0]][zero[1]+1] = " ";
                    }
                    break;
                case UPn: //上移
                    if(zero[0]+1<boxs2.length)
                    {
                        temp = boxs2[zero[0]+1][zero[1]];
                        boxs2[zero[0]+1][zero[1]] = " ";
                    }
                    break;
                case RIGHTn: //右移
                    if(zero[1]-1 >= 0)
                    {
                        temp = boxs2[zero[0]][zero[1]-1];
                        boxs2[zero[0]][zero[1]-1] = " ";
                    }
                    break;
                case DOWNn: //下移
                    if(zero[0]-1 >= 0)
                    {
                        temp = boxs2[zero[0]-1][zero[1]];
                        boxs2[zero[0]-1][zero[1]] = " ";
                    }
                    break;
                default:
            }
            boxs2[zero[0]][zero[1]] = temp;
        }

        return boxs2;
    }

    //取得0的位置
    private int [] getZero(String [][] boxlxly)
    {
        int lxlylocation[] = new int[2];

        for(int r = 0; r < boxlxly.length; r++)
        {
            for(int c = 0; c < boxlxly[r].length; c++)
            {
                //取得數字0的位置
                if(boxlxly[r][c].equals(" "))
                {
                    lxlylocation[0] = r;  //" "的x位置(lx)
                    lxlylocation[1] = c;  //" "的y位置(ly)
                }
            }
        }

        return lxlylocation;
    }



}