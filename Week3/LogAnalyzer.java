import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<>();
    }

    public void readFile(String filename) {
        FileResource fileResource = new FileResource(filename);
        for(String line: fileResource.lines()){
            LogEntry logEntry = WebLogParser.parseEntry(line);
            records.add(logEntry);
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for(LogEntry record: records){
            String currIP = record.getIpAddress();
            if(!uniqueIPs.contains(currIP)){
                uniqueIPs.add(currIP);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num){
        for(LogEntry record: records){
            if(record.getStatusCode() > num){
                System.out.println(record.toString());
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for(LogEntry record: records){
            String currIP = record.getIpAddress();
            String date = record.getAccessTime().toString();
            if(!uniqueIPs.contains(currIP) && date.substring(4,10).equals(someday)){
                uniqueIPs.add(currIP);
            }
        }
        return uniqueIPs;
    }

    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for(LogEntry record: records){
            String currIP = record.getIpAddress();
            int currStatus = record.getStatusCode();
            if(!uniqueIPs.contains(currIP) && currStatus >= low && currStatus <= high){
                uniqueIPs.add(currIP);
            }
        }
        return uniqueIPs.size();
    }

    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> counter = new HashMap<>();
        for(LogEntry record: records){
            String currIP = record.getIpAddress();
            if(!counter.containsKey(currIP)){
                counter.put(currIP, 1);
            }
            else{
                counter.put(currIP, counter.get(currIP) + 1);
            }
        }
        return counter;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counter){
        int maxCount = 0;
        for(String key: counter.keySet()){
            if(counter.get(key) > maxCount){
                maxCount = counter.get(key);
            }
        }
        return maxCount;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counter){
        int maxCount = mostNumberVisitsByIP(counter);
        ArrayList<String> ips = new ArrayList<>();
        for(String key: counter.keySet()){
            if(maxCount == counter.get(key)){
                ips.add(key);
            }
        }
        return ips;
    }

    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> allIPs = new HashMap<>();
        for(LogEntry record: records){
            String currIP = record.getIpAddress();
            String date = record.getAccessTime().toString().substring(4,10);
            if(!allIPs.containsKey(date)){
                ArrayList<String> ips = new ArrayList<>();
                ips.add(currIP);
                allIPs.put(date, ips);
            }
            else{
                allIPs.get(date).add(currIP);
            }
        }
        return allIPs;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> allIPs){
        int maxCount = 0;
        String maxDay = "";
        for(String key: allIPs.keySet()){
            if(allIPs.get(key).size() > maxCount){
                maxDay = key;
                maxCount = 0;
            }
        }
        return maxDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> allIPs, String date){
        ArrayList<String> ips = allIPs.get(date);
        HashMap<String, Integer> counter = new HashMap<>();
        for(String ip: ips){
            if(!counter.containsKey(ip)){
                counter.put(ip, 1);
            }
            else{
                counter.put(ip, counter.get(ip) + 1);
            }
        }
        return iPsMostVisits(counter);
    }
}
