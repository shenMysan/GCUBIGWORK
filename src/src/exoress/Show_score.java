package exoress;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Show_score extends JFrame implements ActionListener {

    JFrame jFrame;
    JButton tjiao;
    int questionNumber = 0;
    JLabel[] jlb = new JLabel[1000];

    public Show_score() throws IOException {
        jFrame = new JFrame("问卷填写情况");
        jFrame.setLayout(null);
        JPanel JP = new JPanel();
        JP.setLayout(null);
        JP.setPreferredSize(new Dimension(400, 5000));

        File F2 = new File("教师文件\\Student.txt");
        int length = 0;
        String[] temp1 = new String[100];
        InputStreamReader read = new InputStreamReader(new FileInputStream(F2), "UTF-8");
        BufferedReader reader = new BufferedReader(read);
        String line;
        int length1=0;
        while ((line = reader.readLine()) != null) {

            temp1[length] = line;


            length++;
        }
        reader.close();
        read.close();
        System.out.println(length);
        for  (int i = 0; i <length /6 ; i++) {

            jlb[0 + questionNumber*6] = new JLabel("姓名" + temp1[length1 ] + "\r\n");
            jlb[1 + questionNumber*6] = new JLabel("学号" + temp1[length1+1] + "\r\n");
            jlb[2 + questionNumber*6] = new JLabel("专业" + temp1[length1+2] + "\r\n");
            jlb[3 + questionNumber*6] = new JLabel("班级" + temp1[length1+3] + "\r\n");
            jlb[4 +questionNumber*6] = new JLabel("总分" + temp1[length1+4] + "\r\n");
            jlb[5+ questionNumber*6] = new JLabel(""+"/r/n");



            questionNumber++;
            length1=   6+length1;
        }
        System.out.println(questionNumber);
        for (int i = 0; i <  length  ; i++) {
            jlb[i].setBounds(16,    i * 16   + 16, 400, 16);
            JP.add(jlb[i]);
        }


        JScrollPane scrollPane = new JScrollPane(JP);
        scrollPane.setSize(400, 500);
        JP.setPreferredSize(new Dimension(380,questionNumber * 100+100));
        jFrame.getContentPane().add(scrollPane);

        jFrame. setVisible(true);

        tjiao = new JButton("提交");
        tjiao.addActionListener(this);
        tjiao.setBounds(500, 600, 70, 30);
        jFrame.getContentPane().add(tjiao);
        jFrame.setBounds(300, 40, 700, 680);
        jFrame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        closeJFrame();      }

    void closeJFrame(){
        jFrame.dispose();

    }



    public static void main(String[] args) throws IOException {
        new Show_score();
    }
}