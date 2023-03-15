package co.mlforex.forecast.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.util.json.JSONObject;

import java.io.Serializable;

@DynamoDBTable(tableName = "GestorResultadosInfo")
public class TransaccionInfo implements Serializable {
    //Partition Key
    private String nombreApp;
    private String version;
    private String idUsuario;
    private String idTransaccion;
    //Autogenerado por DB
    private Integer consecutivo;

    //Atributos
    private String [] metricasCalcular;
    private String IP_API;
    private Integer numeroPuerto;
    private JSONObject openAPIFileContent;
    private String datasetLink;
    private Boolean enEntrenamiento;

    //Atributos resultado
    private String endPoint;
    private JSONObject resultado;


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

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String[] getMetricasCalcular() {
        return metricasCalcular;
    }

    public void setMetricasCalcular(String[] metricasCalcular) {
        this.metricasCalcular = metricasCalcular;
    }

    public String getIP_API() {
        return IP_API;
    }

    public void setIP_API(String IP_API) {
        this.IP_API = IP_API;
    }

    public Integer getNumeroPuerto() {
        return numeroPuerto;
    }

    public void setNumeroPuerto(Integer numeroPuerto) {
        this.numeroPuerto = numeroPuerto;
    }

    public JSONObject getOpenAPIFileContent() {
        return openAPIFileContent;
    }

    public void setOpenAPIFileContent(JSONObject openAPIFileContent) {
        this.openAPIFileContent = openAPIFileContent;
    }

    public String getDatasetLink() {
        return datasetLink;
    }

    public void setDatasetLink(String datasetLink) {
        this.datasetLink = datasetLink;
    }

    public Boolean getEnEntrenamiento() {
        return enEntrenamiento;
    }

    public void setEnEntrenamiento(Boolean enEntrenamiento) {
        this.enEntrenamiento = enEntrenamiento;
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

    public JSONObject getResultado() {
        return resultado;
    }

    public void setResultado(JSONObject resultado) {
        this.resultado = resultado;
    }
}
