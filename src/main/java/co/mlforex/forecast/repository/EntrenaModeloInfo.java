package co.mlforex.forecast.repository;

import co.mlforex.forecast.model.TransaccionInfo;

import java.util.List;

public interface EntrenaModeloInfo {

    void guardarEstadoEntrenamiento(TransaccionInfo transaccionInfo);

    void actualizarEstadoEntrenamiento(TransaccionInfo transaccionInfo);

    List<TransaccionInfo> consultarEstadoEntrenamiento();
}
