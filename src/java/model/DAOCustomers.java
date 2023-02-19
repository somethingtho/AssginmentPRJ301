/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Accounts;
import entity.Customers;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daova
 */
public class DAOCustomers extends DBContext {

    public Customers LoginAdmin(String pass, String user) {
        Customers cus = null;
        String sql = "SELECT Customers.*, Accounts.UserName, Accounts.Role, Accounts.RegistrationDate, Accounts.Status FROM dbo.Customers INNER JOIN dbo.Accounts ON Accounts.ID = Customers.ID \n"
                + "WHERE UserName = ? AND Password = ?";
        DAOAccounts daoAccounts = new DAOAccounts();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                int id = rs.getInt("ID");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    Accounts acc = daoAccounts.getID(id);
                    cus = new Customers(customerID, gender, customerName, phone, email, address, acc, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return cus;

    }

    public int LockCustomers(Customers c) {
        int number = 0;
        String sql = "UPDATE Accounts SET Status = ? WHERE ID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setBoolean(1, c.getAcc().isStatus());
            pre.setInt(2, c.getAcc().getId());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public Customers getCustomerByID(int id) {
        Customers cus = null;
        String sql = "SELECT * FROM Customers WHERE ID  = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                Blob blob = rs.getBlob("Image");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    cus = new Customers(customerID, gender, customerName, phone, email, address, id, password, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
        }

        return cus;
    }

    public Customers getCustomerByEmail(String email) {
        Customers cus = null;
        String sql = "SELECT * FROM Customers WHERE Email = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                Blob blob = rs.getBlob("Image");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    cus = new Customers(customerID, gender, customerName, phone, email, address, id, password, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
        }

        return cus;
    }

    public Customers getCustomerByCustomerID(int cid) {
        Customers cus = null;
        String sql = "SELECT * FROM Customers WHERE CustomerID = ?";
        DAOAccounts daoAccounts = new DAOAccounts();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, cid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                Blob blob = rs.getBlob("Image");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    Accounts acc = daoAccounts.getID(id);
                    cus = new Customers(customerID, gender, customerName, phone, email, address, acc, id, password, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
        }

        return cus;
    }

