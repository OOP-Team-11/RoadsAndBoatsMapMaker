package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import util.Observer.TileSelectObserver.TileSelectObserver;

public class TileSelectorView implements TileSelectObserver{
    private Canvas canvas;
    private GraphicsContext gc;

    public TileSelectorView(Canvas canvas){
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.setFontSize();
    }

    // public method called by GameLoop when refresh is necessary
    public void render(){
        drawViewDivider();
        drawCanvasBackGround();
        drawTileSelectBox();
        drawTerrainOptions();
    }
    private void setGraphicsContentStroke(Paint p){
        this.gc.setStroke(p);
    }
    private void setGraphicsContentFill(Paint p){
        this.gc.setFill(p);
    }
    private void setLineWidth(double width){
        this.gc.setLineWidth(width);
    }
    private void drawLine(double x1, double y1, double x2, double y2){
        this.gc.strokeLine(x1,y1,x2,y2);
    }
    private void drawViewDivider(){
        setGraphicsContentStroke(Color.BLACK);
        setGraphicsContentFill(Color.BLACK);
        setLineWidth(5.0);
        drawLine(0,0,0,canvas.getHeight()); // vertical stroke line
        drawLine(0,0,canvas.getWidth(),0); // horizontal stroke line
    }

    private void drawCanvasBackGround(){
        this.gc.setFill(Color.TEAL);
        this.gc.fillRect(2,2,canvas.getWidth(), canvas.getHeight());
    }
    private void setFontSize(){
        this.gc.setFont(Font.font(20));
    }
    private void drawTileSelectBox(){
        this.gc.setLineWidth(3);
        this.gc.strokeRoundRect(35,20,300,600,20,20);
    }
    private void drawArrowKeys(){
        // TODO
    }

    private void drawTerrainOptions(){
        this.gc.strokeRoundRect(40,700,35,35,5,5);
        this.gc.strokeRoundRect(90,700,35,35,5,5);
        this.gc.strokeRoundRect(140,700,35,35,5,5);
        this.gc.strokeRoundRect(190,700,35,35,5,5);
        this.gc.strokeRoundRect(240,700,35,35,5,5);
        this.gc.strokeRoundRect(290,700,35,35,5,5);
        this.gc.strokeText("1",53,760);
        this.gc.strokeText("2",103,760);
        this.gc.strokeText("3",153,760);
        this.gc.strokeText("4",203,760);
        this.gc.strokeText("5",253,760);
        this.gc.strokeText("6",303,760);
    }

}
