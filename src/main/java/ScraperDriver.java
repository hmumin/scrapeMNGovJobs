import java.io.IOException;

public class ScraperDriver {


    public static  void main(String [] args) {

        // turn off htmlunit warnings
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

        JobScraper jobs= null;

        try {
            jobs = new JobScraper();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Page title: " + jobs.getPageTitle());


        try {
            jobs.submitJobSearchForm("\"Information Technology Careers\"");
        } catch (IOException e) {
            e.printStackTrace();
        }

        jobs.waitUntilEverythingLoadsUp(2000);

        System.out.println("Number of Jobs: " + jobs.getNumberOfJobsReturnedFromSearch());

    }
}


//TODO
//Get all jobs titles, etc
//TODO
//Save to textFile
//TODO
//Add testCases