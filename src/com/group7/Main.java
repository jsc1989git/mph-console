package com.group7;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Main {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static void main(String[] args) {String LName = "Crisostomo";
       String empNumber = "10001";
       String FName = "Jose";
       String DOB = "February 14, 1988";
       String address = "17/85 Stracke Via Suite 042, Poblacion, Las Piñas 4783 Dinagat Islands";
       String phoneNumber = "918-621-603";
       String sssNumber = "49-1632020-8";
       String tin = "49-1632020-8";
       String phicNumber = "382189453145";
       String pagibigNumber = "441093369646";
       String status = "Regular";
       String position = "HR Manager";
       String immediateSup = "N/A";
       int basicSalary = 62670;
       int riceSubsidy = 1500;
       int phoneAll = 1000;
       int clothingAll = 1000;
       int grossSemiMoRate = (basicSalary/2);
       int hourlyRate = (basicSalary/21)/8;

        System.out.println("=================================");
        System.out.println("           Information");
        System.out.println("=================================" + "\r\n");
        System.out.println("Employee No.: " + empNumber);
        System.out.println("Name: " + LName + ", " + FName);
        System.out.println("Status: " + status);
        System.out.println("Position: " + position);
        System.out.println("Immediate Supervisor: " + immediateSup + "\r\n");
        System.out.println("Date of Birth: " + DOB);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phoneNumber + "\r\n");
        System.out.println("=================================");
        System.out.println("          Contributions");
        System.out.println("=================================" + "\r\n");
        System.out.println("SSS #:" + sssNumber);
        System.out.println("Philhealth #:" + phicNumber);
        System.out.println("TIN:" + tin);
        System.out.println("Pag-ibig #:" + pagibigNumber + "\r\n");
        System.out.println("=================================");
        System.out.println("      Salary and Benefits");
        System.out.println("=================================" + "\r\n");
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("Rice Subsidy: " + riceSubsidy);
        System.out.println("Phone Allowance: " + phoneAll);
        System.out.println("Clothing Allowance: " + clothingAll + "\r\n");
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        System.out.println("Gross Semi-monthly Rate: " + df.format(grossSemiMoRate));
        System.out.println("Hourly Rate: " + df.format(hourlyRate));


    }
}
