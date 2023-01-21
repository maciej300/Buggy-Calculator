package Calculator.Graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Calculator.Utils.AdvancedCalculatorUtils;

public class AdvancedCalculatorFrame {
	
	boolean trigger = false;
	
	String text = new String();
	
	AdvancedCalculatorUtils acu;
	
	JPanel buttonsPanel;
	
	JTextArea view;
	
	public JPanel createAdvancedCalculatorFrame() {
		
		JPanel panel = new JPanel(new BorderLayout());
		
		view = new JTextArea();
//		view = new JLabel("0", SwingConstants.RIGHT);
//		view.setVerticalAlignment(JLabel.BOTTOM);
		view.setFont(new Font("Arial", Font.BOLD, 30));
		view.setLineWrap(true);
		view.setEditable(false);
		view.getCaret().setVisible(false);
		
		panel.add(view);
		
		panel.add(buttonsPanel(), BorderLayout.SOUTH);
		
		view.setBackground(buttonsPanel.getBackground());
		
		return panel;
	}
	
	JPanel buttonsPanel() {
		
		buttonsPanel = new JPanel(new GridLayout(6,4,8,8));
		
		initializeButtons(buttonsPanel);
		
		return buttonsPanel;
	}
	
	void initializeButtons(JPanel panel) {
		
		List<JButton> list = new ArrayList<>();
		
		JButton sin = new JButton("sin");
		JButton cos = new JButton("cos");
		JButton tan = new JButton("tan");
		JButton sqrt = new JButton("sqrt");
		
		JButton log = new JButton("log");
		JButton pi = new JButton("pi");
		JButton bracketOpen = new JButton("(");
		JButton bracketClose = new JButton(")");
		
		JButton num1 = new JButton("1");
		JButton num2 = new JButton("2");
		JButton num3 = new JButton("3");
		
		JButton num4 = new JButton("4");
		JButton num5 = new JButton("5");
		JButton num6 = new JButton("6");
		
		JButton num7 = new JButton("7");
		JButton num8 = new JButton("8");
		JButton num9 = new JButton("9");
		
		JButton num0 = new JButton("0");
		
		JButton plus = new JButton("+");
		JButton minus = new JButton("-");
		JButton times = new JButton("*");
		JButton divide = new JButton("/");
		
		JButton sum = new JButton("=");
		JButton clear = new JButton("C");
		
		list.add(sin);
		list.add(cos);
		list.add(tan);
		list.add(sqrt);
		list.add(log);
		list.add(pi);
		list.add(bracketOpen);
		list.add(bracketClose);
		list.add(num7);
		list.add(num8);
		list.add(num9);
		list.add(plus);
		list.add(num4);
		list.add(num5);
		list.add(num6);
		list.add(minus);
		list.add(num1);
		list.add(num2);
		list.add(num3);
		list.add(divide);
		list.add(clear);
		list.add(num0);
		list.add(sum);
		list.add(times);
		
		for(JButton x : list) {
			
			x.setFocusPainted(false);
			x.setBorderPainted(false);
			x.setContentAreaFilled(false);
			x.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			
			x.setFont(new Font("Arial", Font.BOLD, 20));
			
			x.addActionListener(actionL);
			
			x.setPreferredSize(new Dimension(40,40));
			
			panel.add(x);
			
		}
		
	}
	
	ActionListener actionL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			char[] a = e.getActionCommand().toCharArray();
			
			if(e.getActionCommand().equalsIgnoreCase("=")) {
				
				acu = new AdvancedCalculatorUtils();
				
				view.setText("");
				
				view.setText(acu.initValues(text));
				
				text = view.getText();
				
				trigger = false;
				
				
			} else if(e.getActionCommand().equalsIgnoreCase("c")){
				
				text = new String();
				view.setText(text);
				trigger = false;
				
			}else if(e.getActionCommand().equalsIgnoreCase("*") || e.getActionCommand().equalsIgnoreCase("+") ||
					 e.getActionCommand().equalsIgnoreCase("/") || e.getActionCommand().equalsIgnoreCase("-")){
				
				
				if(!trigger) {
					text += e.getActionCommand();
					view.setText(text);
					trigger = true;
				} 
				
			} else if(Character.isDigit(a[0]) || e.getActionCommand().equalsIgnoreCase("(") || e.getActionCommand().equalsIgnoreCase(")")) {
				
				text += e.getActionCommand();
				view.setText(text);
				trigger = false;
				
			} else {
				
				switch(e.getActionCommand()) {
				
				case "tan":
					
					text+="tan(";
					view.setText(text);
					
					break;
				case "cos":
					
					text+="cos(";
					view.setText(text);
					
					break;
				case "sin":
					
					text+="sin(";
					view.setText(text);
					
					break;
				case "sqrt":
					
					text+="sqrt(";
					view.setText(text);
					
					break;
				case "log":
					
					text+="log(";
					view.setText(text);
					
					break;
					
				case "pi":
					
					text+="3.14";
					view.setText(text);
					
					break;
				
				}
				
			}
			
		}
	};

}
