package com.babichev.bookmanager.service;


import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Details;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.babichev.bookmanager.util.StringConverter.getSplittedString;
import static com.babichev.bookmanager.util.StringConverter.parseDescription;

@Service
public class BookInfoParserService implements InfoParserService {
    private final String SEARCH_PAGE_URL = "https://openlibrary.org/search?q=%s&mode=everything";
    private final String INFO_MAIN_URL = "https://openlibrary.org";

    private BookService bookService;

    @Autowired
    public BookInfoParserService(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public Details findInfoOnPage(Book book) {

        String readyToParse = buildParsingString(book);

        String formattedUrl = String.format(SEARCH_PAGE_URL, readyToParse);

        Details details = new Details();

        Document document;

        try {
            document = Jsoup.connect(formattedUrl).get();

            Element contentBody = document.getElementById("searchResults");

            Elements searchResultItem = contentBody.getElementsByClass("searchResultItem");
            Element firstBookFromSearch = searchResultItem.first();

            String linkToDetailPage = firstBookFromSearch.getElementsByClass("booktitle")
                    .first()
                    .select("a")
                    .first()
                    .attr("href");

            //CREATE DETAIL INFO LINK
            String linkForDetailInfo = INFO_MAIN_URL + linkToDetailPage;

            details = getDetails(linkForDetailInfo);

        } catch (IOException e) {
            e.getStackTrace();
        } catch (NullPointerException e) {
            details.setDescription("This edition doesn't have a description yet. Can you add one?");
            return details;
        } finally {
            return details;
        }
    }

    private Details getDetails(String link) {
        Details details = new Details();
        Document document;
        try {
            document = Jsoup.connect(link).get();

            Elements bookDescriptionClass = document.getElementsByClass("workDetails");

            //GET DESCRIPTION OF THE BOOK
            String description = bookDescriptionClass
                    .first()
                    .getElementsByClass("editionAbout")
                    .first()
                    .getElementsByClass("book-description")
                    .first()
                    .text();
            description = parseDescription(
                    parseDescription(description,  "Read less"),
                    "Read more");

            //GET IMAGE LINK
            String imgLink = bookDescriptionClass
                    .first()
                    .getElementsByClass("editionCover")
                    .first()
                    .select("a")
                    .first()
                    .attr("href");

            details.setDescription(description);
            details.setImage(imgLink);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return details;
    }

    //BUILD A STRING TO ADD AS PARAM FOR FORMATTER FOR INFO_URL STRING
    private String buildParsingString(Book book) {
        final String resultString;

        String name = book.getName();
        String author = book.getAuthor();
//        Integer year = book.getYear();
//        String yearStr = null;

        name = name == null || name.equals("")? "" : getSplittedString(name);
        author = author == null || author.equals("")? "" : getSplittedString(author);
//        yearStr = year != null? String.valueOf(year) : "";

        resultString = name + author;// + yearStr;

        return resultString;
    }



}
