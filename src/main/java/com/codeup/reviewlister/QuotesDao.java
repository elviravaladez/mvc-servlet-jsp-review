package com.codeup.reviewlister;
import com.codeup.reviewlister.Quote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mysql.cj.jdbc.Driver;

public class QuotesDao {
    private Connection connection = null;

    public QuotesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    public List<Quote> all() {
        List<Quote> quotes = new ArrayList<>();
        String query = "SELECT * FROM quotes";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                quotes.add(
                    new Quote(
                        rs.getLong("id"),
                        rs.getString("quote"),
                        rs.getString("author"),
                        rs.getInt("stars")
                    )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quotes;

    }

    public Quote random() {
        List<Quote> quotes = all();

        int randomIndex = (int) Math.floor(Math.random() * quotes.size());

        return quotes.get(randomIndex);
    }

    public Quote getQuoteById(long id) throws SQLException {
        String query = "SELECT * from reviewlister_db.quotes WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, id);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            rs.next();

            return new Quote(
                    rs.getInt("id"),
                    rs.getString("quote"),
                    rs.getString("author"),
                    rs.getInt("stars")
            );

        } catch(SQLException e) {
            throw new RuntimeException("error retrieving quote!");
        }
    }

    //Void b/c we don't need it to return anything
    public void addStar(long id) throws SQLException {
        Quote quote = getQuoteById(id);
        int starCount = quote.getStars();

        String query = "UPDATE reviewlister_db.quotes SET stars = ? WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, starCount + 1);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("unable to get stars");
        }
    }
}
