import  java.sql.*;
import java.util.Scanner;

public class JdbcDemo {
    public static  void main(String []args)
    {
        String url ="jdbc:postgresql://localhost:5432/demo";
        String uname = "postgres";
        String pwd = "iaMbatMan";
        try{
            Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        try {

            Connection con = DriverManager.getConnection(url,uname,pwd);
            System.out.println("Connection Established");
            Statement st = con.createStatement();

            //FOR INSERTING THE DATA
            System.out.println("How many cols u want to create :");
            Scanner in = new Scanner(System.in);
            int c = in.nextInt();
            int sid,marks;
            String sname;


            for(int i=0;i<c;i++){
                System.out.println("Enter the sid:");
                Scanner sidIn = new Scanner(System.in);
                sid = sidIn.nextInt();

                System.out.println("Enter the Student name :");
                Scanner snameIn = new Scanner(System.in);
                sname = snameIn.nextLine();

                System.out.println("Enter the student marks:");
                Scanner marksIn = new Scanner(System.in);
                marks = marksIn.nextInt();

//                String query = "Insert into public.\"Student\" values("+sid+",'"+sname+"',"+marks+");";
//                System.out.println(query);

                String query = "Insert into public.\"Student\" values(?,?,?);";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1,sid);
                ps.setString(2,sname);
                ps.setInt(3,marks);
                ps.execute();
                System.out.println("Row cretaed ...............");
            }


            //FOR UPDATING THE TABLE
            String update =  "Update public.\"Student\" set sname ='Max' where sid = 4";
            st.execute(update);

            //FOR UPDATING THE TABLE
            String update =  "Delete from public.\"Student\" where sname ='Max' ;";
            st.execute(update);


            //FOR SELECTING THE DATA
            String query = "Select * from public.\"Student\"  ORDER BY sid ASC;";
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
                System.out.println(rs.getInt("sid")+" "+rs.getString("sname")+" "+rs.getInt("marks"));

            con.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }



    }
}
