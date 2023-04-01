package co.mlforex.forecast.gestionResultados.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.google.gson.JsonObject;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@DynamoDBTable(tableName = "GestorResultadosInfo")
public class TransaccionInfo implements Serializable {

    private String UID;

    @DynamoDBAttribute
    private String nombreApp;
    @DynamoDBAttribute
    private String version;
    @DynamoDBAttribute
    private String idUsuario;
    @DynamoDBAttribute
    private String idTransaccion;

    @DynamoDBAttribute
    private List<String> metricasCalcular;

    @DynamoDBAttribute
    private String endPoint;
    @DynamoDBAttribute
    private String requestB64;
    @DynamoDBAttribute
    private String protocol;

    @DynamoDBAttribute
    private String resultado;

    @DynamoDBAttribute
    private String descripcion;


    public TransaccionInfo(){}

    public String getNombreApp() {
        return nombreApp;
    }

    public void setNombreApp(String nombreApp) {
        this.nombreApp = nombreApp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }


    public List<String> getMetricasCalcular() {
        return metricasCalcular;
    }

    public void setMetricasCalcular(List<String> metricasCalcular) {
        this.metricasCalcular = metricasCalcular;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @DynamoDBHashKey(attributeName = "UID")
    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getRequestB64() {
        return requestB64;
    }

    public void setRequestB64(String requestB64) {
        this.requestB64 = requestB64;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String generateUID(){
        return DigestUtils.md5Hex(endPoint);
    }

}
