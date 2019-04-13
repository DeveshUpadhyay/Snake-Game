/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

/**
 *
 * @author lenovo
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

public class Level2 {
   
    public static LinkedList<Point> list;
   
   
    public Level2(){
       init();
    }
    public void init(){
       list = new LinkedList<Point>();
    }
   
    public void DrawWall(Graphics g){
       g.setColor(Color.black);
       for(Point w: list)
       g.fillRect(w.x*snakeCals.BOX_WIDTH, w.y*snakeCals.BOX_HEIGHT, snakeCals.BOX_WIDTH, snakeCals.BOX_HEIGHT);
      
    }
    public void level2(){
       //wall 1
       list.add(new Point(3,4));
       list.add(new Point(4,4));
       list.add(new Point(5,4));
       list.add(new Point(6,4));
       list.add(new Point(7,4));
       list.add(new Point(8,4));
       list.add(new Point(9,4));
       list.add(new Point(10,4));
       list.add(new Point(11,4));
      
       //wall 2
       list.add(new Point(3,12));
       list.add(new Point(4,12));
       list.add(new Point(5,12));
       list.add(new Point(6,12));
       list.add(new Point(7,12));
       list.add(new Point(8,12));
       list.add(new Point(9,12));
       list.add(new Point(10,12));
       list.add(new Point(11,12));
    }

}
