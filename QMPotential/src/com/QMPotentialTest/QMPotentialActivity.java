package com.QMPotentialTest;

import com.CapstonePhys.QMPotentialTest.R;
import com.QMPotentialTest.GaussianPotential;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.*;


import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class QMPotentialActivity extends Activity {

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Hardcoded Kappa and alpha for now:
        double kappa = 1.0;
        double alpha = 1.2;
        
        //Getting GaussianPotential data
        GaussianPotential gp = new GaussianPotential(kappa, alpha);
    	gp.initValues();
    	gp.integrateFun();
    	double T = gp.getT();
    	double R = 1-T;
        
        GraphViewSeries psiSeries = getData(gp);
        GraphViewSeries vSeries = getPotential(gp);
          
        GraphView graphView = new LineGraphView(  
              this // context  
              , "Psi(x) and V(x)" // heading  
        );  
        graphView.addSeries(psiSeries); // Add Psi data
        graphView.addSeries(vSeries);
          
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);  
        layout.addView(graphView);  
      
    }
    public static GraphViewSeries getData(GaussianPotential gp){
    	double[] x = gp.getXvals();
    	double[] psi = gp.getRealPsi();
    	GraphViewData[] data = new GraphViewData[gp.getNMAX()];
    	for(int i=0; i<gp.getNMAX(); i++){
    		data [i] = new GraphViewData(x[i], psi[i]);
    	}
    	GraphViewSeries series = new GraphViewSeries("Psi", Color.rgb(0, 200, 0), data);
    	return series;
    	}
    public static GraphViewSeries getPotential(GaussianPotential gp){
    	double alph = gp.getAlpha();
    	double[] x = gp.getXvals();
    	double[] V = new double[gp.getNMAX()];
    	for(int i = 0; i<gp.getNMAX(); i++){
    		V[i]= gp.van(alph, x[i]);
    	}
    	GraphViewData[] data = new GraphViewData[gp.getNMAX()];
    	for(int j=0; j<gp.getNMAX(); j++){
    		data [j] = new GraphViewData(x[j], V[j]);
    	}
    	GraphViewSeries series = new GraphViewSeries("V(x)", Color.rgb(200, 0, 0), data);
    	return series;
    }
}