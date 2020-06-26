package dev.el.atm.engine;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtmTest {
    private Atm atm;

    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Before
    public void setUpAtm() {
        atm = Atm.getInstance();
    }

    /**
     * чесно больше нет сил работать ))))
     * сделал проверку на ошибку и тд тп
     */
    @Test
    public void verification_of_the_issuance_of_notes() {
       //Увеличиваем баланс
        atm.putAmount(new BigDecimal(1435000.00));

        //Создаем ожидаемые купюры и делаем выдачу далее сверку
        // Ожидание такие от большего к меньшему по количеству  содержит или нет
        // [500.0, 500.0, 500.0, 200.0, 50.0, 1.0, 1.0]
        List<Double> expectedNotes = Arrays.asList(500.0, 500.0, 500.0, 200.0, 50.0, 1.0, 1.0);
        assertEquals(expectedNotes,atm.takeAmount(new BigDecimal(1752.00)));

    }

    @Test
    public void withdrawal_check() {
        //пытаемся снять сумму которой нет
        exception.expect(IllegalStateException.class);
        atm.takeAmount(new BigDecimal(500.00));
}

}
