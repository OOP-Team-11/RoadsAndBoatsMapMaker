package mapMaker.view;
import mapMaker.model.tile.mmLocation;
import mapMaker.utilities.Observer.CursorObserver.mmCursorObserver;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import mapMaker.utilities.Observer.MapMakerObserver.mmMapMakerObserver;
import mapMaker.view.render.mmMapMakerCursorInfo;
import mapMaker.view.render.mmMapMakerRenderInfo;
import mapMaker.view.render.mmRenderToImageConverter;
import mapMaker.view.render.mmTileRenderInformation;
import mapMaker.view.utilities.mmAssets;

import java.util.Map;

public class mmMapMakerView implements mmCursorObserver, mmMapMakerObserver {

    private Canvas canvas;
    private GraphicsContext gc;
    private int cameraX;
    private int cameraY;
    private mmAssets mmAssets;
    private mmMapMakerCursorInfo cursorInformation;
    private mmMapMakerRenderInfo renderInformation;
    private boolean newDataFlag = true;
    private boolean firstTime = true;

    private Image cursorGreen = mmAssets.getInstance().GREEN_CURSOR;
    private Image cursorRed = mmAssets.getInstance().RED_CURSOR;


    private void recenterCanvas(){
        this.cameraX = (int)(canvas.getWidth()/2);
        this.cameraY = (int)(canvas.getHeight()/2);
    }

    public mmMapMakerView(Canvas canvas){

        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        mmAssets = mmAssets.getInstance();
        mmAssets.loadAssets();
    }

    // public method called by mmGameLoop when refresh is necessary
    public void render(){
        if(firstTime){
            recenterCanvas();
            firstTime = false;
        }
        if(newDataFlag){
            checkClearCanvas();
            drawMap();
            drawCursor();
            resetFlag();
        } else {
            // nothing to redraw
        }
    }

    private void resetFlag(){
        this.newDataFlag = false;
    }


    // camera zoom function to move in/out of placement area
    public void changeZoom(int moveZ){
        if(moveZ == 0){
            canvas.setScaleX(0.5);
            canvas.setScaleY(0.5);
        } else {
            canvas.setScaleX(moveZ);
            canvas.setScaleY(moveZ);
        }
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
    private void drawCursor(){

        if(cursorInformation.getIsCursorValid()){
           drawImage(cursorGreen,cursorInformation.getCursorMmLocation().getX(), cursorInformation.getCursorMmLocation().getY(), cursorInformation.getCursorMmLocation().getZ());
        } else {
           drawImage(cursorRed,cursorInformation.getCursorMmLocation().getX(), cursorInformation.getCursorMmLocation().getY(), cursorInformation.getCursorMmLocation().getZ());
        }
    }

    private void drawImage(Image image, int x, int y, int z){
        // first thing we want to do is get the axial coordinates
        int xx = x;
        int yy = z;
        if(xx%2 == 0){ // even
            double offsetHorizontal = image.getWidth()*xx*0.25;
            double offsetVertical = (image.getHeight())*(xx/2);
            gc.drawImage(image,image.getWidth()*xx-offsetHorizontal+cameraX,image.getHeight()*yy+offsetVertical+cameraY); // x, y
        } else {

            double offset = image.getHeight()*0.50;
            double offsetVertical = (image.getHeight())*((xx-1)/2);
            gc.drawImage(image,image.getWidth()*xx*0.75+cameraX,(image.getHeight()*((yy)) + offset +offsetVertical + cameraY)); // x, y
        }
    }

    private void checkClearCanvas(){
        if(newDataFlag){
            // clear all old information
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        } else {
            // nothing to clear
        }
    }

    private void drawMap(){

        if(renderInformation == null){
            // no information yet
        } else {

            for (Map.Entry<mmLocation, mmTileRenderInformation> entry : renderInformation.getTileInformation().entrySet())
            {
                // first time around we just render the terrain
                Image image = mmRenderToImageConverter.getTerrainImage(entry.getValue(), mmAssets);
                drawImage(image, entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ());

                // second time around we draw the rivers
                Image riverImage = mmRenderToImageConverter.getRiverImage(entry.getValue(), mmAssets);
                if(riverImage != null && image != mmAssets.SEA){
                    drawImage(riverImage, entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ());
                }
            }
        }
    }

    private void drawDivider(){
        setGraphicsContentStroke(Color.BLACK);
        setGraphicsContentFill(Color.BLACK);
        setLineWidth(5.0);
        drawLine(0,0,canvas.getWidth(),0);
    }


    @Override
    public void updateCursorInfo(mmMapMakerCursorInfo mmMapMakerCursorInfo) {
        this.newDataFlag = true;
        this.cursorInformation = mmMapMakerCursorInfo;
        this.cameraX = mmMapMakerCursorInfo.getCameraX()*5 +(int)(canvas.getWidth()/2);
        this.cameraY = mmMapMakerCursorInfo.getCameraY()*5 + (int)(canvas.getHeight()/2);
    }

    @Override
    public void updateMapMaker(mmMapMakerRenderInfo mmMapMakerRenderInfo) {
        recenterCanvas();
        this.newDataFlag = true;
        this.renderInformation = mmMapMakerRenderInfo;
    }
    
}
