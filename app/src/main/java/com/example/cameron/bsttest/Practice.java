package com.example.cameron.bsttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Arrays;

public class Practice extends Activity {

    private GLSurfaceView mGLView;
    private Context context; //apparently needed to pass context to check screen resolution
    public int screenLength;
    public int screenWidth;


   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }*/





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_practice);
        showScreenResolution();
        screenWidth = getScreenResolution(context)[0];
        screenLength = getScreenResolution(context)[1];




        //mGLView = new MyGLSurfaceView(this);


        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#CD5C5C"));
        Bitmap bg = Bitmap.createBitmap(screenWidth,screenLength, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bg);


        int x = screenWidth/2; int y = 50;
        int tempX = x;
        int tempY = y;
        int array[] = new int[15]; // array for holding nodes
        int testArray[] = {1,2,3,4,5,1,1,1,1,1,1,1,1,1,1};
        int nodeParent[] = {0,0,0,1,1,2,2,3,3,4,4,5,5,6,6};
        int xCoordinates[] = new int[15];
        int yCoordinates[] = new int[15];
        int textOffset = 15; // this number should be dependent of x and y
        xCoordinates[0] = x;
        yCoordinates[0] = y;

        //Need a method here to retrieve that tree array
        //Need to choose a starting difficulty

        for(int i=0; i<15; i++){
            tempY = y;
            if (i > 0) tempY = tempY + 150;
            Log.d("if test", "loop worked");
            if (i > 2) tempY = tempY + 150;
            Log.d("if 2 test", "loop worked");
            if (i > 6) tempY = tempY + 150;

            if(i==0){
                //nothing changes, uses original value for x
            }

            else if(i==1) {
                x = x/2;
                tempX=x;
            }
            else if(i==3) {
                x=x/2;
                tempX=x;
            }
            else if(i==7) {
                x=x/2;
                tempX=x;
            }

            else tempX= tempX +(x*2);
            //draw it

            //Save coordinates to arrays
            xCoordinates[i]=tempX;
            yCoordinates[i]=tempY;



            Log.d("coordinates", String.valueOf(tempX));

            canvas.drawCircle(tempX, tempY, 30, paint);
            paint.setTextSize(50);
            paint.setColor(Color.WHITE);
            canvas.drawText(String.valueOf(testArray[i]), tempX-textOffset, tempY+textOffset, paint);
            canvas.drawLine(tempX, tempY, xCoordinates[nodeParent[i]],yCoordinates[nodeParent[i]], paint);
            paint.setColor(Color.parseColor("#CD5C5C"));

            RelativeLayout rr = (RelativeLayout) findViewById(R.id.canvas);
            rr.setBackgroundDrawable(new BitmapDrawable(bg));




        }

        Log.d("arrayTest", Arrays.toString(xCoordinates));
        Log.d("arrayTest", Arrays.toString(yCoordinates));

        for(int i=0; i<15; i++){
            Log.d("for loop test", "loop worked");
            if(testArray[i]>0) {//test to see if space in array is filled
                tempY = y;
                if (i > 0) tempY = tempY + 150;
                Log.d("if test", "loop worked");
                if (i > 2) tempY = tempY + 150;
                Log.d("if 2 test", "loop worked");
                if (i > 6) tempY = tempY + 150;
                Log.d("if 3 test", "loop worked");//change addition depending on screen
            }




            }
        /*for(int i=0; i<14; i++) {
            if(i==1 || i==3 || i==7){
                y=y+80;
                x=x-80;
                tempX=x;
            }*/

            if(x!=tempX){

            }



            //canvas.drawCircle(x, y, 40, paint);
            //paint.setTextSize(30);
            //paint.setColor(Color.BLACK);
            //canvas.drawText("Hello"+i, x, y, paint);

            //RelativeLayout rr = (RelativeLayout) findViewById(R.id.canvas);
            //rr.setBackgroundDrawable(new BitmapDrawable(bg));

            // Create a GLSurfaceView instance and set it
            // as the ContentView for this Activity.

        }

    public void buttonPressed(View view) {
        Log.d("button", "button pressed");
        int buttonId = view.getId();
        Button myButton = (Button) findViewById(buttonId);
        String updatedText = "";


        switch (buttonId) {

            case R.id.test_custom_button1:
                updatedText="you pressed 1";
                break;

            case R.id.test_custom_button2:
                updatedText="you pressed 2";
                break;

            case R.id.test_custom_button3:
                updatedText="you pressed 3";
                break;
            case R.id.test_custom_button4:
                updatedText="you pressed 4";
                break;

            default:
                break;
        }
        myButton.setText(updatedText);

    }


    private void showScreenResolution(){
        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        String toastMsg;
        switch(screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                toastMsg = "Large screen";
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                toastMsg = "Normal screen";
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                toastMsg = "Small screen";
                break;
            default:
                toastMsg = "Screen size is neither large, normal or small";
        }
        Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();

    }

    private static int[] getScreenResolution(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int[] dimensions = new int[2];
        dimensions[0] = metrics.widthPixels;
        dimensions[1] = metrics.heightPixels;
        Log.d("dimensions", Arrays.toString(dimensions));

        return dimensions;
    }



}
