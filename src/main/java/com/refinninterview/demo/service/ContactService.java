package com.refinninterview.demo.service;

import com.refinninterview.demo.domain.Contact;
import com.refinninterview.demo.dto.ContactDTO;
import com.refinninterview.demo.dto.ContactRequestDTO;
import com.refinninterview.demo.exception.ContactNotFoundException;
import com.refinninterview.demo.repository.ContactRepository;
import com.refinninterview.demo.web.mapper.ContactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ContactDTO addNewContact(ContactRequestDTO contactRequestDTO) {
        contactRequestDTO.setStatus("NEW");
        Contact newContact = contactRepository.save(contactMapper.requestDtoToContact(contactRequestDTO));
        Instant now = Instant.now();
        newContact.setLastModifiedAt(now);
        newContact.setCreatedAt(now);
        return contactMapper.contactToDto(newContact);
    }

    public ContactDTO updateContact(Long id, ContactRequestDTO contactRequestDTO) {
         contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(String.format("Contact id %d not found", id)));
        Contact contactToUpdate = contactMapper.requestDtoToContact(contactRequestDTO);
        contactToUpdate.setId(id);
        Contact updatedContact = contactRepository.save(contactToUpdate);
        return contactMapper.contactToDto(updatedContact);
    }

    public void deleteContact(Long id) {
        Contact contactToDelete =
                contactRepository.findById(id)
                        .orElseThrow(() -> new ContactNotFoundException(String.format("Contact id %d not found", id)));

        contactRepository.delete(contactToDelete);
    }

    public ContactDTO findContactById(Long id) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(String.format("Contact id %d not found", id)));

        return contactMapper.contactToDto(existingContact);
    }

    public Page<ContactDTO> findContacts(Integer page,
                                         Integer size) {
        List<Contact> contacts = contactRepository.findAll();
        List<ContactDTO> mappedDTOs = contacts.stream().map(contactMapper::contactToDto)
                .collect(Collectors.toList());
        long totalContacts = mappedDTOs.size();
        Pageable pageable = PageRequest.of(page - 1, size);
        int start = (int) pageable.getOffset();
        int end = (int) Math.min((start + pageable.getPageSize()), totalContacts);

        return new PageImpl<>(mappedDTOs.subList(start, end), pageable, totalContacts);
    }
}
