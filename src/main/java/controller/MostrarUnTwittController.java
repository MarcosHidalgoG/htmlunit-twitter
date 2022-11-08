/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import model.EstructuraTwitt;
import model.BusquedaTwitt;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Marcos
 */
public class MostrarUnTwittController {

    public EstructuraTwitt visualizarTwitt(BusquedaTwitt twitt) {
        WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
        webClient.getOptions().setJavaScriptEnabled(true);
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
        HtmlPage page = null;
        EstructuraTwitt estructura=null;
        try {
            estructura=new EstructuraTwitt();
            page = webClient.getPage("https://twitter.com/" + twitt.getUsuario() + "/status/" + twitt.getId());
            webClient.close();
            HtmlElement cuerpo = (HtmlElement) page.getFirstByXPath("//div[@class='permalink-inner permalink-tweet-container']");
            List<HtmlElement> listHref = cuerpo.getElementsByTagName("a");
            for (int i = 0; i < listHref.size(); i++) {
                listHref.get(i).removeAttribute("href");
            }
            
            List <HtmlElement> cabecera=page.getByXPath("//link[@rel='stylesheet']");
            List<String> cabeceraString = new ArrayList();
            for(int i=0; i<cabecera.size(); i++){
                cabeceraString.add(cabecera.get(i).asXml());
            }
            estructura.setCabecera(cabeceraString);
            estructura.setCuerpo(cuerpo.asXml());
            return estructura;
        } catch (FailingHttpStatusCodeException | IOException e) {
            System.out.println("Mensaje de fallo: " + e.getMessage());
            webClient.close();
            return null;
        }
    }
}
