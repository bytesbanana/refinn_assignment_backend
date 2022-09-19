package com.refinninterview.demo.web.mapper;

import com.refinninterview.demo.domain.Asset;
import com.refinninterview.demo.domain.Contact;
import com.refinninterview.demo.dto.ContactDTO;
import com.refinninterview.demo.dto.ContactRequestDTO;
import com.refinninterview.demo.exception.AssetNotFoundException;
import com.refinninterview.demo.repository.AssetRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ContactMapper {

    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    protected AssetMapper assetMapper;



    @Mapping(target = "id", ignore = true)
    @Mapping(target = "asset", expression = "java(findAssetById(contactRequestDTO.getAssetId()))")
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract Contact requestDtoToContact(ContactRequestDTO contactRequestDTO);


    @Mapping(target = "asset", expression = "java(assetMapper.assetToDto(contact.getAsset()))")
    public abstract ContactDTO contactToDto(Contact contact);


    protected Asset findAssetById(Long assetId) {
        return assetRepository.findById(assetId)
                .orElseThrow(AssetNotFoundException::new);
    }
}
