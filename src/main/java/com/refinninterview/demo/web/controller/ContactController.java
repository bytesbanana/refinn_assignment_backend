package com.refinninterview.demo.web.controller;

import com.refinninterview.demo.dto.ContactDTO;
import com.refinninterview.demo.dto.ContactRequestDTO;
import com.refinninterview.demo.dto.PaginateContactDTO;
import com.refinninterview.demo.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;


    @PostMapping
    ResponseEntity<ContactDTO> createContact(@Valid @RequestBody ContactRequestDTO contactRequestDTO) {
        ContactDTO result = contactService.addNewContact(contactRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    ResponseEntity<ContactDTO> updateContact(@PathVariable Long id,
                                             @Valid @RequestBody ContactRequestDTO contactRequestDTO) {
        ContactDTO result = contactService.updateContact(id, contactRequestDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    ResponseEntity<ContactDTO> findContactById(@PathVariable Long id) {
        ContactDTO result = contactService.findContactById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("")
    ResponseEntity<PaginateContactDTO> findContacts(@Param("page") Integer page,
                                                    @Param("size") Integer size) {
        if (page == null) page = 1;
        if (size == null) size = 25;

        Page<ContactDTO> pageContactDto = contactService.findContacts(page, size);
        PaginateContactDTO result = PaginateContactDTO.builder()
                .page(pageContactDto.getNumber() + 1)
                .size(pageContactDto.getSize())
                .total((int) pageContactDto.getTotalElements())
                .totalPage(pageContactDto.getTotalPages())
                .contacts(pageContactDto.getContent())
                .build();

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);

        return ResponseEntity.noContent().build();
    }


}
