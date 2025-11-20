import java.sql.*;
import java.util.Scanner;

/**
 *  Gym Membership Tracker 
 * Features: 1) Add Member   2) List Members
 */

public class GymJDBC {

    
    private static final String URL = "jdbc:mysql://localhost:3306/gym_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Gym Membership Tracker (JDBC) ===");
            System.out.println("1) Add Member");
            System.out.println("2) List Members");
            System.out.println("0) Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            try {
                if (choice.equals("1")) addMember(sc);
                else if (choice.equals("2")) listMembers();
                else if (choice.equals("0")) { System.out.println("Goodbye!"); break; }
                else System.out.println("Invalid option.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }

    private static void addMember(Scanner sc) throws Exception {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter plan: ");
        String plan = sc.nextLine();

        String sql = "INSERT INTO members (name, phone, plan) VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, name);
            pst.setString(2, phone);
            pst.setString(3, plan);

            pst.executeUpdate();
            System.out.println("Member added!");
        }
    }

    private static void listMembers() throws Exception {
        String sql = "SELECT id, name, phone, plan FROM members";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\nID | Name | Phone | Plan");
            System.out.println("--------------------------------");

            while (rs.next()) {
                System.out.printf("%d | %s | %s | %s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("plan"));
            }
        }
    }
}
