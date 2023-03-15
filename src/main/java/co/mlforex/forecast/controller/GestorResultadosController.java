package co.mlforex.forecast.controller;

import co.mlforex.forecast.model.TransaccionInfo;
import co.mlforex.forecast.service.GestorResultados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GestorResultadosController {

    @Autowired
    GestorResultados gestorResultados;

    @PostMapping("/entrenarModelo")
    public ResponseEntity<String> entrnarModelo(TransaccionInfo transaccionInfo){
        gestorResultados.entrenarModelo(transaccionInfo);
        return new ResponseEntity<>("Request sent", HttpStatus.OK);
    }

    @PostMapping("/actualizarEstadoModelo")
    public ResponseEntity<String> actualizarEstadoModelo(TransaccionInfo transaccionInfo){
        gestorResultados.actualizarModeloInfo(transaccionInfo);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }


    @PostMapping("/consultarResultados")
    public ResponseEntity<List<TransaccionInfo>> consultarResultadosModelos(){
        final List<TransaccionInfo> listaResultados = gestorResultados.consultarModeloInfo();
        return new ResponseEntity<>(listaResultados, HttpStatus.OK);
    }
}
