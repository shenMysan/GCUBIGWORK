package exoress;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Show_Num extends JFrame implements ActionListener {

    JFrame jFrame;
    JButton tjiao;
    int questionNumber = 0;
    JLabel[] jlb = new JLabel[1000];

    public Show_Num() throws IOException {
        jFrame = new JFrame("问卷填写情况");
        jFrame.setLayout(null);
        JPanel JP = new JPanel();
        JP.setLayout(null);
        JP.setPreferredSize(new Dimension(400, 5000));

        File F2 = new File("教师文件/one_num.txt");
        int length = 0;
        int[] temp1 = new int[100];
        InputStreamReader read = new InputStreamReader(new FileInputStream(F2), "UTF-8");
        BufferedReader reader = new BufferedReader(read);
        String line;
        while ((line = reader.readLine()) != null) {
            String s = "";
            temp1[length] = 0;
            s += line;
            temp1[length] += Integer.parseInt(s);
            length++;
        }
        reader.close();
        read.close();
        for (int i = 0; i < length / 4; i++) {
            JLabel lb = new JLabel("第" + (1 + questionNumber) + "题");
            lb.setBounds(1, 1 + questionNumber * 100, 400, 16);
            JP.add(lb);
            for (int j = 0; j < 4; j++) {
                jlb[j + 4 * questionNumber] = new JLabel((j + 1) + "选项有" + temp1[j + 4 * questionNumber] + "人选择\r\n");
                jlb[j + 4 * questionNumber].setBounds(16, questionNumber * 100 + j * 16 + 16, 400, 16);
                JP.add(jlb[j + 4 * questionNumber]);
            }
            questionNumber++;
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
        new Show_Num();
    }


}