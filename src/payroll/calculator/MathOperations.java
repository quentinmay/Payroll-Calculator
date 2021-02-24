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
    File name: MathOperations.java
    Compile: javac MathOperations.java
    Purpose: This is the class file that holds the Math operations called and used by the AppUI.java user interface.
    This class is meant to be called from the AppUI.java class.
    The necessary functions involved
*/
package payroll.calculator;

public class MathOperations {
    
    //Calculates the regular pay amount from hoursWorked and payRate. Stops after 40 hours.
    public double computeRegularPay(double hoursWorked, double payRate) {
        //If the hours worked is more than 40, then those extra hours are considered OvertimePay. Use the overtime pay function
        //to calculate the rest of the pay.
        if (hoursWorked <= 40) {
            return hoursWorked * payRate;
        } else if (hoursWorked > 40) return 40 * payRate;
        return 0;
        
    }
    
    //Calculates the overTimePay from hoursWorked and payRate assuming that overtime pay is 1.5x
    //If 40+ hours are worked, the additional hours is only considered overtime.
    public double computeOvertimePay(double hoursWorked, double payRate) {
        //If we don't have 40 hours worked, then just returns 0. Otherwise, calculate.
        if (hoursWorked > 40) {
            return (hoursWorked - 40) * (1.5 * payRate);
        } else return 0;
    }
    
    //Calculates gross pay from just adding overtimePay and regularPay. Simple addition.
    public double computeGrossPay(double overtimePay, double regularPay) {
        return overtimePay + regularPay;
    }
}
