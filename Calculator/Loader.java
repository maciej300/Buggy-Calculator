package Calculator;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Calculator.Graphics.AdvancedCalculatorFrame;
import Calculator.Graphics.NormalCalculatorFrame;

public class Loader {
	
	static final int WIDTH = 380;
	static final int HEIGHT = 550;

	static NormalCalculatorFrame normalCalculator = new NormalCalculatorFrame();
	static AdvancedCalculatorFrame acf = new AdvancedCalculatorFrame();
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {

				JFrame frame = new JFrame("Calculator");
				
				frame.add(acf.createAdvancedCalculatorFrame());
				frame.pack();
				
				frame.setSize(new Dimension(WIDTH, HEIGHT));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
				frame.setVisible(true);
				
				
			}
		});
		
	}
}
