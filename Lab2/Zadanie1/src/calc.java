import javax.swing.*;
import java.awt.event.ActionListener;


public class calc extends JFrame {
    private JPanel mainPanel;
    private JButton wyczyscButton;
    private JButton obliczButton;
    private JRadioButton kwadratRadio;
    private JRadioButton trapezRadio;
    private JRadioButton prostoRadio;
    private JRadioButton trojRadio;
    private JRadioButton szczescianRadio;
    private JRadioButton kulaRadio;
    private JRadioButton walecRadio;
    private JRadioButton prostopadloscianRadio;
    private JLabel chooseLabel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private JPanel topLeftPanel;
    private JPanel topRightPanel;
    private JPanel topMidPanel;
    private JPanel botRightPanel;
    private JPanel botLeftPanel;
    private JTextField poleTextField;
    private JTextField objTextField;
    private JTextField obwTextField;
    private JTextField aTextField;
    private JTextField promTextField;
    private JTextField wysTextField;
    private JTextField bTextField;




    public static void main(String[] args) {
        calc cl = new calc();
        cl.setVisible(true);


    }
    private void clearRadio(){
        if(kwadratRadio.isSelected()) kwadratRadio.setSelected(false);
        if(trapezRadio.isSelected()) trapezRadio.setSelected(false);
        if(prostoRadio.isSelected()) prostoRadio.setSelected(false);
        if(trojRadio.isSelected()) trojRadio.setSelected(false);
        if(szczescianRadio.isSelected()) szczescianRadio.setSelected(false);
        if(kulaRadio.isSelected()) kulaRadio.setSelected(false);
        if(walecRadio.isSelected()) walecRadio.setSelected(false);
        if(prostopadloscianRadio.isSelected()) prostopadloscianRadio.setSelected(false);
    }
    private void clearText(){
        poleTextField.setText("");
        objTextField.setText("");
        obwTextField.setText("");
        aTextField.setText("");
        promTextField.setText("");
        wysTextField.setText("");
        bTextField.setText("");

    }
    private void disableTextField(){
        poleTextField.setEnabled(false);
        objTextField.setEnabled(false);
        obwTextField.setEnabled(false);
        aTextField.setEnabled(false);
        promTextField.setEnabled(false);
        wysTextField.setEnabled(false);
        bTextField.setEnabled(false);
    }
    private void calcKwadrat(){
        try{
            double a = Double.parseDouble(aTextField.getText());
            if(a > 0){
                double pole = a*a;
                double obw = a*4;
                poleTextField.setText(String.valueOf(pole));
                obwTextField.setText(String.valueOf(obw));
            }else{
                poleTextField.setText("error");
                obwTextField.setText("error");
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
            poleTextField.setText("error");
            obwTextField.setText("error");
        }



    }
    private void calcKula() {
        try{
            double promien = Double.parseDouble(promTextField.getText());
            if(promien > 0){
                double pole = 4*Math.PI*Math.pow(promien,2);
                double obj = 4.0/3.0*Math.PI*Math.pow(promien,3);
                poleTextField.setText(String.valueOf(pole));
                objTextField.setText(String.valueOf(obj));
            }else{
                poleTextField.setText("error");
                objTextField.setText("error");
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
            poleTextField.setText("error");
            objTextField.setText("error");
        }
    }

    private void calcProstopadloscian() {
        try{
            double a = Double.parseDouble(aTextField.getText());
            double b = Double.parseDouble(bTextField.getText());
            double wys = Double.parseDouble(wysTextField.getText());
            if(a > 0 && b > 0 && wys > 0){
                double pole = (2*a*b)+(2*a*wys)+(2*b*wys);
                double obj = a*b*wys;
                poleTextField.setText(String.valueOf(pole));
                objTextField.setText(String.valueOf(obj));
            }else{
                poleTextField.setText("error");
                objTextField.setText("error");
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
            poleTextField.setText("error");
            objTextField.setText("error");
        }
    }

    private void calcWalec() {
        try{
            double promien = Double.parseDouble(promTextField.getText());
            double wys = Double.parseDouble(wysTextField.getText());
            if(promien > 0 && wys > 0){
                double pole = 2*Math.PI*Math.pow(promien,2)+2*Math.PI*promien*wys;
                double obj = Math.PI*Math.pow(promien,2)*wys;
                poleTextField.setText(String.valueOf(pole));
                objTextField.setText(String.valueOf(obj));
            }else{
                poleTextField.setText("error");
                objTextField.setText("error");
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
            poleTextField.setText("error");
            objTextField.setText("error");
        }
    }

    private void calcSzesc() {
        try{
            double a = Double.parseDouble(aTextField.getText());
            if(a > 0){
                double pole = 6*a*a;
                double obj = Math.pow(a,3);
                poleTextField.setText(String.valueOf(pole));
                objTextField.setText(String.valueOf(obj));
            }else{
                poleTextField.setText("error");
                objTextField.setText("error");
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
            poleTextField.setText("error");
            objTextField.setText("error");
        }
    }

    private void calcTroj() {
        try{
            double a = Double.parseDouble(aTextField.getText());
            double wys = Double.parseDouble(wysTextField.getText());
            if(a > 0 && wys > 0){
                double pole = (a*wys)/2;
                poleTextField.setText(String.valueOf(pole));
            }else{
                poleTextField.setText("error");
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
            poleTextField.setText("error");
        }
    }

    private void calcProsto() {
        try{
            double a = Double.parseDouble(aTextField.getText());
            double b = Double.parseDouble(bTextField.getText());

            if(a > 0 && b > 0){
                double pole = (a*b);
                double obw = 2*a+2*b;

                poleTextField.setText(String.valueOf(pole));
                obwTextField.setText(String.valueOf(obw));
            }else{
                poleTextField.setText("error");
                obwTextField.setText("error");
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
            poleTextField.setText("error");
            obwTextField.setText("error");
        }
    }

    private void calcTrapez() {
        try{
            double a = Double.parseDouble(aTextField.getText());
            double b = Double.parseDouble(bTextField.getText());
            double wys = Double.parseDouble(wysTextField.getText());

            if(a > 0 && b > 0 && wys > 0){
                double pole = ((a+b)*wys)/2;


                poleTextField.setText(String.valueOf(pole));

            }else{
                poleTextField.setText("error");

            }
        }
        catch(Exception e){
            System.out.println(e.toString());
            poleTextField.setText("error");

        }
    }


    public calc(){
        super("Figury geometryczne - kalkulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLayout(null);



        ActionListener listener = e -> {
            Object obj1 = e.getSource();

            if(obj1 == kwadratRadio){
                clearRadio();
                disableTextField();
                kwadratRadio.setSelected(true);
                aTextField.setEnabled(true);

            } else if(obj1 == trapezRadio){
                clearRadio();
                disableTextField();
                trapezRadio.setSelected(true);
                aTextField.setEnabled(true);
                bTextField.setEnabled(true);
                wysTextField.setEnabled(true);

            } else if(obj1 == prostoRadio){
                clearRadio();
                disableTextField();
                prostoRadio.setSelected(true);
                aTextField.setEnabled(true);
                bTextField.setEnabled(true);

            } else if(obj1 == trojRadio){
                clearRadio();
                disableTextField();
                trojRadio.setSelected(true);
                aTextField.setEnabled(true);
                wysTextField.setEnabled(true);

            } else if(obj1 == szczescianRadio){
                clearRadio();
                szczescianRadio.setSelected(true);
                disableTextField();
                aTextField.setEnabled(true);

            } else if(obj1 == kulaRadio){
                clearRadio();
                kulaRadio.setSelected(true);
                disableTextField();
                promTextField.setEnabled(true);

            } else if(obj1 == walecRadio){
                clearRadio();
                walecRadio.setSelected(true);
                disableTextField();
                promTextField.setEnabled(true);
                wysTextField.setEnabled(true);

            } else if(obj1 == prostopadloscianRadio){
                clearRadio();
                prostopadloscianRadio.setSelected(true);
                disableTextField();
                aTextField.setEnabled(true);
                bTextField.setEnabled(true);
                wysTextField.setEnabled(true);

            } else if(obj1 == obliczButton){
                if(kwadratRadio.isSelected() || prostoRadio.isSelected()){
                    poleTextField.setEnabled(true);
                    obwTextField.setEnabled(true);
                } else if(szczescianRadio.isSelected() || prostopadloscianRadio.isSelected() || walecRadio.isSelected() || kulaRadio.isSelected()){
                    poleTextField.setEnabled(true);
                    objTextField.setEnabled(true);
                } else if(trapezRadio.isSelected() || trojRadio.isSelected())
                {
                    poleTextField.setEnabled(true);
                }

                if(kwadratRadio.isSelected()) calcKwadrat();
                else if(trapezRadio.isSelected()) calcTrapez();
                else if(prostoRadio.isSelected()) calcProsto();
                else if(trojRadio.isSelected()) calcTroj();
                else if(szczescianRadio.isSelected()) calcSzesc();
                else if(kulaRadio.isSelected()) calcKula();
                else if(walecRadio.isSelected()) calcWalec();
                else if(prostopadloscianRadio.isSelected()) calcProstopadloscian();

            } else if(obj1 == wyczyscButton){
                clearRadio();
                clearText();
                disableTextField();
            }

        };

        kwadratRadio.addActionListener(listener);
        trapezRadio.addActionListener(listener);
        prostoRadio.addActionListener(listener);
        trojRadio.addActionListener(listener);
        szczescianRadio.addActionListener(listener);
        kulaRadio.addActionListener(listener);
        walecRadio.addActionListener(listener);
        prostopadloscianRadio.addActionListener(listener);
        wyczyscButton.addActionListener(listener);
        obliczButton.addActionListener(listener);
        obliczButton.addActionListener(listener);
    }





}


