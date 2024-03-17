package demo.code.violation;

//Subclass representing a Consultant
public class Consultant_V2 extends Employee {
 private double hourlyRate;
 private int hoursWorked;

 public Consultant_V2(String name, double hourlyRate, int hoursWorked) {
     super(name, 0, 0); // Consultants don't have a fixed salary or bonus
     this.hourlyRate = hourlyRate;
     this.hoursWorked = hoursWorked;
 }

 @Override
 public double calculateSalary() {
     return hourlyRate * hoursWorked;
 }

 @Override
 public double calculateBonus() {
     // Consultants don't receive a bonus
     return 0;
 }
}
