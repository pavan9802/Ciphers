package cipherApp;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import javax.swing.JFrame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Scale;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class caeser  {
	

	protected Shell shlCarserCipher;
	private Text inputTxt;
	
	private Text outputTxt;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			caeser window = new caeser();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCarserCipher.open();
		shlCarserCipher.layout();
		while (!shlCarserCipher.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCarserCipher = new Shell();
		shlCarserCipher.setSize(450, 300);
		shlCarserCipher.setText("Caeser Cipher");
		
		Label lblKey = new Label(shlCarserCipher, SWT.NONE);
		lblKey.setBounds(111, 72, 59, 14);
		lblKey.setText("Key");
		
		Label lblInput = new Label(shlCarserCipher, SWT.NONE);
		lblInput.setBounds(111, 40, 59, 14);
		lblInput.setText("Input");
		
		inputTxt = new Text(shlCarserCipher, SWT.BORDER);
		inputTxt.setBounds(176, 37, 192, 19);
		
		Spinner keySpinner = new Spinner(shlCarserCipher, SWT.BORDER);
		keySpinner.setPageIncrement(1);
		keySpinner.setMinimum(-100);
		keySpinner.setBounds(176, 67, 51, 22);
		
		Button encodeBtn = new Button(shlCarserCipher, SWT.NONE);
		
			encodeBtn.setBounds(111, 113, 96, 27);
			encodeBtn.setText("Encode");
			
			Label lblOutput = new Label(shlCarserCipher, SWT.NONE);
			lblOutput.setBounds(111, 203, 59, 14);
			lblOutput.setText("Output");
			
			outputTxt = new Text(shlCarserCipher, SWT.BORDER);
			outputTxt.setBounds(176, 203, 192, 19);
			
			Button decodeBtn = new Button(shlCarserCipher, SWT.NONE);
			decodeBtn.setBounds(272, 113, 96, 27);
			decodeBtn.setText("Decode");
			decodeBtn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int key = keySpinner.getSelection();
					char[] text = inputTxt.getText().toCharArray();
					
					String temp = decodeText(text, key);	
					outputTxt.setText(temp);
					
				}
			});
		
		encodeBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int key = keySpinner.getSelection();
				char[] text = inputTxt.getText().toCharArray();
				
					String temp = encodeText(text, key);
					outputTxt.setText(temp);
					
					
				
			}
		});
		

	}
	private static String encodeText (char [] text, int key) 
	{
		
	        String output = "";
	 
	        for (int i=0; i<text.length; i++)
	        {
	            if (Character.isUpperCase(text[i]))
	            {
	                char ch = (char)(((int)text[i] + key - 65) % 26 + 65);
	                output +=  ch;
	            }
	            else
	            {
	                char ch = (char)(((int)text[i] +
	                                  key - 97) % 26 + 97);
	                output +=  ch;
	            }
	        }
	        

	        return output;
	    
			
	}
	private static String decodeText (char [] text, int key) {
		 String output = "";
		
	        for (int i=0; i<text.length; i++)
	        {
	            if (Character.isUpperCase(text[i]))
	            	
	            	// figure out why it gives a negative number;
	            {
	            
	            	
	                char ch = (char)(((int)text[i] +(26-key)- 65) % 26 + 65)   ;       
	                		output +=  ch;
	            }
	            else
	            {	
	            	char ch = (char)(((int)text[i] +(26- key) - 97) % 26 + 97)   ;       
	                output +=  ch;
	            }
	        }
	      
	        return output;
	    
		
	}
}
