package com.casadocodigo.casaDoCodigo.controllers;

import java.util.List;

import javax.validation.Valid;

import com.casadocodigo.casaDoCodigo.controllers.dto.BookDto;
import com.casadocodigo.casaDoCodigo.controllers.dto.DetailedBookDto;
import com.casadocodigo.casaDoCodigo.controllers.form.BookForm;
import com.casadocodigo.casaDoCodigo.services.BookServices;
import com.casadocodigo.casaDoCodigo.services.validators.CheckDuplicatedBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/book")
public class BookController {
    
    @Autowired
    private BookServices bookServices;
    @Autowired
    private CheckDuplicatedBook checkDuplicatedBook;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(checkDuplicatedBook);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> index() {
        return ResponseEntity.ok().body(bookServices.index());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedBookDto> detailedIndex(@PathVariable @Valid Long id) {
        return ResponseEntity.ok().body(bookServices.detailedIndex(id));
    }

    @PostMapping
    public ResponseEntity<DetailedBookDto> createBook(@RequestBody @Valid BookForm form, UriComponentsBuilder uriBuilder) {
        return ResponseEntity.ok().body(bookServices.createBook(form));
    }
}
