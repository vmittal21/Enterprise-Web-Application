package util;

import java.io.*;
import java.io.IOException;
import java.util.*;

public class IOUtil {
    public static HashMap<String, String> readOutputFile() {
        String TOMCAT_HOME = System.getProperty("catalina.home");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        HashMap<String, String> prodRecmMap = new HashMap<String, String>();
        try {

            br = new BufferedReader(new FileReader(new File(
                    TOMCAT_HOME + "\\webapps\\CSP584HealthHub\\python\\matrixFactorizationBasedRecommendations.csv")));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] prod_recm = line.split(cvsSplitBy, 2);
                System.out.println(prod_recm[0]);
                System.out.println(prod_recm[1]);
                prodRecmMap.put(prod_recm[0], prod_recm[1]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return prodRecmMap;
    }

    public static ArrayList<String> readDealMatchesFile() {
        String TOMCAT_HOME = System.getProperty("catalina.home");
        ArrayList<String> content = new ArrayList<String>();
        try {

            BufferedReader reader = new BufferedReader(
                    new FileReader(new File(TOMCAT_HOME + "\\webapps\\CSP584HealthHub\\data\\DealMatches.txt")));
            String line = reader.readLine();
            if (line == null) {
                content.add("No Offers Found");
            } else {
                while (line != null) {
                    content.add(line);
                    line = reader.readLine();
                }
                reader.close();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}