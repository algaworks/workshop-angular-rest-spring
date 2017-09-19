package com.algaworks.patrimonio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.patrimonio.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
