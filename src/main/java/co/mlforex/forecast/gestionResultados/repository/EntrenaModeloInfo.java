package co.mlforex.forecast.gestionResultados.repository;

import co.mlforex.forecast.gestionResultados.model.TransaccionInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface EntrenaModeloInfo extends CrudRepository<TransaccionInfo, String> {


}
