package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeakerRepoImpl implements SpeakerRepository {
    @Override
    public Integer saveSpeaker(Speaker speaker) {
        return 1000;
    }
}

class RegisterBusinessTest {

    @Test
    @DisplayName("ไม่กำหนดชื่อ จะเกิด exception First name is required.")
    public void testCaseFirstName() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            registerBusiness.register(null, new Speaker());
        } catch (ArgumentNullException e ){

        }
    }

    @Test
    @DisplayName("ไม่กำหนดนามสกุล จะเกิด exception Last name is required.")
    public void testCaseLastName() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("Tao");
        try {
            registerBusiness.register(null, speaker);
        } catch (ArgumentNullException e ){
            assertEquals("Last name is required.", e.getMessage());
        }
    }

    @Test
    @DisplayName("ไม่กำหนด email จะเกิด exception Email is required.")
    public void testCaseEmail() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("Tao");
        speaker.setLastName("Tao");
        try {
            registerBusiness.register(null, speaker);
        } catch (ArgumentNullException e ){
            assertEquals("Email is required.", e.getMessage());
        }
    }

    @Test
    @DisplayName("exception Speaker doesn't meet our standard rules.")
    public void testCaseSpeaker() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("Tao");
        speaker.setLastName("Tao");
        speaker.setEmail("tao@email.com");
        try {
            registerBusiness.register(null, speaker);
        } catch (Exception e ){
            assertEquals("Speaker doesn't meet our standard rules.", e.getMessage());
        }
    }

    @Test
    @DisplayName("exception Can't save a speaker.")
    public void testCaseCanNotSaveSpeaker() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("Tao");
        speaker.setLastName("Tao");
        speaker.setEmail("tao@gmail.com");
        try {
            registerBusiness.register(null, speaker);
        } catch (Exception e ){
            assertEquals("Can't save a speaker.", e.getMessage());
        }
    }

    @Test
    @DisplayName("domain ไม่ถูกต้อง length จะเกิด Can't save a speaker.")
    public void case08() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("apirat");
            speaker.setLastName("api");
            speaker.setEmail("apirat@gmail.com@test");
            registerBusiness.register(null, speaker);
            fail();
        } catch (DomainEmailInvalidException e) {
        }
    }

    @Test
    @DisplayName("pass case")
    public void testCasePass() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("Tao");
        speaker.setLastName("Tao");
        speaker.setEmail("tao@gmail.com");
        speaker.setExp(1);
        try {
            registerBusiness.register(null, speaker);
        } catch (Exception e ){
            assertEquals("Can't save a speaker.", e.getMessage());
        }
    }

    @Test
    @DisplayName("get fee experienceYear <= 3")
    public void case10() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("apirat");
            speaker.setLastName("par");
            speaker.setEmail("apirat@gmail.com");
            speaker.setExp(3);
            registerBusiness.register(null, speaker);
            fail();
        } catch (SaveSpeakerException e) {
        }
    }

    @Test
    @DisplayName("get fee experienceYear <= 5")
    public void case11() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("apirat");
            speaker.setLastName("par");
            speaker.setEmail("apirat@gmail.com");
            speaker.setExp(5);
            registerBusiness.register(null, speaker);
            fail();
        } catch (SaveSpeakerException e) {
        }
    }

    @Test
    @DisplayName("get fee experienceYear <= 9")
    public void case12() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("apirat");
            speaker.setLastName("par");
            speaker.setEmail("apirat@gmail.com");
            speaker.setExp(9);
            registerBusiness.register(null, speaker);
            fail();
        } catch (SaveSpeakerException e) {
        }
    }

    @Test
    @DisplayName("สามารถบันทึกข้อมูลได้")
    public void caseSuccess() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("apirat");
        speaker.setLastName("par");
        speaker.setEmail("apirat@gmail.com");
        int result = registerBusiness.register(new SpeakerRepoImpl(), speaker);
        assertEquals(1000 , result);
    }
}