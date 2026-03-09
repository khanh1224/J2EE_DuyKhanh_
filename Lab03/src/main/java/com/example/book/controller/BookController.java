package com.example.book.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.book.model.Book;

@Controller
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();
    private int nextId = 1;

    public BookController() {
        books.add(new Book(nextId++, "Java cơ bản", "Nguyễn Văn A", 50000));
        books.add(new Book(nextId++, "Spring Boot", "Trần Văn B", 75000));
    }

    // Danh sách sách
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", books);
        return "books";
    }

    // Form thêm sách
    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    // Xử lý thêm
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        book.setId(nextId++);
        books.add(book);
        return "redirect:/books";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable int id, Model model) {
        for (Book b : books) {
            if (b.getId() == id) {
                model.addAttribute("book", b);
                break;
            }
        }
        return "edit-book";
    }

    // Xử lý sửa
    @PostMapping("/edit")
    public String editBook(@ModelAttribute Book book) {
        for (Book b : books) {
            if (b.getId() == book.getId()) {
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
                b.setPrice(book.getPrice());
                break;
            }
        }
        return "redirect:/books";
    }

    // Xóa sách
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        books.removeIf(b -> b.getId() == id);
        return "redirect:/books";
    }
}
