package sysaid.hw.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import sysaid.hw.model.Title;



public interface TitleSysRepository extends MongoRepository<Title, Integer>{

}
