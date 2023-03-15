package co.mlforex.forecast.service;

import co.mlforex.forecast.model.TransaccionInfo;

import java.util.List;

public interface GestorResultados {

    void entrenarModelo(TransaccionInfo transaccionInfo);

    void actualizarModeloInfo(TransaccionInfo transaccionInfo);
    List<TransaccionInfo> consultarModeloInfo();

}
