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
public class DataAnalysy1 {

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
            String[] temp1 = new String[length];
            String[] line = new String[length/100];
            int i = 0,j = 0,k = 0;
            int row = 12;
            
            while(reader.readRecord()){
                if(reader.getCurrentRecord()==0){
                    header = reader.getValues();//第一行
                }
                reader.getCurrentRecord();//读取当前位置
                reader.getRawRecord();//读取行
                
                temp[i] = reader.getValues()[row]; //第row列信息存入temp中
                temp1[i++] = reader.getValues()[row-1]; //第row-1列信息存入temp中
            }
            
            ST<String,Integer> map = new ST();//campaign-duration总值
            ST<String,Integer> map1 = new ST(); //campaign-人数
            
            
            for(k = 0;k<temp.length&&k<temp1.length;k++){
                String x = temp[k];
                String y = temp1[k];
                
                if(x!=null&&!"campaign".equals(x)){
                    if(map.contains(x))
                    {              
                        map.put(x, map.get(x)+Integer.parseInt(y));
                    }
                    else
                        map.put(x, Integer.parseInt(y));
                }
                if(x!=null&&!"campaign".equals(x)){
                    if(map1.contains(x))
                    {
                        map1.put(x, map1.get(x)+1);
                    }
                    else
                        map1.put(x, 1);
                }
            }
                       
            Iterator<String> iter = map.keys().iterator();
            while(iter.hasNext()){
                String key ;
                key = iter.next();
                System.out.println(key +"	" +map1.get(key)+"\n");
                //line[j++] = key +"	" +map.get(key)/map1.get(key)+"\n";               
            }                      
            
            //WriteData wd = new WriteData(line);
                                   
            
                  
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataAnalysy1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
