package ui;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ItemPageTest {

    @Test
    @DisplayName("System returns message when there are no items added")
    @Tag("ui")
    @Description("Specific message is displayed when 0 items exist")
    public void specificMessageIsDisplayedWhenZeroItemsExist() {
        //TODO: Implement all the steps bellow (Homework)
        /**
         * 1. Login in the system
         * 2. Navigate to Item page (make sure navigation is added in the correct class)
         * 3. Implement deleteAll() method in ItemAPI class to delete all items from the system automatically
         * 4. Implement method to retrieve the message: Не са намерени артикули, отговарящи на зададените критерии.
         * 5. Assert that the message is shown
         * 6. Add one item using createItem from ItemAPI
         * 7. Check that the message is not displayed
         *
         *
         *
         */

    }


}
