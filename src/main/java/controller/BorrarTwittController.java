/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Marcos
 */
public class BorrarTwittController {

    public static void borrar(String tweetBorrar, WebClient webClient) {
        webClient.getOptions().setJavaScriptEnabled(true);
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
        HtmlPage page = null;
        try {
            page = webClient.getPage("https://twitter.com/login?lang=es");
            HtmlElement usuario = (HtmlElement) page.getFirstByXPath("//b[@class='u-linkComplex-target']");
            page = webClient.getPage("https://twitter.com/" + usuario.asText() + "/status/" + tweetBorrar);
            HtmlButton buttonOpenDelete = page.getFirstByXPath("//button[@class='ProfileTweet-actionButton u-textUserColorHover dropdown-toggle js-dropdown-toggle']");
            page = buttonOpenDelete.click();
            Thread.sleep(1500);

            HtmlButton buttonOpenWindowDelete = (HtmlButton) page.getByXPath("//button[@class='dropdown-link']").get(9);
            HtmlPage page2 = buttonOpenWindowDelete.click();
            Thread.sleep(1500);

            HtmlButton buttonOpenWindowDeleteFinal = (HtmlButton) page.getByXPath("//button[@class='btn primary-btn delete-action']").get(0);
            HtmlPage page3 = buttonOpenWindowDeleteFinal.click();
            Thread.sleep(1500);

            webClient.close();
        } catch (IOException ex) {
            Logger.getLogger(BorrarTwittController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FailingHttpStatusCodeException ex) {
            Logger.getLogger(BorrarTwittController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(BorrarTwittController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
