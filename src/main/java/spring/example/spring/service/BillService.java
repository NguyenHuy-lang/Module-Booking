package spring.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.example.spring.entity.Bill;
import spring.example.spring.repository.BillRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
    public Bill getBillById(Long id) {
        return billRepository.getReferenceById(id);
    }
    public void saveBill(Bill bill) {
        billRepository.save(bill);
    }
    public void deleteBillById(Long id) {
        billRepository.deleteById(id);
    }
    public List<Bill> getAllBill() {
        return billRepository.findAll();
    }

}
