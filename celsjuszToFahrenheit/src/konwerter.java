import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;

public class konwerter extends JFrame {
    private JPanel mainPanel;
    private JTextField celcjuszTextField;
    private JTextField farenheitTextField;
    private JPanel labelPanel;
    private JPanel fieldPanel;
    private JPanel buttonsPanel;
    private JPanel radioPanel;
    private JPanel boxPanel;
    private JPanel resetPanel;
    private JPanel konwertujPanel;
    private JPanel wyjsciePanel;
    private JPanel wyczyscPanel;
    private JPanel malaPanel;
    private JPanel sredniaPanel;
    private JPanel duzaPanel;
    private JButton konwertujButton;
    private JButton wyczyscButton;
    private JButton wyjscieButton;
    private JRadioButton malaButton;
    private JRadioButton sredniaButton;
    private JRadioButton duzaButton;
    private JCheckBox checkBox;
    private JButton resetujButton;
    private JLabel label1;
    private JLabel label2;

    private double tempC, tempF;
    private static konwerter kon;
    public static void main(String[] args) {
        kon = new konwerter();
        kon.setVisible(true);


    }
    public konwerter(){
        super("Konwertowanie stopni C -> F");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        setLayout(null);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object ob1 = e.getSource();
                if(ob1 == konwertujButton || ob1 == celcjuszTextField) {
                    try{
                        tempC = Double.parseDouble(celcjuszTextField.getText());
                        tempF = 32.00 + (9.0 / 5.0) * tempC;
                        farenheitTextField.setText(String.valueOf(tempF));
                    } catch(Exception ex){
                        farenheitTextField.setText("error");
                    }

                } else if(ob1 == wyczyscButton) {
                    farenheitTextField.setText("");
                    celcjuszTextField.setText("");
                    if(checkBox.isSelected()) checkBox.setSelected(false);
                    else if(malaButton.isSelected()) malaButton.setSelected(false);
                    else if(sredniaButton.isSelected()) sredniaButton.setSelected(false);
                    else if(duzaButton.isSelected()) duzaButton.setSelected(false);

                } else if(ob1 == malaButton){
                    farenheitTextField.setFont(new Font("SansSerif", Font.PLAIN, 8));
                    if(sredniaButton.isSelected()) sredniaButton.setSelected(false);
                    if(duzaButton.isSelected()) duzaButton.setSelected(false);
                } else if(ob1 == sredniaButton){
                    farenheitTextField.setFont(new Font("SansSerif", Font.PLAIN, 12));
                    if(malaButton.isSelected()) malaButton.setSelected(false);
                    if(duzaButton.isSelected()) duzaButton.setSelected(false);
                } else if(ob1 == duzaButton){
                    farenheitTextField.setFont(new Font("SansSerif", Font.PLAIN, 20));
                    if(malaButton.isSelected()) malaButton.setSelected(false);
                    if(sredniaButton.isSelected()) sredniaButton.setSelected(false);
                } else if (ob1 == checkBox){
                    if(checkBox.isSelected()) {
                        farenheitTextField.setFont(new Font("SansSerif", Font.BOLD, 14));
                        celcjuszTextField.setFont(new Font("SansSerif", Font.BOLD, 14));
                        malaButton.setFont(new Font("SansSerif", Font.BOLD, 14));
                        sredniaButton.setFont(new Font("SansSerif", Font.BOLD, 14));
                        duzaButton.setFont(new Font("SansSerif", Font.BOLD, 14));
                        wyczyscButton.setFont(new Font("SansSerif", Font.BOLD, 14));
                        wyjscieButton.setFont(new Font("SansSerif", Font.BOLD, 14));
                        resetujButton.setFont(new Font("SansSerif", Font.BOLD, 14));
                        checkBox.setFont(new Font("SansSerif", Font.BOLD, 14));
                        label1.setFont(new Font("SansSerif", Font.BOLD, 14));
                        label2.setFont(new Font("SansSerif", Font.BOLD, 14));
                        konwertujButton.setFont(new Font("SansSerif", Font.BOLD, 14));
                    }
                } else if (ob1 == resetujButton) {

                        farenheitTextField.setFont(new Font("SansSerif", Font.PLAIN, 12));
                        celcjuszTextField.setFont(new Font("SansSerif", Font.PLAIN, 12));
                        malaButton.setFont(new Font("SansSerif", Font.BOLD, 12));
                        sredniaButton.setFont(new Font("SansSerif", Font.BOLD, 12));
                        duzaButton.setFont(new Font("SansSerif", Font.BOLD, 12));
                        wyczyscButton.setFont(new Font("SansSerif", Font.BOLD, 12));
                        wyjscieButton.setFont(new Font("SansSerif", Font.BOLD, 12));
                        resetujButton.setFont(new Font("SansSerif", Font.BOLD, 12));
                        checkBox.setFont(new Font("SansSerif", Font.BOLD, 12));
                        label1.setFont(new Font("SansSerif", Font.BOLD, 12));
                        label2.setFont(new Font("SansSerif", Font.BOLD, 12));
                        konwertujButton.setFont(new Font("SansSerif", Font.BOLD, 12));

                } else if(ob1 == wyjscieButton){
                    kon.dispose();
                }
            }
        };
        konwertujButton.addActionListener(listener);
        wyczyscButton.addActionListener(listener);
        wyjscieButton.addActionListener(listener);
        duzaButton.addActionListener(listener);
        sredniaButton.addActionListener(listener);
        malaButton.addActionListener(listener);
        checkBox.addActionListener(listener);
        resetujButton.addActionListener(listener);
    }

}

