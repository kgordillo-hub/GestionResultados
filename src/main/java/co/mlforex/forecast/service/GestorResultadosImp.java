package co.mlforex.forecast.service;

import co.mlforex.forecast.model.TransaccionInfo;
import co.mlforex.forecast.repository.EntrenaModeloInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GestorResultadosImp implements GestorResultados{

    @Autowired
    EntrenaModeloInfo entrenaModeloInfo;


    @Override
    public void entrenarModelo(TransaccionInfo transaccionInfo) {
        //persistir info
        transaccionInfo.setEnEntrenamiento(Boolean.TRUE);
        entrenaModeloInfo.guardarEstadoEntrenamiento(transaccionInfo);


        //Hacer llamado a endpoint
        final String endPoint = transaccionInfo.getIP_API() + ":" + transaccionInfo.getNumeroPuerto()+"/training";

    }

    @Override
    public void actualizarModeloInfo(TransaccionInfo transaccionInfo) {

        entrenaModeloInfo.actualizarEstadoEntrenamiento(transaccionInfo);

    }

    @Override
    public List<TransaccionInfo> consultarModeloInfo() {
        return entrenaModeloInfo.consultarEstadoEntrenamiento();
    }

}
