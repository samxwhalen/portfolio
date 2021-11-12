package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	public Object getChoiceFromOptions(Object[] options, boolean naturalKey) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options,naturalKey,true);
			choice = getChoiceFromUserInput(options, naturalKey);
		}
		return choice;
	}


	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {

		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options, boolean useNaturalKey) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			if(!useNaturalKey) {
				int selectedOption = Integer.valueOf(userInput);
				if (selectedOption > 0 && selectedOption <= options.length) {
					choice = options[selectedOption - 1];
				}
			}else{

				String selectedOption = userInput.toUpperCase();

				boolean valid = false;

				for(Object obj:options){

					if(!valid){
						valid = (obj.equals(selectedOption));
					}
				}

				if(valid){choice = selectedOption;}
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	private void displayMenuOptions(Object[] options, boolean suppressLineNumbers, boolean suppressList) {
		out.println();
		if(!suppressList) {

			for (int i = 0; i < options.length; i++) {
				int optionNum = i + 1;
				if (suppressLineNumbers) {
					out.println(options[i]);
				} else {
					out.println(optionNum + ") " + options[i]);
				}
			}
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
}