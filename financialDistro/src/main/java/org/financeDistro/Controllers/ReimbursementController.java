package org.financeDistro.Controllers;
import org.financeDistro.Models.Managers;
import org.financeDistro.Models.Reimbursement;
import org.financeDistro.Services.ManagerServices;
import org.financeDistro.Services.ReimbursementServices;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;

public class ReimbursementController {
    ReimbursementServices reService;

    public ReimbursementController() {
        reService = new ReimbursementServices();
    }
    public Handler createNewReimbursement = context -> {
        Reimbursement reimburse = context.bodyAsClass(Reimbursement.class);
        int reimbursementId = reService.createReimbursement(reimburse);

        if(reimbursementId > 0){
            reimburse.setRequestId(reimbursementId);
            context.json(reimburse).status(200);
        }else{
            context.result("Unable to create reimbursement ticket.").status(400);
        }
    };

    public Handler getAllReimbursements = context -> {
        context.json(reService.getAllReimbursements());
    };

    public Handler getReimbursementById = context -> {
        String param = context.pathParam("request_id");
        try{
            int reimbursementId = Integer.parseInt(param);
            Reimbursement reimburse = reService.getReimbursementById(reimbursementId);
            if(reimburse != null){
                context.json(reimburse).status(202);
            }else{
                context.result("Unable to find reimbursement ticket.").status(400);
            }
        }catch(NumberFormatException nFException){
            System.out.println(nFException.getMessage());
        }
    };

    public Handler getReimbursementByStatus = context -> {
        List<Reimbursement> reimbursements = new ArrayList<>();
        context.json(reService.getByStatus());
    };

    public Handler updateReimbursement = context -> {
        Reimbursement reimburse = context.bodyAsClass(Reimbursement.class);
        reimburse = reService.updateReimbursement(reimburse);
        if(reimburse != null){
            context.json(reimburse).status(200);
        }else{
            context.result("Unable to update reimbursement ticket.").status(400);
        }
    };

    public Handler deleteReimbursement = context -> {
        Reimbursement reimburse = context.bodyAsClass(Reimbursement.class);
        if(reimburse != null){
            context.status(200).json(reService.deleteReimbursement(reimburse));
        }else{
            context.status(400).result("Unable to delete reimbursement ticket.");
        }
    };
}
