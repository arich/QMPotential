package com.QMTunnelling;

import com.CapstonePhys.QMPotentialTest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * StartScreen
 * @author AndrewRich
 * 
 *  This file is part of QMPotential.

    QMPotential is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    QMPotential is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with QMPotential.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
public class StartScreen extends Activity {
	TextView textBox;
	Button startButton;
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startscreen);
        
        startButton = (Button) findViewById(R.id.startButtonID);
        
        startButton.setOnClickListener(new ButtonHandler());
	}
	/**
	 * 
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
