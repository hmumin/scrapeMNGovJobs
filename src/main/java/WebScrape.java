import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;

public class WebScrape {

    private WebClient client;
    private HtmlPage innerPage;

    public WebScrape(String url) throws IOException {


        this.client = new WebClient();
        HtmlPage page = this.client.getPage(url);
        HtmlInlineFrame iframe = (HtmlInlineFrame) page.getElementsByTagName("iframe").get(0);
        innerPage = (HtmlPage) iframe.getEnclosedPage();
    }


    public String getTitle() {
        return this.innerPage.getTitleText();
    }


    public void submitForm(String formId, String textFiledName, String submitButtonName, String textToSearchFor) throws IOException {

        //HtmlForm form = (HtmlForm) this.page.getElementById(formId);
        HtmlTextInput textField = (HtmlTextInput) this.innerPage.getElementByName(textFiledName);
        HtmlButtonInput submitButton = (HtmlButtonInput) this.innerPage.getElementByName(submitButtonName);

        textField.type(textToSearchFor);

        submitButton.click();

    }

    public void waitUntilEverythingLoadsUp(int milliseconds) {
        client.waitForBackgroundJavaScript(milliseconds);
    }


    public String getNumberOfJobsReturnedfromSearch() {
        DomNode node = innerPage.getElementById("HRS_SCH_WRK_HRS_SES_CNTS_MSG");
        String jobsFound = node.getTextContent();

        return jobsFound;
    }


}
