package com.example.mes.itemtable;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemTableRepository extends JpaRepository<ItemTable, Long> {

    Optional<ItemTable> findByItemId(String id);
}
