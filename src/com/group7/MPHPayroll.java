package com.group7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.IOException;
import java.text.ParseException;

public class MPHPayroll {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static void main(String[] args) {
        //Ask for Employee Number to search
        Scanner input = new Scanner(System.in);
        System.out.println("Enter employee number: ");
        int empNum = input.nextInt();
        //Read MotorPH Employee Data
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\choi\\IdeaProjects\\it101-motorph-grp7\\src\\com\\group7\\mphEmpData.tsv"))) {
            String line;
            int employeeNumber = 0;
            double basicSalary = 0;
            double riceSubsidy = 0;
            double phoneAllowance = 0;
            double clothingAllowance = 0;
            double semiMonthlyRate;
            double hourlyRate;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                employeeNumber = Integer.parseInt(values[0]);
                if (employeeNumber == empNum) {
                    System.out.println("Employee Number: " + employeeNumber);
                    System.out.println("Name: " + values[1] + ", " + values[2]);
                    System.out.println("Birthday: " + values[3]);
                    System.out.println("Address: " + values[4]);
                    System.out.println("Phone Number: " + values[5]);
                    System.out.println("SSS: " + values[6]);
                    System.out.println("Philhealth: " + values[7]);
                    System.out.println("TIN: " + values[8]);
                    System.out.println("Pag-ibig: " + values[9]);
                    System.out.println("Status: " + values[10]);
                    System.out.println("Position: " + values[11]);
                    System.out.println("Immediate Supervisor: " + values[12]);
                    basicSalary = Double.parseDouble(values[13]);
                    riceSubsidy = Double.parseDouble(values[14]);
                    phoneAllowance = Double.parseDouble(values[15]);
                    clothingAllowance = Double.parseDouble(values[16]);
                    semiMonthlyRate = basicSalary / 2;
                    hourlyRate = (basicSalary / 21) / 8;
                    System.out.println("Basic Salary: " + basicSalary);
                    System.out.println("Rice Subsidy: " + riceSubsidy);
                    System.out.println("Phone Allowance: " + phoneAllowance);
                    System.out.println("Clothing Allowance: " + clothingAllowance);
                    System.out.println("Semi-Monthly Rate: " + semiMonthlyRate);
                    System.out.println("Hourly Rate: " + hourlyRate);
                    break;
                }
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            System.out.println("Enter date to search (YYYY-MM-DD): ");
            String dateToSearch = input.next();
            double totalHoursWorked = 0;
            double totalInMinutes;
            try (BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\choi\\IdeaProjects\\it101-motorph-grp7\\src\\com\\group7\\mphAttendanceRecord.csv"))) {
                while ((line = br2.readLine()) != null) {
                    String[] values = line.split(",");
                    employeeNumber = Integer.parseInt(values[0]);
                    if (employeeNumber == empNum && values[3].equals(dateToSearch)) {
                        LocalTime startTime = LocalTime.parse(values[4], DateTimeFormatter.ofPattern("HH:mm"));
                        LocalTime endTime = LocalTime.parse(values[5], DateTimeFormatter.ofPattern("HH:mm"));
                        Duration duration = Duration.between(startTime, endTime);
                        totalInMinutes = duration.toMinutes();
                        totalHoursWorked = totalInMinutes / 60;
                        System.out.println("Total hours worked for " + dateToSearch + ": " + df.format(totalHoursWorked) + " hours");
                    }
                }
                if (totalHoursWorked >= 9) {
                    System.out.println("Required work hours completed.");
                } else {
                    System.out.println("Required work hours not completed.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

