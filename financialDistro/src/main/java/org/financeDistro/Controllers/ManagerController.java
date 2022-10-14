package org.financeDistro.Controllers;
import org.financeDistro.Models.CurrentUser;
import org.financeDistro.Models.Employees;
import org.financeDistro.Models.Managers;
import org.financeDistro.Services.ManagerServices;
import io.javalin.http.Handler;
public class ManagerController {
    ManagerServices maService;

    public ManagerController() {
        maService = new ManagerServices();
    }

    public Handler managerLogin = context -> {
        Managers managers = context.bodyAsClass(Managers.class);
        CurrentUser.currentMan = maService.managerLogin(managers);
        System.out.println(CurrentUser.currentMan.toString());
        if(CurrentUser.currentMan != null){
            context.json(managers).result("Logged in.").status(202);
        }else{
            context.result("Unable to find user.").status(400);
        }
    };
    public Handler createNewManager = context -> {
        Managers manager = context.bodyAsClass(Managers.class);
        int managerId = maService.createManager(manager);

        if(managerId > 0){
            manager.setManagerId(managerId);
            context.json(manager).status(200);
        }else{
            context.result("Unable to create manager.").status(400);
        }
    };

    public Handler getAllManagers = context -> {
        context.json(maService.getAllManagers());
    };

    public Handler getManagerById = context -> {
        String param = context.pathParam("financial_manager_id");
        try{
            int managerId = Integer.parseInt(param);
            Managers manager = maService.getManagerById(managerId);
            if(manager != null){
                context.json(manager).status(202);
            }else{
                context.result("Unable to find manager.").status(400);
            }
        }catch(NumberFormatException nFException){
            System.out.println(nFException.getMessage());
        }
    };

    public Handler updateManager = context -> {
        Managers manager = context.bodyAsClass(Managers.class);
        manager = maService.updateManager(manager);
        if(manager != null){
            context.json(manager).status(200);
        }else{
            context.result("Unable to update manager.").status(400);
        }
    };

    public Handler deleteManager = context -> {
        Managers manager = context.bodyAsClass(Managers.class);
        if(manager != null){
            context.status(200).json(maService.deleteManager(manager));
        }else{
            context.status(400).result("Unable to delete user.");
        }
    };
}
