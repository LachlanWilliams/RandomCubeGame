package Engine.main;

import Engine.main.Objects.Player;
import Engine.main.Objects.SmartEnemy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter{
    private Game game;
    private Handler handler;
    private HUD hud;

    public Menu(Handler handler, Game game, HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        // all buttons for menu
        if(game.gameState == Game.STATE.Menu){
            // play button
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(100,100,ID.player,handler));
                handler.addObject(new SmartEnemy(250,250,ID.smartEnemy,handler));
            }
            // help button
            if (mouseOver(mx, my, 210, 250, 200, 64)){
                game.gameState = Game.STATE.Help;
            }
            // Quit button
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                System.exit(1);

            }

        }

        // all buttons for help
        if(game.gameState == Game.STATE.Help){
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.gameState = Game.STATE.Menu;
                return;
            }
        }

        if(game.gameState == Game.STATE.End){
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.gameState = Game.STATE.Menu;
                hud.reset();
                return;
            }
        }

    }

    public void mouseReleased(MouseEvent e){

    }

    public boolean mouseOver(int mx, int my, int x , int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else return false;
        }else return false;
    }

    public void tick(){

    }

    public void render(Graphics g){
        if(game.gameState == Game.STATE.Menu){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Menu", 240, 70);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 275, 190);


            g.setColor(Color.white);
            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 275, 290);

            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 275, 390);
        } else if(game.gameState == Game.STATE.Help){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 250, 70);

            g.setFont(fnt2);
            g.drawString("this needs work", 200,150);

            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 275, 390);

        }else if(game.gameState == Game.STATE.End){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.red);
            g.drawString("Game Over", 180, 70);

            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawString("you lost with a score of: " + hud.getScore(), 100,150);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 275, 390);

        }

    }
}
