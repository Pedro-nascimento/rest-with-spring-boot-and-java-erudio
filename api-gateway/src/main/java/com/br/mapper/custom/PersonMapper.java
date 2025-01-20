package com.br.mapper.custom;

import com.br.model.Person;
import com.br.vo.v1.PersonVO;
import com.br.vo.v2.PersonVOV2;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertyEntityToVo(Person person){
        PersonVOV2 vo = new PersonVOV2();

        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        vo.setBirthDay(new Date());

        return vo;

    }

    public Person convertyVoToEntity(PersonVOV2 vo){
        Person person = new Person();

        person.setId(vo.getId());
        person.setFirstName(vo.getFirstName());
        person.setLastName(vo.getLastName());
        person.setAddress(vo.getAddress());
        person.setGender(vo.getGender());
        //person.setBirthDay(new Date());

        return person;

    }
}
