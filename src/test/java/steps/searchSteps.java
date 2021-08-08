package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import netscape.javascript.JSObject;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class searchSteps {

    private static final String BASE_URL = "https://desafioonline.webmotors.com.br/api/OnlineChallenge";
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

    @Quando("realizar um requisição para o serviço de {string}")
    public void requisition_Make(String arg) {

        response = request.get("/Make");

    }

    @Então("a API irá retornar o status code igual a {int}")
    public void statusCode(int arg) {

        Assertions.assertEquals(arg, response.statusCode());
    }

    @E("exibe a listagem das marcas cadastradas")
    public void listMakes() {

        List<Object> resultListID = response.jsonPath().getList("ID");

        Assertions.assertTrue(resultListID.contains(1));
        Assertions.assertTrue(resultListID.contains(2));
        Assertions.assertTrue(resultListID.contains(3));

        List<Object> resultListName = response.jsonPath().getList("Name");

        Assertions.assertTrue(resultListName.contains("Chevrolet"));
        Assertions.assertTrue(resultListName.contains("Honda"));
        Assertions.assertTrue(resultListName.contains("Ford"));

        System.out.println("IDs encontrados " + resultListID);
        System.out.println("Marcas encontradas " + resultListName);
    }

////////// CT02 //////////

    @Quando("realizar um requisição para o serviço de {string} com o id da marca")
    public void requisition_Model(String arg) {

        response = request.get("/Model?MakeID=2");
    }

    @E("exibe a listagem dos modelos cadastrados")
    public void listModels() {

        List<Object> resultListMakeID = response.jsonPath().getList("MakeID");

        Assertions.assertTrue(resultListMakeID.contains(2));

        List<Object> resultListID = response.jsonPath().getList("ID");

        Assertions.assertTrue(resultListID.contains(4));
        Assertions.assertTrue(resultListID.contains(5));
        Assertions.assertTrue(resultListID.contains(6));

        List<Object> resultListName = response.jsonPath().getList("Name");

        Assertions.assertTrue(resultListName.contains("City"));
        Assertions.assertTrue(resultListName.contains("CRV"));
        Assertions.assertTrue(resultListName.contains("Fit"));

        System.out.println("ID da Marca " + resultListMakeID);
        System.out.println("IDs dos modelos encontrados " + resultListID);
        System.out.println("Modelos encontrados" + resultListName);
    }

    ////////// CT03 //////////

    @Quando("realizar um requisição para o serviço de {string} com o id do modelo")
    public void requisition_Version(String arg) {

        response = request.get("/Version?ModelID=5");
    }

    @E("exibe a listagem das versões cadastradas")
    public void listVersions() {

        List<Object> resultListModelID = response.jsonPath().getList("ModelID");

        Assertions.assertTrue(resultListModelID.contains(5));

        List<Object> resultListID = response.jsonPath().getList("ID");

        Assertions.assertTrue(resultListID.contains(26));
        Assertions.assertTrue(resultListID.contains(27));
        Assertions.assertTrue(resultListID.contains(29));
        Assertions.assertTrue(resultListID.contains(30));

        List<Object> resultListName = response.jsonPath().getList("Name");

        Assertions.assertTrue(resultListName.contains("1.5 DX 16V FLEX 4P AUTOMÁTICO"));
        Assertions.assertTrue(resultListName.contains("1.5 LX 16V FLEX 4P MANUAL"));
        Assertions.assertTrue(resultListName.contains("1.8 16V EVO FLEX FREEDOM OPEN EDITION AUTOMÁTICO"));
        Assertions.assertTrue(resultListName.contains("1.0 MPI EL 8V FLEX 4P MANUAL"));

        System.out.println("ID da Marca " + resultListModelID);
        System.out.println("IDs das versões encontradas " + resultListID);
        System.out.println("Versões encontradas" + resultListName);
    }

    ////////// CT04 //////////

    @Quando("realizar um requisição para o serviço de {string} com o número da página desejada")
    public void requisition_Page(String arg) {

        response = request.get("/Vehicles?Page=1");
    }

    @E("exibe a listagem dos veículos cadastrados")
    public void listVehicles() {


        List<Object> resultListID = response.jsonPath().getList("ID");

        Assertions.assertTrue(resultListID.contains(1));
        Assertions.assertTrue(resultListID.contains(2));
        Assertions.assertTrue(resultListID.contains(3));
        Assertions.assertTrue(resultListID.contains(4));
        Assertions.assertTrue(resultListID.contains(5));
        Assertions.assertTrue(resultListID.contains(6));
        Assertions.assertTrue(resultListID.contains(7));
        Assertions.assertTrue(resultListID.contains(8));
        Assertions.assertTrue(resultListID.contains(9));
        Assertions.assertTrue(resultListID.contains(10));

        List<Object> resultListModel = response.jsonPath().getList("Model");

        Assertions.assertTrue(resultListModel.contains("City"));
        Assertions.assertTrue(resultListModel.contains("Lancer"));
        Assertions.assertTrue(resultListModel.contains("Fit"));
        Assertions.assertTrue(resultListModel.contains("Agile"));
        Assertions.assertTrue(resultListModel.contains("Ecosport"));

        List<Object> resultJson = response.jsonPath().get();

        System.out.println(resultJson);

    }

}
