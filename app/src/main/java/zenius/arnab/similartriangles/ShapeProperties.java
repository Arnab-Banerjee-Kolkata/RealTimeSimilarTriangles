package zenius.arnab.similartriangles;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

public class ShapeProperties
{
    //          For triangles

    public void constructShape(PointF pointA, PointF pointB, PointF pointC, Canvas canvas, Paint paint)         // Draws the skeleton shape
    {
        canvas.drawLine(pointA.x, pointA.y, pointB.x, pointB.y, paint);
        canvas.drawLine(pointB.x, pointB.y, pointC.x, pointC.y, paint);
        canvas.drawLine(pointC.x, pointC.y, pointA.x, pointA.y, paint);
    }


    public void highlightVertices(PointF pointA, PointF pointB, PointF pointC, Canvas canvas, Paint paint)      //Draws small circles on vertices
    {
        canvas.drawCircle(pointA.x,pointA.y,25,paint);
        canvas.drawCircle(pointB.x,pointB.y,25,paint);
        canvas.drawCircle(pointC.x,pointC.y,25,paint);
    }


    public void displaySideLengths(PointF pointA, PointF pointB, PointF pointC, Canvas canvas, Paint paint)     //Displays the length of sides
    {
        float ab=(float)Math.sqrt(Math.pow(pointA.x-pointB.x,2)+Math.pow(pointA.y-pointB.y,2));
        float bc=(float)Math.sqrt(Math.pow(pointB.x-pointC.x,2)+Math.pow(pointB.y-pointC.y,2));
        float ca=(float)Math.sqrt(Math.pow(pointC.x-pointA.x,2)+Math.pow(pointC.y-pointA.y,2));
        canvas.drawText(Integer.toString((int)ab),(pointA.x+pointB.x)/2+15,(pointA.y+pointB.y)/2+35,paint);
        canvas.drawText(Integer.toString((int)bc),(pointB.x+pointC.x)/2+15,(pointB.y+pointC.y)/2+35,paint);
        canvas.drawText(Integer.toString((int)ca),(pointC.x+pointA.x)/2+15,(pointC.y+pointA.y)/2+35,paint);
    }


    public void drawAngles(PointF pointA, PointF pointB, PointF pointC, Canvas canvas, Paint paint, RectF oval1,RectF oval2,RectF oval3)             //Draws the angle arcs
    {
        float angle1,angle2,startAngle;
        angle1 =(float) Math.atan2(pointC.y-pointA.y,pointC.x-pointA.x);
        angle2 =(float) Math.atan2(pointC.y-pointB.y,pointC.x-pointB.x);
        float acb = (float)Math.abs(Math.toDegrees(angle1-angle2));
        if(acb>180)
            acb=360-acb;
        angle1 =(float) Math.atan2(pointA.y-pointC.y,pointA.x-pointC.x);
        angle2 =(float) Math.atan2(pointA.y-pointB.y,pointA.x-pointB.x);
        float bac = (float)Math.abs(Math.toDegrees(angle1-angle2));
        if(bac>180)
            bac=360-bac;
        angle1=(float)Math.atan2(pointB.y-pointA.y,pointB.x-pointA.x);
        angle2=(float)Math.atan2(pointB.y-pointC.y,pointB.x-pointC.x);
        float abc=(float)Math.abs(Math.toDegrees(angle1-angle2));
        if(abc>180)
            abc=360-abc;
        boolean clockwise=false;
        if(orientation(pointA,pointB,pointC)==1)
            clockwise=true;
        angle1=(float) Math.atan2(pointA.y-pointB.y,pointA.x-pointB.x);
        angle2=(float)Math.atan2(pointA.y-pointA.y,pointA.x-(pointA.x+10f));
        startAngle=(float)Math.abs(Math.toDegrees(angle1-angle2));
        if(clockwise==true)
            canvas.drawArc(oval1,360-startAngle,-bac,true,paint);
        else
            canvas.drawArc(oval1,360-startAngle,bac,true,paint);
        angle1=(float) Math.atan2(pointB.y-pointC.y,pointB.x-pointC.x);
        angle2=(float)Math.atan2(pointB.y-pointB.y,pointB.x-(pointB.x+10f));
        startAngle=(float)Math.abs(Math.toDegrees(angle1-angle2));
        if(clockwise==true)
            canvas.drawArc(oval2,360-startAngle,-abc,true,paint);
        else
            canvas.drawArc(oval2,360-startAngle,abc,true,paint);
        angle1=(float) Math.atan2(pointC.y-pointA.y,pointC.x-pointA.x);
        angle2=(float)Math.atan2(pointC.y-pointC.y,pointC.x-(pointC.x+10f));
        startAngle=(float)Math.abs(Math.toDegrees(angle1-angle2));
        if(clockwise==true)
            canvas.drawArc(oval3,360-startAngle,-acb,true,paint);
        else
            canvas.drawArc(oval3,360-startAngle,acb,true,paint);
    }


