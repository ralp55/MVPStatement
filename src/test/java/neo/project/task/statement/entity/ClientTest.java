package neo.project.task.statement.entity;

import neo.project.task.statement.entity.Client;
import neo.project.task.statement.entity.Employment;
import neo.project.task.statement.entity.Passport;
import neo.project.task.statement.dto.Gender;
import neo.project.task.statement.dto.MaritalStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    @Test
    void testClientEntityGettersAndSetters() {
        Client client = new Client();

        UUID clientId = UUID.randomUUID();
        String lastName = "Иванов";
        String firstName = "Иван";
        String middleName = "Иванович";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        String email = "ivan@example.com";
        Gender gender = Gender.MALE;
        MaritalStatus maritalStatus = MaritalStatus.MARRIED;
        int dependents = 2;
        String accountNumber = "1234567890";

        Passport passport = new Passport();
        Employment employment = new Employment();

        client.setClientId(clientId);
        client.setLastName(lastName);
        client.setFirstName(firstName);
        client.setMiddleName(middleName);
        client.setBirthDate(birthDate);
        client.setEmail(email);
        client.setGender(gender);
        client.setMarualStatus(maritalStatus);
        client.setDependentAmount(dependents);
        client.setPassport(passport);
        client.setEmployment(employment);
        client.setAccountNumber(accountNumber);

        assertEquals(clientId, client.getClientId());
        assertEquals(lastName, client.getLastName());
        assertEquals(firstName, client.getFirstName());
        assertEquals(middleName, client.getMiddleName());
        assertEquals(birthDate, client.getBirthDate());
        assertEquals(email, client.getEmail());
        assertEquals(gender, client.getGender());
        assertEquals(maritalStatus, client.getMarualStatus());
        assertEquals(dependents, client.getDependentAmount());
        assertEquals(passport, client.getPassport());
        assertEquals(employment, client.getEmployment());
        assertEquals(accountNumber, client.getAccountNumber());
    }
}
