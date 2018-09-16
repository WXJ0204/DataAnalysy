/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalysy;

import com.csvreader.*;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class DataNum {

    /**
     * @param args the command line arguments
     */
    static int row;

    public DataNum(int row1) {
        this.row = row1;
        try {
            PrintMsg();
        } catch (IOException ex) {
            Logger.getLogger(DataNum.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void PrintMsg() throws IOException {

        String src = "src/dataanalysy/bank-full.csv";
        try {
            CsvReader reader = new CsvReader(src, ';', Charset.forName("UTF-8"));
            ArrayList<Double> temp = new ArrayList<>();
            String Name = null;

            while (reader.readRecord()) {
                if (reader.getCurrentRecord() == 0) {
                    Name = reader.getValues()[row];
                    reader.readRecord();
                }
                reader.getCurrentRecord();//读取当前位置
                reader.getRawRecord();//读取行               
                temp.add(Double.parseDouble(reader.getValues()[row]));
            }
            TypeNum type = new TypeNum(Name, temp);//计算
            System.out.println(Name);
            System.out.println("方差：" + type.getVariance());
            System.out.println("极大值：" + type.getMax());
            System.out.println("极小值：" + type.getMin());
            System.out.println("极差：" + type.getRange());
            System.out.println("平均数：" + type.getAverage());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataAnalysy.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
