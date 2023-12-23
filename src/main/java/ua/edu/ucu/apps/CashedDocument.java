package ua.edu.ucu.apps;

import lombok.AllArgsConstructor;

import java.sql.*;

@AllArgsConstructor
public class CashedDocument implements Document {
    SmartDocument document;

    private Connection connect() {
        String url = "jdbc:sqlite:identifier.sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(String document, String parsed) {
        String sql = "INSERT INTO documents(document,parsed) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, document);
            pstmt.setString(2, parsed);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String find() {
        String sql = "SELECT document, parsed FROM documents";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                if (rs.getString("document").equals(document.gcsPath))
                return rs.getString("parsed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String parse() {
        String parsed = this.find();
        if (parsed != null) {
            return parsed;
        } else {
            parsed = document.parse();
            this.insert(document.gcsPath, parsed);
        }
        return parsed;
    }
}