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
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
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
public class LoginUsuarioTwitterController {
    public static List<String> mostrarTwitt(UsuarioTwitter user) {
        WebClient webClient = inicio(user);
        HtmlPage page = null;
        List<HtmlListItem> listTwitty = null;
        List<String> listString = null;
        try {
            page = webClient.getPage("https://twitter.com/login?lang=es");
            HtmlElement usuario = (HtmlElement) page.getFirstByXPath("//b[@class='u-linkComplex-target']");
            page = webClient.getPage("https://twitter.com/" + usuario.asText());
            user.setUser(usuario.asText());
            listTwitty = page.getByXPath("//li[@data-item-type='tweet']");
            listString = new ArrayList();
            for (int i = 0; i < listTwitty.size(); i++) {
                String aux=listTwitty.get(i).getAttribute("data-screen-name");
                System.out.println(aux);
                if(aux.equalsIgnoreCase(user.getUser())){
                    listString.add(listTwitty.get(i).asXml());
                }
//                listString.add(listTwitty.get(i).asXml());
            }
            webClient.close();
        } catch (IOException | FailingHttpStatusCodeException ex) {
            Logger.getLogger(LoginUsuarioTwitterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listString;
    }

    private static WebClient inicio(UsuarioTwitter user) {
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
            button.get(0).click();
            Thread.sleep(1500);
        } catch (IOException | ScriptException | InterruptedException | FailingHttpStatusCodeException ex) {
            Logger.getLogger(LoginUsuarioTwitterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return webClient;

    }
    public static List<String> idYDatosTwittys(UsuarioTwitter user) {
        WebClient webClient = inicio(user);
        HtmlPage page = null;
        List<HtmlListItem> listTwittyId = null;
        List<String> listStringId = null;
        try {
            page = webClient.getPage("https://twitter.com/login?lang=es");
            HtmlElement usuario = (HtmlElement) page.getFirstByXPath("//b[@class='u-linkComplex-target']");
            page = webClient.getPage("https://twitter.com/" + usuario.asText());
            listTwittyId = page.getByXPath("//li[@data-item-id]");
            listStringId = new ArrayList();
            for (int i = 0; i < listTwittyId.size(); i++) {
                listStringId.add(listTwittyId.get(i).asText());
            }
            webClient.close();
        } catch (IOException | FailingHttpStatusCodeException ex) {
            Logger.getLogger(LoginUsuarioTwitterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listStringId;
    }
}
