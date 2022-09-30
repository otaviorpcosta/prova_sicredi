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
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class simulationAlternativeSteps {

    private static final String BASE_URL = "http://localhost:8080/api/v1";
    RequestSpecification request;
    private static Response response;

    @Dado("API POST informando os dados nome, email, valor, parcelas e seguro")
    public void apiPOSTNoCPF() throws JSONException {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given().log().all();
            request.header("Content-Type", "application/json");
            JSONObject requestParams = new JSONObject();
            requestParams.put("seguro", true);
            requestParams.put("parcelas", 3);
            requestParams.put("valor", 1500);
            requestParams.put("email", "marcelo@teste.com");
            requestParams.put("nome", "Marcelo Freitas");

            request.body(requestParams.toString());

        }
    }

    @Quando("realizar requisição para o serviço de {string}")
    public void requisition(String requisition) {

        response = request.post("/" + requisition);
    }

    @Então("o sistema retorna o status code igual a {int}")
    public void statusCode(int arg) {

        Assertions.assertEquals(arg, response.statusCode());
        System.out.println("Código retornado do serviço = " + response.statusCode());
    }

    @E("exibe a lista de {string} retornado do serviço informando {string}")
    public void listError(String error, String messageError) {

        String listErros = response.jsonPath().getString("erros." + error);
        Assertions.assertEquals(listErros, messageError);

        System.out.println(listErros);

    }

    @Dado("API POST informando os dados cpf, email, valor, parcelas e seguro")
    public void apiPOSTNoName() throws JSONException {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given().log().all();
            request.header("Content-Type", "application/json");
            JSONObject requestParams = new JSONObject();
            requestParams.put("seguro", true);
            requestParams.put("parcelas", 3);
            requestParams.put("valor", 1500);
            requestParams.put("email", "marcelo@teste.com");
            requestParams.put("cpf", "22174105047");

            request.body(requestParams.toString());

        }
    }

    @Dado("API POST informando os dados cpf, nome, valor, parcelas e seguro")
    public void apiPOSTNoEmail() throws JSONException {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given().log().all();
            request.header("Content-Type", "application/json");
            JSONObject requestParams = new JSONObject();
            requestParams.put("seguro", true);
            requestParams.put("parcelas", 3);
            requestParams.put("valor", 1500);
            requestParams.put("nome", "Marcelo Freitas");
            requestParams.put("cpf", "22174105047");

            request.body(requestParams.toString());

        }
    }

    @Dado("API POST informando email inválido e demais dados válidos")
    public void apiPOSTEmailInvalid() throws JSONException {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given().log().all();
            request.header("Content-Type", "application/json");
            JSONObject requestParams = new JSONObject();
            requestParams.put("seguro", true);
            requestParams.put("parcelas", 3);
            requestParams.put("valor", 1500);
            requestParams.put("nome", "Marcelo Freitas");
            requestParams.put("cpf", "22174105047");
            requestParams.put("email", "marcelotestecom");

            request.body(requestParams.toString());

        }
    }

    @E("exibe a lista de {string} retornado do serviço informando que o {string} está inválido")
    public void listErrorEmail(String error, String messageError) {

        String listErros = response.jsonPath().getString("erros." + error);

        Assert.assertTrue(listErros.contains("não é um endereço de e-mail")||listErros.contains(messageError));

        System.out.println(listErros);

    }

    @Dado("API POST informando os dados cpf, nome, email, parcelas e seguro")
    public void apiPOSTNoValue() throws JSONException {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given().log().all();
            request.header("Content-Type", "application/json");
            JSONObject requestParams = new JSONObject();
            requestParams.put("seguro", true);
            requestParams.put("parcelas", 3);
            requestParams.put("email", "marcelo@teste.com");
            requestParams.put("nome", "Marcelo Freitas");
            requestParams.put("cpf", "22174105047");

            request.body(requestParams.toString());

        }
    }

    @Dado("API POST informando valor maior que o permitido e demais dados válidos")
    public void apiPOSTValueInvalid() throws JSONException {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given().log().all();
            request.header("Content-Type", "application/json");
            JSONObject requestParams = new JSONObject();
            requestParams.put("seguro", true);
            requestParams.put("parcelas", 3);
            requestParams.put("valor", 500000);
            requestParams.put("nome", "Marcelo Freitas");
            requestParams.put("cpf", "22174105047");
            requestParams.put("email", "marcelo@teste.com");

            request.body(requestParams.toString());

        }
    }

    @Dado("API POST informando os dados cpf, nome, email, valor e seguro")
    public void apiPOSTNoPortion() throws JSONException {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given().log().all();
            request.header("Content-Type", "application/json");
            JSONObject requestParams = new JSONObject();
            requestParams.put("seguro", true);
            requestParams.put("valor", 500);
            requestParams.put("email", "marcelo@teste.com");
            requestParams.put("nome", "Marcelo Freitas");
            requestParams.put("cpf", "22174105047");

            request.body(requestParams.toString());

        }
    }

    @Dado("API POST informando parcela menor que o permitido e demais dados válidos")
    public void apiPOSTPortionSmaller() throws JSONException {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given().log().all();
            request.header("Content-Type", "application/json");
            JSONObject requestParams = new JSONObject();
            requestParams.put("seguro", true);
            requestParams.put("parcelas", 1);
            requestParams.put("valor", 35000);
            requestParams.put("nome", "Marcelo Freitas");
            requestParams.put("cpf", "22174105047");
            requestParams.put("email", "marcelo@teste.com");

            request.body(requestParams.toString());

        }
    }

    @Dado("API POST informando cpf já existente e demais dados válidos")
    public void apiPOSTCPFDuplicated() throws JSONException {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given().log().all();
            request.header("Content-Type", "application/json");
            JSONObject requestParams = new JSONObject();
            requestParams.put("seguro", true);
            requestParams.put("parcelas", 10);
            requestParams.put("valor", 35000);
            requestParams.put("nome", "Marcelo Freitas");
            requestParams.put("cpf", "66414919004");
            requestParams.put("email", "marcelo@teste.com");

            request.body(requestParams.toString());

        }
    }

    @E("exibe a mensagem de {string} retornado do serviço informando {string}")
    public void messageError(String error, String messageError) {

        String listErros = response.jsonPath().getString(error);
        Assertions.assertEquals(listErros, messageError);

        System.out.println(listErros);

    }

    @Dado("API PUT informando cpf que não possui simulação cadastrada")
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
            requestParams.put("cpf", "47185729084");

            request.body(requestParams.toString());
        }
    }
    @Quando("realizar requisição de alteração para o serviço de {string}")
    public void requisitionPut(String arg1) {

        response = request.put("/" + arg1 + "/47185729084");

    }

    @Dado("API GET do cpf desejado")
    public void apiGET() {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given().log().all();
            request.header("Content-Type", "application/json");
        }
    }

    @Quando("realizar um requisição para o serviço de busca {string} informando o cpf desejado")
    public void requisitionSearch(String requisition) {

        response = request.get("/simulacoes");

        JsonPath jsonPathEvaluator = new JsonPath(response.asString());
        String simulationCPF = jsonPathEvaluator.getString("[0].cpf");

        response = request.get("/" + requisition + "/" + simulationCPF);

    }
    @E("os dados da simulação do cpf informado")
    public void CPFSimulation() {

        JsonPath body = response.jsonPath();

        String cpfValue = body.get("cpf");
        String nameValue = body.get("nome");
        String emailValue = body.get("email");

        Assertions.assertEquals(cpfValue, "66414919004");
        Assertions.assertEquals(nameValue, "Fulano");
        Assertions.assertEquals(emailValue, "fulano@gmail.com");

        System.out.println("Dados retornados do serviço " + response.asString());

    }

    @Quando("realizar um requisição para o serviço de busca {string} informando o cpf que não possui simulação")
    public void requisitionSearchCPFInvalid(String requisition) {

        response = request.get("/" + requisition + "/65654754076");

    }

    @Dado("API DELETE com id inexistente")
    public void apiDELETE() {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given().log().all();
            request.header("Content-Type", "application/json");
        }
    }

    @Quando("realizar um requisição para o serviço {string} informando id inexistente")
    public void requisitionDelete(String requisition) {

        response = request.delete("/" + requisition + "/00");

    }

}
