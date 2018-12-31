package zenius.arnab.similartriangles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    public static int currentX=0,currentY=0,fingerCount=0;
    public static long noOfTouches=0;
    private LineView mLineView ;
    private DrawSimilar drawSimilar;
    public static boolean toDrawSimilar=false,isAngle=false,isRatio=false;

    public static PointF pointA = new PointF(0,0);

    public static PointF pointB = new PointF(0,500);

    public static PointF pointC = new PointF(300,250);
    public static PointF prev=new PointF(0,0);
    public  static PointF reference=new PointF(0,0);
    public static float referenceDistance=0;
    private int mActivePointerId;





    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mLineView.chk=0;
        noOfTouches=0;

        setContentView(R.layout.activity_main);

        mLineView = (LineView) findViewById(R.id.lineView);
        drawSimilar=(DrawSimilar) findViewById(R.id.drawSimilar);
        drawSimilar.setVisibility(View.GONE);

        mLineView.setPointA(pointA);

        mLineView.setPointB(pointB);

        mLineView.setPointC(pointC);

        mLineView.draw();



        mLineView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int action= motionEvent.getAction();
                switch(action & MotionEvent.ACTION_MASK)
                {
                    case MotionEvent.ACTION_DOWN:
                    {
                        setCurrentX((int)motionEvent.getX());
                        setCurrentY((int)motionEvent.getY());
                        noOfTouches++;
                        if(noOfTouches<=3)
                            mLineView.draw();
                        break;
                    }
                    case MotionEvent.ACTION_MOVE:
                    {
                        //setCurrentX((int)motionEvent.getX());
                        //setCurrentY((int)motionEvent.getY());

                        break;
                    }
                }
                return true;
            }
        });


        drawSimilar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int action= motionEvent.getAction();
                // get pointer index from the event object
                int pointerIndex = motionEvent.getActionIndex();

                // get pointer ID
                int pointerId = motionEvent.getPointerId(pointerIndex);


                switch(action & MotionEvent.ACTION_MASK)
                {
                    case MotionEvent.ACTION_DOWN:
                    {
                        setCurrentX((int)motionEvent.getX());
                        setCurrentY((int)motionEvent.getY());
                        setPrevX(currentX);
                        setPrevY(currentY);

                        break;
                    }
                    case MotionEvent.ACTION_POINTER_DOWN:
                    {
                        reference.x=motionEvent.getX(pointerIndex);
                        reference.y=motionEvent.getY(pointerIndex);
                        referenceDistance=(float)Math.sqrt(Math.pow(reference.x-currentX,2)+Math.pow(reference.y-currentY,2));
                    }
                    case MotionEvent.ACTION_MOVE:
                    {
                        fingerCount=motionEvent.getPointerCount();
                        if(fingerCount<2) {
                            setCurrentX((int) motionEvent.getX());
                            setCurrentY((int) motionEvent.getY());

                        }

                        if(fingerCount>=2)
                        {
                            setCurrentX((int) motionEvent.getX());
                            setCurrentY((int) motionEvent.getY());

                            drawSimilar.draw();
                        }
                        ShapeProperties triangle=new ShapeProperties();
                        if(triangle.isPointInside(prev,DrawSimilar.pointP,DrawSimilar.pointQ,DrawSimilar.pointR) && motionEvent.getPointerCount()<2)
                        {
                            DrawSimilar.pointP.x = DrawSimilar.pointP.x + (currentX - prev.x);
                            DrawSimilar.pointP.y = DrawSimilar.pointP.y + (currentY - prev.y);
                            DrawSimilar.pointQ.x = DrawSimilar.pointQ.x + (currentX - prev.x);
                            DrawSimilar.pointQ.y = DrawSimilar.pointQ.y + (currentY - prev.y);
                            DrawSimilar.pointR.x = DrawSimilar.pointR.x + (currentX - prev.x);
                            DrawSimilar.pointR.y = DrawSimilar.pointR.y + (currentY - prev.y);
                            prev.x = currentX;
                            prev.y = currentY;
                            drawSimilar.draw();
                        }


                        break;
                    }
                }
                return true;
            }
        });

        final Button optionButton=(Button)findViewById(R.id.options);
        final Button angle=(Button)findViewById(R.id.angleSame);
        final Button ratio=(Button)findViewById(R.id.sameRatio);
        angle.setVisibility(View.GONE);
        ratio.setVisibility(View.GONE);
        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(noOfTouches>=3)
                {
                    optionButton.setVisibility(View.GONE);
                    angle.setVisibility(View.VISIBLE);
                    ratio.setVisibility(View.VISIBLE);
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Tap on three points on the screen!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        angle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                toDrawSimilar=true;
                isAngle=true;
                isRatio=false;
                DrawSimilar.setPointP(LineView.pointA);
                DrawSimilar.setPointQ(LineView.pointB);
                DrawSimilar.setPointR(LineView.pointC);
                mLineView.setVisibility(View.GONE);
                drawSimilar.setVisibility(View.VISIBLE);
                drawSimilar.draw();
            }
        });
        ratio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                toDrawSimilar=true;
                isRatio=true;
                isAngle=false;
                DrawSimilar.setPointP(LineView.pointA);
                DrawSimilar.setPointQ(LineView.pointB);
                DrawSimilar.setPointR(LineView.pointC);
                mLineView.setVisibility(View.GONE);
                drawSimilar.setVisibility(View.VISIBLE);
                drawSimilar.draw();
            }
        });

    }
    static void setCurrentX(int x)
    {
        currentX=x;
    }
    static void setCurrentY(int y)
    {
        currentY=y;
    }
    static void setPrevX(int x)
    {
        prev.x=x;
    }
    static void setPrevY(int y)
    {
        prev.y=y;
    }
}

