/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Marcos
 */
public class DatosTwitt {
    private List<String> id_twitty;
    private List<String> twitty;

    public DatosTwitt() {
    }

    public List<String> getId_twitty() {
        return id_twitty;
    }

    public void setId_twitty(List<String> id_twitty) {
        this.id_twitty = id_twitty;
    }

    public List<String> getTwitty() {
        return twitty;
    }

    public void setTwitty(List<String> twitty) {
        this.twitty = twitty;
    }
    
    
}
