/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalysy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Administrator
 */
public class WriteData {
    private String path;
    private String[] str;
    private String FileName;

    public WriteData(String[] str) {
        this("",str,"datafull2.txt");             
    }
    
    

    public WriteData(String path,String[] str,String FileName) {
        this.path = path;
        this.str = str;
        this.FileName = FileName;
        
        File dest = new File(path+"/"+FileName);
        
        OutputStream os = null;
        
        try{
            os = new FileOutputStream(dest,true);
            int i = 0;
            
            for(i = 0;i<str.length;i++){
                if(str[i]!=null){
            byte[] datas = str[i].getBytes();//编码
            os.write(datas,0,datas.length);
                }
            }
            System.out.println(dest.getAbsoluteFile());
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try {
                if(null!=os){
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
        
       
    
}
