package com.Java游戏.贪吃蛇.obj;

import com.Java游戏.贪吃蛇.GameWin;
import com.Java游戏.贪吃蛇.utils.GameUtils;

import java.awt.*;
import java.util.Random;

public class FoodObj extends GameObj{
    //随机函数
    Random r=new Random();


    public FoodObj() {
        super();
    }

    public FoodObj(Image image, int x, int y, GameWin frame) {
        super(image, x, y, frame);
    }

    //获取食物
    public FoodObj getFood(){
        return new FoodObj(GameUtils.foodImg, r.nextInt(20)*30,(r.nextInt(19)+1)*30,this.frame );
    }
    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
    }
}
