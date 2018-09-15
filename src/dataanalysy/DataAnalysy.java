/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalysy;

import com.csvreader.*;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
 
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
 
/**
 *
 * @author Administrator
 */
public class DataAnalysy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String src = "src/dataanalysy/bank-full.csv";
        try {
            CsvReader reader = new CsvReader(src, ';', Charset.forName("UTF-8"));          
            final int length = 46000;//行数，不要超过上限
            String[] header = {};
            String[] temp = new String[length];
            String[] jobs = new String[length/100];
            int i = 0,j = 0;
            int row = 1;
            
            while(reader.readRecord()){
                if(reader.getCurrentRecord()==0){
                    header = reader.getValues();//第一行
                }
                reader.getCurrentRecord();//读取当前位置
                reader.getRawRecord();//读取行
                
                temp[i++] = reader.getValues()[row]; //第row列信息存入temp中              
            }
            
            ST<String,Integer> map = new ST();
            
            for(String x: temp){
                if(x!=null&&!"job".equals(x)){
                    if(map.contains(x))
                    {
                        map.put(x, map.get(x)+1);
                    }
                    else
                        map.put(x, 1);
                }
            }
                       
            Iterator<String> iter = map.keys().iterator();
            
            while(iter.hasNext()){
                String key ;
                key = iter.next();
                System.out.println(key +"	" +map.get(key)+"\n");
                jobs[j++] = key +"	" +map.get(key)+"\n";               
            }
            WriteData wd = new WriteData(jobs);
                                   
            
                  
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataAnalysy.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
