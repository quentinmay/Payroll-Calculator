/*
    Program name: "Payroll Calculator". This program uses a simple UI to
    generate simulated payroll calculations for a made up company.
    Copyright (C) 2021  Quentin May

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

/*
Author information:
    Author: Quentin May
    Email: quentinemay@gmail.com, quentinemay@csu.fullerton.edu
*/

/*
Program information:
    Program name: Payroll Calculator
    Programming language: Java
    Files: main.java, AppUI.java, MathOperations.java, run.sh
    Date project began: 2021-February-01
    Date of last update: 2021-February-12
    Status: Finished
    Purpose: This program uses a simple UI togenerate simulated payroll calculations for a made up company.
    Base test system: Linux system with Bash shell and openjdk-14-jdk
*/

/*
This Module:
    File name: AppUI.java
    Compile: javac AppUI.java
    Purpose: This is the class file that defines the user interface
    This class is meant to be called from the main class.
    It's the backbone of the payroll calculator involves in defining the user interface and holding basic 
    functionality of the calculator.
*/

package payroll.calculator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class AppUI extends JFrame implements ActionListener {
    //Define basic private data section for the UI. Contains the panels, textfields, labels, etc.
    private JPanel pnlCompanyName = new JPanel();
    private JPanel pnlInputs = new JPanel();
    private JPanel pnlOutputs = new JPanel();
    private JPanel pnlControl = new JPanel();

    private JTextField employeeNameTextField = new JTextField();
    private JTextField hoursWorkedTextField = new JTextField();
    private JTextField hourlyPayTextField = new JTextField();

    private JLabel employeeNameLabel = new JLabel();
    private JLabel regularPayLabel = new JLabel("0");
    private JLabel overtimePayLabel = new JLabel("0");
    private JLabel grossPayLabel = new JLabel("0");

    private JButton clearButton = new JButton("Clear");
    private JButton computeButton = new JButton("Compute");
    private JButton quitButton = new JButton("Quit");
        
        
    public AppUI() {
        
        this.setLayout(new GridLayout(4, 1));
        this.add(pnlCompanyName);
        this.add(pnlInputs);
        this.add(pnlOutputs);
        this.add(pnlControl);

        /*
        Company Name Panel Setup
        */
        pnlCompanyName.setBackground(Color.MAGENTA);
        pnlCompanyName.add(new JLabel("May Airline Service Payroll System"));
        
        
        /*
        Panel Inputs Setup
        */
        pnlInputs.setBackground(Color.CYAN);
        pnlInputs.setLayout(new GridLayout(3, 2, 5, 5));
        
        pnlInputs.add(new JLabel("Employee Name:"));
        pnlInputs.add(employeeNameTextField);
        
        pnlInputs.add(new JLabel("Hours worked:"));
        pnlInputs.add(hoursWorkedTextField);
        
        pnlInputs.add(new JLabel("Hourly pay rate:"));
        pnlInputs.add(hourlyPayTextField);
        
        
        /*
        Panel Outputs Setup
        */
        pnlOutputs.setBackground(Color.RED);
        pnlOutputs.setLayout(new GridLayout(4, 2, 5, 5));
        
        pnlOutputs.add(new JLabel("Name of employee"));
        pnlOutputs.add(employeeNameLabel);

        pnlOutputs.add(new JLabel("Regular pay"));
        pnlOutputs.add(regularPayLabel);
        
        pnlOutputs.add(new JLabel("Overtime pay"));
        pnlOutputs.add(overtimePayLabel);
        
        pnlOutputs.add(new JLabel("Gross pay"));
        pnlOutputs.add(grossPayLabel);
        
        
        
        /*
        Control Panel Setup
        */
        pnlControl.setBackground(Color.ORANGE);
        pnlControl.setLayout(new GridLayout(1, 3, 5, 5));
        
        
        pnlControl.add(clearButton);
        clearButton.setBackground(Color.GRAY);
        clearButton.addActionListener(this);
        
        
        pnlControl.add(computeButton);
        computeButton.setBackground(Color.GREEN);
        computeButton.addActionListener(this);
        
        
        pnlControl.add(quitButton);
        quitButton.setBackground(Color.YELLOW);
        quitButton.addActionListener(this);
        
        //Sets the sizes and finishing touches for the UI before making visible.
        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Program 1");
        this.setVisible(true); 
    }
    
    //The basic action handler for all of the 3 buttons (Quit, Compute, Clear).
    @Override
    public void actionPerformed(ActionEvent e) {
        //Clear Button functionality. Just closes the window and establishes a new AppUI.
        if (e.getSource() == clearButton) {
            dispose();
            new AppUI();
        //Compute Button functionality. Does all parsing and math operations necessary.
        } else if (e.getSource() == computeButton ) {
            //First Try catch is to check to see if the hours is a proper integer. If it is an integer (basically checks if has
            //a dot or not), it will err and stop functionality there. If it isn't an integer, it will be caught by the catch
            //then complete the math operations assuming that it is just a double. Kind of weird work around.
            try {
                Integer.parseInt(hoursWorkedTextField.getText());
                employeeNameLabel.setText(employeeNameTextField.getText());
                regularPayLabel.setText("ERROR");
                overtimePayLabel.setText("ERROR");
                grossPayLabel.setText("ERROR");
            } catch (Exception err) {
                try {
                    double hoursWorked = Double.parseDouble(hoursWorkedTextField.getText());
                    double payRate = Double.parseDouble(hourlyPayTextField.getText());
                    if (hoursWorked < 0 || payRate < 0) throw new NumberFormatException();
                    if (hoursWorked > 168) throw new Exception();
                    MathOperations m = new MathOperations();
                    double regularPay = m.computeRegularPay(hoursWorked, payRate);
                    double overtimePay = m.computeOvertimePay(hoursWorked, payRate);
                    double grossPay = m.computeGrossPay(regularPay, overtimePay);


                    employeeNameLabel.setText(employeeNameTextField.getText());
                    regularPayLabel.setText(String.format("%.2f", regularPay));
                    overtimePayLabel.setText(String.format("%.2f", overtimePay));
                    grossPayLabel.setText(String.format("%.2f", grossPay));
                } catch(Exception error) {
                    employeeNameLabel.setText(employeeNameTextField.getText());
                    regularPayLabel.setText("ERROR");
                    overtimePayLabel.setText("ERROR");
                    grossPayLabel.setText("ERROR");
                }
            }
        //Quit button functionality
        } else if (e.getSource() == quitButton) {
            System.exit(0);
        }
    }

    
}
