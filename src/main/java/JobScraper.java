import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.w3c.dom.NodeList;

import java.io.IOException;


public class JobScraper {

    private WebClient client;
    private HtmlPage innerPage;
    private String urlMNGovCareerSite = "https://careers.mn.gov/psp/hcm92apc/MNCAREERS/HRCR/c/HRS_HRAM.HRS_APP_SCHJOB.GBL?Page=HRS_APP_SCHJOB&Action=U&FOCUS=Applicant&SiteId=1001";

    public JobScraper() throws IOException {

        client = new WebClient();
        HtmlPage startPage = this.client.getPage(urlMNGovCareerSite);
        HtmlInlineFrame iframe = (HtmlInlineFrame) startPage.getElementsByTagName("iframe").get(0);
        innerPage = (HtmlPage) iframe.getEnclosedPage();
    }


    public String getPageTitle() {
        return this.innerPage.getTitleText();
    }


    public void submitJobSearchForm(String jobTitle) throws IOException {

        String textFieldName = "HRS_SCH_WRK_HRS_SCH_TEXT100";
        String submitButtonName = "SEARCHACTIONS#SEARCH";

        HtmlTextInput textField = (HtmlTextInput) this.innerPage.getElementByName(textFieldName);
        HtmlButtonInput submitButton = (HtmlButtonInput) this.innerPage.getElementByName(submitButtonName);

        textField.type(jobTitle);

        submitButton.click();

    }

    public void waitUntilEverythingLoadsUp(int milliseconds) {
        client.waitForBackgroundJavaScript(milliseconds);
    }


    public String getNumberOfJobsReturnedFromSearch() {
        DomNode node = innerPage.getElementById("HRS_SCH_WRK_HRS_SES_CNTS_MSG");
        String numJobsFound = node.getTextContent();

        return numJobsFound;
    }




}
