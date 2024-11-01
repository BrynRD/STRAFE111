package com.strafe.repository;

import com.strafe.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor; // Importar este paquete
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Producto, Long>, JpaSpecificationExecutor<Producto> {
    // Puedes agregar métodos de consulta personalizados aquí si lo deseas
}
