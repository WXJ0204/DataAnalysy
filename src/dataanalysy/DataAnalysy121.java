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
import java.util.Arrays;
import java.util.Iterator;
 
/**
 *
 * @author Administrator
 */
public class DataAnalysy121 {

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
            String[] line = new String[length];
            int i = 0,j = 0,k = 0;
            int row = 15,row1 = 5;
            
            while(reader.readRecord()){
                if(reader.getCurrentRecord()==0){
                    header = reader.getValues();//第一行
                }
                reader.getCurrentRecord();//读取当前位置
                reader.getRawRecord();//读取行
                
                temp[i] = reader.getValues()[row]; //第row列信息存入temp中
                temp1[i++] = reader.getValues()[row1]; //第row1列信息存入temp1中
            }
            
            ST<String,Integer> map = new ST();//poutcome,success 收入
            ST<String,Integer> map1 = new ST(); //poutcome,failure 收入
            ST<String,Integer> map2 = new ST(); //poutcome,unknwon 收入
            ST<String,Integer> map3 = new ST(); //poutcome,other 收入
                                             
            int[] count = new int[4];//以上四者的人数
            int[] allBalance = new int[4];//以上四者的收入
            
            
            for(k = 0;k<temp.length&&k<temp1.length;k++){
                String x = temp[k];
                String y = temp1[k];
                
                
                
                
                if(x!=null&&!"poutcome".equals(x)){
                    switch (x) {
                        case "success":
                            allBalance[0] += Integer.parseInt(y);
                            count[0]++;
                            break;
                        case "failure":
                            allBalance[1] += Integer.parseInt(y);
                            count[1]++;
                            break;
                        case "unknown":
                            allBalance[2] += Integer.parseInt(y);
                            count[2]++;
                            break;
                        case "other":
                            allBalance[3] += Integer.parseInt(y);
                            count[3]++;
                            break;
                        default:
                            System.out.println("数据异常");
                            System.exit(0);
                    }
                }

            }
                       
//            Iterator<String> iter = map.keys().iterator();
//            Iterator<String> iter1 = map1.keys().iterator();
//            Iterator<String> iter2 = map2.keys().iterator();
//            Iterator<String> iter3 = map3.keys().iterator();
//            while(iter.hasNext()){
//                String key ;
//                key = iter.next();
//                //System.out.println(Arrays.toString(count));
//                line[j++] = "success:"+"	"+key +"	" +map.get(key)+"\n";               
//            }
//            line[j++] = "\n";
//            
//            while(iter1.hasNext()){
//                String key ;
//                key = iter1.next();
//                //System.out.println(Arrays.toString(count));
//                line[j++] = "failure:"+"	"+key +"	" +map1.get(key)+"\n";               
//            }
//            line[j++] = "\n";
//            
//            while(iter2.hasNext()){
//                String key ;
//                key = iter2.next();
//                //System.out.println(Arrays.toString(count));
//                line[j++] = "unknown:"+"	"+key +"	" +map2.get(key)+"\n";               
//            }
//            line[j++] = "\n";
//            
//            while(iter3.hasNext()){
//                String key ;
//                key = iter3.next();
//                //System.out.println(Arrays.toString(count));
//                line[j++] = "other:"+"	"+key +"	" +map3.get(key)+"\n";               
//            }
            
            line[0] = "success:"+ allBalance[0]/count[0]+"\n";
            line[1] = "failure:"+ allBalance[1]/count[1]+"\n";
            line[2] = "unknown:"+ allBalance[2]/count[2]+"\n";
            line[3] = "other:"+ allBalance[3]/count[3]+"\n";

            WriteData wd = new WriteData("",line,"poutcome 平均balance.txt");
                                   
            
                  
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataAnalysy121.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
