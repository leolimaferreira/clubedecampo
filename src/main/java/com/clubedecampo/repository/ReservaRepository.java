package com.clubedecampo.repository;

import com.clubedecampo.entity.Area;
import com.clubedecampo.entity.Associado;
import com.clubedecampo.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, UUID> {
    @Query("""
    SELECT r FROM Reserva r
    WHERE r.area = :area
    AND r.dataReserva = :data
    AND (
        :horaInicio < r.horaFim AND :horaFim > r.horaInicio
    )
""")
    List<Reserva> findReservasConflitantes(@Param("area") Area area,
                                           @Param("data") LocalDate data,
                                           @Param("horaInicio") LocalTime horaInicio,
                                           @Param("horaFim") LocalTime horaFim);

    boolean existsByAssociado(Associado associado);

    boolean existsByArea(Area area);
}
