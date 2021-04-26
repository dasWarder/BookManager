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

@Service
public class BookDetailsParserService implements DetailsParserService {
    private final String SEARCH_PAGE_URL = "https://openlibrary.org/search?q=%s&mode=everything";
    private final String INFO_MAIN_URL = "https://openlibrary.org";

    private BookService bookService;

    @Autowired
    public BookDetailsParserService(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public Details findInfoOnPage(Book book) {
        Assert.notNull(book, "book must not be null");
        String searchingParam = buildSearchingParam(book);
        String formattedUrl = String.format(SEARCH_PAGE_URL, searchingParam);

        String detailsLink = parseDocumentToGetDetailsLink(formattedUrl);
        Details details = getDetails(detailsLink);

        return details;
    }

    //BUILD A STRING TO ADD AS PARAM FOR FORMATTER OF INFO_URL STRING
    private String buildSearchingParam(Book book) {
        String name = book.getName();
        String author = book.getAuthor();

        name = name == null || name.equals("")? "" : getSplittedString(name);
        author = author == null || author.equals("")? "" : getSplittedString(author);

        return name + author;
    }


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

    private Details getDetails(String link) {
        Details details = new Details();

        try {
            Document document = Jsoup.connect(link).get();

            Elements bookDescriptionClass = document.getElementsByClass("workDetails");

            String description = getDescription(bookDescriptionClass);
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

    private String getDescription(Elements bookDescriptionClass) {
        String description = bookDescriptionClass
                .first()
                .getElementsByClass("editionAbout")
                .first()
                .getElementsByClass("book-description")
                .first()
                .text();

        description = sliceTheDescription(
                sliceTheDescription(description,  "Read less"),
                "Read more");

        return description;
    }

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
