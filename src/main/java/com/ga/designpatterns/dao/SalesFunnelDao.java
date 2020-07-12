package com.ga.designpatterns.dao;

import com.ga.designpatterns.models.SalesFunnel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SalesFunnelDao extends CrudRepository<SalesFunnel, Integer> { }
