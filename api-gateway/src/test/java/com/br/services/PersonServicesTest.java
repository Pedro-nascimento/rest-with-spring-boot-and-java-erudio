package com.br.services;

import com.br.exceptions.RequiredObjectIsNullException;
import com.br.model.Person;
import com.br.repositories.PersonRepository;
import com.br.unittests.mapper.mocks.MockPerson;
import com.br.vo.v1.PersonVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() throws Exception {
        List<Person> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        var people = service.findAll();

        assertNotNull(people);
        assertEquals(14,people.size());

        var personOne = people.get(1);

        assertEquals("Addres Test1",personOne.getAddress());
        assertEquals("First Name Test1",personOne.getFirstName());
        assertEquals("Last Name Test1",personOne.getLastName());
        assertEquals("Female",personOne.getGender());

        var personFour = people.get(4);

        assertEquals("Addres Test4",personFour.getAddress());
        assertEquals("First Name Test4",personFour.getFirstName());
        assertEquals("Last Name Test4",personFour.getLastName());
        assertEquals("Male",personFour.getGender());


        var personSeven = people.get(7);

        assertEquals("Addres Test7",personSeven.getAddress());
        assertEquals("First Name Test7",personSeven.getFirstName());
        assertEquals("Last Name Test7",personSeven.getLastName());
        assertEquals("Female",personSeven.getGender());


    }

    @Test
    void findById() throws Exception {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertEquals("Addres Test1",result.getAddress());
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Female",result.getGender());
    }

    @Test
    void create() throws Exception {
        Person entity = input.mockEntity(1);
        Person persisted = entity;
        entity.setId(1L);

        PersonVO vo  = input.mockVO(1);
        vo.setKey(1L);

        when(repository.save(entity)).thenReturn(persisted);
        var result = service.create(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertEquals("Addres Test1",result.getAddress());
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Female",result.getGender());
    }

    @Test
    void createNull() throws Exception {
     Exception expection = assertThrows(RequiredObjectIsNullException.class, () ->{
         service.create(null);
     });

     String expectedMsg = "não é possível consistir um objeto nulo.";
     String actualdMsg = expection.getMessage();

     assertEquals(expectedMsg,actualdMsg);
    }

    @Test
    void update() throws Exception {
        Person entity = input.mockEntity(1);
        Person persisted = entity;
        entity.setId(1L);

        PersonVO vo  = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertEquals("Addres Test1",result.getAddress());
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Female",result.getGender());
    }

    void updateNull() throws Exception {
        Exception expection = assertThrows(RequiredObjectIsNullException.class, () ->{
            service.update(null);
        });

        String expectedMsg = "não é possível consistir um objeto nulo.";
        String actualdMsg = expection.getMessage();

        assertEquals(expectedMsg,actualdMsg);
    }

    @Test
    void delete() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.delete(1L);
    }
}