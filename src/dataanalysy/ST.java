/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalysy;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Administrator
 * @param <Key>
 * @param <Value>
 */
public class ST<Key extends Comparable<Key>,Value>
{
    private Node root;
    private static final boolean RED = true;
    private static final boolean  BLACK = false;
    
    private class Node
    {
        boolean color;//由其父节点指向它的连接的颜色
        Key key;
        Value val;
        Node left,right;
        int N;//这棵子树中节点的数目

        public Node(boolean color, Key key, Value val, int N) {
            this.color = color;
            this.key = key;
            this.val = val;
            this.N = N;
        }
        
        
    }
        
    private boolean isRed(Node h)
    {
        if(h == null)
        { return BLACK;}
        return h.color == RED;
    }
    
    private Node rotateLeft(Node h)
    {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    
    private  Node rotateRight(Node h)
    {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    
    private void flipColors(Node h)
    {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    public int size()
    {
        return size(root);
    }
    
    private int size(Node h)
    {
        if(h == null) 
        {
            return 0;
        }
        else {return h.N;}
    }
    
    public void put(Key key,Value val)
    {  //查找key，找到更新它的值，否则为其新建一个结点
        root = put(root, key, val);
        root.color = BLACK;
    }
    
    private Node put(Node h,Key key,Value val)
    {
        if( h == null )
            return new Node(RED, key, val, 1);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left,key, val);
        else if(cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;
        
        if(isRed(h.left)&&isRed(h.left.left)) h = rotateRight(h);
        if(!isRed(h.left)&&isRed(h.right)) h = rotateLeft(h);
        if(isRed(h.left)&&isRed(h.right)) flipColors(h);
        h.N = 1 + size(h.left) + size(h.right);
        return h;
    }
    
    public boolean contains(Key key)
    {
        return contains(root,key);
    }
    
    private  boolean  contains(Node h,Key key)
    {
        if( h == null )
            return false;
        int cmp = key.compareTo(h.key);
        if (cmp < 0) return contains(h.left,key);
        else if(cmp > 0) return contains(h.right, key);
        else  return true;
    }
    
   
    
    public Value get(Key key)
    {
        return get(root, key);
    }
    
    private Value get(Node h,Key key)
    {
        if( h == null )
            return null;
        int cmp = key.compareTo(h.key);
        if (cmp < 0) return get(h.left,key);
        else if(cmp > 0) return get(h.right, key);
        else  return h.val ;
    }
    
    public Key min()
    {
        return min(root).key;
    }
    
    private Node min(Node h)
    {
        if(h.left == null) return h;
        else return min(h.left);
    }
    
    public Key max()
    {
        return max(root).key;
    }
    
    private Node max(Node h)
    {
        if(h.right == null) return h;
        else return max(h.right);
    }
    
    public  Iterable<Key> keys()
    {
        return keys(min(),max());
    }
    
    public Iterable<Key> keys(Key lo,Key hi)
    {        
        Queue<Key> queue = new PriorityQueue<>();
        keys(root, queue, lo, hi);
        return queue;
    }
    
    private void keys(Node x,Queue<Key> queue,Key lo,Key hi)
    {
        if(x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo<0) keys(x.left, queue, lo, hi);
        if(cmplo <= 0 && cmphi >= 0) queue.add(x.key);
        if(cmphi>0) keys(x.right,queue,lo,hi);
        
    }
    
    public static void main(String[] args) {
    ST<Integer,String> app = new ST();
    app.put(1, "hhh");
    app.put(2, "qixj");
    app.put(0, "23sh");
    app.put(98, "q45j");
    app.put(-9, "h3dh");
    app.put(19, "qidfj");
        System.out.println(app.get(19));
    
}
}