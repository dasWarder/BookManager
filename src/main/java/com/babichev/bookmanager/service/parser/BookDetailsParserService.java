package com.babichev.bookmanager.service.parser;


import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Details;
import com.babichev.bookmanager.service.book.BookService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;

import static com.babichev.bookmanager.util.StringConverter.*;


/**
 * The service that implements DetailsParserService
 */
@Service
public class BookDetailsParserService implements DetailsParserService {

    /**
     * The field with an URL string that is using for searching
     */
    private final String SEARCH_PAGE_URL = "https://openlibrary.org/search?q=%s&mode=everything";

    /**
     * The field with an URL of the main page of a website that is using for parsing
     */
    private final String INFO_MAIN_URL = "https://openlibrary.org";

    /**
     * The filed with a BookService bean.
     * @see BookService
     */
    private BookService bookService;

    @Autowired
    public BookDetailsParserService(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * The main method that encapsulate the full logic of finding details about a book
     * @param book the book object for which the details must be founded
     * @return the details object for the @param book
     */
    @Override
    public Details findInfoOnPage(Book book) {
        Assert.notNull(book, "book must not be null");

        /**
         * @see BookDetailsParserService#buildSearchingParam(Book)
         */
        String searchingParam = buildSearchingParam(book);
        String formattedUrl = String.format(SEARCH_PAGE_URL, searchingParam);

        /**
         * @see BookDetailsParserService#parseDocumentToGetDetailsLink(String)
         */
        String detailsLink = parseDocumentToGetDetailsLink(formattedUrl);

        /**
         * @see BookDetailsParserService#getDetails(String)
         */
        Details details = getDetails(detailsLink);

        return details;
    }

    /**
     * A method that build a string based on the @param book
     * @param book the book which one details will be searching
     * @return the params for searching the book
     */
    private String buildSearchingParam(Book book) {
        String name = book.getName();
        String author = book.getAuthor();
        /**
         * @see com.babichev.bookmanager.util.StringConverter#getSplittedString(String)
         */
        name = name == null || name.equals("")? "" : getSplittedString(name);
        author = author == null || author.equals("")? "" : getSplittedString(author);

        return name + author;
    }

    /**
     * The method that parse a web page on @param formattedUrl to get a link with the details information
     * @param formattedUrl the URL on which the searching will be implemented
     * @return the link to the details book information page
     */
    private String parseDocumentToGetDetailsLink(String formattedUrl) {
        String detailsLink = null;

        try {
            Document document = Jsoup.connect(formattedUrl).get();

            Element contentBody = document.getElementById("searchResults");
            Elements searchResultItem = contentBody.getElementsByClass("searchResultItem");
            Element firstBookFromSearch = searchResultItem.first();

            String linkToDetailsPage = firstBookFromSearch.getElementsByClass("booktitle")
                    .first()
                    .select("a")
                    .first()
                    .attr("href");

            //CREATE DETAIL INFO LINK
            detailsLink = INFO_MAIN_URL + linkToDetailsPage;

        } catch (IOException e) {
            e.getStackTrace();
        } catch (NullPointerException e) {
            e.getStackTrace();
        }

        return detailsLink;
    }

    /**
     * The method to parse the URL and get the book details information.
     * In case the book details is not possible to identified and the exception occurred return the empty details object
     * @param link the URL to the page with the details book information
     * @return the details object for the book
     */
    private Details getDetails(String link) {
        Details details = new Details();

        try {
            Document document = Jsoup.connect(link).get();

            Elements bookDescriptionClass = document.getElementsByClass("workDetails");

            /**
             * @see BookDetailsParserService#getDescription(Elements)
             */
            String description = getDescription(bookDescriptionClass);

            /**
             * @see BookDetailsParserService#getImageLink(Elements)
             */
            String imgLink = getImageLink(bookDescriptionClass);

            //SET DETAILS FIELDS
            details.setDescription(description);
            details.setImage(imgLink);

        } catch (IllegalArgumentException e) {
            details.setDescription("This edition doesn't have a description yet. Can you add one?");
            return details;
        } catch (IOException e) {
            e.getStackTrace();
        }

        return details;
    }

    /**
     * The method to get the description field for the book details information
     * @param bookDescriptionClass the group of elements with details information
     * @return the string with the description for the book
     */
    private String getDescription(Elements bookDescriptionClass) {
        String description = bookDescriptionClass
                .first()
                .getElementsByClass("editionAbout")
                .first()
                .getElementsByClass("book-description")
                .first()
                .text();
        /**
         * @see com.babichev.bookmanager.util.StringConverter#sliceTheDescription(String, String)
         */
        description = sliceTheDescription(
                sliceTheDescription(description,  "Read less"),
                "Read more");

        return description;
    }

    /**
     * The method to get the imageLink field for the book details information
     * @param bookDescriptionClass the group of elements with the book cover link
     * @return the link to the book cover image
     */
    private String getImageLink(Elements bookDescriptionClass) {
        String imgLink = bookDescriptionClass
                .first()
                .getElementsByClass("editionCover")
                .first()
                .select("a")
                .first()
                .attr("href");

        return imgLink;
    }

}
