/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.html.HtmlOrderedList;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UsuarioTwitter;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Marcos
 */
public class UsuarioTwitterController_copy {

//    public String logeo(Usuario user) {
//        WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
//        webClient.getOptions().setJavaScriptEnabled(true);
//        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
//        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
//        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
//        HtmlPage page = null;
//        try {
//            page = webClient.getPage("https://twitter.com/login?lang=es");
//            List<HtmlTextInput> inputText = page.getByXPath("//input[@name='session[username_or_email]']");
//            inputText.get(1).setValueAttribute(user.getUser());
//
//            List<HtmlPasswordInput> pass = page.getByXPath("//input[@name='session[password]']");
//            pass.get(1).setDefaultValue(user.getPass());
//
//            List<HtmlButton> button = page.getByXPath("//button[@class='submit btn primary-btn']");
//            page = button.get(0).click();
//            Thread.sleep(1500);
//
//            page = webClient.getPage("https://twitter.com/" + user.getUser());
//
//            List<HtmlOrderedList> ol = page.getByXPath("//ol[@class='stream-items js-navigable-stream']");
//            System.out.println(ol.get(0).asXml());
//            //webClient.close();
//            return ol.get(0).asXml();
//        } catch (IOException | ScriptException | InterruptedException | FailingHttpStatusCodeException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }

    public List<String> mostrarTwitt(WebClient webClient, UsuarioTwitter user) {
        HtmlPage page = null;
        List<HtmlOrderedList> listTwitty = null;
        List<String> listString = null;
        try {
            page = webClient.getPage("https://twitter.com/login?lang=es");
            HTMLElement usuario = (HTMLElement) page.getFirstByXPath("//b[@class='u-linkComplex-target']");
            page = webClient.getPage("https://twitter.com/" + usuario.toString());
            listTwitty = page.getByXPath("//li[@data-item-type='tweet']");
            listString = new ArrayList();
            for (int i = 0; i < listTwitty.size(); i++) {
                System.out.println(listTwitty.get(i).toString());
                listString.add(listTwitty.get(i).asXml());
            }
            webClient.close();
        } catch (IOException | FailingHttpStatusCodeException ex) {
            Logger.getLogger(UsuarioTwitterController_copy.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listString;
    }

    public WebClient login(UsuarioTwitter user) {
        WebClient webClient = null;
        HtmlPage page = null;
        try {
            webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
            webClient.getOptions().setJavaScriptEnabled(true);
            LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
            java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
            page = webClient.getPage("https://twitter.com/login?lang=es");
            List<HtmlTextInput> inputText = page.getByXPath("//input[@name='session[username_or_email]']");
            inputText.get(1).setValueAttribute(user.getUser());
            List<HtmlPasswordInput> pass = page.getByXPath("//input[@name='session[password]']");
            pass.get(1).setDefaultValue(user.getPass());
            List<HtmlButton> button = page.getByXPath("//button[@class='submit btn primary-btn']");
            page = button.get(0).click();
            Thread.sleep(1500);
        } catch (IOException | ScriptException | InterruptedException | FailingHttpStatusCodeException ex) {
            Logger.getLogger(UsuarioTwitterController_copy.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return webClient;
    }

}
