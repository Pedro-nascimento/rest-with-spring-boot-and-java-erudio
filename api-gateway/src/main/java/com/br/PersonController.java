package com.br;

import com.br.model.Person;
import com.br.services.PersonServices;
import com.br.util.MediaType;
import com.br.vo.v1.PersonVO;
import com.br.vo.v2.PersonVOV2;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {
    @Autowired
    private PersonServices service;


    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML} )
    public PersonVO findById(@PathVariable(value = "id")  Long id) throws Exception {

        return service.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public List<PersonVO> findAll() throws Exception{

        return service.findAll();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML} ,produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML} )
    public PersonVO create(@RequestBody PersonVO person) throws Exception{

        return service.create(person);
    }

/*    @PostMapping(value = "/v2",consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML} ,produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML} )
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person) throws Exception{

        return service.createV2(person);
    }*/

    @PutMapping(consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML} ,produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML} )
    public PersonVO update(@RequestBody PersonVO person) throws Exception{

        return service.update(person);
    }

/*    @PutMapping(value = "/v2",consumes = MediaType.APPLICATION_JSON ,produces = MediaType.APPLICATION_JSON )
    public PersonVOV2 update(@RequestBody PersonVOV2 person) throws Exception{

        return service.updateV2(person);
    }*/

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")  Long id) throws Exception{
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }


}
