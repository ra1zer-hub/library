package ru.ra1zer.elibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ra1zer.elibrary.entities.Author;
import ru.ra1zer.elibrary.entities.Book;
import ru.ra1zer.elibrary.entities.Genre;
import ru.ra1zer.elibrary.entities.Publisher;
import ru.ra1zer.elibrary.repositories.specifications.BooksSpecs;
import ru.ra1zer.elibrary.services.AuthorsService;
import ru.ra1zer.elibrary.services.BooksService;
import ru.ra1zer.elibrary.services.GenresService;
import ru.ra1zer.elibrary.services.PublishersService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping
public class BooksController {
    private GenresService genresService;
    private BooksService booksService;
    private AuthorsService authorsService;
    private PublishersService publishersService;
    private String uploadContentPath = "D:/Java/Projects/library/src/main/uploads/static/content/pdf/";

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public void setGenresService(GenresService genresService) {
        this.genresService = genresService;
    }

    @Autowired
    public void setBooksService(BooksService booksService) {
        this.booksService = booksService;
    }

    @Autowired
    public void setAuthorsService(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @Autowired
    public void setPublishersService(PublishersService publishersService) {
        this.publishersService = publishersService;
    }

    @GetMapping
    public String showMainPage(Model model,
                               @RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "word", required = false) String word) {

        if (page == null)
            page = 1;

        Specification<Book> specification = Specification.where(null);
        if (word != null)
            specification = specification.and(BooksSpecs.titleContains(word));

        List<Genre> genres = genresService.getAllGenres();
        List<Book> books = booksService.getBooksWithPagingAndFiltering(specification, PageRequest.of(page - 1, 10)).getContent();
        double numberOfPages = Math.ceil(booksService.getAllBooks().size() / 10.0);
        model.addAttribute("genres", genres);
        model.addAttribute("books", books);
        model.addAttribute("number_of_pages", numberOfPages);
        model.addAttribute("word", word);
        model.addAttribute("noPage", false);
        return "main-page";
    }

    @GetMapping("/genre/{id}")
    public String searchBooksByGenre(Model model, @PathVariable Long id) {
        List<Genre> genres = genresService.getAllGenres();
        List<Book> books = booksService.searchBooksByGenre(id);
        model.addAttribute("genres", genres);
        model.addAttribute("books", books);
        model.addAttribute("noPage", true);
        return "main-page";
    }

    @GetMapping("/newbook")
    public String addNewBook(Model model) {
        Book book = new Book();
        getValuesFromBDAndSendToForm(model, book);
        return "add-new-book-page";
    }

    @PostMapping("/addnewbook")
    public String addBook(@ModelAttribute(value = "book") Book book, @RequestParam("cover") MultipartFile cover, @RequestParam("PDF") MultipartFile PDF) throws IOException {
        createOrUpdateBook(book, cover, PDF, true);
        return "redirect:/";
    }

    @GetMapping("/book/edit/{id}")
    public String editOrReadBook(Model model, @PathVariable(value = "id") Long id) {
        Book book = booksService.getById(id);
        getValuesFromBDAndSendToForm(model, book);
        return "book-edit-page";
    }

    @PostMapping("/editbook")
    public String editbook(@ModelAttribute(value = "book") Book book, @RequestParam MultipartFile cover, @RequestParam MultipartFile PDF) throws IOException {
        createOrUpdateBook(book, cover, PDF, false);
        return "redirect:/";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteById(@PathVariable(value = "id") Long id) {
        booksService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(value = "/book/read/{id}")
    public void readBook(HttpServletResponse response, @PathVariable(value = "id") Long id) throws IOException {
        response.setContentType("application/pdf");
        InputStream inputStream = new FileInputStream(new File(uploadContentPath + booksService.getById(id).getContent()));
        int nRead;
        while ((nRead = inputStream.read()) != -1) {
            response.getWriter().write(nRead);
        }
    }

    private void getValuesFromBDAndSendToForm(Model model, Book book) {
        List<Genre> genres = genresService.getAllGenres();
        List<Author> authors = authorsService.getAllAuthors();
        List<Publisher> publishers = publishersService.getAllPublishers();
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);
    }

    private void createOrUpdateBook(Book book, MultipartFile cover, MultipartFile PDF, boolean newBook) throws IOException {
        if (!cover.isEmpty()) {
            File uploadCoversDir = new File(uploadPath);
            if (!uploadCoversDir.exists())
                uploadCoversDir.mkdir();

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + cover.getOriginalFilename();
            File coverFile = new File(uploadPath + "covers/" + resultFilename);
            cover.transferTo(coverFile);
            book.setImage(resultFilename);
        } else if (newBook)
            book.setImage("No_Image.jpg");
        else
            book.setImage(booksService.getById(book.getId()).getImage());

        if (!PDF.isEmpty()) {
            File uploadContentsDir = new File(uploadContentPath);
            if (!uploadContentsDir.exists())
                uploadContentsDir.mkdir();

            File contentFile = new File(uploadContentPath + PDF.getOriginalFilename());
            PDF.transferTo(contentFile);
            book.setContent(PDF.getOriginalFilename());
        } else if (!booksService.getById(book.getId()).getContent().isEmpty())
            book.setContent(booksService.getById(book.getId()).getContent());
        else
            book.setContent("No-pdf-image.jpg");

        booksService.saveOrUpdate(book);
    }
}