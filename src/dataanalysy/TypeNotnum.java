/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalysy;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Administrator
 */
public class TypeNotnum {
    private String[] CountName;
    private int[] count;
    ArrayList<String> name;
    private int Mode;
    private String ModeName;

    public TypeNotnum(ArrayList<String> name1) {
        name = name1;
        ST<String,Integer> map = new ST();
        for(int i = 0;i<name.size();i++){
            if(map.contains(name.get(i))){
                map.put(name.get(i), map.get(name.get(i))+1);
            }
            else
                map.put(name.get(i), 1);
        }
        CountName = new String[map.size()];
        count = new int[map.size()];
        Iterator<String> iter = map.keys().iterator();
        int i = 0;
        while (iter.hasNext()) {
            String next = iter.next();
            CountName[i] = next;
            count[i++] = map.get(next);            
        }
        Mode = count[0];
        ModeName = CountName[0];
        for(int j = 1;j<count.length;j++){
            if(Mode<count[j]){
                Mode = count[j];
                ModeName = CountName[j];
            }
        }
    }

    
    
    public int[] getCount() {
        return count;
    }

    public String[] getCountName() {
        return CountName;
    }

    public String getModeName() {
        return ModeName;
    }



    public int getMode() {
        return Mode;
    }
    

    
  
}
