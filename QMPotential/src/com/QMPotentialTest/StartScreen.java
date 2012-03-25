package com.QMPotentialTest;

import com.CapstonePhys.QMPotentialTest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartScreen extends Activity {
	TextView textBox;
	Button startButton;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startscreen);
        
        startButton = (Button) findViewById(R.id.startButtonID);
        
        startButton.setOnClickListener(new ButtonHandler());
	}
	/**
	 * @author AndrewRich
	 *	An OnClickListener to call handleButtonClick
	 */
	public class ButtonHandler implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			handleButtonClick();
		}
	}
	/**
	 * A method in the StartScreen Activity to start the QMPotentialActivity.
	 */
	private void handleButtonClick(){
		startActivity(new Intent(this, QMPotentialActivity.class));
	}
	
}
