package zenius.arnab.similartriangles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LineView extends View
{
    public static int chk=0;
    private Paint paint = new Paint();

    public static PointF pointA, pointB,pointC;

    public LineView(Context context) {
        super(context);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        if(MainActivity.noOfTouches<=3)
        {
            paint.setColor(Color.RED);
            paint.setTextSize(50);

            if(MainActivity.noOfTouches==1)
            {
                pointA.x=MainActivity.currentX;
                pointA.y=MainActivity.currentY;
                canvas.drawCircle(pointA.x,pointA.y,25,paint);
            }
            else if(MainActivity.noOfTouches==2)
            {
                pointB.x=MainActivity.currentX;
                pointB.y=MainActivity.currentY;
                canvas.drawCircle(pointA.x,pointA.y,25,paint);
                canvas.drawCircle(pointB.x,pointB.y,25,paint);
            }

        }
        if(MainActivity.noOfTouches==3)
        {
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(10);
            pointC.x=MainActivity.currentX;
            pointC.y=MainActivity.currentY;

            ShapeProperties triangle=new ShapeProperties();



            RectF oval1=new RectF(pointA.x-50,pointA.y-50,pointA.x+50,pointA.y+50);
            RectF oval2=new RectF(pointB.x-50,pointB.y-50,pointB.x+50,pointB.y+50);
            RectF oval3=new RectF(pointC.x-50,pointC.y-50,pointC.x+50,pointC.y+50);


            //Drawing the skeleton triangle
            paint.setColor(Color.BLACK);
            triangle.constructShape(pointA,pointB,pointC,canvas,paint);
            //Marking the angles
            paint.setColor(Color.GREEN);
            triangle.drawAngles(pointA,pointB,pointC,canvas,paint,oval1,oval2,oval3);
            //Making the three red dots
            paint.setColor(Color.RED);
            triangle.highlightVertices(pointA,pointB,pointC,canvas,paint);
            //Displaying side lengths
            paint.setColor(Color.BLACK);
            triangle.displaySideLengths(pointA,pointB,pointC,canvas,paint);
            //Displaying Vertex names
            triangle.showPointNames("A","B","C",pointA,pointB,pointC,canvas,paint);
            //Displaying vertex angle values
            paint.setColor(Color.GRAY);
            triangle.showAngleValues(pointA,pointB,pointC,canvas,paint);

        }
        super.onDraw(canvas);


    }

    public void setPointA(PointF point)
    {
        pointA = point ;
    }

    public void setPointB(PointF point)
    {
        pointB = point ;
    }

    public void setPointC(PointF point)
    {
        pointC=point;
    }

    public void draw()
    {
        invalidate();
        requestLayout();
    }
    static float min(float a, float b)
    {
        float temp=0;
        if(a<=b)
            temp=a;
        else
            temp=b;
        return temp;
    }
    static float max(float a,float b)
    {
        float temp=0;
        if(a>=b)
            temp=a;
        else
            temp=b;
        return temp;
    }


}
