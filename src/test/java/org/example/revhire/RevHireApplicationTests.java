package org.example.revhire;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;NON_KEYWORDS=YEAR",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.mail.host=localhost",
        "spring.mail.port=25",
        "jwt.secret=dGVzdFNlY3JldEtleUZvclRlc3RpbmdPbmx5MTIzNDU2Nzg=",
        "jwt.expiration=86400000"
})
class RevHireApplicationTests {

    @Test
    void contextLoads() {
    }
}