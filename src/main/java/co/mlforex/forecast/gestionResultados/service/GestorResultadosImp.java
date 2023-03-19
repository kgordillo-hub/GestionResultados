package co.mlforex.forecast.gestionResultados.service;

import co.mlforex.forecast.gestionResultados.logic.InvocadorModelo;
import co.mlforex.forecast.gestionResultados.model.TransaccionInfo;
import co.mlforex.forecast.gestionResultados.repository.EntrenaModeloInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class GestorResultadosImp implements GestorResultados{

    @Autowired
    EntrenaModeloInfo entrenaModeloInfo;

    @Value("${aws.sns.topic.resultadosOut.arn}")
    String topicArnResultadosOut;


    @Override
    public void entrenarModelo(TransaccionInfo transaccionInfo) {
        new InvocadorModelo(transaccionInfo, entrenaModeloInfo, topicArnResultadosOut).start();
    }

    @Override
    public List<TransaccionInfo> getResultados(String idUsuario) {
        final List<TransaccionInfo> tiList = new ArrayList<>();
        final Iterable<TransaccionInfo> iterable = entrenaModeloInfo.findAll();
        if(iterable!=null){
            final Iterator<TransaccionInfo> it = iterable.iterator();
            while(it.hasNext()){
                TransaccionInfo ti = it.next();
                if(ti.getIdUsuario().equalsIgnoreCase(idUsuario)){
                    tiList.add(ti);
                }
            }
        }
        return tiList;
    }

    @Override
    public List<TransaccionInfo> getResultado(String idTransaccion) {
        final List<TransaccionInfo> tiList = new ArrayList<>();
        final Iterable<TransaccionInfo> iterable = entrenaModeloInfo.findAll();
        if(iterable!=null){
            final Iterator<TransaccionInfo> it = iterable.iterator();
            while(it.hasNext()){
                TransaccionInfo ti = it.next();
                if(ti.getIdTransaccion().equalsIgnoreCase(idTransaccion)){
                    tiList.add(ti);
                }
            }
        }
        return tiList;
    }


}
