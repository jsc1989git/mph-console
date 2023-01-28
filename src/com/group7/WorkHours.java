package com.group7;


public class WorkHours {

    int minutes;
    int hours;
    public WorkHours(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }
    public static void main(String[] args) {

        //create WorkHours class objects
        WorkHours start = new WorkHours(8, 0);
        WorkHours stop = new WorkHours(17, 0);
        WorkHours diff;

        //call difference method
        diff = difference(start, stop);

        //print hours worked with correct formatting hh:mm
        System.out.printf("Hours Worked: %d:%02d\n", diff.hours, diff.minutes);


    }

    public static WorkHours difference(WorkHours start, WorkHours stop) {

        WorkHours diff = new WorkHours(0,0);

        //if the start minute is greater
        //decrement hour
        //convert one stop hour to minutes
        //then add the converted minutes to stop minutes
        if(start.minutes > stop.minutes){
            --stop.hours;
            stop.minutes += 60;
        }

        diff.minutes = stop.minutes - start.minutes;
        diff.hours = stop.hours - start.hours;


        //return the difference time
        return(diff);


    }

}
//TODO: HH:MM to X.XX
//Salary = X.XX * Hourly Rate
//10-24 to 10/28
