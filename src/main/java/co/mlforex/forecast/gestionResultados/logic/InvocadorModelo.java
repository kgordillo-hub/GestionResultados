package co.mlforex.forecast.gestionResultados.logic;

import co.mlforex.forecast.gestionResultados.model.TransaccionInfo;
import co.mlforex.forecast.gestionResultados.notification.NotificadorSns;
import co.mlforex.forecast.gestionResultados.repository.EntrenaModeloInfo;
import co.mlforex.forecast.gestionResultados.service.GestorResultados;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;


public class InvocadorModelo extends Thread {

    Logger logger = LoggerFactory.getLogger(InvocadorModelo.class);

    private EntrenaModeloInfo entrenaModeloInfo;

    private String topicArnResultadosOut;

    private TransaccionInfo transaccionInfo;

    public InvocadorModelo(TransaccionInfo transaccionInfo, EntrenaModeloInfo entrenaModeloInfo, String topicArnResultadosOut) {
        this.transaccionInfo = transaccionInfo;
        this.entrenaModeloInfo = entrenaModeloInfo;
        this.topicArnResultadosOut = topicArnResultadosOut;
    }

    private void llamarModelo() {
        try {
            if (transaccionInfo.getIdTransaccion() != null) {
                final String endPoint = transaccionInfo.getEndPoint();
                final String requestB64 = transaccionInfo.getRequestB64();
                final String content = requestB64 != null ? new String(Base64.getDecoder().decode(requestB64)) : "";
                String protocol = transaccionInfo.getProtocol();
                HttpResponse status = createHttpCall(endPoint, protocol, content);

                final Integer statusCode = status.statusCode();

                logger.info("Estado transaccion: " + statusCode);
                logger.info("Respuesta transaccion: " + status.body());
                final String resultadoB64 = Base64.getEncoder().encodeToString((status.body() + "").getBytes());
                transaccionInfo.setResultado(resultadoB64);
                transaccionInfo.setRequestB64("");
                final String message = new GsonBuilder().disableHtmlEscaping().create().toJson(transaccionInfo);
                new NotificadorSns().publishMessageSns(message, topicArnResultadosOut);
                transaccionInfo.setRequestB64(requestB64);
                transaccionInfo.setUID(transaccionInfo.generateUID());
                entrenaModeloInfo.save(transaccionInfo);
                logger.info("Mensaje notificado");
            }
        } catch (final Exception e) {
            e.printStackTrace();
            logger.error("Error en InvocadorModelo:llamarModelo" + e.getMessage());
        }
    }

    public HttpResponse createHttpCall(String endPoint, String protocol, String request) throws URISyntaxException, IOException, InterruptedException {
        String contentType = protocol.equalsIgnoreCase("POST") ? "application/json" : "text/plain;charset=UTF-8";
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(endPoint))
                .timeout(Duration.ofSeconds(600))
                .headers("Content-Type", contentType)
                .POST(HttpRequest.BodyPublishers.ofString(request))
                .build();
        //var client = HttpClient.newHttpClient();
        var client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(600)).build();
        var response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return response;
    }

    @Override
    public void run() {
        llamarModelo();
    }
}