    public void showPointNames(String v1,String v2,String v3,PointF pointA, PointF pointB, PointF pointC, Canvas canvas, Paint paint)     //Display point names
    {
        if(pointA.y<=pointB.y && pointA.y<=pointC.y)        //A is uppermost point
        {

            canvas.drawText(v1,pointA.x,pointA.y-35f,paint);

        }
        else if(pointA.x<=pointB.x && pointA.x<=pointC.x)   //A is leftmost point
        {

            canvas.drawText(v1,pointA.x-60f,pointA.y,paint);

        }
        else if(pointA.x>=pointB.x && pointA.x>=pointC.x)   //A is rightmost
        {

            canvas.drawText(v1,pointA.x+35f,pointA.y,paint);

        }
        else   //A is bottom most
        {

            canvas.drawText(v1,pointA.x,pointA.y+60f,paint);

        }


        if(pointB.y<=pointA.y && pointB.y<=pointC.y)        //B is uppermost point
        {

            canvas.drawText(v2,pointB.x,pointB.y-35f,paint);

        }
        else if(pointB.x<=pointA.x && pointB.x<=pointC.x)   //B is leftmost point
        {

            canvas.drawText(v2,pointB.x-60f,pointB.y,paint);

        }
        else if(pointB.x>=pointA.x && pointB.x>=pointC.x)   //B is rightmost
        {

            canvas.drawText(v2,pointB.x+35f,pointB.y,paint);

        }
        else   //B is bottom most
        {

            canvas.drawText(v2,pointB.x,pointB.y+60f,paint);

        }


        if(pointC.y<=pointB.y && pointC.y<=pointA.y)        //C is uppermost point
        {

            canvas.drawText(v3,pointC.x,pointC.y-35f,paint);

        }
        else if(pointC.x<=pointB.x && pointC.x<=pointA.x)   //C is leftmost point
        {

            canvas.drawText(v3,pointC.x-60f,pointC.y,paint);

        }
        else if(pointC.x>=pointB.x && pointC.x>=pointA.x)   //C is rightmost
        {

            canvas.drawText(v3,pointC.x+35f,pointC.y,paint);

        }
        else   //C is bottom most
        {

            canvas.drawText(v3,pointC.x,pointC.y+60f,paint);

        }
    }


