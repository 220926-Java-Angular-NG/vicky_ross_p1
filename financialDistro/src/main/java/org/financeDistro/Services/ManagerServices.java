package org.financeDistro.Services;

import org.financeDistro.Models.Employees;
import org.financeDistro.Models.Managers;
import org.financeDistro.Repos.ManagerRepo;
import org.financeDistro.utils.CRUDDaoInterface;

import java.util.ArrayList;
import java.util.List;

public class ManagerServices {
    private ManagerRepo managerRepo;
    public ManagerServices(){ managerRepo = new ManagerRepo(); }

    public ManagerServices(ManagerServices managerServices){
        this.managerRepo = managerRepo;
    }
    public int createManager(Managers manager){ return managerRepo.create(manager); }
    public List<Managers> getAllManagers(){
        return managerRepo.getAll();
    }
    public Managers getManagerById(int id) { return managerRepo.getById(id); }
    public Managers updateManager(Managers manager) { return managerRepo.update(manager); }
    public boolean deleteManager(Managers manager){ return managerRepo.delete(manager); }

    public Managers managerLogin(Managers managers) {return managerRepo.managerLogin(managers);
    }
}