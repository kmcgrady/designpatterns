package com.ga.designpatterns.dao;

import com.ga.designpatterns.models.Action;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ActionDao extends CrudRepository<Action, Integer> { }