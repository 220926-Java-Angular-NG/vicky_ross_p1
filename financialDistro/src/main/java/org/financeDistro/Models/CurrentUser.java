package org.financeDistro.Models;

import org.eclipse.jetty.server.Authentication;

public class CurrentUser {
    public static Employees currentEmp;
    public static Managers currentMan;

    private CurrentUser(){

    }
    public CurrentUser(Employees employees){
        currentEmp = employees;
    }
    public CurrentUser(Managers manager){
        currentMan = manager;
    }
}
