package spring.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.example.spring.entity.Voucher;
import spring.example.spring.repository.VoucherRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoucherService {
    private final VoucherRepository voucherRepository;
    public Voucher getVoucherById(Long id) {
        return voucherRepository.getReferenceById(id);
    }
    public void saveVoucher(Voucher voucher) {
        voucherRepository.save(voucher);
    }
    public void deleteVoucherById(Long id) {
        voucherRepository.deleteById(id);
    }
    public List<Voucher> getAllVoucher(){
        return voucherRepository.findAll();
    }
}
