package com.apidemo.service;

import com.apidemo.entity.Registration;
import com.apidemo.repository.RegistrationRepository;

import com.apidemo.exceptions.ResourceNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;


    @Autowired
    private RegistrationMapper registrationMapper;

    public RegistrationService(RegistrationRepository registrationRepository, RegistrationMapper registrationMapper, ModelMapper modelMapper, RegistrationMapper registrationMapper1) {
        this.registrationRepository = registrationRepository;

        this.registrationMapper = registrationMapper;

    }

    public RegistrationDto createRegistration(
            RegistrationDto registrationDto
    ) {
        Registration registration = convertToEntity(registrationDto);
        Registration savedRegistration = registrationRepository.save(registration);
        return convertToDto(savedRegistration);
    }

    Registration convertToEntity(RegistrationDto registrationDto) {
        ;
        // Registration reg = modelMapper.map(registrationDto, Registration.class);
        Registration registration = registrationMapper.userDTOToUser(registrationDto);
//Registration registration = new Registration();
//    registration.setName(registrationDto.getName());
//    registration.setEmailId(registrationDto.getEmailId());
//    registration.setMobile(registrationDto.getMobile());
        return registration;


    }

    RegistrationDto convertToDto(Registration registration) {
        //  copy registration to dto
        //RegistrationDto dto = modelMapper.map(registration, RegistrationDto.class);
        RegistrationDto registrationDto = registrationMapper.userToUserDTO(registration);
//        RegistrationDto registrationDto = new RegistrationDto();
//        registrationDto.setId(registration.getId());
//        registrationDto.setName(registration.getName());
//        registrationDto.setEmailId(registration.getEmailId());
//        registrationDto.setMobile(registration.getMobile());
        //      return registrationDto;
        return registrationDto;
    }


    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    public void updateRegistration(long id, RegistrationDto registrationDto) {
        Optional<Registration> opReg = registrationRepository.findById(id);
        if (opReg.isPresent()) {
            Registration reg = opReg.get();
            reg.setName(registrationDto.getName());
            reg.setEmailId(registrationDto.getEmailId());
            reg.setMobile(registrationDto.getMobile());
            registrationRepository.save(reg);
        }

    }


    public List<RegistrationDto> getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equals("asc") ? Sort.by(Sort.Order.asc(sortBy)) :Sort.by(Sort.Order.desc(sortBy));
        Pageable page = PageRequest.of(pageNo,pageSize, sort);
        Page<Registration> all = registrationRepository.findAll(page);
        List<Registration> registrations = all.getContent();
        System.out.println(page.getPageNumber());
        System.out.println(page.getPageSize());
        System.out.println(all.getTotalPages());
        System.out.println(all.isLast());
        System.out.println(all.isFirst());


        return registrations.stream().map(registrationMapper::userToUserDTO).collect(Collectors.toList());
    }





        public Registration getRegistrationById(Long id) {
            return registrationRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound("Could not find registration"));
        }


    }




