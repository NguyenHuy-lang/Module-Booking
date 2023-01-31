package spring.example.spring.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import spring.example.spring.entity.*;
import spring.example.spring.repository.FoodRepositoryRedis;
import spring.example.spring.service.BookingService;
import spring.example.spring.service.FoodService;
import spring.example.spring.service.TableService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class BookingController {
    private final TableService tableService;
    private final BookingService bookingService;
    private final FoodService foodService;

    private final FoodRepositoryRedis foodRepositoryRedis;

    @GetMapping("/booking")
    public String showBookingView(Model model,
                                  HttpSession session) {
        // get All table to display
        List<Table> lstTable = tableService.getAllTable();
        model.addAttribute("filterTable", lstTable);
        model.addAttribute("table", new Table());
        // when staff click order for customer, init Booking and add to session
        if(session.getAttribute("booking") == null) {
            Booking booking = new Booking();
            session.setAttribute("booking", new Booking());
        }
        // save to redis
//        redisService.saveObject("1", "booking", booking);

        return "booking-view";
    }
    @PostMapping("/booking/filter-table")
    public String filterTable(@ModelAttribute Table object,
                              Model model) {
        // get capacity staff enter
        System.out.println("RequestParam    " + object.getCapacity());
        //get All table in database
        List<Table> lstTable = tableService.getAllTable();
        // init usedTableOrNotCapactity to mark table has not been booked and have capacity equal need find
        HashMap<Table, Boolean> usedTableOrNotCapacity = new HashMap<>();

        // get All booking from database
        List<Booking> lstBooking = bookingService.getAllBooking();

        // iterator over lstBooking to find booking ever checkout
        for (Booking booking : lstBooking) {
            if(booking.getCheckoutAt() == null) {
                // get List bookedTable of booking
                List<BookedTable> lstBookedTable = booking.getBookedTable();
                // iterator over lstBookedTable of booking, mark table of BookedTable has been booked
                for (BookedTable bookedTable : lstBookedTable) {
                    usedTableOrNotCapacity.put(bookedTable.getTable(), false);
                }
            }
        }
        // iterator over lstTable, mark table has size not equal capacity need find
        for(Table table : lstTable) {
            if (table.getCapacity() != object.getCapacity()) {
                usedTableOrNotCapacity.put(table, false);
            }
        }
        // init list filterTable satisfy
        List<Table> filterTable = new ArrayList<>();
        for (Table table : lstTable) {
            // add table has not been booked and has capacity equal need find
            if (usedTableOrNotCapacity.get(table) == null) {
                filterTable.add(table);
            }
        }
        model.addAttribute("filterTable" , filterTable);
        return "booking-view";
    }

    @GetMapping("/booking/table/{tableId}")
    public String showViewPickFood(@PathVariable("tableId") Long tableId,
                                   Model model,
                                   HttpSession session) {
        // get booking from session
        Booking booking = (Booking) session.getAttribute("booking");
        // get lstBookedTable from session , if lstBookedTable is null, we will initlization
        List<BookedTable> lstBookedTable = booking.getBookedTable();
        if(lstBookedTable == null) {
            lstBookedTable = new ArrayList<>();

        }
        // check table current is in any BookedTable
        Boolean exist = false;
        for (BookedTable bookedTable : lstBookedTable) {
            if(bookedTable.getTable().getId() == tableId) {
                exist = true; break;
            }
        }
        // if current table not in any bookedTable, init new bookedTable and add to listBookedTable
        if(exist == false) {
            BookedTable bookedTable = new BookedTable();
            bookedTable.setTable(tableService.getTableById(tableId));
            lstBookedTable.add(bookedTable);
        }

        // set listBookedTable because it can be change if table current not in any bookedTable
        booking.setBookedTable(lstBookedTable);
        BookedTable bookedTable = booking.getBookedTable()
                .stream()
                .filter(bookedtable -> bookedtable.getTable().getId() == tableId)
                .findAny()
                .orElse(null);
        List<UsedFood> listUsedFood = bookedTable.getListUsedFood();
        // set new booking to session
        // get All food
        List<Food> listFood = new ArrayList<>();
        System.out.println(foodRepositoryRedis.findAll());
        listFood = (List<Food>) foodRepositoryRedis.findAll();
        if (listFood == null) {
            listFood = foodService.getAllFood();
            // save listFood to redis
            foodRepositoryRedis.saveAll(listFood);
        }
        // update booking
        session.setAttribute("booking", booking);
        model.addAttribute("filterFood", listFood);
        model.addAttribute("tableId", tableId);
        model.addAttribute("food", new Food());
        model.addAttribute("listUsedFood", listUsedFood);
        return "search-food";
    }
    @PostMapping("/booking/table/{tableId}/filter-food")
    public String getAllFoodByName(@PathVariable("tableId") Long tableId,
                                   @ModelAttribute Food food , Model model) {
        // get all food has name equal input staff
        List<Food> lstFood = foodService.getAllFoodByName(food.getName());
        // add lstfood to model to send to view
        model.addAttribute("filterFood", lstFood);
        model.addAttribute("tableId", tableId);
        return "search-food";
    }
    @GetMapping("/booking/table/{tableId}/food/{foodId}")
    public String showFormEnterQuanity(@PathVariable("tableId") Long tableId,
                                       @PathVariable("foodId") Long foodId, Model model) {
        // init UsedFood (quanity, price current, food)
        UsedFood usedFood = new UsedFood();
        // get food by id that staff pick
        Food food = foodService.getFoodById(foodId);
        // set food to usedFood
        usedFood.setFood(food);
        // send usedFood to view enter-quanity-food to staff enter quanity food, then auto inject field quanity of usedFood
        model.addAttribute("usedFood", usedFood);
        model.addAttribute("tableId" , tableId);
        return "enter-quanity-food";
    }
    @PostMapping("/booking/table/{tableId}/food/{foodId}")
    public RedirectView getQuanityEnter(@PathVariable("tableId") Long tableId,
                                        @PathVariable("foodId") Long foodId,
                                        @ModelAttribute UsedFood usedFood,
                                        Model model, HttpSession session) {
        // get usedFood with field quanity that staff enter
        Food food = foodService.getFoodById(foodId);
        // set more information about food and current price
        usedFood.setUnitPrice(food.getPrice());
        usedFood.setFood(food);
        // get booking from session to update information
        Booking booking = (Booking) session.getAttribute("booking");
        // get list BookedTable of current booking
        // and iterator over each bookedTable and find table with id, update usedFood of this table
        List<BookedTable> listBookedTable = booking.getBookedTable();
        for (BookedTable bookedTable : listBookedTable) {
            // find table of bookedTable has id equal tableId
            if (bookedTable.getTable().getId() == tableId) {
                // get listUsedFood of bookedTable
                List<UsedFood> listUserFood = bookedTable.getListUsedFood();
                // if listUsedFood of bookedTable is null, will init
                if(listUserFood == null) {
                    listUserFood = new ArrayList<>();
                }
                // add usedFood to
                listUserFood.add(usedFood);
                // update listUsedFood to bookedTable
                bookedTable.setListUsedFood(listUserFood);
                break;
            }
        }
        // update listBookedTable to booking
        booking.setBookedTable(listBookedTable);
        // update booking to session
        session.setAttribute("booking", booking);
        return new RedirectView("/customer/booking/table/" + tableId + "");
    }
    @GetMapping("/booking/table/{tableId}/food/{foodId}/delete")
    public RedirectView deleteDish(@PathVariable("tableId") Long tableId,
                                   @PathVariable("foodId") Long foodId,
                                   Model model, HttpSession session) {
        // get booking from session
        Booking booking = (Booking)session.getAttribute("booking");
        // get all listBookedTable from booking
        List<BookedTable> listBookedTable = booking.getBookedTable();
        // filter to get bookedTable that has tableid equal tablId
        BookedTable bookedTable = listBookedTable
                .stream()
                .filter(bookedtable -> bookedtable.getTable().getId() == tableId).findAny().orElse(null);
        // get all listUsedFood of bookedTable
        List<UsedFood> listUsedFood = bookedTable.getListUsedFood();
        // remove usedFood that staff pick
        listUsedFood.removeIf(usedfood -> usedfood.getFood().getId() == foodId);
        // update listUsedFood of bookedTable
        bookedTable.setListUsedFood(listUsedFood);
        // update listBookedTable in 2 step :
        // 1) delete old bookedTable
        listBookedTable.removeIf(bookedtable -> bookedtable.getId() == bookedTable.getId());
        // 2) add new bookedTable
        listBookedTable.add(bookedTable);
        return new RedirectView("/customer/booking/table/" + tableId + "");
    }
    @GetMapping("/booking/table/{tableId}/food/{foodId}/edit")
    public String ShowFormUpdateQuantityDish(@PathVariable Long tableId,
                                             @PathVariable("foodId") Long foodId,
                                             Model model, HttpSession session) {
        // get booking from session
        Booking booking = (Booking) session.getAttribute("booking");
        // get ListBookedTable from booking
        List<BookedTable> listBookedTable = booking.getBookedTable();
        // filter to get BookedTable which has table with id equal tableId
        BookedTable bookedTable = listBookedTable
                .stream()
                .filter(bookedtable -> bookedtable.getTable().getId() == tableId)
                .findAny().orElse(null);
        // get listUsedFood of bookedTable which has table with id equal tableid
        List<UsedFood> listUsedFood = bookedTable.getListUsedFood();
        // get usedFood which has food with id equal foodId
        UsedFood usedFood = listUsedFood
                .stream()
                .filter(usedfood -> usedfood.getFood().getId() == foodId)
                .findAny().orElse(null);
        // add usedFood to model to display to view edit-used-food
        model.addAttribute("usedFood", usedFood);
        model.addAttribute("tableId", tableId);
        return "edit-used-food";
    }

    @PostMapping("/booking/table/{tableId}/food/{foodId}/edit")
    public RedirectView updateQuantityUsedFood(@PathVariable Long tableId,
                                         @PathVariable("foodId") Long foodId,
                                         @ModelAttribute UsedFood _usedFood,
                                         Model model, HttpSession session) {
        // get booking from session
        Booking booking = (Booking) session.getAttribute("booking");
        // get ListBookedTable from booking
        List<BookedTable> listBookedTable = booking.getBookedTable();
        // filter to get BookedTable which has table with id equal tableId
        BookedTable bookedTable = listBookedTable
                .stream()
                .filter(bookedtable -> bookedtable.getTable().getId() == tableId)
                .findAny().orElse(null);
        // get listUsedFood of bookedTable which has table with id equal tableid
        List<UsedFood> listUsedFood = bookedTable.getListUsedFood();
        // get usedFood which has food with id equal foodId
        UsedFood usedFood = listUsedFood
                .stream()
                .filter(usedfood -> usedfood.getFood().getId() == foodId)
                .findAny().orElse(null);
        // update quantity
        usedFood.setQuantity(_usedFood.getQuantity());
        // remove old usedFood
        listUsedFood.removeIf(usedfood -> usedfood.getFood().getId() == foodId);
        // add new usedFood which is updated
        listUsedFood.add(usedFood);
        // update listUsedFood of bookedTable
        bookedTable.setListUsedFood(listUsedFood);
        // remove old bookedTable
        listBookedTable.removeIf(_bookedTable -> _bookedTable.getTable().getId() == tableId);
        // add new
        listBookedTable.add(bookedTable);
        // update booking
        booking.setBookedTable(listBookedTable);
        // update session
        session.setAttribute("booking", booking);

        return new RedirectView("/customer/booking/table/" + tableId + "");
    }
    @GetMapping("/booking/save")
    @Transactional
    public String saveBooking(Model model, HttpSession session){
        Booking booking = (Booking) session.getAttribute("booking");
        System.out.println(booking.getBookedTable());
        System.out.println(booking.getBookedTable().get(0).getTable());
        System.out.println(booking.getBookedTable().get(0).getListUsedFood());
        bookingService.saveBooking(booking);
        return "customer-home-view";
    }

}
