package exoress;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class One_Expree extends JFrame implements ActionListener {

    private static Object tjiao;
    private String Str = "";
    int socre[] = new int[1000];
    int questionNumber = 0;
    String[] titles = new String[100];
    int j = 0;
    int JCB_NUM = 0;
    JRadioButton[] radioButton = new JRadioButton[1000];
    JRadioButton[] titleButton = new JRadioButton[250];
    JButton[] jButtons = new JButton[1000];
    JCheckBox[] Jcb = new JCheckBox[100];
    TextField[] tf = new TextField[1000];
    JFrame panel2 = new JFrame("题目选择");
    String questionBank;


    public void daoru() throws IOException {

        JLabel bti = new JLabel("请在需要的题目打钩");
        bti.setFont(new Font("宋体", Font.BOLD, 20));
        bti.setBounds(450, 15, 800, 30);
        panel2.setLayout(null);
        panel2.getContentPane().add(bti);

        JPanel JP = new JPanel();
        JPanel JP1 = new JPanel();
        JPanel JP2 = new JPanel();

        JP.setLayout(null);
        JP1.setLayout(null);
        JP2.setLayout(null);


        JP.setPreferredSize(new Dimension(400, 5000));
        JP1.setPreferredSize(new Dimension(400, 5000));
        JP2.setPreferredSize(new Dimension(400, 5000));


        JButton tjiao = new JButton("提交");
        tjiao.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                       Boolean st=true;
                for (int i = 0; i < questionNumber * 4; i++) {


                    if (tf[i].getText() == null || tf[i].getText().equals("")) {

                        JOptionPane.showMessageDialog(null, "请输入完全分数", "alert", JOptionPane.ERROR_MESSAGE);
                        st=false;
                        break;

                    }
                    try {
                        socre[i] = Integer.parseInt(tf[i].getText());
                    } catch (Exception e1) {

                        JOptionPane.showMessageDialog(null, "请输入数字", "alert", JOptionPane.ERROR_MESSAGE);
                    }

                }

                File F1 = new File("教师文件//one_score.txt");

                   while (st) {
                       try {

                           FileWriter pw = new FileWriter(F1);
                           for (int k = 0; k < questionNumber * 4; k++) {

                               pw.write(socre[k] + "\r\n");
                           }

                           pw.close();
                           panel2.dispose();
                           setpingyu();

                                st=false;
                       } catch (IOException e1) {
                           // TODO Auto-generated catch block
                           e1.printStackTrace();
                       }
                   }

            }
        });

        JButton tjiao1 = new JButton("提交");
        tjiao1.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < j / 4; i++) {

                    if (titleButton[i].isSelected()) {
                        Str += titles[i] + "\r\n";
                        for (int k = 0; k < 4; k++) {
                            Str += radioButton[i + k].getText().toString() + "\r\n";

                        }
                        Str += "" + "\r\n";

                    }
                }
                try {
                    questionBank = "学生文件/one.txt";
                    File F1 = new File(questionBank);
                    BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(F1), "utf-8"));


                    pw.write(Str);

                    pw.close();

                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    panel2.getContentPane().removeAll();

                    questionNumber = 0;

                    changePanel(JP, tjiao, questionBank);
                    for (int i = 0; i < questionNumber * 4; i++) {

                        tf[i] = new TextField();
                        JP.add(tf[i]);
                        tf[i].setBounds(366, i / 4 * 100 + i % 4 * 16 + 16, 20, 16);

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });
        questionBank = "教师文件//题库.txt";
        changePanel(JP1, tjiao1, questionBank);


        for (int i = 0; i < questionNumber; i++) {

            titleButton[i] = new JRadioButton("在需要的题目打钩");
            JP1.add(titleButton[i]);
            titleButton[i].setBounds(200, +i * 100, 200, 16);

        }


    }

    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void changePanel(JPanel jPanel, JButton tjiao, String questionBank) throws IOException {


        BufferedReader br1;
        String tmp = "";
        File file = new File(questionBank);
        br1 = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));


        String title;

        while ((title = br1.readLine()) != null) {//读一行标题

            String[] options = new String[5];
            for (int i = 0; i < options.length; i++) {
                options[i] = br1.readLine();//读四行
            }
            //添加个标题


            JLabel labelTitle = new JLabel(title);
            jPanel.add(labelTitle);
            labelTitle.setBounds(1, 1 + questionNumber * 100, 200, 16);
            //循环添加按钮


            for (int i = 0; i < options.length - 1; i++) {
                radioButton[j] = new JRadioButton(options[i], i == 0);
                jPanel.add(radioButton[j]);
                radioButton[j].setBounds(16, questionNumber * 100 + i * 16 + 16, 350, 16);
                j++;


            }

            titles[questionNumber] = title;


            //问题数+1
            questionNumber++;
        }
        jPanel.setPreferredSize(new Dimension(380, questionNumber * 100 + 100));
        JScrollPane scrollPane = new JScrollPane(jPanel);
        scrollPane.setSize(400, 500);
        panel2.getContentPane().add(scrollPane);


        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        tjiao.setBounds(500, 600, 70, 30);
        panel2.getContentPane().add(tjiao);
        panel2.setVisible(true);
        panel2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel2.setBounds(300, 40, 700, 680);
        panel2.setResizable(false);


    }

     public static void  setpingyu() {
         String pingyu[] = new String[5];

         JFrame jFrame1 = new JFrame("评语设置");
         jFrame1.setLayout(null);
         TextField[] textFields = new TextField[4];

         textFields[0] = new TextField("输入优秀的评语");
         textFields[1] = new TextField("输入良好的评语");
         textFields[2] = new TextField("输入及格的评语");
         textFields[3] = new TextField("输入不及格的评语");
         JComboBox jc[]=new JComboBox[4];      //创建下拉框
         //创建子项


         for (int i = 0; i < textFields.length; i++) {

             jc[i]=new JComboBox();
             jFrame1.add(jc[i]);
             textFields[i].setBounds(270, +i * 50 + 100, 400, 30);
             jFrame1.add(textFields[i]);
             jc[i].setBounds(30, +i * 50 + 100, 200, 30);

         }
         jc[0].addItem("--请选择优秀分数--");
         jc[0].addItem("90");
         jc[0].addItem("80");
         jc[0].addItem("70");
         //创建下拉框
         //创建子项
         jc[1].addItem("--请选择良好分数--");
         jc[1].addItem("80");
         jc[1].addItem("70");
         jc[1].addItem("60");

         jc[2].addItem("--请选择及格分数--");
         jc[2].addItem("70");
         jc[2].addItem("60");
         jc[2].addItem("50");

         jc[3].addItem("--请选择不及格分数--");

         jc[3].addItem("60");
         jc[3].addItem("50");
         jc[3].addItem("40");

         JButton baocun = new JButton("写入评语");

         baocun.setBounds(400, 600, 150, 30);

         jFrame1.getContentPane().add(baocun);

         jFrame1.setVisible(true);
         jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         jFrame1.setBounds(300, 40, 700, 680);
         jFrame1.setResizable(false);


         baocun.addActionListener(new ActionListener() {
                             String pingyu[] =new String[8];

             public void actionPerformed(ActionEvent e1) {
                  for (int i = 0;i<4;i++){
                      try {
                         pingyu[i] = jc[i].getSelectedItem().toString();
                          pingyu[i+4] = textFields[i].getText().toString();


                      }catch (Exception E){
                          JOptionPane.showMessageDialog(null, "有遗漏项目，请检查输入", "错误", JOptionPane.ERROR_MESSAGE);
                          break;
                      }
                  }
                 try {
                     File F1 = new File("教师文件//评语.txt");

                     BufferedWriter pw= new BufferedWriter (new OutputStreamWriter(new FileOutputStream(F1),"UTF-8"));
                     for (int i = 0;i<8;i++) {

                         pw.write(pingyu[i]+"\r\n");
                     }
                     pw.close();
                           jFrame1.dispose();
                 } catch (IOException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                 }
             }
         });

     }


    public One_Expree() {
        String tiku[] = new String[5];
        int number = 1;
        JFrame jFrame = new JFrame("导入");
        jFrame.setLayout(null);
        TextField[] tf1 = new TextField[5];
        JLabel[] jLabels = new JLabel[5];
        jLabels[0] = new JLabel("题目");
        jLabels[1] = new JLabel("A");
        jLabels[2] = new JLabel("B");
        jLabels[3] = new JLabel("C");
        jLabels[4] = new JLabel("D");

        for (int i = 0; i < tf1.length; i++) {
            tf1[i] = new TextField("");
            jFrame.add(tf1[i]);
            tf1[i].setBounds(100, +i * 50 + 100, 500, 30);
            jFrame.add(jLabels[i]);
            jLabels[i].setBounds(30, +i * 50 + 100, 100, 30);
        }
        JButton tjiao = new JButton("写入");
        JButton tjiao1 = new JButton("编写试卷");
        tjiao.setBounds(400, 600, 70, 30);
        tjiao1.setBounds(500, 600, 140, 30);
        jFrame.getContentPane().add(tjiao);
        jFrame.getContentPane().add(tjiao1);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(300, 40, 700, 680);
        jFrame.setResizable(false);


        tjiao.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e1) {
                try {
                    String b;
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("教师文件//题库.txt"), "UTF-8"));
                    while ((b = br1.readLine()) != null) {//读一行标题
                        String[] options = new String[5];
                        for (int i = 0; i < options.length; i++) {
                            options[i] = br1.readLine();//读四行
                        }


                        //问题数+1
                        questionNumber++;
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                boolean start = true;
                for (int i = 0; i < 5; i++) {

                    if (tf1[i].getText() == null || tf1[i].getText().equals("")) {

                        JOptionPane.showMessageDialog(null, "请完全输入所有选项", "alert", JOptionPane.ERROR_MESSAGE);
                        start = false;
                        break;

                    }

                }


                tiku[0] = questionNumber + 1 + "." + tf1[0].getText().toString();
                tiku[1] = tf1[1].getText().toString();
                tiku[2] = tf1[2].getText().toString();
                tiku[3] = tf1[3].getText().toString();
                tiku[4] = tf1[4].getText().toString();


                File F1 = new File("教师文件//题库.txt");

                questionNumber = 0;
                while (start) {
                    try {
                        BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(F1, true), "UTF-8"));

                        for (int k = 0; k < 5; k++) {

                            pw.write(tiku[k] + "/r/n");

                            tf1[k].setText("");

                        }
                        pw.write("" + "/r/n");

                        pw.close();

                        start = false;
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }


            }
        });
        tjiao1.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < 5; i++) {


                    if (tf1[i].getText() == null || tf1[i].getText().equals("")) {
                        while (i == 4) {
                            try {
                                jFrame.dispose();
                                daoru();

                                break;
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }


                    } else {
                        JOptionPane.showMessageDialog(null, "请保存", "错误", JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                }
            }
        });


    }

    public static void main(String[] args) {
      new One_Expree();
    }


}
