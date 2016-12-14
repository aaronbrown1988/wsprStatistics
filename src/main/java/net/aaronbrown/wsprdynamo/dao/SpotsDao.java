package net.aaronbrown.wsprdynamo.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.*;
import net.aaronbrown.wsprdynamo.dto.WSPRSpotDTO;
import net.aaronbrown.wsprdynamo.models.WSPRSpot;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SpotsDao {

    private Table table;
    private AmazonDynamoDBClient client;
    DynamoDB dynamoDB;
    String tableName;

    public SpotsDao() {
        client = new AmazonDynamoDBClient()
                .withEndpoint("http://localhost:8000");

        dynamoDB = new DynamoDB(client);
        tableName = "WSPR_spots";

        table = dynamoDB.getTable(tableName);

        if (dynamoDB.listTables().firstPage().size() != 1) {
            table = createTable(dynamoDB, tableName);
        }
    }

    public List<WSPRSpot> getSpotsForCall(String callsign ){

        List<WSPRSpot> results = new ArrayList<>();
        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#call", "callsign");

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":call", callsign);
        QuerySpec querySpec = new QuerySpec()
                .withKeyConditionExpression("#call = :call ")
                .withNameMap(nameMap)
                .withValueMap(valueMap);

        ItemCollection<QueryOutcome> items = null;
        Iterator<Item> iterator = null;
        Item item = null;

        try {
            items = table.query(querySpec);
            iterator = items.iterator();
            while (iterator.hasNext()) {
                item = iterator.next();
                results.add(WSPRSpotDTO.convertToSpot(item));
            }

        } catch (Exception e) {
            System.err.println("Unable to query for spots by callsign");
            System.err.println(e.getMessage());
        }
        return results;
    }


    private Table createTable(DynamoDB dynamoDB, String tableName) {
        try {
            System.out.println("Attempting to create table; please wait...");
            table = dynamoDB.createTable(tableName,
                    Arrays.asList(
                            new KeySchemaElement("callsign", KeyType.HASH),  //Partition key
                            new KeySchemaElement("spotID", KeyType.RANGE)), //Sort key
                    Arrays.asList(
                            new AttributeDefinition("callsign", ScalarAttributeType.S),
                            new AttributeDefinition("spotID", ScalarAttributeType.N)),
                    new ProvisionedThroughput(100L, 100L));
            table.waitForActive();
            System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());
            return table;

        } catch (Exception e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
            return null;
        }

    }

    public void addSpot(WSPRSpot spot) {
        table.putItem(WSPRSpotDTO.convertToItem(spot));
    }

}