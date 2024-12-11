package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public class LocalLazerRepository extends JpaRepository<LocalLazer, Long> {

    @Query("SELECT l FROM LocalLazer l WHERE ST_Distance(l.geom, :userLocation) <= :radius")
    List<LocalLazer> findNearby(@Param("userLocation") org.locationtech.jts.geom.Point userLocation, @Param("radius") double radius);
}
