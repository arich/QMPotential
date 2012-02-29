package com.QMPotentialTest;

import com.CapstonePhys.QMPotentialTest.R;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.androidplot.ui.AnchorPosition;
import com.androidplot.ui.widget.Widget;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.LineAndPointRenderer;
import com.androidplot.xy.XLayoutStyle;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.YLayoutStyle;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class QMPotentialActivity extends Activity {
	private XYPlot myXYPlot;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Initialize XYPlot reference:
        myXYPlot = (XYPlot) findViewById(R.id.myXYPlot);
        
        //Add a new series
        myXYPlot.addSeries(new SimpleXYSeries(), LineAndPointRenderer.class, 
        		new LineAndPointFormatter(Color.rgb(0, 200, 0), Color.rgb(200, 0, 0), null));
        
        //set range labels per ticks
        myXYPlot.getGraphWidget().setTicksPerRangeLabel(4);
        
        //Reposition domain label for a cleaner look:
        Widget domainLabelWidget = myXYPlot.getDomainLabelWidget();
        
        myXYPlot.position(domainLabelWidget, 45, XLayoutStyle.ABSOLUTE_FROM_LEFT,
        		0, YLayoutStyle.ABSOLUTE_FROM_BOTTOM, AnchorPosition.LEFT_BOTTOM);
        
        //Get rid of visual positioning aids
        //("Luke, you've turned off your targeting computer, what's wrong?")
        myXYPlot.disableAllMarkup();
        
    }
}