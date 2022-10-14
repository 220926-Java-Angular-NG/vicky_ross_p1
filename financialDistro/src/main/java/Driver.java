import org.financeDistro.Controllers.EmployeeController;
import org.financeDistro.Controllers.ManagerController;
import org.financeDistro.Controllers.ReimbursementController;
import org.financeDistro.Models.Employees;
import org.financeDistro.Models.Managers;
import org.financeDistro.Models.Reimbursement;
import org.financeDistro.Repos.EmployeeRepo;
import org.financeDistro.Repos.ManagerRepo;
import org.financeDistro.Services.EmployeeServices;
import org.financeDistro.Services.ManagerServices;
import org.financeDistro.Services.ReimbursementServices;
import org.financeDistro.utils.ConnectionManager;
import org.financeDistro.utils.CRUDDaoInterface;

import io.javalin.Javalin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Driver {

    public static void main (String[] args){
        Javalin app = Javalin.create().start(8080);

        //employees
        EmployeeServices employeeServices = new EmployeeServices();
        EmployeeController employeeController = new EmployeeController();
        app.post("/employee", employeeController.userLogin);
            app.post("/employees", employeeController.createNewEmployee);
            app.get("/employees/getAll", employeeController.getAllEmployees);
            app.get("/employees/{employee_id}", employeeController.getEmployeeById);
            app.put("/employees/update", employeeController.updateEmployee);
            app.delete("/employees/delete", employeeController.deleteEmployee);
        //managers
        ManagerServices managerServices = new ManagerServices();
        ManagerController managerController = new ManagerController();
            app.post("/manager", managerController.managerLogin);
            app.post("/managers", managerController.createNewManager);
            app.get("/managers/getAll", managerController.getAllManagers);
            app.get("/managers/{finance_manager_id}", managerController.getManagerById);
            app.put("/managers/update", managerController.updateManager);
            app.delete("/managers/delete", managerController.deleteManager);
        //reimbursements
        ReimbursementServices reimbursementServices = new ReimbursementServices();
        ReimbursementController reimbursementController = new ReimbursementController();
            app.post("/reimbursement", reimbursementController.createNewReimbursement);
            app.get("/reimbursement", reimbursementController.getAllReimbursements);
            app.get("/reimbursement/{request_id}", reimbursementController.getReimbursementById);
            app.put("/reimbursement", reimbursementController.updateReimbursement);
            app.delete("/reimbursement", reimbursementController.deleteReimbursement);
    }

}