    public void showAngleValues(PointF pointA, PointF pointB, PointF pointC, Canvas canvas, Paint paint)        //Displays value of angles
    {
        float angle1,angle2,startAngle;
        angle1 =(float) Math.atan2(pointC.y-pointA.y,pointC.x-pointA.x);
        angle2 =(float) Math.atan2(pointC.y-pointB.y,pointC.x-pointB.x);
        float acb = (float)Math.abs(Math.toDegrees(angle1-angle2));
        if(acb>180)
            acb=360-acb;
        angle1 =(float) Math.atan2(pointA.y-pointC.y,pointA.x-pointC.x);
        angle2 =(float) Math.atan2(pointA.y-pointB.y,pointA.x-pointB.x);
        float bac = (float)Math.abs(Math.toDegrees(angle1-angle2));
        if(bac>180)
            bac=360-bac;
        angle1=(float)Math.atan2(pointB.y-pointA.y,pointB.x-pointA.x);
        angle2=(float)Math.atan2(pointB.y-pointC.y,pointB.x-pointC.x);
        float abc=(float)Math.abs(Math.toDegrees(angle1-angle2));
        if(abc>180)
            abc=360-abc;
        if(pointA.y<=pointB.y && pointA.y<=pointC.y)        //A is uppermost point
        {


            canvas.drawText((int)bac+"\u00B0",pointA.x,pointA.y+75f,paint);
        }
        else if(pointA.x<=pointB.x && pointA.x<=pointC.x)   //A is leftmost point
        {

            canvas.drawText((int)bac+"\u00B0",pointA.x+75f,pointA.y,paint);
        }
        else if(pointA.x>=pointB.x && pointA.x>=pointC.x)   //A is rightmost
        {

            canvas.drawText((int)bac+"\u00B0",pointA.x-75f,pointA.y,paint);
        }
        else   //A is bottom most
        {

            canvas.drawText((int)bac+"\u00B0",pointA.x,pointA.y-75f,paint);
        }


        if(pointB.y<=pointA.y && pointB.y<=pointC.y)        //B is uppermost point
        {

            canvas.drawText((int)abc+"\u00B0",pointB.x,pointB.y+75f,paint);
        }
        else if(pointB.x<=pointA.x && pointB.x<=pointC.x)   //B is leftmost point
        {

            canvas.drawText((int)abc+"\u00B0",pointB.x+75f,pointB.y,paint);
        }
        else if(pointB.x>=pointA.x && pointB.x>=pointC.x)   //B is rightmost
        {

            canvas.drawText((int)abc+"\u00B0",pointB.x-75f,pointB.y,paint);
        }
        else   //B is bottom most
        {

            canvas.drawText((int)abc+"\u00B0",pointB.x,pointB.y-75f,paint);
        }


        if(pointC.y<=pointB.y && pointC.y<=pointA.y)        //C is uppermost point
        {

            canvas.drawText((int)acb+"\u00B0",pointC.x,pointC.y+75f,paint);
        }
        else if(pointC.x<=pointB.x && pointC.x<=pointA.x)   //C is leftmost point
        {

            canvas.drawText((int)acb+"\u00B0",pointC.x+75f,pointC.y,paint);
        }
        else if(pointC.x>=pointB.x && pointC.x>=pointA.x)   //C is rightmost
        {

            canvas.drawText((int)acb+"\u00B0",pointC.x-75f,pointC.y,paint);
        }
        else   //C is bottom most
        {

            canvas.drawText((int)acb+"\u00B0",pointC.x,pointC.y-75f,paint);
        }
    }


    public void drawSimilarShape(PointF pointA, PointF pointB, PointF pointC, Canvas canvas, Paint paint)       //Draws Similar triangle
    {
        PointF pointP=new PointF();
        PointF pointQ=new PointF();
        PointF pointR=new PointF();
        PointF mid=new PointF();
        mid.x=(pointA.x+pointB.x)/2;
        mid.y=(pointA.y+pointB.y)/2;
        pointP.x=pointA.x;
        pointP.y=pointA.y;
        pointQ.x=(2*pointB.x)-mid.x;
        pointQ.y=(2*pointB.y)-mid.y;
        mid.x=(pointA.x+pointC.x)/2;
        mid.y=(pointA.y+pointC.y)/2;
        pointR.x=(2*pointC.x)-mid.x;
        pointR.y=(2*pointC.y)-mid.y;
        canvas.drawLine(pointP.x,pointP.y,pointQ.x,pointQ.y,paint);
        canvas.drawLine(pointQ.x,pointQ.y,pointR.x,pointR.y,paint);
        canvas.drawLine(pointR.x,pointR.y,pointP.x,pointP.y,paint);
        DrawSimilar.setPointP(pointP);
        DrawSimilar.setPointQ(pointQ);
        DrawSimilar.setPointR(pointR);
    }



    float sign (PointF p1, PointF p2, PointF p3)
    {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }

    public boolean isPointInside (PointF pt, PointF v1, PointF v2, PointF v3)       //Checks if the point is in the shape or not
    {
        boolean b1, b2, b3;

        b1 = sign(pt, v1, v2) < 0.0f;
        b2 = sign(pt, v2, v3) < 0.0f;
        b3 = sign(pt, v3, v1) < 0.0f;

        return ((b1 == b2) && (b2 == b3));
    }


    public static int orientation(PointF p1, PointF p2,PointF p3)           //Checks and returns if the points are oriented clockwise or counter clockwise
    {
        // See 10th slides from following link
        // for derivation of the formula
        float val = (p2.y - p1.y) * (p3.x - p2.x) -
                (p2.x - p1.x) * (p3.y - p2.y);

        if (val == 0) return 0;  // colinear

        // clock or counterclock wise
        return (val > 0)? 1: 2;
    }
}
