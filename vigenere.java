package cipherApp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

public class vigenere {

	protected Shell shlVigenereCipher;
	private Text keyTxt;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			vigenere window = new vigenere();
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
		shlVigenereCipher.open();
		shlVigenereCipher.layout();
		while (!shlVigenereCipher.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlVigenereCipher = new Shell();
		shlVigenereCipher.setSize(450, 300);
		shlVigenereCipher.setText("Vigenere Cipher");
		
		Label lblKey = new Label(shlVigenereCipher, SWT.NONE);
		lblKey.setBounds(111, 72, 59, 14);
		lblKey.setText("Key");
		
		Label lblInput = new Label(shlVigenereCipher, SWT.NONE);
		lblInput.setBounds(111, 41, 59, 14);
		lblInput.setText("Input");
		
		Text inputTxt = new Text(shlVigenereCipher, SWT.BORDER);
		inputTxt.setBounds(176, 38, 192, 19);
		
		Button encodeBtn = new Button(shlVigenereCipher, SWT.NONE);
		
			encodeBtn.setBounds(111, 113, 96, 27);
			encodeBtn.setText("Encode");
			
			Label lblOutput = new Label(shlVigenereCipher, SWT.NONE);
			lblOutput.setBounds(111, 203, 59, 14);
			lblOutput.setText("Output");
			
			Text outputTxt = new Text(shlVigenereCipher, SWT.BORDER);
			outputTxt.setBounds(176, 203, 192, 19);
			
			Button decodeBtn = new Button(shlVigenereCipher, SWT.NONE);
			decodeBtn.setBounds(272, 113, 96, 27);
			decodeBtn.setText("Decode");
			
			keyTxt = new Text(shlVigenereCipher, SWT.BORDER);
			keyTxt.setBounds(176, 69, 192, 19);
			decodeBtn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String key = keyTxt.getText().toUpperCase();
					
					String text = inputTxt.getText().toUpperCase();
					key = makeKey(text, key);
					String temp = decodeText(text, key);
					outputTxt.setText(temp);
					
				}
			});
		
		encodeBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String key = keyTxt.getText().toUpperCase();
	
				String text = inputTxt.getText().toUpperCase();
				key = makeKey(text, key);
				String temp = encodeText(text, key);
				outputTxt.setText(temp);
					
					
				
			}

			
		});
		

	}
	private String makeKey(String text, String key) {
		int textLen = text.length();
		
		for(int i = 0; ; i++) {
			if( textLen == i) {
				i = 0;
			}
			if(key.length() == textLen) {
				break;
			}
			key+=(key.charAt(i));
		}
		return key;
		
	}
	private String encodeText(String text, String key) {
		String output = "";
		for(int i = 0; i < text.length(); i ++) {
			
			int letterNum = (text.charAt(i) + key.charAt(i)) %26;
			letterNum += 'A';
			
			output += (char)letterNum;

			
		}
		

		return output;
	}

	protected String decodeText(String text, String key) {
		String output = "";
		for(int i = 0; i < text.length() && i<key.length(); i ++) {
			
			int letterNum = (text.charAt(i) - key.charAt(i) +26) %26;
			letterNum += 'A';
			
			output += (char)letterNum;
			
		}
		

		return output;
	}
}
