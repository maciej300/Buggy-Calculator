package Calculator.Utils;

import java.util.ArrayList;
import java.util.List;

public class AdvancedCalculatorUtils {
	
	public String initValues(String text) {
		
		List<String> clarified = new ArrayList<>();
		List<String> operations = new ArrayList<>();
		
		String baseText = text.trim().replaceAll(" +", "");
		
		char[] charArray = baseText.toCharArray();
		
		String toAdd = new String();
		int index = 0;
		for(char x : charArray) {
			
			if(Character.isDigit(x)) {
				
				toAdd += x;
				
			} else if(!Character.isAlphabetic(x)) {
				
				toAdd.trim().replaceAll(" +", "");
				
				switch(x) {
				
				case '+':
					clarified.add(toAdd);
					toAdd = new String();
					clarified.add("+");
					break;
				case '-':
					clarified.add(toAdd);
					toAdd = new String();
					clarified.add("-");
					break;
				case '/':
					clarified.add(toAdd);
					toAdd = new String();
					clarified.add("/");
					break;
				case '*':
					clarified.add(toAdd);
					toAdd = new String();
					clarified.add("*");
					break;
				case '^':
					clarified.add(toAdd);
					toAdd = new String();
					clarified.add("^");
					break;
				case '.':
					toAdd += ".";
					break;
				case '(':
					clarified.add(toAdd);
					toAdd = new String();
					clarified.add("(");
					break;
				case ')':
					clarified.add(toAdd);
					toAdd = new String();
					clarified.add(")");
					break;
				}
				
			} else if(Character.isAlphabetic(x)) {
				
				toAdd += x;
				
				switch(toAdd) {
				
				case "sqrt":
					clarified.add(toAdd);
					toAdd = new String();
					break;
				case "cos":
					clarified.add(toAdd);
					toAdd = new String();
					break;
				case "sin":
					clarified.add(toAdd);
					toAdd = new String();
					break;
				case "tan":
					clarified.add(toAdd);
					toAdd = new String();
					break;
				case "log":
					clarified.add(toAdd);
					toAdd = new String();
					break;
				}
				
				
			}
			index++;
		}
		
		if(index == charArray.length) {
			clarified.add(toAdd);
			toAdd = new String();
		}
		
		for(String x : clarified) {
			
			if(x.isBlank()) {
				continue;
			} else {
				operations.add(x);
			}
			
		}
		
		clarified.clear();
		
		if(operations.contains("(")) {
			operations = doOperations(operations);
		}
		
		operations = doSpecialOperations(operations);
		
		operations = orderOfOperations(operations);
		
		String toOperate = new String();
		
		for(String x : operations) {
			
			toOperate += x;
			
		}
		
		return toOperate;
	}
	
	List<String> doOperations(List<String> math) {
		
		int firstBracket = -1;
		int lastBracket = -1;
		
		if(math.contains("(")) {
			
			firstBracket = math.lastIndexOf("(");
			
			math.remove(firstBracket);
		}
		
		if(math.contains(")")) {
			
			for(int i = firstBracket; i < math.size(); i++) {
				
				if(math.get(i) == ")") {
					lastBracket = i;
					math.remove(lastBracket);
					break;
					
				}
				
			}
			
		}
		
		List<String> arr = new ArrayList<>();
		
		if(firstBracket != -1 && lastBracket != -1) {
		
			arr = doOperationsInBrackets(math, firstBracket, lastBracket);
			
			doOperations(math);
		}
		return arr;
		
	}
	
	List<String> doOperationsInBrackets(List<String> math, int start, int end) {
		
		List<String> returner = math;
		List<String> arr = new ArrayList<>();
		
		for(int i = start; i < end; i++) {
			
			arr.add(returner.get(i));
			
		}
		
		arr = orderOfOperations(arr);
		
		for(int i = start; i < end; i++) {
			returner.remove(start);
		}
		
		returner.addAll(start, arr);
		
		return returner;
		
	}
	
