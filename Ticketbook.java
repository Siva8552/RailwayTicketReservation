import java.util.*;

public class Ticketbook {

    static int availablelowerberth = 10;
    static int availableupperberth = 10;
    static int availablemiddleberth = 10;
    static int availableRacTickets = 10;
    static int availableWaitinglist = 10;

    static Queue<Integer> waitinglist = new LinkedList<>();
    static Queue<Integer> raclist = new LinkedList<>();

    static List<Integer> lowerberthposition = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    static List<Integer> upperberthposition = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    static List<Integer> middleberthposition = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    static List<Integer> Racposition = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    static List<Integer> Waitinglistposition = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    static List<Integer> bookedTicketList = new ArrayList<>();

    static Map<Integer, Passenger> passenger = new HashMap<>();

    public static void BookTicket(Passenger p, int brethinfo, String allotedbreth) {
        p.number = brethinfo;
        p.alloted = allotedbreth;
        passenger.put(p.Passenger_Id, p);
        bookedTicketList.add(p.Passenger_Id);
        System.out.println("------------------booked successfully-----------------");
    }


    public void addtoRAC(Passenger p, int racinfo, String allotedrac) {
        p.number = racinfo;
        p.alloted = allotedrac;
        passenger.put(p.Passenger_Id, p);
        raclist.add(p.Passenger_Id);
        Racposition.remove(0);
        availableRacTickets--;
        System.out.println("------------------booked successfully-----------------");
    }

    public void addtowaiting(Passenger p, int waitinglistinfo, String allotedWL) {
        p.number = waitinglistinfo;
        p.alloted = allotedWL;
        passenger.put(p.Passenger_Id, p);
        waitinglist.add(p.Passenger_Id);
        Waitinglistposition.remove(0);
        availableWaitinglist--;
        System.out.println("------------------booked successfully-----------------");
    }

    public void printAvailableTickets() {
        System.out.println("Available Lowerberth  :" + availablelowerberth);
        System.out.println("Available Upperberth  :" + availableupperberth);
        System.out.println("Available Middleberth :" + availablemiddleberth);
        System.out.println("Available RACs        :" + availableRacTickets);
        System.out.println("Available Waiting List:" + availableWaitinglist);
        System.out.println("---------------------------------------------------------");
    }

    public void printPassenger() {
        if (passenger.size() == 0) {
            System.out.println("No details Available");
        }
        for (Passenger p : passenger.values()) {
            System.out.println("Passenger ID:" + p.Passenger_Id);
            System.out.println("Name:" + p.name);
            System.out.println("Age:" + p.age);
            System.out.println("Status:" + p.number + p.alloted);
            System.out.println("-------------------------------------------------------");
        }
    }

    public void cancelTicket(int idc) {
        Passenger pasd = passenger.get(idc);
        passenger.remove(Integer.valueOf(idc));
        bookedTicketList.remove(Integer.valueOf(idc));
        int seatnumber = pasd.number;
        if ((pasd.alloted).equals("L")) {
            lowerberthposition.add(seatnumber);
            availablelowerberth++;
        } else if ((pasd.alloted).equals("U")) {
            upperberthposition.add(seatnumber);
            availableupperberth++;
        } else if ((pasd.alloted).equals("M")) {
            middleberthposition.add(seatnumber);
            availablemiddleberth++;
        }
        if (raclist.size() > 0) {
            Passenger passengerfromrac = passenger.get(raclist.poll());
            int seatnumberrac = passengerfromrac.number;
            raclist.remove(Integer.valueOf(passengerfromrac.Passenger_Id));
            Racposition.add(seatnumberrac);
            availableRacTickets++;

            if (waitinglist.size() > 0) {
                Passenger passengerfromWL = passenger.get(waitinglist.poll());
                int seatnumWl = passengerfromWL.number;
                Waitinglistposition.add(seatnumWl);
                waitinglist.remove(Integer.valueOf(passengerfromWL.Passenger_Id));

                passengerfromWL.number = Racposition.get(0);
                passengerfromWL.alloted = "RAC";
                Racposition.remove(0);
                availableWaitinglist++;
                availableRacTickets--;
            }
            Main.bookTicket(passengerfromrac);
        }


    }
}
