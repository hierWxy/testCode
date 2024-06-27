package com.Java游戏.贪吃蛇;

import com.Java游戏.贪吃蛇.obj.BodyObj;
import com.Java游戏.贪吃蛇.obj.FoodObj;
import com.Java游戏.贪吃蛇.obj.HeadObj;
import com.Java游戏.贪吃蛇.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWin extends JFrame {
    //游戏状态 0.未开始 1.游戏中 2.暂停 3.失败 4.通关 5.失败后重启 6.下一关
    public static int state =0;
    //分数
    public int score =0;
    //双缓存
    Image offScreenImage = null;
    //窗口宽高
    int winWidth=800;
    int winHeight=600;
    //创建蛇头对象
    HeadObj headObj=new HeadObj(GameUtils.rightImg,60,570,this);

    //创建蛇身集合
    public List<BodyObj> bodyObjList=new ArrayList<>();

    //食物
    public FoodObj foodObj=new FoodObj().getFood();
    public void launch() throws InterruptedException {
        //设置窗口可见
        this.setVisible(true);
        //设置窗口大小
        this.setSize(winWidth,winHeight);
        //设置窗口位置居中
        this.setLocationRelativeTo(null);
        //设置窗口标题
        this.setTitle("贪吃蛇游戏");

        //蛇身初始化
        bodyObjList.add(new BodyObj(GameUtils.bodyImg,30,570,this));
        bodyObjList.add(new BodyObj(GameUtils.bodyImg,0,570,this));
        //添加键盘事件
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    switch(state){
                        //未开始
                        case 0:
                            state=1;
                            break;
                        //暂停
                        case 1:
                            state=2;
                            repaint();
                            break;
                        //继续
                        case 2:
                            state=1;
                            break;
                        case 3:
                            //失败继续
                            state=5;
                            break;
                        case 4:
                            //下一关
                            state=6;
                        default:
                            break;
                    }
                }
            }
        });
        while(true)
        {
            if(state==1)
            {
                repaint(); //游戏中调用
            }
            //失败重启
            if(state==5)
            {
                state=0;
                resetGame();
            }
            //下一关
            if(state==6)
            {
                state=1;
                GameUtils.level++;
                resetGame();
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void paint(Graphics g) {
        //初始化双缓存图片
        if(offScreenImage==null)
        {
            offScreenImage=this.createImage(winWidth,winHeight);
        }
        //获取图片对象
        Graphics gImage=offScreenImage.getGraphics();
        //绘制一个灰色矩形
        gImage.setColor(Color.gray);
        gImage.fillRect(0,0,winWidth,winHeight);
        //网格线
        gImage.setColor(Color.black);
        //横线
        for (int i = 0; i <20 ; i++) {
            gImage.drawLine(0,i*30,600,i*30);
        }
        for (int i = 0; i <20 ; i++) {
            gImage.drawLine(i*30,0,i*30,600);
        }
        //绘制蛇身
        for (int i = bodyObjList.size()-1; i >=0 ; i--) {
            bodyObjList.get(i).paintSelf(gImage);
        }

        //绘制蛇头
        headObj.paintSelf(gImage);

        //绘制食物
        foodObj.paintSelf(gImage);
        //绘制关卡数
        GameUtils.drawWord(gImage,"第"+GameUtils.level+"关",Color.magenta,50,650,260);
        //绘制分数
        GameUtils.drawWord(gImage,score+"分:",Color.BLUE,50,650,330);
        //绘制提示语
        gImage.setColor(Color.gray);
        prompt(gImage);
        //将双缓存图片绘制到窗口
        g.drawImage(offScreenImage,0,0,null);
    }
    //绘制提示语
    void prompt(Graphics g){
        //未开始
        if(state==0)
        {
            g.fillRect(120,240,400,70);
            GameUtils.drawWord(g,"按下空格开始",Color.YELLOW,35,150,290);
        }
        if(state==2)
        {
            g.fillRect(120,240,400,70);
            GameUtils.drawWord(g,"按下空格继续",Color.YELLOW,35,150,290);
        }
        //失败
        if(state==3)
        {
            g.fillRect(120,240,400,70);
            GameUtils.drawWord(g,"游戏失败 ",Color.RED,35,150,290);
        }
        //通关
        if(state==4)
        {
            g.fillRect(120,240,400,70);
            GameUtils.drawWord(g,"达成条件游戏通关",Color.GREEN,35,150,290);
        }
        if(state==5)
        {
            g.fillRect(120,240,400,70);
            GameUtils.drawWord(g,"失败，空格重新开始游戏",Color.GREEN,35,150,290);
        }
    }
    //游戏重置
    void resetGame(){
        //1.关闭当前窗口
        this.dispose();
        //2.开启新窗口
        String[] args={};
        main(args);
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        try {
            gameWin.launch();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
