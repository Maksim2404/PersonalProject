package pages;

import base.BaseTest;
import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GraphQLScriptTest extends BaseTest {

    @Test
    public static void graphQLTest() {

        /*Query*/

        int characterId = 6405;

        String response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .body("{\"query\":\"query ($characterId: Int!, $episodeId: Int!) {\\n  character(characterId: $characterId) {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  location(locationId: 7302) {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: $episodeId) {\\n    name\\n    id\\n    air_date\\n  }\\n  characters(filters: {name: \\\"Rahul\\\"}) {\\n    info {\\n      count\\n    }\\n    result {\\n      type\\n      name\\n    }\\n  }\\n  episodes(filters: {episode: \\\"hulu\\\"}) {\\n    result {\\n      name\\n      air_date\\n      id\\n      episode\\n    }\\n  }\\n}\\n\",\"variables\":{\"characterId\":" + characterId + ",\"episodeId\":5235}}")
                .when()
                .post("https://rahulshettyacademy.com/gq/graphql")
                .then()
                .extract()
                .response().asString();

        System.out.println(response);

        JsonPath jsonPath = new JsonPath(response);
        String characterName = jsonPath.getString("data.character.name");

        Assertions.assertThat(characterName).isEqualTo("Robin");

        /*Mutation*/

        String createdCharacterName = "Baskin Robin";

        String mutationResponse = given()
                .log().all()
                .header("Content-Type", "application/json")
                .body("{\"query\":\"mutation($locationName: String!, $characterName: String!, $episodeName: String!)\\n{\\n  createLocation(location: {name: $locationName, type: \\\"Southzone\\\", dimension: \\\"234\\\"})\\n  {\\n    id\\n  }\\n  \\n  createCharacter(character: {name: $characterName, type: \\\"macho\\\", status: \\\"dead\\\", species: \\\"fantasy\\\", gender: \\\"man\\\", image: \\\"path\\\", originId: 7299, locationId: 7299})\\n  {\\n    id\\n  }\\n  \\n  createEpisode(episode: {name: $episodeName, air_date: \\\"24/04/2024\\\", episode: \\\"Test123456\\\"}) \\n  {\\n    id\\n  }\\n  \\n  deleteLocations(locationIds: [7302, 14])\\n  {\\n    locationsDeleted\\n  }\\n}\\n\",\"variables\":{\"locationName\":\"NewZealand\",\"characterName\":\"" + createdCharacterName + "\",\"episodeName\":\"Manifest\"}}")
                .when()
                .post("https://rahulshettyacademy.com/gq/graphql")
                .then()
                .extract()
                .response().asString();

        System.out.println(mutationResponse);

        JsonPath jsonPath1 = new JsonPath(response);
    }
}
