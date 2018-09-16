/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalysy;

import com.csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DataNotnum {
    
    /**
     * @param args the command line arguments
     */
    static int row;

    public DataNotnum(int row1) {
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
            ArrayList<String> temp = new ArrayList<>();
            String Name = null;

            while (reader.readRecord()) {
                if (reader.getCurrentRecord() == 0) {
                    Name = reader.getValues()[row];
                    reader.readRecord();
                }
                reader.getCurrentRecord();//读取当前位置
                reader.getRawRecord();//读取行               
                temp.add(reader.getValues()[row]);
            }
            TypeNotnum type = new TypeNotnum(temp);//计算
            int count[] = type.getCount();
            String name[] = type.getCountName();

            
            System.out.println(Name);            
            for(int i = 0;i<name.length;i++){
                System.out.println(name[i]+":"+count[i]);
            }
            System.out.println("Mode:"+type.getModeName()+" ("+type.getMode()+")");
            


        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataAnalysy.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