    public Vector<Customers> getCustomerFriendlyBySupplier(int supplierID) {
        Vector<Customers> vector = new Vector<>();

        String sql = "SELECT TOP 5 Customers.*, b.TotalMoney FROM dbo.Customers INNER JOIN \n"
                + "(\n"
                + "SELECT CustomerID, a.TotalMoney FROM dbo.Orders INNER JOIN \n"
                + "(SELECT OrderID,SUM(UnitPrice*Quantity) AS TotalMoney FROM dbo.OrderDetails\n"
                + "WHERE ProductID IN(SELECT Products.ProductID FROM dbo.Products WHERE SupplierID = ?)\n"
                + "GROUP BY OrderID) AS A ON A.OrderID = Orders.OrderID\n"
                + ") AS b ON b.CustomerID = Customers.CustomerID ORDER BY b.TotalMoney DESC";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, supplierID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                Blob blob = rs.getBlob("Image");
                double totalMoney = rs.getDouble("TotalMoney");
                Accounts acc = null;
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    vector.add(new Customers(customerID, gender, customerName, phone, email, address, acc, id, password, base64Image, totalMoney));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public Vector<Customers> getNewCustomers() {
        Vector<Customers> vector = new Vector<>();
        String sql = "SELECT TOP 5 * FROM dbo.Customers INNER JOIN dbo.Accounts ON Accounts.ID = Customers.ID ORDER BY RegistrationDate DESC";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                String username = rs.getString("UserName");
                int role = rs.getInt("Role");
                String dateRegis = rs.getString("RegistrationDate");
                boolean status = rs.getBoolean("Status");
                Accounts acc = new Accounts(id, username, role, dateRegis, status);
                Blob blob = rs.getBlob("Image");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    vector.add(new Customers(customerID, gender, customerName, phone, email, address, acc, id, password, base64Image));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    //list all customers
    public Vector getAllCustomers() {
        Vector<Customers> vector = new Vector<>();
        String sql = "SELECT * FROM Customers";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                Blob blob = rs.getBlob("Image");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    vector.add(new Customers(customerID, gender, customerName, phone, email, address, id, password, base64Image));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    //Detele Customers
    public int DeleteCustomers(int id) {
        int number = 0;
        String sql = "DELETE FROM Customers WHERE CustomerID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    //Update customer
    public int UpdateCustomers(Customers customer, InputStream file) {
        int number = 0;
        String sql = "UPDATE Customers SET CustomerName = ?, Phone = ?, Email = ?, Address = ?,Gender = ? WHERE CustomerID = ?";
        try {
            if (file != null) {
                sql = "UPDATE Customers SET CustomerName = ?, Phone = ?, Email = ?, Address = ?,Gender = ?, Image = ? WHERE CustomerID = ?";
            }
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, customer.getCustomerName());
            pre.setString(2, customer.getPhone());
            pre.setString(3, customer.getEmail());
            pre.setString(4, customer.getAddress());
            pre.setBoolean(5, customer.isGender());
            if (file != null) {
                pre.setBlob(6, file);
                pre.setInt(7, customer.getCustomerID());
                number = pre.executeUpdate();
            } else {
                pre.setInt(6, customer.getCustomerID());
                number = pre.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }

    public Customers getCustomerByUserName(String userName, String password) {
        Customers cus = null;
        String sql = "SELECT Customers.*, Accounts.UserName, Accounts.Role, Accounts.RegistrationDate, Accounts.Status  FROM dbo.Customers INNER JOIN dbo.Accounts ON Accounts.ID = Customers.ID\n"
                + "WHERE UserName = ? AND Password = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, userName);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                int role = rs.getInt("Role");
                boolean status = rs.getBoolean("Status");
                String registerDate = rs.getString("RegistrationDate");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Accounts acc = new Accounts(id, userName, role, registerDate, status);
                    cus = new Customers(customerID, gender, customerName, phone, email, address, acc, password, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cus;
    }

    public Customers getCustomerByUserName(String userName) {
        Customers cus = null;
        String sql = "SELECT Customers.*, Accounts.UserName, Accounts.Role, Accounts.RegistrationDate, Accounts.Status  FROM dbo.Customers INNER JOIN dbo.Accounts ON Accounts.ID = Customers.ID\n"
                + "WHERE UserName = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, userName);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                int role = rs.getInt("Role");
                boolean status = rs.getBoolean("Status");
                String password = rs.getString("Password");
                String registerDate = rs.getString("RegistrationDate");
                Blob blob = rs.getBlob("Image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    Accounts acc = new Accounts(id, userName, role, registerDate, status);
                    cus = new Customers(customerID, gender, customerName, phone, email, address, acc, password, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cus;
    }

    public int InsertNewCustomers(Customers cus) {
        int number = 0;
        String sql1 = "INSERT INTO dbo.Accounts (UserName, Role, Status, RegistrationDate) VALUES (?, 3, 1, GETDATE() )";
        try {
            PreparedStatement pre1 = connection.prepareStatement(sql1);
            pre1.setString(1, cus.getAcc().getUserName());
            number = pre1.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String sql2 = "INSERT INTO dbo.Customers (CustomerName, Gender, Phone, Email, Address, ID, Password, Image)\n"
                + "VALUES (?, 1, NULL, ?, NULL, (SELECT ID FROM dbo.Accounts WHERE UserName = ?), ?, "
                + "'0x89504E470D0A1A0A0000000D49484452000001F3000001F20803000000C88DD7DC0000001974455874536F6674776172650041646F626520496D616765526561647971C9653C00000180504C544583807BBCBAB748454197938E605C57F1EADB706C67B3B1AEE2858FA09D98A7A29A8D8A84C9C2B95B57526C6863817C76EBE6DFEADCCCDB6470E8E7E7D9D8D67A767154514DD1CDC7AEA9A2DDD6CDC2BCB3E2DED7938F88F1C4CAF9F5EEFBF9F577746EA752599B9790B6AFA6F6F1E6EDB7BAF8F5F0F5D5D95A4745BDB5ABC6C4C2DBD4CAE9A1A84C4945D8D4CD894E52FFFFFFC85662D9D5D0E9C3BA67635DDE7A81ECE8E4D1C9BF8A8680F6F3EEF5F1ECF4F0EAF6F3EDC8C4BEF3EFE8F5F1EDE3DFDBEFE7D5F4F0EBEBE7E1F5F1EBD95967F4EFE8F2EDE5F5F0EBF4F0E9F3EEE6F2EDE6F1ECE3F2ECE4F1ECE4F6F2EDF1EBE2EFEBE1EFEBE2EFEAE0F1EBE3EEE9E0EFEAE1EEE9DFF8F6F2EEE8DEF3EFE7EDE8DDEEE8DDF2ECE5F5F5F4E2E1E0ECEBEAA9A7A3CFCECCFEFDFCF0E8D7F7F4EF64605AF8F5F1F3F3F3FAF7F1F3EDE0F7F4F0DCDCDB504D48F7F3EADCD5CBFDFCF9A3A2A0ECACB3FCF4F58C8A88D1D0CFF7F3EEAEADABE4A09EF7DFE2DED6C6FAEAECE6B1ABD5CEBEE5979CECCDC474EF5B4D0000280E4944415478DAECDDFB6313C79D00F0951F2BCBF20388642BB6835501A5A831D99005B9F8EC228239B8F7F55EBDCB9D241B4A890B2490D03C9AB6FFFAEDAC5EFB98D99DF77C47DAF9312DE0DD8FE73BDFF9CEEC8CF3DB5FCD5FFB1BEAF6CF41FB757EBBCFDB1E25DA93717B886D47B4EDE9B03D8EB49349737E5B90CF19F93C9ACF3BF91C9ACF3DF9FC9917E473675E90CF9D79413E77E605F9DC9917E473675E90CF9D79413E77E605F9DC9917E473675E90CF9D79413E77E605F9DC9917E473675E90CF9D79413E77E605F9DC9917E473675E90CF9D79413E77E605F9DC9917E473675E90CF9D79413E77E605F9DC9917E473675E90CF9D79413E77E605F9DC9917E473675E90CF9D79413E77E605F9DC9917E473675E90CF9D79413E77E605F9DC9917E473675E90CF9D79414ED7FE6776CC0B724AF2D9312FC869C9FF6E56CC0B726AF259312FC8E9C967C4BC2067209F0DF3829C857C26CC0B7226F259302FC8D9C867C0BC206724B7DFBC206725B7DEBC206726B7DDBC206727B7DCBC20E720B7DBBC20E721B7DABC20E722B7D9BC20E723B7D8BC20E724FF7B6BCD0B725E726BCD0B726E725BCD0B727E724BCD0B7201723BCD0B7211722BCD0B7221721BCD0B7231720BCD0B724172FBCC0B725172EBCC0B726172DBCC0B727172CBCC0B7209E4769917E432C8AD322FC8A590DB645E90CB21FF5B7BCC0B7249E4F69817E4B2C8AD312FC8A591DB625E90CB23B7C4BC2097486E8779412E93DC0AF3825C2AB90DE6D2C8EF6D1F371A878787AE1F6B6EF09F1A8DF2F69D3921B7C05C06F9BDED72EBD0CF6DEE61ABB1FD60D6C9E19B0B933F28B5367DA676B85ABA33C3E4E0CDC5C8EF1DB79A114CA77A566ED76A032FD606B5DA79B9BC58ADC7BB7C637B46C9A19B8B90DF2BED4D08EB8BE52435A6F56BE54A35DAE1CB7766901CB8B900F9F104BC5AAEF53C8636382F4FE1DDD6F1BD1923876DCE4DFE6075949BD7CFCE3DAED62F2D4ED2FBBDD2BD5922076DCE4BBEDD1A83F73D9116B813D8AD26876CCE49BE3D9C94B91531F0513B3FAB8F83FCF68C900336E7231F893BED9E27ABF5C7ECCDF2BD5920876BCE45FE6018D5AB354F6E9BB0B7EED84F0ED69C8BBCE12A111F06F9CA68FE766C3B3954731EF2ED4D75E2A8F54AC3CEDE2CD94D0ED49C877C354CD5CF3D95EDBC3A51B7971CA63907F99DB093977B9EE2361886F8E6B6BDE420CD39C84B682477FA9E8636523FBC632B3944730EF230AE973D4D6DA4BEFAB99DE400CDD9C91FA0D2BA5BF3F4B54158A0734B5692C3336724DF6E8C8A303D4F6BAB396180DFB5901C9C3913F9716BBC0E52F1B4B730859876757BC8FF0F983903F9BD4673BA5AEA1968BD30C0EF7D6E1B3930737AF25195D5772BEDBE67AA9DA3AEDEBC6319F93F8232A716FFCDEA08FCDC33DAC2AE1EC477ABC84199539397C361BC5EEA79C65B299CB559450EC99C56BC1666EAF5B607A2F5D1EF5FCB267240E6D49D7C3835F6A0B49E438B0E841C8E39ED481E6E6D3CEB79705A38A8B7EC2107634E1BD7D1FCAC5EF360B50A0D3A187228E694E4C768F05CEC791E40F4B22DE440CC29C977505C2F79001B5A573FB6841C863903B95B83481E2672EEAE1DE420CC59C8FB1ECC3608469D4D3BC8219833903B3D0F6AAB053F5EC30A7200E6B341EE79A86EB06D03B97973DA495A103BEB90C93DCF21447768E4C6CD694B312EE0B17C5C85C5477770E4FF64D89CB6E08A4AECC0C9C3E89ECEDDE1911B36A7256F04E46DE8E45EAF9E2EC70124376B4E4BBE6D66F713FB1E8A541A0791DCA839F50E8926F0943D5A8E3B044F6ED29C7A8BC4AA0D83F96492BE0D9DDCA03935794DE7F70A323B3A507273E6F4DB1D839CDDB1843CECE87780931B33A7274705B89A2DE6A8A3B780939B3267D8C7DEB423678F74F45DD8E486CC19C8836EEE0EEC31F7EAC3621C607233E62C1F2835ED49E0C2D6F6FD266C7223E62CE4A89BF76C32EFB9BE7F0C9ADC8439D367889BBE7FE659D52ABEBF079ADC80391339AABA0EEC3247CB6B9F4326FF07EDE66CDF97B7AC4ADA2759DC0E6472EDE66CE4F7AC9A9B4F9754372193EB36673C45A2E4FB75DBC8BD019AA20326D76CCE7A56CC9E6513B5C926A9126072BDE6ACE4F7ECCBE046C17D0F30B95673E613A14A16ADAE2432F74B70C9759AB31F026667680F33F763B8E41ACD398EFA736DD92B912ECBACC225D767CE7386ABEFBB3692A37D714DB8E4DACC794E6A0E72A1452BCD7BA3D91A4C725DE65C47F0EF01FDF0986AB6760D2CB92673BEBB559A1616E186EDCCF7AB1F4225D763CE478E66E77692A301DDED7C08945C8B39EFA55976CECEC703FA1A33BA26721DE6BC57E3952D5C538B0CE88D0E23BA2E720DE6DC1760AE5A5A9119CDD05B1D36746DE4EACDF9AFB93DF4FD735BCDDBBEBFD96142D747AEDC5CE066E34D6BD3F6B0E4BED16141D748AEDA5CE4FEF2200FEAD96AEE053FFC0E03BA4E72C5E622E4BFB677AA36AACA74A8D1B592FFBB527321F207B656DBA3491C1DBA5E72A5E642E4687A5EB5D73C9868563B94E89AC9559A8B915B6E5E1B25EE14E8BAC9159A0B925B6E8E364276A8D0B593AB3317259763DEAF540D2DCD85D5570A74FDE4CACC85C96598B783ECB9AEE91A5572E29E896E80FC5F14998B930B9B0FCE5CBF5E1EA0BBEF2A06E6F955DF5FEDE4A29B2057642E815CD0BCB618FCF161E976B068E208C9F274B246463742AEC65C06B98879AF5DF7DDB3E9CEF89AABFFA8B15264B246423743AEC45C0A39BF7918D4DBBDF89287F655D9D8640D8F6E885C85B91C725EF37E6512D46365B1B67EF37A2713DD14B9027349E47CE6B52075AA603E77EA39DAD7656313740CBA3172F9E6B2C879CC03F1E8301E43AFEBCEE370E6117473E4D2CDA591B39B23F1323157EBBB9AD1EBBE7F8B8C6E905CB6B93C72F4154B5D9AB801F46ABC28134737492ED95C2239DBFAF9A09223AE1F9D601EA21B25976B2E959CC5BCEC6233B7D426359DE8C1FCA1D2C1A39B25976A2E979CDEBC56F7AB5498359D3D3D55889BA29B2597692E999CD6BC77E6BBB4936F9DE19D6CDEF9D028F96FE499CB26A734EFD7592ECD0DD0CFCD9BC7D17593CB33974E7E9FCABCE4B32106BF229A2A72E7BEEF7428D0B5934B33974F7E9F667F7BC577180F19EA39D8C344EB67B257613005771CBA7E7259E60AC8EF1FE69A077EEC6B27BD45DC6050A54F0AA4988FD10D904B3257419E6F1E90736D7C3AC3040774997575A0D17C886E825C8EB912F27C738777686E63AE510F6F302F693447E846C8A598AB21CF35AFF09F3CD277D37F16DD602EB3ABE79A773E34432EC35C11799EB9D03E88A057A706F5BEEB9CF9F2A672F9E609745DE412CC5591DFCFFEFE7C20B8DFE92CBD1FB6ED2F065339591B2629CC63E8DAC8C5CD9591DF6F649A57458F0B6CBBA94CFDCC2FF72AB2F646D39847D0F5910B9BAB23CF363F173F83A2EFA4FA7435184D30BF0BEACC27E81AC945CD1592679BD7EBE22138DDA77BAEDB0B7F17B4998FD07592FFAB98B94AF24CF3B69C0A6AAA4FD7D0C9933DF6EA1EBF7988AE955CCC5C29F9FD52C6D19F7549D737F493F9FB59B857B28499C0476AFC54C19FD63C40D74B2E64AE96FC7EC686B8B6B48592A04FD7FBB1BD92E1A0114CE0DB599A6589E69D0FF5928B982B26CF32AF4BBCA5E53C5E9F19258799A5FC3ACDC975F4E6647425E402E6AAC933CC6B52CFFE1D387EB517CDDD07E304AF479CDB57F3D119CC49E86AC8F9CD959367982F4ABE4EB31C2DBFF5C79CA578D48F66017EBB921BDE59CCF1E88AC8B9CDD593DF7F40DA34D193FEF559AD1E5954AF8CEFFD3927CED4DDC5201CB4259AE3D05591F39A6B20BF4FDC28D396FF1D526F713A551F4C7EA582ACBE4C0834C1809FB3B5AECD649E465746CE69AE859C68BEA8E29EBDD2B4534F878E5E155F7E2FF97DB424C3BD1F8E025D1D399FB91E7292794FCD87C5D3A97A3445C49767FA28B0B7B36F6C66358FA32B24E732D7447EBF89BF76A9ADE813D3DED93869AB3BB1FD15981F22E45ECC5CE661368FA2AB24E731D7454E5A40AF283B12743C552F476F6FC49667AAD5E1726EC65FB698385086055D293987B9367292795DDDB17183617C1FC4E6FF034C79A6E20E87F576D6622FFE7B350A74B5E4ECE6FAC8EFE30F701F283DC97F18DFEB4E72FDAD970CDCB9AB7B3CE64374C5E4CCE61AC9091B65DA6A4F753F47A1FC2C718A786ACDA53D1CCADB19BF7F93D3BC59D15593B39AEB24272CA69E293EE11B85F2F3E4EF552DB169B236FA3F38E48A20F69C090A74E5E4FFC966AE951C9963664355D59731A1A5B6D42F5B62D3E4D8BC4DACFC4F6E6A6046574DCE66AE97FCFE31B6E0AEE1D82FECCAFD59B4FC5E1B676FC4153EB6D22B115D013993B96672349E3B9A53B84928AFE246FA48F97DFC531057F24BDCE6517415E42CE6BAC95BC188E82FD652FD47C7C53CBD0179261733EFB9D899E3A0E4F8ECF3F314BA12720673DDE4C168EED72B6E72482FF903CF549B56EA26E641C84FFF3C838AEFBBE8B3986B62E86AC8E9CD7593DF095E59A58716BCAA3DCCCCD8509B6CAA99980FD27966DBF52B7DF4A5BBEFAE89A02B22A736D74D8E0A32C34248393EA857CDDEA13ADA54539B568B9C7AAA363C5C96EDBBCC15F718BA2A725A73EDE4A89BD7C6D1BC92AC749B6CE1A69A4856514AACB45426CBAF65743B3237BA32F27FA333D74E8E72F6EAF42D467263D7F8BDB968534DA4689308EE95E96F68CF0D6FCA95812E939CCE5C3F395A476D4FEB21EE20354932D8821CC38D74EE5870EF4783522575863B27BA54722A7303E40F621767D622251210772507A95C7CD34CA4723388EE9B1608EE1174B9E434E606C8D1372CD558C0AC4DF841DC953CE847AB4491FA6B6C45AE17BF7C89175D323985B909F2E492DAB4F2510378577264413FBEF29EBC7C890F5D3679BEB911F2D4D279794C0DD13CB2F29A48E217F96B711374E9E4B9E666C8535B6426FB1E219A9F1327EB658119FA085D3E799EB921F2F496D74577BCA6310067DE9BCCD692453971731CBA20798EB929F2B4F968638AE1D22BA13955427D264845F73AB2D145C9B3CD8D91A7CDC77D09A4F964EB4EDD91B5884E461726CF3437478EF99C6114DC419A8FB7E8F593BB66A498C7D1C5C9B3CC0D9263CC475F3280341FCFD052EBAA72CCA3E812C8FF836C6E921C633ECADC419A8F1701DCC5D47A8CF8781E4397414E36374A8EFB54AD12CE82619A0F1778D3FBA424E4ED317429E44473B3E438F3E1978330CD87495CD5F554990FD1E59093CC0D93633F4F0CB798C2340F6792981D33C4BB97B8D0259113CC4D93633F552BA1FF04D33C5CF9C1EC8CAB0AD6DB63E8B2C8F1E6C6C991796A0F71B8D002D33C5CE1C5EC80AD4B34EF7C25891C6B6E9E1CFFD9125A682901ACBD0E1377DC4E77CEEF97C8E832C871E600C8EF97715F920CFC2AC83596E12E3DCC172D35B13D13697429E4187308E4E89CB03A2E3DAE0135AFB8354C606ACB29C9E0D1B9C9D3E620C80967C904233A50F3B25FC57CA17A266DAA9646E7274F9903214793B51AEED52EC2342F613F9B7644F6BD66A30B90FF57C21C0A39FA580DB7D9D1F161EC87C38CDC98DC92EFD8011A7411F28439187294C4E13E5EE8FB20F6BD62CC2B9EF2146E8A2E441E378743FEE80EE170B8324C73AFD4C3A59C22DBDB33D0C5C863E680C81F3D6A126EDA6AF73D5B9A23BA0392842E461E350745FEA8E5671FB308BF0D0477B7D3A0F39047CC61913F3AC6CED06D6AC1ECBCDEE92845E7229F9A03237FF4C8F5FDBED5E68BF216D508E87CE4137370E4D607F7818A995A0C9D937C6C0E8FDCFAE05E5217DA87E8BCE4237380E461703FB7D8BCAE286B9FA0F3920FCD41923F5A25DFBB6441ABF9FEC65A471F3A3D79680E93FC11FA067D60AD79554D418684CE40FEDF813950F2478F0E7DBF62730677ABA30D9D853C30074BFE68DBE28E5E91BD749E89CE44CE62AE9BFCC9934D6B3B7ADF97B9132E0F9D8D9CC15C3FF99360BA66695DA6AAA59B8FD019C9E9CD0D903F79726869EA7EAEA99B87E8ACE4D4E646C89FA011BD641F792F989B3B1D4DED2B56725A7333E44F9EECF9BE6B5F1A77A6B4EC8A47A727A7343745FEE473D7C2E88E227BABD3D18ACE404E676E8CFCE14394C659B6D482CEF6AD773A5AD159C8A9CC0D923F7C1844F7BC1B88810DE68EEF6FDCEAE8456721A731374AFEF0F34DBBD07B2217344840CF27FFDF7C73B3E40F1FDE716D420FC95B9D8E29740AF27C73D3E423744BC6F45A304B53BEB692814E439E6B6E9E7C845EB7A0ABA37B580C910FD1A9C8F3CC21903F7CB88BC6F4E06582EEECFD2AEAE3FEC66AA7630A9D8E3CC71C0679D01A6EA80EB9F85E0D7FC2CD5B1D63ED2B3AF26C7330E441FADE6802AFC3A23E5EBDD631D9BEA222CF3407448E5A197449EE5CC9C7697CE8D9E459E6C0C88F7663F77500ACB1573B30D073C833CCA1911F1D35216F84ADCBFFD69C133D8F9C6C0E8FFC08F2570EEA3E4E6345CF25FF82640E90FC08F2570E255D1B6358D0F1E4247388E4479F039EADA9FC388D179D404E3007497E74B40776B6D6D3B1B599119D448E37074A7E14CCD61C98E66D0833B5383A911C6B0E951CF06CAD22E9A07679E864729C3958F2A3A34DA8ABAAAEB67DAE94E819E41873C0E447ABB823416114E1363AA0DA2FC9E46973C8E447778006F70A88221C163D4D9E32074D0EB614E7C228C261D031E44973E0E428B8578AD04E8F8E234F98432747C1DD2D423B353A963C6E0E9E1C68708718DA43742C79CCDC027290C11D66688FA27F4132B7811C64E6BE0833B44FD1BF20995B411E067760651950B5761CFA1724734BC801966560D5DAD3E85F90CC6D210718DC1D231FAED0A393CCAD210F6BEEA01654075A3F3697813E34B7881CDC82EA19981D32B4E8A1B94DE4E1822AA4DD3275039FA18AA12373ABC8C3DD3280A6E8E7CACFF8948E1E985B467E540AF26438591CE0C93909DDF9AD6DE447472EA0CB97069027E70474E7F7D691A3293A988E5ED67D768C0474646E197998C541A9C5B9F033B8147A606E1BF9D1D121988F1BDA3664704974E7F7F691A32C0E4847AFC3DAEF4A874E630E8DFCE8691348476F43AFC161D129CCE1913F6D00E9E83ACF7595879E6F0E90FC293A12B40EA39B5FEB58879E6B0E91FCE9D316888E5EB765A21647CF338749FE144DD7EA3D00DD7CB5631F7A8E3950F2A78F5147375C8CEBB9A0374B90D1B3CDC1923FBEED1B2FC6956DECE6083DD31C2EF9E3C787A60F04B5B49B072DCB1C32F963746D87D19BB82A3EEC3D515CE6A0C91F3F46E7811A3C2F0EDDA6654DD995D61C38390AEE75BF66CCBC6A6D37279B43277FDC08C673D7586106DDB9E2AECD96397872645E6E9B9AAFA1DBB4AC4CDA33CCE19387E65ED5D04D5C689EE67666CADC02F2C7C72885EB9B49E35002676D37C79BDB407EB21DA6ED65239F26A3046EB3334BE656908FCC7B7503D538B467C3B205B51C733BC84F2E05EF3D78FF35FD9F2C0E5C74B9526786CC2D213F39199A7B67DAA37BD5EF4339C5598EB935E46373EDD1BD14CC179C598AEDF6909F3447DFADD5F4E6EE7DD70993B89931B788FCE4D01F955ECB5A3F4E76DC7EB8C0529911739BC8A7E641A4D5F7A1EA59F80B56B6B6D89E34B78A3C623E70B5ED933A1F4E13CA36ED6BCF32B78BFCA4353D70E25CD784AD3FFAEDAACD484DC632F293466447DC999EC5965E3898CF8EB96DE431F32091D6B1F5B932FE5706608F016431B78E3C6E3EE9816A57D326075CD85C881B9BDB471E370F465AE5A59976A4106073216E646E2179C23C18621DB5E8B17FC0E6A2CCD0DC46F2A479D00D95A2078164E0CD8EB995E42973B5E80179346168595C8843E676929F1CA7F63A2B444F907BAB1617E202734BC9479B261273A9453DE4C87CCF5E735BC971E6017A450BB977CBE2A20CBFB96972ACB91AF434B9B7368FE6C6C9F1E62AD031E4DE2FADDEEB6C2B39C15C3E7ADFC5ECBEBAB0B810C7690E80FCA444F84651327A408E29E5CF9F3904F2F4FC5C097A10D8711F425E34ED2DCA70998320279B07E8D59EC2B13C343F047AA79A227318E427ABE413652AB28A3324726FD9E2093A87391072B437AA4D5E0293825E23917BDD1DFBCE09E33787427EE2FA19470E04E8E2EBE96D9FB828DFBD6CD7B19F42E660C87747DF3490BC5CE14D1425DF217EE9DCED1E5ABBCAC26A0E861C4DD51CF63916C3DEB7CCA4A0DBBD656D5586D11C0E391ACEB3F73D0E1C91C3C4823F9D35E3EB86C1FDDA1C98032247A1FD414E4F5DCC08CE79FBD873A244B7DB5DB4B5E4CE640E881CED6E3F7C933F22BB5C9F3505BF2DF5EC6C2030FFD1B7748ACE620E891C75F35B6FF2E7D78E5F65EFEA6DD73FCB99EA75871DDDCAA3A318CC2191A3D1BCD97D43E157F673FD9293F2AA5FCF3D770E99FFE8DA5997A13707458E4EF758EB5E5025638BBE5B1EB088BBF9DFC4EC23F3EE353BD3386A7350E4B7831E560D5EFA335A46BF42756264AFED04E21461E17A68DEDDF4FD8D9D993507457EA91944F6CBC13BBF4A5B36AFF87EFD2C877DD00E06E87A996A20783E3447D1BDBE36A3E6B0C8D1F1BE7F46EF7C993E136FA3737FAAE5737C94AFB52BC1FFEE56288FA6B9DA1DB51D1B0F0DA33387477E6BF8CEAFB24CC0DA157462A7EF54CFCAE5F3DAB095CAE5C56AF89FAB65EA03839F3D1F9B77577D2BEEC8653787359647C8BBCFBF669C78D7CA6755D4E323CDAD2E96DB4CB5F9EBDD695BB40F9DC61C14F9B61B21EFD2A5EEB8603E6A3C75BAFD6E3781BEB93663E6A0C8D16D7A1172B6E82EA74522FB04DDAA442EDF1C54F50DDDC2E2EEC45EF997BACD97BBDD34FAC6AD193287445E46717DF3CFF137FE7C5F2FF9F56EAAA1AB592C5A4CCF3387B492863AB9BF7839F9C6F5A263C8C3B5748B06F51C7338E49756FD745CD78F8E25EF76FFDC44F1BD310BE670C8C3B0EE572F63DFB83E740279B77BB91A76F51DEBCDC190975037F29B3BA437FE5C4FF6FEECA24B6EB7C2DFCAD69ADDE650C887E2EE6AC60BD73265FB7A39EB271875F58D559BCD61905F1A8A6372B778BBFE4C35F9FEF36E4E5B1BFD725A6B0E82FC52C31D8AFF98F7BEBBCB8A07F5375D8AB61AFEB86E65CD4A7308E4B75B3EADB8EA413D73288F06F8A1FA4675C73E73E3E4BBC7ABC3A0EEAEFED8A56C17CAE2FB97CF697F86B1BA5FBD6699B951F24BDB8DBDE668D1AB79ED7297BE3DFFD268271FAB5F1BFDB6EE5DB3C8DC1CF9ED526B73BACCB95E7ADE656B2ABAFA97AC3F44B75B5E1A3DC166ABB16685B921F2DD52AB39F55E5ADF5A5878FF25EBDB963EAA7F7DC12CDEBD282DBC5E7F317E92FA1E2C779CB909F2EDF29E1BE57EBD10B692C7FEC62FA426F06FD83B79F7F9B316FAE123ECC87D07B0B96EF2DDE3C661A47B8FB9C3F636B55A4DD3E4CDD5F797BB1C6DDF6B8E7EFE83ADF5A5E9861CA7756D0DA4B94E7294ADB9B18D4AEB0BB1E604A329C74B9714E079C23AFA95F3BCE83304EED19D58E6E153E6BAC8A3C979A42DC5CD9BE8E8169EF7BE2CAEFEF5752EF1EEF233EF7DFC29B6924F19C01B0CF549731DE44130C771E3CC17307B91F40CEBCFDEF0FDB328B27BDFE598876D63736FF51A0073D5E4B7771A87AE9FD512E61F787CD15D509D5F3CFC88CE893FC452C6F3D68358BF63D25C25F976B975E8E7B783F8EB0A3F02BFE8EA5517101F7E6691F8C57D91F7D07ABB7CCC5C11F9EE363996A7DAEBF8EB7AEBF147774EF5AF05C4871BEE13C3F901E5A3D735A577517305E4681EE6FA2C6D2B9DC4F147778E6C8E37738BAEE3B76886736297575EB98B984B27DF6D6CFACC6D3D9DC48944F7B0EFBDA19EAFEF0BFD43E34F2C1289E83AF34BA85776B498CB26FFBCE5F3B464E2FECA138CEEC329334D887F767559EC5FE93E0F7FB93E601DCE716D6F4DBDB96CF292CB45EEBF48BCB0EF3CE1E83E0AF1399D7DFF7A57B80D97F5BEE31BCE9373F85BAACD25937376724CE23E3E03EE42182433C4EF8BFFFD61010EB50D81E13CDA56D59A4B26DFDDE4264F26EE5B84AFC4F8D4558AA3021C2EB4AF73BF8AAA4A73C9E4775C7EF25412F7CA9314DDC99BE6DE74A5B4D1DFFD36F10002EFA2AACE1C12792A897BEB498BEEF89D34CF2EE4908F83C892A4D0AE0C1D9983224F555F373C89D11DB313FED9B21CF2F13927EFA5857655E8813930F2E480BEF0D29319DD531F2F4B221F4776EF5B193335A5E8CEEF6567ECA2E4C94ADCC2F4F44E3941387E1EC97549E4E3F0F17241626857F38DB3F373C9E49BA2CF981AD01D4F7274BF209E1322E1EFFC4E6A68F7559C3A9834172DC5B47CF1B6400AEE42D1FDF9F57D6F7FB87C723511D92FBE7CF6F5D50B39B163495ED63EAEC0EFA83517252F4B20CF08EE02D1FD62A8F2F567A8DD7CF7EEDD473783F6D7C8C982579F4BC811DAB243BB2FFFDE97B8B928F91D19E4A9197AD3138FEE376FBEFB7865E514D7EEAEBCFBE8078AEF4EA9468BA6DC0C6E547B57682EBCACD294629E2CB9879B6504A2FBE5EFEF9EE6B6951B3F70CFD49F4F8ABAAF123FF96B292F44F2901E35175E3C5D95F384A9D9DA5B4F28BA7F7C4AD7EE7E74E327A1695A7AA2B62EE785C83D263E622E4CBE2D893C15DC973CA1E8BE724ADF3EE35F5AC194DA0F64BD913D35E6C2E492223B2EB8BFF744A2FB5D06F32B7FE55D5AC175F32D596F446A749F988B6F845A95F680A9CCFD5B4F20BA7F7FCAD2AE5CE68FECC96E2E27831B467705E6E2E477E491A7827B348B638DEE374ED9DA5DCEA515A5DDDCF75BD2CD256C773C94F880A9B2CC5B8F37BA5FBEC2687EFA17AEA515B5DD5C6A6566682E81BC24933C15DCB73CDEE87EF794B97DC655804BCFCDB7A4BE92AA5C7309E4F212387C1657F2F8A2FBF7ECE44C437AA48CFB5E65379799C62173199F2E34E43E5F6A8ABEE47145F7BF9EF2B415AEE51AB5DD5CE21D2081B90C72F115D4BC2CAEC415DDEF72999FDE602EC0795E49713797D7D19D9F4BF9406955F6F325B7BF263A3A6574FF9E8F9C3ABA47B65FBC5C52DCCDE5ADB5387F9041BE2BFDF9F23A3A5574BF7CCADB56180B70E99D8FF2BBB9B4CDCF1373A1CF105BF29F2F95C5C53B3A5574BFCB6D4E95BB470A70E9799AFC6E2EAD303336172257D0CDD3D3B54447A788EE3FF1939F5E612AC0A5133815DD5C56471F998B7D6CDC52F17C791DFD4BF9D59868FB9E29B2A712B87515AF4452471F9A8B914B4FDAA93A7A6E74FF8B08797E1A17BDC3F1E596AA053515A97B682E788A4443CDF3A53AFAD64B96E87EF954ACAD30447627159314996FCA32172457D4CD311DFD2D4B74BF2B689E93C6453F8D486E8293B53D4651470FCC45CF8A29AB7ABE7447FF80F27694A07D264A9EBDC0769115D9D52470D2AAEECE1F848F076A2A7BC0D4BB74E83F3BBA226C7EFA13E577118E8E79DAB8ED283367203F56F77C2F0EB236CC647E91F0933879D67C2D33B21F282497B14B0A6FCE7208D8A1C2075CCF99AF11A3FB6509DD3C63BE168DEC1F6CE94AE086EBE86B6ACC59C877553E60AAEA9E4CE348D1FD7B19E4C4F95A7469255D8DD952FA4624D46570E64C47FDB5943E6072E122BE4B8A18DDE574736247DFCFAAB31FA825975097C19833912B9BA811D6D1A31FB590A3FB8A1CF22B1F2FE715E0521B25D4467629D3B5B439DB19AE3B8A1F30355F1B1F249515DD2F4B115FF9E81BEF6ACED24A7A9AB6A59A5C7CBA9632673CB67753F513AEE74DD2F75574F32B089C702241E6D2CA81AFBEAD49366724BFA3FE090F98A3FBF2471FAF0879BFFB61F2975FCDDADB9C3CE453693566DA2A72CD590FE76EA97FC225E6E81E4E9E6FDEE080BF32FE44957CF4C872D66A9AA2E534C9595CDC9CF93C7657C323A686CCC9F161F8E81E51F9E6E647EFFEB2429BC4DFF806738ED8557201EE9581C13C6CB7249A3393EF6879C45474DF789915DD71E738FF40E3FE27EC7981CBA4BF3CB5036EE1E0851EF3AA3C73F68B36F6B43C623ABA7F9B11DD97B172148BE957F067445E2515E036F44FD3C6B53869E6ECE4BB9A9E311DDD4BE4E88E3FAEFD4FF9E61F130E065DC617E0BE3533984BA8C54DCD39AED329EB7AC65474DF7A458AEECB04B9FC84EE232FB7A347F6367FB760683047CD9163CE7383D2A6AE674C47F7C4903E8DEEA45B19F23BFA375E5E47BF9E95B21FF81ADB9A0C731EF25D7DCF98AACC2497D2F773BAB9E7E59D30B242FC9357D305B8570B4666E672A6E82373AE7BD21A1A1FF275DE2CFD7A76370FE66D399BA56E900F775F4E16E0D2B3346DF9DBB0D5C5CDF96E436C6A7CC8F4F689E1355C89E8FE3CE322866FB2CD6F7A391DFD7AC692B9C6FC4D7CBB4C68CE477E5BEB43A687F4441EB79F2C8CA61A773F0F77D84E478D971B26F337F1E08ECC39EF3C5DD5FB94E9217DE96532BA3FCFBA73E526DF546D7CA8FF7E06F96BDDE442C13D30E7BDE6B6A9F931B77292F76717179957A9DDE4CDE1E27F3586FCE085767391E0EEFC8197FCB6F6C73CC8ABC765B78FB8CA70C906845C24B83BBFE3BDCC7A55FB63A6F74F30A1BFE39D9FE7906B4ED9C5833BB579EACAFA4DFDCF99CEE392333611F39B36918B04775AF314F9AE89E74CE771C9CA7B46CB2BCADCE0235F37432E10DC29CD53E4FA6AED39791C3DFACA297FE20E8F5C20B8D399A7C94D847641F49553FEC41D1EB94070A732C7905F32F5A4E9226C72DB0CA95D39154BDC71E45BE6C8F9175469CC31E42725534F8A29C26ED1A1F32FAC8124E75F50A530C7919FECF9D6A1E79A6726EEAFA091F32FA8E69B63C94F0C3E2A66C6B6D5CE27FF217F0F6416F9163872BFA1CA1C4F7EECC342A748E46E726F8E429F280124E7DE0A99678E273F59F5AD43CF372727EEA50580E4DC5B2173CC09E4274DDF3AF477BC1B5F31DF9E8220E7DEE79E6D4E22DF35FDB438F4B7A2E6A4C4FD5BA0E4BC674E649A93C84FCAC61F175385CD5970A130BF493B4733598A112FC5659913C90DCED432D19D9702653842E2FE6A092C396F292EC39C4C7E02E17971161B2F85CC31897B7B0B303967298E6C9E417EEC4345CFA8CED0DC9F4995BDC121E72CC511CD33C80DCFD4B2D14BDC65B874E2FED2C1912F8121E79CAD91CCB3C84F367DB8E8C4F49DE66BE46FF287F20340E49CB335827926F925308FBC74409DC9FD89C63C96B8977043B991BD6FE4D692679E496E6E4D8D6AC125C8E45E7195E1E289FB4BDCAC7CE1352C72BED91AD63C9BFCA4E50347C70DEA54E61F672EA301A9C408AFADE1CC73C84D175E130D1781175A2973AA6B535732E33AA0845D686D0D639E47BE0BECB1B13CA9F8FEEE943E71C7E7EB10C9B9CAAF69F33C7248C339B924978AEF54E6C3C4FD3D763E002B611F375786792E3984C22BCD9C2D91BFD31D1C76137BE81BC4EC4DA0FC9A34CF273F71E13D3876CEB6B0F59ED9FC1D21790398BDF1975F13E614E4B7213EF98BD758A9D64B46F3BF608BAD3087F261AB8A9A5390035847A5CFE41696DEB394E14E3FFDC5823D4339F7801E33A72107389C676472D3AE4E43FEC982554339EF801E35A72287389C670DEA0B4B6D8A834550FB19BE93C31DCA7907F488391DF92EDCA7270CEA0BCE071465B84F099D7C61093639C7803E35A72387373BCF1FD417B6DEE69AFFECC0C6B8CE37A04FCC29C94115DB6967EA41676DF0E46EE0E33ADF803E36A72507566CA78EEF0B9F7CCA1ED6C1C775BE017D644E4DBE0BFE156C2D30AA93C2BA05719D6F401F9A539303D90AC793BF07D3EC4F18B275C87518C1013D34A72787B2152E3BBE13BBFA2F7E463B9083AEC3080EE8C89C811CCC5638AEFA4C529D3C905B91BC8D5B83DD9C85FCC492D7404CE522EA19E21675729F7D0DDDF91D13F9B6352F82DCD587EA9F7E7240EEE42F6C2267DE143736A7233F69D8F32632BA7AA09E216E5727478DCF9C921CEC020B6B57CF68967572D4AEF198D392C35D6061EEEA33D3C97DE65DEEA13935F9AE6D6F637DF63B79D036D9CDA9C9612FB030CED567A593FBCC9FAD05E6F4E4565464A8CB7256CFC9E3ED16A33903B925159964A3EBEAAF5F584BCEB8CCE2FC9181FCC4D2574293CBADFB16B72AB7792EF9B6B52F252FC06FBDB0999CB12A1331CF2587BAE55538C0BF5EF22D6F6B7CE6F9E4C0F7C8F066F007EBBEF5ED1A97390539F43D32B901FEF50C86758EAACCD89C86FC92F5AF66FD6096B275DEAACCC89C86DCE2148E54983B58F267A36DB09B5391DBB4A84635ACCFC2403E6E3BACE674E4762DAAE5AB6FBD981D72A6BD32C89C92DCF6142EAE3E53E26C7B6502735AF24B33F48A5ECC96385B12E7FC91967C2652B8D96D2CE6BFA2366F142F1670BBA5C47CAF78B180DBAA12F366F162672389A337BF54BCD71949E2E8CD8B146E5692387AF322859B95248EDEBC48E1662589A337DF2C5EEB8C2471F4E6C55B9D9524EEFF0518008B01A6ADA850110B0000000049454E44AE426082')";

        try {
            PreparedStatement pre2 = connection.prepareStatement(sql2);
            pre2.setString(1, cus.getAcc().getUserName());
            pre2.setString(2, cus.getEmail());
            pre2.setInt(3, cus.getAcc().getId());
            pre2.setString(4, cus.getPassword());
            number = pre2.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }

    public int ChangePassword(Customers cus) {
        String sql = "UPDATE Customers SET Password = ? Where CustomerID = ?";
        int number = 0;
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, cus.getPassword());
            pre.setInt(2, cus.getCustomerID());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    //Admin  
    public Vector getAllCustomersByAdmin() {
        Vector<Customers> vector = new Vector<>();
        String sql = "SELECT * FROM dbo.Customers INNER JOIN dbo.Accounts ON Accounts.ID = Customers.ID";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                boolean gender = rs.getBoolean("Gender");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                int id = rs.getInt("ID");
                String password = rs.getString("Password");
                String username = rs.getString("UserName");
                int role = rs.getInt("Role");
                String dateRegis = rs.getString("RegistrationDate");
                boolean status = rs.getBoolean("Status");
                Accounts acc = new Accounts(id, username, role, dateRegis, status);
                Blob blob = rs.getBlob("Image");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    vector.add(new Customers(customerID, gender, customerName, phone, email, address, acc, id, password, base64Image));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public int TotalCustomers() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Customers";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public static void main(String[] args) {
        DAOCustomers dao = new DAOCustomers();
//        Vector<Customers> vector = dao.getCustomerFriendlyBySupplier(1);
//        for (Customers customers : vector) {
//            System.out.println(customers);
//        }
        System.out.println(dao.getCustomerByEmail("daoson03112002@gmail.com"));
    }
}
