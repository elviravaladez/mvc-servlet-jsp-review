package com.codeup.reviewlister.controllers;

import com.codeup.reviewlister.Config;
import com.codeup.reviewlister.Quote;
import com.codeup.reviewlister.QuotesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="StarsServlet", urlPatterns = "/stars")
public class StarsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Config config = new Config();
        // Instantiate a new QuotesDao object
        QuotesDao quotesDao = new QuotesDao(config);
        long quoteId = Long.parseLong(request.getParameter("quote-id"));

        try {
            Quote currentQuote = quotesDao.getQuoteById(quoteId);
            quotesDao.addStar(quoteId);
            response.sendRedirect("/quotes");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
