package config;

public interface Constants {
     //INV
     String BASE_URI = System.getProperty("baseUri", "https://st2016.inv.bg");
     String DOMAIN = System.getProperty("domain", "st2016");
     String BASE_PATH = System.getProperty("basePath", "/v3");
     String BASE_API_URI = System.getProperty("baseAPIUri", "https://api.inv.bg");
     String EMAIL = System.getProperty("email", "karamfilovs@gmail.com");
     String RESTORE_EMAIL = System.getProperty("restoreEmail", "wav4e.test@inbox.testmail.app");
     String PASSWORD = System.getProperty("password", "111111");

     //TEST MAIL
     String TEST_MAIL_BASE_URI = System.getProperty("testMailBaseUri", "https://api.testmail.app");
     String TEST_MAIL_TOKEN = System.getProperty("testMailToken", "eb6c6d8f-2d2c-48f5-8e28-49a1c3d3be5d");
     String TEST_MAIL_BASE_PATH = System.getProperty("testMailBasePath", "/api");



}
