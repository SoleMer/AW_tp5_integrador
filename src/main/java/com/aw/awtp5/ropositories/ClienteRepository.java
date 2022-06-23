package com.aw.awtp5.ropositories;

import com.aw.awtp5.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ClienteRepository")
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
