package co.mlforex.forecast.repository;

import co.mlforex.forecast.config.DynamoDBConfig;
import co.mlforex.forecast.model.TransaccionInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EntrenaModeloInfoImp implements EntrenaModeloInfo {

    @Autowired
    DynamoDBConfig dynamoDBConfig;

    @Override
    public void guardarEstadoEntrenamiento(TransaccionInfo transaccionInfo) {

    }

    @Override
    public void actualizarEstadoEntrenamiento(TransaccionInfo transaccionInfo) {

    }

    @Override
    public List<TransaccionInfo> consultarEstadoEntrenamiento() {
        return null;
    }
}
