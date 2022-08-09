package steps;

import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;
import utils.DBUtils;
import utils.GlobalVariables;

public class QueryDataBase  extends CommonMethods {
    @When("query the information from backend")
    public void query_the_information_from_backend() {
       String query = "select * from hs_hr_employees where employee_id='" + GlobalVariables.empId + " ' ";
       GlobalVariables.tableData = DBUtils.getTableData(query);
    }
    @When("verify the results from UI and Backend")
    public void verify_the_results_from_ui_and_backend() {
         String firstNameFromDB = GlobalVariables.tableData.get(0).get("emp_firstname");
        System.out.println("firstName from dataBase"+firstNameFromDB);
        System.out.println("first name from GUI "+GlobalVariables.firstName);
        Assert.assertEquals(firstNameFromDB,GlobalVariables.firstName);
    }
}
