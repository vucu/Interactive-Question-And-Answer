package application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JPanel implements ActionListener {
	QuestionBank questionBank = new QuestionBank();
	TimeCounter timeCounter = new TimeCounter();
	protected JLabel label;
	protected JLabel labelTime;
	protected JTextField textField;
    protected JTextArea textArea;
    private final static String newline = "\n";

    public Main() {
        super(new GridBagLayout());
        
        Font font1 = new Font("SansSerif", Font.PLAIN, 30);
        
        String q = questionBank.nextQuestion();
        label = new JLabel();
        label.setText(q);
        label.setFont(font1);
        
        labelTime = new JLabel();
        int t = timeCounter.drop();
        labelTime.setText("Time left: "+Integer.toString(t));
        labelTime.setFont(font1);
        
        textField = new JTextField(32);
        textField.setFont(font1);
        textField.addActionListener(this);

        textArea = new JTextArea(8, 32);
        textArea.setFont(font1);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(label,c);
        add(labelTime,c);
        add(textField, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
        
        // Add timer action
        ActionListener listener = new ActionListener(){
        	  public void actionPerformed(ActionEvent event){
        		  if (questionBank.isOutOfQuestion()) return;
        		  
        		  int t = timeCounter.drop();
        		  if (t==0) {
        			  textArea.append("Out of time!" + newline);
        			  textField.setText("");
        			  String q = questionBank.nextQuestion();
        			  label.setText(q);
        			  t = timeCounter.reset();
        			  
        			  //Make sure the new text is visible, even if there
        			  //was a selection in the text area.
        			  textArea.setCaretPosition(textArea.getDocument().getLength());
        		  }
        		  
        		  labelTime.setText("Time left: "+Integer.toString(t));
        	  }
        	};
        Timer displayTimer = new Timer(1000, listener);
        displayTimer.start();
    }

    public void actionPerformed(ActionEvent evt) {
    	if (questionBank.isOutOfQuestion()) return;
    	
        String text = textField.getText();
        String output = questionBank.checkAnswer(text);
        textArea.append(output + newline);
        textField.setText("");
        String q = questionBank.nextQuestion();
        label.setText(q);
        int t = timeCounter.reset();
        labelTime.setText("Time left: "+Integer.toString(t));
        if (questionBank.isOutOfQuestion()) labelTime.setText("");

        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Question & Answer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new Main());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
