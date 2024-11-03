/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.accesscontrol;

import java.util.ArrayList;

/**
 *
 * @author sonnt-local
 */
public class Feature {
    private int fid;
    private String fname;
    private String url;
    
    private ArrayList<User> user = new ArrayList<>();

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<User> getUser() {
        return user;
    }

    public void setUser(ArrayList<User> user) {
        this.user = user;
    }
    
}
