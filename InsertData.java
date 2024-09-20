import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsertData {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; // Update your DB URL
        String username = "SYSTEM"; // Oracle DB username
        String password = "BCA5C"; // Oracle DB password

        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {

            // Insert into Department
            String insertDeptSQL = "INSERT INTO DepartmentTable (Did, Dname) VALUES (?, ?)";
            PreparedStatement pstmtDept = conn.prepareStatement(insertDeptSQL);

            System.out.println("Enter number of departments to insert:");
            int deptCount = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            for (int i = 0; i < deptCount; i++) {
                System.out.println("Enter Department ID:");
                int did = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                System.out.println("Enter Department Name:");
                String dname = scanner.nextLine();

                pstmtDept.setInt(1, did);
                pstmtDept.setString(2, dname);
                pstmtDept.addBatch();
            }

            pstmtDept.executeBatch();
            System.out.println("Departments inserted successfully!");

            // Insert into Employee
            String insertEmpSQL = "INSERT INTO EmployeeTable (Eid, Ename, Salary, Address, Did) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmtEmp = conn.prepareStatement(insertEmpSQL);

            System.out.println("Enter number of employees to insert:");
            int empCount = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            for (int i = 0; i < empCount; i++) {
                System.out.println("Enter Employee ID:");
                int eid = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                System.out.println("Enter Employee Name:");
                String ename = scanner.nextLine();

                System.out.println("Enter Employee Salary:");
                double salary = scanner.nextDouble();
                scanner.nextLine();  // Consume newline

                System.out.println("Enter Employee Address:");
                String address = scanner.nextLine();

                System.out.println("Enter Department ID for Employee:");
                int did = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                pstmtEmp.setInt(1, eid);
                pstmtEmp.setString(2, ename);
                pstmtEmp.setDouble(3, salary);
                pstmtEmp.setString(4, address);
                pstmtEmp.setInt(5, did);
                pstmtEmp.addBatch();
            }

            pstmtEmp.executeBatch();
            System.out.println("Employees inserted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}