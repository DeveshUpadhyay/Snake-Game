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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.Random;


public class snakeCals implements Runnable,KeyListener{
     
    private Thread thread;
    private Display display;
    private String title;
    private int width,height;
    private BufferStrategy buffer;
    private Graphics g;
    private LinkedList<Point> snake;
    private int direction = Direction.no_direction ;
   
    //rectangle set up
    public static final int WIDTH = 15;
    public static final int HEIGHT =15;
    public static final int BOX_WIDTH =30;
    public static final int BOX_HEIGHT =30;
     
    //Fruit
    private Point fruit;
    //score
    private int score;
    private Level2 level2;
   
    public snakeCals(String title,int width,int height){
       this.title = title;
       this.width = width;
       this.height = height ;
      
     }
   
     
    public void init(){
        display = new Display(title,width,height);
        display.getFrame().addKeyListener(this);
       snake = new LinkedList<Point>();
       level2 = new Level2();
   
         reStart();
        randFruit(); 
     
     }

    public void Draw(Graphics g){
       DrawRect(g);
       DrawSnake(g);
       DrawFruit(g);
       DrawScore(g);
       level2.DrawWall(g);

    }
    public void DrawRect(Graphics g){
       Color color = new Color(179,250,130);
       g.setColor(color);
        g.fillRect(0, 0, WIDTH*BOX_WIDTH, HEIGHT*BOX_HEIGHT);
        g.setColor(Color.red);

    }
   
    private void DrawSnake(Graphics g){
       g.setColor(Color.blue);

           for(Point p:snake){
     
           g.fillRect(p.x*BOX_WIDTH, p.y*BOX_HEIGHT, BOX_WIDTH,BOX_HEIGHT );
       }
           g.setColor(Color.black);
    }
   
      
    public void reStart(){
       direction  = Direction.no_direction;
       snake.clear();
       level2.list.clear();
       score = 0;
       snake.add(new Point(9,7));
       snake.add(new Point(8,7));
        snake.add(new Point(7,7));
        
    }
    public void DrawFruit(Graphics g){
       g.setColor(Color.red);
       g.fillOval(fruit.x*BOX_WIDTH, fruit.y*BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
        g.setColor(Color.BLACK);
    }
    private void DrawScore(Graphics g){
       g.setColor(Color.red);
       g.setFont(new Font("arial",Font.PLAIN,37));
       g.drawString("Score = "+score,22,HEIGHT*BOX_HEIGHT+40);
       g.setColor(Color.black);
    }
      
        public void randFruit(){
           Random rand = new Random();
           int randX = rand.nextInt(WIDTH);
           int randY = rand.nextInt(HEIGHT);
           fruit = new Point(randX,randY);
           while(snake.contains(fruit) || level2.list.contains(fruit)){
               fruit = new Point(randX,randY);
              
           }
          
        }
      
     
    public void move(){
       Point head = snake.peekFirst();
       Point newPoint = head;
       switch(direction){
       case Direction.up:
           newPoint = new Point(head.x,head.y-1);
           break;
       case Direction.down:
           newPoint = new Point(head.x,head.y+1);
           break;
       case Direction.right:
           newPoint = new Point(head.x+1,head.y);
           break;
       case Direction.left:
           newPoint = new Point(head.x-1,head.y);
           break;
       }
         
       if(newPoint.x>= WIDTH){
           newPoint = new Point(newPoint.x=0,newPoint.y);
       }
      
       else if (newPoint.x<0){
           newPoint = new Point(newPoint.x=WIDTH-1,newPoint.y);
       }
       else if(newPoint.y>=HEIGHT){
           newPoint = new Point(newPoint.x, newPoint.y=0);
          
       }else if (newPoint.y<0){
           newPoint = new Point(newPoint.x,newPoint.y=HEIGHT-1);
       }
       if(snake.contains(newPoint) || level2.list.contains(newPoint)){
         
          reStart(); 
           return;
    }
     
       snake.remove(snake.peekLast());

       if(newPoint.equals(fruit) ){
           Point add = (Point) newPoint.clone();
           score  = score + 5;
           snake.push(add);
           if(score >= 20){
           level2.level2();
           }
           randFruit();
       }  
       snake.push(newPoint);

      
        
   
    }
    public void render(){
      
     buffer = display.getCanvas().getBufferStrategy();
     if(buffer == null ){
       display.getCanvas().createBufferStrategy(3);
       return;
      }
       g = buffer.getDrawGraphics();
       g.clearRect(0, 0, width, height);
       //draw here
        
       Draw(g);
      

       //draw end
       buffer.show();
       g.dispose();
      
    }
    public synchronized void start(){
     
     if(thread ==null){
        thread = new Thread(this);
        thread.start();
     }
    }
    public synchronized void stop(){
        
       try {
           thread.join();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
      
    }
     
     public void run(){
   
           init();
           while(true){
           render();
           move();
           Thread.currentThread();
          try {
             Thread.sleep(100);
         } catch (InterruptedException e) {
                  e.printStackTrace();
               }
           }
        
          
       }
    public void keyPressed(KeyEvent e) {
       switch(e.getKeyCode()){
       case KeyEvent.VK_UP:
          if(direction != Direction.down)
          direction = Direction.up;
          break;
       case KeyEvent.VK_DOWN:
          if(direction != Direction.up)
          direction =Direction.down;
          break;
       case KeyEvent.VK_LEFT:
          if(direction != Direction.right)
          direction =Direction.left;

       break;
      
       case KeyEvent.VK_RIGHT:
          if(direction != Direction.left)
          direction = Direction.right;
          break;
       }
      
    }
    public void keyReleased(KeyEvent e) {
      
    }
    public void keyTyped(KeyEvent e) {
      
    }
     
   
   

}
