package Model;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBmanager {

    private static DBmanager dbm;

    private String user;
    private String password;
    private int port;

    private Connection con;
    private Statement stt;
    private PreparedStatement psst;
    private ResultSet res;

    public DBmanager(String user, String password, int port) {
        this.user = user;
        this.password = password;
        this.port = port;
    }

    public static void setInstance(DBmanager dbm) { DBmanager.dbm = dbm; }

    public static DBmanager getInstance() { return dbm; }

    public Connection getCon() {
        return con;
    }

    public boolean connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(("jdbc:mysql://localhost:" + port + "/?serverTimezone=UTC"), user, password);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean dbExists() {
        boolean bol = false;
        try {
            stt = con.createStatement();
            res = stt.executeQuery("SHOW DATABASES LIKE 'DB_public_transport'");
            bol = res.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bol;
    }

    public Stop getStop(String stopId) {
        Stop stop = new Stop();
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("SELECT * FROM stop WHERE Stop_Id = ?");
            psst.setString(1, stopId);
            ResultSet res = psst.executeQuery();

            res.next();
            String id = res.getString("Stop_Id");
            String name = res.getString("Stop_Name");
            String town = res.getString("Town");
            String neighborhood = res.getString("Neighborhood");
            String street = res.getString("Street");
            int x_coordinate = res.getInt("X_Coordinate");
            int y_coordinate = res.getInt("Y_Coordinate");
            stop = new Stop(id, name, town, neighborhood, street, x_coordinate, y_coordinate);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return stop;
    }

    public List<Stop> getStopList(boolean routeList) {
        List<Stop> stops = new LinkedList<>();

        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            res = stt.executeQuery("SELECT * FROM stop");
            while (res.next()) {
                String stopId = res.getString("Stop_Id");
                String stopName = res.getString("Stop_Name");
                String town = res.getString("Town");
                String neighborhood = res.getString("Neighborhood");
                String street = res.getString("Street");
                int x_coordinate = res.getInt("X_Coordinate");
                int y_coordinate = res.getInt("Y_Coordinate");
                stops.add(new Stop(stopId, stopName, town, neighborhood, street, x_coordinate, y_coordinate));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        if (routeList) {
            setStopRouteList(stops);
        }

        return stops;
    }

    private void setStopRouteList(List<Stop> stops) {
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            for (Stop stop: stops) {
                List<Route> routeList = new LinkedList<>();
                psst = con.prepareStatement("SELECT * FROM route_includes WHERE Stop_Id = ?");
                psst.setString(1, stop.getId());
                ResultSet res = psst.executeQuery();
                psst.clearParameters();

                while (res.next()) {
                    String routeId = res.getString("Route_Id");
                    routeList.add(new Route(routeId));
                }
                stop.setRouteList(routeList);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Route> getRouteList(boolean includesList, boolean departuresList, boolean priceList) {
        List<Route> routes = new LinkedList<>();
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            res = stt.executeQuery("SELECT Route_Id FROM route");
            while (res.next()) {
                String routeId = res.getString("Route_Id");
                if (routes.contains(new Route(routeId))) {
                    continue;
                }
                routes.add(new Route(routeId));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        if (includesList) {
            setRouteIncludesList(routes);
        }
        if (departuresList) {
            setRouteDeparturesList(routes);
        }
        if (priceList) {
            setRoutePriceList(routes);
        }

        return routes;
    }

    private void setRouteIncludesList(List<Route> routes) {
        try {
            for (Route route:routes) {
                List<RouteIncludes> includesList = new LinkedList<>();
                stt = con.createStatement();
                stt.execute("USE DB_public_transport");
                psst = con.prepareStatement("SELECT Stop_Id, Visit_Order FROM route_includes WHERE Route_Id = ?");
                psst.setString(1, route.getId());
                ResultSet res = psst.executeQuery();
                psst.clearParameters();

                while (res.next()) {
                    String stopId = res.getString("Stop_Id");
                    int order = res.getInt("Visit_Order");
                    includesList.add(new RouteIncludes(getStop(stopId), order));
                }
                route.setIncludesList(includesList);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setRouteDeparturesList(List<Route> routes) {
        try {
            for (Route route:routes) {
                List<String> departures0 = new LinkedList<>();
                List<String> departures1 = new LinkedList<>();
                stt = con.createStatement();
                stt.execute("USE DB_public_transport");
                psst = con.prepareStatement("SELECT Departure_Time, Direction FROM route_departure_time WHERE Route_Id = ?");
                psst.setString(1, route.getId());
                ResultSet res = psst.executeQuery();
                psst.clearParameters();

                while (res.next()) {
                    String departure = res.getString("Departure_Time");
                    boolean direction = res.getBoolean("Direction");
                    if (!direction) {
                        departures0.add(departure);
                    }else
                        departures1.add(departure);
                }
                route.setDepartures0(departures0);
                route.setDepartures1(departures1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setRoutePriceList(List<Route> routes) {
        try {
            for (Route route:routes) {
                List<TransportPrice> priceList = new LinkedList<>();
                stt = con.createStatement();
                stt.execute("USE DB_public_transport");
                psst = con.prepareStatement("SELECT cType, Price FROM transport_price WHERE Route_Id = ?");
                psst.setString(1, route.getId());
                ResultSet res = psst.executeQuery();
                psst.clearParameters();

                while (res.next()) {
                    String cardType = res.getString("cType");
                    double price = res.getDouble("Price");
                    priceList.add(new TransportPrice(cardType, price));
                }
                route.setPriceList(priceList);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OnTheMove getOnTheMove(Route route, boolean dir) {
        OnTheMove otm = new OnTheMove();
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("SELECT Status, Vehicle_Id, Last_Stop FROM on_the_move WHERE Route_Id = ? AND Direction = ?");
            psst.setString(1, route.getId());
            psst.setBoolean(2, dir);
            ResultSet res = psst.executeQuery();
            res.next();
            otm.setStatus(res.getString("Status"));
            String vehicleId = res.getString("Vehicle_Id");
            String stopId = res.getString("Last_Stop");
            Stop stop = getStop(stopId);
            otm.setLastStop(stop.getName());
            psst.clearParameters();
            psst = con.prepareStatement("SELECT Capacity, Vehicle_Type FROM vehicle WHERE Vehicle_Id = ?");
            psst.setString(1, vehicleId);
            res = psst.executeQuery();
            res.next();
            otm.setVehicleType(res.getString("Vehicle_Type"));
            otm.setCapacity(res.getInt("Capacity"));
            psst = con.prepareStatement("SELECT Driver_Id FROM driver_drives WHERE Vehicle_Id = ?");
            psst.setString(1, vehicleId);
            res = psst.executeQuery();
            res.next();
            String driverId = res.getString("Driver_Id");
            psst = con.prepareStatement("SELECT Driver_Name FROM driver WHERE Driver_Id = ?");
            psst.setString(1, driverId);
            res = psst.executeQuery();
            res.next();
            otm.setDriverName(res.getString("Driver_Name"));
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return otm;
    }

    public List<Review> getReviewList() {
        List<Review> reviewList = new LinkedList<>();
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            res = stt.executeQuery("SELECT * FROM travel_review");
            while (res.next()) {
                String id = res.getString("Review_Id");
                String message = res.getString("Message");
                String email = res.getString("Email");
                int rating = Integer.valueOf(res.getString("Travel_Rating"));

                reviewList.add(new Review(id, message, rating, email));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewList;
    }

    public void writeReview(Review review) {
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("INSERT INTO travel_review" +
                    "(Review_Id, Message, Travel_Rating, Review_Rating, Email) VALUES (?, ?, ?, ?, ?)");
            psst.setString(1, review.getId());
            psst.setString(2, review.getMessage());
            psst.setString(3, String.valueOf(review.getRating()));
            psst.setString(4, "0");
            psst.setString(5, review.getEmail());

            psst.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setReview(Review review) {
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("UPDATE travel_review SET Message = ?, Travel_Rating = ? WHERE Review_Id = ?");
            psst.setString(1, review.getMessage());
            psst.setString(2, String.valueOf(review.getRating()));
            psst.setString(3, review.getId());

            psst.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeReview(Review review) {
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("DELETE FROM travel_review WHERE Review_Id = ?");
            psst.setString(1, review.getId());

            psst.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAddress(Address address, String passengerId) {
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("INSERT INTO passenger_address" +
                    "(Address_Id, Town, Neighborhood, Street, Number, X_Coordinate, Y_Coordinate, Passenger_Id)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            psst.setString(1, address.getId());
            psst.setString(2, address.getTown());
            psst.setString(3, address.getNeighborhood());
            psst.setString(4, address.getStreet());
            psst.setString(5,address.getNumber());
            psst.setInt(6, address.getCoorX());
            psst.setInt(7,address.getCoorY());
            psst.setString(8, passengerId);

            psst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAddress(Address address) {
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("UPDATE passenger_address SET " +
                    "Town = ?, Neighborhood = ?, Street = ?, Number = ?, X_Coordinate = ?, Y_Coordinate = ?" +
                    " WHERE Address_Id = ?");
            psst.setString(1, address.getTown());
            psst.setString(2, address.getNeighborhood());
            psst.setString(3, address.getStreet());
            psst.setString(4, address.getNumber());
            psst.setInt(5, address.getCoorX());
            psst.setInt(6, address.getCoorY());
            psst.setString(7, address.getId());

            psst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAddress(Address address) {
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("DELETE FROM passenger_address WHERE Address_Id = ?");
            psst.setString(1, address.getId());

            psst.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPhone(Phone phone, String passengerId) {
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("INSERT INTO passenger_phone" +
                    "    (Phone, Phone_Type, Passenger_Id)" +
                    "VALUES (?, ?, ?)");
            psst.setString(1, phone.getPhone());
            psst.setString(2, phone.getPhoneType());
            psst.setString(3, passengerId);
            psst.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPhone(Phone phone, String oldPhone, String passengerID) {
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("UPDATE passenger_phone SET Phone = ?, Phone_Type = ? WHERE Phone = ? AND Passenger_Id = ?");
            psst.setString(1, phone.getPhone());
            psst.setString(2, phone.getPhoneType());
            psst.setString(3, oldPhone);
            psst.setString(4, passengerID);
            psst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removePhone(Phone phone, String passengerID) {
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("DELETE FROM passenger_phone WHERE Phone = ? AND Passenger_Id = ?");
            psst.setString(1, phone.getPhone());
            psst.setString(2, passengerID);

            psst.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Login getRandomLogin() {
        String mail = "";
        String pass = "";
        try {
            List<User> users = new LinkedList<>();
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            ResultSet res = stt.executeQuery("SELECT * FROM user_account ORDER BY RAND() LIMIT 1;");
            res.next();
            mail = res.getString("Email");
            pass = res.getString("Password");

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return new Login(mail, pass);
    }

    public boolean login(String email, String password) {
        String rPass = "";
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("SELECT Password FROM user_account WHERE Email = ?");
            psst.setString(1, email);
            ResultSet res = psst.executeQuery();
            res.next();
            rPass = res.getString("Password");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        if (rPass.equals(password)) {
            setUser(email, password);
            return true;
        } else
            return false;
    }

    public void setUser(String email, String password) {
        String lastLogin, passengerId;
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("SELECT * FROM user_account WHERE Email = ?");
            psst.setString(1, email);
            res = psst.executeQuery();
            res.next();
            lastLogin = res.getString("Last_Login");
            passengerId = res.getString("Passenger_Id");

            User user = new User(email, password, lastLogin);
            UserInfo.setUser(user);

            getUserReview(email);

            setPassenger(passengerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setPassenger(String passengerId) {
        String fullName, birthDate, cardId;
        try {
            psst = con.prepareStatement("SELECT * FROM passenger WHERE Passenger_Id = ?");
            psst.setString(1, passengerId);
            res = psst.executeQuery();
            res.next();
            fullName = res.getString("Full_Name");
            birthDate = res.getString("Birth_Date");
            cardId = res.getString("Card_Id");

            Passenger passenger = new Passenger(passengerId, fullName, birthDate);
            UserInfo.setPassenger(passenger);

            getPassengerAddress(passengerId);
            getPassengerPhone(passengerId);

            setCard(cardId);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setCard(String cardId) {
        String cardType, status, lastUsage, issueDate;
        int travelCount;
        double balance;
        try {
            psst = con.prepareStatement("SELECT * FROM travel_card WHERE Card_Id = ?");
            psst.setString(1, cardId);
            res = psst.executeQuery();
            res.next();
            cardType = res.getString("cType");
            status = res.getString("Status");
            lastUsage = res.getString("Last_Usage");
            issueDate = res.getString("Issue_Date");
            travelCount = res.getInt("Travel_Count");
            balance = res.getDouble("Balance");

            Card card = new Card(cardId, cardType, status, lastUsage, issueDate, travelCount, balance);
            UserInfo.setCard(card);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getPassengerAddress(String passengerId) {
        List<Address> addressList = new LinkedList<>();
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("SELECT * FROM passenger_address WHERE Passenger_Id = ?");
            psst.setString(1, passengerId);
            res = psst.executeQuery();
            while (res.next()) {
                String id = res.getString("Address_Id");
                String town = res.getString("Town");
                String nbhd = res.getString("Neighborhood");
                String street = res.getString("Street");
                String num = res.getString("Number");
                int coorX = res.getInt("X_Coordinate");
                int coorY = res.getInt("Y_Coordinate");
                addressList.add(new Address(id, town, nbhd, street, num, coorX, coorY));
            }
            UserInfo.getPassenger().setAddressList(addressList);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getPassengerPhone(String passengerId) {
        List<Phone> phoneList = new LinkedList<>();
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("SELECT * FROM passenger_phone WHERE Passenger_Id = ?");
            psst.setString(1, passengerId);
            res = psst.executeQuery();
            while (res.next()) {
                String phone = res.getString("Phone");
                String phoneType = res.getString("Phone_Type");
                phoneList.add(new Phone(phone, phoneType));
            }
            UserInfo.getPassenger().setPhoneList(phoneList);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getUserReview(String email) {
        List<Review> reviewList = new LinkedList<>();
        try {
            stt = con.createStatement();
            stt.execute("USE DB_public_transport");
            psst = con.prepareStatement("SELECT * FROM travel_review WHERE Email = ?");
            psst.setString(1, email);
            res = psst.executeQuery();
            while (res.next()) {
                String id = res.getString("Review_Id");
                String message = res.getString("Message");
                int rating = Integer.valueOf(res.getString("Travel_Rating"));
                reviewList.add(new Review(id, message, rating, email));
            }
            UserInfo.getUser().setReviewList(reviewList);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}