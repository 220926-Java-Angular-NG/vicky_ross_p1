package org.financeDistro.Services;

import org.financeDistro.Models.Reimbursement;
import org.financeDistro.Repos.ReimbursementRepo;
import org.financeDistro.utils.CRUDDaoInterface;

import java.util.ArrayList;
import java.util.List;

public class ReimbursementServices {
    private ReimbursementRepo reimbursementRepo;

    public ReimbursementServices(){
        reimbursementRepo = new ReimbursementRepo();
    }

    public ReimbursementServices(ReimbursementRepo reimbursementRepo){
        this.reimbursementRepo = reimbursementRepo;
    }

    public int createReimbursement(Reimbursement reimbursement){
        return reimbursementRepo.create(reimbursement);
    }

    public List<Reimbursement> getAllReimbursements(){
        return reimbursementRepo.getAll();
    }

    public Reimbursement getReimbursementById(int id){
        return reimbursementRepo.getById(id);
    }

    public List<Reimbursement> getByStatus() {return reimbursementRepo.getByStatus(); }
    public Reimbursement updateReimbursement(Reimbursement reimbursement){
        return reimbursementRepo.update(reimbursement);
    }

    public boolean deleteReimbursement(Reimbursement reimbursement){
        return reimbursementRepo.delete(reimbursement);
    }

}
