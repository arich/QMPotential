package com.QMPotentialTest;

import java.lang.Math; // Gives Math.exp, Math.sin, etc.
import java.io.*;
import org.apache.commons.math.complex.*;

public class GaussianPotential {
	//fields:
	private final static double DXI = 0.01; //step by which to integrate
	private final static Complex E = new Complex(Math.E, 0);  //Complex version of E so we can have complex exponentials
	private final int NMAX = 1000;  //Maximum steps in either direction
	private double kappa = 1; //energy level of wave
	private double alpha = 1.0; //energy level of potential
	private double[] xvals = new double[2*NMAX]; // x-values from -NMAX to NMAX
	private Complex[] psi = new Complex[2*NMAX]; //array to hold wavefunction values
	
	static ComplexFormat format = new ComplexFormat(); //object to access method to format Complex objects

	//Constructor with params:
	public GaussianPotential(double kappa, double alpha) {
		super();
		this.kappa = kappa;
		this.alpha = alpha;
		this.fillXVals();
	}
	//methods:
	public double van(double ALPHA, double xi){//Gaussian potential function
		return ALPHA*Math.exp(-1*xi*xi);
	}
	public double unifV(double ALPHA, double width, double xi){//Uniform (constant) potential function
		if(xi>width/2 | xi< -width/2)
			return 0;
		else return ALPHA;
	}
	
	public static Complex Exp(Complex x){ //defining a complex exponential function
		return E.pow(x);
	}
	public static Double Exp(double x){ //let the Exp function handle a double parameter so we 
		return Math.exp(x);				//dont have to worry about which to use
	}
	public void fillXVals(){ //fills the xvals from -Nmax*DVI to NMAX*DVI. (usually -10 to 10)
		for(int h=0; h<2*NMAX; h++){
			this.xvals[h] = (h-NMAX)*DXI; //Start at -NMAX*DVI, go up to NMAX*DVI
		}
	}
	public Complex getA(){//finds A coef
		Complex psi1 = this.psi[0];
		Complex psi2 = this.psi[1];		
		double x1 = this.xvals[0];
		double x2 = this.xvals[1];
		
		Complex A = (psi2.multiply(Exp(Complex.I.multiply(Math.sqrt(kappa)*x2))));
		A = A.subtract(psi1.multiply(Exp(Complex.I.multiply(Math.sqrt(kappa)*x1))));
		Complex divisor = Exp(Complex.I.multiply(2*Math.sqrt(kappa)*x2)).
				subtract(Exp(Complex.I.multiply(2*Math.sqrt(kappa)*x1)));
		A = A.divide(divisor);
		return A;
	}
	public double getT(){//returns T coef
		Complex A = this.getA();
		double T = 1/(A.abs()*A.abs());
		return T;
	}
	public void initValues(){ //set two initial values for euler integrator
		this.psi[2*NMAX-1] = Exp(Complex.I.multiply(Math.sqrt(kappa)*NMAX*DXI)); //Initial value at NMAX*DXI
		this.psi[2*NMAX-2] = Exp(Complex.I.multiply(Math.sqrt(kappa)*(NMAX-1)*DXI));
	}
	public void integrateFun(){
		for(int i=2*NMAX-2; i>0; i--){ //Integration backwards
			psi[i-1] = psi[i].multiply(DXI*DXI*(-kappa+unifV(alpha, 3, xvals[i])));
			/*This is the unif.
			* or gaussian: van(ALPHA, xvals[i])));
			*/
			psi[i-1] = psi[i-1].add(psi[i].multiply(2));
			psi[i-1] = psi[i-1].subtract(psi[i+1]);
		}
	}
	public void printToFile(PrintWriter outFile){ // prints each real row to an output file
		for(int j = 0; j<2*NMAX; j++){
			outFile.println(xvals[j]+","+psi[j].getReal()+",");
		}
	}
	public double[] getXvals() {
		return xvals;
	}
	/**
	 * @return rPsi, an array of the real Psi values. 
	 */
	public double[] getRealPsi() {
		double rPsi[] = new double[2*NMAX];
		for (int i=0; i<2*NMAX; i++){
			rPsi[i] = psi[i].getReal();
		}
		return rPsi;
	}
	public int getNMAX(){
		return NMAX;
	}
	public double getKappa() {
		return kappa;
	}
	public void setKappa(double kappa) {
		this.kappa = kappa;
	}
	public double getAlpha() {
		return alpha;
	}
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	

}
