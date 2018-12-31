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

import static zenius.arnab.similartriangles.MainActivity.currentX;
import static zenius.arnab.similartriangles.MainActivity.currentY;
import static zenius.arnab.similartriangles.MainActivity.referenceDistance;

public class DrawSimilar extends View
{
    public static int chk=0;
    private Paint paint = new Paint();

    public static PointF pointP, pointQ,pointR;
    public static float distanceMidQ,distanceMidR,currentDistance;
    public static PointF midPQ=new PointF(0,0);
    public static PointF midPR=new PointF(0,0);

    public DrawSimilar(Context context) {
        super(context);
    }

    public DrawSimilar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawSimilar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        paint.setTextSize(50);

        ShapeProperties triangle=new ShapeProperties();



        RectF oval4=new RectF(LineView.pointA.x-50,LineView.pointA.y-50,LineView.pointA.x+50,LineView.pointA.y+50);
        RectF oval5=new RectF(LineView.pointB.x-50,LineView.pointB.y-50,LineView.pointB.x+50,LineView.pointB.y+50);
        RectF oval6=new RectF(LineView.pointC.x-50,LineView.pointC.y-50,LineView.pointC.x+50,LineView.pointC.y+50);


        //Drawing the skeleton triangle
        paint.setColor(Color.BLACK);
        triangle.constructShape(LineView.pointA,LineView.pointB,LineView.pointC,canvas,paint);
        //Marking the angles
        paint.setColor(Color.GREEN);
        if(MainActivity.isAngle)
        {
            triangle.drawAngles(LineView.pointA, LineView.pointB, LineView.pointC, canvas, paint, oval4, oval5, oval6);
        }
        //Making the three red dots
        paint.setColor(Color.RED);
        triangle.highlightVertices(LineView.pointA,LineView.pointB,LineView.pointC,canvas,paint);
        //Displaying side lengths
        paint.setColor(Color.GRAY);
        if(MainActivity.isRatio)
        {
            triangle.displaySideLengths(LineView.pointA, LineView.pointB, LineView.pointC, canvas, paint);
        }
        //Displaying Vertex names
        triangle.showPointNames("A","B","C",LineView.pointA,LineView.pointB,LineView.pointC,canvas,paint);
        //Displaying vertex angle values
        paint.setColor(Color.GRAY);
        if(MainActivity.isAngle)
        {
            triangle.showAngleValues(LineView.pointA, LineView.pointB, LineView.pointC, canvas, paint);
        }


        if(MainActivity.fingerCount>=2)
        {
            currentDistance=(float)Math.sqrt(Math.pow(MainActivity.reference.x-MainActivity.currentX,2)+Math.pow(MainActivity.reference.y-MainActivity.currentY,2));
            midPQ.x=(pointP.x+pointQ.x)/2;
            midPQ.y=(pointP.y+pointQ.y)/2;
            midPR.x=(pointP.x+pointR.x)/2;
            midPR.y=(pointP.y+pointR.y)/2;
            distanceMidQ=(float)Math.sqrt(Math.pow(midPQ.x-pointQ.x,2)+Math.pow(midPQ.y-pointQ.y,2));
            distanceMidR=(float)Math.sqrt(Math.pow(midPR.x-pointR.x,2)+Math.pow(midPR.y-pointR.y,2));
            while(distanceMidQ>=20 && distanceMidR>=20)
            {
                midPQ.x=(midPQ.x+pointQ.x)/2;
                midPQ.y=(midPQ.y+pointQ.y)/2;
                midPR.x=(midPR.x+pointR.x)/2;
                midPR.y=(midPR.y+pointR.y)/2;
                distanceMidQ=(float)Math.sqrt(Math.pow(midPQ.x-pointQ.x,2)+Math.pow(midPQ.y-pointQ.y,2));
                distanceMidR=(float)Math.sqrt(Math.pow(midPR.x-pointR.x,2)+Math.pow(midPR.y-pointR.y,2));
            }
            if(currentDistance>MainActivity.referenceDistance)       //Triangle size increases
            {
                MainActivity.referenceDistance=currentDistance;
                pointQ.x=(2*pointQ.x)-midPQ.x;
                pointQ.y=(2*pointQ.y)-midPQ.y;
                pointR.x=(2*pointR.x)-midPR.x;
                pointR.y=(2*pointR.y)-midPR.y;
            }
            else if(currentDistance<MainActivity.referenceDistance)          //Triangle size decreases
            {
                MainActivity.referenceDistance=currentDistance;
                pointQ.x=midPQ.x;
                pointQ.y=midPQ.y;
                pointR.x=midPR.x;
                pointR.y=midPR.y;
            }
        }


        //Drawing the skeleton triangle
        paint.setColor(Color.MAGENTA);
        if (MainActivity.toDrawSimilar) {
            triangle.drawSimilarShape(pointP, pointQ, pointR, canvas, paint);
            MainActivity.toDrawSimilar = false;
        } else
            triangle.constructShape(pointP, pointQ, pointR, canvas, paint);
        //Marking the angles
        RectF oval1 = new RectF(pointP.x - 50, pointP.y - 50, pointP.x + 50, pointP.y + 50);
        RectF oval2 = new RectF(pointQ.x - 50, pointQ.y - 50, pointQ.x + 50, pointQ.y + 50);
        RectF oval3 = new RectF(pointR.x - 50, pointR.y - 50, pointR.x + 50, pointR.y + 50);
        paint.setColor(Color.CYAN);
        if (MainActivity.isAngle) {
            triangle.drawAngles(pointP, pointQ, pointR, canvas, paint, oval1, oval2, oval3);
        }
        //Making the three dots
        paint.setColor(Color.YELLOW);
        triangle.highlightVertices(pointP, pointQ, pointR, canvas, paint);
        //Displaying side lengths
        paint.setColor(Color.BLACK);
        if (MainActivity.isRatio) {
            triangle.displaySideLengths(pointP, pointQ, pointR, canvas, paint);
        }
        //Displaying Vertex names
        triangle.showPointNames("P","Q","R",pointP, pointQ, pointR, canvas, paint);
        //Displaying vertex angle values
        paint.setColor(Color.BLACK);
        if (MainActivity.isAngle) {
            triangle.showAngleValues(pointP, pointQ, pointR, canvas, paint);
        }




        super.onDraw(canvas);


    }

    public static void setPointP(PointF point)
    {
        pointP = point ;
    }

    public static void setPointQ(PointF point)
    {
        pointQ = point ;
    }

    public static void setPointR(PointF point)
    {
        pointR=point;
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
