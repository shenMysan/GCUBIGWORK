package exoress;

import Bean.Student;
import exoress.OneChoice;
import exoress.One_Expree;
import exoress.Show_Num;
import exoress.Show_score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;



public  class UI extends JFrame {
    //创建标签的引用
    JButton jb1;
    JButton jb2;

    //在构造界面的时候就进行初始化界面
    public UI() {
        init();
    }
    //初始化界面
    private void init() {
        //设置窗体
        this.setSize(600, 400);//设置窗体大小
        this.setLocationRelativeTo(null);//设置窗体居中显示
        this.setTitle("心理测试系统");//设置窗体标题
//        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//设置点击窗体x号时退出程序

        Container container = this.getContentPane();
        container.setLayout(null);

        jb1 = new JButton("管理员");
        jb2 = new JButton("学生");
        jb1.setSize(100, 100);


        jb2.setSize(100, 100);
        jb2.setLocation(250, 300);
        jb1.setLocation(250, 100);

        container.add(jb1);
        container.add(jb2);
        this.setBounds(500, 200, 600, 580);
        this.setResizable(false);


        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                closeJFrame();

                mangerUI mangerJFrame   =new mangerUI();
                mangerJFrame .setDefaultCloseOperation(DISPOSE_ON_CLOSE);



            }


        });

        jb2.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                closeJFrame();
                studentUI studentJFrame =  new studentUI();
                studentJFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            }


        });
        this.setVisible(true);


    }

    void closeJFrame(){
        this.dispose();

    }
    public static void main(String[] args) {

        UI ui=new UI();
        ui.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


}


class mangerUI extends JFrame {
    //创建标签的引用
    JButton jb1;
    JButton jb2;
    JButton jb3;

    //在构造界面的时候就进行初始化界面
    public mangerUI() {
        init();
    }
    //初始化界面
    private void init()
    {
        //设置窗体
        this.setSize(600,400);//设置窗体大小
        this.setLocationRelativeTo(null);//设置窗体居中显示
        this.setTitle("心理测试系统");//设置窗体标题


        Container container=this.getContentPane();
        container.setLayout(null);

        jb1 =new JButton("查看学生成绩");
        jb2 =new JButton("查看学生做题情况");
        jb3=new JButton("编辑试卷");
        jb1.setSize(150,100);
        jb2.setSize(150,100);
        jb3.setSize(150,100);


        jb1.setLocation(250,200);
        jb2.setLocation(250,30);
        jb3.setLocation(250,370);
        container.add(jb1);
        container.add(jb2);
        container.add(jb3);
        this.setBounds(500, 200, 700, 680);
        this.setResizable(false);

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Show_score();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Show_Num();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new One_Expree();
            }
        });



        this.setVisible(true);
    }
    void closeJFrame(){
        this.dispose();

    }







}



class studentUI extends JFrame {
    //创建标签的引用
    JButton jb1;
    JButton jb2;
    TextField [] tf =new TextField[4];
    JLabel [] bti =new JLabel[4];
    String []option =new String[4];

    //在构造界面的时候就进行初始化界面
    public studentUI() {
        // init();
        verify();
    }
    //初始化界面

    private  void  verify(){
        JFrame verifyJFrame =new JFrame("登录验证");
        verifyJFrame.setLayout(null);


        for (int i = 0;i < 4;i++ ){
            tf[i]=new TextField();
            bti[i]=new JLabel();
            verifyJFrame.add(tf[i]);
            tf[i].setBounds(150, i*50+70, 300, 30);
            bti[i].setFont(new Font("宋体", Font.BOLD, 20));
            bti[i].setBounds(10, i*50+70, 100, 30);
            verifyJFrame.add(bti[i]);
        }
        bti[0].setText("姓名");
        bti[1].setText("学号");
        bti[2].setText("专业");
        bti[3].setText("班级号");
        JButton   login  = new JButton("登录");
        login.setBounds(500,500,100,30);
        verifyJFrame.add(login);
        verifyJFrame.setVisible(true);
        verifyJFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        verifyJFrame.setBounds(300, 40, 700, 680);
        verifyJFrame.setResizable(false);
        login.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                boolean st =true;
                for (int i = 0;i < 4;i++ ){
                    if ( tf[i].getText()  ==null  || tf[i].getText().equals("")){

                        JOptionPane.showMessageDialog(null, "有遗漏项目，请检查输入", "alert", JOptionPane.ERROR_MESSAGE);
                        st=false;
                        break  ;

                    }
                    option [i]=tf[i].getText();


                }

                if ( Student.isEmpty(option[1])) {
                    while (st) {
                        Student student = new Student(option[0], option[1], option[2], option[3]);
                        verifyJFrame.dispose();
                        init(student);
                        st=false;
                    }

                }else{JOptionPane.showMessageDialog(null, "仅限答题一次", "alert", JOptionPane.ERROR_MESSAGE);}


            }  });

    }

    void  getyourSum( Object student  ){
        Student student1 = (Student) student;
        int i = student1.getSum();
        String s="";
        try {
            File F1 = new File("教师文件//评语.txt");
            InputStreamReader read = new InputStreamReader(new FileInputStream(F1), "utf-8");
            BufferedReader reader = new BufferedReader(read);
            String line;
            String[] options = new String[8];
            while ((line = reader.readLine()) != null) {
                for (int k = 1; k < 8; k++) {
                    options[0] =line;
                    options[k] =reader.readLine();

                }
            }
            if (i>=Integer.parseInt(options[0])){
                s=options[4];
            }else if (i<Integer.parseInt(options[0])&&i>=Integer.parseInt(options[1])){
                s=options[5];
            }else if(i<Integer.parseInt(options[1])&&i>=Integer.parseInt(options[2])){
                s=options[6];
            }else if (i<Integer.parseInt(options[2])){s=options[7];};







            reader.close();
            read.close();
        }catch (Exception e1) {
            e1.printStackTrace();
        }




        JOptionPane.showMessageDialog(null, "总分为"+i+","+s, "alert", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }


    private void init( Object student)
    {
        //设置窗体
        this.setSize(600,400);//设置窗体大小
        this.setLocationRelativeTo(null);//设置窗体居中显示
        this.setTitle("心理测试系统");//设置窗体标题
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//设置点击窗体x号时退出程序

        Container container=this.getContentPane();
        container.setLayout(null);

        jb1 =new JButton("开始考试");
        jb2 =new JButton("查看成绩");
        jb1.setSize(100,100);


        jb2.setSize(100,100);
        jb2.setLocation(300,0);
        container.add(jb1);
        container.add(jb2);



        jb2.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                getyourSum( student);



            }
        });
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    new OneChoice(student);   //做题
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });


        this.setVisible(true);
    }
    void closeJFrame(){
        this.dispose();

    }






}
