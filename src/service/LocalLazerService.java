package src.service;

import model.LocalLazer;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.LocalLazerRepository;

import java.util.List;

@Service
public class LocalLazerService {

    @Autowired
    private LocalLazerRepository repository;

    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    public LocalLazer salvarLocal(String nome, String descricao, String endereco, double latitude, double longitude) {
        LocalLazer local = new LocalLazer();
        local.setNome(nome);
        local.setDescricao(descricao);
        local.setEndereco(endereco);
        local.setGeom(geometryFactory.createPoint(new org.locationtech.jts.geom.Coordinate(longitude, latitude)));

        return repository.save(local);
    }

    public List<LocalLazer> buscarProximos(double latitude, double longitude, double raio) {
        Point userLocation = geometryFactory.createPoint(new org.locationtech.jts.geom.Coordinate(longitude, latitude));
        return repository.findNearby(userLocation, raio);
    }
}