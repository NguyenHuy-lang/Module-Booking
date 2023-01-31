package spring.example.spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.example.spring.entity.Bill;
import spring.example.spring.entity.BookedTable;
import spring.example.spring.entity.Booking;
import spring.example.spring.entity.UsedFood;
import spring.example.spring.repository.BillRepository;
import spring.example.spring.repository.BookedTableRepository;
import spring.example.spring.repository.BookingRepository;
import spring.example.spring.repository.UsedFoodRepository;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookedTableRepository bookedTableRepository;
    private final UsedFoodRepository usedFoodRepository;
    public Booking getBookingByid(Long id) {
        return bookingRepository.getReferenceById(id);
    }
    @Transactional
    public void saveBooking(Booking booking) {
        List<BookedTable> listBookedTable = booking.getBookedTable();
        List<BookedTable> _listBookedTable = new ArrayList<>();
        for (BookedTable bookedTable : listBookedTable) {
            List<UsedFood> _listUsedFood = new ArrayList<>();
            for (UsedFood usedFood : bookedTable.getListUsedFood()) {
                UsedFood _usedFood = usedFoodRepository.save(usedFood);
                _listUsedFood.add(_usedFood);
            }
            bookedTable.setListUsedFood(_listUsedFood);

            _listBookedTable.add(bookedTableRepository.save(bookedTable));
        }
        booking.setBookedTable(_listBookedTable);
        bookingRepository.save(booking);
    }
    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }
}
