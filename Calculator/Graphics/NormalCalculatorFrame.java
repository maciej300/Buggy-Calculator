package Calculator.Graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class NormalCalculatorFrame {

	JPanel main;
	JLabel view;
	List<String> args = new ArrayList<>();
	
	String text = new String();
	
	public JPanel createNormalCalculatorUI() {
		
		main = new JPanel(new BorderLayout());
		
		view = new JLabel("0", SwingConstants.RIGHT);
		main.add(view, BorderLayout.NORTH);
		
		main.add(initializeElements(), BorderLayout.SOUTH);
		
		return main;
	}
	
	JPanel initializeElements() {
		
		JPanel initPanel = new JPanel(new GridLayout(4,4, 8, 8));
		
		addButtons(initPanel);
		
		return initPanel;
	}
	
	void addButtons(JPanel panel) {
		
		List<JButton> list = new ArrayList<>();
		
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
			
			x.addActionListener(actionL);
			
			x.setPreferredSize(new Dimension(60,60));
			
			panel.add(x);
			
		}
				
	}
	
	ActionListener actionL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getActionCommand().equalsIgnoreCase("1") || e.getActionCommand().equalsIgnoreCase("2") || e.getActionCommand().equalsIgnoreCase("3") ||
			   e.getActionCommand().equalsIgnoreCase("4") || e.getActionCommand().equalsIgnoreCase("5") || e.getActionCommand().equalsIgnoreCase("6") ||
			   e.getActionCommand().equalsIgnoreCase("7") || e.getActionCommand().equalsIgnoreCase("8") || e.getActionCommand().equalsIgnoreCase("9") || e.getActionCommand().equalsIgnoreCase("0")) {
				
				text += e.getActionCommand();
				view.setText(text);
				
			} else if(e.getActionCommand().equalsIgnoreCase("C")) {
				
				args.clear();
				text = new String();
				view.setText("0");
			
			}else if(view.getText() != "") { 
			
				if(e.getActionCommand().equalsIgnoreCase("+")) {
					
					args.add(text);
					
					view.setText("");
					text = new String();
					args.add("+");
					
				} else if(e.getActionCommand().equalsIgnoreCase("-")) {
					
					args.add(text);
					
					view.setText("");
					text = new String();
					args.add("-");
				
				} else if(e.getActionCommand().equalsIgnoreCase("*")) { 
				
					args.add(text);
					
					view.setText("");
					text = new String();
					args.add("*");
					
				} else if(e.getActionCommand().equalsIgnoreCase("/")) {
					
					args.add(text);
					
					view.setText("");
					text = new String();
					args.add("/");
					
				} else if(e.getActionCommand().equalsIgnoreCase("=")) {
	
					
					args.add(view.getText());
						
					long sum = Integer.parseInt(args.get(0));
				
						
					for(int i = 0; i < args.size(); i++) {
						
						if(args.get(i) == "+") {
								
							sum += Long.parseLong(args.get(i + 1));
							
						} else if(args.get(i) == "-") {
								
							sum -= Long.parseLong(args.get(i + 1));
								
						} else if(args.get(i) == "*") {
								
							sum *= Long.parseLong(args.get(i + 1));
								
						} else if(args.get(i) == "/") {
								
							sum /= Long.parseLong(args.get(i + 1));
								
						}
							
					}
					
					view.setText(String.valueOf(sum));		
				}
			}
		}
	};
	
}
