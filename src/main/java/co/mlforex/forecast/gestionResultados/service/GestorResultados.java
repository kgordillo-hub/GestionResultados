package co.mlforex.forecast.gestionResultados.service;

import co.mlforex.forecast.gestionResultados.model.TransaccionInfo;

import java.util.List;

public interface GestorResultados {

    void entrenarModelo(TransaccionInfo transaccionInfo);

    List<TransaccionInfo> getResultados(String idUsuario);

    List<TransaccionInfo> getResultado(String idTransaccion);
}
