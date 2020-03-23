import java.util.*;

public class Tester
{
    private LogAnalyzer logAnalyzer;

    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("TestData/Week3/short-test_log");
        logAnalyzer.printAll();
    }

    public void testUniqueIP(){
        logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("TestData/Week3/short-test_log");
        System.out.println(logAnalyzer.countUniqueIPs());
    }

    public void testPrintAllHigherThanNum(){
        logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("TestData/Week3/short-test_log");
        logAnalyzer.printAllHigherThanNum(200);
    }

    public void testUniqueIPVisitsOnDay(){
        logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("TestData/Week3/weblog-short_log");
        System.out.println(logAnalyzer.uniqueIPVisitsOnDay("Sep 30").size());
    }

    public void testCountUniqueIPsInRange(){
        logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("TestData/Week3/short-test_log");
        System.out.println(logAnalyzer.countUniqueIPsInRange(200,299));
    }

    public void testCountVisitsPerIP(){
        logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("TestData/Week3/short-test_log");
        HashMap<String, Integer> counter = logAnalyzer.countVisitsPerIP();
        for(String key: counter.keySet()){

        }
        System.out.println(logAnalyzer.mostNumberVisitsByIP(counter));
    }

    public void testIPsForDays(){
        logAnalyzer = new LogAnalyzer();

        logAnalyzer.readFile("TestData/Week3/weblog3-short_log");
        HashMap<String, ArrayList<String>> allIPs = logAnalyzer.iPsForDays();
        System.out.println(logAnalyzer.dayWithMostIPVisits(allIPs));
    }

    public void testIPsWithMostVisitsOnDay(){
        logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("TestData/Week3/weblog3-short_log");
        HashMap<String, ArrayList<String>> allIPs = logAnalyzer.iPsForDays();
        ArrayList<String> ips = logAnalyzer.iPsWithMostVisitsOnDay(allIPs, "Sep 30");
        for(String ip: ips){
            System.out.println(ip);
        }
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
//        tester.testLogEntry();
//        tester.testLogAnalyzer();
//        tester.testUniqueIP();
//        tester.testPrintAllHigherThanNum();
//        tester.testUniqueIPVisitsOnDay();
//        tester.testCountUniqueIPsInRange();
//        tester.testCountVisitsPerIP();
//        tester.testIPsForDays();
        tester.testIPsWithMostVisitsOnDay();
    }
}

