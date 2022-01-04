package exoress;

import Bean.Student;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.IOException;

public class OneChoice extends JFrame {
    private static final String path = "学生文件//";// 表示当前目录
    private static String str;
    private static Object student;


    public OneChoice(   Object student             ) throws IOException {


        OneChoice.student = (Student) student;

        fileChooserTest(fileChooser( student ));//直接调用该方法



    }


    static void  startJFrame() throws IOException {
        new One_Choice(student,str);

    }




    public static File fileChooser(Object student) {
        JFileChooser jf = new JFileChooser(path);// 实例化文件选择器
        /*
         * 设置可选类型 参数有三个 分别为
         * ?JFileChooser.FILES_ONLY  仅仅选择文件
         * ?JFileChooser.DIRECTORIES_ONLY 仅仅选择目录
         * ?JFileChooser.FILES_AND_DIRECTORIES  既选择文件又选择目录
         */
        jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        // 设置文件过滤
        jf.setFileFilter(new FileFilter() {// FileFilter 为抽象类
            // 注意：这个不是实例化FileFilter类 ， 这是采用内部类的方式

            @Override
            public String getDescription() {// 显示为指定后缀名的文件
                return ".txt";
            }

            @Override
            public boolean accept(File f) {// 判断文件是否已java结尾
                if (f.getName().endsWith(".txt")) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        jf.showOpenDialog(null);// 设置打开时的窗口 null表示没有父窗口
        return jf.getSelectedFile(); // 返回得到选择器选择的文件
    }
    public static void fileChooserTest(File file) throws IOException {
        if (file == null) {
            JOptionPane.showMessageDialog(null, "你已取消选择");// 弹窗提示
            System.exit(0);
        } else {


            str =   file.getAbsolutePath();
            startJFrame();

        }
    }






    public static void main(String[] args) throws IOException {
        Student student =new Student();
        new  OneChoice(student);
    }

}
