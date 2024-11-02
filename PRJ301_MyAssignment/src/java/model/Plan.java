/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author nlong
 */
public class Plan {

    private Integer plId;
    private Department dept;
    private Date start;
    private Date end;

    public Plan() {
    }

    public Plan(Integer plId, Department dept, Date start, Date end) {
        this.plId = plId;
        this.dept = dept;
        this.start = start;
        this.end = end;
    }

    private ArrayList<PlanCampaign> campains = new ArrayList<>();

    public Integer getPlId() {
        return plId;
    }

    public void setPlId(Integer plId) {
        this.plId = plId;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public ArrayList<PlanCampaign> getCampains() {
        return campains;
    }

    public void setCampains(ArrayList<PlanCampaign> campains) {
        this.campains = campains;
    }

}
