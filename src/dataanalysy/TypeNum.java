/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalysy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Administrator
 */
public class TypeNum {
    private double Average;
    private double Range;
    private double Max;
    private double Min;
    private double Variance;//方差
    private double Sum;//总数
    private final String Num_Name;
    ArrayList<Double> Num;

    public TypeNum(ArrayList<Double> Num) {
        this("",Num);
        
    }

    
    public TypeNum(String Num_Name,ArrayList<Double> Num1) {
        this.Num_Name = Num_Name;       
        this.Num = Num1;
        int length = Num.size();
        double[] temp = new double[length];
        int i ;
        
        for(i = 0;i<length;i++){
            //第一次循环可求出平均值,极值和极差          
            //统计总数
            
            Sum = Sum + Num.get(i);
            temp[i] = Num.get(i);
        }
        Average = Sum/(Num.size()+1);//平均数
 
        
        double VarianceSon =0;//方差的分子

        for ( i = 0;i<length;i++) {
            VarianceSon += (Average-Num.get(i))*(Average-Num.get(i));
        }
        
        Variance = Math.sqrt(VarianceSon/(Num.size()-1));//方差
        Arrays.sort(temp);
        Max = temp[length-1];
        Min = temp[0];
        Range = Max - Min;
        
    }


    
    
    public double getAverage() {
        return Average;
    }

    public double getMax() {
        return Max;
    }

    public double getMin() {
        return Min;
    }

    public double getRange() {
        return Range;
    }
    

    public double getVariance() {
        return Variance;
    }
    
    
    
}
