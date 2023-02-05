package com.group7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.IOException;

public class MPHPayroll {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static void main(String[] args) {

//        Ask for Employee Number to search
        Scanner input = new Scanner(System.in);
        System.out.println("Enter employee number: ");
        int empNum = input.nextInt();
//        Boolean for handling non-existent employee numbers
        boolean found = false;
//        Read MotorPH Employee Data with values separated by tabs to avoid cutting the address if comma is used
        try (BufferedReader br = new BufferedReader(new FileReader("./src/com/group7/mphEmpData.tsv"))) {
            String line;
            int employeeNumber;
            double basicSalary;
            double riceSubsidy;
            double phoneAllowance;
            double clothingAllowance;
            double semiMonthlyRate;
            double hourlyRate = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                employeeNumber = Integer.parseInt(values[0]);
                if (employeeNumber == empNum) {
                    found = true;
//                    Display employee information
                    System.out.println("=====================================");
                    System.out.println("         Employee Information");
                    System.out.println("=====================================");
                    System.out.println("Employee Number: " + employeeNumber);
                    System.out.println("Name: " + values[1] + ", " + values[2]);
                    System.out.println("Status: " + values[10]);
                    System.out.println("Position: " + values[11]);
                    System.out.println("Immediate Supervisor: " + values[12]);
                    System.out.println("Birthday: " + values[3]);
                    System.out.println("Address: " + values[4]);
                    System.out.println("Phone Number: " + values[5]);

//                    Display contributions
                    System.out.println("=====================================");
                    System.out.println("            Contributions");
                    System.out.println("=====================================");
                    System.out.println("SSS: " + values[6]);
                    System.out.println("Philhealth: " + values[7]);
                    System.out.println("TIN: " + values[8]);
                    System.out.println("Pag-ibig: " + values[9]);

//                    Display Salary Information
                    System.out.println("=====================================");
                    System.out.println("           Salary Information");
                    System.out.println("=====================================");
                    basicSalary = Double.parseDouble(values[13]);
                    riceSubsidy = Double.parseDouble(values[14]);
                    phoneAllowance = Double.parseDouble(values[15]);
                    clothingAllowance = Double.parseDouble(values[16]);

                    //Calculate semi-monthly rate
                    semiMonthlyRate = basicSalary / 2;

                    //Calculate Hourly Rate
                    hourlyRate = (basicSalary / 21) / 8;
                    
                    System.out.println("Basic Salary: " + df.format(basicSalary));
                    System.out.println("Rice Subsidy: " + df.format(riceSubsidy));
                    System.out.println("Phone Allowance: " + df.format(phoneAllowance));
                    System.out.println("Clothing Allowance: " + df.format(clothingAllowance));
                    System.out.println("Semi-Monthly Rate: " + df.format(semiMonthlyRate));
                    System.out.println("Hourly Rate: " + df.format(hourlyRate) + "\r\n");
                    break;
                }
            }
//            Print message if employee number is not found
            if (!found) {
                System.out.println(empNum + " does not exist.");
                return;
            }
//            Menu for other features
            int choice;
            do {
                System.out.println("Menu:");
                System.out.println("1. Hours Worked by Date");
                System.out.println("2. Net Income");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = input.nextInt();

                switch (choice) {
                    case 1 -> {
//            Date search and work hours calculation
                System.out.println("Enter date to search (YYYY-MM-DD): ");
                String dateToSearch = input.next();
                double totalHoursWorked = 0;
                double totalInMinutes;
                double totalDayPay;

//            Read Employee Attendance Record file
                try (BufferedReader br2 = new BufferedReader(new FileReader("./src/com/group7/mphAttendanceRecord.csv"))) {
                    while ((line = br2.readLine()) != null) {
                        String[] values = line.split(",");
                        employeeNumber = Integer.parseInt(values[0]);
                        if (employeeNumber == empNum && values[3].equals(dateToSearch)) {
                            LocalTime startTime = LocalTime.parse(values[4], DateTimeFormatter.ofPattern("HH:mm"));
                            LocalTime endTime = LocalTime.parse(values[5], DateTimeFormatter.ofPattern("HH:mm"));
                            Duration duration = Duration.between(startTime, endTime);
                            totalInMinutes = duration.toMinutes();
                            totalHoursWorked = totalInMinutes / 60;
                            totalDayPay = totalHoursWorked * hourlyRate;

//                            Display total hours worked for the searched date
                            System.out.println("Total hours worked for " + dateToSearch + ": " + df.format(totalHoursWorked) + " hours");

//                            Calculate total pay for searched date
                            System.out.println("Total pay for " + dateToSearch + ": " + df.format(totalDayPay));
                        }
                    }
//                    Verify if work hours have been completed
                    if (totalHoursWorked >= 9) {
                        System.out.println("Required work hours completed." + "\r\n");
                    } else {
                        System.out.println("Required work hours not completed." + "\r\n");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
                    case 2 -> System.out.println("You chose option 2. Feature is under construction." + "\r\n");
                    case 0 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid option. Try again.");
                }
            } while (choice != 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//TODO: Apply government deductions
