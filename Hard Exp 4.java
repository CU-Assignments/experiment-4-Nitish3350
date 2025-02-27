import java.util.*;

class TicketBookingSystem {
    private final Set<Integer> bookedSeats = new HashSet<>();

    public synchronized boolean bookSeat(int seatNumber) {
        if (!bookedSeats.contains(seatNumber)) {
            bookedSeats.add(seatNumber);
            System.out.println(Thread.currentThread().getName() + " booked seat: " + seatNumber);
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() + " tried to book already booked seat: " + seatNumber);
            return false;
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem system;
    private int seatNumber;

    public BookingThread(TicketBookingSystem system, int seatNumber, String name, int priority) {
        super(name);
        this.system = system;
        this.seatNumber = seatNumber;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber);
    }
}

public class Main {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();
        List<Thread> threads = new ArrayList<>();

        threads.add(new BookingThread(bookingSystem, 1, "VIP User 1", Thread.MAX_PRIORITY));
        threads.add(new BookingThread(bookingSystem, 2, "VIP User 2", Thread.MAX_PRIORITY));
        threads.add(new BookingThread(bookingSystem, 1, "Regular User 1", Thread.NORM_PRIORITY));
        threads.add(new BookingThread(bookingSystem, 3, "Regular User 2", Thread.NORM_PRIORITY));
        threads.add(new BookingThread(bookingSystem, 4, "Late User", Thread.MIN_PRIORITY));

        for (Thread t : threads) {
            t.start();
        }
    }
}
