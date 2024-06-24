import java.util.*;

public class Main {

    public static void bookTicket(Passenger p) {
        Ticketbook booker = new Ticketbook();
        if (Ticketbook.availableWaitinglist == 0) {
            System.out.println("no tickets available");
            return;
        }
        if (((p.Prefered_berth).equals("L") && Ticketbook.availablelowerberth > 0) ||
                ((p.Prefered_berth).equals("M") && Ticketbook.availablemiddleberth > 0) ||
                ((p.Prefered_berth).equals("U") && Ticketbook.availableupperberth > 0)) {
            System.out.println("the prefered breth is available");
            if ((p.Prefered_berth).equals("L")) {
                System.out.println("Lower breth is given");
                booker.BookTicket(p, (Ticketbook.lowerberthposition.get(0)), "L");
                Ticketbook.lowerberthposition.remove(0);
                Ticketbook.availablelowerberth--;
            } else if ((p.Prefered_berth).equals("U")) {
                System.out.println("upper breth is given");
                booker.BookTicket(p, (Ticketbook.upperberthposition.get(0)), "U");
                Ticketbook.upperberthposition.remove(0);
                Ticketbook.availableupperberth--;
            } else if ((p.Prefered_berth).equals("M")) {
                System.out.println("Middle breth is given");
                booker.BookTicket(p, (Ticketbook.middleberthposition.get(0)), "M");
                Ticketbook.middleberthposition.remove(0);
                Ticketbook.availablemiddleberth--;
            }
        } else if (Ticketbook.availablelowerberth > 0) {
            System.out.println("Lower breth is given");
            booker.BookTicket(p, (Ticketbook.lowerberthposition.get(0)), "L");
            Ticketbook.lowerberthposition.remove(0);
            Ticketbook.availablelowerberth--;
        } else if (Ticketbook.availableupperberth > 0) {
            System.out.println("upper breth is given");
            booker.BookTicket(p, (Ticketbook.upperberthposition.get(0)), "U");
            Ticketbook.upperberthposition.remove(0);
            Ticketbook.availableupperberth--;
        } else if (Ticketbook.availablemiddleberth > 0) {
            System.out.println("Middle breth is given");
            booker.BookTicket(p, (Ticketbook.middleberthposition.get(0)), "M");
            Ticketbook.middleberthposition.remove(0);
            Ticketbook.availablemiddleberth--;
        } else if (Ticketbook.availableRacTickets > 0) {
            System.out.println("Added to RAC berth");
            booker.addtoRAC(p, (Ticketbook.Racposition.get(0)), "RAC");

        } else if (Ticketbook.availableWaitinglist > 0) {
            System.out.println("Added to waiting list");
            booker.addtowaiting(p, (Ticketbook.Waitinglistposition.get(0)), "WL");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        int choice = 0;
        List<Passenger> p = new ArrayList<>();

        while (loop) {
            System.out.println("1.Book a ticket");
            System.out.println("2.Cancel a ticket");
            System.out.println("3.Print the available ticket");
            System.out.println("4.Print passengrs details");
            System.out.println("5.Exit");
            System.out.println("choose any one option");
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    String n = sc.next();
                    int a = sc.nextInt();
                    String pbh = sc.next();
                    Passenger pas = new Passenger(n, a, pbh);
                    p.add(pas);
                    bookTicket(pas);
                }
                break;
                case 2: {
                    System.out.println("Enter the passenger id:");
                    int idb = sc.nextInt();
                    Ticketbook booker = new Ticketbook();
                    booker.cancelTicket(idb);
                }
                break;

                case 3: {
                    Ticketbook booker = new Ticketbook();
                    booker.printAvailableTickets();
                }
                break;
                case 4: {
                    Ticketbook booker = new Ticketbook();
                    booker.printPassenger();
                }
                break;
                case 5:
                    loop = false;
                    break;
            }
        }
    }
}
