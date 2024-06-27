package com.Java游戏.贪吃蛇.obj;

import com.Java游戏.贪吃蛇.GameWin;

import java.awt.*;

public class GameObj {
    //定义游戏物体的图片
    Image image;
    //坐标
    int x;
    int y;
    //宽高
    int width=30;
    int height=30;
    //窗口类的引用
    GameWin frame;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GameWin getFrame() {
        return frame;
    }

    public void setFrame(GameWin frame) {
        this.frame = frame;
    }

    public GameObj() {
    }

    public GameObj(Image image, int x, int y, GameWin frame) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.frame = frame;
    }

    public GameObj(int x, Image image, int y, int width, int height, GameWin frame) {
        this.x = x;
        this.image = image;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frame = frame;
    }
    //定义物体绘制自身的方法
    public void paintSelf(Graphics g) {
        g.drawImage(image,x,y,null);
    }
}
