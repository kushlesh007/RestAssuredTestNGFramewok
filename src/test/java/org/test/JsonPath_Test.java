package org.test;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import testdata.PayloadTestData;

public class JsonPath_Test {

    int sum = 0;

    @Test
    public void jsonPathTest(){

        JsonPath jsonPath = new JsonPath(PayloadTestData.coursePayload());

        // 1. Print No of courses returned by API
        int courseCount = jsonPath.getInt("courses.size()");
        System.out.println(courseCount);

        // 2.Print Purchase Amount
        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

        // 3. Print Title of the first course
        String firstTitle = jsonPath.getString("courses[0].title");
        System.out.println(firstTitle);

        // 4. Print All course titles and their respective Prices
        for(int i=0; i<courseCount; i++){
            System.out.println(jsonPath.get("courses["+i+"].title").toString());
            System.out.println(jsonPath.getInt("courses["+i+"].price"));
        }

        // 5. Print no of copies sold by RPA Course
        for(int i=0; i<courseCount; i++){
            String title = jsonPath.get("courses["+i+"].title");
            if(title.equalsIgnoreCase("RPA")){
                System.out.println(jsonPath.getInt("courses["+i+"].copies"));
            }
            break;
        }

        // 6. Verify if Sum of all Course prices matches with Purchase Amount
        for(int i=0; i<courseCount; i++){
            int price = jsonPath.getInt("courses["+i+"].price");
            int copies = jsonPath.getInt("courses["+i+"].copies");
            int amount = price * copies;
            sum = sum + amount;
        }
        System.out.println(sum);

        int totalAmount = jsonPath.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(totalAmount, sum, "Not Matching");
    }
}
