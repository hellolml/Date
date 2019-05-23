import java.lang.String;
import java.io.*;
class Date{
        private int year;
        private int mouth;
        private int day;
        public Date(int year,int mouth,int day){
            if(year<1900||year>2100){
                System.out.println("年"+year+"不合法");
                return;
            }
            if(mouth<1||mouth>12){
                System.out.println("月"+mouth+"不合法");
                return;
            }
            if(day<1||day>getDayOfMouth(year,mouth)){
                System.out.println("日"+day+"不合格");
                return;
            }
            this.year=year;
            this.mouth=mouth;
            this.day=day;
        }
        private Date(Date other){
            this(other.year,other.mouth,other.day);
        }
        private static final int[] DAY_OF_MOUTH={31,28,31,30,31,30,31,31,30,31,30,31};
        private static int getDayOfMouth(int year,int mouth){
        int days = DAY_OF_MOUTH[mouth-1];
        if(mouth==2&&isLeapYear(year)){
            days = 29;
        }
        return days;
        }
        public void add(int days){
        if(days<0){
            System.out.println("days不合法: "+days);
            return;
        }
        day += days;
        while(day>getDayOfMouth(year,mouth)){
            day -= getDayOfMouth(year,mouth);
            mouth++;
            if(mouth>12){
                mouth = 1;
                year++;
            }
        }
    }
        public void sub(int days){
            if(days < 0){
                System.out.println("days不合法 :"+days);
                return;
            }
            day-=days;
            while(day < 1){
                mouth--;
            if(mouth < 1){
                mouth = 12;
                year--;
            }
            day += getDayOfMouth(year,mouth);
        }
    }
    public static boolean isLeapYear(int year){
            if(year%4==0 && year%100!=0 || year%400==0){
                return true;
            }
            return false;
    }
    public static int differ(Date d1,Date d2){
     if(!isGreatThan(d1,d2)){
         System.out.println("d1必须大于d2");
         return -1;
     }
     int days = 0;
     Date temp = new Date(d1);
     while (isGreatThan(temp,d2)){
         temp.sub(1);
         days++;
     }
     return days;
    }
    public static boolean isGreatThan(Date d1,Date d2){
            if(d1.year > d2.year){
                return true;
            }
            if(d1.year == d2.year && d1.mouth > d2.mouth){
                return true;
            }
            if(d1.year == d2.year && d1.mouth == d2.mouth && d1.day > d2.day){
                return true;
            }
            return false;
    }
    public String toString(){
            return String.format("%04d-%02d-%02d",year,mouth,day);
    }
    public static void main(String[] args) {
         Date date = new Date(2019,5,23);
         System.out.println(date.toString());
         date.add(15);
         System.out.println(date.toString());
         date.sub(40);
         System.out.println(date.toString());
         Date today = new Date(2019,5,23);
         Date future = new Date(2019,6,7);
         System.out.printf("距离高考还有 %d 天 %n",date.differ(future,today));
    }
}