	List<String> doSpecialOperations(List<String> math) {
		
		//checking for last index, then do methods in last index from the end
		
		int startIndex = -1;
		
		List<String> temp = math;
		
		if(temp.contains("sqrt")) {
			
			startIndex = temp.lastIndexOf("sqrt");
			
			double sum = Math.sqrt(Double.parseDouble(temp.get(startIndex + 1)));
			
			temp.remove(startIndex);
			temp.remove(startIndex);

			temp.add(startIndex, String.valueOf(sum));
			
			doSpecialOperations(temp);
			
		} else if(temp.contains("log")) {
			
			startIndex = temp.lastIndexOf("log");
			
			double sum = Math.log(Double.parseDouble(temp.get(startIndex + 1)));
			
			temp.remove(startIndex);
			temp.remove(startIndex);

			temp.add(startIndex, String.valueOf(sum));
			
			doSpecialOperations(temp);
			
		} else if(temp.contains("sin")) {
			
			startIndex = temp.lastIndexOf("sin");
			
			double sum = Math.sin(Math.toRadians(Double.parseDouble(temp.get(startIndex + 1))));
			
			temp.remove(startIndex);
			temp.remove(startIndex);

			temp.add(startIndex, String.valueOf(sum));
			
			doSpecialOperations(temp);
			
		} else if(temp.contains("cos")) {
			
			startIndex = temp.lastIndexOf("cos");
			
			double sum = Math.cos(Math.toRadians(Double.parseDouble(temp.get(startIndex + 1))));
			
			temp.remove(startIndex);
			temp.remove(startIndex);

			temp.add(startIndex, String.valueOf(sum));
			
			doSpecialOperations(temp);
			
		} else if(temp.contains("tan")) {
			
			startIndex = temp.lastIndexOf("tan");
			
			double sum = Math.tan(Math.toRadians(Double.parseDouble(temp.get(startIndex + 1))));
			
			temp.remove(startIndex);
			temp.remove(startIndex);

			temp.add(startIndex, String.valueOf(sum));
			
			doSpecialOperations(temp);
			
		} else if(temp.contains("log")) {
			
			startIndex = temp.lastIndexOf("log");
			
			double sum = Math.log(Math.toRadians(Double.parseDouble(temp.get(startIndex + 1))));
			
			temp.remove(startIndex);
			temp.remove(startIndex);

			temp.add(startIndex, String.valueOf(sum));
			
			doSpecialOperations(temp);
			
		}
		
		return temp;
	}
	
	List<String> orderOfOperations(List<String> math) {
		
		List<String> temp = math;
		
		int index = 0;
		
		if(temp.indexOf("^") != -1) {
			
			index = temp.indexOf("^");
			
			String toAdd = String.valueOf((int) Math.pow(Double.parseDouble(temp.get(index - 1)), Double.parseDouble(temp.get(index + 1))));
			
			temp.remove(index + 1);
			temp.remove(index);
			temp.remove(index - 1);
			
			temp.add(index - 1, toAdd);
			
			orderOfOperations(temp);
			
			
		} else {
			
			if(temp.indexOf("/") != -1) {
				
				index = temp.indexOf("/");
				String toAdd = String.valueOf(Double.parseDouble(temp.get(index - 1)) / Double.parseDouble(temp.get(index + 1)));
				
				temp.remove(index + 1);
				temp.remove(index);
				temp.remove(index - 1);
				
				temp.add(index - 1, toAdd);
				
				orderOfOperations(temp);
				
			} else {
				
				if(temp.indexOf("*") != -1) {
					
					index = temp.indexOf("*");
					String toAdd = String.valueOf(Double.parseDouble(temp.get(index - 1)) * Double.parseDouble(temp.get(index + 1)));
					
					temp.remove(index + 1);
					temp.remove(index);
					temp.remove(index - 1);
					
					temp.add(index - 1, toAdd);
					
					orderOfOperations(temp);
					
				} else {
					if(temp.indexOf("-") != -1) {
						
						index = temp.indexOf("-");
						
						String toAdd = String.valueOf(Double.parseDouble(temp.get(index - 1)) - Double.parseDouble(temp.get(index + 1)));
						
						temp.remove(index + 1);
						temp.remove(index);
						temp.remove(index - 1);
						
						temp.add(index - 1, toAdd);
						
						orderOfOperations(temp);
						
					} else {
						if(temp.indexOf("+") != -1) {
							
							index = temp.indexOf("+");
							String toAdd = String.valueOf(Double.parseDouble(temp.get(index - 1)) + Double.parseDouble(temp.get(index + 1)));
							
							temp.remove(index + 1);
							temp.remove(index);
							temp.remove(index - 1);
							
							temp.add(index - 1, toAdd);
							
							orderOfOperations(temp);
							
						} 
					}
				}
			}
		}
		return temp;
	}
}
