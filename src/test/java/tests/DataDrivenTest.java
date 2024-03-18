package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.dataDrivenTesting.DataDriven;

import java.io.IOException;
import java.util.ArrayList;

public class DataDrivenTest extends BaseTest {

    @Test
    public void dataDrivenTest() throws IOException {

        String testCaseName = "Add Profile";

        DataDriven dataDriven = new DataDriven(getDriver());
        ArrayList<String> data = dataDriven.getData(testCaseName);

        System.out.println(data.get(0));
        System.out.println(data.get(1));
        System.out.println(data.get(2));
        System.out.println(data.get(3));
    }
}
