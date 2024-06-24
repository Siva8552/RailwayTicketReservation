public class Passenger {
    String name;
    int age;
    static int id = 1;
    String Prefered_berth;
    int Passenger_Id;
    String alloted;
    int number;

    public Passenger(String name, int age, String prefered_berth) {
        this.name = name;
        this.age = age;
        this.Prefered_berth = prefered_berth;
        this.Passenger_Id = id++;
        alloted = " ";
        number = -1;
    }
}
