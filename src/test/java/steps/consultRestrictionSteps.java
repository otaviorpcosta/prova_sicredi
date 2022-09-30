package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class consultRestrictionSteps {

    private static final String BASE_URL = "http://localhost:8080/api/v1";
    RequestSpecification request;
    private static Response response;

    @Dado("API GET")
    public void apiGET() {
        {
            RestAssured.baseURI = BASE_URL;
            request = RestAssured.given();
            request.header("Content-Type", "application/json");
        }
    }

    //////// CT01 ///////////

    @Quando("realizar um requisição para o serviço de {string} com o cpf com restrição {string}")
    public void requisition_cpf(String requisition, String cpf) {

        response = request.get("/" + requisition + "/" + cpf);
    }

    @Então("a API irá retornar o status code igual a {int}")
    public void statusCode(int arg) {

        Assertions.assertEquals(arg, response.statusCode());
        System.out.println("Código retornado do serviço = " + response.statusCode());
    }

    @E("exibe a mensagem informando O CPF {string} possui restrição")
    public void cpfRestrition(String arg) {

        JsonPath body = response.jsonPath();
        String bodyStringValue = body.get("mensagem");

        Assertions.assertEquals(bodyStringValue, "O CPF 97093236014 tem problema", "Response retorna mensagem O CPF 97093236014 tem problema");
        System.out.println("Mensagem retornada do serviço = " + bodyStringValue);
    }
}