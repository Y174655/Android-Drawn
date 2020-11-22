package com.example.drawn;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class JuXing extends Activity {
    /** Called when the activity is first created. */

    private SurfaceView surfaceView;
    private Paint paint;
    private SurfaceHolder surfaceHolder;
    private int scale_x = 20;
    private int scale_y = 50;
    private double ox=10;
    private double oy=240;
    private double x=0;
    private double y=0;
    private double t=0;
    private boolean flag=true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juxing);

        surfaceView = (SurfaceView) findViewById(R.id.juxing);
        surfaceHolder = surfaceView.getHolder();

        paint = new Paint();

        surfaceHolder.addCallback(new Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // TODO Auto-generated method stub
            }
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // TODO Auto-generated method stub
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        while (flag) {
                            try {
                                Thread.sleep(10);
                                Canvas canvas=surfaceHolder.lockCanvas();
                                canvas.drawRGB(255,255,255);
                                if(canvas!=null)
                                {
                                    //绘制坐标轴
                                    paint.setColor(Color.BLACK);
                                    canvas.drawText("O",20,220, paint);
                                    paint.setColor(Color.BLUE);
                                    canvas.drawLine(10,10,10,480, paint);
                                    canvas.drawText("Y",20,30, paint);
                                    paint.setColor(Color.GREEN);
                                    canvas.drawLine(0,240,320,240, paint);
                                    canvas.drawText("X",300,260, paint);
                                    //绘制三角形
                                    paint.setColor(Color.RED);
                                    Path path = new Path();
                                    float  x=50;
                                    float y=70;
                                    path.moveTo((float)ox,(float)oy);

                                    path.lineTo((float)ox,y);
                                    path.lineTo(x,y);
                                    path.lineTo(x,(float)oy);
                                    path.close();
                                    canvas.drawPath(path,paint);
                                   /* t+=0.1;
                                    x=t*scale_x;
                                    y=Math.sin(t)*scale_y+240;
                                    System.out.println("---------"+Math.round(Math.sin(t)));
                                    if (t>0) {
                                        paint.setColor(Color.RED);
                                        canvas.drawLine(10+(int)ox,(int)oy,10+(int)x,(int)y, paint);
                                        System.out.println(x+"s"+y);
                                    }
                                    ox=x;
                                    oy=y;
                                    if (t>15) {
                                        flag=false;
                                    }*/
                                }
                                surfaceHolder.unlockCanvasAndPost(canvas);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                       int height) {
                // TODO Auto-generated method stub
            }
        });
    }
}

