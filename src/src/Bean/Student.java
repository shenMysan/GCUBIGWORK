package Bean;

import java.io.*;

public class Student {



    private  String name;
    private  String  snex;
    private  String      specialty;
    private  String  banji;
    private  int sum =0;
    private   static int  number=0;
    int j=0;

    public Student(){}
    public Student(String name, String snex, String specialty, String banji  ) {


        this.name = name;
        this.snex = snex;
        this.specialty = specialty;
        this.banji = banji;


    }

    public void setSum(int sum) {
        this.sum = sum;
        save();
    }

    public int getSum() {
        return sum;
    }

    public static boolean isEmpty(String snex) {

        boolean a=true;
        try {
            File F1 = new File("教师文件//Student.txt");
            InputStreamReader read = new InputStreamReader(new FileInputStream(F1), "utf-8");
            BufferedReader reader = new BufferedReader(read);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] options = new String[5];


                for (int i = 0; i < 5; i++) {

                    options[i] =reader.readLine();//读三行内容     一行为空

                }
                number ++;
                String s =options [0];
                if (s.equals(snex)){
                    a=false;
                }
            }

            reader.close();
            read.close();
        }catch (Exception e1) {
            e1.printStackTrace();
        }
        return  a;


    }

    @Override
    public String toString() {
        return
                name + "\r\n" +
                        snex + "\r\n" +
                        specialty+      "\r\n" +
                        banji +"\r\n" +
                        sum +"\r\n" +

                        " "+ "\r\n"
                ;
    }
    public void save(){
        try {
            File F1 = new File("教师文件//Student.txt");

            BufferedWriter pw= new BufferedWriter (new OutputStreamWriter(new FileOutputStream(F1,true),"UTF-8"));

            String infor =toString();
            pw.write( infor );

            pw.close();

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }



}
