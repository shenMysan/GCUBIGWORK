package exoress;

import Bean.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.NumberFormat;

public class One_Choice extends JFrame implements ActionListener {
    private static Object tjiao;
    int questionNumber = 0;

    int j = 0;
    int sum =0;
    JRadioButton[] radioButton = new JRadioButton[1000];
    JCheckBox[] Jcb = new JCheckBox[100];

    private int testtime =30;
    public int getSum() {
        return sum;
    }

    public One_Choice( Object studnet,String file) throws IOException {
        JFrame panel2 = new JFrame("问卷填写");
        panel2.setLayout(null);
        JLabel bti = new JLabel("a");
        bti.setFont(new Font("宋体", Font.BOLD, 20));
        bti.setBounds(400, 15, 800, 30);
        ClockDispaly clockDispaly = new ClockDispaly( bti,30);
        clockDispaly.start();
        panel2.getContentPane().add(bti);

        BufferedReader br1;


        br1 = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));

        JPanel JP =new JPanel();

        JP.setLayout(null);

        JP.setPreferredSize(new Dimension(400,5000));
        String title;
        while ((title = br1.readLine()) != null) {//读一行标题
            String[] options = new String[5];
            for (int i = 0; i < options.length; i++) {
                options[i] = br1.readLine();//读四行
            }
            //添加个标题
            JLabel labelTitle = new JLabel( title);
            JP.add(labelTitle);
            labelTitle.setBounds(1, 1 + questionNumber * 100, 400, 16);
            //循环添加按钮
            ButtonGroup buttonGroup = new ButtonGroup();
            for (int i = 0; i < options.length-1; i++) {
                radioButton[j] = new JRadioButton(options[i], i == 0);
                buttonGroup.add(radioButton[j]);
                JP.add(radioButton[j]);
                radioButton[j].setBounds(16, questionNumber * 100 + i * 16 + 16, 400, 16);
                j++;


            }

            //问题数+1
            questionNumber++;
        }
        JP.setPreferredSize(new Dimension(380,questionNumber * 100+100));
        JScrollPane scrollPane =new JScrollPane(JP);
        scrollPane.setSize(400,500);
        panel2.getContentPane().add(scrollPane);

        JButton tjiao;

        tjiao = new JButton("提交");
        tjiao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int[]socre =new int[100];
                int[] temp = new int[100];
                int length = 0;
                int length1 = 0;

                File F1 = new File("教师文件/one_num.txt");
                File F2 = new File("教师文件/one_score.txt");

                try {
                    InputStreamReader read = new InputStreamReader(new FileInputStream(F1), "utf-8");
                    BufferedReader reader = new BufferedReader(read);
                    InputStreamReader read1 = new InputStreamReader(new FileInputStream(F2), "utf-8");
                    BufferedReader reader1= new BufferedReader(read1);
                    String line;
                    String line1;
                    while ((line = reader.readLine()) != null) {
                        String s = "";
                        temp[length] = 0;
                        s += line ;
                        temp[length] += Integer.parseInt(s);
                        length++;

                    }
                    while ((line1 = reader1.readLine()) != null) {

                        socre[length1]   =Integer.parseInt(line1);

                        length1++;
                    }
                    reader.close();
                    read.close();
                    reader1.close();
                    read1.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                for(int i = 0;i < j;i++){
                    if(radioButton[i].isSelected()){
                        sum = sum + socre[i];
                        temp[i]++;


                    }
                }

                Student student1 =(Student)studnet;
                student1.setSum(sum);
                try {

                    FileWriter pw = new FileWriter(F1);

                    for(int i = 0;i < j;i++){
                        pw.write(temp[i]+"\r\n");
                    }
                    pw.close();
                    panel2.dispose();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        tjiao.setBounds(500, 600, 70, 30);
        panel2.getContentPane().add(tjiao);
        panel2.setVisible(true);
        panel2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel2.setBounds(300, 40, 700, 680);
        panel2.setResizable(false);
    }
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
    }


}



class ClockDispaly extends Thread{
    private JLabel lefttimer;
    private int testtime;
    public ClockDispaly(JLabel lt,int time) {
        lefttimer = lt;
        testtime = time * 60;
    }
    public void run(){
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumIntegerDigits(2);
        int h,m,s;
        while (testtime >= 0) {
            h = testtime / 3600;
            m = testtime % 3600 / 60;
            s = testtime % 60;
            StringBuffer stringBuffer = new StringBuffer("");
            stringBuffer.append("考试剩余时间为："+numberFormat.format(h)+":"+numberFormat.format(m)+":"+numberFormat.format(s));
            lefttimer.setText(stringBuffer.toString());
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            testtime = testtime - 1;
        }
        if (testtime <= 0) {
            JOptionPane.showMessageDialog(null, "考试结束");
            System.exit(0);
        }
    }
}

