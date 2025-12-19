// Main.java — Students version
import java.io.*;
import java.util.*;

public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January","February","March","April","May","June",
                              "July","August","September","October","November","December"};
    

    // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    static int[][][] profits = new int[MONTHS][DAYS][COMMS];
    public static void loadData() {
        for(int m = 0; m < MONTHS; m++){
            String filename = months[m] + ".txt";

            Scanner reader = null;
            try{
                reader = new Scanner(new File(filename));
                if(reader.hasNextLine()){
                    reader.nextLine();
                }
                while(reader.hasNextLine()){
                    String line = reader.nextLine().trim();
                    if(line.length() == 0) continue;
                    String[] parts = line.split(",");
                    int day = Integer.parseInt(parts[0].trim()) - 1;
                    String commName = parts[1].trim();
                    int profit = Integer.parseInt(parts[2].trim());

                    int commIndex = -1;
                    for(int c = 0; c < COMMS; c++){
                        if(commodities[c].equals(commName)){
                            commIndex = c;
                            break;
                        }
                    }
                    if(day >= 0 && day < DAYS && commIndex != -1){
                        profits[m][day][commIndex] = profit;
                    }
                }
            } catch(Exception e){

            } finally{
                if(reader != null){
                    reader.close();
                }
            }
        }
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========

    public static String mostProfitableCommodityInMonth(int month) {
        if(month < 0 || month >= MONTHS){
            return "INVALID_MONTH";
        }

        int maxProfit = profits[month][0][0];
        int bestCommIndex = -1;
        for(int c = 0; c < COMMS; c++){
            int sum = 0;
            for(int d = 0; d < DAYS; d++){
                sum+=profits[month][d][c];
            }
            if(sum > maxProfit){
                maxProfit = sum;
                bestCommIndex = c;
            }
        }
        return commodities[bestCommIndex] + " " + maxProfit;
    }

    public static int totalProfitOnDay(int month, int day) {
        if (month < 0 || month >= MONTHS || day < 1 || day >DAYS){
            return -99999;
        }

        int totalProfit = 0;
        int d = day - 1;
        for(int c = 0; c < COMMS; c++){
            totalProfit+=profits[month][d][c];
        }
        return totalProfit;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        if(from < 1 || to > DAYS || from > to){
            return -99999;
        }

        int commIndex = -1;
        for(c = 0; c < COMMS; c++){
            if(commodities[c].equals(commodity)){
                commIndex = c;
                break;
            }
        }
        if(commIndex == -1){
            return -99999;
        }

        int total = 0;
        for(int m = 0; m < MONTHS; m++){
            for(int d = from - 1; d <= to - 1; d++){
                total+=profits[m][d][commIndex];
            }
        }
        return total;
    }

    public static int bestDayOfMonth(int month) {
        if(month < 0 || month >= MONTHS){
            return -1;
        }

        int maxProfit = 0;
        for(int c = 0; c < COMMS; c++){
            maxProfit+=profits[month][0][c];
        }
        int bestDay = 1;
        for(int day = 2; day <= DAYS; day++){
            int total = 0;
            for(int c = 0; c <= COMMS; c++){
                total+=profits[month][day-1][c];
            }
            if(total > maxProfit){
                maxProfit = total;
                bestDay = day;
            }
        }
        return bestDay;
    }
    
    public static String bestMonthForCommodity(String comm) { 
        return "DUMMY"; 
    }

    public static int consecutiveLossDays(String comm) { 
        return 1234; 
    }
    
    public static int daysAboveThreshold(String comm, int threshold) { 
        return 1234; 
    }

    public static int biggestDailySwing(int month) { 
        return 1234; 
    }
    
    public static String compareTwoCommodities(String c1, String c2) { 
        return "DUMMY is better by 1234"; 
    }
    
    public static String bestWeekOfMonth(int month) { 
        return "DUMMY"; 
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}