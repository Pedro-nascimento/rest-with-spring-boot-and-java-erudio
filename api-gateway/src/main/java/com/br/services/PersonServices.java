package com.br.services;

import com.br.PersonController;
import com.br.exceptions.RequiredObjectIsNullException;
import com.br.exceptions.ResourceNotFoundException;
import com.br.mapper.DozerMapper;
import com.br.mapper.custom.PersonMapper;
import com.br.model.Person;
import com.br.repositories.PersonRepository;
import com.br.vo.v1.PersonVO;
import com.br.vo.v2.PersonVOV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
@Service
public class PersonServices {


    private  Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll(){


        var persons =  DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons.stream().forEach(p -> {
            try {
                p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return persons;
    }

    public PersonVO findById(Long id) throws Exception {

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) throws Exception {

        if(person == null)
            throw new RequiredObjectIsNullException();

        logger.info("create one person!");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }


    public PersonVO update(PersonVO person) throws Exception {

        if(person == null)
            throw new RequiredObjectIsNullException();

        logger.info("update one person!");
        Person personObject =  repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("no records found for this ID"));

        personObject.setFirstName(person.getFirstName());
        personObject.setLastName(person.getLastName());
        personObject.setAddress(person.getAddress());
        personObject.setGender(person.getGender());

        var vo =  DozerMapper.parseObject(repository.save(personObject), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }


    public void delete(Long id){
        logger.info("deleting one person!");
        Person personObject =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no records found for this ID"));

        repository.delete(personObject);
    }


}
