package co.mlforex.forecast.gestionResultados.controller;

import co.mlforex.forecast.gestionResultados.logic.InvocadorModelo;
import co.mlforex.forecast.gestionResultados.model.TransaccionInfo;
import co.mlforex.forecast.gestionResultados.service.GestorResultados;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GestorResultadosController {

    Logger logger = LoggerFactory.getLogger(GestorResultadosController.class);

    @Autowired
    GestorResultados gestorResultados;

    @PostMapping("/llamarModelo")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> entrnarModelo(@RequestBody TransaccionInfo transaccionInfo) {
        try {
            gestorResultados.entrenarModelo(transaccionInfo);
            return new ResponseEntity<>("Request sent", HttpStatus.OK);

        } catch (final Exception e) {
            logger.error("Error en GestorResultadosController:entrnarModelo ", e.getMessage());
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/consultarResultados/{idUsuario}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<TransaccionInfo>> consultarResultadosModelos(@PathVariable String idUsuario) {
        try {
            final List<TransaccionInfo> listaResultados = gestorResultados.getResultados(idUsuario);
            return new ResponseEntity<>(listaResultados, HttpStatus.OK);
        } catch (final Exception e) {
            logger.error("Error en GestorResultadosController:consultarResultadosModelos" + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/consultarResultado/{idTransaccion}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<TransaccionInfo>> consultarResultadosPorTransaccion(@PathVariable String idTransaccion) {
        try {
            final List<TransaccionInfo> resultado = gestorResultados.getResultado(idTransaccion);
            if (resultado != null) {
                return new ResponseEntity<>(resultado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
        } catch (final Exception e) {
            logger.error("Error en GestorResultadosController:consultarResultadosPorTransaccion" + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
