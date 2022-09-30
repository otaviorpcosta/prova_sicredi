package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class createSimulationSteps {

    private static final String BASE_URL = "http://localhost:8080/api/v1";
    RequestSpecification request;
    private static Response response;

    ///////////////////////////////////////// POST //////////////////////////////////////////////////////////////
    @Dado("API POST informando os dados cpf, nome, email, valor, parcelas e seguro")
    public void apiPOST() throws JSONException {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given().log().all();
            request.header("Content-Type", "application/json");
            JSONObject requestParams = new JSONObject();
            requestParams.put("seguro",true);
            requestParams.put("parcelas", 3);
            requestParams.put("valor", 1500);
            requestParams.put("email", "marcelo@teste.com");
            requestParams.put("nome", "Marcelo Freitas");
            requestParams.put("cpf", "22174105047");

            request.body(requestParams.toString());

        }
    }
    @Quando("realizar um requisição para o serviço de {string}")
    public void requisition(String requisition) {

        response = request.post("/" + requisition);
    }
    @Então("o retorno do status code igual a {int}")
    public void statusCode(int arg) {

        Assertions.assertEquals(arg, response.statusCode());
        System.out.println("Código retornado do serviço = " + response.statusCode());
    }
    @E("exibe os dados inseridos como retorno")
    public void listValueRegistered() {

        JsonPath body = response.jsonPath();

        String cpfStringValue = body.get("cpf");
        String nameStringValue = body.get("nome");
        String emailStringValue = body.get("email");
        Integer valueStringValue = body.get("valor");
        Integer portionStringValue = body.get("parcelas");
        Boolean safeStringValue = body.get("seguro");

        Assertions.assertEquals(cpfStringValue, "22174105047");
        Assertions.assertEquals(nameStringValue, "Marcelo Freitas");
        Assertions.assertEquals(emailStringValue, "marcelo@teste.com");
        Assertions.assertEquals(valueStringValue, 1500);
        Assertions.assertEquals(portionStringValue, 3);
        Assertions.assertEquals(safeStringValue, true);

        System.out.println("Dados retornados do serviço" + response.asString());
    }

    /////////////////////////////////////////////////////// PUT ////////////////////////////////////////////////////

    @Dado("API PUT quando alterar o nome do cliente")
    public void apiPUT() throws JSONException {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given();
            request.header("Content-Type", "application/json");
            JSONObject requestParams = new JSONObject();
            requestParams.put("seguro",true);
            requestParams.put("parcelas", 3);
            requestParams.put("valor", 1500);
            requestParams.put("email", "marcelo@teste.com");
            requestParams.put("nome", "Marcia Silva");
            requestParams.put("cpf", "22174105047");

            request.body(requestParams.toString());
        }
    }
    @Quando("realizar um requisição para o serviço de {string} passando o cpf desejado")
    public void requisitionPut(String arg1) {

        response = request.get("/simulacoes");

        JsonPath jsonPathEvaluator = new JsonPath(response.asString());
        String simulationCPF = jsonPathEvaluator.getString("[2].cpf");

        response = request.put("/" + arg1 + "/" + simulationCPF);

    }

    @E("exibe os dados atualizados como retorno")
    public void listSimulationUpdated() {

        JsonPath body = response.jsonPath();
        String nameUpdatedValue = body.get("nome");

        Assertions.assertEquals(nameUpdatedValue, "Marcia Silva");
        System.out.println("Nome retornado do serviço = " + nameUpdatedValue);
    }


    /////////////////////////////////////// GET ////////////////////////////////////////////////////////////////

    @Dado("API GET simulation")
    public void apiGET() {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given();
            request.header("Content-Type", "application/json");
        }
    }

    @Quando("realizar um requisição para o serviço de busca {string}")
    public void requisitionSearch(String requisition) {

        response = request.get("/" + requisition);
    }

    @E("a lista das simulacoes cadastradas")
    public void listSimulations() {

        List<Object> body = response.jsonPath().get();
        String bodyParameter = body.toString();

        Assertions.assertTrue(bodyParameter.contains("id"));
        Assertions.assertTrue(bodyParameter.contains("cpf"));
        Assertions.assertTrue(bodyParameter.contains("nome"));
        Assertions.assertTrue(bodyParameter.contains("email"));
        Assertions.assertTrue(bodyParameter.contains("valor"));
        Assertions.assertTrue(bodyParameter.contains("parcelas"));
        Assertions.assertTrue(bodyParameter.contains("seguro"));

        System.out.println("Dados retornados do serviço" + bodyParameter);

    }

    @Dado("API DELETE")
    public void apiDELETE() {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given().log().all();
            request.header("Content-Type", "application/json");
        }
    }

    @Quando("realizar um requisição para o serviço de {string} com o id da simulacao a ser excluida")
    public String requisitionDelete(String arg0) {

        response = request.get("/" + arg0);

        JsonPath jsonPathEvaluator = new JsonPath(response.asString());
        String simulationId = jsonPathEvaluator.getString("[2].id");

        response = request.delete("/" + arg0 + "/" + simulationId);

        return arg0;

    }

}
