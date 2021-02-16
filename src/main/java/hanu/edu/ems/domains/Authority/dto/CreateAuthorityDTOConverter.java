package hanu.edu.ems.domains.Authority.dto;

import hanu.edu.ems.domains.Authority.entity.Authority;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateAuthorityDTOConverter implements Converter<CreateAuthorityDTO, Authority> {

    private final ModelMapper modelMapper;

    @Autowired
    public CreateAuthorityDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Authority convert(CreateAuthorityDTO createAuthorityDTO) {
        return modelMapper.map(createAuthorityDTO, Authority.class);
    }
}
