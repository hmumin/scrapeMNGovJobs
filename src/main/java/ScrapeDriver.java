import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

public class ScrapeDriver {


    public static  void main(String [] args) {

        // turn off htmlunit warnings
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

        WebScrape scrapeMNJobs= null;

        try {
            scrapeMNJobs = new WebScrape("https://careers.mn.gov/psp/hcm92apc/MNCAREERS/HRCR/c/HRS_HRAM.HRS_APP_SCHJOB.GBL?Page=HRS_APP_SCHJOB&Action=U&FOCUS=Applicant&SiteId=1001");
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Page title: " + scrapeMNJobs.getTitle());


        try {
            scrapeMNJobs.submitForm("win0divHRS_SCH_WRK_HRS_SCH_GRPBOXB","HRS_SCH_WRK_HRS_SCH_TEXT100",
                    "SEARCHACTIONS#SEARCH", "\"Information Technology Careers\"");
        } catch (IOException e) {
            e.printStackTrace();
        }

        scrapeMNJobs.waitUntilEverythingLoadsUp(2000);

        System.out.println("Number of Jobs: " + scrapeMNJobs.getNumberOfJobsReturnedfromSearch());

    }
}


//TODO
//Get all jobs titles, etc
//TODO
//Save to textFile
//TODO
//Add testCases